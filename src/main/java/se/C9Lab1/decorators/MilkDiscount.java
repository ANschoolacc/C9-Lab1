package se.C9Lab1.decorators;

import se.C9Lab1.entities.ShoppingCart;
import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
//Concrete Decorator
public class MilkDiscount extends BaseDiscount {
  public static final double FIVE_PERCENT = 0.05;
  public static final String MILK = "MJÖLK";
  public static final String DESCRIPTION = "5% rabatt på mjölk.";

  public MilkDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  protected String getOwnDescription() {
      return DESCRIPTION;
  }

  @Override
  protected boolean isApplicable(Product product, ShoppingCart shoppingCart) {
    return product.name().equalsIgnoreCase(MILK);
  }

  @Override
  protected double calculateDiscount(Product product, ShoppingCart shoppingCart) {
  return FIVE_PERCENT * product.price();
  }
}
