package se.C9Lab1;

import se.C9Lab1.decorators.*;
import se.C9Lab1.components.*;
import se.C9Lab1.entities.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Checkout {
  public static void main(String[] args) {
    ShoppingCart cart = new ShoppingCart(
        "ALBIN",
        LocalDate.of(2024, 9, 19));

    cart.addProduct(new Product("Mjöl", 100, 1));
    cart.addProduct(new Product("smör", 60, 1));
    cart.addProduct(new Product("ost", 70, 1));

//    printDiscounts(cart, generalDiscounts(), "General discounts");
//    System.out.println("----------------------------");
//    printDiscounts(cart, classDiscounts(), "Class discounts");
    classDiscounts().apply(cart).stream().forEach(System.out::println);
//    System.out.println(classDiscounts().apply(cart).stream().mapToDouble(Double::doubleValue).sum());
    System.out.println(classDiscounts().getDescription(cart));

  }

//  static private Discount generalDiscounts() {
////    IsApplicable fridayCondition = (Product product) -> LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY;
////    CalculateDiscount fridayCalc = (Product product) -> product.price() * 0.1;
////
////    IsApplicable milkCondition = (Product product) -> product.name().equalsIgnoreCase("MJÖLK");
////    CalculateDiscount milkCalc = (Product product) -> product.price() * 0.05;
////
////    IsApplicable quantityCondition = (Product product) -> product.quantity() >= 5;
////    CalculateDiscount quantityCalc = (Product product) -> product.quantity() * 10;
////
////
////
////
////    return
////        new GeneralDiscount("Fredagsrabatt 10%.", fridayCondition, fridayCalc,
////            new GeneralDiscount("5% rabatt på mjölk.", milkCondition, milkCalc,
////                new GeneralDiscount("10kr avdrag per produkt när man handlar minst 5 produkter.", quantityCondition, quantityCalc,
////                    new NoDiscount())));
//  }

  static public Discount classDiscounts() {
    return new MilkDiscount(new FridayDiscount(new QuantityDiscount(new NoDiscount())));
  }

  static private void printDiscounts(ShoppingCart cart, Discount discounts, String discountsName) {
//    double total = cart.getTotal();
//    double totalDiscount = 0;
//    System.out.println(discountsName);
//    System.out.println("----------------------------");
//
//    for (int i = 0; i < cart.getProducts().size(); i++) {
//      Product product = cart.getProducts().get(i);
//      double productDiscount = discounts.apply(product);
//      System.out.println(product.name().toUpperCase());
//      System.out.println(discounts.getDescription(product));
//      System.out.println("Produktrabatt: " + productDiscount + "kr");
//      System.out.println("----------------------------");
//      totalDiscount += productDiscount;
//    }
//    System.out.println("Att betala: " + (total - totalDiscount));
  }
}


