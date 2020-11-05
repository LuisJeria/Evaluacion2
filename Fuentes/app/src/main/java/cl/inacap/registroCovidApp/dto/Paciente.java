package cl.inacap.registroCovidApp.dto;

import java.io.Serializable;

public class Paciente implements Serializable {

    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String fechaExamen;
    private String areaTrabajo;
    private boolean sintoma;
    private float temperatura;
    private boolean tos;
    private int presionArterial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(String fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public boolean isSintoma() {
        return sintoma;
    }

    public void setSintoma(boolean sintoma) {
        this.sintoma = sintoma;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isTos() {
        return tos;
    }

    public void setTos(boolean tos) {
        this.tos = tos;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaExamen='" + fechaExamen + '\'' +
                ", areaTrabajo='" + areaTrabajo + '\'' +
                ", sintoma=" + sintoma +
                ", temperatura=" + temperatura +
                ", tos=" + tos +
                ", presionArterial=" + presionArterial +
                '}';
    }
}