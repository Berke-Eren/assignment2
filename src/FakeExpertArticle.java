/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 348736794
 */
public class FakeExpertArticle extends NewsArticle {
    private String explanation = "This article is based solely on the words of an \"expert\" who is not credible.";
    
    public FakeExpertArticle(String headline, String content, boolean validity, String additionalInformation){
        super(headline, content, validity, additionalInformation);
    }
    
    public FakeExpertArticle(String headline, String content, boolean validity){
        super(headline, content, validity);
    }
    
    @Override
    public String getExplanation(){
        return explanation;
    }
}
