package net.studio24.blackbird.i18n.test;

public interface TestMessages {

    String message();

    String messageCamelCase();

    String parameterizedMessage(String stringVal, Integer intVal);

    String missingMessage();

}
