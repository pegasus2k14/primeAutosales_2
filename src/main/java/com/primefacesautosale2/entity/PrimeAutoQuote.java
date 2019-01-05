/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefacesautosale2.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m_ang
 */
@Entity
@Table(name = "prime_auto_quote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrimeAutoQuote.findAll", query = "SELECT p FROM PrimeAutoQuote p"),
    @NamedQuery(name = "PrimeAutoQuote.findById", query = "SELECT p FROM PrimeAutoQuote p WHERE p.id = :id"),
    @NamedQuery(name = "PrimeAutoQuote.findByFirstName", query = "SELECT p FROM PrimeAutoQuote p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "PrimeAutoQuote.findByLastName", query = "SELECT p FROM PrimeAutoQuote p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "PrimeAutoQuote.findByPhone", query = "SELECT p FROM PrimeAutoQuote p WHERE p.phone = :phone"),
    @NamedQuery(name = "PrimeAutoQuote.findByEmail", query = "SELECT p FROM PrimeAutoQuote p WHERE p.email = :email"),
    @NamedQuery(name = "PrimeAutoQuote.findByTexto", query = "SELECT p FROM PrimeAutoQuote p WHERE p.texto = :texto"),
    @NamedQuery(name = "PrimeAutoQuote.findByQuoteDate", query = "SELECT p FROM PrimeAutoQuote p WHERE p.quoteDate = :quoteDate"),
    @NamedQuery(name = "PrimeAutoQuote.findByMake", query = "SELECT p FROM PrimeAutoQuote p WHERE p.make = :make"),
    @NamedQuery(name = "PrimeAutoQuote.findByModel", query = "SELECT p FROM PrimeAutoQuote p WHERE p.model = :model"),
    @NamedQuery(name = "PrimeAutoQuote.findByYearmade", query = "SELECT p FROM PrimeAutoQuote p WHERE p.yearmade = :yearmade")})
public class PrimeAutoQuote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 30)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Formato tel./fax. invalido, debe ser xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 1000)
    @Column(name = "texto")
    private String texto;
    @Column(name = "quote_date")
    @Temporal(TemporalType.DATE)
    private Date quoteDate;
    @Size(max = 50)
    @Column(name = "make")
    private String make;
    @Size(max = 50)
    @Column(name = "model")
    private String model;
    @Size(max = 4)
    @Column(name = "yearmade")
    private String yearmade;

    public PrimeAutoQuote() {
    }

    public PrimeAutoQuote(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearmade() {
        return yearmade;
    }

    public void setYearmade(String yearmade) {
        this.yearmade = yearmade;
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
        if (!(object instanceof PrimeAutoQuote)) {
            return false;
        }
        PrimeAutoQuote other = (PrimeAutoQuote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primefacesautosale2.entity.PrimeAutoQuote[ id=" + id + " ]";
    }
    
}
