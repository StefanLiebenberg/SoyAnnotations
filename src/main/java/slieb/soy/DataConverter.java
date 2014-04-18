package slieb.soy;


import com.google.inject.Inject;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.context.JsonDataFactoryContext;
import slieb.soy.context.RendererFactoryContext;
import slieb.soy.context.SoyDataFactoryContext;

public class DataConverter {

    private final JsonDataFactoryContext jsonDataFactoryContext;
    private final SoyDataFactoryContext soyDataFactoryContext;
    private final RendererFactoryContext rendererFactoryContext;

    @Inject
    public DataConverter(JsonDataFactoryContext jsonDataFactoryContext, SoyDataFactoryContext soyDataFactoryContext, RendererFactoryContext rendererFactoryContext) {
        this.jsonDataFactoryContext = jsonDataFactoryContext;
        this.soyDataFactoryContext = soyDataFactoryContext;
        this.rendererFactoryContext = rendererFactoryContext;
    }

    public SoyData getSoyData(Object instanceObject) {
        return soyDataFactoryContext.convert(instanceObject);
    }

    public SoyMapData getSoyMapData(Object instanceObject) {
        return soyDataFactoryContext.getSoyMapData(instanceObject);
    }

    public Object getJsonData(Object instanceObject) {
        return jsonDataFactoryContext.convert(instanceObject);
    }

    public String getRenderString(Object object) {
        return rendererFactoryContext.render(object);
    }
}
