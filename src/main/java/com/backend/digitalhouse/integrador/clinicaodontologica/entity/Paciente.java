package com.backend.digitalhouse.integrador.clinicaodontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "PACIENTES")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOMBRE_PACIENTE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "APELLIDO_PACIENTE", nullable = false, length = 50)
    private String apellido;

    @Positive
    @Column(name = "DNI_PACIENTE", nullable = false, length = 50)
    private int dni;

    //@JsonProperty("fechaIngreso") en caso de que el campo a mapear este escrito distinto a nuestro modelo

    @Column(name = "FECHA_INGRESO", nullable = false)
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id") // DOMICILIO_ID -> nombre de la clave foránea
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")// nombre de la columna que tiene relación
    @JsonIgnore
    private Set<Turno> turnos;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, int dni, LocalDate fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
