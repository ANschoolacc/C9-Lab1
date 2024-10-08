package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.entities.ShoppingCart;

import java.time.DayOfWeek;

public class FridayDiscount extends BaseDiscount {
  private static final double PERCENT = 0.1;
  private static final String DESCRIPTION = "Fredagsrabatt, 10% rabatt på totalbeloppet.";

  public FridayDiscount(Discount nextDiscount) {super(nextDiscount);}

  @Override
  protected String getOwnDescription() {
    return DESCRIPTION;
  }

  @Override
  protected boolean isApplicable(ShoppingCart shoppingCart) {
    return shoppingCart.getDateOfPurchase().getDayOfWeek() == DayOfWeek.FRIDAY;
  }

  @Override
  protected double calculateDiscount(ShoppingCart shoppingCart) {
    return PERCENT * shoppingCart.getTotal();
  }
}
