/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 348736794
 */
public class LackingFactsArticle extends NewsArticle {
    private String explanation = "This article tries to pass off the author's opinion as fact. There is no evidence to support the content of the article.";
    
    public LackingFactsArticle(String headline, String content, boolean validity, String additionalInformation){
        super(headline, content, validity, additionalInformation);
    }
    
    public LackingFactsArticle(String headline, String content, boolean validity){
        super(headline, content, validity);
    }
    
    @Override
    public String getExplanation(){
        return explanation;
    }

}
