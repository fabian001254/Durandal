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
@Table(name = "tipo_de_postulacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDePostulacion.findAll", query = "SELECT t FROM TipoDePostulacion t"),
    @NamedQuery(name = "TipoDePostulacion.findByIdTipoPostulacion", query = "SELECT t FROM TipoDePostulacion t WHERE t.idTipoPostulacion = :idTipoPostulacion"),
    @NamedQuery(name = "TipoDePostulacion.findByTipopostulacion", query = "SELECT t FROM TipoDePostulacion t WHERE t.tipopostulacion = :tipopostulacion")})
public class TipoDePostulacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_postulacion")
    private Integer idTipoPostulacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Tipo_postulacion")
    private String tipopostulacion;

    public TipoDePostulacion() {
    }

    public TipoDePostulacion(Integer idTipoPostulacion) {
        this.idTipoPostulacion = idTipoPostulacion;
    }

    public TipoDePostulacion(Integer idTipoPostulacion, String tipopostulacion) {
        this.idTipoPostulacion = idTipoPostulacion;
        this.tipopostulacion = tipopostulacion;
    }

    public Integer getIdTipoPostulacion() {
        return idTipoPostulacion;
    }

    public void setIdTipoPostulacion(Integer idTipoPostulacion) {
        this.idTipoPostulacion = idTipoPostulacion;
    }

    public String getTipopostulacion() {
        return tipopostulacion;
    }

    public void setTipopostulacion(String tipopostulacion) {
        this.tipopostulacion = tipopostulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPostulacion != null ? idTipoPostulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDePostulacion)) {
            return false;
        }
        TipoDePostulacion other = (TipoDePostulacion) object;
        if ((this.idTipoPostulacion == null && other.idTipoPostulacion != null) || (this.idTipoPostulacion != null && !this.idTipoPostulacion.equals(other.idTipoPostulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoDePostulacion[ idTipoPostulacion=" + idTipoPostulacion + " ]";
    }
    
}
