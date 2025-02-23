package com.example.prypoliza1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "poliza.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "poliza";
    private static final String COLUM_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_VALOR = "valor_auto";
    private static final String COLUM_ACCIDENTE = "accidentes";
    private static final String COLUM_MODELO = "modelo";
    private static final String COLUM_EDAD = "edad";
    private static final String COLUM_COSTO = "costo_poliza";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_VALOR + " REAL NOT NULL, " +
                    COLUM_ACCIDENTE + " INTEGER NOT NULL, " +
                    COLUM_MODELO + " TEXT NOT NULL, " +
                    COLUM_EDAD + " TEXT NOT NULL, " +
                    COLUM_COSTO + " REAL NOT NULL)";



    private static final String TABLE_NAME_MODELOS = "modelos";


    private static final String TABLE_MODELO_CREATE =
            "CREATE TABLE " + TABLE_NAME_MODELOS + "(" +
                    COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL, ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_MODELO_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // Metodo para insertar datos en la base de datos
    public boolean insertarPoliza(String nombre, double valorAuto, String modelo, int accidentes, String edad, double costo_Poliza) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_VALOR, valorAuto);
        values.put(COLUM_MODELO, modelo);
        values.put(COLUM_ACCIDENTE, accidentes);
        values.put(COLUM_EDAD, edad);
        values.put(COLUM_COSTO, costo_Poliza);

        long resultado = sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();

        return resultado != -1;
    }

    public boolean insertarModelo(String nombre) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        long resultado = sqLiteDatabase.insert(TABLE_NAME_MODELOS, null, values);
        sqLiteDatabase.close();
        return resultado != -1;
    }

    public Cursor obtenerPolizas() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}



















































