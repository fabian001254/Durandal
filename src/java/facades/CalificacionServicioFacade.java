/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Aspirantes;
import entidades.CalificacionServicio;
import entidades.Clientes;
import entidades.Promedio;
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
public class CalificacionServicioFacade extends AbstractFacade<CalificacionServicio> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionServicioFacade() {
        super(CalificacionServicio.class);
    }
    public List<Promedio> consulta(){
         List<Promedio> prom= new ArrayList<>();
         List<Object[]> prom1 = new ArrayList<>();
         try {
             Query consulta;
             consulta = em.createNativeQuery("SELECT Numero_documento_aspirante,CAST(AVG(Promedio) AS SIGNED) as Promedio FROM calificacion_servicio GROUP BY Numero_documento_aspirante");
             prom1 = consulta.getResultList();
             for(Object[] elemento : prom1){
                 Promedio promedio = new Promedio();
                 promedio.setDocumento((Long)elemento[0]);
                 promedio.setPromedio(Integer.parseInt(elemento[1].toString()));
                 prom.add(promedio);
             }
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Promedio>) prom;
    }
    public List<CalificacionServicio> consultaA(Aspirantes asp){
         List<CalificacionServicio> asp1=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM CalificacionServicio asp WHERE asp.numerodocumentoaspirante=:doc");
             consulta.setParameter("doc", asp);
             asp1=(List<CalificacionServicio>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<CalificacionServicio>) asp1;
    }
    public List<CalificacionServicio> consultaB(Clientes cl){
         List<CalificacionServicio> asp1=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM CalificacionServicio asp WHERE asp.numerodocumentocliente=:doc and asp.estado=:estado");
             consulta.setParameter("doc", cl);
             consulta.setParameter("estado", "Calificado");
             asp1=(List<CalificacionServicio>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<CalificacionServicio>) asp1;
    }
   public List<CalificacionServicio> consultac(Clientes cl){
         List<CalificacionServicio> asp1=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM CalificacionServicio asp WHERE asp.numerodocumentocliente=:doc and asp.estado=:estado");
             consulta.setParameter("doc", cl);
             consulta.setParameter("estado", "Sin contestar");
             asp1=(List<CalificacionServicio>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<CalificacionServicio>) asp1;
    }
    public List<Promedio> grafica(){
         List<Promedio> prom= new ArrayList<>();
         List<Object[]> prom1 = new ArrayList<>();
         try {
             Query consulta;
             consulta = em.createNativeQuery("SELECT Promedio, COUNT(Promedio) FROM calificacion_servicio GROUP by Promedio;");
             prom1 = consulta.getResultList();
             for(Object[] elemento : prom1){
                 Promedio promedio = new Promedio();
                 promedio.setDocumento((Long)elemento[1]);
                 promedio.setPromedio(Integer.parseInt(elemento[0].toString()));
                 prom.add(promedio);
             }
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Promedio>) prom;
    }
}
