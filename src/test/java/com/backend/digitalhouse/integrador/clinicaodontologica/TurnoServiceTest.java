package com.backend.digitalhouse.integrador.clinicaodontologica;

import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Odontologo;
import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Paciente;
import com.backend.digitalhouse.integrador.clinicaodontologica.entity.Turno;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.impl.OdontologoService;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.impl.PacienteService;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.impl.TurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static PacienteEntradaDto pacienteEntradaDto;

    private static OdontologoEntradaDto odontologoEntradaDto;

    @BeforeAll
    static void doBefore() {
        pacienteEntradaDto = new PacienteEntradaDto("Juliana", "Casas", 124233, LocalDate.parse("2023-09-21"), new DomicilioEntradaDto("Buenavista", 1314, "Medellín", "Antioquia"));
        odontologoEntradaDto = new OdontologoEntradaDto("UI/12345", "Didier", "Diaz");
    }

    @Test
    @Order(1)
    void deberiaAgregarUnTurno_cuandoLasCasillasEstenCompletas() throws BadRequestException {

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        PacienteSalidaDto pacienteSalidaDto  = pacienteService.registrarPaciente(pacienteEntradaDto);
        TurnoSalidaDto turnoSalidaDto  = turnoService.registrarTurno(new TurnoEntradaDto(pacienteSalidaDto.getId(),odontologoSalidaDto.getId(), LocalDateTime.of(2023,9,22,18,00)));


        Assertions.assertEquals(1L, turnoSalidaDto.getId());
    }
}