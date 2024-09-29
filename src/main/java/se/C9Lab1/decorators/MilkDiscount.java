package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

public class MilkDiscount extends BaseDiscount {
  public static final double PERCENT = 0.05;
  public static final String PRODUCT_NAME = "MJÖLK";
  public static final String DESCRIPTION = "5% rabatt på mjölk.";

  public MilkDiscount(Discount nextDiscount) {
    super(nextDiscount);
  }

  protected String getOwnDescription() {
      return DESCRIPTION;
  }

  @Override
  protected boolean isApplicable(ShoppingCart shoppingCart) {
    return shoppingCart
        .getProducts()
        .stream()
        .anyMatch(product -> PRODUCT_NAME.equalsIgnoreCase(product.name()));
  }

  @Override
  protected double calculateDiscount(ShoppingCart shoppingCart) {
  return shoppingCart
      .getProducts()
      .stream()
      .filter(product -> PRODUCT_NAME.equalsIgnoreCase(product.name()))
      .mapToDouble(product -> product.price() * PERCENT)
      .sum();
  }
}
