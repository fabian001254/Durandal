/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Especialidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author facat
 */
@Stateless
public class EspecialidadFacade extends AbstractFacade<Especialidad> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecialidadFacade() {
        super(Especialidad.class);
    }
    public Especialidad Consulta(String nombre){
        Especialidad esp=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT esp FROM Especialidad esp WHERE esp.nombreespecialidad=:nombre");
             consulta.setParameter("nombre",nombre);
             esp=(Especialidad)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return esp;
    }
    }
