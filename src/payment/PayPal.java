package payment;

public class PayPal implements PaymentInterface {

	@Override
	public void payment() {
		System.out.println("Payment method: paypal");

	}

}
