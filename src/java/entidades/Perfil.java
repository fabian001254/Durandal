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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author facat
 */
@Entity
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findByIdPerfil", query = "SELECT p FROM Perfil p WHERE p.idPerfil = :idPerfil"),
    @NamedQuery(name = "Perfil.findByPl", query = "SELECT p FROM Perfil p WHERE p.pl = :pl"),
    @NamedQuery(name = "Perfil.findByFichatecnica", query = "SELECT p FROM Perfil p WHERE p.fichatecnica = :fichatecnica")})
public class Perfil implements Serializable {

    @Size(max = 50)
    @Column(name = "PL")
    private String pl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Ficha_tecnica")
    private String fichatecnica;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil")
    private Integer idPerfil;
    @OneToMany(mappedBy = "idPerfil", fetch = FetchType.LAZY)
    private List<Aspirantes> aspirantesList;
    @OneToMany(mappedBy = "idPerfil", fetch = FetchType.LAZY)
    private List<PruebasPsicotecnicas> pruebasPsicotecnicasList;

    public Perfil() {
    }

    public Perfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Perfil(Integer idPerfil, String fichatecnica) {
        this.idPerfil = idPerfil;
        this.fichatecnica = fichatecnica;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }


    public String getFichatecnica() {
        return fichatecnica;
    }

    public void setFichatecnica(String fichatecnica) {
        this.fichatecnica = fichatecnica;
    }

    @XmlTransient
    public List<Aspirantes> getAspirantesList() {
        return aspirantesList;
    }

    public void setAspirantesList(List<Aspirantes> aspirantesList) {
        this.aspirantesList = aspirantesList;
    }

    @XmlTransient
    public List<PruebasPsicotecnicas> getPruebasPsicotecnicasList() {
        return pruebasPsicotecnicasList;
    }

    public void setPruebasPsicotecnicasList(List<PruebasPsicotecnicas> pruebasPsicotecnicasList) {
        this.pruebasPsicotecnicasList = pruebasPsicotecnicasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfil != null ? idPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idPerfil == null && other.idPerfil != null) || (this.idPerfil != null && !this.idPerfil.equals(other.idPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Perfil[ idPerfil=" + idPerfil + " ]";
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    
}
