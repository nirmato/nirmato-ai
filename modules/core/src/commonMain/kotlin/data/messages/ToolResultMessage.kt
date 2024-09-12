package org.nirmato.ai.data.messages

public class ToolResultMessage(
    public val toolResponses: List<ToolResult>,
) : Message(MessageType.TOOL) {

    public class ToolResult(
        public val id: String,
        public val name: String,
        public val data: String,
    )
}
