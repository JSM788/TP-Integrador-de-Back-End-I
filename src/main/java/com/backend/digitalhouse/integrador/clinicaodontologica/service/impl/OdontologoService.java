package com.backend.digitalhouse.integrador.clinicaodontologica.service.impl;

import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integrador.clinicaodontologica.repository.OdontologoRepository;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) throws BadRequestException {
        if (odontologo != null) {
            Odontologo odGuardado = odontologoRepository.save(dtoEntradaAEntidad(odontologo));
            OdontologoSalidaDto odontologoSalidaDto = entidadADtoSalida(odGuardado);
            LOGGER.info("Odontologo guardado: {}", odontologoSalidaDto);
            return odontologoSalidaDto;
        } else {
            LOGGER.error("No se puedo registrar el odontologo");
            throw new BadRequestException("No se puedo registrar el odontologo");
        }
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificado) throws ResourceNotFoundException {
        Odontologo odontologoRecibido = dtoModificadoAEntidad(odontologoModificado);
        Odontologo odontologoAModificar = odontologoRepository.findById(odontologoRecibido.getId()).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoAModificar != null) {

            odontologoAModificar = odontologoRecibido;
            odontologoRepository.save(odontologoAModificar);
            odontologoSalidaDto = entidadADtoSalida(odontologoAModificar);
            LOGGER.warn("Odontologo actualizado: {}", odontologoSalidaDto);

        } else {

            LOGGER.error("No fue posible actualizar los datos, odontologo no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos, odontologo no se encuentra registrado");
        }
        return odontologoSalidaDto;
    }

    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null); //orElse -> para anular el null
        OdontologoSalidaDto odontologoSalida = null;
        if (odontologoBuscado != null) {
            odontologoSalida = entidadADtoSalida(odontologoBuscado);
            LOGGER.info("Odontolgo por id: {}", odontologoSalida);
        } else LOGGER.error("El id_odontologo no se encuentra registrado en la baase de datos");
        return odontologoSalida;
    }

    public List<OdontologoSalidaDto> listarOdontologos() {

        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll().stream()
                .map(this::entidadADtoSalida)
                .toList(); // -> .toList para listar
        LOGGER.info("Listado de todos los odontologos: {}", odontologos);
        return odontologos;
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }

    public Odontologo dtoEntradaAEntidad(OdontologoEntradaDto odontologoEntradaDto) {
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }

    public OdontologoSalidaDto entidadADtoSalida(Odontologo odontologo) {
        return modelMapper.map(odontologo, OdontologoSalidaDto.class);
    }

    public Odontologo dtoModificadoAEntidad(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) {
        return modelMapper.map(odontologoModificacionEntradaDto, Odontologo.class);
    }
}



