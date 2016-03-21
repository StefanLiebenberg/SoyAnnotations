package example.models;

import example.renderer.SettingsRenderer;
import org.slieb.soy.annotations.CustomRenderer;
import org.slieb.soy.annotations.Soy;
import org.slieb.soy.context.RendererFactoryContext;

import java.util.Collection;

import static java.lang.String.format;

@Soy
@CustomRenderer(SettingsRenderer.class)
public class Settings extends Setting<Boolean> {

    @Soy.Field("GeneralSettings")
    public Collection<Setting<?>> GeneralSettings;

    @Soy.Field("SpecialSettings")
    public Collection<Setting<?>> SpecialSettings;


    private String renderSettingsCollection(RendererFactoryContext factoryContext, Collection<Setting<?>> settings) {
        StringBuilder stringBuilder = new StringBuilder();
        if (settings != null) {
            stringBuilder.append("[");
            String delim = "";

            for (Setting setting : settings) {
                stringBuilder.append(delim).append(factoryContext.render(setting));
                delim = ",";
            }
            stringBuilder.append("]");
        } else {
            stringBuilder.append("null");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString(RendererFactoryContext factoryContext) {
        return format("{ %s : %s, GeneralSettings: %s, SpecialSettings: %s}",
                Name,
                factoryContext.render(Value),
                renderSettingsCollection(factoryContext, GeneralSettings),
                renderSettingsCollection(factoryContext, SpecialSettings));
    }
}

