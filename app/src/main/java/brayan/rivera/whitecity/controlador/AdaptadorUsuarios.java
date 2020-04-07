package brayan.rivera.whitecity.controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.data.modelos.Usuario;


public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuarioViewHolder> {

    private ArrayList<Usuario> usuarios;
    //escuchador onclicklistener


    public AdaptadorUsuarios() {
        usuarios = new ArrayList<>();
    }

    //metodo para inflar la platilla personalizada y retornarla
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item, parent, false);
        UsuarioViewHolder holder = new UsuarioViewHolder(v);
        return holder;
    }

    //metodo para asignar los valores a cada elemento de la plantilla personalizada
    @Override
    public void onBindViewHolder(@NonNull final UsuarioViewHolder holder, final int position) {
        final Usuario sitio = usuarios.get(position);
        holder.nombre.setText(sitio.getNombre());
        holder.correo.setText(sitio.getCorreo());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    //metodo para instaciar cada elemento de la plantilla perzonalizada
    class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView correo;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.nombre_usuario_item);
            this.correo = itemView.findViewById(R.id.correo_usuario_item);
        }
    }

}