package com.example.ne_aplicacion_movil.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ne_aplicacion_movil.entidades.EstadoRegistro;
import com.example.ne_aplicacion_movil.entidades.Paises;
import com.example.ne_aplicacion_movil.entidades.Proveedores;
import com.example.ne_aplicacion_movil.entidades.TipoProveedores;

import java.util.ArrayList;
import java.util.List;

public class DbProveedores extends DbHelper {
    Context context;
    public DbProveedores(@Nullable Context context) {
        super(context);
        this.context=context;
    }


    public long insertarProveedor(String nombre,int RUC,int tipoProveedor,int pais,String estadoRegistro){
        long id=0;
        try {
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("nombre",nombre);
            values.put("RUC",RUC);
            values.put("tipo_proveedor",tipoProveedor);
            values.put("pais",pais);
            values.put("estado_registro",estadoRegistro);
            id=db.insert(TABLE_PROVEEDORES,null,values);
        } catch (Exception e){
            e.toString();
        }

        return id;
    }
    public ArrayList<Proveedores> mostrarProveedores(){
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ArrayList<Proveedores> listaProveedores = new ArrayList<Proveedores>();
        Proveedores proveedores=null;
        Cursor cursorProveedores=null;

        cursorProveedores = db.rawQuery("SELECT * FROM "+TABLE_PROVEEDORES,null);
        if (cursorProveedores.moveToFirst()){
            do{
                proveedores=new Proveedores();
                proveedores.setId(cursorProveedores.getInt(0));
                proveedores.setNombre(cursorProveedores.getString(1));
                proveedores.setRUC(cursorProveedores.getInt(2));
                proveedores.setTipoProveedor(cursorProveedores.getString(3));
                proveedores.setPais(cursorProveedores.getString(4));
                proveedores.setEstadoRegistro(cursorProveedores.getString(5));
                listaProveedores.add(proveedores);
            } while (cursorProveedores.moveToNext());
        }
        cursorProveedores.close();
        return  listaProveedores;
    }

    public List<TipoProveedores> findAllTipoProveedores(){
        List<TipoProveedores> tipoProveedoresList=null;
        try{
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_TIPO_PROVEEDORES,null);
            if(cursor.moveToFirst()){
                tipoProveedoresList = new ArrayList<TipoProveedores>();
                do {
                    TipoProveedores tipoProveedores= new TipoProveedores();
                    tipoProveedores.setId(cursor.getInt(0));
                    tipoProveedores.setNombre(cursor.getString(1));
                    tipoProveedores.setEstadoRegistro(cursor.getString(2));
                    tipoProveedoresList.add(tipoProveedores);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("INFO","ERROR AL AGREGAR"+e.toString());
            tipoProveedoresList=null;
        }
        return tipoProveedoresList;
    }

    public List<Paises> findAllPaises(){
        List<Paises> paisesList=null;
        try{
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_PAISES,null);
            if(cursor.moveToFirst()){
                paisesList = new ArrayList<Paises>();
                do {
                    Paises paises= new Paises();
                    paises.setId(cursor.getInt(0));
                    paises.setNombre(cursor.getString(1));
                    paises.setEstadoRegistro(cursor.getString(2));
                    paisesList.add(paises);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("INFO","ERROR AL AGREGAR"+e.toString());
            paisesList=null;
        }
        return paisesList;
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
            db.execSQL("UPDATE "+TABLE_PROVEEDORES+" SET estado_registro = '"+estadoRegistro+"' WHERE idproveedor='"+id+"'");
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

    public boolean actualizarRegistro(int id,String nombre,int RUC,int tipoProveedor,int pais,String estadoRegistro){
        boolean correcto=false;
        DbHelper dbHelper= new DbHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+TABLE_PROVEEDORES+" SET nombre='"+nombre+"' , RUC ='"+RUC+"'," +
                    "tipo_proveedor='"+tipoProveedor+"',pais='"+pais+"', estado_registro='"+estadoRegistro+"' " +
                    "WHERE idproveedor='"+id+"'");
            correcto=true;
        } catch(Exception e){
            e.toString();
            correcto=false;
        } finally {
            db.close();
        }
        return  correcto;
    }
}
