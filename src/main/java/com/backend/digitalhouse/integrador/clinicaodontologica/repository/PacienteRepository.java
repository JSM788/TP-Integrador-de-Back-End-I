package com.backend.digitalhouse.integrador.clinicaodontologica.repository;

import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends CrudRepository <Paciente, Long> {
    boolean existsById(Long id);

    List<Paciente> findAll();

    Optional<Paciente> findById(Long id);

}

