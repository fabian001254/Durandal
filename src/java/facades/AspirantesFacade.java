/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Aspirantes;
import entidades.ConteoEstado;
import java.math.BigInteger;
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
public class AspirantesFacade extends AbstractFacade<Aspirantes> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AspirantesFacade() {
        super(Aspirantes.class);
    }
    public List<Aspirantes> consulta(){
         List<Aspirantes> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM Aspirantes asp WHERE asp.estado='Contratado'");
             asp=(List<Aspirantes>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Aspirantes>) asp;
    }
    public List<Aspirantes> consultaR(){
         List<Aspirantes> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM Aspirantes asp WHERE asp.estado='Reclutado'");
             asp=(List<Aspirantes>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Aspirantes>) asp;
    }
    public List<Aspirantes> consultaAspirantesE(){
         List<Aspirantes> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM Aspirantes asp WHERE asp.estado='Registrado'");
             asp=(List<Aspirantes>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Aspirantes>) asp;
    }
    public List<Aspirantes> consultaEnEspera(){
         List<Aspirantes> asp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT asp FROM Aspirantes asp WHERE asp.estado='En espera'");
             asp=(List<Aspirantes>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<Aspirantes>) asp;
    }
    public List<ConteoEstado> consultaEstados(){
        List<Object[]> estado = new ArrayList<>();
        List<ConteoEstado> estado1 = new ArrayList<>();
        try {
             Query consulta;
             consulta = em.createNativeQuery("SELECT estado,COUNT(Estado) as Total FROM aspirantes GROUP BY(Estado)");
             estado = consulta.getResultList();
             for (Object[] elemento : estado) {
                ConteoEstado conteo2 = new ConteoEstado();
                conteo2.setEstado(elemento[0].toString());
                conteo2.setConteo(Integer.parseInt(elemento[1].toString()));
                estado1.add(conteo2);
            }
        } catch (Exception e) {
        }
        return estado1;
    }
}
