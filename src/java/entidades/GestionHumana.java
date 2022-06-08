/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "gestion_humana")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GestionHumana.findAll", query = "SELECT g FROM GestionHumana g"),
    @NamedQuery(name = "GestionHumana.findByNumerodocumento", query = "SELECT g FROM GestionHumana g WHERE g.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "GestionHumana.findByCargo", query = "SELECT g FROM GestionHumana g WHERE g.cargo = :cargo")})
public class GestionHumana implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero_documento")
    private Long numerodocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Cargo")
    private String cargo;
    @JoinColumn(name = "Numero_documento", referencedColumnName = "Numero_documento", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuarios;
    @JoinColumn(name = "id_de_documento", referencedColumnName = "id_de_documento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Documentos idDeDocumento;

    public GestionHumana() {
    }

    public GestionHumana(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public GestionHumana(Long numerodocumento, String cargo) {
        this.numerodocumento = numerodocumento;
        this.cargo = cargo;
    }

    public Long getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
        if (!(object instanceof GestionHumana)) {
            return false;
        }
        GestionHumana other = (GestionHumana) object;
        if ((this.numerodocumento == null && other.numerodocumento != null) || (this.numerodocumento != null && !this.numerodocumento.equals(other.numerodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.GestionHumana[ numerodocumento=" + numerodocumento + " ]";
    }
    
}
