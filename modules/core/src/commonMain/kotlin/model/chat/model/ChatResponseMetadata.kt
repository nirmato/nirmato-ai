package org.nirmato.ai.chat.model

import model.output.TokenUsage

public class ChatResponseMetadata(
    public val model: String,
    public val tokenUsage: TokenUsage,
)
