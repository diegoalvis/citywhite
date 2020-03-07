package brayan.rivera.whitecity.ui.comida_tradicional;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brayan.rivera.whitecity.R;

public class ComidaTradicional extends Fragment {

    private ComidaTradicionalViewModel mViewModel;

    public static ComidaTradicional newInstance() {
        return new ComidaTradicional();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comida_tradicional, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ComidaTradicionalViewModel.class);
        // TODO: Use the ViewModel
    }

}
