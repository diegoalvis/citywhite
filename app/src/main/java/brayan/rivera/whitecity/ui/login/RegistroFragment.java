package brayan.rivera.whitecity.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import brayan.rivera.whitecity.R;

public class RegistroFragment extends Fragment {

    private View.OnClickListener irALoginistener;

    public RegistroFragment(View.OnClickListener irALoginistener) {
        this.irALoginistener = irALoginistener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registro, container, false);

        TextView login = v.findViewById(R.id.btn_tengo_cuenta);
        login.setOnClickListener(irALoginistener);

        return v;
    }
}
