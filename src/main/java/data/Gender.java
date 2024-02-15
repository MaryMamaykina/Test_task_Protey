package data;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");
    private String text;
    Gender(String text){
        this.text = text;
    }
    public String getText(){
        return text;
    }
}
