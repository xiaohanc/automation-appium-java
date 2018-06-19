package driver;

public enum Action {
    Click("Click");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getStringValue() {
        return name;
    }
}
