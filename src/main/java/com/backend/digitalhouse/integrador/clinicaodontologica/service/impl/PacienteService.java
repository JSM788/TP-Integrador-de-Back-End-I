package com.backend.digitalhouse.integrador.clinicaodontologica.service.impl;

import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Paciente;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integrador.clinicaodontologica.repository.PacienteRepository;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) throws BadRequestException {
        if (paciente != null) {
            Paciente paGuardado = pacienteRepository.save(dtoEntradaAEntidad(paciente));
            PacienteSalidaDto pacienteSalidaDto = entidadADtoSalida(paGuardado);
            LOGGER.info("Paciente guardado: {}", pacienteSalidaDto);
            return pacienteSalidaDto;
        } else {
            LOGGER.error("No se puedo registrar el paciente");
            throw new BadRequestException("No se puedo registrar el paciente");
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException {
        Paciente pacienteRecibido = dtoModificadoAEntidad(pacienteModificado);
        Paciente pacienteAModificar = pacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;

        if (pacienteAModificar != null) {

            pacienteAModificar = pacienteRecibido;
            pacienteRepository.save(pacienteAModificar);
            pacienteSalidaDto = entidadADtoSalida(pacienteAModificar);
            LOGGER.info("EL paciente ha sido actualizado: {}", pacienteAModificar);

        } else {

            LOGGER.error("No fue posible actualizar los datos, odontologo no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, odontologo no se encuentra registrado");
        }
        return pacienteSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;
        if (pacienteBuscado != null) {
            pacienteSalidaDto = entidadADtoSalida(pacienteBuscado);
            LOGGER.info("Paciente por id: {}", pacienteSalidaDto);
        } else LOGGER.info("Paciente por id: {}", id);
        return pacienteSalidaDto;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {

        List<PacienteSalidaDto> pacientes = pacienteRepository.findAll().stream()
                .map(paciente -> entidadADtoSalida(paciente)).toList();
        LOGGER.info("Listado de todos los odontologos: {}", pacientes);
        return pacientes;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el paciente con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilio));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    public Paciente dtoEntradaAEntidad(PacienteEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    public PacienteSalidaDto entidadADtoSalida(Paciente paciente) {
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    public Paciente dtoModificadoAEntidad(PacienteModificacionEntradaDto pacienteModificacionEntradaDto) {
        return modelMapper.map(pacienteModificacionEntradaDto, Paciente.class);
    }

}