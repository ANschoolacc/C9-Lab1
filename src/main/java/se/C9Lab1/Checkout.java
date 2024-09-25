package se.C9Lab1;


import se.C9Lab1.decorators.*;
import se.C9Lab1.components.*;
import se.C9Lab1.entities.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Checkout {
  public static void main(String[] args) {
    ShoppingCart cart = new ShoppingCart(
        "ALBIN",
        LocalDate.of(2024, 9, 20));

    cart.addProduct(new Product("mjölk", 100, 5));
    cart.addProduct(new Product("smör", 60, 1));
    cart.addProduct(new Product("ost", 70, 1));

    printDiscounts(cart, generalDiscounts(), "General discounts");
    System.out.println("----------------------------");
    printDiscounts(cart, classDiscounts(), "Class discounts");
  }

  static private Discount generalDiscounts() {
    IsApplicable fridayCondition = (Product product, ShoppingCart shoppingCart) -> shoppingCart.getDateOfPurchase().getDayOfWeek() == DayOfWeek.FRIDAY;
    CalculateDiscount fridayCalc = (Product product, ShoppingCart shoppingCart) -> product.price() * 0.1;

    IsApplicable milkCondition = (Product product, ShoppingCart shoppingCart) -> product.name().equalsIgnoreCase("MJÖLK");
    CalculateDiscount milkCalc = (Product product, ShoppingCart shoppingCart) -> product.price() * 0.05;

    IsApplicable quantityCondition = (Product product, ShoppingCart shoppingCart) -> product.quantity() >= 5;
    CalculateDiscount quantityCalc = (Product product, ShoppingCart shoppingCart) -> product.quantity() * 10;

    List<String> knownCustomers = List.of("ALBIN", "ERIK", "SIMON");
    IsApplicable knownCustomerCondition = (Product product, ShoppingCart shoppingCart) -> knownCustomers.contains(shoppingCart.getCustomerName());
    CalculateDiscount knownCustomerCalc = (Product product, ShoppingCart shoppingCart) -> product.price() * 0.05;


    return
        new GeneralDiscount("Fredagsrabatt 10%.", fridayCondition, fridayCalc,
            new GeneralDiscount("5% rabatt på mjölk.", milkCondition, milkCalc,
                new GeneralDiscount("10kr avdrag per produkt när man handlar minst 5 produkter.", quantityCondition, quantityCalc,
                    new GeneralDiscount("Känd kund 5% rabatt.", knownCustomerCondition, knownCustomerCalc, new NoDiscount()))));
  }

  static public Discount classDiscounts() {
    return new MilkDiscount(new FridayDiscount(new QuantityDiscount(new NoDiscount())));
  }

  static private void printDiscounts(ShoppingCart cart, Discount discounts, String discountsName) {
    double total = cart.getTotal();
    double totalDiscount = 0;
    System.out.println(discountsName);
    System.out.println("----------------------------");

    for (int i = 0; i < cart.getProducts().size(); i++) {
      Product product = cart.getProducts().get(i);
      double productDiscount = discounts.apply(product, cart);
      System.out.println(product.name().toUpperCase());
      System.out.println(discounts.getDescription(product, cart));
      System.out.println("Produktrabatt: " + discounts.apply(product, cart) + "kr");
      System.out.println("----------------------------");
      totalDiscount += productDiscount;
    }
    System.out.println("Att betala: " + (total - totalDiscount));
  }
}


