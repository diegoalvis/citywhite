package brayan.rivera.whitecity.controlador;

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


public class AdaptadorSitios extends RecyclerView.Adapter<AdaptadorSitios.SitioViewHolder> implements View.OnClickListener {

    private ArrayList<Sitio> sitios;
    //escuchador onclicklistener
    private View.OnClickListener onClickListener;


    public AdaptadorSitios(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        sitios = new ArrayList<>();
    }

    //metodo para inflar la platilla personalizada y retornarla
    @NonNull
    @Override
    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_item, parent, false);
        v.setOnClickListener(this);
        SitioViewHolder holder = new SitioViewHolder(v);
        return holder;
    }

    //metodo para asignar los valores a cada elemento de la plantilla personalizada
    @Override
    public void onBindViewHolder(@NonNull final SitioViewHolder holder, final int position) {
        Sitio sitio = sitios.get(position);
        holder.nombre.setText(sitios.get(position).getNombre());

        // cargar imagen alamcenada en firebase
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        storageRef.child("Fotos/Iglesias/" + sitio.getNombreimagen()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.imagen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sitios.size();
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
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

        public SitioViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.titulo_lugar);
            this.imagen = itemView.findViewById(R.id.imagen_lugar);
        }
    }

    public void setFilter(ArrayList<Sitio> listafiltrada) {
        this.sitios = new ArrayList<>();
        this.sitios.addAll(listafiltrada);
        notifyDataSetChanged();
    }
}
