package com.example.recetas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarReceta extends AppCompatActivity implements View.OnClickListener{
    private EditText editPlato, editIngredientes, editElaboracion;
    private Button botonGuardar;
    private AyudanteDB ayudanteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ayudanteDB = new AyudanteDB(getApplicationContext());
        editPlato = findViewById(R.id.edit_receta);
        editIngredientes = findViewById(R.id.edit_ingredientes);
        editElaboracion = findViewById(R.id.edit_elaboracion);
        botonGuardar = findViewById(R.id.btn_guardar);
        botonGuardar.setOnClickListener(this);

    }
    public void insertarReceta() {
        String parametros[] = {
                editPlato.getEditableText().toString(),
                editIngredientes.getEditableText().toString(),
                editElaboracion.getEditableText().toString(),
        };

        ayudanteDB.insertarReceta(parametros);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regresar:
                Intent miintent = new Intent(EditarReceta.this,MainActivity.class);
                startActivity(miintent);
                break;
            case R.id.btn_guardar:
                insertarReceta();
                Toast.makeText(getApplicationContext(),"Receta guardada correctamente",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}