package com.example.passwordmanagerdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerdemo.model.AccountDetail
import com.example.passwordmanagerdemo.roomdb.repository.AccountDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewmodel @Inject constructor(private val accountRepository: AccountDetailRepository) :
    ViewModel() {
    private val _accountDetailState = MutableStateFlow(AccountDetail())
    val accountDetailState = _accountDetailState.asStateFlow()

    private val _accountList = MutableStateFlow<List<AccountDetail>>(emptyList())
    val accountList = _accountList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.getAllAccount().collect {
                _accountList.value = it
            }
        }

    }

    fun updateAccountDetailState(accountDetail: AccountDetail) {
        _accountDetailState.update { accountDetail }
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
        // todo if nothing is empty call then add new account


    }


    fun deleteItem(){
        val accountDetail = _accountDetailState.value
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.deleteItem(accountDetail)
        }
    }


    fun editAccountDetail() {
        val accountDetail = _accountDetailState.value
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.updateItem(accountDetail)
        }
    }
}
