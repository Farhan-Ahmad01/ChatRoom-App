package com.example.chatroomapp.repository

import com.example.chatroomapp.data.Resultt
import com.example.chatroomapp.data.Room
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RoomRepository(private val firestore: FirebaseFirestore) {

    suspend fun createRoom(name: String): Resultt<Unit> = try {
        val room = Room(name = name)
        firestore.collection("rooms").add(room).await()
        Resultt.Success(Unit)
    } catch (e: Exception) {
        Resultt.Error(e)
    }

    suspend fun getRooms(): Resultt<List<Room>> = try {
        val querySnapshot = firestore.collection("rooms").get().await()
        val rooms = querySnapshot.documents.map {
            document ->
            document.toObject(Room::class.java)!!.copy(id = document.id)
        }
        Resultt.Success(rooms)
    } catch (e: Exception) {
        Resultt.Error(e)
    }
}
