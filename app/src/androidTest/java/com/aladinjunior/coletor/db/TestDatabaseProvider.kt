package com.aladinjunior.coletor.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aladinjunior.coletor.main.data.db.AppDatabase


val getTestDatabase = Room.inMemoryDatabaseBuilder(
    ApplicationProvider.getApplicationContext(),
    AppDatabase::class.java
).allowMainThreadQueries().build()


