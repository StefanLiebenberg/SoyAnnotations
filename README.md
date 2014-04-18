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
import slieb.soy.factories.jsondata.JsonDataFactoryContext;
import slieb.soy.factories.soydata.SoyDataFactoryContext;
import slieb.soy.factories.rendering.RendererFactoryContext;
import slieb.soy.Loader;


public class UserApplication {

  private static final SoyTofu soyTofu = new SoyFileSet.Builder()
        .add(new File("templates.soy"))
        .compileToFu();

  private static final Injector injector = Loader.getFullInjector(soyTofu, null);
  
  public static void main (final String[] args) {

    User user = new User("John", "john@domain.com");

    SoyDataFactoryContext soyContext = injector.getInstance(SoyDataFactoryContext.class);
    SoyData data = soyContext.getSoyData(user); // SoyData to match

    JsonDataFactoryContext jsonContext = injector.getInstance(JsonDataFactoryContext.class);
    Object jsonData = jsonContext.getJsonData(user); // JsonData to match

    RendererFactoryContext rendererContext = injector.getInstance(RendererFactoryContext);
    String result = rendererContext.getRenderString(user); // "John (john@domain.com)"

  }
}
```