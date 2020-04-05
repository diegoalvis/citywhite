package brayan.rivera.whitecity.ui.hoteles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.ui.Detalle_Sitio;
import brayan.rivera.whitecity.ui.home.MainActivity;
import brayan.rivera.whitecity.ui.iglesias.Iglesias;

public class Hoteles extends Fragment {

    private HotelesViewModel hotelesViewModel;
    public static RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //MainActivity.listafotos.clear();
        MainActivity.nodo="Hoteles";
        Iglesias.validacion=5;
        //MainActivity.listafotos.add("comida_mora_castilla.jpeg");

        hotelesViewModel = ViewModelProviders.of(this).get(HotelesViewModel.class);
        View view= inflater.inflate(R.layout.fragment_hoteles, container, false);

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Hoteles);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(this.getActivity(),1));


        helper = new FireBaseHelper(getActivity());

//
//        helper.listarsitios(rv_lista_Sitios);
//
//
//
//
//
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
}