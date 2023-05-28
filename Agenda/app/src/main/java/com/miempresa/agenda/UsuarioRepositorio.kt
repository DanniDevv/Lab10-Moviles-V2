package com.miempresa.agenda

import com.orm.SugarRecord

class UsuarioRepositorio {
    fun crear(nombre:String,correo:String,telefono:String,observaciones:String){
        var usuario = Usuario(nombre,correo,telefono,observaciones)
        SugarRecord.save(usuario)
    }
    fun listar():List<Usuario>{
        var usuario = SugarRecord.listAll(Usuario::class.java)
        return usuario
    }
    fun borrar(id:Long){
        var usuario: Usuario = SugarRecord.findById(Usuario::class.java,id)
        SugarRecord.delete(usuario)
    }
    fun leer(id:Long): Usuario {
        var usuario: Usuario = SugarRecord.findById(Usuario::class.java,id)
        return usuario
    }
    fun actualizar(id:Long, nombre:String, correo:String, telefono:String, observaciones:String){
        var usuario: Usuario = SugarRecord.findById(Usuario::class.java,id)
        usuario.nombre = nombre
        usuario.correo = correo
        usuario.telefono = telefono
        usuario.observaciones = observaciones
        SugarRecord.save(usuario)
    }
}