package uk.ac.rothamsted.ide.data.property.sadi.services;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.sadiframework.service.annotations.*;
import org.sadiframework.service.simple.SimpleSynchronousServiceServlet;

@Name("getGreetingFail")
@Description("Testing string data property value with failure: Greet a person by his/her first name")
@ContactEmail("sadnanalmanir@gmail.com")
@InputClass("http://localhost:8080/ontology-data/service-ontology/getGreetingFail.owl#Input")
@OutputClass("http://localhost:8080/ontology-data/service-ontology/getGreetingFail.owl#Output")
public class GetGreetingFail extends SimpleSynchronousServiceServlet {

    private static final Logger log = Logger.getLogger(GetGreetingFail.class);

    public void processInput(Resource input, Resource output) {

        Model outputModel = output.getModel();

        PropertyConfigurator.configure(log.getClass().getClassLoader().getResource("log4j.properties"));
        
        log.info("Service invoked: getGreetingFail");

        
        Statement firstNameStmt = input.getProperty(Vocab.has_first_name);
        
        Resource firstNameRes = firstNameStmt.getResource();

        Statement firstNameValueStmt  = firstNameRes.getProperty(Vocab.HAS_VALUE);
        
        String firstName = firstNameValueStmt.getString();

        
        String greeting  = "Hi, " + firstName;
        log.info("greeting : " + greeting );

        if (firstName == "")
            output.addLiteral(Vocab.greeted_as, "Hi Anonymous!");
        else
            output.addLiteral(Vocab.greeted_as, greeting);
        
        log.info("All done.");
    }

    @SuppressWarnings("unused")
    private static final class Vocab {

        private static final Model m_model = ModelFactory.createDefaultModel();

        public static final Property HAS_VALUE = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#HAS_VALUE");        
        public static final Property REPRESENTS_VALUE = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#REPRESENTS_VALUE");
        public static final Property greeted_as  = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#greeted_as");
        public static final Resource FirstName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#FirstName");
        public static final Resource LastName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#LastName");
        public static final Resource FullName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#FullName");        
        public static final Property type = m_model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        public static final Property has_first_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_first_name");        
        public static final Property has_last_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_last_name");        
        public static final Property has_full_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_full_name");        
        public static final Resource Input = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getGreetingFail.owl#Input");
        public static final Resource Output = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getGreetingFail.owl#Output");
    }
}