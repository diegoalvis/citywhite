package brayan.rivera.whitecity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        TimerTask actividad = new TimerTask() {
            @Override
            public void run() {
                Intent intento = new Intent(Splash.this,MainActivity.class);
                startActivity(intento);
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(actividad,2000);
    }
}
