/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.ClientesPotenciales;
import entidades.ListaMarketing;
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
public class ClientesPotencialesFacade extends AbstractFacade<ClientesPotenciales> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesPotencialesFacade() {
        super(ClientesPotenciales.class);
    }
    public List<ClientesPotenciales> consulta(ListaMarketing li){
         List<ClientesPotenciales> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM ClientesPotenciales asp WHERE asp.idDeLista=:id");
             consulta.setParameter("id", li);
             asp=(List<ClientesPotenciales>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<ClientesPotenciales>) asp;
    }
     public List<String> consultaC(){
         List<String> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp.correo FROM ClientesPotenciales asp");
             asp=(List<String>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<String>) asp;
    }
}
