package brayan.rivera.whitecity.modelo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import brayan.rivera.whitecity.R;

public class MainActivity extends AppCompatActivity {
    public static String nodo="";
    public static ArrayList<String>listafotos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_iglesias, R.id.navigation_comida_tradicional, R.id.navigation_museos,R.id.navigation_hoteles,R.id.navigation_sitios_interes)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        listafotos=new ArrayList<>();
        MainActivity.listafotos.add("iglesia_ermita.jpg");
        MainActivity.listafotos.add("iglesia_santo_domingo.jpeg");
        MainActivity.listafotos.add("iglesia_santo_domingo.jpeg");
    }


}
