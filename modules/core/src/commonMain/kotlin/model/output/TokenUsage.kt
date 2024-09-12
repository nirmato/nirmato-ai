package model.output

/**
 * Represents the token usage of a response.
 */
public class TokenUsage (
    /** The number of tokens that were consumed. */
    public val inputTokenCount: Int = 0,
    /** The number of tokens that were generated. */
    public val outputTokenCount: Int = 0,
    /** The total number of tokens. */
    public val tokenCount: Int = 0,
) {
    public operator fun plus(other: TokenUsage): TokenUsage {
        return TokenUsage(
            inputTokenCount = this.inputTokenCount + other.inputTokenCount,
            outputTokenCount = this.outputTokenCount + other.outputTokenCount,
            tokenCount = this.tokenCount + other.tokenCount
        )
    }
}
