package com.miempresa.agenda

import AdaptadorUsuarios
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lista.layoutManager = LinearLayoutManager(this);

        var usuariorepo = UsuarioRepositorio()
        var listaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorUsuarios(listaUsuarios as ArrayList<Usuario>)
        lista.adapter = adapter

        btnAgregar.setOnClickListener {
            val intent = Intent(this, RegistroUsuarios::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        var usuariorepo = UsuarioRepositorio()
        var listaUsuarios = usuariorepo.listar()

        val adapter = AdaptadorUsuarios(listaUsuarios as ArrayList<Usuario>)
        lista.adapter = adapter
    }
}