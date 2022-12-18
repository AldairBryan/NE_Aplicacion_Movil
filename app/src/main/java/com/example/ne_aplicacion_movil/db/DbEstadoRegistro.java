package com.example.ne_aplicacion_movil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;

import java.util.ArrayList;

public class DbEstadoRegistro extends  DbHelper{
    Context context;
    public DbEstadoRegistro(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarEstadoRegistro(String codigo){
        long id=0;
        try {
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("codigo",codigo);

            id=db.insert(TABLE_ESTADO_REGISTRO,null,values);
        } catch (Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<EstadoRegistro> mostrarEstadosRegistros(){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ArrayList<EstadoRegistro> listaEstadosRegistros = new ArrayList<EstadoRegistro>();
        EstadoRegistro estadoRegistro=null;
        Cursor cursorEstadoRegistro=null;

        cursorEstadoRegistro = db.rawQuery("SELECT * FROM "+TABLE_ESTADO_REGISTRO,null);
        if (cursorEstadoRegistro.moveToFirst()){
            do{
                estadoRegistro=new EstadoRegistro();
                estadoRegistro.setCodigo(cursorEstadoRegistro.getString(0));
                listaEstadosRegistros.add(estadoRegistro);
            } while (cursorEstadoRegistro.moveToNext());
        }
        cursorEstadoRegistro.close();
        return  listaEstadosRegistros;
    }

    public boolean actualizarRegistro(String nuevoCodigo,String viejoCodigo){
        boolean correcto=false;
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+TABLE_ESTADO_REGISTRO+" SET codigo='"+nuevoCodigo+"' " +
                    "WHERE codigo='"+viejoCodigo+"'");
            correcto=true;
        } catch(Exception e){
            e.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return  correcto;
    }

    public EstadoRegistro verEstadoRegistroSeleccionado(String codigo){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        EstadoRegistro estadoRegistro=null;
        Cursor cursorEstadoRegistro=null;

        cursorEstadoRegistro = db.rawQuery("SELECT * FROM "+TABLE_ESTADO_REGISTRO+" WHERE codigo='"+codigo+"'",null);
        if (cursorEstadoRegistro.moveToFirst()){
            estadoRegistro=new EstadoRegistro();
            estadoRegistro.setCodigo(cursorEstadoRegistro.getString(0));
        }
        cursorEstadoRegistro.close();
        return  estadoRegistro;
    }
}
