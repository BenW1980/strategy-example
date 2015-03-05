package shopobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import payment.CreditCard;
import payment.PayPal;
import payment.PaymentInterface;
import payment.PaymentType;
import regionaltax.AntwerpTax;
import regionaltax.BrusselsTax;
import regionaltax.Region;
import regionaltax.TaxInterface;

public final class Customer {

	private String name;
	private static AtomicLong id = new AtomicLong(); // Thread-safe counter to create customer-id's
	private List<Product> products;
	private Region region;
	private PaymentInterface paymentInterface;
	private TaxInterface taxInterface;
	private boolean withTaxes;

	public Customer(String name, Region region) {

		id.incrementAndGet();
		this.name = name;
		this.region = region;
		this.products = new ArrayList<>();
		taxInterface = useTaxInterface(region);
	}

	public TaxInterface useTaxInterface(Region region) {

		switch (region) {

		case ANTWERP:
			taxInterface = new AntwerpTax();
			break;
		case BRUSSELS:
			taxInterface = new BrusselsTax();
			break;
		}

		return taxInterface;

	}

	public PaymentInterface usePaymentInterface(PaymentType paymentType) {

		switch (paymentType) {

		case CREDITCARD:
			paymentInterface = new CreditCard();
			break;
		case PAYPAL:
			paymentInterface = new PayPal();
			break;
		}

		return paymentInterface;

	}

	// Determine whether the total price includes taxes

	public void showTotalPrice(boolean hasTaxes) {

		BigDecimal total = BigDecimal.ZERO;

		if (!hasTaxes) {

			for (Product p : products) {
				total = total.add(p.getBasePrice());
			}

		} else {

			for (Product p : products) {
				total = total.add(taxInterface.calculateTax(p.getBasePrice()));
			}
		}

		System.out.println("\nTotal: " + total);

	}

	public void showProductsBeforeTaxes() {

		withTaxes = false;

		System.out.println("Before taxes: ");
		for (Product p : products) {
			System.out.println(p.getProductName() + ": " + p.getBasePrice());
		}

		showTotalPrice(withTaxes);

	}

	public void showProductsAfterTaxes() {

		withTaxes = true;

		System.out.println("\nAfter taxes: ");
		for (Product p : products) {
			System.out.println(p.getProductName() + ": " + taxInterface.calculateTax(p.getBasePrice()));
		}

		showTotalPrice(withTaxes);

	}

	public String showCustomerInfo() {

		return "Name: " + getName() + "  - Region: " + getRegion() + " - ID: " + getId();
	}

	public void pay() {
		paymentInterface.payment();
	}

	public void checkOut() {

		showProductsBeforeTaxes();
		showProductsAfterTaxes();
		pay();
		System.out.println("\n----------------------------------\n");

	}

	// Adds or removes a product from a shopping list + the amount to add or remove

	public void addProduct(Product product, int amount) {

		for (int i = 0; i < amount; i++) {
			products.add(product);
		}
	}

	public void removeProduct(Product product, int amount) {

		for (int i = 0; i < amount; i++) {
			products.remove(product);
		}
	}

	public AtomicLong getId() {
		return id;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setPaymentInterface(PaymentInterface paymentInterface) {
		this.paymentInterface = paymentInterface;
	}

	public void setTaxInterface(TaxInterface tax) {
		this.taxInterface = tax;
	}

	public String getName() {
		return name;
	}

}
