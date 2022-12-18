package com.example.ne_aplicacion_movil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.ArrayList;
import java.util.List;

public class DbTipoProveedores extends DbHelper{
    Context context;
    public DbTipoProveedores(@Nullable Context context) {
        super(context);
        this.context=context;
    }
    public long insertarTipoProveedor(String nombre,String estadoRegistro){
        long id=0;
        try {
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("nombre",nombre);
            values.put("estado_registro",estadoRegistro);

            id=db.insert(TABLE_TIPO_PROVEEDORES,null,values);
        } catch (Exception e){
            e.toString();
        }

        return id;
    }

    public ArrayList<TipoProveedores> mostrarTiposProveedores(){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ArrayList<TipoProveedores> listaTiposProveedores = new ArrayList<TipoProveedores>();
        TipoProveedores tipoProveedores=null;
        Cursor cursorTipoProveedores=null;

        cursorTipoProveedores = db.rawQuery("SELECT * FROM "+TABLE_TIPO_PROVEEDORES,null);
        if (cursorTipoProveedores.moveToFirst()){
            do{
                tipoProveedores=new TipoProveedores();
                tipoProveedores.setId(cursorTipoProveedores.getInt(0));
                tipoProveedores.setNombre(cursorTipoProveedores.getString(1));
                tipoProveedores.setEstadoRegistro(cursorTipoProveedores.getString(2));
                listaTiposProveedores.add(tipoProveedores);
            } while (cursorTipoProveedores.moveToNext());
        }
        cursorTipoProveedores.close();
        return  listaTiposProveedores;
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
            db.execSQL("UPDATE "+TABLE_TIPO_PROVEEDORES+" SET estado_registro = '"+estadoRegistro+"' WHERE idtipo_proveedor='"+id+"'");
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
}
