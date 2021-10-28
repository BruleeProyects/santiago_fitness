package com.example.proyectobasestgo_javierahormazabal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import Objetos.Insumos;

public class Insumos_act extends AppCompatActivity {

    private Spinner insumos;
    private TextView result;
    private RatingBar calificar;
    private Insumos in  = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);

        insumos = findViewById(R.id.spnInsumos);
        result = findViewById(R.id.tv_resultado);
        calificar = findViewById(R.id.rt);

        //Recibo mis extras desde el main
        Bundle bun = getIntent().getExtras();//Recibo el intent con los valores del bundle.
        String[] listado = bun.getStringArray("insumos"); //Recibo el listado por si referencia.

        ArrayAdapter adaptInsumos =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, listado);
        insumos.setAdapter(adaptInsumos);

    }

    //metodo para calcular insumos
    public void Calular(View view)
    {
        String opcion = insumos.getSelectedItem().toString(); //obtengo la seleccion en una variable
        int resultado = 0;

        for(int i = 0; i < opcion.length(); i++)
        {
            if(opcion.equals(in.getInsumos()[i])) //Pregunto por la seleccion del spinner
            {
                //resultado = in.getPrecios()[i]; //Obtengo los precios
                resultado = in.AnadirAdicional(in.getPrecios()[i],350); //obtengo mi regla del adicional, solo añado el adicional
                calificar.setRating(i);//pinto la estrella segun la seleccion segun la seleccion
                break;
            }
        }

        result.setText("La opcion es: " + opcion + "\nSu precio es: " + resultado);


    }
}