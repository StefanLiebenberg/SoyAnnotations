package slieb.soy.meta;


import javax.annotation.concurrent.Immutable;
import java.util.Map;

@Immutable
public class MetaClassInformation extends MetaValueConvertableInformation {

    private final Map<String, MetaMemberInformation> membersInformation;

    public MetaClassInformation(Boolean dynamic, Class<?> type, MetaConverter valueConverter, Map<String, MetaMemberInformation> membersInformation) {
        super(dynamic, type, valueConverter);
        this.membersInformation = membersInformation;
    }

    public final Map<String, MetaMemberInformation> getMembersInformation() {
        return membersInformation;
    }
}
