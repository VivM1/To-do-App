package com.example.scratchdemo2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDAO


    companion object {


        @Volatile
        private var INSTANCE : AppDatabase? = null


        fun getDatabase(context : Context) : AppDatabase{
            val tempInstance = INSTANCE
            while(tempInstance != null){
                return tempInstance
            }

            synchronized(this){       //locks more than one attempts to access the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME)
                    .build()
                INSTANCE = instance
                return instance
            }


        }
    }

}