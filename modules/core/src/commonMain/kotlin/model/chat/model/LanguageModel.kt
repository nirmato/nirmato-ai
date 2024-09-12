package org.nirmato.ai.chat.model

import model.input.Prompt

public interface LanguageModel {
    public suspend fun generate(prompt: Prompt): ChatResponse
}
