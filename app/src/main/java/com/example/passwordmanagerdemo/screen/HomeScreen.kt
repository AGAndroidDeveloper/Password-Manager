package com.example.passwordmanagerdemo.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanagerdemo.model.AccountDetail
import com.example.passwordmanagerdemo.ui.theme.backgroundColor
import com.example.passwordmanagerdemo.viewmodel.HomeViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier, viewmodel: HomeViewmodel) {

    val accountState by viewmodel.accountDetailState.collectAsState()
    val addOrEditAccountState = rememberSaveable {
        mutableIntStateOf(0)
    }

    if (addOrEditAccountState.intValue == 0) {
        viewmodel.resetAccountDetailState()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Title()
        },
        floatingActionButton = {
            AddAccount {
                addOrEditAccountState.intValue = 0
                viewmodel.updateSheetState(true)

            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) { padding ->
        Box(contentAlignment = Alignment.BottomEnd) {
            HomeScreenContent(padding, viewmodel) {
              viewmodel.updateSheetState(true)
                addOrEditAccountState.intValue = 1
                viewmodel.updateAccountDetailState(it)
               // viewmodel.resetAccountDetailState(it)
            }

            if (viewmodel.showBottomSheet.value) {
                EditDeleteBottomSheet(
                    addOrEditAccountState,
                    viewmodel,
                    accountState
                )
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

