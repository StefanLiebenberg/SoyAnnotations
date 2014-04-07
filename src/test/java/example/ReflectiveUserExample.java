package example;


public class ReflectiveUserExample extends AbstractUser {

    public final String HomePage;

    public ReflectiveUserExample(String homePage, String name, String email, Integer id) {
        super(name, email, id);
        this.HomePage = homePage;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }
}
