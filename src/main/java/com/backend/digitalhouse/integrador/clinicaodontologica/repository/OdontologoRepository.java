package com.backend.digitalhouse.integrador.clinicaodontologica.repository;

import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
