package com.androiddevs.mvvmnewsapp.database

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.data.Source

class Convertors {
    @TypeConverter
    fun fromSource(srce: Source):String{
        return srce.name
    }
    @TypeConverter
    fun toSource(s:String):Source{
        return Source(s,s)
    }
}