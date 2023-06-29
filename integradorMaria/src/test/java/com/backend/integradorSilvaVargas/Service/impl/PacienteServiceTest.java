package com.backend.integradorSilvaVargas.Service.impl;

import com.backend.integradorSilvaVargas.dto.PacienteDto;
import com.backend.integradorSilvaVargas.entity.Domicilio;
import com.backend.integradorSilvaVargas.entity.Paciente;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class PacienteServiceTest {
    @Autowired
    private  PacienteService pacienteService;



    @Test
    @Order(2)
    void deberiaBuscarPacientePorId() throws ResourceNotFoundException {
        PacienteDto pacienteEncontrado = pacienteService.buscarPacientePorId(1L);
        Assertions.assertNotNull(pacienteEncontrado);
    }

    @Test
    @Order(1)
    void deberiaRegistrarPaciente() throws BadRequestException {

            Paciente pacienteARegistrar = new Paciente(
                    "Serrana",
                    "Marset",
                    "2222222222",
                    LocalDate.of(2023, 07, 01),
                    new Domicilio("Florida", 2908, "Canelones", "Canelones")

            );
            PacienteDto pacienteDto = pacienteService.registrarPaciente(pacienteARegistrar);
            Assertions.assertNotNull(pacienteDto);
            Assertions.assertNotNull(pacienteDto.getId());


    }

    @Test
    @Order(3)
    void deberiaEliminarElPacienteConId1() throws ResourceNotFoundException {
            pacienteService.eliminarPaciente(1L);
            Assertions.assertTrue(pacienteService.listarPacientes().isEmpty());
    }
}