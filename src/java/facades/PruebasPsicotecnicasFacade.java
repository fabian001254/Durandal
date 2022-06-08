/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Aspirantes;
import entidades.PruebasPsicotecnicas;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author facat
 */
@Stateless
public class PruebasPsicotecnicasFacade extends AbstractFacade<PruebasPsicotecnicas> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PruebasPsicotecnicasFacade() {
        super(PruebasPsicotecnicas.class);
    }
    
    public PruebasPsicotecnicas Comprobar(Aspirantes doc){
        PruebasPsicotecnicas usu = null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM PruebasPsicotecnicas usu WHERE usu.numerodocumento=:doc");
             consulta.setParameter("doc", doc) ;          
             usu=(PruebasPsicotecnicas)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    
    
    

}
