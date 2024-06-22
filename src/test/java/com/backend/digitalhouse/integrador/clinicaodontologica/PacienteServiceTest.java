package com.backend.digitalhouse.integrador.clinicaodontologica;

import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.impl.PacienteService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ClinicaOdontologicaApplication.class)
//@TestPropertySource(locations = "classpath:application-test_properties")


public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

   @Test
   @Order(1)
    void deberiaAgregarUnPaciente_cuandoLasCasillasEstenCompletas() {
        try {

            DomicilioEntradaDto domicilioPacienteNuevo = new DomicilioEntradaDto("Conquistadores", 23, "Medellin", "Antioquia");

            PacienteEntradaDto pacienteNuevo = new PacienteEntradaDto("Astrid", "Torres", 111111, LocalDate.of(2023, 9, 11), domicilioPacienteNuevo);

            pacienteService.registrarPaciente(pacienteNuevo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(pacienteService.buscarPacientePorId(1L).getId());
        assertEquals("Astrid", pacienteService.buscarPacientePorId(1L).getNombre());

    }
   @Test
    void deberiaRetornarUnaListaNoVaciaDePacientes(){
       try {

           DomicilioEntradaDto domicilioPacienteNuevo = new DomicilioEntradaDto("Conquistadores", 23, "Medellin", "Antioquia");

           PacienteEntradaDto pacienteNuevo = new PacienteEntradaDto("Astrid", "Torres", 111111, LocalDate.of(2023, 9, 11), domicilioPacienteNuevo);

           pacienteService.registrarPaciente(pacienteNuevo);

       } catch (Exception e) {
           e.printStackTrace();
       }

        assertTrue(pacienteService.listarPacientes().size() > 0);

   }
    @Test
    @Order(3)
    void alIntertarActualizarElpacienteId2_deberaLanzarUnaResourceNotFoundException() {

        PacienteModificacionEntradaDto pacienteModificacionEntradaDto = new PacienteModificacionEntradaDto();
        pacienteModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.modificarPaciente(pacienteModificacionEntradaDto));

    }

   }
