package com.b.dateroapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "BUSES")
public class BusesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_bus;
    private String mod_bus;
    private String placa_bus;
    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;
    @Column(precision = 20, scale = 15)
    private BigDecimal  latitud;
    private Boolean est_bus;

    @ManyToOne
    @JoinColumn(name = "trabajador_id", referencedColumnName = "id_tra", nullable = false)
    private TrabajadoresModel trabajadoresModel;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "id_emp", nullable = false)
    private EmpresasModel empresasModel;

    @ManyToOne
    @JoinColumn(name = "ruta_id", referencedColumnName = "id_ruta", nullable = false)
    private RutasModel rutasModel;


    public BusesModel() {
    }

    public Long getId_bus() {
        return id_bus;
    }

    public void setId_bus(Long id_bus) {
        this.id_bus = id_bus;
    }

    public String getMod_bus() {
        return mod_bus;
    }

    public void setMod_bus(String mod_bus) {
        this.mod_bus = mod_bus;
    }

    public String getPlaca_bus() {
        return placa_bus;
    }

    public void setPlaca_bus(String placa_bus) {
        this.placa_bus = placa_bus;
    }

    public Boolean getEst_bus() {
        return est_bus;
    }

    public void setEst_bus(Boolean est_bus) {
        this.est_bus = est_bus;
    }

    public TrabajadoresModel getTrabajadoresModel() {
        return trabajadoresModel;
    }

    public void setTrabajadoresModel(TrabajadoresModel trabajadoresModel) {
        this.trabajadoresModel = trabajadoresModel;
    }

    public EmpresasModel getEmpresasModel() {
        return empresasModel;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public void setEmpresasModel(EmpresasModel empresasModel) {
        this.empresasModel = empresasModel;
    }

    public RutasModel getRutasModel() {
        return rutasModel;
    }

    public void setRutasModel(RutasModel rutasModel) {
        this.rutasModel = rutasModel;
    }
}
