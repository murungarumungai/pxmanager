package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String name;

	private BigDecimal price;

	//bi-directional many-to-one association to OrderProduct
	@OneToMany(mappedBy="product")
	private List<OrderProduct> orderProducts;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<OrderProduct> getOrderProducts() {
		return this.orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public OrderProduct addOrderProduct(OrderProduct orderProduct) {
		getOrderProducts().add(orderProduct);
		orderProduct.setProduct(this);

		return orderProduct;
	}

	public OrderProduct removeOrderProduct(OrderProduct orderProduct) {
		getOrderProducts().remove(orderProduct);
		orderProduct.setProduct(null);

		return orderProduct;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}