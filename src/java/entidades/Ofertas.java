/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "ofertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ofertas.findAll", query = "SELECT o FROM Ofertas o"),
    @NamedQuery(name = "Ofertas.findByIdOfertas", query = "SELECT o FROM Ofertas o WHERE o.idOfertas = :idOfertas"),
    @NamedQuery(name = "Ofertas.findByFechainicio", query = "SELECT o FROM Ofertas o WHERE o.fechainicio = :fechainicio"),
    @NamedQuery(name = "Ofertas.findByFechafinal", query = "SELECT o FROM Ofertas o WHERE o.fechafinal = :fechafinal"),
    @NamedQuery(name = "Ofertas.findByPl", query = "SELECT o FROM Ofertas o WHERE o.pl = :pl"),
    @NamedQuery(name = "Ofertas.findByDescripcion", query = "SELECT o FROM Ofertas o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Ofertas.findByTiempoexperiencia", query = "SELECT o FROM Ofertas o WHERE o.tiempoexperiencia = :tiempoexperiencia"),
    @NamedQuery(name = "Ofertas.findBySueldo", query = "SELECT o FROM Ofertas o WHERE o.sueldo = :sueldo"),
    @NamedQuery(name = "Ofertas.findByRequerimientos", query = "SELECT o FROM Ofertas o WHERE o.requerimientos = :requerimientos"),
    @NamedQuery(name = "Ofertas.findByFechadepublicacion", query = "SELECT o FROM Ofertas o WHERE o.fechadepublicacion = :fechadepublicacion"),
    @NamedQuery(name = "Ofertas.findByCantvacantes", query = "SELECT o FROM Ofertas o WHERE o.cantvacantes = :cantvacantes"),
    @NamedQuery(name = "Ofertas.findByEstado", query = "SELECT o FROM Ofertas o WHERE o.estado = :estado")})
public class Ofertas implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta", fetch = FetchType.LAZY)
    private List<InscripcionesOfertas> inscripcionesOfertasList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ofertas")
    private Integer idOfertas;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PL")
    private String pl;
    @Size(max = 100)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "Tiempo_experiencia")
    private String tiempoexperiencia;
    @Column(name = "Sueldo")
    private Integer sueldo;
    @Size(max = 100)
    @Column(name = "Requerimientos")
    private String requerimientos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_de_publicacion")
    @Temporal(TemporalType.DATE)
    private Date fechadepublicacion;
    @Column(name = "Cant_vacantes")
    private Integer cantvacantes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Estado")
    private String estado;
    @JoinColumn(name = "id_apartes", referencedColumnName = "id_apartes")
    @ManyToOne(fetch = FetchType.LAZY)
    private AparteContratacion idApartes;
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDeContrato idTipoContrato;

    public Ofertas() {
    }

    public Ofertas(Integer idOfertas) {
        this.idOfertas = idOfertas;
    }

    public Ofertas(Integer idOfertas, Date fechainicio, Date fechafinal, String pl, Date fechadepublicacion, String estado) {
        this.idOfertas = idOfertas;
        this.fechainicio = fechainicio;
        this.fechafinal = fechafinal;
        this.pl = pl;
        this.fechadepublicacion = fechadepublicacion;
        this.estado = estado;
    }

    public Integer getIdOfertas() {
        return idOfertas;
    }

    public void setIdOfertas(Integer idOfertas) {
        this.idOfertas = idOfertas;
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

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempoexperiencia() {
        return tiempoexperiencia;
    }

    public void setTiempoexperiencia(String tiempoexperiencia) {
        this.tiempoexperiencia = tiempoexperiencia;
    }

    public Integer getSueldo() {
        return sueldo;
    }

    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public Date getFechadepublicacion() {
        return fechadepublicacion;
    }

    public void setFechadepublicacion(Date fechadepublicacion) {
        this.fechadepublicacion = fechadepublicacion;
    }

    public Integer getCantvacantes() {
        return cantvacantes;
    }

    public void setCantvacantes(Integer cantvacantes) {
        this.cantvacantes = cantvacantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public AparteContratacion getIdApartes() {
        return idApartes;
    }

    public void setIdApartes(AparteContratacion idApartes) {
        this.idApartes = idApartes;
    }

    public TipoDeContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoDeContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOfertas != null ? idOfertas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ofertas)) {
            return false;
        }
        Ofertas other = (Ofertas) object;
        if ((this.idOfertas == null && other.idOfertas != null) || (this.idOfertas != null && !this.idOfertas.equals(other.idOfertas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ofertas[ idOfertas=" + idOfertas + " ]";
    }

    @XmlTransient
    public List<InscripcionesOfertas> getInscripcionesOfertasList() {
        return inscripcionesOfertasList;
    }

    public void setInscripcionesOfertasList(List<InscripcionesOfertas> inscripcionesOfertasList) {
        this.inscripcionesOfertasList = inscripcionesOfertasList;
    }
    
}
