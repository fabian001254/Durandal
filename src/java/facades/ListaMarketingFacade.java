/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.Campaña;
import entidades.ListaMarketing;
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
public class ListaMarketingFacade extends AbstractFacade<ListaMarketing> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaMarketingFacade() {
        super(ListaMarketing.class);
    }
    public ListaMarketing Comprobar(Campaña camp){
        ListaMarketing usu=null;
         try {
             Query consulta;
             consulta = em.createQuery("SELECT usu FROM ListaMarketing usu WHERE usu.idDeCampaña=:id");
             consulta.setParameter("id", camp);             
             usu=(ListaMarketing)consulta.getResultList().get(0);
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return usu;
    }
    public List<ListaMarketing> consultacant(){
         List<ListaMarketing> li= new ArrayList<>();
         List<Object[]> prom1 = new ArrayList<>();
         try {
             Query consulta;
             consulta = em.createNativeQuery("SELECT lista_marketing.Titulo,COUNT(clientes_potenciales.id_de_lista) FROM clientes_potenciales INNER JOIN lista_marketing WHERE clientes_potenciales.id_de_lista = lista_marketing.id_de_lista GROUP BY clientes_potenciales.id_de_lista;");
             prom1 = consulta.getResultList();
             for(Object[] elemento : prom1){
                 ListaMarketing promedio = new ListaMarketing();
                 promedio.setTitulo(elemento[0].toString());
                 promedio.setIdDeLista(Integer.parseInt(elemento[1].toString()));
                 li.add(promedio);
             }
             
         } catch (Exception e) {
             System.out.println("Error: "+e.getMessage());
         }
            return (List<ListaMarketing>) li;
    }
}
