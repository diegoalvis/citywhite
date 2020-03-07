package brayan.rivera.whitecity.ui.hoteles;

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

public class Hoteles extends Fragment {

    private HotelesViewModel hotelesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotelesViewModel =
                ViewModelProviders.of(this).get(HotelesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hoteles, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        hotelesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}