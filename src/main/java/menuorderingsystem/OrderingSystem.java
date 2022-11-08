package menuorderingsystem;

public class OrderingSystem {
    public Menu menu;

    public OrderingSystem() {
        menu = Menu.getInstance();
    }

    public String processOrder(String meal, String[] itemNumbers) {
        Order order = new Order();

        for (String itemNum: itemNumbers) {
            MenuItem item = menu.getMenuItem(meal, Integer.parseInt(itemNum));
            if (item != null) {
                switch (item.getType()) {
                    case "Main" -> {
                        order.main.add(item);
                    }
                    case "Side" -> {
                        order.sides.add(item);
                    }
                    case "Drink" -> {
                        order.drinks.add(item);
                    }
                    case "Dessert" -> order.desserts.add(item);
                }
            }
        }

        return order.printOrder(meal);
    }

}
