package example.models;


public class ReflectiveManExample {

    public String name;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void clearName() {
        name = null;
    }

    private String getName() {
        return name;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public static final String RACE = "Man";

    public static String getRace() {
        return RACE;
    }
}
