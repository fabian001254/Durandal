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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "calificacion_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalificacionServicio.findAll", query = "SELECT c FROM CalificacionServicio c"),
    @NamedQuery(name = "CalificacionServicio.findByIdCuestionario", query = "SELECT c FROM CalificacionServicio c WHERE c.idCuestionario = :idCuestionario"),
    @NamedQuery(name = "CalificacionServicio.findByPuntualidad", query = "SELECT c FROM CalificacionServicio c WHERE c.puntualidad = :puntualidad"),
    @NamedQuery(name = "CalificacionServicio.findByCalitrabajo", query = "SELECT c FROM CalificacionServicio c WHERE c.calitrabajo = :calitrabajo"),
    @NamedQuery(name = "CalificacionServicio.findByActiipersonal", query = "SELECT c FROM CalificacionServicio c WHERE c.actiipersonal = :actiipersonal"),
    @NamedQuery(name = "CalificacionServicio.findByResponsabilidad", query = "SELECT c FROM CalificacionServicio c WHERE c.responsabilidad = :responsabilidad"),
    @NamedQuery(name = "CalificacionServicio.findByPromedio", query = "SELECT c FROM CalificacionServicio c WHERE c.promedio = :promedio"),
    @NamedQuery(name = "CalificacionServicio.findByFechainicio", query = "SELECT c FROM CalificacionServicio c WHERE c.fechainicio = :fechainicio"),
    @NamedQuery(name = "CalificacionServicio.findByFechafinal", query = "SELECT c FROM CalificacionServicio c WHERE c.fechafinal = :fechafinal"),
    @NamedQuery(name = "CalificacionServicio.findByEstado", query = "SELECT c FROM CalificacionServicio c WHERE c.estado = :estado")})
public class CalificacionServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuestionario")
    private Integer idCuestionario;
    @Column(name = "Puntualidad")
    private Integer puntualidad;
    @Column(name = "Cali_trabajo")
    private Integer calitrabajo;
    @Column(name = "Actii_personal")
    private Integer actiipersonal;
    @Column(name = "Responsabilidad")
    private Integer responsabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Promedio")
    private int promedio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "Fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechafinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "Numero_documento_aspirante", referencedColumnName = "Numero_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Aspirantes numerodocumentoaspirante;
    @JoinColumn(name = "Numero_documento_cliente", referencedColumnName = "Numero_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes numerodocumentocliente;

    public CalificacionServicio() {
    }

    public CalificacionServicio(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public CalificacionServicio(Integer idCuestionario, int promedio, Date fechainicio, String estado) {
        this.idCuestionario = idCuestionario;
        this.promedio = promedio;
        this.fechainicio = fechainicio;
        this.estado = estado;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Integer getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(Integer puntualidad) {
        this.puntualidad = puntualidad;
    }

    public Integer getCalitrabajo() {
        return calitrabajo;
    }

    public void setCalitrabajo(Integer calitrabajo) {
        this.calitrabajo = calitrabajo;
    }

    public Integer getActiipersonal() {
        return actiipersonal;
    }

    public void setActiipersonal(Integer actiipersonal) {
        this.actiipersonal = actiipersonal;
    }

    public Integer getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(Integer responsabilidad) {
        this.responsabilidad = responsabilidad;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        hash += (idCuestionario != null ? idCuestionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionServicio)) {
            return false;
        }
        CalificacionServicio other = (CalificacionServicio) object;
        if ((this.idCuestionario == null && other.idCuestionario != null) || (this.idCuestionario != null && !this.idCuestionario.equals(other.idCuestionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CalificacionServicio[ idCuestionario=" + idCuestionario + " ]";
    }
    
}
