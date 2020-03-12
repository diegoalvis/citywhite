package brayan.rivera.whitecity.modelo.iglesias;

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

public class Iglesias extends Fragment {

    private IglesiasViewModel mViewModel;
    RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;

    public static Iglesias newInstance() {
        return new Iglesias();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainActivity.nodo="Iglesias";
        //MainActivity.listafotos.clear();
        //MainActivity.listafotos.add("iglesia_ermita.jpg");
        //MainActivity.listafotos.add("iglesia_santo_domingo.jpeg");


        View view = inflater.inflate(R.layout.fragment_iglesias, container, false);

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Iglesias);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(getContext(),1));

        //creamos un objeto nuevo de tipo Firebase helper y le mandamos el contesto
        helper=new FireBaseHelper(getActivity());
        //le mandamos nuestro recyclerview a nuesta clase firebase que maneja la consulta de datos
        helper.listarsitios(rv_lista_Sitios);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IglesiasViewModel.class);
        // TODO: Use the ViewModel
    }

}
