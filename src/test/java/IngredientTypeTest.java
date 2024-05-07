import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {
    @Test
    public void sauceNotNullTest() {
        assertNotNull(IngredientType.SAUCE);
    }

    @Test
    public void fillingNotNullTest() {
        assertNotNull(IngredientType.FILLING);
    }
}
