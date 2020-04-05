package brayan.rivera.whitecity.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import brayan.rivera.whitecity.R;
import brayan.rivera.whitecity.ui.home.MainActivity;

public class Splash extends AppCompatActivity {
    final private int REQUEST_CODE_PERMISSION = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        solicitarPermisos();
    }

    private void solicitarPermisos() {
        int permisoInternet = ActivityCompat.checkSelfPermission(Splash.this, Manifest.permission.INTERNET);
        int permisoLocalisacion = ActivityCompat.checkSelfPermission(Splash.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permisoCoarseLocalisacion = ActivityCompat.checkSelfPermission(Splash.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permisoInternet != PackageManager.PERMISSION_GRANTED || permisoLocalisacion != PackageManager.PERMISSION_GRANTED || permisoCoarseLocalisacion != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSION);
        } else {
            navegarAlMain();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        navegarAlMain();
    }

    private void navegarAlMain() {
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);*/
    }
}
