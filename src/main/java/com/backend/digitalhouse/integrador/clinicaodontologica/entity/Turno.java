package com.backend.digitalhouse.integrador.clinicaodontologica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", nullable = false) // odontologo -> entidad : id -> referencia
    private Odontologo odontologo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "FECHA_Y_HORA", nullable = false)
    private LocalDateTime fechaYHora;

    public Turno() {
        }
        public Turno(Odontologo odontologo, Paciente paciente, LocalDateTime fechaYHora) {
            this.odontologo = odontologo;
            this.paciente = paciente;
            this.fechaYHora = fechaYHora;
        }

        public Long getId() {
            return id;
        }

        public Odontologo getOdontologo() {
            return odontologo;
        }

        public void setOdontologo(Odontologo odontologo) {
            this.odontologo = odontologo;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }

        public LocalDateTime getFechaYHora() {
            return fechaYHora;
        }

        public void setFechaYHora(LocalDateTime fechaYHora) {
            this.fechaYHora = fechaYHora;
        }
}