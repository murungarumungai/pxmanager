/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Customer;
import entity.CustomerOrder;
import entity.OrderProduct;
import entity.OrderProductPK;
import entity.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author razvan
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {

    /**
     * - all of the EJBs(ProductFacade, etc...) employ the EntityManager. <br/>
     * - the EntityManager API is included in JPA, and is responsible for
     * performing persistence operations on the database. <br/>
     *
     * The @PersistenceContext annotation is used to inject a container-managed
     * EntityManager into the class. In other words, we rely on GlassFish' EJB
     * container to open and close EntityManagers as and when needed. The
     * unitName element specifies the AffableBeanPU persistence unit, which has
     * been defined in the application's persistence.xml file.
     */
    /**
     * In OrderManager, apply the @PersistenceContext annotation to express a
     * dependency on a container-managed EntityManager and the AffableBeanPU
     * persistence context. Also declare an EntityManager instance.
     */
    @PersistenceContext(unitName = "AffableBeanPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private OrderedProductFacade orderedProductFacade;

    /**
     * The @TransactionAttribute annotation placed on the placeOrder method
     * specifies that any operations occurring in the method must be treated as
     * part of a transaction.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(String name, String email, String phone, String address, String cityRegion, String ccNumber, ShoppingCart cart) {

        try {
            Customer customer = addCustomer(name, email, phone, address, cityRegion, ccNumber);
            CustomerOrder order = addOrder(customer, cart);
            addOrderedItems(order, cart);
            return order.getId();
        } catch (Exception e) {
            //explicitly set the transaction for rollback in the catch clause
            context.setRollbackOnly();
            return 0;
        }

    }

    private Customer addCustomer(String name, String email, String phone, String address, String cityRegion, String ccNumber) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCityRegion(cityRegion);
        customer.setCcNumber(ccNumber);

        em.persist(customer);

        return customer;
    }

    private CustomerOrder addOrder(Customer customer, ShoppingCart cart) {

        // set up customer order
        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customer);
        order.setAmount(BigDecimal.valueOf(cart.getTotal()));

        // create confirmation number
        Random random = new Random();
        int i = random.nextInt(999999999);
        order.setConfirmationNumber(i);

        em.persist(order);
        return order;
    }

    private void addOrderedItems(CustomerOrder order, ShoppingCart cart) {
        em.flush();//Synchronize the persistence context to the underlying database.
        List<ShoppingCartItem> items = cart.getItems();

        // iterate through shopping cart and create OrderedProducts
        for (ShoppingCartItem scItem : items) {
            int productId = scItem.getProduct().getId();

            // set up primary key object
            OrderProductPK orderedProductPK = new OrderProductPK();
            orderedProductPK.setCustomerOrderId(order.getId());
            orderedProductPK.setProductId(productId);

            // create ordered item using PK object
            OrderProduct orderedItem = new OrderProduct(orderedProductPK);

            // set quantity
            orderedItem.setQuantity(scItem.getQuantity());

            em.persist(orderedItem);
        }
    }

    public Map getOrderDetails(int orderId) {

        Map orderMap = new HashMap();

        // get order
        CustomerOrder order = customerOrderFacade.find(orderId);

        // get customer
        Customer customer = order.getCustomerId();

        // get all ordered products
        List<OrderProduct> orderedProducts = orderedProductFacade.findByOrderId(orderId);//orderedProductFacade.find(em);

        // get product details for ordered items
        List<Product> products = new ArrayList<Product>();

        for (OrderProduct op : orderedProducts) {

            Product p = (Product) productFacade.find(op.getOrderProductPK().getProductId());
            products.add(p);
        }

        // add each item to orderMap
        orderMap.put("orderRecord", order);
        orderMap.put("customer", customer);
        orderMap.put("orderedProducts", orderedProducts);
        orderMap.put("products", products);

        return orderMap;
    }

}
