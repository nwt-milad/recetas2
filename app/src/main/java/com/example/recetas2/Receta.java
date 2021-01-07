package com.example.recetas2;

public class Receta {
    private int id;
    private String plato;
    private String ingredientes;
    private String elaboracion;

    public int getId() {
        return id;
    }
    public String getPlato() {
        return plato;
    }
    public String getIngredientes() {
        return ingredientes;
    }
    public String getElaboracion() {
        return elaboracion;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }
}

