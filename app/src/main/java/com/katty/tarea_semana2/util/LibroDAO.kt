package com.katty.tarea_semana2.util

import android.content.ContentValues
import android.content.Context
import com.katty.tarea_semana2.entidad.Libro
import java.lang.Exception

class LibroDAO (context: Context){
    private var sqlLiteHelper: SQLiteHelper = SQLiteHelper(context)

    fun registarLibro(libro: Libro):String{
        var respuesta = ""

        val db = sqlLiteHelper.writableDatabase
        try {
            val contentValues = ContentValues()
            contentValues.put("nombre", libro.nombre)
            contentValues.put("autor", libro.autor)
            contentValues.put("anio", libro.anio)

            var resultado = db.insert("libro", null, contentValues)
            if(resultado == -1L){
                respuesta = "Error al insertar el registro"
            }else{
                respuesta = "Registro insertado correctamente"
            }

        }catch (e:Exception){
            respuesta = e.message.toString()
        }finally{
            db.close()
        }

        return  respuesta;

    }


}