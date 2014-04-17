package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order_product database table.
 * 
 */
@Embeddable
public class OrderProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="customer_order_id", insertable=false, updatable=false)
	private int customerOrderId;

	@Column(name="product_id", insertable=false, updatable=false)
	private int productId;

	public OrderProductPK() {
	}
	public int getCustomerOrderId() {
		return this.customerOrderId;
	}
	public void setCustomerOrderId(int customerOrderId) {
		this.customerOrderId = customerOrderId;
	}
	public int getProductId() {
		return this.productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderProductPK)) {
			return false;
		}
		OrderProductPK castOther = (OrderProductPK)other;
		return 
			(this.customerOrderId == castOther.customerOrderId)
			&& (this.productId == castOther.productId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.customerOrderId;
		hash = hash * prime + this.productId;
		
		return hash;
	}
}