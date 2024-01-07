package cn.app.cloudnative.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.camel.builder.endpoint.LambdaEndpointRouteBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBootApplication {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
    
    // embed route directly using type-safe endpoint-dsl using java lambda style
    @Bean
    public LambdaEndpointRouteBuilder myRoute() {
        return rb -> rb
                .from(rb.timer("timer").period(2000))
                    .to(rb.bean("myBean").method("saySomething"))
                    .log("${body}");
    }
}
