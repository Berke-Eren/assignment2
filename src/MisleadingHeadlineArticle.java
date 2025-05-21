/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * A type of NewsArticle that is misleading because the headline is misleading.
 * 
 * @author Zi Cheng Qiu
 * @version 1.0
 * @since 2025-05-21
 */
public class MisleadingHeadlineArticle extends NewsArticle {
    // Constant that explains why the article is misleading.
    private final String explanation =  "The headline in this article is misleading because the content of the article does not support it.";
    
    /**
     * A parameterized constructor that creates an article that has all the necessary 
     * information along with additional context.
     * 
     * @param headline the headline of the article
     * @param content the content of the article
     * @param validity whether the article is real or fake
     * @param additionalInformation any additional context that may be needed to determine whether the article is real or fake
     */
    public MisleadingHeadlineArticle(String headline, String content, boolean validity, String additionalInformation){
        super(headline, content, validity, additionalInformation);
    }
    
    /**
     * A parameterized constructor that creates an article with the necessary 
     * information, with no additional context provided.
     * 
     * @param headline the headline of the article
     * @param content the content of the article
     * @param validity whether the article is real or fake
     */
    public MisleadingHeadlineArticle(String headline, String content, boolean validity){
        super(headline, content, validity);
    }
    
    /**
     * Returns the explanation as to why this article is fake.
     * 
     * @return an explanation about why the article is misleading
     */
    @Override
    public String getExplanation(){
        return explanation;
    }
}
