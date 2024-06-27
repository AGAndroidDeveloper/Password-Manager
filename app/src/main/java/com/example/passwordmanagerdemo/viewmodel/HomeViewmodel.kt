package com.example.passwordmanagerdemo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerdemo.model.AccountDetail
import com.example.passwordmanagerdemo.roomdb.repository.AccountDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(private val accountRepository: AccountDetailRepository) :
    ViewModel() {

    private val _showBottomSheet = mutableStateOf(false)
    val showBottomSheet: State<Boolean> = _showBottomSheet


    fun updateSheetState(a: Boolean) {
        _showBottomSheet.value = a
    }

    private val _accountDetailState = MutableStateFlow(AccountDetail())
    val accountDetailState = _accountDetailState.asStateFlow()

    private val _accountList = MutableStateFlow<List<AccountDetail>>(emptyList())
    val accountList = _accountList.asStateFlow()


    init {
        Log.e("TAG", "HomeViewmodel: init")
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.getAllAccount().collect {
                _accountList.value = it
            }
        }
    }

    fun updateAccountDetailState(accountDetail: AccountDetail) {
        _accountDetailState.update { accountDetail }
    }


    fun resetAccountDetailState() {
        _accountDetailState.update { AccountDetail() }
    }

    fun addAccountType(accountName: String) {
        _accountDetailState.update {
            it.copy(accountName = accountName, accountErr = accountName.isBlank())
        }
    }

    fun addPassword(password: String) {
        _accountDetailState.update {
            it.copy(password = password, passwordErr = password.isBlank())
        }
    }


    fun addUserName(userName: String) {
        _accountDetailState.update {
            it.copy(userName = userName, userNameErr = userName.isBlank())
        }
    }

    fun addAccountDetailInRoomDb() {
        val accountDetail = _accountDetailState.value
        val newAccountDetail = accountDetail.copy(
            accountName = accountDetail.accountName,
            userName = accountDetail.userName,
            password = accountDetail.password,
            accountErr = accountDetail.accountName.isBlank(),
            userNameErr = accountDetail.userName.isBlank(),
            passwordErr = accountDetail.password.isBlank()
        )

        _accountDetailState.update { newAccountDetail }
        if (newAccountDetail.accountErr || newAccountDetail.userNameErr || newAccountDetail.passwordErr) {
            // Validation failed, return early
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.insertAccountDetail(accountDetail)
        }

        updateSheetState(false)

    }

    fun deleteItem() {
        val accountDetail = _accountDetailState.value
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.deleteItem(accountDetail)
        }
        resetAccountDetailState()
    }

    fun editAccountDetail() {
        val accountDetail = _accountDetailState.value
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.updateItem(accountDetail)
        }
        resetAccountDetailState()
    }
}
