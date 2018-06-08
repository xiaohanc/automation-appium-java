package driver;

public enum Action {
    BeforeClick("Attempting to click"),

    AfterClick("Clicked successful");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getStringValue() {
        return name;
    }
}
