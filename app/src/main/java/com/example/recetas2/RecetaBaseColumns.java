package com.example.recetas2;

import android.provider.BaseColumns;

public class RecetaBaseColumns implements BaseColumns {
    //esta clase se llamaba TareaAuxiliar, habria que refactorizarlo luego.
    public static final String NOMBRE_TABLA ="Recetas";
    public static final String PLATO = "Plato";
    public static final String INGREDIENTES = "Ingredientes";
    public static final String ELABORACION = "Elaboracion";
}