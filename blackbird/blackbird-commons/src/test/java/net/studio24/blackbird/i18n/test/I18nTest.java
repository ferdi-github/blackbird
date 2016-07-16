package net.studio24.blackbird.i18n.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.studio24.blackbird.i18n.I18n;

public class I18nTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    public void testMessagesDefault() {
        Locale.setDefault(Locale.ENGLISH);
        I18n.reset();
        TestMessages messagesProxy = I18n.from(TestMessages.class);
        assertThat(messagesProxy).isNotNull();
        assertThat(messagesProxy.message()).isEqualTo("Message");
        assertThat(messagesProxy.messageCamelCase()).isEqualTo("Message Camel Case");
        assertThat(messagesProxy.parameterizedMessage("abc", Integer.valueOf(123)))
                .isEqualTo("StringVal=abc, IntVal=123");
    }

    @Test
    public void testMessagesLocalized() {
        Locale.setDefault(Locale.GERMAN);
        I18n.reset();
        TestMessages messagesProxy = I18n.from(TestMessages.class);
        assertThat(messagesProxy).isNotNull();
        assertThat(messagesProxy.message()).isEqualTo("Message (de)");
        assertThat(messagesProxy.messageCamelCase()).isEqualTo("Message Camel Case (de)");
    }

    @Test
    public void testMissingProperty() {
        Locale.setDefault(Locale.ENGLISH);
        I18n.reset();
        TestMessages messagesProxy = I18n.from(TestMessages.class);
        assertThat(messagesProxy).isNotNull();
        exceptions.expect(IllegalStateException.class);
        messagesProxy.missingMessage();
    }

    @Test
    public void testUnbackedInterface() {
        Locale.setDefault(Locale.ENGLISH);
        I18n.reset();
        exceptions.expect(IllegalArgumentException.class);
        I18n.from(TestMessagesNoFile.class);
    }

}
