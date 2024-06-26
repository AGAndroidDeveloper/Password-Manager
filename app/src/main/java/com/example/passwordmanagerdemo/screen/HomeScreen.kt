package com.example.passwordmanagerdemo.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerdemo.model.AccountDetail
import com.example.passwordmanagerdemo.ui.theme.backgroundColor
import com.example.passwordmanagerdemo.ui.theme.btnBackgroundColor
import com.example.passwordmanagerdemo.ui.theme.btnColor
import com.example.passwordmanagerdemo.ui.theme.deleteBtnColor
import com.example.passwordmanagerdemo.viewmodel.HomeViewmodel
import androidx.compose.runtime.rememberCoroutineScope as rememberCoroutineScope1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, viewmodel: HomeViewmodel, onFabClick: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val editSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope1()

    var showBottomSheet by remember { mutableStateOf(false) }
    var showEditBottomSheet by remember { mutableStateOf(false) }

    val accountState by viewmodel.accountDetailState.collectAsState()

    val textFieldColor = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        errorContainerColor = Color.White,
        errorTrailingIconColor = Color.Red,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        unfocusedTextColor = Color.LightGray,
        focusedTextColor = Color.DarkGray,
        disabledTextColor = Color.DarkGray,
        unfocusedLabelColor = Color.LightGray,
        focusedLabelColor = Color.DarkGray,
        disabledLabelColor = Color.Transparent,
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Title()
        },
        floatingActionButton = {
            AddAccount {
                showBottomSheet = true
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { padding ->
        Box(contentAlignment = Alignment.BottomEnd) {
            HomeScreenContent(padding, viewmodel) {
                showEditBottomSheet = true
                viewmodel.updateAccountDetailState(it)
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        showBottomSheet = false
                        viewmodel.updateAccountDetailState(
                            AccountDetail(
                                accountName = "",
                                userName = "",
                                password = ""
                            )
                        )
                    },
                    containerColor = backgroundColor,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier.wrapContentHeight(),
                    windowInsets = WindowInsets(0, 0, 0, 0),
                    scrimColor = MaterialTheme.colorScheme.background.copy(.3f)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    TextField(
                        value = accountState.accountName,
                        onValueChange = { accountName -> viewmodel.addAccountType(accountName) },
                        label = {
                            Text(text = "Account Type")
                        },
                        isError = accountState.accountErr,
                        maxLines = 1,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        colors = textFieldColor,
                        shape = RoundedCornerShape(10.dp),

                        )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = accountState.userName,
                        onValueChange = { userName -> viewmodel.addUserName(userName) },
                        label = {
                            Text(text = "User Name/Email")
                        },
                        isError = accountState.userNameErr,
                        maxLines = 1,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(shape = RoundedCornerShape(10.dp), color = backgroundColor)
                            .padding(horizontal = 30.dp),
                        colors = textFieldColor,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = accountState.password,
                        onValueChange = { password -> viewmodel.addPassword(password) },
                        label = {
                            Text(text = "Password")
                        },
                        isError = accountState.passwordErr,
                        maxLines = 1,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = textFieldColor,
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    ElevatedButton(
                        onClick = {
                            viewmodel.addAccountDetailInRoomDb()
                            showBottomSheet = false
                            viewmodel.updateAccountDetailState(
                                AccountDetail(
                                    accountName = "",
                                    userName = "",
                                    password = ""
                                )
                            )

                            // todo Add new account
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(45.dp)
                            .padding(horizontal = 30.dp)
                    ) {
                        Text(text = "Add New Account", fontSize = 16.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(50.dp))

                }
            }
            if (showEditBottomSheet) {
                ModalBottomSheet(
                    sheetState =editSheetState ,
                    onDismissRequest = {
                        showEditBottomSheet = false
                        viewmodel.updateAccountDetailState(
                            AccountDetail(
                                accountName = "",
                                userName = "",
                                password = ""
                            )
                        )
                    },
                    containerColor = backgroundColor,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier.wrapContentHeight(),
                    windowInsets = WindowInsets(0, 0, 0, 0),
                    scrimColor = MaterialTheme.colorScheme.background.copy(.3f)
                ) {

                    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(
                            text = "Account Detail", style = TextStyle(
                                fontSize = 19.sp,
                                color = btnColor,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Account Type", style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = accountState.accountName,
                            onValueChange = { accountName -> viewmodel.addAccountType(accountName) },
                            label = {
                                Text(text = "Account Type")
                            },
                            isError = accountState.accountErr,
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = textFieldColor.copy(
                                unfocusedTextColor = Color.DarkGray
                            ),
                            shape = RoundedCornerShape(10.dp),

                            )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "userName/Email", style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = accountState.userName,
                            onValueChange = { userName -> viewmodel.addUserName(userName) },
                            label = {
                                Text(text = "User Name/Email")
                            },
                            isError = accountState.userNameErr,
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    shape = RoundedCornerShape(10.dp),
                                    color = backgroundColor
                                ),
                            colors = textFieldColor.copy(
                                unfocusedTextColor = Color.DarkGray
                            ),
                        )

                        Spacer(modifier = Modifier.height(30.dp))


                        Text(
                            text = "Password", style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = accountState.password,
                            onValueChange = { password -> viewmodel.addPassword(password) },
                            label = {
                                Text(text = "Password")
                            },
                            isError = accountState.passwordErr,
                            maxLines = 1,
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            colors = textFieldColor.copy(
                                unfocusedTextColor = Color.DarkGray
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                onClick = {
                                    viewmodel.editAccountDetail()
                                    showEditBottomSheet = false
                                    viewmodel.updateAccountDetailState(
                                        AccountDetail(
                                            accountName = "",
                                            userName = "",
                                            password = ""
                                        )
                                    )

                                }, colors = CardDefaults.cardColors(
                                    containerColor = btnBackgroundColor
                                ), modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                shape = RoundedCornerShape(30.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Edit", modifier = Modifier.align(Alignment.Center))
                                }

                            }

                            Card(
                                onClick = {
                                    viewmodel.deleteItem()
                                    showEditBottomSheet = false
                                    viewmodel.updateAccountDetailState(
                                        AccountDetail(
                                            accountName = "",
                                            userName = "",
                                            password = ""
                                        )
                                    )

                                }, colors = CardDefaults.cardColors(
                                    containerColor = deleteBtnColor
                                ), modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                shape = RoundedCornerShape(30.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Delete",
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                    }

                }
            }


        }

    }

}
@Composable
fun HomeScreenContent(
    padding: PaddingValues,
    viewmodel: HomeViewmodel,
    onClick: (AccountDetail) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .background(color = backgroundColor)
    ) {
        val accountList by viewmodel.accountList.collectAsState()
        Log.e("TAG", "HomeScreenContent: $accountList")
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.background.copy(.2f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            GetAccountsFromDatabase(
                accountList,
                Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { account ->
                onClick.invoke(account)
            }
        }
    }

}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
//    HomeScreen(Modifier.fillMaxSize(), viewmodel) {
//
//    }
//    HomeScreen(modifier = Modifier, viewmodel = HomeViewmodel()) {
//
//
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountDetail(
    viewmodel: HomeViewmodel,
    accountDetail: AccountDetail,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    viewmodel.updateAccountDetailState(accountDetail)
    val accountDetailState by viewmodel.accountDetailState.collectAsState()

}