import javax.swing.JFrame;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

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
    private JFrame[] articleFrames;
    private static int currentFrame = 0;
    private static int score = 0;
    
    public Quiz(NewsArticle[] articles, JFrame titlePage, JFrame finalPage) {
        this.articles = articles;
        this.titlePage = titlePage;
        this.finalPage = finalPage;
    }
    
    public static int countLines(String filename) {
        // Initialize count variable to track number of lines
        int count = 0;
        // Create Scanner object to read file
        Scanner fileInput = new Scanner(filename);
        // Loop until no lines are left
        while (fileInput.hasNext()) {
            // Go to the next line and increase the count
            fileInput.nextLine();
            count++;
        }
        // Return the number of lines
        return count;
    }
    
    public static NewsArticle[] getArticlesFromFile(String filename) {
        // Get number of lines
        int numLines = countLines(filename);
        NewsArticle[] articles = new NewsArticle[numLines];
        int count = 0;
        Scanner fileInput = new Scanner(filename);
        while (fileInput.hasNext()) {
            String currentLine = fileInput.nextLine();
            String[] info = currentLine.split(",");
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
        return articles;
    }
    
    public static JFrame[] generateArticles(NewsArticle[] articles) {
        JFrame[] frames = new JFrame[articles.length];
        for (int i = 0; i < articles.length; i++) {
            ArticleFrame newFrame = new ArticleFrame();
            newFrame.displayArticle(articles[i]);
            frames[i] = newFrame;
        }
        return frames;
    }
    
    public static void writeScoreToFile(String filename, int score) {
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
    
    public static String getScoresFromFile(String filename) {
        String scores = "";
        Scanner fileInput = new Scanner(filename);
        while (fileInput.hasNext()) {
            scores += (fileInput.nextLine() + "\n");
        }
        return scores;
    }
    
    public static void displayResults(boolean misleading) {
        
        // todo: change labels, display new button
        score++;
    }
    
    public static void nextPage() {
        currentFrame++;
        // hide current jframe, show the next one
    }
}
