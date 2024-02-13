package pd.java.dsl.file.javadslcamelfileenv;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
// Declare the class as a Spring component and specify property source
@Component
@PropertySource("classpath:application.properties")
public class SecondRoute extends RouteBuilder {

    // Injecting properties from application.properties file
    @Value("${route2.source.directory}")
    private String sourceDirectory;
    @Value("${route2.hyderabad.directory}")
    private String hyderabadDirectory;
    @Value("${route2.delhi.directory}")
    private String delhiDirectory;
    @Value("${route2.mumbai.directory}")
    private String mumbaiDirectory;
    @Value("${route2.other.directory}")
    private String otherDirectory;
    /**
         * Override the configure method of RouteBuilder to define Camel routes
         * Define Camel route
         * Listen for files in the specified source directory
         * Set route ID for identification
         * Modify the body of the message
         * If body contains "hyderabad",Save the file to Hyderabad directory with timestamp
         *  If body contains "delhi",Save the file to delhi directory with timestamp
         *  If body contains "mumbai",Save the file to mumbai directory with timestamp
         * If the condition is not met, Save the file to other directory with timestamp.
     */
    @Override
    public void configure() throws Exception {
        from("file:"+sourceDirectory + "?noop=true")
                .routeId("route2")
                .setBody(simple("${body} is a very good place."))
                .choice()
                .when(body().contains("hyderabad"))
                .to("file:"+hyderabadDirectory + "?fileName=${date:now:yyyyMMdd_HHmmss}_${file:name}")
                .choice()
                .when(body().contains("delhi"))
                .to("file:"+delhiDirectory + "?fileName=${date:now:yyyyMMdd_HHmmss}_${file:name}")
                .choice()
                .when(body().contains("mumbai"))
                .to("file:"+mumbaiDirectory + "?fileName=${date:now:yyyyMMdd_HHmmss}_${file:name}")
                .otherwise()
                .to("file:"+otherDirectory + "?fileName=${date:now:yyyyMMdd_HHmmss}_${file:name}");
    }
}
