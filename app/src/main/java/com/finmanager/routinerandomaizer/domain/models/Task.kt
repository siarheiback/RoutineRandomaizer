package com.finmanager.routinerandomaizer.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.finmanager.routinerandomaizer.db.TaskDatabase.Companion.dbName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = dbName)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String?,
    var description:String?
): Parcelable