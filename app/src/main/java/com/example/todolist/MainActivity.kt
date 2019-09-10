package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintStream
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val FILE_NAME = "grades.txt"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarORefrescarLista()
    }

    fun iniciarORefrescarLista(){
        val grades = leerLista()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, grades)
        listaTareas.adapter = adapter
        listaTareas!!.onItemLongClickListener = AdapterView.OnItemLongClickListener{parent, view, position, id ->
            grades!!.removeAt(position)
            adapter!!.notifyDataSetChanged()
            true
        }
    }

    fun guardarNota(view: View) {
        val tarea = editTextEnterHomeWork.text.toString()
        Log.i("CLASE", "Guardando la tarea $tarea")
        val output = PrintStream(
            openFileOutput(FILE_NAME, Context.MODE_APPEND))
        output.println(tarea)
        output.close()
        iniciarORefrescarLista()
    }

    fun leerLista() : ArrayList<String> {
        val listaTareas = arrayListOf<String>()
        try {
            val scan = Scanner(openFileInput(FILE_NAME))
            while (scan.hasNextLine()) {
                val line = scan.nextLine()
                listaTareas.add(line)
            }
            scan.close()

        }
        catch (e: Exception) {
            Log.e("CLASE", "Error al leer archivo", e)

        }
        Log.i("CLASE", "Las notas en el archivo son: $listaTareas")
        return listaTareas
    }


}
