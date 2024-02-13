package pd.java.dsl.file.javadslcamelfileenv;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the Java DSL Camel File Environment application.
 * Start the Spring Boot application
 * Create a new Camel context
 * Add routes to the Camel context
 * Start the Camel context
 * Stop the Camel context
 */
@SpringBootApplication
public class JavaDslCamelFileEnvApplication {
    public static void main(String[] args) throws Exception {
       
        SpringApplication.run(JavaDslCamelFileEnvApplication.class, args);

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new FirstRoute());
        context.addRoutes(new SecondRoute());

        context.start();

        Thread.sleep(10000);

        context.stop();
    }
}
