/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 348736794
 */
public class AppealToEmotionsArticle extends NewsArticle {
    private String explanation = "This article is appealing to emotions rather than basing their ideas on fact. This is a common technique used in misinformation.";
    
    public AppealToEmotionsArticle(String headline, String content, boolean validity, String additionalInformation){
        super(headline, content, validity, additionalInformation);
    }
    
    public AppealToEmotionsArticle(String headline, String content, boolean validity){
        super(headline, content, validity);
    }
    
    @Override
    public String getExplanation(){
        return explanation;
    }
}
