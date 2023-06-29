package com.backend.integradorSilvaVargas.Service.impl;

import com.backend.integradorSilvaVargas.Service.IOdontologoService;
import com.backend.integradorSilvaVargas.dto.OdontologoDto;
import com.backend.integradorSilvaVargas.entity.Odontologo;
import com.backend.integradorSilvaVargas.exception.BadRequestException;
import com.backend.integradorSilvaVargas.exception.ResourceNotFoundException;
import com.backend.integradorSilvaVargas.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class OdontologoService implements IOdontologoService {
        private final static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
        private final OdontologoRepository odontologoRepository;
        private final ObjectMapper objectMapper;

        @Autowired
        public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper objectMapper) {
            this.odontologoRepository = odontologoRepository;
            this.objectMapper = objectMapper;
        }


        @Override
        public OdontologoDto registrarOdontologo(Odontologo odontologo) throws BadRequestException {
            //throw new BadRequestException("No se pudo registar al odontólog");
            OdontologoDto odontologoDto = objectMapper.convertValue(odontologoRepository.save(odontologo), OdontologoDto.class);
            LOGGER.info("Se guardó el odontólogo: {}", odontologoDto);
            return odontologoDto;
        }


        @Override
        public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
            Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
            OdontologoDto odontologoDto = null;
            if (odontologoBuscado != null) {
                odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
                LOGGER.info("Se ha encontrado al odontólogo con ID {}: {}", id, odontologoDto);
            } else {
                LOGGER.info("El odontólogo con id {} no se encuentra en nuestros registros", id);
                throw new ResourceNotFoundException("El odontólogo no se encuentra en nuestros registros");
            }
            return odontologoDto;
        }

        @Override
        public List<OdontologoDto> listarOdontologos() {
            List<OdontologoDto> odontologoDtos = odontologoRepository
                    .findAll()
                    .stream()
                    .map(odontologo -> objectMapper.convertValue(odontologo, OdontologoDto.class))
                    .toList();
            LOGGER.info("Listando todos los odontólogos: {}", odontologoDtos);
            return odontologoDtos;
        }

        @Override
        public OdontologoDto actualizarOdontologo(Odontologo odontologo) throws BadRequestException {
            Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
            OdontologoDto odontologoActualizadoDto = null;
            if (odontologoAActualizar != null) {
                odontologoAActualizar = odontologo;
                odontologoActualizadoDto = registrarOdontologo(odontologoAActualizar);
                LOGGER.warn("El odontólogo con ID {} ha sido actualizado: {}", odontologo.getId(), odontologoActualizadoDto);
            } else {
                LOGGER.error("No es posible actualizar el odontólogo porque no se encuentra registrado");
                throw new BadRequestException("El odontólogo no existe en nuestros registros");
            }
            return odontologoActualizadoDto;
        }

        @Override
        public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
            if (buscarOdontologoPorId(id) != null) {
                odontologoRepository.deleteById(id);
                LOGGER.warn("Se ha eliminado al odontólogo con ID: {}", id);
            } else {
                LOGGER.error("No se ha encontrado el odontologo con id " + id);
                throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
            }
        }


    }