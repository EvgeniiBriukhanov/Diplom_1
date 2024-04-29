import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BunTest {

    private final String bunName;
    private final float bunPrice;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    protected static Database database = new Database();

    @Parameterized.Parameters(name = "Модель булочки: {0}, Цена: {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {database.availableBuns().get(0).name, database.availableBuns().get(0).price},
                {database.availableBuns().get(1).name, database.availableBuns().get(1).price},
                {database.availableBuns().get(2).name, database.availableBuns().get(2).price}
        };
    }
    @Test
    public void getNameTest(){
       Bun bun = new Bun(bunName,bunPrice);
        assertEquals(bunName,bun.getName());
    }
    @Test
    public void getPriceTest(){
        Bun bun = new Bun(bunName,bunPrice);
        assertEquals(bunPrice,bun.getPrice(),0.001);
    }
}