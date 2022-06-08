/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Clientes;
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
public class ClientesFacade extends AbstractFacade<Clientes> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientesFacade() {
        super(Clientes.class);
    }
    public List<Clientes> consulta(){
         List<Clientes> cl=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT cl FROM Clientes cl");
             cl=(List<Clientes>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Clientes>) cl;
    }
    public Clientes comprobar(int nit){
        Clientes cl=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT cl FROM Clientes cl WHERE cl.nit=:nit");
             consulta.setParameter("nit", nit);
             cl=(Clientes)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return cl;
    }
}
