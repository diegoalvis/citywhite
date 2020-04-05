package brayan.rivera.whitecity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;

import java.io.IOException;

import brayan.rivera.whitecity.ui.SectionsPagerAdapter;

public class AdministradorActivity extends AppCompatActivity {

    public  static  Uri url_Imagen_ADMIN = null;
    public static  Bitmap bitmap=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }

    //metodo que nos trae por defeto el valor o contenido obtenido dentro del storage del celular
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            url_Imagen_ADMIN=data.getData();

            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),url_Imagen_ADMIN);
                Registrar_Sitio.img_imagen_Sitio_ADMIN.setImageBitmap(bitmap);
                Registrar_Sitio.ibtn_escoger_Imagen_ADMIN.setImageBitmap(bitmap);

            }
            catch(IOException e){
                e.printStackTrace();
            }

    }
}