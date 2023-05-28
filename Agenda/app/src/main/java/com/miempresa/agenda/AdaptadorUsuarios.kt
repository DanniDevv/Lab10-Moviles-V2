import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import android.widget.ImageButton
import android.content.Intent
import android.widget.Toast
import com.miempresa.agenda.R
import com.miempresa.agenda.RegistroUsuarios
import com.miempresa.agenda.Usuario
import com.miempresa.agenda.UsuarioRepositorio

class AdaptadorUsuarios (val ListaUsuarios: ArrayList<Usuario>): RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fNombre = itemView.findViewById<TextView>(R.id.lblNombre)
        val fCorreo = itemView.findViewById<TextView>(R.id.lblCorreo)
        val fTelefono = itemView.findViewById<TextView>(R.id.lblTelefono)
        val fObservaciones = itemView.findViewById<TextView>(R.id.lblObservaciones)
        val fEliminar = itemView.findViewById<ImageButton>(R.id.btnEliminar)
        val fEditar = itemView.findViewById<ImageButton>(R.id.btnEditar)
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
        holder.fNombre.text = ListaUsuarios[position].nombre
        holder.fCorreo.text = ListaUsuarios[position].correo
        holder.fTelefono.text = ListaUsuarios[position].telefono
        holder.fObservaciones.text = ListaUsuarios[position].observaciones
        holder.fEliminar.setOnClickListener {
            val context = holder.itemView.context
            val confirmDialog = androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Eliminar Usuario")
                .setMessage("¿Estás seguro de que quieres eliminar este usuario?")
                .setPositiveButton("Eliminar") { dialog, which ->
                    var userrepo = UsuarioRepositorio()
                    userrepo.borrar(user.id!!)
                    this.ListaUsuarios.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount)
                    Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .create()
            confirmDialog.show()
        }
        holder.fEditar.setOnClickListener {
            val context = holder.itemView.context
            var userrepo = UsuarioRepositorio()
            var usuario = userrepo.leer(user.id!!)

            var editarUsuario = Intent(context, RegistroUsuarios::class.java)
            editarUsuario.putExtra("usuario", usuario as Serializable)
            context.startActivity(editarUsuario)
        }
    }

    override fun getItemCount(): Int {
        return ListaUsuarios.size
    }
}
