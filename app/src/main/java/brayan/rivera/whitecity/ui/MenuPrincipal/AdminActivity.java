package brayan.rivera.whitecity.ui.MenuPrincipal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import brayan.rivera.whitecity.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void goCreateUser(View view) {
        Intent intent = new Intent(this, CrearUsuarios.class);
        startActivity(intent);
    }

    public void goCreateSitios(View view) {
        Intent intent = new Intent(this, CrearSitios.class);
        startActivity(intent);
    }
}
