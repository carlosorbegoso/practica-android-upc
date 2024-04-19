package com.katty.tarea_semana2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.katty.tarea_semana2.entidad.Libro
import com.katty.tarea_semana2.util.LibroDAO

class FormUserActivity : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtAutor: EditText
    private lateinit var txtAnio: EditText
    private lateinit var btnGuardar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        asignarReferencias()
    }
    private fun asignarReferencias() {
        txtNombre = findViewById(R.id.txtNombre)
        txtAutor = findViewById(R.id.textAutor)
        txtAnio = findViewById(R.id.textAnioPublicacion)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            registrarLibro()
        }
    }
    fun registrarLibro(){

        var nombre = txtNombre.text.toString()
        var autor = txtAutor.text.toString()
        var anio = txtAnio.text.toString()
        var registro = true
        if(nombre.isEmpty()){
            registro = false
            txtNombre.error = "El nombre es requerido"
        }
        if(autor.isEmpty()){
            registro = false
            txtAutor.error = "El autor es requerido"
        }
        if(anio.isEmpty()){
            registro = false
            txtAnio.error = "El año es requerido"
        }
        if(registro){
            val objeto = Libro()
            objeto.nombre = nombre
            objeto.autor = autor
            objeto.anio = anio.toInt()

            val libroDAO = LibroDAO(this)
            val mensaje = libroDAO.registarLibro(objeto)
            mostrarMensaje(mensaje)
        }



    }

    private fun mostrarMensaje(mensaje: String) {
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", null)
        ventana.create().show()

    }

}