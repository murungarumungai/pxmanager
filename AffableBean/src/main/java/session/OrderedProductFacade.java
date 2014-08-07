/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.OrderProduct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tgiunipero
 */
@Stateless
public class OrderedProductFacade extends AbstractFacade<OrderProduct> {
    @PersistenceContext(unitName = "AffableBeanPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderedProductFacade() {
        super(OrderProduct.class);
    }

        // manually created
    public List<OrderProduct> findByOrderId(Object id) {
        return em.createNamedQuery("OrderProduct.findByCustomerOrderId").setParameter("customerOrderId", id).getResultList();
    }
}