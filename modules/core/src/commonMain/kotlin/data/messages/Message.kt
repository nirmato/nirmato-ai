package org.nirmato.ai.data.messages

public abstract class Message(
    public val messageType: MessageType,
    public val metadata: Map<String, String> = mutableMapOf(),
)
