//package org.nirmato.ai.ollama.model.language.model.language
//
//import model.input.Prompt
//import model.output.TokenUsage
//import org.nirmato.ai.chat.model.ChatResponse
//import org.nirmato.ai.chat.model.ChatResponseMetadata
//import org.nirmato.ai.chat.model.LanguageModel
//import org.nirmato.ollama.api.CompletionRequest
//import org.nirmato.ollama.api.CompletionResponse
//import org.nirmato.ollama.client.OllamaClient
//
//public class OllamaLanguageModel(
//    private val ollamaClient: OllamaClient,
//) : LanguageModel {
//
//    public override suspend fun generate(prompt: Prompt): ChatResponse {
//        val completionRequest = prompt.toCompletionRequest()
//
//        val response = ollamaClient.completion(completionRequest)
//
//        return response.toChatResponse()
//    }
//
//    private fun Prompt.toCompletionRequest(): CompletionRequest {
//        return CompletionRequest(
//            model = "tinyllama",
//            prompt = prompt,
//        )
//    }
//
//    private fun CompletionResponse.toChatResponse(): ChatResponse {
//        val chatResponseMetadata = ChatResponseMetadata(
//            model = model ?: "",
//            tokenUsage = TokenUsage(promptEvalCount ?: 0, evalCount ?: 0)
//        )
//
//        return ChatResponse(chatResponseMetadata)
//    }
//}
