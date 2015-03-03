package regionaltax;

import java.math.BigDecimal;

public abstract class Tax implements TaxInterface {

	private BigDecimal percentageToAdd;

	@Override
	public BigDecimal calculateTax(BigDecimal basePrice) {

		BigDecimal total = basePrice.add(basePrice.multiply(percentageToAdd));

		return total.setScale(1, BigDecimal.ROUND_UP);
	}

	public void setPercentageToAdd(BigDecimal percentageToAdd) {
		this.percentageToAdd = percentageToAdd;
	}

}
