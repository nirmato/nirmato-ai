package org.nirmato.ai.data.messages

public enum class MessageType(public val value: String) {
    USER("user"),
    ASSISTANT("assistant"),
    SYSTEM("system"),
    TOOL("tool");

    public companion object {
        public fun fromValue(value: String): MessageType {
            return values().firstOrNull { it.value == value } ?: throw IllegalArgumentException("Unknown message type: $value")
        }

        public fun fromValueOrNull(value: String): MessageType? {
            return values().firstOrNull { it.value == value }
        }
    }
}
