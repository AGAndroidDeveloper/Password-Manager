package com.example.passwordmanagerdemo.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanagerdemo.viewmodel.HomeViewmodel


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddAccountBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    viewmodel: HomeViewmodel
) {

    val accountState by viewmodel.accountDetailState.collectAsState()

    ModalBottomSheet(
        modifier = Modifier,
        onDismissRequest = {
            showBottomSheet.value = false
        },
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = MaterialTheme.colorScheme.background,
        scrimColor = MaterialTheme.colorScheme.scrim,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center) {
            TextField(
                value = accountState.accountName,
                onValueChange = { accountName -> viewmodel.addAccountType(accountName) },
                label = {
                    Text(text = "Account Type")
                })

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = accountState.userName,
                onValueChange = { userName -> viewmodel.addUserName(userName) },
                label = {
                    Text(text = "User Name/Email")
                })

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = accountState.password,
                onValueChange = { password -> viewmodel.addPassword(password) },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(20.dp))


        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    val viewmodel: HomeViewmodel = viewModel()
    val showBottomSheet = remember { mutableStateOf(false) }
//    AddAccountBottomSheet(
//        showBottomSheet = showBottomSheet,
//        sheetState = sheetState,
//        viewmodel = viewmodel
//    )
}