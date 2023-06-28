package com.backend.integradorMaria.controller;


import com.backend.integradorMaria.Service.impl.TurnoService;
import com.backend.integradorMaria.dto.OdontologoDto;
import com.backend.integradorMaria.dto.TurnoDto;
import com.backend.integradorMaria.entity.Turno;
import com.backend.integradorMaria.exception.BadRequestException;
import com.backend.integradorMaria.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;


    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //GET

    @Operation(summary = "Buscar turno por id", responses = {
            @ApiResponse(responseCode = "200", description = "Turno obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.buscarTurnoPorId(id);
        if (turnoDto != null) response = ResponseEntity.ok(turnoDto);
        return response;
    }

    @Operation(summary = "Listado de todos los turnos", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Turnos obtenidos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @GetMapping
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTodos();
    }

    //POST
    @Operation(summary = "Registrar un turno", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Turno creado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> guardarTurno(@Valid @RequestBody Turno turno) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.registrarTurno(turno);
        if (turnoDto != null) respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //DELETE
    @Operation(summary = "Eliminar un turno", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Se eliminó al turno",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el id no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Servidor no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Se ha eliminado al turno");
    }

    //PUT
    @Operation(summary = "Actualizar un turno", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Turno actualizado con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoDto.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Error de tipeo, el turno no existe",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@Valid @RequestBody Turno turno) throws BadRequestException {
        ResponseEntity<TurnoDto> response = ResponseEntity.badRequest().build();
        TurnoDto turnoDto = turnoService.actualizarTurno(turno);
        if (turnoDto != null) response = ResponseEntity.ok(turnoDto);
        return response;
    }
}


