/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fabian
 */
@Entity
@Table(name = "aspirantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aspirantes.findAll", query = "SELECT a FROM Aspirantes a"),
    @NamedQuery(name = "Aspirantes.findByNumerodocumento", query = "SELECT a FROM Aspirantes a WHERE a.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Aspirantes.findByDiashabiles", query = "SELECT a FROM Aspirantes a WHERE a.diashabiles = :diashabiles"),
    @NamedQuery(name = "Aspirantes.findByHorainicial", query = "SELECT a FROM Aspirantes a WHERE a.horainicial = :horainicial"),
    @NamedQuery(name = "Aspirantes.findByHorafinal", query = "SELECT a FROM Aspirantes a WHERE a.horafinal = :horafinal"),
    @NamedQuery(name = "Aspirantes.findByEstado", query = "SELECT a FROM Aspirantes a WHERE a.estado = :estado"),
    @NamedQuery(name = "Aspirantes.findByHojavida", query = "SELECT a FROM Aspirantes a WHERE a.hojavida = :hojavida")})
public class Aspirantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Numero_documento")
    private Long numerodocumento;
    @Size(max = 1000)
    @Column(name = "Dias_habiles")
    private String diashabiles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_inicial")
    @Temporal(TemporalType.TIME)
    private Date horainicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_final")
    @Temporal(TemporalType.TIME)
    private Date horafinal;
    @Size(max = 30)
    @Column(name = "Estado")
    private String estado;
    @Size(max = 100)
    @Column(name = "Hoja_vida")
    private String hojavida;
    @JoinColumn(name = "Numero_documento", referencedColumnName = "Numero_documento", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuarios;
    @JoinColumn(name = "id_de_documento", referencedColumnName = "id_de_documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Documentos idDeDocumento;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(fetch = FetchType.LAZY)
    private Perfil idPerfil;
    @JoinColumn(name = "id_especializacion", referencedColumnName = "id_especialidad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad idEspecializacion;

    public Aspirantes() {
    }

    public Aspirantes(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Aspirantes(Long numerodocumento, Date horainicial, Date horafinal) {
        this.numerodocumento = numerodocumento;
        this.horainicial = horainicial;
        this.horafinal = horafinal;
    }

    public Long getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(Long numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getDiashabiles() {
        return diashabiles;
    }

    public void setDiashabiles(String diashabiles) {
        this.diashabiles = diashabiles;
    }

    public Date getHorainicial() {
        return horainicial;
    }

    public void setHorainicial(Date horainicial) {
        this.horainicial = horainicial;
    }

    public Date getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(Date horafinal) {
        this.horafinal = horafinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHojavida() {
        return hojavida;
    }

    public void setHojavida(String hojavida) {
        this.hojavida = hojavida;
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

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Especialidad getIdEspecializacion() {
        return idEspecializacion;
    }

    public void setIdEspecializacion(Especialidad idEspecializacion) {
        this.idEspecializacion = idEspecializacion;
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
        if (!(object instanceof Aspirantes)) {
            return false;
        }
        Aspirantes other = (Aspirantes) object;
        if ((this.numerodocumento == null && other.numerodocumento != null) || (this.numerodocumento != null && !this.numerodocumento.equals(other.numerodocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Aspirantes[ numerodocumento=" + numerodocumento + " ]";
    }
    
}
