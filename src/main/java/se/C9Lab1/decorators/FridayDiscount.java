package se.C9Lab1.decorators;

import se.C9Lab1.components.Discount;
import se.C9Lab1.Product;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
//Concrete Decorator
public class FridayDiscount extends BaseDiscount {
  public static final double TEN_PERCENT = 0.1;

  public FridayDiscount(Discount nextDiscount) {super(nextDiscount);}

  @Override
  public String getDescription(Product product) {
   if (isApplicable(product)) {
     return nextDiscount.getDescription(product) + " Fredagsrabatt 10%.";
   }
   return nextDiscount.getDescription(product);
  }

  @Override
  protected boolean isApplicable(Product product) {
    LocalDate today = LocalDate.now();
    return today.getDayOfWeek() == DayOfWeek.FRIDAY;
  }

  @Override
  protected double calculateDiscount(Product product) {
    return TEN_PERCENT * product.price();
  }
}
