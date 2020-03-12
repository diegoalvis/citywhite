package brayan.rivera.whitecity.modelo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import brayan.rivera.whitecity.R;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    final private  int REQUEST_CODE_ASK_PERMISSION = 111;
    int permisoInternet, permisoLocalisacion, permisoCoarseLocalisacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        solicitarPermisos();

        TimerTask actividad = new TimerTask() {
            @Override
            public void run() {
                Intent intento = new Intent(Splash.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(actividad,2000);
    }
    private void solicitarPermisos() {

        permisoInternet = ActivityCompat.checkSelfPermission(Splash.this, Manifest.permission.INTERNET);
        permisoLocalisacion = ActivityCompat.checkSelfPermission(Splash.this, Manifest.permission.ACCESS_FINE_LOCATION);
        permisoCoarseLocalisacion = ActivityCompat.checkSelfPermission(Splash.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if ( permisoInternet != PackageManager.PERMISSION_GRANTED || permisoLocalisacion != PackageManager.PERMISSION_GRANTED || permisoCoarseLocalisacion!= PackageManager.PERMISSION_GRANTED){
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ASK_PERMISSION);
            }
        }
    }
}
