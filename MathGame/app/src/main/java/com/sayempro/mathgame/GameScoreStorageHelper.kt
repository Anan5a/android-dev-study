package com.sayempro.mathgame

import android.content.Context
import android.content.SharedPreferences
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class GameScoreStorageHelper {
    val FILENAME = "score.dat"


    fun saveScores(items: ArrayList<Int>, context: Context)
    {
        val fos : FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

        val oas = ObjectOutputStream(fos)
        oas.writeObject(items)
        oas.close()
        fos.close()

    }
    fun saveScore(item: Int, context: Context)
    {
        val fos : FileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

        val oas = ObjectOutputStream(fos)

        val items = this.readScores(context).add(item)
        oas.writeObject(items)
        oas.close()
        fos.close()

    }
    fun readScores(context: Context): ArrayList<Int> {
        return try{
            val fis: FileInputStream = context.openFileInput(FILENAME)

            val ois = ObjectInputStream(fis)
            val obj: ArrayList<Int> = ois.readObject() as ArrayList<Int>
            ois.close()
            fis.close()
            obj
        }catch (e:Exception){
            val itemList = arrayListOf<Int>()
            itemList
        }
    }
}