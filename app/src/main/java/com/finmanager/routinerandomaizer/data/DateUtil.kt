package com.finmanager.routinerandomaizer.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.finmanager.routinerandomaizer.domain.models.Task
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

interface DateUtilInterface{
    fun getWakeUpDate(task: Task):String
    fun String.convertToDate(): Date
    fun toWake(task: Task):Boolean
    fun now():String
}

class DateUtil@Inject constructor(

):DateUtilInterface {

    private val formatDate = SimpleDateFormat("dd.MM.yyyy", Locale.US)


    override fun getWakeUpDate(task: Task):String{
        val date = Calendar.getInstance()
        date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)+task.period)
        date.set(Calendar.MONTH, date.get(Calendar.MONTH))
        date.set(Calendar.YEAR, date.get(Calendar.YEAR))
        return formatDate.format(date.time)
    }

    override fun String.convertToDate(): Date {
        return formatDate.parse(this)!!
    }

    override fun toWake(task: Task):Boolean{
        return if (task.wakeUpDate!=null) {task.wakeUpDate.convertToDate() < now().convertToDate()}
        else{false}

    }

     override fun now():String {
         val date = Calendar.getInstance()
         return formatDate.format(date.time)
    }
}