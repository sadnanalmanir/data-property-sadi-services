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

@Name("getFullNameFail")
@Description("Testing string data property value with failure: Greet a person by full named consisting of first and last name")
@ContactEmail("sadnanalmanir@gmail.com")
@InputClass("http://localhost:8080/ontology-data/service-ontology/getFullNameFail.owl#Input")
@OutputClass("http://localhost:8080/ontology-data/service-ontology/getFullNameFail.owl#Output")
public class GetFullNameFail extends SimpleSynchronousServiceServlet {

    private static final Logger log = Logger.getLogger(GetFullNameFail.class);

    public void processInput(Resource input, Resource output) {

        Model outputModel = output.getModel();

        PropertyConfigurator.configure(log.getClass().getClassLoader().getResource("log4j.properties"));
        
        log.info("Service invoked: getFullNameFail");

        
        Statement firstNameStmt = input.getProperty(Vocab.has_first_name);
        
        Resource firstNameRes = firstNameStmt.getResource();

        Statement firstNameValueStmt  = firstNameRes.getProperty(Vocab.HAS_VALUE);
        
        String firstName = firstNameValueStmt.getString();

        Statement lastNameStmt = input.getProperty(Vocab.has_last_name);
        
        Resource lastNameRes = lastNameStmt.getResource();

        Statement lastNameValueStmt  = lastNameRes.getProperty(Vocab.HAS_VALUE);
        
        String lastName = lastNameValueStmt.getString();

        
        String fullname  = "Hi, " + firstName + " "+ lastName;
        log.info("Full name : " + fullname );

        Resource fullNameResource = outputModel.createResource();
        
        fullNameResource.addProperty(Vocab.type, Vocab.FullName);

        fullNameResource.addLiteral(Vocab.HAS_VALUE, fullname);

        output.addProperty(Vocab.has_full_name, fullNameResource);
        
        log.info("All done.");
    }

    @SuppressWarnings("unused")
    private static final class Vocab {

        private static final Model m_model = ModelFactory.createDefaultModel();

        public static final Property HAS_VALUE = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#HAS_VALUE");                
        
        public static final Property type = m_model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        
        public static final Property has_first_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_first_name");        
        public static final Property has_last_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_last_name");        
        public static final Property has_full_name = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_full_name");        
        
        public static final Resource Input = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getFullNameFail.owl#Input");
        public static final Resource Output = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/getFullNameFail.owl#Output");

        public static final Resource FirstName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#FirstName");
        public static final Resource LastName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#LastName");
        public static final Resource FullName = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#FullName");        
        
    }
}