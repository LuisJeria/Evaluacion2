package cl.inacap.registroCovidApp.dao;

import java.util.List;

import cl.inacap.registroCovidApp.dto.Paciente;

public interface PacientesDAO {

    Paciente save(Paciente p);
    List<Paciente> getAll();
}
