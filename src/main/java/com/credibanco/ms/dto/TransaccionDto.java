package com.credibanco.ms.dto;

import com.credibanco.ms.entity.TransaccionEntity;

public class TransaccionDto extends TransaccionEntity {

    private String codRespuesa;
    private String mensajeRespuesta;
    private String idSistema;
    private String estadoTransaccion;

    public TransaccionDto() {

    }

    public TransaccionDto(String codRespuesa, String mensajeRespuesta, String estadoTransaccion) {
        this.codRespuesa = codRespuesa;
        this.mensajeRespuesta = mensajeRespuesta;
        this.estadoTransaccion = estadoTransaccion;
    }

    public String getCodRespuesa() {
        return codRespuesa;
    }

    public void setCodRespuesa(String codRespuesa) {
        this.codRespuesa = codRespuesa;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    public String getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }
}
