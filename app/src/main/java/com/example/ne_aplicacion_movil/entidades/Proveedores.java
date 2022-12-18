package com.example.ne_aplicacion_movil.entidades;

import com.example.ne_aplicacion_movil.db.DbPaises;
import com.example.ne_aplicacion_movil.db.DbProveedores;
import com.example.ne_aplicacion_movil.db.DbTipoProveedores;

public class Proveedores {
    private int id;
    private String nombre;
    private int RUC;
    private String tipoProveedor;
    private String pais;
    private String estadoRegistro;
    public boolean isChecked=false;

    private String tipoProveedorNombre;
    private String paisNombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRUC() {
        return RUC;
    }

    public void setRUC(int RUC) {
        this.RUC = RUC;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getPais() {
        return pais;
    }


    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getTipoProveedorNombre() {
        return tipoProveedorNombre;
    }

    public void setTipoProveedorNombre(String tipoProveedorNombre) {
        this.tipoProveedorNombre = tipoProveedorNombre;
    }

    public String getPaisNombre() {
        return paisNombre;
    }

    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
    }
}
