package menuorderingsystem;

public class CommandParser {

    public String parseOrder(String orderString) {
        String[] order = orderString.split(" ");
        if (order.length < 1) return printFormatError();

        //Check if meal type is valid (e.g. Breakfast, Lunch, or Dinner)
        String meal = order[0].toLowerCase();
        boolean validMeal = false;
        for (String mealType: Menu.meals) {
            if (meal.equals(mealType)) {
                validMeal = true;
                break;
            }
        }
        if (!validMeal) return printMealTypeError(meal);

        String[] items;
        if (order.length > 1) {
            //Check if item list only contains numbers and digits
            if (!order[1].matches("^[\\d|,]+$")) return printFormatError();
            items = order[1].split(",");
        } else {
            //Empty order
            items = new String[]{};
        }
        OrderingSystem system = new OrderingSystem();

        return system.processOrder(meal, items);
    }

    private String printFormatError() {
        return "Invalid format, please format orders like so: Breakfast 1,2,3";
    }

    private String printMealTypeError(String meal) {
        return "Invalid meal \""+meal+"\", must be breakfast, lunch, or dinner";
    }
}
