package slieb.soy.context;


import com.google.inject.Inject;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

public class Context {

    private final JsonDataFactoryContext jsonDataFactoryContext;
    private final SoyDataFactoryContext soyDataFactoryContext;
    private final RendererFactoryContext rendererFactoryContext;

    @Inject
    public Context(JsonDataFactoryContext jsonDataFactoryContext, SoyDataFactoryContext soyDataFactoryContext, RendererFactoryContext rendererFactoryContext) {
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
