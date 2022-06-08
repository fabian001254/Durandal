/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Campaña;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author facat
 */
@Stateless
public class CampañaFacade extends AbstractFacade<Campaña> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampañaFacade() {
        super(Campaña.class);
    }
    public Campaña Comprobar(String nombre,int id){
        Campaña usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM Campaña usu WHERE usu.nombrecampaña=:nombre and usu.idDeCampaña!=:id");
             consulta.setParameter("nombre", nombre);             
             consulta.setParameter("id", id);
             usu=(Campaña)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    public Campaña Comprobar1(String nombre){
        Campaña usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM Campaña usu WHERE usu.nombrecampaña=:nombre");
             consulta.setParameter("nombre", nombre);             
             usu=(Campaña)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
}
