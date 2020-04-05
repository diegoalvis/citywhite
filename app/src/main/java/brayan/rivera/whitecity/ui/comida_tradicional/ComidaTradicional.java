package brayan.rivera.whitecity.ui.comida_tradicional;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.ui.Detalle_Sitio;
import brayan.rivera.whitecity.ui.home.MainActivity;
import brayan.rivera.whitecity.ui.iglesias.Iglesias;


public class ComidaTradicional extends Fragment {

    public static  RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;


    private ComidaTradicionalViewModel mViewModel;

    public static ComidaTradicional newInstance() {
        return new ComidaTradicional();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //MainActivity.listafotos.clear();
        MainActivity.nodo="Comida Tipica";
        Iglesias.validacion=4;

        //MainActivity.listafotos.add("comida_mora_castilla.jpeg");


        View view= inflater.inflate(R.layout.fragment_comida_tradicional, container, false);

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Comida_Tradicional);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(this.getActivity(),1));


        helper = new FireBaseHelper(getActivity());


//        helper.listarsitios(rv_lista_Sitios);
//
//        FireBaseHelper.adaptador_sitios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FireBaseHelper.posicion2=rv_lista_Sitios.getChildLayoutPosition(v);
//                Toast.makeText(getContext(),"Ingresaste a sitio turistico : "+FireBaseHelper.sitios.get(rv_lista_Sitios.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();
//
//                // Crea el nuevo fragmento y la transacción.
//                Fragment nuevoFragmento = new Detalle_Sitio();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
//                transaction.addToBackStack(null);
//
//                // Commit a la transacción
//                transaction.commit();
//
//            }
//        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ComidaTradicionalViewModel.class);
        // TODO: Use the ViewModel
    }

}
