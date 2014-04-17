package example.cases;


import org.junit.Test;
import slieb.soy.annotations.CustomConverter;
import slieb.soy.annotations.Soy;
import slieb.soy.configuration.Loader;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.meta.MetaConverter;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class CustomConverterTestCase {

    public static class ExampleToMapConverter implements MetaConverter {

        @Override
        public Map<String, Integer> convert(Object from) {
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

        public String getHash() {
            return format("%s:%s", userName, userEmail);
        }
    }

    @Test
    public void testCustomConverter() {
        SoyDataFactoryContext context = Loader.getFullSoyDataContext();
        Example example = new Example("John", "john@domain.com");
        Object object = context.convert(example);
    }
}
