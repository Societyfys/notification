package com.societyfy.notification.utils;

import java.util.Map;

public interface MessageFormatter {

    /**
     * Replaces placeholders in the template with actual values.
     *
     * @param values A map of placeholders and their corresponding values.
     * @return The formatted message with placeholders replaced.
     */
    static String format(String formattedMessage, Map<String, Object> values) {
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            formattedMessage = formattedMessage.replace(
                    "{{" + entry.getKey() + "}}",
                    entry.getValue().toString());
        }
        return formattedMessage;
    }
}
