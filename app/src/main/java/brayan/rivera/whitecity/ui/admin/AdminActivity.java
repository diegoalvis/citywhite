package brayan.rivera.whitecity.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import brayan.rivera.whitecity.R;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_admin);

        Button listarSitios = findViewById(R.id.btn_listar_sitios_ADMIN);
        Button listarUsuarios = findViewById(R.id.btn_listar_usuarios_ADMIN);
        Button agregarSitio = findViewById(R.id.btn_agregar_sitio_ADMIN);

        listarSitios.setOnClickListener(this);
        listarUsuarios.setOnClickListener(this);
        agregarSitio.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_listar_sitios_ADMIN:
                intent = new Intent(this, ListarSitiosActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_listar_usuarios_ADMIN:
                intent = new Intent(this, ListarUsuariosActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_agregar_sitio_ADMIN:
                intent = new Intent(this, RegistrarSitioActivity.class);
                startActivity(intent);
                break;
        }
    }
}
