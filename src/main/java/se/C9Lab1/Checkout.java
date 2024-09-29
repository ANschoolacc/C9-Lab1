package se.C9Lab1;

import se.C9Lab1.decorators.*;
import se.C9Lab1.components.*;
import se.C9Lab1.entities.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.List;

public class Checkout {
  public static void main() {
    ShoppingCart cart = new ShoppingCart(
        "ALBIN",
        LocalDate.of(2024, 9, 20));

    cart.addProduct(new Product("Mjölk", 100, 5));
    cart.addProduct(new Product("smör", 60, 1));
    cart.addProduct(new Product("ost", 70, 1));

    printDiscounts(cart, generalDiscounts(), "General discounts");
    System.out.println("----------------------------");
    printDiscounts(cart, classDiscounts(), "Class discounts");
  }

  static private Discount generalDiscounts() {
    IsApplicable fridayCondition = (ShoppingCart shoppingCart) ->
        shoppingCart.getDateOfPurchase().getDayOfWeek() == DayOfWeek.FRIDAY;

    CalculateDiscount fridayCalc = (ShoppingCart shoppingCart) ->
        shoppingCart.getTotal() * 0.1;

    IsApplicable milkCondition =
        (ShoppingCart shoppingCart) ->
            shoppingCart.getProducts()
                .stream()
                .anyMatch(product -> product.name().equalsIgnoreCase("MJÖLK"));

    CalculateDiscount milkCalc = (ShoppingCart shoppingCart) ->
        shoppingCart.getProducts()
            .stream()
            .filter(product -> product.name().equalsIgnoreCase("MJÖLK"))
            .mapToDouble(product -> product.price() * 0.05)
            .sum();

    IsApplicable quantityCondition = (ShoppingCart shoppingCart) ->
        shoppingCart.getProducts()
            .stream()
            .anyMatch(product -> product.quantity() >= 5);

    CalculateDiscount quantityCalc = (ShoppingCart shoppingCart) ->
        shoppingCart.getProducts()
            .stream()
            .filter(product -> product.quantity() >= 5)
            .mapToDouble(product -> product.quantity() * 10)
            .sum();

    List<String> knownCustomers = List.of("ALBIN", "ANNA", "ERIK", "GARGAMEL");

    IsApplicable knownCustomerCondition = (ShoppingCart shoppingCart) ->
        knownCustomers
            .stream()
            .anyMatch(name -> shoppingCart.getCustomerName().equalsIgnoreCase(name));

    CalculateDiscount knownCustomerCalc = (ShoppingCart shoppingCart) ->
        shoppingCart.getTotal() * 0.05;

    return
        new GeneralDiscount(
            "Fredagsrabatt, 10% rabatt på totalbeloppet.",
            fridayCondition,
            fridayCalc,
            new GeneralDiscount(
                "5% rabatt på mjölk.",
                milkCondition,
                milkCalc,
                new GeneralDiscount(
                    "10kr avdrag per produkt när man handlar minst 5 produkter.",
                    quantityCondition,
                    quantityCalc,
                    new GeneralDiscount(
                        "Känd kund, 5% rabatt på totalbeloppet.",
                        knownCustomerCondition,
                        knownCustomerCalc,
                        new NoDiscount()))));
  }

  static public Discount classDiscounts() {
    return new MilkDiscount(new FridayDiscount(new QuantityDiscount(new NoDiscount())));
  }

  static private void printDiscounts(ShoppingCart cart, Discount discounts, String discountsName) {
    double total = cart.getTotal();
    double totalDiscount = discounts.apply(cart).stream().mapToDouble(Double::doubleValue).sum();
    System.out.println(discountsName);
    System.out.println("----------------------------");
    discounts.getDescription(cart).forEach(System.out::println);
    discounts.apply(cart).forEach(System.out::println);
    System.out.println("Att betala: " + (total - totalDiscount));
  }
}


