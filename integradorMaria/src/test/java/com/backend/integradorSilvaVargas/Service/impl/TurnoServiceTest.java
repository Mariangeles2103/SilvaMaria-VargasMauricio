package com.backend.integradorSilvaVargas.Service.impl;


import com.backend.integradorSilvaVargas.dto.TurnoDto;
import com.backend.integradorSilvaVargas.entity.Domicilio;
import com.backend.integradorSilvaVargas.entity.Odontologo;
import com.backend.integradorSilvaVargas.entity.Paciente;
import com.backend.integradorSilvaVargas.entity.Turno;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TurnoServiceTest {

    private final TurnoService turnoService;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private final ObjectMapper mapper;


    private static Paciente paciente;
    private static Odontologo odontologo;
    private static Turno turno;


    @Autowired
    TurnoServiceTest(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService, ObjectMapper mapper) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.mapper = mapper;
    }
    @BeforeAll
    public static void crearTodasLasEntidades() {
        odontologo = new Odontologo(
                "995522222222",
                "Luciana",
                "Murga"
        );
        paciente = new Paciente(
                "Serrana",
                "Marset",
                "2222222222",
                LocalDate.of(2023, 07, 01),
                new Domicilio("Florida", 2908, "Canelones", "Canelones")
        );
        turno = new Turno(LocalDateTime.of(2023, 7, 1, 1, 0), odontologo, paciente);
    }


    @Test
    @Order(1)
    void deberiaRegistarUnTurno() throws BadRequestException, ResourceNotFoundException {
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
        TurnoDto turnoDto = turnoService.registrarTurno(turno);

        Assertions.assertEquals(LocalDateTime.of(2023, 7, 1, 1, 0), turno.getFechaHora());
        Assertions.assertEquals(1, (Long) turno.getId());
    }
        @Test
        @Order(2)
        void deberialistarTodos () {
            List<TurnoDto> turnoDtoList = turnoService.listarTodos();
            Assertions.assertTrue(turnoDtoList.isEmpty());
        }

        @Test
        @Order(3)
        void deberiaEliminarTurnoConId1 () throws ResourceNotFoundException {
            turnoService.eliminarTurno(1L);
            Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
        }
    }