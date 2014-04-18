SoyAnnotations
==============

Annotations for turning soy classes into SoyData and rendering straight to templates.


## Example:

Here is a example that renders a User instance into a string by analyzing the `@Soy.Template` annotation.

**User.java**
```java
@Soy
@Soy.Template("templates.User")
public class User {

   private final String name, email;

   public User(String name, String email) {
     this.name = name;
     this.email = email;
   }

   @Soy.Method("Name")
   public String getName() {
     return name;
   }

   @Soy.Method("Email")
   public String getEmail() {
     return email;
   }
}
```


**templates.soy:**
```soy
{namespace templates}

/**
 * @param Name
 * @param Email
 */
{template .User} 
  {$Name} ({$Email})
{/template}
```


**UserApplication.java:**
```java
import com.google.template.soy.SoyFileSet;
import slieb.soy.Sauce;
import slieb.soy.Loader;


public class UserApplication {
  
  public static void main (final String[] args) {

    SoyTofu soyTofu = new SoyFileSet.Builder()
        .add(new File("templates.soy"))
        .compileToFu();

    Sauce sauce = Loader.getContext(soyTofu);

    User user = new User("John", "john@domain.com");

    SoyData data = sauce.getSoyData(user); // SoyData to match

    Object jsonData = sauce.getJsonData(user); // JsonData to match

    String result = sauce.getRenderString(user); // "John (john@domain.com)"

  }
}
```