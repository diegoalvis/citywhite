package brayan.rivera.whitecity.ui.iglesias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.controlador.AdaptadorSitios;
import brayan.rivera.whitecity.controlador.FireBaseHelper;
import brayan.rivera.whitecity.ui.home.MainActivity;


public class Iglesias extends Fragment {

    private IglesiasViewModel mViewModel;
    public static RecyclerView rv_lista_Sitios;
    public static int validacion = 1;
    FireBaseHelper helper;
    AdaptadorSitios adaptador;

    public static Iglesias newInstance() {
        return new Iglesias();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainActivity.nodo = "Iglesias";

        View view = inflater.inflate(R.layout.fragment_iglesias, container, false);

        rv_lista_Sitios = view.findViewById(R.id.rv_lista_Iglesias);
        rv_lista_Sitios.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //creamos un objeto nuevo de tipo Firebase helper y le mandamos el contesto
        helper = new FireBaseHelper(getActivity());


//            helper.listarsitios(rv_lista_Sitios);
//
//            FireBaseHelper.adaptador_sitios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 FireBaseHelper.posicion2=rv_lista_Sitios.getChildLayoutPosition(v);
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
//
//


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IglesiasViewModel.class);
        // TODO: Use the ViewModel
    }

}