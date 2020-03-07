package brayan.rivera.whitecity.ui.museos;

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

public class MuseosFragment extends Fragment {

    private MuseosViewModel museosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        museosViewModel =
                ViewModelProviders.of(this).get(MuseosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_museos, container, false);
        final TextView textView = root.findViewById(R.id.text_museos);
        museosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}