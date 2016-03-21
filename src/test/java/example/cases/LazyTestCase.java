package example.cases;

import com.google.inject.Injector;
import com.google.template.soy.data.SoyValue;
import org.junit.Test;
import slieb.soy.Loader;
import slieb.soy.annotations.Soy;
import slieb.soy.context.SoyDataFactoryContext;

public class LazyTestCase {

    @Soy
    @Soy.Dynamic
    public static class Node {

        @Soy.Field("Name")
        public String name;

        @Soy.Field("Next")
        @Soy.Dynamic
        public Node next;

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            b.append(name).append(":");
            if (next != null) {
                b.append("[")
                 .append(next.toString())
                 .append("]");
            } else {
                b.append("null");
            }
            return b.toString();
        }
    }

    public Node createChain(Integer count,
                            Node lastNode) {
        if (count > 0) {
            Node node = new Node();
            node.name = "Node " + count;
            node.next = createChain(count - 1, lastNode);
            return node;
        } else {
            return lastNode;
        }
    }

    public Node createSelfReferenceChain(Integer count) {
        Node root = new Node();
        root.name = "Root Node";
        root.next = createChain(count - 1, root);
        return root;
    }

    @Test
    public void testExceptionIsNotThrownNormalChain() {
        Injector injector = Loader.getFullInjector();
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Node node = createChain(3, null);
        SoyValue soyData = context.apply(node);
    }

    @Test(expected = StackOverflowError.class)
    public void testExceptionIsThrownOnSelfReferencingChain() {
        Injector injector = Loader.getFullInjector();
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Node node = createSelfReferenceChain(10);
        SoyValue soyData = context.apply(node);
    }

    @Test
    public void testExceptionIsNotThrownOnSelfReferencingChainWithLazyContext() {
        Injector injector = Loader.getLazyInjector();
        SoyDataFactoryContext context = injector.getInstance(SoyDataFactoryContext.class);
        Node node = createSelfReferenceChain(10);
        SoyValue soyData = context.apply(node);
    }
}
