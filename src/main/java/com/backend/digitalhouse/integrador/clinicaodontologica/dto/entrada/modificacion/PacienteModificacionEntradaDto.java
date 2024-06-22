package com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.modificacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteModificacionEntradaDto {

    @NotNull
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String dni;

    @NotNull
    private LocalDate fechaIngreso;

    @NotNull
    private DomicilioModificacionEntradaDto domicilio;

    public PacienteModificacionEntradaDto() {
    }

    public PacienteModificacionEntradaDto(Long id, String nombre, String apellido, String dni, LocalDate fechaIngreso, DomicilioModificacionEntradaDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioModificacionEntradaDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioModificacionEntradaDto domicilio) {
        this.domicilio = domicilio;
    }
}
