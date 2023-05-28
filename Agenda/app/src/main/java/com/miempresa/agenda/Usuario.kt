package com.miempresa.agenda
import com.orm.dsl.Table
import java.io.Serializable
@Table
data class Usuario (
    var id: Long? = null,
    var nombre: String? = null,
    var correo: String? = null,
    var telefono: String? = null,
    var observaciones: String? = null,): Serializable {
        constructor(nombre:String?, correo:String?, telefono:String?, observaciones:String?):this(){
            this.nombre = nombre
            this.correo = correo
            this.telefono = telefono
            this.observaciones = observaciones
        }
    }