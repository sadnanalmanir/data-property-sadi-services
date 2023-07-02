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


@Name("computeBMIFail")
@Description("Testing string data property value with failure: Compute BMI from height and weight")
@ContactEmail("sadnanalmanir@gmail.com")
@InputClass("http://localhost:8080/ontology-data/service-ontology/computeBMIFail.owl#Input")
@OutputClass("http://localhost:8080/ontology-data/service-ontology/computeBMIFail.owl#Output")
public class ComputeBMIFail extends SimpleSynchronousServiceServlet {

    private static final Logger log = Logger.getLogger(ComputeBMIFail.class);

    public void processInput(Resource input, Resource output) {

        PropertyConfigurator.configure(log.getClass().getClassLoader().getResource("log4j.properties"));
        
        log.info("Service invoked: computeBMIFail");

        // Identify the RDF node for mass:
        Statement massStatement = input.getProperty(Vocab.has_mass);
        Resource mass = massStatement.getResource();

        // Read the mass value in kg:
        Statement massValueStatement = mass.getProperty(Vocab.HAS_VALUE);
        float massValue = massValueStatement.getFloat();

        // Identify the RDF node for height:
        Statement heightStatement = input.getProperty(Vocab.has_height);
        Resource height = heightStatement.getResource();

        // Read the height value in metres:
        Statement heightValueStatement = height.getProperty(Vocab.HAS_VALUE);
        float heightValue = heightValueStatement.getFloat();

        // Compute the BMI:
        float bmiValue = massValue/(heightValue * heightValue);

        // Form the output RDF:
        output.addLiteral(Vocab.has_BMI,bmiValue);
        
        log.info("All done.");
    }

    @SuppressWarnings("unused")
    private static final class Vocab {

        private static final Model m_model = ModelFactory.createDefaultModel();

        public static final Property HAS_VALUE = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#HAS_VALUE");        
        public static final Property has_BMI = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_BMI");        

        public static final Property type = m_model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
        
        public static final Property has_height = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_height");        
        public static final Property has_mass = m_model.createProperty("http://localhost:8080/ontology-data/domain-ontology.owl#has_mass");        
                
        public static final Resource Person = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#Person");        
        public static final Resource Measurement = m_model.createResource("http://localhost:8080/ontology-data/domain-ontology.owl#Measurement");        
        
        public static final Resource Input = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/computeBMIFail.owl#Input");
        public static final Resource Output = m_model.createResource("http://localhost:8080/ontology-data/service-ontology/computeBMIFail.owl#Output");
    }
}