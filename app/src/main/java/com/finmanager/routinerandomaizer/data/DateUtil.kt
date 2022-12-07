package com.finmanager.routinerandomaizer.data

import android.os.Build
import com.finmanager.routinerandomaizer.domain.models.Task
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface DateUtilInterface{
    fun getWakeUpDate(task: Task):String
    fun String.convertToDate(): Date
    fun toWake(sleepDate:String, wakeUpDate:String):Boolean
    fun now():String
}

class DateUtil@Inject constructor(

):DateUtilInterface {

    private val formatDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat("dd.MM.yyyy", Locale.US)
    } else {
        TODO("VERSION.SDK_INT < N")
    }

    override fun getWakeUpDate(task: Task):String{
        val date = Calendar.getInstance()
        //date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)+task.period)
        date.set(Calendar.MONTH, date.get(Calendar.MONTH))
        date.set(Calendar.YEAR, date.get(Calendar.YEAR))
        return formatDate.format(date.time)
    }

    override fun String.convertToDate(): Date {

        return formatDate.parse(this)!!
    }

    override fun toWake(sleepDate:String, wakeUpDate:String):Boolean{

        return wakeUpDate.convertToDate() < 1

    }

     override fun now():String {
         val date = Calendar.getInstance()


         return formatDate.format(date.time)
    }
}