import menuorderingsystem.CommandParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOrders {

    @Test
    public void Test1() {
        CommandParser parser = new CommandParser();
        String output;

        output = parser.parseOrder("Breakfast 1,2,3");
        Assertions.assertEquals("Eggs, Toast, Coffee", output);

        output = parser.parseOrder("Breakfast 3,2,1");
        Assertions.assertEquals("Eggs, Toast, Coffee", output);

        output = parser.parseOrder("Breakfast 1,2,3,3,3");
        Assertions.assertEquals("Eggs, Toast, Coffee(3)", output);

        output = parser.parseOrder("Breakfast 1");
        Assertions.assertEquals("Unable to process: Side is missing", output);

        output = parser.parseOrder("Lunch 1,2,3");
        Assertions.assertEquals("Sandwich, Chips, Soda", output);

        output = parser.parseOrder("Lunch 1,2");
        Assertions.assertEquals("Sandwich, Chips, Water", output);

        output = parser.parseOrder("Lunch 1,1,2,3");
        Assertions.assertEquals("Unable to process: Sandwich cannot be ordered more than once", output);

        output = parser.parseOrder("Lunch 1,2,2");
        Assertions.assertEquals("Sandwich, Chips(2), Water", output);

        output = parser.parseOrder("Lunch");
        Assertions.assertEquals("Unable to process: Main is missing, side is missing", output);

        output = parser.parseOrder("Dinner 1,2,3,4");
        Assertions.assertEquals("Steak, Potatoes, Wine, Water, Cake", output);

        output = parser.parseOrder("Dinner 1,2,3");
        Assertions.assertEquals("Unable to process: Dessert is missing", output);
    }

    @Test
    public void Test2() {
        CommandParser parser = new CommandParser();
        String output;

        output = parser.parseOrder("Dinner 1,1,1,1");
        Assertions.assertEquals("Unable to process: Steak cannot be ordered more than once, side is missing, dessert is missing", output);

        output = parser.parseOrder("Dinner 1,2,4,4,4");
        Assertions.assertEquals("Steak, Potatoes, Water, Cake(3)", output);

        output = parser.parseOrder("Breakfast");
        Assertions.assertEquals("Unable to process: Main is missing, side is missing", output);

        output = parser.parseOrder("Dinner");
        Assertions.assertEquals("Unable to process: Main is missing, side is missing, dessert is missing", output);
    }
}
