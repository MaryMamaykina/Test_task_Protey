package data;

public enum Option2 {
    ONE("2.1"),
    TWO("2.2"),
    THREE("2.3");

    private final String text;

    Option2(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
