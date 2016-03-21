package example.models;

import org.slieb.soy.annotations.Soy;


@Soy
@Soy.Template("example.User")
public class User {

    public User(String id) {
        this.id = id;
    }

    @Soy.Field("Id")
    public final String id;

    @Soy.Field("IsHuman")
    public boolean isHuman = false;

    private String name;

    private String email;

    @Soy.Method("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Soy.Method("Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
