/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 348736794
 */
public class MisleadingHeadlineArticle extends NewsArticle {
    private String explanation =  "The headline in this article is misleading because the content of the article does not support it.";
    
    public MisleadingHeadlineArticle(String headline, String content, boolean validity, String additionalInformation){
        super(headline, content, validity, additionalInformation);
    }
    
    public MisleadingHeadlineArticle(String headline, String content, boolean validity){
        super(headline, content, validity);
    }
    
    @Override
    public String getExplanation(){
        return explanation;
    }
}
