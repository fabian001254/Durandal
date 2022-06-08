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
@Table(name = "campa\u00f1a")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campa\u00f1a.findAll", query = "SELECT c FROM Campa\u00f1a c"),
    @NamedQuery(name = "Campa\u00f1a.findByIdDeCampa\u00f1a", query = "SELECT c FROM Campa\u00f1a c WHERE c.idDeCampa\u00f1a = :idDeCampa\u00f1a"),
    @NamedQuery(name = "Campa\u00f1a.findByFechainicial", query = "SELECT c FROM Campa\u00f1a c WHERE c.fechainicial = :fechainicial"),
    @NamedQuery(name = "Campa\u00f1a.findByFechafinal", query = "SELECT c FROM Campa\u00f1a c WHERE c.fechafinal = :fechafinal"),
    @NamedQuery(name = "Campa\u00f1a.findByNombrecampa\u00f1a", query = "SELECT c FROM Campa\u00f1a c WHERE c.nombrecampa\u00f1a = :nombrecampa\u00f1a")})
public class Campaña implements Serializable {

    @OneToMany(mappedBy = "idDeCampa\u00f1a", fetch = FetchType.LAZY)
    private List<ListaMarketing> listaMarketingList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_de_campa\u00f1a")
    private Integer idDeCampaña;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechainicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechafinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre_campa\u00f1a")
    private String nombrecampaña;
    @JoinColumn(name = "id_tp_red_social", referencedColumnName = "id_tp_red_social")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDeRedSocial idTpRedSocial;

    public Campaña() {
    }

    public Campaña(Integer idDeCampaña) {
        this.idDeCampaña = idDeCampaña;
    }

    public Campaña(Integer idDeCampaña, Date fechainicial, Date fechafinal, String nombrecampaña) {
        this.idDeCampaña = idDeCampaña;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.nombrecampaña = nombrecampaña;
    }

    public Integer getIdDeCampaña() {
        return idDeCampaña;
    }

    public void setIdDeCampaña(Integer idDeCampaña) {
        this.idDeCampaña = idDeCampaña;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public String getNombrecampaña() {
        return nombrecampaña;
    }

    public void setNombrecampaña(String nombrecampaña) {
        this.nombrecampaña = nombrecampaña;
    }

    public TipoDeRedSocial getIdTpRedSocial() {
        return idTpRedSocial;
    }

    public void setIdTpRedSocial(TipoDeRedSocial idTpRedSocial) {
        this.idTpRedSocial = idTpRedSocial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeCampaña != null ? idDeCampaña.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campaña)) {
            return false;
        }
        Campaña other = (Campaña) object;
        if ((this.idDeCampaña == null && other.idDeCampaña != null) || (this.idDeCampaña != null && !this.idDeCampaña.equals(other.idDeCampaña))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Campa\u00f1a[ idDeCampa\u00f1a=" + idDeCampaña + " ]";
    }

    @XmlTransient
    public List<ListaMarketing> getListaMarketingList() {
        return listaMarketingList;
    }

    public void setListaMarketingList(List<ListaMarketing> listaMarketingList) {
        this.listaMarketingList = listaMarketingList;
    }
    
}
