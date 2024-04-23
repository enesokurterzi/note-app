package com.example.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.login.R
import com.example.login.UserTextFieldState

@Composable
fun PagerContent(
    emailTitle: String,
    emailState: UserTextFieldState,
    onEmailChange: (String) -> Unit,
    passwordTitle: String,
    passwordState: UserTextFieldState,
    onPasswordChange: (String) -> Unit,
    buttonTitle: String,
    onButtonClick: () -> Unit
) {
    val resources = LocalContext.current.resources
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(0.8f),
            text = emailTitle,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            singleLine = true,
            shape = RoundedCornerShape(32.dp),
            isError = emailState.isErrorVisible,
            supportingText = {
                if (emailState.isErrorVisible) {
                    Text(text = resources.getString(emailState.error))
                }
            },
            value = emailState.text,
            onValueChange = onEmailChange
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(0.8f),
            text = passwordTitle,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            singleLine = true,
            shape = RoundedCornerShape(32.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordState.isErrorVisible,
            supportingText = {
                if (passwordState.isErrorVisible) {
                    Text(text = resources.getString(passwordState.error))
                }
            },
            value = passwordState.text,
            onValueChange = onPasswordChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.85f),
            shape = RoundedCornerShape(16.dp),
            onClick = onButtonClick
        ) {
            Text(style = MaterialTheme.typography.titleMedium, text = buttonTitle)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(id = R.string.or_continue)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(style = MaterialTheme.typography.titleMedium, text = "Google")
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(style = MaterialTheme.typography.titleMedium, text = "Facebook")
            }
        }
    }

}