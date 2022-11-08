package menuorderingsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    public List<MenuItem> main;
    public List<MenuItem> sides;
    public List<MenuItem> drinks;
    public List<MenuItem> desserts;

    public Order() {
        main = new ArrayList<>();
        sides = new ArrayList<>();
        drinks = new ArrayList<>();
        desserts = new ArrayList<>();
    }

    public String printOrder(String meal) {
        //Verify rules
        List<String> errors = new ArrayList<>();

        //All meals must have a main dish
        if (main.size() == 0)
            errors.add("main is missing");
        //No meal can have more than one main dish
        if (main.size() > 1)
            errors.add(main.get(0).getName().toLowerCase() + " cannot be ordered more than once");
        //All meals must have a side
        if (sides.size() == 0)
            errors.add("side is missing");
        //Multiple sides can be ordered at lunch
        if (!meal.equals("lunch") && sides.size() > 1)
            errors.add(sides.get(0).getName().toLowerCase() + " cannot be ordered more than once");
        //Coffee can be ordered multiple times at breakfast
        if (!meal.equals("breakfast") && drinks.size() > 1)
            errors.add(drinks.get(0).getName().toLowerCase() + " cannot be ordered more than once");
        //Water is added to all dinner meals, and added if no other drinks are ordered
        if (meal.equals("dinner") || drinks.size() == 0)
            drinks.add(new MenuItem("Water", "Drink"));
        //Dessert must be ordered at dinner
        if (meal.equals("dinner") && desserts.size() == 0)
            errors.add("dessert is missing");

        //Print errors
        if (errors.size() > 0) {
            //Capitalize only first error
            String firstValue = errors.get(0);
            errors.set(0, firstValue.substring(0, 1).toUpperCase() + firstValue.substring(1));
            return "Unable to process: " + String.join(", ", errors);
        }
        else return toString();
    }

    @Override
    public String toString() {
        //Build list to print out
        List<String> list = new ArrayList<>();
        addItemString(list, main);
        addItemString(list, sides);
        addItemString(list, drinks);
        addItemString(list, desserts);

        return String.join(", ", list);
    }

    private void addItemString(List<String> strList, List<MenuItem> list) {
        int size = list.size();
        if (size > 0) {
            HashMap<String, Integer> itemCounts = new HashMap<>();

            //Get count of each type of item
            for (MenuItem item: list) {
                String name = item.getName();
                if (itemCounts.containsKey(name)) itemCounts.put(name, itemCounts.get(name) + 1);
                else itemCounts.put(name, 1);
            }

            for (HashMap.Entry<String, Integer> elem : itemCounts.entrySet()) {
                String str = elem.getKey();
                if (elem.getValue() > 1) str += String.format("(%d)",elem.getValue());
                strList.add(str);
            }
        }
    }
}
