package com.credibanco.ms.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "transacciones")
public class TransaccionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tansaccion")
    private Integer idTransaccion;

    @NotNull
    private String referencia;

    @NotNull
    @Column(name = "total_compra")
    private Integer totalCompra;

    @NotNull
    private String direccion;

    @NotNull
    private String estado;

    @NotNull
    @Column(name = "fecha_transaccion")
    private Timestamp fechatransaccion;

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Integer totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechatransaccion() {
        return fechatransaccion;
    }

    public void setFechatransaccion(Timestamp fechatransaccion) {
        this.fechatransaccion = fechatransaccion;
    }
}
