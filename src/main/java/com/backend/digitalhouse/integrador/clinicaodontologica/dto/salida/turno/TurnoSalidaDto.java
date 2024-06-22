package com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.turno;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
        private int id;
        private PacienteTurnoSalidaDto pacienteTurnoSalidaDto;
        private OdontologoTurnoSalidaDto odontologoTurnoSalidaDto;
        private LocalDateTime fechaYHora;

        public TurnoSalidaDto() {
        }

        public TurnoSalidaDto(int id, PacienteTurnoSalidaDto pacienteTurnoSalidaDto, OdontologoTurnoSalidaDto odontologoTurnoSalidaDto, LocalDateTime fechaYHora) {
            this.id = id;
            this.pacienteTurnoSalidaDto = pacienteTurnoSalidaDto;
            this.odontologoTurnoSalidaDto = odontologoTurnoSalidaDto;
            this.fechaYHora = fechaYHora;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public PacienteTurnoSalidaDto getPacienteTurnoSalidaDto() {
            return pacienteTurnoSalidaDto;
        }

        public void setPacienteTurnoSalidaDto(PacienteTurnoSalidaDto pacienteTurnoSalidaDto) {
            this.pacienteTurnoSalidaDto = pacienteTurnoSalidaDto;
        }

        public OdontologoTurnoSalidaDto getOdontologoTurnoSalidaDto() {
            return odontologoTurnoSalidaDto;
        }

        public void setOdontologoTurnoSalidaDto(OdontologoTurnoSalidaDto odontologoTurnoSalidaDto) {
            this.odontologoTurnoSalidaDto = odontologoTurnoSalidaDto;
        }

        public LocalDateTime getFechaYHora() {
            return fechaYHora;
        }

        public void setFechaYHora(LocalDateTime fechaYHora) {
            this.fechaYHora = fechaYHora;
        }
    @Override
    public String toString() {
        return "Id: " + id + " - Paciente: " + pacienteTurnoSalidaDto + " - Odontologo: " + odontologoTurnoSalidaDto + " - Fecha y hora: " + fechaYHora;
    }
}

