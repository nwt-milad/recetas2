package com.example.recetas2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

public class AyudanteDB extends SQLiteOpenHelper {
    public static final int DB_VERSION = 3;
    public static final String DB_NOMBRE = "recetas.db";

    public AyudanteDB(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + RecetaBaseColumns.NOMBRE_TABLA + "  ("
                + RecetaBaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RecetaBaseColumns.PLATO + " TEXT,"
                + RecetaBaseColumns.INGREDIENTES + " TEXT,"
                + RecetaBaseColumns.ELABORACION + " TEXT"
                + ");";
        db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put(RecetaBaseColumns.PLATO, "Este es un título de ejemplo");
        values.put(RecetaBaseColumns.INGREDIENTES, "Ingredientes");
        values.put(RecetaBaseColumns.ELABORACION, "La elaboracion puede ser trabajosa");

        db.insert(RecetaBaseColumns.NOMBRE_TABLA, null, values);
    }

    public ArrayList devolverRecetas() {
        ArrayList listaRecetas = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                RecetaBaseColumns.NOMBRE_TABLA, null, null, null, null, null, RecetaBaseColumns._ID + " DESC"
        );
        while (cursor.moveToNext()) {
            Receta receta = new Receta();
            receta.setId(cursor.getInt(0));
            receta.setPlato(cursor.getString(1));
            receta.setIngredientes(cursor.getString(2));
            receta.setElaboracion(cursor.getString(3));
            listaRecetas.add(receta);
        }
        db.close();
        return listaRecetas;
    }

    public void insertarReceta(String[] params) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO recetas (Plato, Ingredientes, Elaboracion) " +
                "VALUES ( '" + params[0] + "', '" + params[1] + "', '" + params[2] + "');");
        Log.d("aino", params[1]);
        db.close();
    }

    public void editarReceta(String[] params) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("UPDATE " + RecetaBaseColumns.NOMBRE_TABLA +
                " SET " + RecetaBaseColumns.PLATO + " =?,"+ RecetaBaseColumns.INGREDIENTES + " =?,"+
                RecetaBaseColumns.ELABORACION + "=? " + "WHERE " + RecetaBaseColumns._ID + " =?");


        statement.bindString(1, params[0]);
        statement.bindString(2, params[1]);
        statement.bindString(3, params[2]);
        statement.bindString(4, params[3]);
        statement.execute();
        statement.close();
        db.close();
    }

    public void borrarReceta(int taskid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                RecetaBaseColumns.NOMBRE_TABLA,
                RecetaBaseColumns._ID + " =?",
                new String[]{Integer.toString(taskid)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.w("TAG", "Actualizando DB desde la versión " + oldVersion + " a "
                    + newVersion + ", que destruirá todos los datos antiguos");
            db.execSQL("DROP TABLE IF EXISTS " + RecetaBaseColumns.NOMBRE_TABLA);
            onCreate(db);
        }
    }
}
