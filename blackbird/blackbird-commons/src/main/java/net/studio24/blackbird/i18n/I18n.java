package net.studio24.blackbird.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides message interface instances for the current default locale.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public interface I18n {

    static final Logger LOGGER = LoggerFactory.getLogger(I18n.class);
    static final Map<Class<?>, Object> CACHE = new HashMap<>();

    /**
     * Creates a new instance of the provides interface which is backed by a
     * property file. The instantiation requires that the property filename is
     * equal to the interface class name and for each method there must be a
     * property with the name of the method. Note that interface instances are
     * cached.<br>
     * <br>
     * Examples:<br>
     * ClassName: MyClass, Locale: ENGLISH or ROOT<br>
     * Property file: MyClass.properties<br>
     * ClassName: MyClass, Locale: GERMAN<br>
     * Property file: MyClass.de.properties
     * 
     * @param messageInterface
     *            the interface that defines the messages
     * @return the interface instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T from(Class<T> messageInterface) {
        if (messageInterface == null) {
            throw new IllegalArgumentException("Message interface must not be null.");
        }
        Object cachedInstance = CACHE.get(messageInterface);
        if (cachedInstance == null) {
            LOGGER.debug("Try creation of new message proxy for interface " + messageInterface.getName());
            Optional<Object> optInstance = tryBuildProxyInstance(messageInterface, Locale.getDefault());
            if (!optInstance.isPresent()) {
                optInstance = tryBuildProxyInstance(messageInterface, Locale.ROOT);
                if (!optInstance.isPresent()) {
                    throw new IllegalArgumentException(
                            "Message interface must have an associated property file with equal name.");
                }
            }
            cachedInstance = optInstance.get();
            CACHE.put(messageInterface, cachedInstance);
        }
        return (T) cachedInstance;
    }

    /**
     * Clears all cached message instance proxies.
     */
    public static void reset() {
        CACHE.clear();
    }

    static Optional<Object> tryBuildProxyInstance(Class<?> messageInterface, Locale locale) {
        String propertyFileName = getLocalizedMessageFilename(messageInterface, locale);
        try (InputStream fileStream = messageInterface.getResourceAsStream(propertyFileName)) {
            if (fileStream == null) {
                return Optional.empty();
            }
            Properties messages = new Properties();
            messages.load(fileStream);
            return Optional.of(Proxy.newProxyInstance(
                    messageInterface.getClassLoader(),
                    new Class<?>[] { messageInterface },
                    new I18nMessagesInvocationHandler(messages)));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read message property file", e);
        }
    }

    static String getLocalizedMessageFilename(Class<?> messageInterface, Locale locale) {
        if (Locale.ROOT.equals(locale) || Locale.ENGLISH.equals(locale)) {
            return String.join("", messageInterface.getSimpleName(), ".properties");
        }
        return String.join("", messageInterface.getSimpleName(), ".", locale.getLanguage(), ".properties");
    }

    static class I18nMessagesInvocationHandler implements InvocationHandler {

        private final Properties messages;

        public I18nMessagesInvocationHandler(Properties messages) {
            this.messages = messages;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String propertyName = method.getName();
            String propertyValue = messages.getProperty(propertyName);
            if (propertyValue == null) {
                throw new IllegalStateException(
                        "The messages file does not contain a property for the message " + propertyName);
            }
            return propertyValue;
        }
    }

}
