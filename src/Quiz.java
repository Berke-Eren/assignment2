import javax.swing.JFrame;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Serves as a class that manages the quiz. Stores the news articles along with all
 * the frames, keeps track of score, moves between frames, and displays results.
 * 
 * @author Berke Eren
 * @version 1.0
 * @since 2025-05-13
 */
public class Quiz {
    // Declare attributes
    private NewsArticle[] articles;
    private JFrame titlePage;
    private JFrame finalPage;
    private ExplanationFrame explanationFrame;
    private ArticleFrame[] articleFrames;
    private int numArticles;
    private int currentFrame = 0;
    private int score = 0;
    
    public Quiz(NewsArticle[] articles, JFrame titlePage, JFrame finalPage, int numArticles) {
        this.articles = articles;
        this.titlePage = titlePage;
        this.finalPage = finalPage;
        this.numArticles = numArticles;
    }
    
    public static int countLines(String filename) {
        // Initialize count variable to track number of lines
        int count = 0;
        try {
            // Create Scanner object to read file
            Scanner fileInput = new Scanner(new File(filename));
            // Loop until no lines are left
            while (fileInput.hasNext()) {
                // Go to the next line and increase the count
                fileInput.nextLine();
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        // Return the number of lines
        return count;
    }
    
    public static int[] generateRandomLinesFromFile(int numberOfRandomLines, String filename) {
        int numLines = countLines(filename);
        int[] randomLines = new int[numberOfRandomLines];

    }
    
    public static NewsArticle[] getArticlesFromFile(String filename, int numArticles) {
        // Get number of lines
        int numLines = countLines(filename);
        NewsArticle[] articles = new NewsArticle[numLines];
        int count = 0;
        try {
            Scanner fileInput = new Scanner(new File(filename));
            while (fileInput.hasNext()) {
                String currentLine = fileInput.nextLine();
                String[] info = currentLine.split(";");
                String headline = info[0].trim();
                String content = info[1].trim();
                boolean validity = Boolean.parseBoolean(info[2].trim());
                String type = info[3].trim();
                if (info.length == 4) {
                    if (type.toLowerCase().equals("misleadingheadline")) {
                        articles[count] = new MisleadingHeadlineArticle(headline, content, validity);
                    } else if (type.toLowerCase().equals("misusedsource")) {
                        articles[count] = new MisusedSourceArticle(headline, content, validity);
                    } else if (type.toLowerCase().equals("appealtoemotions")) {
                        articles[count] = new AppealToEmotionsArticle(headline, content, validity);
                    } else if (type.toLowerCase().equals("lackingfacts")) {
                        articles[count] = new LackingFactsArticle(headline, content, validity);
                    } else if (type.toLowerCase().equals("fakeexpert")) {
                        articles[count] = new LackingFactsArticle(headline, content, validity); 
                    }
                } else {
                    String explanation = info[4].trim();
                    if (type.toLowerCase().equals("misleadingheadline")) {
                        articles[count] = new MisleadingHeadlineArticle(headline, content, validity, explanation);
                    } else if (type.toLowerCase().equals("misusedsource")) {
                        articles[count] = new MisusedSourceArticle(headline, content, validity, explanation);
                    } else if (type.toLowerCase().equals("appealtoemotions")) {
                        articles[count] = new AppealToEmotionsArticle(headline, content, validity, explanation);
                    } else if (type.toLowerCase().equals("lackingfacts")) {
                        articles[count] = new LackingFactsArticle(headline, content, validity, explanation);
                    } else if (type.toLowerCase().equals("fakeexpert")) {
                        articles[count] = new FakeExpertArticle(headline, content, validity, explanation);
                    }
                }
            count++;
            } 
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return articles;
    }
    
    public void generateArticles(NewsArticle[] articles) {
        ArticleFrame[] frames = new ArticleFrame[articles.length];
        for (int i = 0; i < articles.length; i++) {
            ArticleFrame newFrame = new ArticleFrame(this);
            frames[i] = newFrame;
        }
        articleFrames = frames;
        System.out.println(articleFrames.length);
    }
    
    public void writeScoreToFile(String filename, int score) {
        String currentDate = LocalDate.now().toString();
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            PrintWriter fileOutput = new PrintWriter(fileWriter);
            fileOutput.printf("%s: %s\n", currentDate, score);
            fileOutput.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }
    
    public String getScoresFromFile(String filename) {
        String scores = "";
        Scanner fileInput = new Scanner(filename);
        while (fileInput.hasNext()) {
            scores += (fileInput.nextLine() + "\n");
        }
        return scores;
    }
    
    public void displayResults(boolean guess) {
        explanationFrame = new ExplanationFrame(this);
        explanationFrame.setVisible(true);
        articleFrames[currentFrame - 1].setVisible(false);
        NewsArticle article = articles[currentFrame - 1];
        boolean correct = (guess == articles[currentFrame - 1].isValid());
        if (correct) {
            score++;
        }
        if (article instanceof AppealToEmotionsArticle) {
            explanationFrame.displayExplanation(((AppealToEmotionsArticle)article).getExplanation(), correct);
        } else if (article instanceof FakeExpertArticle) {
            explanationFrame.displayExplanation(((FakeExpertArticle)article).getExplanation(), correct);
        } else if (article instanceof LackingFactsArticle) {
            explanationFrame.displayExplanation(((LackingFactsArticle)article).getExplanation(), correct);
        } else if (article instanceof MisleadingHeadlineArticle) {
            explanationFrame.displayExplanation(((MisleadingHeadlineArticle)article).getExplanation(), correct);
        } else if (article instanceof MisusedSourceArticle) {
            explanationFrame.displayExplanation(((MisusedSourceArticle)article).getExplanation(), correct);
        }
        
    }
    
    public void nextPage() {
        currentFrame++;
        System.out.println(currentFrame);
        if (currentFrame == 1) {
            titlePage.setVisible(false);
            articleFrames[currentFrame - 1].setVisible(true);
            articleFrames[currentFrame - 1].displayArticle(articles[currentFrame - 1], score);
        } else if (currentFrame >= 2 && currentFrame <= 12) {
            explanationFrame.setVisible(false);
            articleFrames[currentFrame - 1].setVisible(true);
            articleFrames[currentFrame - 1].displayArticle(articles[currentFrame - 1], score);
        } else {
            writeScoreToFile("scores.txt", score);
            explanationFrame.setVisible(false);
            finalPage.setVisible(true);
        }
    }
}
