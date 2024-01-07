package cn.app.cloudnative.demo;
/**
 * A interface to hide Camel pojo producer
 */
public interface MagicNumber {
    /**
     * When we have a message with a new number
     */
    void onMagicNumber(String message);
}
