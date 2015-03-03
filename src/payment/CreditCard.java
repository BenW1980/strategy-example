package payment;

public class CreditCard implements PaymentInterface{

	@Override
	public void payment() {
		System.out.println("Payment method: credit card");
		
	}

}
