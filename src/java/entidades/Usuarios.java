/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByNumerodocumento", query = "SELECT u FROM Usuarios u WHERE u.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByDireccion", query = "SELECT u FROM Usuarios u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuarios.findByFechanacimiento", query = "SELECT u FROM Usuarios u WHERE u.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuarios.findByNombre1", query = "SELECT u FROM Usuarios u WHERE u.nombre1 = :nombre1"),
    @NamedQuery(name = "Usuarios.findByNombre2", query = "SELECT u FROM Usuarios u WHERE u.nombre2 = :nombre2"),
    @NamedQuery(name = "Usuarios.findByApellido1", query = "SELECT u FROM Usuarios u WHERE u.apellido1 = :apellido1"),
    @NamedQuery(name = "Usuarios.findByApellido2", query = "SELECT u FROM Usuarios u WHERE u.apellido2 = :apellido2"),
    @NamedQuery(name = "Usuarios.findByClave", query = "SELECT u FROM Usuarios u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuarios.findByRol", query = "SELECT u FROM Usuarios u WHERE u.rol = :rol"),
    @NamedQuery(name = "Usuarios.findByFoto", query = "SELECT u FROM Usuarios u WHERE u.foto = :foto")})
public class Usuarios implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre_1")
    private String nombre1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Nombre_2")
    private String nombre2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Apellido_1")
    private String apellido1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Apellido_2")
    private String apellido2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "Clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "rol")
    private String rol;
    @Size(max = 100)
    @Column(name = "Foto")
    private String foto;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarios", fetch = FetchType.LAZY)
    private Aspirantes aspirantes;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero_documento")
    private Long numerodocumento;
    @Column(name = "Telefono")
    private BigInteger telefono;
    @Column(name = "Fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @JoinColumn(name = "id_de_documento", referencedColumnName = "id_de_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Documentos idDeDocumento;

    public Usuarios() {
    }

    public Usuarios(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Usuarios(Long numerodocumento, String direccion, String correo, String nombre1, String nombre2, String apellido1, String apellido2, String clave, String rol) {
        this.numerodocumento = numerodocumento;
        this.direccion = direccion;
        this.correo = correo;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.clave = clave;
        this.rol = rol;
    }

    public Long getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }


    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.numerodocumento == null && other.numerodocumento != null) || (this.numerodocumento != null && !this.numerodocumento.equals(other.numerodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuarios[ numerodocumento=" + numerodocumento + " ]";
    }
    public Aspirantes getAspirantes() {
        return aspirantes;
    }
    public void setAspirantes(Aspirantes aspirantes) {
        this.aspirantes = aspirantes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
}
