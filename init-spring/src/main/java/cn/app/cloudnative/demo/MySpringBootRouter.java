package cn.app.cloudnative.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:hello?period={{timer.period}}").routeId("hello")
            .transform().method("myBean", "saySomething")
            .filter(simple("${body} contains 'foo'"))
                .to("log:foo")
            .end()
            .to("stream:out");
        from("kamelet:chuck-norris-source").routeId("chuck-norris")
            .log("${body}");
        // generate random number every second
        // which is send to this seda queue that the NumberPojo will consume
        from("timer:number?period=1000").routeId("randomNumbers")
            .transform().simple("${random(0,200)}")
            .to("direct:numbers");
    }

}
