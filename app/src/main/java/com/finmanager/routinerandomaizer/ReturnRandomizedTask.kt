package com.finmanager.routinerandomaizer

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ReturnRandomizedTask@Inject constructor(
    @ApplicationContext private val context: Context,
):ReturnTask  {

    override val lastID = context.getSharedPreferences(last, Context.MODE_PRIVATE)

    override fun saveTask(id:Int) {
        lastID.edit().putInt(savedID, id).apply()
    }

    override fun returnTask():Int {
       return lastID.getInt(savedID, 0)
    }


}
const val last = "lastId"
const val savedID = "id"