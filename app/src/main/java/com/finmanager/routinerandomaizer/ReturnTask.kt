package com.finmanager.routinerandomaizer

import android.content.SharedPreferences

interface ReturnTask {
    val lastID:SharedPreferences

    fun saveTask(id:Int)
    fun returnTask():Int
}