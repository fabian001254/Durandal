/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "clientes_potenciales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientesPotenciales.findAll", query = "SELECT c FROM ClientesPotenciales c"),
    @NamedQuery(name = "ClientesPotenciales.findByNumerodocumento", query = "SELECT c FROM ClientesPotenciales c WHERE c.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "ClientesPotenciales.findByTelefono", query = "SELECT c FROM ClientesPotenciales c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "ClientesPotenciales.findByNombre1", query = "SELECT c FROM ClientesPotenciales c WHERE c.nombre1 = :nombre1"),
    @NamedQuery(name = "ClientesPotenciales.findByNombre2", query = "SELECT c FROM ClientesPotenciales c WHERE c.nombre2 = :nombre2"),
    @NamedQuery(name = "ClientesPotenciales.findByApellido1", query = "SELECT c FROM ClientesPotenciales c WHERE c.apellido1 = :apellido1"),
    @NamedQuery(name = "ClientesPotenciales.findByApellido2", query = "SELECT c FROM ClientesPotenciales c WHERE c.apellido2 = :apellido2"),
    @NamedQuery(name = "ClientesPotenciales.findByCorreo", query = "SELECT c FROM ClientesPotenciales c WHERE c.correo = :correo"),
    @NamedQuery(name = "ClientesPotenciales.findByInteres", query = "SELECT c FROM ClientesPotenciales c WHERE c.interes = :interes"),
    @NamedQuery(name = "ClientesPotenciales.findByCargo", query = "SELECT c FROM ClientesPotenciales c WHERE c.cargo = :cargo")})
public class ClientesPotenciales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero_documento", nullable = false)
    private Long numerodocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Telefono", nullable = false)
    private long telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre_1", nullable = false, length = 30)
    private String nombre1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre_2", nullable = false, length = 30)
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Apellido_1", nullable = false, length = 30)
    private String apellido1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Apellido_2", nullable = false, length = 30)
    private String apellido2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;
    @Size(max = 200)
    @Column(name = "Interes", length = 200)
    private String interes;
    @Size(max = 50)
    @Column(name = "Cargo", length = 50)
    private String cargo;
    @JoinColumn(name = "id_de_lista", referencedColumnName = "id_de_lista")
    @ManyToOne
    private ListaMarketing idDeLista;
    @JoinColumn(name = "id_de_documento", referencedColumnName = "id_de_documento", nullable = false)
    @ManyToOne(optional = false)
    private Documentos idDeDocumento;

    public ClientesPotenciales() {
    }

    public ClientesPotenciales(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public ClientesPotenciales(Long numerodocumento, long telefono, String nombre1, String nombre2, String apellido1, String apellido2, String correo) {
        this.numerodocumento = numerodocumento;
        this.telefono = telefono;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
    }

    public Long getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public ListaMarketing getIdDeLista() {
        return idDeLista;
    }

    public void setIdDeLista(ListaMarketing idDeLista) {
        this.idDeLista = idDeLista;
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
        if (!(object instanceof ClientesPotenciales)) {
            return false;
        }
        ClientesPotenciales other = (ClientesPotenciales) object;
        if ((this.numerodocumento == null && other.numerodocumento != null) || (this.numerodocumento != null && !this.numerodocumento.equals(other.numerodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ClientesPotenciales[ numerodocumento=" + numerodocumento + " ]";
    }
    
}
