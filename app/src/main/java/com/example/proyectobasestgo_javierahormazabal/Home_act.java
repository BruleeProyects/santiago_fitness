package com.example.proyectobasestgo_javierahormazabal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import Objetos.Insumos;

public class Home_act extends AppCompatActivity {

    private VideoView video;

    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        video = findViewById(R.id.vw);

        //obtener ruta del video

        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video; //revisar donde va el video
        Uri uri = Uri.parse(ruta); //parseo la ruta
        video.setVideoURI(uri); //Le paso mi ruta al videoView

        video.start(); //iniciar el video


        //Controles para el video
       /* MediaController media = new MediaController(this);
        video.setMediaController(media);*/
    }

    //tarea muy pesada o proceso muy largo
   /* public void Task (View view)
    {
        try {

            for (int i = 0; i <= 10; i++)
            {
                Thread.sleep(2000);
                Toast.makeText(this, "Este es un gran proceso", Toast.LENGTH_SHORT).show();
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
    }
    }*/

    public void Insumos(View View)
    {
        //Preparo los extras
        Intent i = new Intent(this, Insumos_act.class);
        Bundle bun = new Bundle(); // necesario para envíar arreglos
        bun.putStringArray("insumos", in.getInsumos()); // relleno el bundle
        i.putExtras(bun);//Le paso mi bundle al intent

        startActivity(i);
    }

    public void Clases(View view)
    {
        Intent i = new Intent(this, Clases_act.class);
        startActivity(i);
    }
}