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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "tipo_de_red_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDeRedSocial.findAll", query = "SELECT t FROM TipoDeRedSocial t"),
    @NamedQuery(name = "TipoDeRedSocial.findByIdTpRedSocial", query = "SELECT t FROM TipoDeRedSocial t WHERE t.idTpRedSocial = :idTpRedSocial"),
    @NamedQuery(name = "TipoDeRedSocial.findByTpredsocial", query = "SELECT t FROM TipoDeRedSocial t WHERE t.tpredsocial = :tpredsocial")})
public class TipoDeRedSocial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tp_red_social")
    private Integer idTpRedSocial;
    @Size(max = 30)
    @Column(name = "Tp_red_social")
    private String tpredsocial;
    @OneToMany(mappedBy = "idTpRedSocial", fetch = FetchType.LAZY)
    private List<Campaña> campañaList;

    public TipoDeRedSocial() {
    }

    public TipoDeRedSocial(Integer idTpRedSocial) {
        this.idTpRedSocial = idTpRedSocial;
    }

    public Integer getIdTpRedSocial() {
        return idTpRedSocial;
    }

    public void setIdTpRedSocial(Integer idTpRedSocial) {
        this.idTpRedSocial = idTpRedSocial;
    }

    public String getTpredsocial() {
        return tpredsocial;
    }

    public void setTpredsocial(String tpredsocial) {
        this.tpredsocial = tpredsocial;
    }

    @XmlTransient
    public List<Campaña> getCampañaList() {
        return campañaList;
    }

    public void setCampañaList(List<Campaña> campañaList) {
        this.campañaList = campañaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTpRedSocial != null ? idTpRedSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDeRedSocial)) {
            return false;
        }
        TipoDeRedSocial other = (TipoDeRedSocial) object;
        if ((this.idTpRedSocial == null && other.idTpRedSocial != null) || (this.idTpRedSocial != null && !this.idTpRedSocial.equals(other.idTpRedSocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoDeRedSocial[ idTpRedSocial=" + idTpRedSocial + " ]";
    }
    
}
