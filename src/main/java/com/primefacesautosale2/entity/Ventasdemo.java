/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefacesautosale2.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m_ang
 */
@Entity
@Table(name = "ventasdemo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventasdemo.findAll", query = "SELECT v FROM Ventasdemo v"),
    @NamedQuery(name = "Ventasdemo.findById", query = "SELECT v FROM Ventasdemo v WHERE v.id = :id"),
    @NamedQuery(name = "Ventasdemo.findByTipoVehiculo", query = "SELECT v FROM Ventasdemo v WHERE v.tipoVehiculo = :tipoVehiculo"),
    @NamedQuery(name = "Ventasdemo.findByVentas", query = "SELECT v FROM Ventasdemo v WHERE v.ventas = :ventas"),
    @NamedQuery(name = "Ventasdemo.findByClasificacion", query = "SELECT v FROM Ventasdemo v WHERE v.clasificacion = :clasificacion")})
public class Ventasdemo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipoVehiculo")
    private String tipoVehiculo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Ventas")
    private Float ventas;
    @Column(name = "clasificacion")
    private Character clasificacion;

    public Ventasdemo() {
    }

    public Ventasdemo(Integer id) {
        this.id = id;
    }

    public Ventasdemo(Integer id, String tipoVehiculo) {
        this.id = id;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Float getVentas() {
        return ventas;
    }

    public void setVentas(Float ventas) {
        this.ventas = ventas;
    }

    public Character getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Character clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventasdemo)) {
            return false;
        }
        Ventasdemo other = (Ventasdemo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primefacesautosale2.entity.Ventasdemo[ id=" + id + " ]";
    }
    
}
