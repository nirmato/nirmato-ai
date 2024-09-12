package org.nirmato.ai.data.messages

import org.nirmato.ai.model.Content

public class UserMessage(
    public val contents: Collection<Content>,
) : Message(MessageType.USER)
