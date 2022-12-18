package com.example.ne_aplicacion_movil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;

import java.util.ArrayList;
import java.util.List;

public class DbPaises extends DbHelper{
    Context context;
    public DbPaises(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarPais(String nombre,String estadoRegistro){
        long id=0;
        try {
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("nombre",nombre);
            values.put("estado_registro",estadoRegistro);

            id=db.insert(TABLE_PAISES,null,values);
        } catch (Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<Paises> mostrarPaises(){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ArrayList<Paises> listaPaises = new ArrayList<Paises>();
        Paises pais=null;
        Cursor cursorPaises=null;

        cursorPaises = db.rawQuery("SELECT * FROM "+TABLE_PAISES,null);
        if (cursorPaises.moveToFirst()){
            do{
                pais=new Paises();
                pais.setId(cursorPaises.getInt(0));
                pais.setNombre(cursorPaises.getString(1));
                pais.setEstadoRegistro(cursorPaises.getString(2));
                listaPaises.add(pais);
            } while (cursorPaises.moveToNext());
        }
        cursorPaises.close();
        return  listaPaises;
    }

    public List<EstadoRegistro> findAllEstadoRegistro(){
        List<EstadoRegistro> estadoRegistros=null;
        try{
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_ESTADO_REGISTRO,null);
            if(cursor.moveToFirst()){
                estadoRegistros = new ArrayList<EstadoRegistro>();
                do {
                    EstadoRegistro estadoRegistro= new EstadoRegistro();
                    estadoRegistro.setCodigo(cursor.getString(0));
                    estadoRegistros.add(estadoRegistro);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("INFO","ERROR AL AGREGAR"+e.toString());
            estadoRegistros=null;
        }
        return estadoRegistros;
    }

    public boolean actualizarEstadoRegistro(int id,String estadoRegistro){
        boolean correcto = false;
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+TABLE_PAISES+" SET estado_registro = '"+estadoRegistro+"' WHERE idpais='"+id+"'");
            correcto=true;
        } catch (Exception e){
            e.toString();
            correcto=false;
            Log.d("INFO",e.toString());
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean habilitarRegistro(int id){
        return actualizarEstadoRegistro(id,"A");
    }
    public boolean inhabilitarRegistro(int id){
        return actualizarEstadoRegistro(id,"I");
    }
    public boolean eliminarRegistro(int id){
        return actualizarEstadoRegistro(id,"*");
    }

    public boolean actualizarRegistro(int id,String nombre,String estadoRegistro){
        boolean correcto=false;
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+TABLE_PAISES+" SET nombre='"+nombre+"' , estado_registro='"+estadoRegistro+"' " +
                    "WHERE idpais='"+id+"'");
            correcto=true;
        } catch(Exception e){
            e.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return  correcto;
    }

    public Paises verPaisSeleccionado(int id){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        Paises pais=null;
        Cursor cursorPaises=null;

        cursorPaises = db.rawQuery("SELECT * FROM "+TABLE_PAISES+" WHERE idpais='"+id+"'",null);
        if (cursorPaises.moveToFirst()){
            pais=new Paises();
            pais.setId(cursorPaises.getInt(0));
            pais.setNombre(cursorPaises.getString(1));
            pais.setEstadoRegistro(cursorPaises.getString(2));
        }
        cursorPaises.close();
        return  pais;
    }
}
