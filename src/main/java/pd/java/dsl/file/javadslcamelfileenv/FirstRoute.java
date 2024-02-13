// Import necessary packages
package pd.java.dsl.file.javadslcamelfileenv;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// Declare the class as a Spring component and specify property source
@Component
@PropertySource("classpath:application.properties")
public class FirstRoute extends RouteBuilder {

    // Injecting property values from application.properties using Spring's @Value annotation
    @Value("${route1.source.directory}")
    private String sourceDirectory;
    @Value("${route1.hyderabad.directory}")
    private String hyderabadDirectory;
    @Value("${route1.delhi.directory}")
    private String delhiDirectory;
    @Value("${route1.mumbai.directory}")
    private String mumbaiDirectory;
    @Value("${route1.other.directory}")
    private String otherDirectory;

    /**
     * Override the configure method of RouteBuilder to define Camel routes
     * Define Camel route
     * Listen for files in the specified source directory
     * Set route ID for identification
     * Modify the body of the message
     * If body contains "hyderabad",Save the file to Hyderabad directory with timestamp
     * If the condition is not met, Save the file to other directory with timestamp.
     */
    @Override
    public void configure() throws Exception {

        from(sourceDirectory)  
                .routeId("route1")  
                .setBody(simple("${body} is a very good place."))
                .choice()
                .when(body().contains("hyderabad"))  
                .to("file:" + hyderabadDirectory + "?fileName=${date:now:yyyyMMdd_HHmmss}_${file:name}")
                .otherwise()  
                .to("file:" + otherDirectory + "?fileName=${date:now:yyyyMMdd_HHmmss}_${file:name}");  
    }
}
