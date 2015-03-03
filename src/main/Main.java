package main;

import java.math.BigDecimal;

import payment.PaymentType;
import regionaltax.Region;
import shopobjects.Customer;
import shopobjects.Product;

public class Main {

	public static void main(String[] args) {

		Product book = new Product("Book", new BigDecimal(10.50));
		Product dvd = new Product("DVD", new BigDecimal(20));
		Product game = new Product("Game", new BigDecimal(30));

		Customer c1 = new Customer("Ben", Region.ANTWERP);

		c1.addProduct(book, 1);
		c1.addProduct(dvd, 1);
		c1.addProduct(game, 1);

		c1.usePaymentInterface(PaymentType.CREDITCARD);

		System.out.println(c1.showCustomerInfo());
		c1.checkOut();

		Customer c2 = new Customer("Guido", Region.BRUSSELS);

		c2.addProduct(book, 2);
		c2.addProduct(dvd, 1);
		c2.addProduct(game, 1);

		c2.usePaymentInterface(PaymentType.PAYPAL);

		System.out.println(c2.showCustomerInfo());
		c2.checkOut();

	}
}
