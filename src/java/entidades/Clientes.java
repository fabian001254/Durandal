/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByNumerodocumento", query = "SELECT c FROM Clientes c WHERE c.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Clientes.findByNit", query = "SELECT c FROM Clientes c WHERE c.nit = :nit"),
    @NamedQuery(name = "Clientes.findByRazonsocial", query = "SELECT c FROM Clientes c WHERE c.razonsocial = :razonsocial"),
    @NamedQuery(name = "Clientes.findByTelefono", query = "SELECT c FROM Clientes c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Clientes.findByObjetosocial", query = "SELECT c FROM Clientes c WHERE c.objetosocial = :objetosocial"),
    @NamedQuery(name = "Clientes.findByCorreo", query = "SELECT c FROM Clientes c WHERE c.correo = :correo")})
public class Clientes implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Telefono")
    private BigInteger telefono;
    @OneToMany(mappedBy = "numerodocumentocliente", fetch = FetchType.LAZY)
    private List<InformeDeContratacion> informeDeContratacionList;
    @OneToMany(mappedBy = "numerodocumentocliente", fetch = FetchType.LAZY)
    private List<CalificacionServicio> calificacionServicioList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero_documento")
    private Long numerodocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIT")
    private int nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Razon_social")
    private String razonsocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Objeto_social")
    private String objetosocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Correo")
    private String correo;
    @JoinColumn(name = "Numero_documento", referencedColumnName = "Numero_documento", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuarios;
    @JoinColumn(name = "id_de_documento", referencedColumnName = "id_de_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Documentos idDeDocumento;

    public Clientes() {
    }

    public Clientes(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Clientes(Long numerodocumento, int nit, String razonsocial, BigInteger telefono, String objetosocial, String correo) {
        this.numerodocumento = numerodocumento;
        this.nit = nit;
        this.razonsocial = razonsocial;
        this.telefono = telefono;
        this.objetosocial = objetosocial;
        this.correo = correo;
    }

    public Long getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }


    public String getObjetosocial() {
        return objetosocial;
    }

    public void setObjetosocial(String objetosocial) {
        this.objetosocial = objetosocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Documentos getIdDeDocumento() {
        return idDeDocumento;
    }

    public void setIdDeDocumento(Documentos idDeDocumento) {
        this.idDeDocumento = idDeDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerodocumento != null ? numerodocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.numerodocumento == null && other.numerodocumento != null) || (this.numerodocumento != null && !this.numerodocumento.equals(other.numerodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Clientes[ numerodocumento=" + numerodocumento + " ]";
    }


    @XmlTransient
    public List<CalificacionServicio> getCalificacionServicioList() {
        return calificacionServicioList;
    }

    public void setCalificacionServicioList(List<CalificacionServicio> calificacionServicioList) {
        this.calificacionServicioList = calificacionServicioList;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<InformeDeContratacion> getInformeDeContratacionList() {
        return informeDeContratacionList;
    }

    public void setInformeDeContratacionList(List<InformeDeContratacion> informeDeContratacionList) {
        this.informeDeContratacionList = informeDeContratacionList;
    }
    
}
