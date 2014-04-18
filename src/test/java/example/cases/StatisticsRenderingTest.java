package example.cases;


import com.google.inject.Injector;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import example.models.Statistics;
import org.junit.Test;
import slieb.soy.Loader;
import slieb.soy.context.RendererFactoryContext;
import slieb.soy.factories.rendering.Renderer;

import static org.junit.Assert.assertEquals;

public class StatisticsRenderingTest {


    public final SoyTofu soyTofu = new SoyFileSet.Builder()
            .add(getClass().getResource("/templates/models/StatisticsTemplate.soy"))
            .build().compileToTofu();

    public final Injector injector = Loader.getFullInjector(soyTofu, null);


    @Test
    public void testRenderStatistics() {
        Renderer<Object> renderer = injector.getInstance(RendererFactoryContext.class);
        Statistics stats = new Statistics(10, 100, 1);
        String result = renderer.render(stats);
        assertEquals("<ul><li>Downloads: 10</li><li>PageViews: 100</li><li>UniqueVisitors: 1</li></ul>", result);
    }
}
