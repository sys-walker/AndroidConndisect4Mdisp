package com.eps.androidconnect4.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.eps.androidconnect4.MainActivity;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    public static  UsuariosSQLiteHelper instance;
//INSERT INTO Usuarios (alias,size,time_control,fecha_txt,resultado_int)
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Usuarios " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " alias TEXT, " +
            " size INTEGER, " +
            " time_control INTEGER, " +
            " fecha_txt TEXT, " +
            " resultado_int INTEGER )";

   /* public UsuariosSQLiteHelper( Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/



   public UsuariosSQLiteHelper(Context contexto, String databaseName) {
        super(contexto, databaseName, null, 3);
    }

    public static void saveInstanceHelper(UsuariosSQLiteHelper usdbh) {
        instance = usdbh;
    }


    public void create_samples(){
        //Insertamos 5 clientes de ejemplo
        /*for(int i=1; i<=5; i++)
        {
            //Generamos los datos de muestra
            String nombre = "Exemple" + i;
            String telefono = "688-555-981" + i;
            int email = (i%3);
            System.out.println("#################"+email);


            //Insertamos los datos en la tabla Clientes
            getWritableDatabase().execSQL("INSERT INTO Usuarios (nombre, telefono, email) " +
                    "VALUES ('" + nombre + "', '" + telefono +"', '" + email + "')");
        }*/
    }

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
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios", null);
        return cursor;
    }
}