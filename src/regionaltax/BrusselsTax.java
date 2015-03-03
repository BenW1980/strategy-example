package regionaltax;

import java.math.BigDecimal;

public final class BrusselsTax extends Tax {

	public BrusselsTax() {
		setPercentageToAdd(new BigDecimal(0.03));
	}

}
