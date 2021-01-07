package com.example.recetas2;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class AdapterList extends BaseAdapter {
    private MainActivity actividad;
    private ArrayList<Receta> listaRecetas;
    private Button btnActualizar, btnBorrar;
    private TextView id;

    public AdapterList(MainActivity actividad, ArrayList<Receta> items) {
        this.actividad = actividad;
        this.listaRecetas = items;
    }

    @Override
    public int getCount() {
        return listaRecetas.size();
    }

    @Override
    public Receta getItem(int id) {
        return listaRecetas.get(id);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inflador = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflador.inflate(R.layout.item, null);
        }
        final Receta recetaActual = getItem(position);
        final EditText editPlato = v.findViewById(R.id.edit_receta);
        final EditText editIngredientes = v.findViewById(R.id.edit_ingredientes);
        final EditText editElaboracion = v.findViewById(R.id.edit_elaboracion);

        btnActualizar = v.findViewById(R.id.btn_actualizar);
        btnBorrar = v.findViewById(R.id.btn_borrar);
        id = v.findViewById(R.id.txt_id);
        id.setText(recetaActual.getId() + " - ");

        editPlato.setText(recetaActual.getPlato());
        editIngredientes.setText(recetaActual.getIngredientes());
        editElaboracion.setText(recetaActual.getElaboracion());


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] parametros = {
                        editPlato.getEditableText().toString(),
                        editIngredientes.getEditableText().toString(),
                        editElaboracion.getEditableText().toString(),
                        Integer.toString(recetaActual.getId()),
                };
                actividad.actualizarReceta(parametros);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actividad.eliminarReceta(recetaActual);
            }
        });
        return v;
    }
}