package cn.printf.practise.basic.network.webserver.xml;

public class MappingElementHandler implements ElementHandler<MappingElement> {

    @Override
    public MappingElement startElement() {
        return new MappingElement();
    }

    @Override
    public void handleChildValue(String qName, String value, MappingElement mappingElement) {
        switch (qName) {
            case "name":
                mappingElement.setName(value);
                break;
            case "url-pattern":
                mappingElement.setUrlPattern(value);
                break;
        }
    }
}
