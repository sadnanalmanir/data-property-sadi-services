package uk.ac.rothamsted.ide.data.property.sadi.services;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.sadiframework.service.annotations.*;
import org.sadiframework.service.simple.SimpleSynchronousServiceServlet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Name("getGenerationName")
@Description("Testing dateTime data property value successfully: Get name of the generation from Birthday")
@ContactEmail("sadnanalmanir@gmail.com")
@InputClass("http://localhost:8080/ontology-data/service-ontology/getGenerationName.owl#Input")
@OutputClass("http://localhost:8080/ontology-data/service-ontology/getGenerationName.owl#Output")
public class GetGenerationName extends SimpleSynchronousServiceServlet {

    private static final Logger log = Logger.getLogger(GetConvertedCurrency.class);

    public void processInput(Resource input, Resource output) {

        Model outputModel = output.getModel();

        PropertyConfigurator.configure(log.getClass().getClassLoader().getResource("log4j.properties"));

        log.info("Service invoked: getGenerationName");

        String dateTimeValue = input.getRequiredProperty(Vocab.HAS_VALUE).getLiteral().getLexicalForm();
        log.info("BirthDay: " + dateTimeValue);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDate date = LocalDate.parse(dateTimeValue, formatter);

        String generation;

        if (date.isBefore(LocalDate.of(1900, 1, 1))) {
            generation = "Invalid Birthday"; // Invalid date, before known generations
        } else if (date.isBefore(LocalDate.of(1925, 1, 1))) {
            generation = "Greatest"; // Invalid date, before known generations
        } else if (date.isBefore(LocalDate.of(1946, 1, 1))) {
            generation = "Silent"; // Silent Generation
        } else if (date.isBefore(LocalDate.of(1966, 1, 1))) {
            generation = "Baby boomers"; // Baby Boomers
        } else if (date.isBefore(LocalDate.of(1980, 1, 1))) {
            generation = "Gen X"; // Generation X
        } else if (date.isBefore(LocalDate.of(1995, 1, 1))) {
            generation = "Gen Y"; // Millennials (Generation Y)
        } else if (date.isBefore(LocalDate.of(2016, 1, 1))) {
            generation = "Gen Z"; // Generation Z
        } else {
            generation = "Gen Alpha"; // Generation Alpha
        }

        output.addLiteral(Vocab.has_generation_name, generation);

        log.info("All done.");
    }

    @SuppressWarnings("unused")
    private static final class Vocab {

        private static final Model m_model = ModelFactory.createDefaultModel();

        public static final Property HAS_VALUE = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#HAS_VALUE");
        public static final Property REPRESENTS_VALUE = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#REPRESENTS_VALUE");
        public static final Property greeted_as  = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#greeted_as");
        public static final Property years_old  = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#years_old");
        public static final Property converted_to  = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#converted_to");
        public static final Resource FirstName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#FistName");
        public static final Resource LastName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#LastName");
        public static final Resource FullName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#FullName");
        public static final Resource BirthYear = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#BirthYear");
        public static final Resource BirthDay = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#BirthDay");
        public static final Resource AmountInUSD = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#AmountInUSD");
        public static final Resource AmountInCAD = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#AmountInCAD");
        public static final Property type = m_model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        public static final Property has_first_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_first_name");
        public static final Property has_last_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_last_name");
        public static final Property has_full_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_full_name");
        public static final Property has_generation_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_generation_name");
        public static final Resource Input = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getGenerationName.owl#Input");
        public static final Resource Output = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getGenerationName.owl#Output");
    }
}