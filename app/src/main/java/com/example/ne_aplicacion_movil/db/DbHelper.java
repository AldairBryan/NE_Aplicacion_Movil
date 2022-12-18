package com.example.ne_aplicacion_movil.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ne_aplicacion_movil.MainActivity;

import java.io.File;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=3;
    private static final String DATABASE_NOMBRE="proveedores.db";
    public static final String TABLE_PROVEEDORES="proveedores";
    public static final String TABLE_TIPO_PROVEEDORES="tipo_proveedores";
    public static final String TABLE_PAISES="paises";
    public static final String TABLE_ESTADO_REGISTRO="estado_registro";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        crearBaseDeDatos(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropBaseDeDatos(sqLiteDatabase);
    }

    public void crearBaseDeDatos(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_ESTADO_REGISTRO+"\n" +
                "(\n" +
                "  codigo varchar(1) NOT NULL,\n" +
                "  CONSTRAINT PK_estado_registro PRIMARY KEY (codigo)\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_PAISES+"\n" +
                "(\n" +
                "  idpais INTEGER NOT NULL\n" +
                "        CONSTRAINT PK_paises PRIMARY KEY AUTOINCREMENT,\n" +
                "  nombre varchar(45) NOT NULL,\n" +
                "  estado_registro varchar(1),\n" +
                "  CONSTRAINT Relationship6 FOREIGN KEY (estado_registro) REFERENCES estado_registro (codigo)\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship6 ON "+TABLE_PAISES+" (estado_registro);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_TIPO_PROVEEDORES+"\n" +
                "(\n" +
                "  idtipo_proveedor INTEGER NOT NULL\n" +
                "        CONSTRAINT PK_tipo_proveedores PRIMARY KEY AUTOINCREMENT,\n" +
                "  nombre varchar(45) NOT NULL,\n" +
                "  estado_registro varchar(1),\n" +
                "  CONSTRAINT Relationship7 FOREIGN KEY (estado_registro) REFERENCES estado_registro (codigo)\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship7 ON "+TABLE_TIPO_PROVEEDORES+" (estado_registro);\n");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_PROVEEDORES+"\n" +
                "(\n" +
                "  idproveedor INTEGER NOT NULL\n" +
                "        CONSTRAINT PK_proveedores PRIMARY KEY AUTOINCREMENT,\n" +
                "  nombre varchar(45) NOT NULL,\n" +
                "  RUC INTEGER NOT NULL,\n" +
                "  tipo_proveedor INTEGER,\n" +
                "  pais INTEGER,\n" +
                "  estado_registro varchar(1),\n" +
                "  CONSTRAINT Relationship8 FOREIGN KEY (tipo_proveedor) REFERENCES tipo_proveedores (idtipo_proveedor),\n" +
                "  CONSTRAINT Relationship9 FOREIGN KEY (pais) REFERENCES paises (idpais),\n" +
                "  CONSTRAINT Relationship10 FOREIGN KEY (estado_registro) REFERENCES estado_registro (codigo)\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship8 ON "+TABLE_PROVEEDORES+" (tipo_proveedor);\n");
        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship9 ON "+TABLE_PROVEEDORES+" (pais);\n");
        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship10 ON "+TABLE_PROVEEDORES+" (estado_registro);\n");
    }

    public void dropBaseDeDatos(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_PAISES);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_TIPO_PROVEEDORES);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_PROVEEDORES);
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_ESTADO_REGISTRO);
        onCreate(sqLiteDatabase);
    }
}
