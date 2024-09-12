package org.nirmato.ai.data.messages

import org.nirmato.ai.model.TextContent

public class AssistantMessage(
    public val content: TextContent,
    public val toolCalls: List<ToolCall>,
) : Message(MessageType.ASSISTANT) {

    public class ToolCall(
        public val id: String,
        public val type: String,
        public val name: String,
        public val arguments: String,
    )
}
