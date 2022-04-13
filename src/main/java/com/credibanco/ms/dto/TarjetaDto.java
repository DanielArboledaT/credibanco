package com.credibanco.ms.dto;

import com.credibanco.ms.entity.TarjetaEntity;

public class TarjetaDto extends TarjetaEntity {

    private String codRespuesa;
    private String mensajeRespuesta;
    private String PanEnmascarado;

    public TarjetaDto() {

    }

    public TarjetaDto(String codRespuesa, String mensajeRespuesta, String panEnmascarado) {
        this.codRespuesa = codRespuesa;
        this.mensajeRespuesta = mensajeRespuesta;
        PanEnmascarado = panEnmascarado;
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

    public String getPanEnmascarado() {
        return PanEnmascarado;
    }

    public void setPanEnmascarado(String panEnmascarado) {
        PanEnmascarado = panEnmascarado;
    }

}
