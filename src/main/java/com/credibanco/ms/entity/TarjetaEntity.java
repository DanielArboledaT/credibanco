package com.credibanco.ms.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tarjetas")
public class TarjetaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Integer idTarjeta;

    @NotNull
    private String pan;

    @NotNull
    private String titular;

    @NotNull
    private String cedula;

    @NotNull
    private String tipo;

    @NotNull
    private String telefono;

    @NotNull
    private String estado;

    @NotNull
    @Column(name = "numero_validacion")
    private String numeroValidacion;

    @NotNull
    @Column(name = "id_sistema")
    private String idSistema;

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumeroValidacion() {
        return numeroValidacion;
    }

    public void setNumeroValidacion(String numeroValidacion) {
        this.numeroValidacion = numeroValidacion;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }
}
