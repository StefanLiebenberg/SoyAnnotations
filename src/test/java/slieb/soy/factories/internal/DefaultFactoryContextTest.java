package slieb.soy.factories.internal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import slieb.soy.exceptions.MissingFactory;

import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFactoryContextTest {

    private static class Model {
    }

    private static class InputClass {
    }

    @Mock
    private Model mockGoodModel, mockBadModel;

    @Mock
    private Factory<Model> mockFactoryA, mockFactoryB, mockFactoryC;

    private DefaultFactoryContext<Model> context;

    @Before
    public void setUp() throws Exception {
        when(mockFactoryA.create(InputClass.class)).thenReturn(mockBadModel);
        when(mockFactoryB.create(InputClass.class)).thenReturn(mockBadModel);
        when(mockFactoryC.create(InputClass.class)).thenReturn(mockBadModel);
        context = new DefaultFactoryContext<>();
        context.addCustomFactory(mockFactoryA);
        context.addCustomFactory(mockFactoryB);
        context.addCustomFactory(mockFactoryC);
    }

    @Test(expected = MissingFactory.class)
    public void testCreateWithoutAnyFactoryThatReturnsCanCreate() throws Exception {
        context.create(InputClass.class);
    }

    @Test
    public void testCreateWhenFactoryAReturnsCanCreateTrue() throws Exception {
        when(mockFactoryA.canCreate(InputClass.class)).thenReturn(TRUE);
        when(mockFactoryA.create(InputClass.class)).thenReturn(mockGoodModel);
        assertEquals(mockGoodModel, context.create(InputClass.class));
    }

    @Test
    public void testCreateWhenFactoryBReturnsCanCreateTrue() throws Exception {
        when(mockFactoryB.canCreate(InputClass.class)).thenReturn(TRUE);
        when(mockFactoryB.create(InputClass.class)).thenReturn(mockGoodModel);
        assertEquals(mockGoodModel, context.create(InputClass.class));
    }

    @Test
    public void testCreateWhenFactoryCReturnsCanCreateTrue() throws Exception {
        when(mockFactoryC.canCreate(InputClass.class)).thenReturn(TRUE);
        when(mockFactoryC.create(InputClass.class)).thenReturn(mockGoodModel);
        assertEquals(mockGoodModel, context.create(InputClass.class));
    }

    @Test
    public void testCreateWhenFactoryAAndBReturnsCanCreateTrue() throws Exception {
        when(mockFactoryA.canCreate(InputClass.class)).thenReturn(TRUE);
        when(mockFactoryA.create(InputClass.class)).thenReturn(mockGoodModel);
        when(mockFactoryB.canCreate(InputClass.class)).thenReturn(TRUE);
        assertEquals(mockGoodModel, context.create(InputClass.class));
    }
}
