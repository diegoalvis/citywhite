package brayan.rivera.whitecity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brayan.rivera.whitecity.controlador.FireBaseHelper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mostrar_Sitio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mostrar_Sitio extends Fragment {

    RecyclerView rc_mostrar_Sitio_ADMIN;
    FireBaseHelper helper;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Mostrar_Sitio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Mostrar_Sitio.
     */
    // TODO: Rename and change types and number of parameters
    public static Mostrar_Sitio newInstance(String param1, String param2) {
        Mostrar_Sitio fragment = new Mostrar_Sitio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        helper = new FireBaseHelper(getActivity());
        // Inflate the layout for this fragmentç
        View view = inflater.inflate(R.layout.fragment_mostrar__sitio, container, false);
        rc_mostrar_Sitio_ADMIN = view.findViewById(R.id.rc_mostrar_Sitio_ADMIN);
        rc_mostrar_Sitio_ADMIN.setLayoutManager(new GridLayoutManager(getContext(), 1));

        helper.consultarTodosLosSitios(rc_mostrar_Sitio_ADMIN);


        return view;

    }
}
