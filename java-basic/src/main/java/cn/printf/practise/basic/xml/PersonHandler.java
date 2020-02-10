package cn.printf.practise.basic.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PersonHandler extends DefaultHandler {

    private List<Person> persons = new ArrayList<>();
    private Person person;
    private String tag;

    @Override
    public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println("处理元素开始："+ qName);

        if (null != qName) {
            tag = qName;
        }

        if ("person".equals(qName)) {
            person = new Person();
        }
    }

    @Override
    public void endElement(java.lang.String uri, java.lang.String localName, java.lang.String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("处理元素结束："+ qName);

        tag = null;
        if ("person".equals(qName)) {
            persons.add(person);
            person = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        System.out.println("处理元素值："+ new String(ch, start, length));

        if (null == person || null == tag) {
            return;
        }

        if (tag.equals("name")) {
            person.setName(new String(ch, start, length));
            return;
        }
        if (tag.equals("age")) {
            person.setAge(Integer.parseInt(
                    new String(ch, start, length)
            ));
            return;
        }
    }

    public List<Person> getPersons() {
        return persons;
    }
}
