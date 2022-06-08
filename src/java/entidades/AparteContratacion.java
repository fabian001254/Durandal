/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "aparte_contratacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AparteContratacion.findAll", query = "SELECT a FROM AparteContratacion a"),
    @NamedQuery(name = "AparteContratacion.findByIdApartes", query = "SELECT a FROM AparteContratacion a WHERE a.idApartes = :idApartes"),
    @NamedQuery(name = "AparteContratacion.findByCantpersonal", query = "SELECT a FROM AparteContratacion a WHERE a.cantpersonal = :cantpersonal"),
    @NamedQuery(name = "AparteContratacion.findByPl", query = "SELECT a FROM AparteContratacion a WHERE a.pl = :pl"),
    @NamedQuery(name = "AparteContratacion.findByDescripcion", query = "SELECT a FROM AparteContratacion a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AparteContratacion.findByTiempoexperiencia", query = "SELECT a FROM AparteContratacion a WHERE a.tiempoexperiencia = :tiempoexperiencia"),
    @NamedQuery(name = "AparteContratacion.findBySueldo", query = "SELECT a FROM AparteContratacion a WHERE a.sueldo = :sueldo"),
    @NamedQuery(name = "AparteContratacion.findByRequrimientos", query = "SELECT a FROM AparteContratacion a WHERE a.requrimientos = :requrimientos"),
    @NamedQuery(name = "AparteContratacion.findByDiafinal", query = "SELECT a FROM AparteContratacion a WHERE a.diafinal = :diafinal"),
    @NamedQuery(name = "AparteContratacion.findByDiainicial", query = "SELECT a FROM AparteContratacion a WHERE a.diainicial = :diainicial"),
    @NamedQuery(name = "AparteContratacion.findByEstado", query = "SELECT a FROM AparteContratacion a WHERE a.estado = :estado")})
public class AparteContratacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_apartes")
    private Integer idApartes;
    @Column(name = "Cant_personal")
    private Integer cantpersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PL")
    private String pl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tiempo_experiencia")
    private String tiempoexperiencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sueldo")
    private int sueldo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Requrimientos")
    private String requrimientos;
    @Column(name = "Dia_final")
    @Temporal(TemporalType.DATE)
    private Date diafinal;
    @Column(name = "Dia_inicial")
    @Temporal(TemporalType.DATE)
    private Date diainicial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Estado")
    private String estado;
    @OneToMany(mappedBy = "idApartes", fetch = FetchType.LAZY)
    private List<Ofertas> ofertasList;
    @JoinColumn(name = "Numero_documento_cliente", referencedColumnName = "Numero_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes numerodocumentocliente;
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDeContrato idTipoContrato;

    public AparteContratacion() {
    }

    public AparteContratacion(Integer idApartes) {
        this.idApartes = idApartes;
    }

    public AparteContratacion(Integer idApartes, String pl, String descripcion, String tiempoexperiencia, int sueldo, String requrimientos, String estado) {
        this.idApartes = idApartes;
        this.pl = pl;
        this.descripcion = descripcion;
        this.tiempoexperiencia = tiempoexperiencia;
        this.sueldo = sueldo;
        this.requrimientos = requrimientos;
        this.estado = estado;
    }

    public Integer getIdApartes() {
        return idApartes;
    }

    public void setIdApartes(Integer idApartes) {
        this.idApartes = idApartes;
    }

    public Integer getCantpersonal() {
        return cantpersonal;
    }

    public void setCantpersonal(Integer cantpersonal) {
        this.cantpersonal = cantpersonal;
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

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getRequrimientos() {
        return requrimientos;
    }

    public void setRequrimientos(String requrimientos) {
        this.requrimientos = requrimientos;
    }

    public Date getDiafinal() {
        return diafinal;
    }

    public void setDiafinal(Date diafinal) {
        this.diafinal = diafinal;
    }

    public Date getDiainicial() {
        return diainicial;
    }

    public void setDiainicial(Date diainicial) {
        this.diainicial = diainicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Ofertas> getOfertasList() {
        return ofertasList;
    }

    public void setOfertasList(List<Ofertas> ofertasList) {
        this.ofertasList = ofertasList;
    }

    public Clientes getNumerodocumentocliente() {
        return numerodocumentocliente;
    }

    public void setNumerodocumentocliente(Clientes numerodocumentocliente) {
        this.numerodocumentocliente = numerodocumentocliente;
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
        hash += (idApartes != null ? idApartes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AparteContratacion)) {
            return false;
        }
        AparteContratacion other = (AparteContratacion) object;
        if ((this.idApartes == null && other.idApartes != null) || (this.idApartes != null && !this.idApartes.equals(other.idApartes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AparteContratacion[ idApartes=" + idApartes + " ]";
    }
    
}
