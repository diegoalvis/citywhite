package brayan.rivera.whitecity.ui.iglesias;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import brayan.rivera.whitecity.R;

public class Iglesias extends Fragment {

    private IglesiasViewModel mViewModel;

    public static Iglesias newInstance() {
        return new Iglesias();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iglesias, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IglesiasViewModel.class);
        // TODO: Use the ViewModel
    }

}
