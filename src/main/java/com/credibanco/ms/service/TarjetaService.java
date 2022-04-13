package com.credibanco.ms.service;

import com.credibanco.ms.dto.TarjetaDto;
import com.credibanco.ms.entity.TarjetaEntity;
import com.credibanco.ms.repository.ITarjetaRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
public class TarjetaService {

    private final static Logger logger = LoggerFactory.getLogger(TarjetaService.class);

    @Autowired
    private ITarjetaRepository tarjetaRepository;

    public TarjetaDto crearTarjeta(TarjetaEntity tarjeta) {

        String numerValidacion;
        int numRandom = (int) ((Math.random() * 100));

        if (numRandom < 100) {
            numerValidacion = "0" + String.valueOf(numRandom);
        } else {
            numerValidacion = String.valueOf(numRandom);
        }

        String idSistema;
        try {
            idSistema = this.encriptarPan(tarjeta.getPan());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error al encriptar en PAN");
            return new TarjetaDto("01", "Error al encriptar en PAN", null);
        }

        tarjeta.setNumeroValidacion(numerValidacion);
        tarjeta.setEstado("Creada");
        tarjeta.setIdSistema(idSistema);

        try {
            tarjetaRepository.save(tarjeta);
        } catch (DataAccessException e) {
            logger.error("Error al guardar la tarjeta" + e);
            return new TarjetaDto("01", "Error al guardar la tarjeta", null);
        }

        TarjetaDto respuesta = new TarjetaDto();
        String panEnmascarado = tarjeta.getPan().substring(0,6) + "****" + tarjeta.getPan().substring(10,14);

        respuesta.setCodRespuesa("00");
        respuesta.setMensajeRespuesta("Éxito");
        respuesta.setNumeroValidacion(numerValidacion);
        respuesta.setPanEnmascarado(panEnmascarado);
        respuesta.setIdSistema(idSistema);

        return respuesta;

    }

    public TarjetaDto enrolarTarjeta(TarjetaDto infoTarjeta) {

        TarjetaEntity tarjeta = tarjetaRepository.findByIdSistema(infoTarjeta.getIdSistema());

        if (tarjeta == null) {
            logger.error("Error al cambiar estado de la tarjeta");
            return new TarjetaDto("01", "Tarjeta no existe", null);
        }
        if (!tarjeta.getNumeroValidacion().equals(infoTarjeta.getNumeroValidacion())) {
            logger.error("Error al cambiar estado de la tarjet");
            return new TarjetaDto("02", "Número de validación inválido", null);
        }

        tarjeta.setEstado("Enrolada");

        try {
            tarjetaRepository.save(tarjeta);
        } catch (DataAccessException e) {
            logger.error("Error al guardar la tarjeta" + e);
            return new TarjetaDto("01", "Error al guardar la tarjeta", null);
        }

        TarjetaDto respuesta = new TarjetaDto();
        String panEnmascarado = tarjeta.getPan().substring(0,6) + "****" + tarjeta.getPan().substring(10,14);

        respuesta.setCodRespuesa("00");
        respuesta.setMensajeRespuesta("Éxito");
        respuesta.setPanEnmascarado(panEnmascarado);

        return respuesta;

    }

    public TarjetaDto consultarTarjeta(String idSistema) {

        TarjetaEntity tarjeta = tarjetaRepository.findByIdSistema(idSistema);

        if (tarjeta == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Actor Not Found", null);
        }

        TarjetaDto respuesta = new TarjetaDto();
        String panEnmascarado = tarjeta.getPan().substring(0,6) + "****" + tarjeta.getPan().substring(10,14);

        respuesta.setPanEnmascarado(panEnmascarado);
        respuesta.setTitular(tarjeta.getTitular());
        respuesta.setCedula(tarjeta.getCedula());
        respuesta.setTelefono(tarjeta.getTelefono());
        respuesta.setEstado(tarjeta.getEstado());

        return respuesta;

    }

    public TarjetaDto eliminarTarjeta(TarjetaEntity infoTarjeta) {

        TarjetaEntity tarjeta = tarjetaRepository.findByIdSistema(infoTarjeta.getIdSistema());

        if (tarjeta == null) {
            return new TarjetaDto("01", "No se ha eliminado la tarjeta", null);
        }

        try {
            tarjetaRepository.delete(tarjeta);
        } catch (DataAccessException e) {
            logger.error("Error al elimiar la tarjeta" + e);
            return new TarjetaDto("01", "No se ha eliminado la tarjeta", null);
        }

        TarjetaDto respuesta = new TarjetaDto();

        respuesta.setCodRespuesa("00");
        respuesta.setMensajeRespuesta("Se ha eliminado la tarjeta");

        return respuesta;

    }


    private String encriptarPan(String pan) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pan.getBytes());
        byte[] digest = md.digest();
        String miHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();

        return miHash;

    }

}
