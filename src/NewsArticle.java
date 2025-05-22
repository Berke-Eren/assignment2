    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Stores the information contained in a news article including its headline, content,
 * any additional context, and whether the article is real or fake.
 * 
 * @author Zi Cheng Qiu
 * @version 1.0
 * @since 2025-05-21
 */
public class NewsArticle {
    // Declare instance variables.
    private String headline;
    private String content;
    private String additionalInformation;
    private boolean validity;
    private final String DEFAULT_HEADLINE = "No Headline";
    private final String DEFAULT_CONTENT = "No Content";
    private final boolean DEFAULT_VALIDITY = true;
    // Declare and initialize class variable.
    private static int numNewsArticles = 0;
    
    /**
     * A parameterized constructor that creates an article that has all the necessary 
     * information along with additional context.
     * 
     * @param headline the headline of the article
     * @param content the content of the article
     * @param validity whether the article is real or fake
     * @param additionalInformation any additional context that may be needed to determine whether the article is real or fake
     */
    public NewsArticle(String headline, String content, boolean validity, String additionalInformation){
        this.headline = headline;
        this.content = content;
        this.validity = validity;
        this.additionalInformation = additionalInformation;
        numNewsArticles++;
    }
    
    /**
     * A parameterized constructor that creates an article with the necessary 
     * information, with no additional context provided.
     * 
     * @param headline the headline of the article
     * @param content the content of the article
     * @param validity whether the article is real or fake
     */
    public NewsArticle(String headline, String content, boolean validity){
        this.headline = headline;
        this.content = content;
        this.validity = validity;
        this.additionalInformation = "";
        numNewsArticles++;
    }
    
    /**
     * A constructor with no parameters. Creates an article with default values.
     */
    public NewsArticle(){
        this.headline = DEFAULT_HEADLINE;
        this.content = DEFAULT_CONTENT;
        this.validity = DEFAULT_VALIDITY;
        this.additionalInformation = "";
        numNewsArticles++;

    }
    
    /**
     * Gets the headline of the article.
     * 
     * @return the headline of the article
     */
    public String getHeadline(){
        return headline;
    }
    
    /**
     * Gets the content of the article.
     * 
     * @return the content of the article
     */
    public String getContent(){
        return content;
    }
    
    /**
     * Gets any additional information needed to determine whether the article is real or fake.
     * 
     * @return the additional information 
     */
    public String getAdditionalInformation(){
        return additionalInformation;
    }
    
    /**
     * Gets an explanation of why the article is real or fake.
     * 
     * @return the explanation regarding the validity of the article
     */
    public String getExplanation() {
        if (validity) {
            return "This article is not misleading. The claims are supported by facts";
        } else {
            return "This article is misleading";
        }
    }
    
    /**
     * Checks whether the article is real or fake.
     * 
     * @return the validity of the article 
     */
    public boolean isValid(){
        return validity;
    }
    
    /**
     * Gets the total number of news articles created.
     * 
     * @return the total number of news articles
     */
    public static int getNumNewsArticles(){
        return numNewsArticles;
    }
}
