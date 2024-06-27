package com.example.passwordmanagerdemo.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordmanagerdemo.R
import com.example.passwordmanagerdemo.model.AccountDetail

@Composable
fun GetAccountsFromDatabase(
    accountList: List<AccountDetail>,
    modifier: Modifier,
    onFabClick: (accountDetail: AccountDetail) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(20.dp)) {
        items(accountList.size) { index ->
            AccountItem(accountDetail = accountList[index]) { it ->
                onFabClick.invoke(it)
            }

            Spacer(
                modifier = Modifier.height(
                    20.dp
                )
            )
        }
    }
}

@Composable
fun AccountItem(accountDetail: AccountDetail, onClick: (AccountDetail) -> Unit) {
    ElevatedCard(
        onClick = {
            onClick.invoke(accountDetail)
        },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp,
                    horizontal = 20.dp
                ), // Adjust the vertical padding as needed
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = accountDetail.accountName,
                fontSize = 20.sp, color = Color.Black
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = accountDetail.password, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.Black.copy(.5f),
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }

    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Account() {
    AccountItem(
        accountDetail = AccountDetail(
            userName = "ss",
            accountName = "ss",
            password = "ss"
        )
    ) {

    }
}
