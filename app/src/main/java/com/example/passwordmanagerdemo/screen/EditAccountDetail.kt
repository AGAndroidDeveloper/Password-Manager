package com.example.passwordmanagerdemo.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerdemo.model.AccountDetail
import com.example.passwordmanagerdemo.ui.theme.backgroundColor
import com.example.passwordmanagerdemo.ui.theme.btnBackgroundColor
import com.example.passwordmanagerdemo.ui.theme.btnColor
import com.example.passwordmanagerdemo.ui.theme.deleteBtnColor
import com.example.passwordmanagerdemo.viewmodel.HomeViewmodel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditDeleteBottomSheet(
    addOrEditAccountState: MutableIntState,
    viewmodel: HomeViewmodel,
    accountState: AccountDetail,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val textFieldColor = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        errorContainerColor = Color.White,
        errorTrailingIconColor = Color.Red,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        unfocusedTextColor = Color.DarkGray,
        focusedTextColor = Color.Black,
        disabledTextColor = Color.LightGray,
        unfocusedLabelColor = Color.LightGray,
        focusedLabelColor = Color.DarkGray,
        disabledLabelColor = Color.Transparent,
        focusedTrailingIconColor = Color.Black,
        unfocusedTrailingIconColor = Color.LightGray,
        disabledTrailingIconColor = Color.LightGray,
    )


    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
           viewmodel.updateSheetState(false)
        },
        containerColor = backgroundColor,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier.wrapContentHeight(),
        windowInsets = WindowInsets(0, 0, 0, 0),
        scrimColor = MaterialTheme.colorScheme.background.copy(.3f)
    ) {
        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            AnimatedVisibility(visible = addOrEditAccountState.intValue == 1) {
                Column {
                    Text(
                        text = "Account Detail", style = TextStyle(
                            fontSize = 19.sp,
                            color = btnColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            Text(
                text = "Account Type", style = TextStyle(
                    color = Color.Black.copy(.8f),
                    fontSize = 13.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = accountState.accountName,
                onValueChange = { accountName -> viewmodel.addAccountType(accountName) },
                isError = accountState.accountErr,
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontWeight = FontWeight.Medium),
                colors = textFieldColor.copy(),
                shape = RoundedCornerShape(12.dp),
                )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "UserName", style = TextStyle(
                    color = Color.Black.copy(.8f),
                    fontSize = 13.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = accountState.userName,
                onValueChange = { userName -> viewmodel.addUserName(userName) },
                textStyle = TextStyle(fontWeight = FontWeight.Medium),
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
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Password", style = TextStyle(
                    color = Color.Black.copy(.8f),
                    fontSize = 13.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            TextField(
                value = accountState.password,
                onValueChange = { password -> viewmodel.addPassword(password) },
                isError = accountState.passwordErr,
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(fontWeight = FontWeight.Medium),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                colors = textFieldColor.copy(
                    unfocusedTextColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    if (passwordVisible) {
                        IconButton(onClick = { passwordVisible = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { passwordVisible = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ClickEventEditOrDelete(viewmodel, addOrEditAccountState)
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
private fun ClickEventEditOrDelete(
    viewmodel: HomeViewmodel,
    addOrEditAccountState: MutableIntState
) {
    Column(verticalArrangement = Arrangement.Center) {

        AnimatedVisibility(visible = addOrEditAccountState.intValue == 1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    onClick = {
                        viewmodel.editAccountDetail()
                       viewmodel.updateSheetState(false)

                    }, colors = CardDefaults.cardColors(
                        containerColor = btnBackgroundColor
                    ), modifier = Modifier
                        .weight(2f),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Edit",
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }

                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.5f)
                )
                Card(
                    onClick = {
                        viewmodel.deleteItem()
                       viewmodel.updateSheetState(false)
                    }, colors = CardDefaults.cardColors(
                        containerColor = deleteBtnColor
                    ), modifier = Modifier
                        .weight(2f),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Delete",
                            modifier = Modifier.align(Alignment.Center), style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        AnimatedVisibility(visible = addOrEditAccountState.intValue == 0) {
            ElevatedButton(
                onClick = {
                    viewmodel.addAccountDetailInRoomDb()
                   // showEditBottomSheet.value = false
                    // todo Add new account
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(45.dp)
                    .padding(horizontal = 30.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Text(text = "Add New Account", fontSize = 16.sp, color = Color.White)
            }
        }

    }

}