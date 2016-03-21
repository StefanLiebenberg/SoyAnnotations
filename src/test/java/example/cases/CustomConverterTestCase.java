package example.cases;


import com.google.inject.Injector;
import org.junit.Test;
import slieb.soy.Loader;
import slieb.soy.annotations.CustomConverter;
import slieb.soy.annotations.Soy;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.meta.MetaConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomConverterTestCase {

    private final Injector injector = Loader.getFullInjector();

    public static class ExampleToMapConverter implements MetaConverter {

        @Override
        public Map<String, Integer> apply(Object from) {
            if (from instanceof Example) {
                Map<String, Integer> amountMap = new HashMap<>();
                amountMap.put("Name", ((Example) from).userName.length());
                amountMap.put("Email", ((Example) from).userEmail.length());
                return amountMap;
            } else {
                return null;
            }
        }

    }


    @Soy
    @CustomConverter(ExampleToMapConverter.class)
    public static class Example {
        @Soy.Field("Name")
        public final String userName;

        @Soy.Field("Email")
        public final String userEmail;

        public Example(String userName, String userEmail) {
            this.userName = userName;
            this.userEmail = userEmail;
        }

    }

    @Test
    public void testCustomConverter() {
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Example example = new Example("John", "john@domain.com");
        Object object = context.apply(example);
    }
}
