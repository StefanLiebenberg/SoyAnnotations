SoyAnnotations
==============

Annotations for turning soy classes into SoyData and rendering straight to templates.

**User.java**
'''java
    @Soy
    @Soy.Template("templates.User")
    public class User {
       private final String name, email;

       @Soy.Method("Name")
       public String getName() {
         return name;
       }

       @Soy.Method("Email")
       public String getEmail() {
         return email;
       }
    }
'''


**templates.soy:**
'''soy
    {namespace templates}

    /**
     * @param Name
     * @param Email
     */
    {template .User}
       {$Name} ({$Email})
    {/template}
'''


**UserApplication.java:**
'''java
    public class UserApplication {

      public static void main (final String[] args) {

        SoyTofu soyTofu = SoyFileSet.Builder()
            .add(new File("templates.soy"))
            .compileToFu();

        RendererFactoryContext context = new RendererFactoryContext(soyTofu);

        User user = new User("John", "john@domain.com");
        String result = context.render(user); // "John (john@domain.com)"

      }
    }
'''