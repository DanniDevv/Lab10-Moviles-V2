package com.miempresa.usosugarormv4
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro_usuarios.*

class RegistroUsuarios : AppCompatActivity() {

    var edita: Boolean = false
    var id: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuarios)

        btnRegistrar.setOnClickListener (){
            var nombre = txtNombre.text.toString()
            var correo = txtCorreo.text.toString()
            var clave = txtClave.text.toString()

            if (nombre.isEmpty()||correo.isEmpty()||clave.isEmpty()){
                Toast.makeText(this,"algunos campos estan vacios",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            if (edita){
                var usuariorepo = UsuarioRepositorio()
                usuariorepo.actualizar(id, nombre, correo, clave)
            }else{
                var usuariorepo = UsuarioRepositorio()
                usuariorepo.crear(nombre, correo, clave)
            }
            finish()

            /*var datoGuardado = usuariorepo.listar().size
            Toast.makeText(this,"Datos guardados:\n" + datoGuardado,Toast.LENGTH_LONG).show()*/
        }

        var recibidos:Bundle? = intent.extras
        if (recibidos != null) {
            var usuario = recibidos?.get("usuario") as Usuario
            edita = true
            id = usuario.id!!
            txtNombre.setText(usuario.nombre)
            txtCorreo.setText(usuario.correo)
            txtClave.setText(usuario.clave)
        }
    }
}