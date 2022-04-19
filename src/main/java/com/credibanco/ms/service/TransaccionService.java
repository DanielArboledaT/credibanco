package com.credibanco.ms.service;


import com.credibanco.ms.dto.TarjetaDto;
import com.credibanco.ms.dto.TransaccionDto;
import com.credibanco.ms.entity.TarjetaEntity;
import com.credibanco.ms.entity.TransaccionEntity;
import com.credibanco.ms.repository.ITarjetaRepository;
import com.credibanco.ms.repository.ITransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TransaccionService {

    private final static Logger logger = LoggerFactory.getLogger(TransaccionService.class);

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Autowired
    private ITarjetaRepository tarjetaRepository;

    public TransaccionDto crearTransaccion(TransaccionDto infoTransaccion) {

        TarjetaEntity tarjeta = tarjetaRepository.findByIdSistema(infoTransaccion.getIdSistema());

        if (tarjeta == null) {
            logger.error("Error al crear la transacción");
            return new TransaccionDto("01", "Tarjeta no existe", null);
        }
        if (!tarjeta.getEstado().equals("Enrolada")) {
            logger.error("Error al elimiar la tarjeta");
            return new TransaccionDto("02", "Tarjeta no enrolada", "Rechazada");
        }

        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setReferencia(infoTransaccion.getReferencia());
        transaccion.setTotalCompra(infoTransaccion.getTotalCompra());
        transaccion.setDireccion(infoTransaccion.getDireccion());
        transaccion.setEstado("Aprobada");
        Date date = new Date();
        transaccion.setFechatransaccion(new Timestamp(date.getTime()));

        transaccionRepository.save(transaccion);

        TransaccionDto respuesta = new TransaccionDto();

        respuesta.setCodRespuesa("00");
        respuesta.setMensajeRespuesta("Compra exitosa");
        respuesta.setEstadoTransaccion("Aprobada");
        respuesta.setReferencia(infoTransaccion.getReferencia());

        return respuesta;

    }

    public TransaccionDto anularTransaccion(TransaccionDto infoTransaccion) {

        TransaccionEntity transaccion = transaccionRepository.findByReferencia(infoTransaccion.getReferencia());

        if (transaccion == null) {
            logger.error("Error al anular la transacción");
            return new TransaccionDto("01", "Número de referencia inválido", null);
        }

        Date date = new Date();
        Timestamp fechaActual = new Timestamp(date.getTime());
        Date fechaTransaccion = new Date(transaccion.getFechatransaccion().getTime());

        long diff = fechaActual.getTime() - fechaTransaccion.getTime();
        long minutos = TimeUnit.MILLISECONDS.toMinutes(diff);

        logger.info("Minutos de diferencia" + minutos);
        if (minutos > 5) {
            logger.error("Error al elimiar la tarjeta");
            return new TransaccionDto("02", "No se puede anular transacción", null);
        }

        transaccion.setEstado("Anulada");

        TransaccionDto respuesta = new TransaccionDto();

        respuesta.setCodRespuesa("00");
        respuesta.setMensajeRespuesta("Compra anulada");
        respuesta.setReferencia(infoTransaccion.getReferencia());

        return respuesta;

    }

}
