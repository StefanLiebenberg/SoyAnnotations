package example;

import slieb.soy.annotations.SoyAnnotated;
import slieb.soy.annotations.SoyField;
import slieb.soy.annotations.SoyMethod;
import slieb.soy.annotations.SoyTemplate;


@SoyAnnotated
@SoyTemplate("example.User")
public class User {

    public User(String id) {
        this.id = id;
    }

    @SoyField("Id")
    public final String id;

    private String name;

    private String email;

    @SoyMethod("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SoyMethod("Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
