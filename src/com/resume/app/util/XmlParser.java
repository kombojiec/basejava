package com.resume.app.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.Reader;
import java.io.Writer;

public class XmlParser {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public XmlParser (Class...classesToBeBound) {
        try{
            JAXBContext context = JAXBContext.newInstance(classesToBeBound);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public <T> T unmarshall(Reader reader) {
        try{
            return (T)unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public void marshall(Object object, Writer writer) {
        try{
            marshaller.marshal(object, writer);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

}
