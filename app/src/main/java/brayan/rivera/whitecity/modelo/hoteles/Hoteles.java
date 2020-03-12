package brayan.rivera.whitecity.modelo.hoteles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.modelo.Adaptador_Sitios;
import brayan.rivera.whitecity.modelo.MainActivity;

public class Hoteles extends Fragment {

    private HotelesViewModel hotelesViewModel;
    RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //MainActivity.listafotos.clear();
        MainActivity.nodo="Hoteles";
        //MainActivity.listafotos.add("comida_mora_castilla.jpeg");

        hotelesViewModel = ViewModelProviders.of(this).get(HotelesViewModel.class);
        View view= inflater.inflate(R.layout.fragment_hoteles, container, false);

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Hoteles);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(this.getActivity(),1));

        //creamos un objeto nuevo de tipo Firebase helper y le mandamos el contesto
        helper=new FireBaseHelper(getContext());
        //le mandamos nuestro recyclerview a nuesta clase firebase que maneja la consulta de datos
        helper.listarsitios(rv_lista_Sitios);

        return view;
    }
}