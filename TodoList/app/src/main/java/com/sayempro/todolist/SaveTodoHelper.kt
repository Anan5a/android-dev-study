package com.sayempro.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SaveTodoHelper {
    val FILENAME = "listinfo.dat"


    fun writeData(items: ArrayList<String>, context: Context)
    {
        val fos : FileOutputStream=context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

        val oas = ObjectOutputStream(fos)
        oas.writeObject(items)
        oas.close()
        fos.close()

    }
    fun readData(context: Context): ArrayList<String> {
        return try{
            val fis: FileInputStream = context.openFileInput(FILENAME)

            val ois = ObjectInputStream(fis)
            val obj: ArrayList<String> = ois.readObject() as ArrayList<String>
            ois.close()
            fis.close()
            obj
        }catch (e:Exception){
            val itemList = arrayListOf<String>()
            itemList
        }
    }


}