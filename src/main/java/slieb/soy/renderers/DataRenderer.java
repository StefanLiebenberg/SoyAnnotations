package slieb.soy.renderers;

import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.tofu.SoyTofu;
import slieb.soy.factories.rendering.Renderer;

import javax.annotation.Nullable;
import java.util.Set;

public class DataRenderer implements Renderer<SoyValue> {

    private final SoyTofu tofu;

    private final Set<String> activePackages;

    private final String templateName;

    public DataRenderer(SoyTofu tofu,
                        String templateName,
                        Set<String> activePackages) {
        this.tofu = tofu;
        this.templateName = templateName;
        this.activePackages = activePackages;
    }

    private SoyTofu.Renderer internalRenderer;

    private SoyTofu.Renderer getRenderer() {
        if (internalRenderer == null) {
            internalRenderer = tofu
                    .newRenderer(templateName)
                    .setActiveDelegatePackageNames(activePackages);
        }
        return internalRenderer;
    }

    @Nullable
    @Override
    public String render(@Nullable SoyValue data) {
        return getRenderer().setData((SoyMapData) data).render();
    }
}
