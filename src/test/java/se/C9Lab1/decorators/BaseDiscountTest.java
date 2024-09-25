package se.C9Lab1.decorators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.*;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;


import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

public class BaseDiscountTest {
  private final ShoppingCart shoppingCart = new ShoppingCart("TEST_CART", LocalDate.now());
  private final Product product = new Product("TEST_PRODUCT", 100, 1);
  private final MockedConcreteComponent mockedConcreteComponent = new MockedConcreteComponent();
  private final String FIRST_DESCRIPTION = "5% off!";
  private final String SECOND_DESCRIPTION = "10% off!";
  private final Double DECORATOR_CALC1 = 20.0;
  private final Double DECORATOR_CALC2 = 30.0;


  @Nested
  class GetDescription {
    @Test
    @DisplayName("No discount applied should return empty string")
    void noDiscountAppliedShouldReturnEmptyString() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, false, "5% off!", 0);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, "10% off!", 0);
      String description = firstDiscount.getDescription(product, shoppingCart);

      assertEquals("", description);
    }

    @Test
    @DisplayName("Only one decorators isApplicable is true should return only one description string")
    void onlyOneDecoratorsIsApplicableIsTrueShouldReturnOnlyOneDescriptionString() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, SECOND_DESCRIPTION, 0);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, FIRST_DESCRIPTION, 0);
      String description = firstDiscount.getDescription(product, shoppingCart);

      assertEquals(SECOND_DESCRIPTION, description);
    }

    @Test
    @DisplayName("Both decorators isApplicable are true should return combined description string")
    void bothDecoratorsIsApplicableAreTrueShouldReturnCombinedDescriptionString() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, SECOND_DESCRIPTION, 0);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, true, FIRST_DESCRIPTION, 0);
      String description = firstDiscount.getDescription(product, shoppingCart);

      assertEquals(FIRST_DESCRIPTION + System.lineSeparator() + SECOND_DESCRIPTION, description);
    }
  }

  @Nested
  class Apply {
    @Test
    @DisplayName("No decorators isApplicable is true should return zero")
    void noDecoratorsIsApplicableIsTrueShouldReturnZero() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, false, FIRST_DESCRIPTION, DECORATOR_CALC2);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, SECOND_DESCRIPTION, DECORATOR_CALC1);
      double discount = firstDiscount.apply(product, shoppingCart);

      assertEquals(0, discount);
    }

    @Test
    @DisplayName("One decorators isApplicable is true should return that decorators calculateDiscount return value")
    void oneDecoratorsIsApplicableIsTrueShouldReturnThatDecoratorsCalculateDiscountReturnValue() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, FIRST_DESCRIPTION, DECORATOR_CALC2);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, SECOND_DESCRIPTION, DECORATOR_CALC1);
      double discount = firstDiscount.apply(product, shoppingCart);

      assertEquals(DECORATOR_CALC2, discount);
    }

    @Test
    @DisplayName("Both decorators isApplicable is true should return sum of both decorators calculateDiscount return value")
    void bothDecoratorsIsApplicableIsTrueShouldReturnSumOfBothDecoratorsCalculateDiscountReturnValue() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, FIRST_DESCRIPTION, DECORATOR_CALC2);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, true, SECOND_DESCRIPTION, DECORATOR_CALC1);
      double discount = firstDiscount.apply(product, shoppingCart);

      assertEquals(DECORATOR_CALC1 + DECORATOR_CALC2, discount);
    }

  }
}


