package brayan.rivera.whitecity.modelo.comida_tradicional;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.modelo.MainActivity;


public class ComidaTradicional extends Fragment {

    RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;


    private ComidaTradicionalViewModel mViewModel;

    public static ComidaTradicional newInstance() {
        return new ComidaTradicional();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //MainActivity.listafotos.clear();
        MainActivity.nodo="Comida Tradicional";

        //MainActivity.listafotos.add("comida_mora_castilla.jpeg");


        View view= inflater.inflate(R.layout.fragment_comida_tradicional, container, false);

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Comida_Tradicional);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(this.getActivity(),1));

        //creamos un objeto nuevo de tipo Firebase helper y le mandamos el contesto
        helper=new FireBaseHelper(getActivity());
        //le mandamos nuestro recyclerview a nuesta clase firebase que maneja la consulta de datos
        //helper.listarsitios(rv_lista_Sitios);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ComidaTradicionalViewModel.class);
        // TODO: Use the ViewModel
    }

}
