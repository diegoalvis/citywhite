package brayan.rivera.whitecity.modelo.sitios_interes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.modelo.Detalle_Sitio;
import brayan.rivera.whitecity.modelo.MainActivity;

public class SitiosInteres extends Fragment {

    private SitiosInteresViewModel sitiosInteresViewModel;
    RecyclerView rv_lista_Sitios;
    FireBaseHelper helper;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        sitiosInteresViewModel = ViewModelProviders.of(this).get(SitiosInteresViewModel.class);
        View view = inflater.inflate(R.layout.fragment_sitios_interes, container, false);

        //MainActivity.listafotos.clear();
        MainActivity.nodo="Sitios de Interes";
        //MainActivity.listafotos.add("comida_mora_castilla.jpeg");

        rv_lista_Sitios=view.findViewById(R.id.rv_lista_Sitios_Interes);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(getContext(),1));


        helper = new FireBaseHelper(getActivity());


        helper.listarsitios(rv_lista_Sitios);

        FireBaseHelper.adaptador_sitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireBaseHelper.posicion2=rv_lista_Sitios.getChildLayoutPosition(v);
                Toast.makeText(getContext(),"Ingresaste a sitio turistico : "+FireBaseHelper.sitios.get(rv_lista_Sitios.getChildAdapterPosition(v)).getNombre()+
                                "en la posicion "+(FireBaseHelper.posicion2+1)
                        ,Toast.LENGTH_SHORT).show();

                // Crea el nuevo fragmento y la transacción.
                Fragment nuevoFragmento = new Detalle_Sitio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();

            }
        });
        return view;

    }
}