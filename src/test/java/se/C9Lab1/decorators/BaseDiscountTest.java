package se.C9Lab1.decorators;

import org.junit.jupiter.api.*;
import se.C9Lab1.entities.Product;
import se.C9Lab1.entities.ShoppingCart;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseDiscountTest {
  private final ShoppingCart shoppingCart = new ShoppingCart("TEST_SHOPPINGCART", LocalDate.now());
  private final Product product = new Product("TEST_PRODUCT", 100, 1);
  private final MockedConcreteComponent mockedConcreteComponent = new MockedConcreteComponent();
  private final String FIRST_DESCRIPTION = "5% off!";
  private final String SECOND_DESCRIPTION = "10% off!";
  private final Double DECORATOR_CALC1 = 20.0;
  private final Double DECORATOR_CALC2 = 30.0;

  @BeforeEach
  void setUp() {
    shoppingCart.addProduct(product);
  }

  @Nested
  class GetDescription {
    @Test
    @DisplayName("No discount applied should return empty list")
    void noDiscountAppliedShouldReturnEmptyString() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, false, "5% off!", 0);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, "10% off!", 0);
      List<String> description = firstDiscount.getDescription(shoppingCart);

      assertEquals(List.of(), description);
    }

    @Test
    @DisplayName("Only one decorators isApplicable is true should return only one description string")
    void onlyOneDecoratorsIsApplicableIsTrueShouldReturnOnlyOneDescriptionString() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, SECOND_DESCRIPTION, 0);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, FIRST_DESCRIPTION, 0);
      List<String> description = firstDiscount.getDescription(shoppingCart);

      assertEquals(List.of(SECOND_DESCRIPTION), description);
    }

    @Test
    @DisplayName("Both decorators isApplicable are true should return combined description string")
    void bothDecoratorsIsApplicableAreTrueShouldReturnCombinedDescriptionString() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, SECOND_DESCRIPTION, 0);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, true, FIRST_DESCRIPTION, 0);
      List<String> description = firstDiscount.getDescription(shoppingCart);

      assertEquals(List.of(FIRST_DESCRIPTION, SECOND_DESCRIPTION), description);
    }
  }

  @Nested
  class Apply {
    @Test
    @DisplayName("No decorators isApplicable is true should return zero")
    void noDecoratorsIsApplicableIsTrueShouldReturnZero() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, false, FIRST_DESCRIPTION, DECORATOR_CALC2);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, SECOND_DESCRIPTION, DECORATOR_CALC1);
      List<Double> discount = firstDiscount.apply(shoppingCart);

      assertEquals(List.of(), discount);
    }

    @Test
    @DisplayName("One decorators isApplicable is true should return that decorators calculateDiscount return value")
    void oneDecoratorsIsApplicableIsTrueShouldReturnThatDecoratorsCalculateDiscountReturnValue() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, FIRST_DESCRIPTION, DECORATOR_CALC2);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, false, SECOND_DESCRIPTION, DECORATOR_CALC1);
      List<Double> discount = firstDiscount.apply(shoppingCart);

      assertEquals(List.of(DECORATOR_CALC2), discount);
    }

    @Test
    @DisplayName("Both decorators isApplicable is true should return sum of both decorators calculateDiscount return value")
    void bothDecoratorsIsApplicableIsTrueShouldReturnSumOfBothDecoratorsCalculateDiscountReturnValue() {
      BaseDiscount secondDiscount = new MockedConcreteDecorator(mockedConcreteComponent, true, FIRST_DESCRIPTION, DECORATOR_CALC2);
      BaseDiscount firstDiscount = new MockedConcreteDecorator(secondDiscount, true, SECOND_DESCRIPTION, DECORATOR_CALC1);
      List<Double> discount = firstDiscount.apply(shoppingCart);

      assertEquals(List.of(DECORATOR_CALC1, DECORATOR_CALC2), discount);
    }

  }
}


