/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.AparteContratacion;
import entidades.Clientes;
import java.util.ArrayList;
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
public class AparteContratacionFacade extends AbstractFacade<AparteContratacion> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AparteContratacionFacade() {
        super(AparteContratacion.class);
    }
    public List<AparteContratacion> consulta(String estado){
         List<AparteContratacion> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT apt FROM AparteContratacion apt WHERE apt.estado=:estado");
             consulta.setParameter("estado",estado);
             asp=(List<AparteContratacion>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<AparteContratacion>) asp;
    }
      public List<AparteContratacion> consultaA(Clientes cl){
         List<AparteContratacion> ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT apt FROM AparteContratacion apt WHERE apt.numerodocumentocliente=:cl");
             consulta.setParameter("cl", cl);
             ofer=(List<AparteContratacion>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<AparteContratacion>) ofer;
    }
       public List<AparteContratacion> consulta1(Clientes cliente){
         List<AparteContratacion> ofer=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT ofe FROM AparteContratacion ofe WHERE ofe.numerodocumentocliente=:doc");
             consulta.setParameter("doc",cliente);
             ofer=(List<AparteContratacion>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<AparteContratacion>) ofer;
    }
       public List<AparteContratacion> consultaap(){
         List<AparteContratacion> prom= new ArrayList<>();
         List<Object[]> prom1 = new ArrayList<>();
         try {
             Query consulta;
             consulta = em.createNativeQuery("SELECT estado, COUNT(Estado) FROM aparte_contratacion GROUP BY Estado;");
             prom1 = consulta.getResultList();
             for(Object[] elemento : prom1){
                 AparteContratacion aparte = new AparteContratacion();
                 aparte.setEstado(elemento[0].toString());
                 aparte.setCantpersonal(Integer.parseInt(elemento[1].toString()));
                 prom.add(aparte);
             }
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<AparteContratacion>) prom;
    }
}
