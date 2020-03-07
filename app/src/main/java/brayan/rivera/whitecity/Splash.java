package brayan.rivera.whitecity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imagen=findViewById(R.id.imgsplash); //referenciamos

        // creacion del Glide

        Glide.with(this).load("https://blogs.elespectador.com/wp-content/uploads/2017/04/torre-del-reloj-300x199.jpg").into(imagen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pasar=new Intent(Splash.this,MainActivity.class);
                startActivity(pasar);
                finish();
            }
        },2000);

    }
}


