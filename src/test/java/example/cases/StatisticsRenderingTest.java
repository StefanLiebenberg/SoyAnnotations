package example.cases;


import com.google.common.collect.Sets;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import example.models.Statistics;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.Loader;
import slieb.soy.factories.rendering.Renderer;

import static org.junit.Assert.assertEquals;

public class StatisticsRenderingTest {


    public SoyTofu soyTofu;

    @Before
    public void setUp() {
        soyTofu = new SoyFileSet.Builder()
                .add(getClass().getResource("/templates/models/StatisticsTemplate.soy"))
                .build().compileToTofu();
    }

    @Test
    public void testRenderStatistics() {
        Renderer<Object> renderer = Loader.getRenderer(soyTofu, null);
        Statistics stats = new Statistics(10, 100, 1);
        String result = renderer.render(stats);
        assertEquals("<ul><li>Downloads: 10</li><li>PageViews: 100</li><li>UniqueVisitors: 1</li></ul>", result);
    }
}
