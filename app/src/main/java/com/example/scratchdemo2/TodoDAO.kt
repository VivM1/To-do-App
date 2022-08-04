package com.example.scratchdemo2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDAO{

                                                    //here we insert it --- that's what she said but anyways ...
    @Insert()                                       // on-conflict is for replacing if the idea exists isliye use nahi kiya
    suspend fun insertTask(todoModel: TodoModel)

    @Query("SELECT * from TodoModel WHERE isFinished != -1")
    fun getTask() : LiveData<List<TodoModel>>

    @Query("UPDATE TodoModel Set isFinished =1 where id=:uid")
    fun finishTask(uid:Long)

    @Query("Delete  from TodoModel where id=:uid")
    fun deleteTask(uid:Long)






}