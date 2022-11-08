package menuorderingsystem;

public class MenuItem {
    private String name;
    private String type;

    public MenuItem(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
