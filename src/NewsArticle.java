/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 348736794
 */
public class NewsArticle {
    private String headline;
    private String content;
    private String additionalInformation;
    private boolean validity;
    private int numNewsArticles = 0;
    private String DEFAULT_HEADLINE = "No Headline";
    private String DEFAULT_CONTENT = "No Content";
    private boolean DEFAULT_VALIDITY = true;
    
    public NewsArticle(String headline, String content, boolean validity, String additionalInformation){
        this.headline = headline;
        this.content = content;
        this.validity = validity;
        this.additionalInformation = additionalInformation;
        numNewsArticles++;
    }
    
    public NewsArticle(String headline, String content, boolean validity){
        this.headline = headline;
        this.content = content;
        this.validity = validity;
        this.additionalInformation = "";
        numNewsArticles++;

    }
    
    public NewsArticle(){
        this.headline = DEFAULT_HEADLINE;
        this.content = DEFAULT_CONTENT;
        this.validity = DEFAULT_VALIDITY;
        this.additionalInformation = "";
        numNewsArticles++;

    }
    
    public String getHeadline(){
        return headline;
    }
    
    public String getContent(){
        return content;
    }
    
    public String getAdditionalInformation(){
        return additionalInformation;
    }
    
    public String getExplanation() {
        if (validity) {
            return "This article is not misleading. The claims are supported by facts";
        } else {
            return "This article is misleading";
        }
    }
    
    public boolean isValid(){
        return validity;
    }
    
    public int getNumNewsArticles(){
        return numNewsArticles;
    }
}
