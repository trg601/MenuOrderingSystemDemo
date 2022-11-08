package menuorderingsystem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.io.InputStreamReader;

public class Menu {
    public final static String[] meals = new String[]{
            "breakfast",
            "lunch",
            "dinner"
    };

    private static JsonObject jsonMenu;
    private static Gson gson;
    private static Menu instance;

    public static Menu getInstance() {
        if (instance == null) instance = new Menu();
        return instance;
    }

    private Menu() {
        gson = new Gson();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.json");
        InputStreamReader reader = new InputStreamReader(inputStream);

        jsonMenu = gson.fromJson(reader, JsonObject.class);
    }

    public MenuItem getMenuItem(String meal, int num) {
        JsonArray array = (JsonArray) jsonMenu.get(meal);
        if (array != null && (num > 0 && num <= array.size())) {
            JsonObject obj = (JsonObject) array.get(num - 1);
            if (obj != null) {
                return gson.fromJson(obj, MenuItem.class);
            }
        }
        System.out.printf("Error finding item \"%d\" for meal \"%s\" in json file%n", num, meal);
        return null;
    }
}
