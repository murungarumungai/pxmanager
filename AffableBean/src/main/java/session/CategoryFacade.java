/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {
    @PersistenceContext(unitName = "AffableBeanPU")
    private EntityManager em;
    
	 
	/* This works with WAS Liberty Profile and OPEN JPA */ 
//    @PersistenceUnit(unitName = "AffableBeanPU")
//    static EntityManagerFactory emf;
    
    

    protected EntityManager getEntityManager() {
    	
    	//return emf.createEntityManager();  	
        return em;	 
    }

    public CategoryFacade() {
        super(Category.class);
    }

}