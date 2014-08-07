/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Category;
import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    /**
     * The @PersistenceContext annotation is used to inject a container-managed
     * EntityManager into the class. In other words, we rely on GlassFish' EJB
     * container to open and close EntityManagers as and when needed. The
     * unitName element specifies the AffableBeanPU persistence unit, which has
     * been defined in the application's persistence.xml file.
     */
    @PersistenceContext(unitName = "AffableBeanPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
       // manually created
    public List<Product> findForCategory(Category category) {
        return em.createQuery("SELECT p FROM Product p WHERE p.category = :category").
               setParameter("category", category).getResultList();
    }

}
