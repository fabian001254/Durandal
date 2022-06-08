/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Aspirantes;
import entidades.Clientes;
import entidades.InformeDeContratacion;
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
public class InformeDeContratacionFacade extends AbstractFacade<InformeDeContratacion> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformeDeContratacionFacade() {
        super(InformeDeContratacion.class);
    }
    
    public InformeDeContratacion consulta(InformeDeContratacion info){
         InformeDeContratacion usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM InformeDeContratacion asp WHERE asp.numerodocumentoaspirante=:doc1 and asp.numerodocumentocliente=:doc2 and asp.fechainicio=:fecha1 and asp.fechafinal=:fecha2");
             consulta.setParameter("doc1", info.getNumerodocumentoaspirante());
             consulta.setParameter("doc2", info.getNumerodocumentocliente());
             consulta.setParameter("fecha1", info.getFechainicio());
             consulta.setParameter("fecha2", info.getFechafinal());
             usu=(InformeDeContratacion)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    
    public List<InformeDeContratacion> consultaInformesA(Aspirantes as){   
         List<InformeDeContratacion> usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM InformeDeContratacion asp WHERE asp.numerodocumentoaspirante=:doc");
             consulta.setParameter("doc",as);
             usu=(List<InformeDeContratacion>)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<InformeDeContratacion>) usu;
    }
    public List<InformeDeContratacion> consultaInformesB(Clientes cl){   
         List<InformeDeContratacion> usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM InformeDeContratacion asp WHERE asp.numerodocumentocliente=:doc");
             consulta.setParameter("doc",cl);
             usu=(List<InformeDeContratacion>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<InformeDeContratacion>) usu;
    }
}

