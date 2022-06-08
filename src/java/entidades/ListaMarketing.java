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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "lista_marketing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaMarketing.findAll", query = "SELECT l FROM ListaMarketing l"),
    @NamedQuery(name = "ListaMarketing.findByIdDeLista", query = "SELECT l FROM ListaMarketing l WHERE l.idDeLista = :idDeLista"),
    @NamedQuery(name = "ListaMarketing.findByTitulo", query = "SELECT l FROM ListaMarketing l WHERE l.titulo = :titulo")})
public class ListaMarketing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_de_lista")
    private Integer idDeLista;
    @Size(max = 100)
    @Column(name = "Titulo")
    private String titulo;
    @JoinColumn(name = "id_de_campa\u00f1a", referencedColumnName = "id_de_campa\u00f1a")
    @ManyToOne(fetch = FetchType.LAZY)
    private Campaña idDeCampaña;
    @OneToMany(mappedBy = "idDeLista", fetch = FetchType.LAZY)
    private List<ClientesPotenciales> clientesPotencialesList;

    public ListaMarketing() {
    }

    public ListaMarketing(Integer idDeLista) {
        this.idDeLista = idDeLista;
    }

    public Integer getIdDeLista() {
        return idDeLista;
    }

    public void setIdDeLista(Integer idDeLista) {
        this.idDeLista = idDeLista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Campaña getIdDeCampaña() {
        return idDeCampaña;
    }

    public void setIdDeCampaña(Campaña idDeCampaña) {
        this.idDeCampaña = idDeCampaña;
    }

    @XmlTransient
    public List<ClientesPotenciales> getClientesPotencialesList() {
        return clientesPotencialesList;
    }

    public void setClientesPotencialesList(List<ClientesPotenciales> clientesPotencialesList) {
        this.clientesPotencialesList = clientesPotencialesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeLista != null ? idDeLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaMarketing)) {
            return false;
        }
        ListaMarketing other = (ListaMarketing) object;
        if ((this.idDeLista == null && other.idDeLista != null) || (this.idDeLista != null && !this.idDeLista.equals(other.idDeLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ListaMarketing[ idDeLista=" + idDeLista + " ]";
    }
    
}
