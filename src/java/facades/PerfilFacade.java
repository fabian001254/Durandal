/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Perfil;
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
public class PerfilFacade extends AbstractFacade<Perfil> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilFacade() {
        super(Perfil.class);
    }
    
    public Perfil consulta(String nombre){
         Perfil usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT per FROM Perfil per WHERE per.pl=:perfil");
             consulta.setParameter("perfil",nombre);
             usu=(Perfil)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    
    public List<Perfil> consulta1(){
        List<Object[]> perfil = new ArrayList();
         List<Perfil> pl= new ArrayList();
         try {
             Query consulta;
             consulta = em.createNativeQuery("SELECT perfil.PL, COUNT(aspirantes.id_perfil) FROM aspirantes INNER JOIN perfil WHERE perfil.id_perfil = aspirantes.id_perfil GROUP by perfil.PL;");
             perfil = consulta.getResultList();
             for(Object[] elemento: perfil){
                 Perfil perfilito = new Perfil();
                 perfilito.setPl(elemento[0].toString());
                 perfilito.setIdPerfil(Integer.parseInt(elemento[1].toString()));
                 pl.add(perfilito);
             }
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
        return pl;
    }
}
