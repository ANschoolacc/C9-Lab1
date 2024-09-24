package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.Product;
//Concrete Decorator
public class MilkDiscount extends BaseDiscount {
  public static final double FIVE_PERCENT = 0.05;
  public static final String MILK = "MJÖLK";

  public MilkDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  @Override
  public String getDescription(Product product) {
    if (isApplicable(product)) {
      return nextDiscount.getDescription(product) + " 5% rabatt på mjölk.";
    }
    return nextDiscount.getDescription(product);
  }

  @Override
  protected boolean isApplicable(Product product) {
    return product.name().equalsIgnoreCase(MILK);
  }

  @Override
  protected double calculateDiscount(Product product) {
  return FIVE_PERCENT * product.price();
  }
}
