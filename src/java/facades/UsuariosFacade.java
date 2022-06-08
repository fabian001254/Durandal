/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Usuarios;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author facat
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
   
    
    public Usuarios consulta(String correo, String contraseña){
         Usuarios usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM Usuarios usu WHERE usu.correo=:correo and usu.clave=:contraseña");
             consulta.setParameter("correo", correo);
             consulta.setParameter("contraseña", contraseña);
             usu=(Usuarios)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    public Usuarios Comprobar(String correo,long cedula){
        Usuarios usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM Usuarios usu WHERE usu.correo=:correo or usu.numerodocumento=:cedula");
             consulta.setParameter("correo", correo);
             consulta.setParameter("cedula", cedula);
             usu=(Usuarios)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    
    public List<String> listarAspirantes(){
         List<String> usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu.correo FROM Usuarios usu WHERE usu.rol='As'");
             usu=(List<String>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<String>)usu;
    }
    public List<String> listarUsuarios(){
         List<String> usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu.correo FROM Usuarios usu");
             usu=(List<String>)consulta.getResultList();
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<String>)usu;
    }
    public Usuarios recuperar(String correo,long cedula,Date fecha,String nombre1,String apellido2){
        Usuarios usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM Usuarios usu WHERE usu.correo=:correo and usu.numerodocumento=:cedula and usu.fechanacimiento=:fecha and usu.apellido2=:apellido2 and usu.nombre1=:nombre1");
             consulta.setParameter("correo", correo);
             consulta.setParameter("cedula", cedula);
             consulta.setParameter("fecha", fecha);
             consulta.setParameter("nombre1", nombre1);
             consulta.setParameter("apellido2", apellido2);

             usu=(Usuarios)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    
    
}
