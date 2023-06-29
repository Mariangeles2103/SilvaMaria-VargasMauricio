package com.backend.integradorSilvaVargas.Service.impl;

import com.backend.integradorSilvaVargas.dto.OdontologoDto;
import com.backend.integradorSilvaVargas.dto.PacienteDto;
import com.backend.integradorSilvaVargas.dto.TurnoDto;
import com.backend.integradorSilvaVargas.entity.Domicilio;
import com.backend.integradorSilvaVargas.entity.Odontologo;
import com.backend.integradorSilvaVargas.entity.Paciente;
import com.backend.integradorSilvaVargas.entity.Turno;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TurnoServiceTest {
    @Autowired
    private  TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;}
    /*@Test
    void deberiaRegistrarTurno() throws BadRequestException {

        PacienteDto pacienteDto = pacienteService.registrarPaciente(new Paciente(
                "Serrana",
                "Marset",
                "2222222222",
                LocalDate.of(2023, 07, 01),
                new Domicilio("Florida", 2908, "Canelones", "Canelones")
        ));

        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(new Odontologo(
                "1111444455555",
                "Lucia",
                "Nuñez"
        ));
        ObjectMapper objectMapper = new ObjectMapper();
        Turno turno = new Turno(LocalDateTime.of(2023, 7, 1, 1, 0),
                objectMapper.convertValue(pacienteDto, Paciente.class));

        TurnoDto turnoDto = turnoService.registrarTurno();


    }

    @Test
    void deberialistarTodos() {
        List<TurnoDto> turnoDtoList = turnoService.listarTodos();
        Assertions.assertTrue(turnoDtoList.isEmpty());
    }

    @Test
    void deberiaEliminarTurnoConId1() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }*/
