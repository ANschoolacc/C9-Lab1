package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

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
  protected boolean isApplicable(ShoppingCart shoppingCart) {
    return shoppingCart
        .getProducts()
        .stream()
        .anyMatch(product -> product.quantity() >= FIVE);}

  @Override
  protected double calculateDiscount(ShoppingCart shoppingCart) {
    double discount = 0;
    for (Product product : shoppingCart.getProducts()) {
      if (product.quantity() >= FIVE) {
        discount += (product.quantity() * 10);
      }
    }
    return discount;
  }
}
