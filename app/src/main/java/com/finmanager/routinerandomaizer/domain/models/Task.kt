package com.finmanager.routinerandomaizer.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.finmanager.routinerandomaizer.db.TaskDatabase.Companion.dbName
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = dbName)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String?,
    val description:String?,
    val isSleeping:Boolean = false,
    val isActive:Boolean = false,
    val period:Short,
    val sleepDate: String?,
    val wakeUpDate: String?
): Parcelable

