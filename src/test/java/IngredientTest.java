import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType ingredientType;
    private final String name;
    private final float price;

    protected static Database database = new Database();

    public IngredientTest(IngredientType ingredientType, String name, float price) {
        this.ingredientType = ingredientType;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Ингредиент : {0}, Название: {1}, Цена :{2} ")
    public static Object[][] dataForTest() {
        return new Object[][]{
                {database.availableIngredients().get(0).getType(),database.availableIngredients().get(0).getName(),database.availableIngredients().get(0).getPrice()},
                {database.availableIngredients().get(1).getType(),database.availableIngredients().get(1).getName(),database.availableIngredients().get(1).getPrice()},
                {database.availableIngredients().get(2).getType(),database.availableIngredients().get(2).getName(),database.availableIngredients().get(2).getPrice()},
                {database.availableIngredients().get(3).getType(),database.availableIngredients().get(3).getName(),database.availableIngredients().get(3).getPrice()},
                {database.availableIngredients().get(4).getType(),database.availableIngredients().get(4).getName(),database.availableIngredients().get(4).getPrice()},
                {database.availableIngredients().get(5).getType(),database.availableIngredients().get(5).getName(),database.availableIngredients().get(5).getPrice()},
        };
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        assertEquals(price, ingredient.getPrice(), 0.0f);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(ingredientType, name, price);
        assertEquals(ingredientType, ingredient.getType());
    }
}
