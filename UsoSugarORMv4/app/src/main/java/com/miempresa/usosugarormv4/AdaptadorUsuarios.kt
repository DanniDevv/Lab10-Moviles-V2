package com.miempresa.usosugarormv4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class AdaptadorUsuarios(val ListaUsuarios: ArrayList<Usuario>): RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fNombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val fCorreo = itemView.findViewById<TextView>(R.id.lblCorreo)
        val fEliminar = itemView.findViewById<ImageButton>(R.id.btnEliminar)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.vista_usuario, parent, false);
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdaptadorUsuarios.ViewHolder, position: Int) {
        var user = this.ListaUsuarios.get(position)
        holder?.fNombre?.text= ListaUsuarios[position].nombre
        holder?.fCorreo?.text= ListaUsuarios[position].correo
        holder?.fEliminar?.setOnClickListener {
            var userrepo = UsuarioRepositorio()
            userrepo.borrar(user.id!!)
            this.ListaUsuarios.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
        holder?.itemView?.setOnClickListener(){
            var userrepo = UsuarioRepositorio()
            var usuario = userrepo.leer(user.id!!)

            var editarUsuario = Intent(holder?.itemView?.context,RegistroUsuarios::class.java)
            editarUsuario.putExtra("usuario",usuario as Serializable)
            holder?.itemView?.context?.startActivity(editarUsuario)
        }
    }
    override fun getItemCount(): Int {
        return ListaUsuarios.size
    }
}