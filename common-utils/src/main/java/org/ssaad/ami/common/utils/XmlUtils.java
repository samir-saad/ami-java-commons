package org.ssaad.ami.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class XmlUtils {
    private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    public static void validateXML(String xml, String xsd) throws SAXException, IOException {
        validateXML(new StreamSource(new StringReader(xml)), new StreamSource(new StringReader(xsd)));
    }

    public static void validateXML(String xml, File xsd) throws SAXException, IOException {
        validateXML(new StreamSource(new StringReader(xml)), new StreamSource(xsd));
    }

    public static void validateXML(String xml, InputStream xsd) throws SAXException, IOException {
        validateXML(new StreamSource(new StringReader(xml)), new StreamSource(xsd));
    }

    public static void validateXML(Source xml, Source xsd) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(xsd);
        Validator validator = schema.newValidator();
        validator.validate(xml);
    }

    public static <T> T unmarshal(String xml, Class<T> objectClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }
}
