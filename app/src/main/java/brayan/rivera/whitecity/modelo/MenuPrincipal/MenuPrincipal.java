package brayan.rivera.whitecity.modelo.MenuPrincipal;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brayan.rivera.whitecity.R;

public class MenuPrincipal extends Fragment {

    private MenuPrincipalViewModel mViewModel;

    public static MenuPrincipal newInstance() {
        return new MenuPrincipal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_principal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MenuPrincipalViewModel.class);
        // TODO: Use the ViewModel
    }

}
