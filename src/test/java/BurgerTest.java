import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    protected Bun bunMock;

    @Mock
    protected Ingredient ingredientMock;

    @Spy
    protected Burger burgerSpy;


    @Test
    public void setBunTest() {
        burgerSpy.setBuns(bunMock);
        Mockito.verify(burgerSpy).setBuns(bunMock);
        Mockito.verify(burgerSpy, Mockito.times(1)).setBuns(bunMock);
        assertEquals(bunMock, burgerSpy.bun);
    }

    @Test
    public void addIngredientTest() {
        assertTrue(burgerSpy.ingredients.isEmpty());
        burgerSpy.addIngredient(ingredientMock);
        Mockito.verify(burgerSpy).addIngredient(ingredientMock);
        Mockito.verify(burgerSpy, Mockito.times(1)).addIngredient(ingredientMock);
        assertEquals(ingredientMock, burgerSpy.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burgerSpy.addIngredient(ingredientMock);
        burgerSpy.removeIngredient(0);
        Mockito.verify(burgerSpy).removeIngredient(Mockito.anyInt());
        Mockito.verify(burgerSpy, Mockito.times(1)).removeIngredient(0);
        assertTrue(burgerSpy.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Mockito.when(firstIngredient.getName()).thenReturn("firstIngredient");
        burgerSpy.addIngredient(firstIngredient);
        assertEquals("firstIngredient", burgerSpy.ingredients.get(0).getName());

        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        Mockito.when(secondIngredient.getName()).thenReturn("SecondIngredient");
        burgerSpy.addIngredient(secondIngredient);
        assertEquals("SecondIngredient", burgerSpy.ingredients.get(1).getName());

        burgerSpy.moveIngredient(0, 1);
        assertEquals("firstIngredient", burgerSpy.ingredients.get(1).getName());
    }

    @Test
    public void getPriceTest() {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(11f);

        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getPrice()).thenReturn(5f);

        burgerSpy.setBuns(bun);
        burgerSpy.addIngredient(ingredient);
        float expected = (bun.getPrice() * 2) + ingredient.getPrice();
        assertEquals(expected, burgerSpy.getPrice(),0.001);
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(ingredientMock.getName()).thenReturn("ingredient");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(bunMock.getName()).thenReturn("bun");
        burgerSpy.setBuns(bunMock);
        burgerSpy.addIngredient(ingredientMock);
        String actual = burgerSpy.getReceipt();
        System.out.println(actual);

        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("(==== %s ====)%n", bunMock.getName()));
        receipt.append(String.format("= %s %s =%n", burgerSpy.ingredients.get(0).getType().toString().toLowerCase(), burgerSpy.ingredients.get(0).getName()));
        receipt.append(String.format("(==== %s ====)%n", bunMock.getName()));
        receipt.append(String.format("%nPrice: %f%n", burgerSpy.getPrice()));

        String expected = receipt.toString();
        assertEquals(expected, actual);
    }
}
