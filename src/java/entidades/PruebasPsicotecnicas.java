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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "pruebas_psicotecnicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PruebasPsicotecnicas.findAll", query = "SELECT p FROM PruebasPsicotecnicas p"),
    @NamedQuery(name = "PruebasPsicotecnicas.findByIdPrueba", query = "SELECT p FROM PruebasPsicotecnicas p WHERE p.idPrueba = :idPrueba"),
    @NamedQuery(name = "PruebasPsicotecnicas.findByHoraenvio", query = "SELECT p FROM PruebasPsicotecnicas p WHERE p.horaenvio = :horaenvio"),
    @NamedQuery(name = "PruebasPsicotecnicas.findByTiempolimite", query = "SELECT p FROM PruebasPsicotecnicas p WHERE p.tiempolimite = :tiempolimite"),
    @NamedQuery(name = "PruebasPsicotecnicas.findByPathPrueba", query = "SELECT p FROM PruebasPsicotecnicas p WHERE p.pathPrueba = :pathPrueba"),
    @NamedQuery(name = "PruebasPsicotecnicas.findByPathPruebaEntregada", query = "SELECT p FROM PruebasPsicotecnicas p WHERE p.pathPruebaEntregada = :pathPruebaEntregada")})
public class PruebasPsicotecnicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prueba")
    private Integer idPrueba;
    @Column(name = "Hora_envio")
    @Temporal(TemporalType.TIME)
    private Date horaenvio;
    @Column(name = "Tiempo_limite")
    @Temporal(TemporalType.DATE)
    private Date tiempolimite;
    @Size(max = 100)
    @Column(name = "pathPrueba")
    private String pathPrueba;
    @Size(max = 100)
    @Column(name = "pathPruebaEntregada")
    private String pathPruebaEntregada;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(fetch = FetchType.LAZY)
    private Perfil idPerfil;
    @JoinColumn(name = "Numero_documento", referencedColumnName = "Numero_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Aspirantes numerodocumento;

    public PruebasPsicotecnicas() {
    }

    public PruebasPsicotecnicas(Integer idPrueba) {
        this.idPrueba = idPrueba;
    }

    public Integer getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Integer idPrueba) {
        this.idPrueba = idPrueba;
    }

    public Date getHoraenvio() {
        return horaenvio;
    }

    public void setHoraenvio(Date horaenvio) {
        this.horaenvio = horaenvio;
    }

    public Date getTiempolimite() {
        return tiempolimite;
    }

    public void setTiempolimite(Date tiempolimite) {
        this.tiempolimite = tiempolimite;
    }

    public String getPathPrueba() {
        return pathPrueba;
    }

    public void setPathPrueba(String pathPrueba) {
        this.pathPrueba = pathPrueba;
    }

    public String getPathPruebaEntregada() {
        return pathPruebaEntregada;
    }

    public void setPathPruebaEntregada(String pathPruebaEntregada) {
        this.pathPruebaEntregada = pathPruebaEntregada;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Aspirantes getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Aspirantes numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrueba != null ? idPrueba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebasPsicotecnicas)) {
            return false;
        }
        PruebasPsicotecnicas other = (PruebasPsicotecnicas) object;
        if ((this.idPrueba == null && other.idPrueba != null) || (this.idPrueba != null && !this.idPrueba.equals(other.idPrueba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PruebasPsicotecnicas[ idPrueba=" + idPrueba + " ]";
    }
    
}
