package com.backend.digitalhouse.integrador.clinicaodontologica.service;

import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;

    List<TurnoSalidaDto> listarTurno();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificado) throws ResourceNotFoundException;
}
