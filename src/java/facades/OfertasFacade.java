/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.AparteContratacion;
import entidades.Clientes;
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
public class OfertasFacade extends AbstractFacade<Ofertas> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfertasFacade() {
        super(Ofertas.class);
    }
    
     public List<Ofertas> consulta(String perfil){
         List<Ofertas> ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT ofe FROM Ofertas ofe WHERE ofe.pl=:perfil");
             consulta.setParameter("perfil", perfil);
             ofer=(List<Ofertas>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Ofertas>) ofer;
    }
    public Ofertas consulta1(AparteContratacion ap){
         Ofertas ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT ofe FROM Ofertas ofe WHERE ofe.idApartes=:doc");
             consulta.setParameter("doc",ap);
             ofer=(Ofertas) consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (Ofertas) ofer;
    }
}
