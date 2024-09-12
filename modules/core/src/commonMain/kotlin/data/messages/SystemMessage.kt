package org.nirmato.ai.data.messages

import org.nirmato.ai.model.TextContent

public class SystemMessage(
    public val content: TextContent,
) : Message(MessageType.SYSTEM) {
}
