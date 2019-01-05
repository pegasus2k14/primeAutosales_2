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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m_ang
 */
@Entity
@Table(name = "prime_visitor_count")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrimeVisitorCount.findAll", query = "SELECT p FROM PrimeVisitorCount p"),
    @NamedQuery(name = "PrimeVisitorCount.findById", query = "SELECT p FROM PrimeVisitorCount p WHERE p.id = :id"),
    @NamedQuery(name = "PrimeVisitorCount.findByCount", query = "SELECT p FROM PrimeVisitorCount p WHERE p.count = :count")})
public class PrimeVisitorCount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "count")
    private Integer count;

    public PrimeVisitorCount() {
    }

    public PrimeVisitorCount(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        if (!(object instanceof PrimeVisitorCount)) {
            return false;
        }
        PrimeVisitorCount other = (PrimeVisitorCount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primefacesautosale2.entity.PrimeVisitorCount[ id=" + id + " ]";
    }
    
}
