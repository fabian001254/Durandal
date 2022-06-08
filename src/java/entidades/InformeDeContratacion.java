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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "informe_de_contratacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformeDeContratacion.findAll", query = "SELECT i FROM InformeDeContratacion i"),
    @NamedQuery(name = "InformeDeContratacion.findByIdInforme", query = "SELECT i FROM InformeDeContratacion i WHERE i.idInforme = :idInforme"),
    @NamedQuery(name = "InformeDeContratacion.findByFechainicio", query = "SELECT i FROM InformeDeContratacion i WHERE i.fechainicio = :fechainicio"),
    @NamedQuery(name = "InformeDeContratacion.findByFechafinal", query = "SELECT i FROM InformeDeContratacion i WHERE i.fechafinal = :fechafinal")})
public class InformeDeContratacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_informe")
    private Integer idInforme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechafinal;
    @JoinColumn(name = "Numero_documento_aspirante", referencedColumnName = "Numero_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Aspirantes numerodocumentoaspirante;
    @JoinColumn(name = "Numero_documento_cliente", referencedColumnName = "Numero_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes numerodocumentocliente;

    public InformeDeContratacion() {
    }

    public InformeDeContratacion(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public InformeDeContratacion(Integer idInforme, Date fechainicio, Date fechafinal) {
        this.idInforme = idInforme;
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
    }

    public Integer getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Aspirantes getNumerodocumentoaspirante() {
        return numerodocumentoaspirante;
    }

    public void setNumerodocumentoaspirante(Aspirantes numerodocumentoaspirante) {
        this.numerodocumentoaspirante = numerodocumentoaspirante;
    }

    public Clientes getNumerodocumentocliente() {
        return numerodocumentocliente;
    }

    public void setNumerodocumentocliente(Clientes numerodocumentocliente) {
        this.numerodocumentocliente = numerodocumentocliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInforme != null ? idInforme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformeDeContratacion)) {
            return false;
        }
        InformeDeContratacion other = (InformeDeContratacion) object;
        if ((this.idInforme == null && other.idInforme != null) || (this.idInforme != null && !this.idInforme.equals(other.idInforme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InformeDeContratacion[ idInforme=" + idInforme + " ]";
    }
    
}
