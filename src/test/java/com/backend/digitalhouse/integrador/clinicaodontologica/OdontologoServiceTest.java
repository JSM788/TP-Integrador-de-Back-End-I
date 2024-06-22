package com.backend.digitalhouse.integrador.clinicaodontologica;

import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.integrador.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.integrador.clinicaodontologica.service.impl.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaAgregarUnOdontologo_cuandoLasCasillasEstenCompletas() {
        OdontologoSalidaDto odontologoSalidaDto = null;
        try {
            OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("12345", "Andrea", "Romero" );
            odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Andrea",odontologoSalidaDto.getNombre());
    }
    @Test
    @Order(2)
    void deberiaRetornarUnaListaNoVaciaDeLosOdontologos(){
        assertTrue(odontologoService.listarOdontologos().size() > 0);
    }
    @Test
    @Order(3)
    void alIntertarActualizarElOdontologoId2_deberaLanzarUnaResourceNotFoundException() {

        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto));

    }

}

