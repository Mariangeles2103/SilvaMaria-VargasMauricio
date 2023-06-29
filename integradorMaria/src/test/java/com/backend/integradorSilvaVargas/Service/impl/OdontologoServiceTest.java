package com.backend.integradorSilvaVargas.Service.impl;

import com.backend.integradorSilvaVargas.dto.OdontologoDto;
import com.backend.integradorSilvaVargas.entity.Odontologo;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    private static Odontologo odontologo;



    @Test
    @Order(1)
    void deberiaRegistrarOdontologo() throws BadRequestException {
        Odontologo odontologoARegistrar = new Odontologo(
                "466464646464646",
                "Alejandro",
                "Perez");
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoARegistrar);
        Assertions.assertNotNull(odontologoDto);
        Assertions.assertNotNull(odontologoDto.getId());

    }

    @Test
    @Order(2)
    void deberiaListarUnOdontologo() {
        List<OdontologoDto> odontologosDtos = odontologoService.listarOdontologos();
        assertEquals(1, odontologosDtos.size());
    }

    @Test
    @Order(3)
    void deberiaEliminarOdontologoConId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> odontologoService.eliminarOdontologo(1L));
    }
}
