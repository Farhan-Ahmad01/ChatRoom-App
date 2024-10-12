package com.example.chatroomapp.appScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatroomapp.data.Room
import eu.tutorials.chatroomapp.viewmodel.RoomViewModel


@Composable
fun ChatRoomListScreen(
    roomViewModel: RoomViewModel = viewModel()
) {

    val rooms by roomViewModel.rooms.observeAsState(emptyList())

    // we will create two state, one for opening and closing of the dialog used to create a new chatroom and the other for the room name.
    var showDialog by remember { mutableStateOf(false)}
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize().background(color = Color(rgb(246,237,236)))
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Text("Chat Rooms",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(rgb(79, 6, 23)),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display a list of chat rooms
        LazyColumn {
            items(rooms) {room ->
                RoomItem(room = room)
            }
        } // lazycolumn ends here

        Spacer(modifier = Modifier.height(16.dp))

        // Button to create a new room
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.width(200.dp).align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color(rgb(79, 6, 23))),

        ) {
            Text("Create Room", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.ExtraBold)
        } // rowscope ends here

        if (showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = true },
                title = { Text("Create a new room", color = Color(rgb(79, 6, 23))) },
                containerColor = Color(rgb(246,237,236)),
                text={
                    OutlinedTextField(
                        value = name,
                        label = { Text(text = "Enter Room name", color = Color.Black)},
                        onValueChange = { name = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
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
                }, confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                if (name.isNotBlank()) {
                                    showDialog = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(Color(rgb(79, 6, 23)))
                        ) {
                            Text("Add", color = Color.White)
                        }
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(Color(rgb(79, 6, 23)))
                        ) {
                            Text("Cancel", color = Color.White)
                        }
                    }
                })
        }

    } // first coloumn ends here

} // main function closed


@Composable
fun RoomItem(room: Room) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = room.name, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        OutlinedButton(
            onClick = {  },
            colors = ButtonDefaults.outlinedButtonColors()
        ) {
            Text("Join", color = Color(rgb(79, 6, 23)))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun preve() {
    RoomItem(room = Room("Name", "Name"))
}
