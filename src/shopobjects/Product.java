package shopobjects;

import java.math.BigDecimal;

public final class Product {

	private BigDecimal basePrice;
	private String productName;

	public Product(String productName, BigDecimal basePrice) {

		this.productName = productName;
		this.basePrice = basePrice;

	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getProductName() {
		return productName;
	}

}
