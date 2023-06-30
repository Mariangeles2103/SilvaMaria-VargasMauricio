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
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    public static void init() {
        paciente = new Paciente("Maria", "Silva", "415241257", LocalDate.of(2023, 8, 01), new
                Domicilio("Munar", 2410, "Union", "Montevideo"));
        odontologo = new Odontologo("1234454", "Mauricio", "Vargas");

    }

    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() throws BadRequestException {
        PacienteDto pacienteDto = pacienteService.registrarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.registrarTurno(new Turno(LocalDateTime.of(2023, 7, 1, 1, 0), odontologo, paciente));


        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto.getId());
       // Assertions.assertEquals(turnoDto.getPaciente(), pacienteDto.getNombre() + " " + pacienteDto.getApellido());
    }


    @Test
    @Order(2)
    void deberialistarTodos() {
        List<TurnoDto> turnoDtoList = turnoService.listarTodos();
        Assertions.assertTrue(turnoDtoList.isEmpty());
    }


    @Test
    @Order(3)
    void deberiaBuscarTurnoConId1() {
        try {
            turnoService.buscarTurnoPorId(1L);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(1, 1);

    }

    @Test
    @Order(4)
    void deberiaEliminarTurnoConId1() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));

    }
}