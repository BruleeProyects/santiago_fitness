
package com.example.proyectobasestgo_javierahormazabal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectobasestgo_javierahormazabal.database.AdminSQLiteOpenHelper;

public class Clases_act extends AppCompatActivity {

    private EditText code, clas, inte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        code = findViewById(R.id.et_code);
        clas = findViewById(R.id.et_clase);
        inte = findViewById(R.id.et_intensidad);
    }

    //Metodo para guardar clases
    public void guardarClases(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null,1); //Obtengo mi database.
        SQLiteDatabase db = admin.getWritableDatabase(); //Me permite sobreescribir la database

        //Obtengo los valores que escribe el cliente
        String codigo = code.getText().toString();
        String clase = clas.getText().toString();
        String intensidad = inte.getText().toString();

        if(!codigo.isEmpty() && !clase.isEmpty() && !intensidad.isEmpty())
        {
            //guardo datos
            ContentValues cont = new ContentValues(); //Me permite contener valores
            cont.put("codigo", codigo);
            cont.put("clase", clase);
            cont.put("intensidad", intensidad);

            db.insert("clases", null, cont);
            db.close();
            Clean();
            Toast.makeText(getBaseContext(), "Has guardado una clase", Toast.LENGTH_SHORT).show();

        }else
        {
            Toast.makeText(getBaseContext(), "Los campos no pueden ir vacíos", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo para consultar clases
    public void mostrarClases(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null,1); //Obtengo mi database.
        SQLiteDatabase db = admin.getWritableDatabase(); //Me permite sobreescribir la database

        String codigo = code.getText().toString();

        if(!codigo.isEmpty())
        {
            Cursor file = db.rawQuery("SELECT clase, intensidad FROM clases WHERE codigo="+codigo, null);

            if(file.moveToFirst()) //Comprobar si mi sinsulta tiene valores
            {
                clas.setText(file.getString(0)); //Muestro por posición
                inte.setText(file.getString(1));

            }else{
                Toast.makeText(getBaseContext(), "No hay clases asociadas", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getBaseContext(), "El código esta vacío", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para eliminar clases
    public void eliminarClases(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null,1); //Obtengo mi database.
        SQLiteDatabase db = admin.getWritableDatabase(); //Me permite sobreescribir la database
        
        String codigo = code.getText().toString();
        
        if(!codigo.isEmpty())
        {
            //Eliminar
            db.delete("clases", "codigo="+codigo, null);
            db.close();
            Clean();
            Toast.makeText(getBaseContext(), "Has eliminado la clase: "+codigo, Toast.LENGTH_SHORT).show();
            
        }else{
            Toast.makeText(getBaseContext(), "Ingrese código por favor", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para actualizar clases
    public void actualizarClases(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null,1); //Obtengo mi database.
        SQLiteDatabase db = admin.getWritableDatabase(); //Me permite sobreescribir la database

        //Obtengo los valores que escribe el cliente
        String codigo = code.getText().toString();
        String clase = clas.getText().toString();
        String intensidad = inte.getText().toString();

        if(!codigo.isEmpty() && !clase.isEmpty() && !intensidad.isEmpty())
        {
            ContentValues cont =new ContentValues();
            cont.put("clase",clase);
            cont.put("intensidad",intensidad);

            db.update("clases", cont, "codigo="+codigo, null);
            db.close();
            Clean();
            Toast.makeText(getBaseContext(), "Has actualizado correctamente", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(getBaseContext(), "Hay campos vacíos", Toast.LENGTH_SHORT).show();

        }



    }

    //Metodo para limpiar campos
    public void Clean()
    {
        code.setText("");
        clas.setText("");
        inte.setText("");
    }
}