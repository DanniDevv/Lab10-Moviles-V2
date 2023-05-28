package com.miempresa.usosugarormv4
import com.orm.SugarRecord
import com.orm.dsl.Table
import java.io.Serializable

@Table
data class Usuario (
    var id: Long? = null,
    var nombre: String? = null,
    var correo: String? = null,
    var clave: String? = null,): Serializable {
    constructor(nombre: String?,correo: String?,clave: String?) : this (){
        this.nombre = nombre
        this.correo = correo
        this.clave = clave
    }
}