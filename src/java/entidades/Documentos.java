/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentos.findAll", query = "SELECT d FROM Documentos d"),
    @NamedQuery(name = "Documentos.findByIdDeDocumento", query = "SELECT d FROM Documentos d WHERE d.idDeDocumento = :idDeDocumento"),
    @NamedQuery(name = "Documentos.findByTipodocumento", query = "SELECT d FROM Documentos d WHERE d.tipodocumento = :tipodocumento")})
public class Documentos implements Serializable {

    @Size(max = 30)
    @Column(name = "Tipo_documento")
    private String tipodocumento;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_de_documento")
    private Integer idDeDocumento;
    @OneToMany(mappedBy = "idDeDocumento", fetch = FetchType.LAZY)
    private List<GestionComercial> gestionComercialList;
    @OneToMany(mappedBy = "idDeDocumento", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeDocumento", fetch = FetchType.LAZY)
    private List<ClientesPotenciales> clientesPotencialesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeDocumento", fetch = FetchType.LAZY)
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idDeDocumento", fetch = FetchType.LAZY)
    private List<Clientes> clientesList;
    @OneToMany(mappedBy = "idDeDocumento", fetch = FetchType.LAZY)
    private List<GestionHumana> gestionHumanaList;

    public Documentos() {
    }

    public Documentos(Integer idDeDocumento) {
        this.idDeDocumento = idDeDocumento;
    }

    public Integer getIdDeDocumento() {
        return idDeDocumento;
    }

    public void setIdDeDocumento(Integer idDeDocumento) {
        this.idDeDocumento = idDeDocumento;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    @XmlTransient
    public List<GestionComercial> getGestionComercialList() {
        return gestionComercialList;
    }

    public void setGestionComercialList(List<GestionComercial> gestionComercialList) {
        this.gestionComercialList = gestionComercialList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<ClientesPotenciales> getClientesPotencialesList() {
        return clientesPotencialesList;
    }

    public void setClientesPotencialesList(List<ClientesPotenciales> clientesPotencialesList) {
        this.clientesPotencialesList = clientesPotencialesList;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @XmlTransient
    public List<GestionHumana> getGestionHumanaList() {
        return gestionHumanaList;
    }

    public void setGestionHumanaList(List<GestionHumana> gestionHumanaList) {
        this.gestionHumanaList = gestionHumanaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeDocumento != null ? idDeDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentos)) {
            return false;
        }
        Documentos other = (Documentos) object;
        if ((this.idDeDocumento == null && other.idDeDocumento != null) || (this.idDeDocumento != null && !this.idDeDocumento.equals(other.idDeDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Documentos[ idDeDocumento=" + idDeDocumento + " ]";
    }





    
}
