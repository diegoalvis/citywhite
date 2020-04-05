package brayan.rivera.whitecity.controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.data.modelos.Sitio;


public class AdaptadorSitios extends RecyclerView.Adapter<AdaptadorSitios.SitioViewHolder> implements View.OnClickListener {


    public  static  ArrayList<Sitio>ListaSitios;
    //escuchador onclicklistener
    private View.OnClickListener onClickListener;
    private Fragment fragment;


    public AdaptadorSitios(ArrayList<Sitio> listallena) {
        ListaSitios=listallena;

    }



    //metodo para inflar la platilla personalizada y retornarla
    @NonNull
    @Override
    public SitioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sitio,parent,false);
        v.setOnClickListener(this);
        SitioViewHolder holder= new SitioViewHolder(v);
        return holder;

    }

    //metodo para asignar los valores a cada elemento de la plantilla personalizada
    @Override
    public void onBindViewHolder(@NonNull SitioViewHolder holder, final int position) {

        Sitio sitio=ListaSitios.get(position);
        holder.txtdescripcion.setText(ListaSitios.get(position).getDescripcion());
        holder.txtnombre.setText(sitio.getNombre());
        holder.txtdireccion.setText(sitio.getDireccion());
        holder.txttelefono.setText(sitio.getTelefono());

    }

    public  void setOnClickListener(View.OnClickListener listener  ){
         this.onClickListener=listener;
    }


    @Override
    public void onClick(View v) {

        if (onClickListener!=null)
        {
            onClickListener.onClick(v);
        }

    }


    //metodo para instaciar cada elemento de la plantilla perzonalizada
    public static  class SitioViewHolder extends RecyclerView.ViewHolder {

        TextView txtnombre,txtdescripcion,txtdireccion,txttelefono;


        public SitioViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtnombre= itemView.findViewById(R.id.txtnombre_item);
            this.txtdescripcion= itemView.findViewById(R.id.txtdescripcion_item);
            this.txtdireccion=itemView.findViewById(R.id.txtdireccion_item);
            this.txttelefono=itemView.findViewById(R.id.txttelefono_item);

        }
    }

    @Override
    public int getItemCount() {

        return ListaSitios.size() ;

    }


    public  void setFilter(ArrayList<Sitio>listafiltrada)
    {
        this.ListaSitios=new ArrayList<>();
        this.ListaSitios.addAll(listafiltrada);
        notifyDataSetChanged();
    }




}
