package example.models;


public abstract class AbstractUser {

    protected final String name, email;
    protected final Integer id;

    public AbstractUser(String name, String email, Integer id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }
}
