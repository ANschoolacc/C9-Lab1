package se.C9Lab1;

import se.C9Lab1.components.Discount;
import se.C9Lab1.components.GeneralDiscount;
import se.C9Lab1.components.NoDiscount;
import se.C9Lab1.decorators.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {
    Product product = new Product("mjölk", 150, 5);
    IsApplicable<LocalDate> fridayCondition = (LocalDate date) -> date.getDayOfWeek() == DayOfWeek.FRIDAY;
    CalculateDiscount fridayCalc = (Product p) -> p.price() * 0.1;
    GeneralDiscount<LocalDate> fridayDiscount = new GeneralDiscount<>("Fredagsrabatt 10%",LocalDate.of(2024, 9, 20) ,fridayCondition, fridayCalc);

    IsApplicable<String> milkCondition = (String name) -> name.equalsIgnoreCase("MJÖLK");
    CalculateDiscount milkCalc = (Product p) -> p.price() * 0.05;
    GeneralDiscount<String> milkDiscount = new GeneralDiscount<>("5% rabatt på mjölk.", product.name(), milkCondition, milkCalc);

    IsApplicable<Integer> quantityCondition = (Integer quantity) -> quantity >= 5;
    CalculateDiscount quantityCalc = (Product p) -> p.quantity() * 10;
    GeneralDiscount<Integer> quantityDiscount = new GeneralDiscount<>("10kr avdrag per produkt när man handlar minst 5 produkter.", product.quantity(), quantityCondition, quantityCalc);

    Discount discount = new MilkDiscount(
                            new FridayDiscount(
                                new QuantityDiscount(
                                    new NoDiscount())));


    System.out.println("Total rabatt: " + discount.apply(product) + "kr");
    System.out.println("Applicerade rabatter: " + discount.getDescription(product));
  }
}
