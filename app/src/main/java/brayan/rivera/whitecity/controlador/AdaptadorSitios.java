package brayan.rivera.whitecity.controlador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.data.modelos.Sitio;
import brayan.rivera.whitecity.ui.detalle.DetalleActivity;


public class AdaptadorSitios extends RecyclerView.Adapter<AdaptadorSitios.SitioViewHolder> {

    private ArrayList<Sitio> sitios;
    //escuchador onclicklistener
    private Context context;


    public AdaptadorSitios(Context context) {
        this.context = context;
        sitios = new ArrayList<>();
    }

    //metodo para inflar la platilla personalizada y retornarla
    @NonNull
    @Override
    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_item, parent, false);
        SitioViewHolder holder = new SitioViewHolder(v);
        return holder;
    }

    //metodo para asignar los valores a cada elemento de la plantilla personalizada
    @Override
    public void onBindViewHolder(@NonNull final SitioViewHolder holder, final int position) {
        Sitio sitio = sitios.get(position);
        holder.nombre.setText(sitios.get(position).getNombre());

        // cargar imagen guardada en firebase
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("fotos/" + sitio.getImagenPath()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri)
                        .resize(600, 900)
                        .centerInside()
                        .into(holder.imagen);
            }
        });

        // set ver mas listener
        holder.verMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalleActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sitios.size();
    }

    public ArrayList<Sitio> getSitios() {
        return sitios;
    }

    public void setSitios(ArrayList<Sitio> sitios) {
        this.sitios = sitios;
    }

    //metodo para instaciar cada elemento de la plantilla perzonalizada
    class SitioViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView imagen;
        TextView verMas;

        public SitioViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.titulo_lugar);
            this.imagen = itemView.findViewById(R.id.imagen_lugar);
            this.verMas = itemView.findViewById(R.id.ver_mas);
        }
    }

}
