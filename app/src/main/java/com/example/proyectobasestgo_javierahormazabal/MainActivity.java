package com.example.proyectobasestgo_javierahormazabal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import Objetos.Administrador;
import Objetos.Insumos;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private TextView msj;
    private Button btn;
    private ProgressBar barra;
    private Administrador adm = new Administrador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.etuser);
        pass = findViewById(R.id.etpass);
        msj = findViewById(R.id.msj);
        btn = findViewById(R.id.btn);
        barra = findViewById(R.id.pb);

        msj.setVisibility(View.INVISIBLE);
        barra.setVisibility(View.INVISIBLE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aqui voy a correr mi tarea
                new Task().execute();
            }
        });

    }

    //Tarea Asíncrona.
    class Task extends AsyncTask<String, Void, String>
    {
        //Es donde puedo dar la configuracion inicial a mi tarea
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            barra.setVisibility(View.VISIBLE); //Visivilizo la barra
        }
        //Es el encargado de procesar en segundo plano la tarea pesada
        @Override
        protected String doInBackground(String... strings) {

            try {

                for (int i = 0; i <= 10; i++)
                {
                    Thread.sleep(500); // duerme un proceso por 2000 milisegundos
                }
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }
        //Es donde finaliza la tarea asincrona
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            barra.setVisibility(View.INVISIBLE);

            //Validación de la sesión
            String usuario = user.getText().toString().trim();
            String contrasena = pass.getText().toString().trim();

            String userObj = adm.getUser().trim();
            String passObj = adm.getPass().trim();

            switch (usuario)
            {
                case "patricia":
                    if(usuario.equals(userObj) && contrasena.equals(passObj))
                    {
                        msj.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(getBaseContext(),Home_act.class);
                        startActivity(i);


                    }
                    break;
                case "":
                    if(usuario.equals("") && contrasena.equals(""))
                    {
                        msj.setVisibility(View.VISIBLE);
                        msj.setText("Campos vacíos");
                    }
                    break;
                default:
                    if(!usuario.equals(userObj) && !contrasena.equals(passObj))
                    {
                        msj.setVisibility(View.VISIBLE);
                        msj.setText("Campos incorrectos");
                    }
                    break;
            }
        }
    }


    public void Facebook(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW); //accion para abrir un sitio web
        i.setData(Uri.parse("https://www.facebook.com/"));
        startActivity(i);
    }

    public void Twitter(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW); //accion para abrir un sitio web
        i.setData(Uri.parse("https://www.twitter.com/"));
        startActivity(i);
    }

    public void Youtube(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW); //accion para abrir un sitio web
        i.setData(Uri.parse("https://www.youtube.com/"));
        startActivity(i);
    }

    public void Info (View view)
    {
        Intent i = new Intent(this, Info_act.class);
        startActivity(i);

    }
}