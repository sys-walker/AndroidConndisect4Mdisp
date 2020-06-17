package com.eps.connect4.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    public static  UsuariosSQLiteHelper INSTANCE;
    //INSERT INTO Usuarios (alias,size,time_control,fecha_txt,resultado_int)
    //Sentencia SQL para crear la tabla de Usuarios

    /*String sqlCreate = "CREATE TABLE Usuarios " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " alias TEXT, " +
            " size INTEGER, " +
            " time_control INTEGER, " +
            " fecha_txt TEXT, " +
            " resultado_int INTEGER )";*/ //exemples

    String sqlCreate = "CREATE TABLE Usuarios " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " alias TEXT, " +
            " size INTEGER, " +
            " time_control INTEGER, " +
            " fecha_txt TEXT, " +
            " resultado_int INTEGER )";




    public UsuariosSQLiteHelper(Context contexto) {
        super(contexto, constants.DATABASE_NAME, null, 3);
    }

    public static void saveInstanceHelper(UsuariosSQLiteHelper usdbh) {
        INSTANCE = usdbh;
    }


    /*public void create_samples(){
        Random r = new Random();
        //Insertamos 5 clientes de ejemplo
        for(int i=1; i<=25; i++)
        {
            //Generamos los datos de muestra
            String alias = "ejemplo"+i;
            int size = i;
            int timecontrol = i;
            String fecha = "fecahmen"+i;
            int resultado_int=(int)Math.abs(r.nextInt())%4;
            System.out.println("resultado ="+resultado_int);

            getWritableDatabase().execSQL("INSERT INTO Usuarios (alias,size,time_control,fecha_txt,resultado_int) " +
                    "VALUES ('" + alias + "', '" + size +"', '" + timecontrol + "', '" + fecha +"', '" + resultado_int + "')");
            //
        }
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creacion de la tabla
        db.execSQL(sqlCreate);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aqui utilizamos directamente
        //      la opcion de eliminar la tabla anterior y crearla de nuevo
        //      vacia con el nuevo formato.
        //      Sin embargo lo normal sera que haya que migrar datos de la
        //      tabla antigua a la nueva, por lo que este metodo deberia
        //      ser mas elaborado.

        //Se elimina la version anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios ORDER BY _id DESC", null);
        return cursor;
    }
}
