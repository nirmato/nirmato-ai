//package model.language
//
//import kotlin.test.Test
//import kotlin.test.assertTrue
//import kotlinx.coroutines.test.runTest
//import model.input.Prompt
//import org.nirmato.ai.ollama.model.language.model.language.OllamaLanguageModel
//import org.nirmato.ollama.client.OllamaClient
//
//class OllamaLanguageModelTest {
//
//    private val sut = OllamaLanguageModel(OllamaClient { })
//
//    @Test
//    fun generate_validRequest_returnsChatResponse() = runTest {
//        val ollamaClient = OllamaClient {
//            // empty
//        }
//
//        val userMessage = "What is the capital of Germany?"
//
//        val response = sut.generate(Prompt(userMessage))
//
//        // assertTrue(response.content.contains("Berlin"))
//        println("response: ${response.metadata}")
//    }
//}
