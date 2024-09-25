package se.C9Lab1.decorators;

import se.C9Lab1.entities.ShoppingCart;
import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.Product;

import java.time.DayOfWeek;

//Concrete Decorator
public class FridayDiscount extends BaseDiscount {
  private static final double TEN_PERCENT = 0.1;
  private static final String DESCRIPTION = "Fredagsrabatt 10%.";

  public FridayDiscount(Discount nextDiscount) {super(nextDiscount);}

  @Override
  protected String getOwnDescription() {
    return DESCRIPTION;
  }

  @Override
  protected boolean isApplicable(Product product, ShoppingCart shoppingCart) {
    return shoppingCart.getDateOfPurchase().getDayOfWeek() == DayOfWeek.FRIDAY;
  }

  @Override
  protected double calculateDiscount(Product product, ShoppingCart shoppingCart) {
    return TEN_PERCENT * product.price();
  }
}
