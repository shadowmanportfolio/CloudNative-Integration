package cn.app.cloudnative.demo;

import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.springframework.stereotype.Component;

/**
 * A POJO that listen to messages from the seda:numbers endpoint via {@link Consume}
 * and then via {@link MagicNumber} and {@link Produce} sends a message that will
 * be printed in the console.
 */
@Component
public class NumberPojo {

    // sends the message to the stream:out endpoint but hidden behind this interface
    // so the client java code below can use the interface method instead of Camel's
    // FluentProducerTemplate or ProducerTemplate APIs
    @Produce("stream:out")
    private MagicNumber magic;

    // only consume when the predicate matches, eg when the message body is lower than 100
    @Consume(value = "direct:numbers", predicate = "${body} < 100")
    public void lowNumber(int number) {
        magic.onMagicNumber("Got a low number " + number);
    }

    // only consume when the predicate matches, eg when the message body is higher or equal to 100
    @Consume(value = "direct:numbers", predicate = "${body} >= 100")
    public void highNumber(int number) {
        magic.onMagicNumber("Got a high number " + number);
    }

}
