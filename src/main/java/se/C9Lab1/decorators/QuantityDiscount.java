package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.Product;
//Concrete Decorator
public class QuantityDiscount extends BaseDiscount {
  public static final int FIVE = 5;

  public QuantityDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  @Override
  public String getDescription(Product product) {
    if (isApplicable(product)) {
      return nextDiscount.getDescription(product) + " 10kr avdrag per produkt när man handlar minst 5 produkter.";
    }
    return nextDiscount.getDescription(product);
  }

  @Override
  protected boolean isApplicable(Product product) {return product.quantity() >= FIVE;}

  @Override
  protected double calculateDiscount(Product product) {
    return 10 * product.quantity();
  }
}
