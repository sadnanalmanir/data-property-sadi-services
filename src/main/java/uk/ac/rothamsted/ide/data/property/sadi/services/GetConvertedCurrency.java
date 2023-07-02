package uk.ac.rothamsted.ide.data.property.sadi.services;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.sadiframework.service.annotations.*;
import org.sadiframework.service.simple.SimpleSynchronousServiceServlet;


@Name("getConvertedCurrency")
@Description("Testing float data property value successfully: Get USD conversed into CAD")
@ContactEmail("sadnanalmanir@gmail.com")
@InputClass("http://localhost:8080/ontology-data/service-ontology/getConvertedCurrency.owl#Input")
@OutputClass("http://localhost:8080/ontology-data/service-ontology/getConvertedCurrency.owl#Output")
public class GetConvertedCurrency extends SimpleSynchronousServiceServlet {

    private static final Logger log = Logger.getLogger(GetConvertedCurrency.class);

    public void processInput(Resource input, Resource output) {

        Model outputModel = output.getModel();

        PropertyConfigurator.configure(log.getClass().getClassLoader().getResource("log4j.properties"));
        
        log.info("Service invoked: getConvertedCurrency");

        // Extracting literals (lexical form and type) from the input
        //String disability = input.getRequiredProperty(Vocab.HAS_VALUE).getLiteral().getLexicalForm();
        float amountInUSD = input.getRequiredProperty(Vocab.HAS_VALUE).getFloat();
        log.info("amoutn in USD: " + amountInUSD);

        float usdToCadRate = 1.3f;
        
        float amountInCAD = amountInUSD * usdToCadRate;

        output.addLiteral(Vocab.converted_to, amountInCAD);
        
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
        public static final Resource AmountInUSD = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#AmountInUSD");        
        public static final Resource AmountInCAD = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#AmountInCAD");        
        public static final Property type = m_model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        public static final Property has_first_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_first_name");        
        public static final Property has_last_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_last_name");        
        public static final Property has_full_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_full_name");        
        public static final Resource Input = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getConvertedCurrency.owl#Input");
        public static final Resource Output = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getConvertedCurrency.owl#Output");
    }
}