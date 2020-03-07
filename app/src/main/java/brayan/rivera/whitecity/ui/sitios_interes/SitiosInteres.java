package brayan.rivera.whitecity.ui.sitios_interes;

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
import brayan.rivera.whitecity.R;

public class SitiosInteres extends Fragment {

    private SitiosInteresViewModel sitiosInteresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sitiosInteresViewModel =
                ViewModelProviders.of(this).get(SitiosInteresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sitios_interes, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        sitiosInteresViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}