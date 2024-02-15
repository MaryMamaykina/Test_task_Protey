package data;

public enum Option1 {
    NONE ("Нет"),
    ONE("1.1"),
    TWO("1.2")
    ;

    private final String text;

    Option1(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
