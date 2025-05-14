/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 348736794
 */
public class MisusedSourceArticle extends NewsArticle {
 
    private String explanation =  "This article is misleading because the source used does not support the conclusion being made.";
    
    public MisusedSourceArticle(String headline, String content, boolean validity, String additionalInformation){
        super(headline, content, validity, additionalInformation);
    }
    
    public MisusedSourceArticle(String headline, String content, boolean validity){
        super(headline, content, validity);
    }
    
    @Override
    public String getExplanation(){
        return explanation;
    }
}
