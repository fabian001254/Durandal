/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "entrevista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrevista.findAll", query = "SELECT e FROM Entrevista e"),
    @NamedQuery(name = "Entrevista.findByDocumento", query = "SELECT e FROM Entrevista e WHERE e.documento = :documento"),
    @NamedQuery(name = "Entrevista.findByFecha", query = "SELECT e FROM Entrevista e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Entrevista.findByHora", query = "SELECT e FROM Entrevista e WHERE e.hora = :hora")})
public class Entrevista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Documento")
    private Long documento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "Documento", referencedColumnName = "Numero_documento", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Aspirantes aspirantes;

    public Entrevista() {
    }

    public Entrevista(Long documento) {
        this.documento = documento;
    }

    public Entrevista(Long documento, Date fecha, Date hora) {
        this.documento = documento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Aspirantes getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documento != null ? documento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrevista)) {
            return false;
        }
        Entrevista other = (Entrevista) object;
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Entrevista[ documento=" + documento + " ]";
    }
    
}
