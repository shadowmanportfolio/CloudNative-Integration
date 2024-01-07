package cn.app.cloudnative.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A bean that returns a message when you call the {@link #saySomething()} method.
 * <p/>
 * Uses <tt>@Component("myBean")</tt> to register this bean with the name <tt>myBean</tt>
 * that we use in the Camel route to lookup this bean.
 */
@Component("myBean")
public class MySpringBean {

    private int counter;

    @Value("${greeting}")
    private String say;

    public String saySomething() {
        return String.format("%s I an invoked %d times", say, ++counter);
    }

}
