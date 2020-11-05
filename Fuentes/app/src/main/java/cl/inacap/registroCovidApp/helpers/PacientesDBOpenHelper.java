package cl.inacap.registroCovidApp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesDBOpenHelper extends SQLiteOpenHelper {

    private final String sqlCreate = "CREATE TABLE pacientes ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
            "rut TEXT," +
            "nombre TEXT,"+
            "apellido TEXT,"+
            "fecha TEXT,"+
            "areaTrabajo TEXT,"+
            "sintoma INTEGER,"+
            "temperatura REAL,"+
            "tos INTEGER,"+
            "presionArterial INTEGER)";
    public PacientesDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(this.sqlCreate);
        db.execSQL("INSERT INTO pacientes(rut,nombre,apellido,fecha,areaTrabajo,sintoma,temperatura,tos,presionArterial) VALUES ('7420329-9','Paciente','Prueba','03/11/2020','Otro',0,36.9,0,120)");
        db.execSQL("INSERT INTO pacientes(rut,nombre,apellido,fecha,areaTrabajo,sintoma,temperatura,tos,presionArterial) VALUES ('7420329-9','Paciente','Prueba','03/11/2020','Otro',1,39.9,1,130)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pacientes");
        db.execSQL(this.sqlCreate);
    }
}
