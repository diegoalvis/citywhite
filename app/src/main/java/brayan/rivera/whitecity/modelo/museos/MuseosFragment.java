package brayan.rivera.whitecity.modelo.museos;

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
import brayan.rivera.whitecity.modelo.MainActivity;

public class MuseosFragment extends Fragment {

    private MuseosViewModel museosViewModel;
    RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //MainActivity.listafotos.clear();
        MainActivity.nodo="Museos";
        //MainActivity.listafotos.add("museo_valencia.jpeg");

        museosViewModel = ViewModelProviders.of(this).get(MuseosViewModel.class);

        View view = inflater.inflate(R.layout.fragment_museos, container, false);

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Museos);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(this.getActivity(),1));

        //creamos un objeto nuevo de tipo Firebase helper y le mandamos el contesto
        helper=new FireBaseHelper(getContext());
        //le mandamos nuestro recyclerview a nuesta clase firebase que maneja la consulta de datos
        helper.listarsitios(rv_lista_Sitios);
        return view;


    }
}