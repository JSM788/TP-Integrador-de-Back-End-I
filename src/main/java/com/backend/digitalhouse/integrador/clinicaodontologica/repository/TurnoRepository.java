package com.backend.digitalhouse.integrador.clinicaodontologica.repository;

import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    /*
    @Query("Select t from TURNOS t join t.Paciente p where p.apellido =?2") //se indica el numero del parametro
    List<Turno> ListarTurnosPorApellidosPaciente(String apellido); //esto es un ejemplo para listar lo que queremos

    @Query(value = "SELECT * FROM TURNOS JOIN ODONTOLOGOS ON TURNOS.ODONTOLOGO_ID = ODONTOLOGOS.ID WHERE ODONTOLOGOS.APELLIDO = ?1",
    nativeQuery = true)
    List<Turno> listarTurnosPorApellidoOdontologo(String apellido);

     */

}
