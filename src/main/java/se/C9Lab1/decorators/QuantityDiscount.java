package se.C9Lab1.decorators;

import se.C9Lab1.entities.ShoppingCart;
import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
//Concrete Decorator
public class QuantityDiscount extends BaseDiscount {
  public static final int FIVE = 5;
  private static final String DESCRIPTION = "10kr avdrag per produkt när man handlar minst 5 produkter.";

  public QuantityDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  @Override
  protected String getOwnDescription() {
      return  DESCRIPTION;
  }

  @Override
  protected boolean isApplicable(Product product) {return product.quantity() >= FIVE;}

  @Override
  protected double calculateDiscount(Product product) {
    return 10 * product.quantity();
  }
}
