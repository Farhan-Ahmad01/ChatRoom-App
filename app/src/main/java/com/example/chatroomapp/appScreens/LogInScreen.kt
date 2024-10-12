package com.example.chatroomapp.appScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.graphics.Color.rgb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatroomapp.data.Resultt
import com.example.chatroomapp.viewModel.AuthViewModel


@Composable
fun LogInScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
    // Also we add a sing in success function that will take the user into the chat room list on successful login
    onSignInSuccess: () -> Unit
) {

    // We then proceed to observe the authResult for cecking the login state.
    val result by authViewModel.authResult.observeAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(rgb(79, 6, 23))),
        verticalArrangement = Arrangement.Bottom
    ) {

        var email by remember{ mutableStateOf("") }

        var password by remember{ mutableStateOf("") }

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(650.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp
                )
            ) // Clip before applying the background
            .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Log-In",
                modifier = Modifier.padding(16.dp),
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(rgb(79, 6, 23))
            )
            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.wrapContentSize()) {

                Text(text = "Email", fontSize = 25.sp,
                    modifier = Modifier.padding(top = 20.dp),
                    color = Color(rgb(79, 6, 23)),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                    label = { Text(text = "Enter your email", color = Color.Black)},
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black),
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                    )
                )

                Text(text = "Password", fontSize = 25.sp,
                    modifier = Modifier.padding(top = 20.dp),
                    color = Color(rgb(79, 6, 23)),
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                    label = { Text(text = "Enter your password", color = Color.Black)},
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black),
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                    )
                )

            }

            Text(text = "Forget password?", modifier = Modifier
                .padding(8.dp)
                .clickable { }, fontSize = 15.sp,
                color = Color.Black
            )

            Button(
                onClick = {
                          authViewModel.login(email, password)
                    when (result) {
                        is Resultt.Success -> {
                            onSignInSuccess()
                        }
                        is  Resultt.Error -> {

                        }
                        else -> {

                        }
                    }
                },
                modifier = Modifier
                    .padding(10.dp)
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors(Color(rgb(79, 6, 23)))
            ) {
                Text(text = "Log In", fontSize = 24.sp, color = Color.White)
            }

            Text(text = "Already have an account? Sign up.",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )

        }
    }
}




@Preview(showBackground = true)
@Composable
fun pwerjew() {
//    LogInScreen({})
}























