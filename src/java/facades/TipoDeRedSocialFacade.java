/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entidades.TipoDeRedSocial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author facat
 */
@Stateless
public class TipoDeRedSocialFacade extends AbstractFacade<TipoDeRedSocial> {

    @PersistenceContext(unitName = "DurandalSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDeRedSocialFacade() {
        super(TipoDeRedSocial.class);
    }
    
}
