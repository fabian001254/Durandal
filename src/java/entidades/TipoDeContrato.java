/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "tipo_de_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeContrato.findAll", query = "SELECT t FROM TipoDeContrato t"),
    @NamedQuery(name = "TipoDeContrato.findByIdTipoContrato", query = "SELECT t FROM TipoDeContrato t WHERE t.idTipoContrato = :idTipoContrato"),
    @NamedQuery(name = "TipoDeContrato.findByTipocontrato", query = "SELECT t FROM TipoDeContrato t WHERE t.tipocontrato = :tipocontrato")})
public class TipoDeContrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_contrato")
    private Integer idTipoContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tipo_contrato")
    private String tipocontrato;
    @OneToMany(mappedBy = "idTipoContrato", fetch = FetchType.LAZY)
    private List<Ofertas> ofertasList;
    @OneToMany(mappedBy = "idTipoContrato", fetch = FetchType.LAZY)
    private List<AparteContratacion> aparteContratacionList;

    public TipoDeContrato() {
    }

    public TipoDeContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public TipoDeContrato(Integer idTipoContrato, String tipocontrato) {
        this.idTipoContrato = idTipoContrato;
        this.tipocontrato = tipocontrato;
    }

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    @XmlTransient
    public List<Ofertas> getOfertasList() {
        return ofertasList;
    }

    public void setOfertasList(List<Ofertas> ofertasList) {
        this.ofertasList = ofertasList;
    }

    @XmlTransient
    public List<AparteContratacion> getAparteContratacionList() {
        return aparteContratacionList;
    }

    public void setAparteContratacionList(List<AparteContratacion> aparteContratacionList) {
        this.aparteContratacionList = aparteContratacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoContrato != null ? idTipoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDeContrato)) {
            return false;
        }
        TipoDeContrato other = (TipoDeContrato) object;
        if ((this.idTipoContrato == null && other.idTipoContrato != null) || (this.idTipoContrato != null && !this.idTipoContrato.equals(other.idTipoContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoDeContrato[ idTipoContrato=" + idTipoContrato + " ]";
    }
    
}
