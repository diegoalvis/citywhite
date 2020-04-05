package brayan.rivera.whitecity.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import brayan.rivera.whitecity.Mostrar_Sitio;
import brayan.rivera.whitecity.Mostrar_Usuario;
import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.Registrar_Sitio;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {

        Fragment fragment = null;

        switch (index) {
            case 1:fragment= new Registrar_Sitio();
            break;
            case 2:fragment= new Mostrar_Sitio();
                break;
            case 3:fragment= new Mostrar_Usuario();
                break;
        }


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_administrador, container, false);


        return root;
    }
}