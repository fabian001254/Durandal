/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.AparteContratacion;
import entidades.Aspirantes;
import entidades.InscripcionesOfertas;
import entidades.Ofertas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author facat
 */
@Stateless
public class InscripcionesOfertasFacade extends AbstractFacade<InscripcionesOfertas> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionesOfertasFacade() {
        super(InscripcionesOfertas.class);
    }
    
    public List<InscripcionesOfertas> consulta(Aspirantes doc){
         List<InscripcionesOfertas> ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT ins FROM InscripcionesOfertas ins WHERE ins.numeroDocumento=:doc");
             consulta.setParameter("doc",doc);
             ofer=(List<InscripcionesOfertas>) consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<InscripcionesOfertas>) ofer;
    }
    public List<InscripcionesOfertas> consulta1(Ofertas apt){
         List<InscripcionesOfertas> ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT ofe FROM InscripcionesOfertas ofe WHERE ofe.idOferta=:id");
             consulta.setParameter("id", apt);
             ofer=(List<InscripcionesOfertas>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<InscripcionesOfertas>) ofer;
    }
     public InscripcionesOfertas consultaA(Aspirantes doc){
        InscripcionesOfertas ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT ins FROM InscripcionesOfertas ins WHERE ins.numeroDocumento=:doc");
             consulta.setParameter("doc",doc);
             ofer= (InscripcionesOfertas) consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (InscripcionesOfertas) ofer;
    }
}
