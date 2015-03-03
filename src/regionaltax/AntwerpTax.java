package regionaltax;

import java.math.BigDecimal;

public final class AntwerpTax extends Tax {

	public AntwerpTax() {
		setPercentageToAdd(new BigDecimal(0.02));

	}

}
