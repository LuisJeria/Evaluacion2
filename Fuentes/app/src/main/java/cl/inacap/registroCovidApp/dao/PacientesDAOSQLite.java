package cl.inacap.registroCovidApp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.registroCovidApp.dto.Paciente;
import cl.inacap.registroCovidApp.helpers.PacientesDBOpenHelper;

public class PacientesDAOSQLite implements PacientesDAO {

    private PacientesDBOpenHelper pacientesDB;

    public PacientesDAOSQLite(Context contexto){
        this.pacientesDB = new PacientesDBOpenHelper(contexto,"PacientesDB",null,1);
    }

    @Override
    public Paciente save(Paciente p) {
        int sintomaInt = p.isSintoma() ? 1:0;
        int tosInt = p.isTos() ? 1:0;
        SQLiteDatabase writer = this.pacientesDB.getWritableDatabase();
        String sql ="INSERT INTO pacientes" +
                "(rut,nombre,apellido,fecha,areaTrabajo,sintoma,temperatura,tos,presionArterial) VALUES('" +
                p.getRut()+"','"+
                p.getNombre()+"','"+
                p.getApellido()+"','"+
                p.getFechaExamen()+"','"+
                p.getAreaTrabajo()+"','"+
                sintomaInt+"','"+
                p.getTemperatura()+"','"+
                tosInt+"','"+
                p.getPresionArterial()+"')";

        writer.execSQL(sql);
        writer.close();
        return p;
    }

    @Override
    public List<Paciente> getAll() {

        SQLiteDatabase reader = this.pacientesDB.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try{
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido,fecha,areaTrabajo,sintoma,temperatura,tos," +
                        "presionArterial FROM pacientes",null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFechaExamen(c.getString(4));
                        p.setAreaTrabajo(c.getString(5));
                        if(c.getInt(6)==1){
                            p.setSintoma(true);
                        }else{
                            p.setSintoma(false);
                        }
                        p.setTemperatura(c.getFloat(7));
                        if(c.getInt(8)==1){
                            p.setTos(true);
                        }else{
                            p.setTos(false);
                        }
                        p.setPresionArterial(c.getInt(9));
                        pacientes.add(p);
                    }while(c.moveToNext());
                }
                reader.close();

            }

        }catch (Exception ex){
            pacientes = null;
        }
        return pacientes;
    }

    @Override
    public List<Paciente> getByRut(String s) {

        SQLiteDatabase reader = this.pacientesDB.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();

        try{
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido,fecha,areaTrabajo,sintoma,temperatura,tos," +
                        "presionArterial FROM pacientes WHERE rut ='" + s + "'",null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFechaExamen(c.getString(4));
                        p.setAreaTrabajo(c.getString(5));
                        if(c.getInt(6)==1){
                            p.setSintoma(true);
                        }else{
                            p.setSintoma(false);
                        }
                        p.setTemperatura(c.getFloat(7));
                        if(c.getInt(8)==1){
                            p.setTos(true);
                        }else{
                            p.setTos(false);
                        }
                        p.setPresionArterial(c.getInt(9));
                        pacientes.add(p);
                    }while(c.moveToNext());
                }
                reader.close();
            }

        }catch (Exception e){
            pacientes = null;
        }
        return pacientes;
    }
}
