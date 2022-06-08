/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "inscripciones_ofertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InscripcionesOfertas.findAll", query = "SELECT i FROM InscripcionesOfertas i"),
    @NamedQuery(name = "InscripcionesOfertas.findByIdIncripcion", query = "SELECT i FROM InscripcionesOfertas i WHERE i.idIncripcion = :idIncripcion")})
public class InscripcionesOfertas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_incripcion")
    private Integer idIncripcion;
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_ofertas")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ofertas idOferta;
    @JoinColumn(name = "numero_documento", referencedColumnName = "Numero_documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aspirantes numeroDocumento;

    public InscripcionesOfertas() {
    }

    public InscripcionesOfertas(Integer idIncripcion) {
        this.idIncripcion = idIncripcion;
    }

    public Integer getIdIncripcion() {
        return idIncripcion;
    }

    public void setIdIncripcion(Integer idIncripcion) {
        this.idIncripcion = idIncripcion;
    }

    public Ofertas getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Ofertas idOferta) {
        this.idOferta = idOferta;
    }

    public Aspirantes getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Aspirantes numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncripcion != null ? idIncripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionesOfertas)) {
            return false;
        }
        InscripcionesOfertas other = (InscripcionesOfertas) object;
        if ((this.idIncripcion == null && other.idIncripcion != null) || (this.idIncripcion != null && !this.idIncripcion.equals(other.idIncripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InscripcionesOfertas[ idIncripcion=" + idIncripcion + " ]";
    }
    
}
