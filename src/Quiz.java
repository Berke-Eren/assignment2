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
 * @since 2025-05-21
 */
public class Quiz {
    // Declare attributes
    private NewsArticle[] articles;
    private CoverPage titlePage;
    private FinalFrame finalPage;
    private ExplanationFrame explanationFrame;
    private ArticleFrame[] articleFrames;
    private int numArticles;
    private int currentFrame = 0;
    private int score = 0;
    
    /**
     * Parameterized constructor to create a quiz.
     * 
     * @param articles an array of NewsArticles
     * @param titlePage the title page frame
     * @param finalPage the final page frame
     * @param numArticles the number of articles
     */
    public Quiz(NewsArticle[] articles, CoverPage titlePage, FinalFrame finalPage, int numArticles) {
        this.articles = articles;
        this.titlePage = titlePage;
        this.finalPage = finalPage;
        this.numArticles = numArticles;
    }
    
    /**
     * Counts the number of lines in a file.
     * 
     * @param filename the name of the file
     * @return the number of lines in the file
     */
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
    
    /**
     * Reads a file and turns and turns it into NewsArticle objects.
     * 
     * @param filename the name of the file
     * @return an array of NewsArticles
     */
    public static NewsArticle[] getArticlesFromFile(String filename) {
        // Get number of lines.
        int numLines = countLines(filename);
        // Create array for articles.
        NewsArticle[] articles = new NewsArticle[numLines];
        int count = 0;
        try {
            // Create Scanner object to read file.
            Scanner fileInput = new Scanner(new File(filename));
            // Loop until all lines have been read.
            while (fileInput.hasNext()) {
                // Read the next line.
                String currentLine = fileInput.nextLine();
                // Split the line into an array seperated by a semicolon delimiter.
                String[] info = currentLine.split(";");
                // Store each part of the line in the appropiate variable.
                String headline = info[0].trim();
                String content = info[1].trim();
                boolean validity = Boolean.parseBoolean(info[2].trim());
                String type = info[3].trim();
                // If there are only 4 elements in the array present, then no additional information for the
                // article is provided. So, only the constructors with 3 parameters are called.
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
                // If there are 5 elements in the array, then additional information for the article was provided.
                // So, the fifth element is read and the constructors with 4 parameters are called.
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
            // Increment count
            count++;
            } 
        } catch (IOException e) {
            // Print error if exception is caught.
            System.err.println("Error: " + e);
        }
        // Return the array of news articles.
        return articles;
    }
    
    /**
     * Given an array of news articles, creates an array of frames for each article
     * and stores it in an instance variable.
     * 
     * @param articles an array of NewsArticles
     */
    public void generateArticles(NewsArticle[] articles) {
        // Create array to store the frames for the articles
        ArticleFrame[] frames = new ArticleFrame[articles.length];
        // Iterate through the indices of the array of NewsArticles
        for (int i = 0; i < articles.length; i++) {
            // Create an ArticleFrame
            ArticleFrame newFrame = new ArticleFrame(this);
            // Store the frame in the array
            frames[i] = newFrame;
        }
        // Store the frames in an instnace variable
        articleFrames = frames;
    }
    
    /**
     * Writes the user's score to a file along with the date the quiz was taken in the
     * following format: YYYY-MM-DD: Score/TotalScore
     * 
     * @param filename the name of the file to write to
     * @param score the user's score
     */
    public void writeScoreToFile(String filename, int score) {
        // Get current date as a string.
        String currentDate = LocalDate.now().toString();
        try {
            // Create a PrintWriter object to write to file.
            FileWriter fileWriter = new FileWriter(filename, true);
            PrintWriter fileOutput = new PrintWriter(fileWriter);
            // Write score to file.
            fileOutput.printf("%s: %d/%d\n", currentDate, score, numArticles);
            // Close PrintWriter to save.
            fileOutput.close();
        } catch (IOException e) {
            // Print error if an exception is caught.
            System.err.println("Error: " + e);
        }
    }
    
    /**
     * Returns all the text contained in a file as a string.
     * 
     * @param filename the name of the file
     * @return all text in the file as a string, with a newline after each line
     */
    public static String getScoresFromFile(String filename) {
        // Initialize string.
        String scores = "";
        try {
            // Create Scanner to read file.
            Scanner fileInput = new Scanner(new File(filename));
            // Loop until all lines are read.
            while (fileInput.hasNext()) {
                // Add the next line to the previously initialized String.
                scores += (fileInput.nextLine() + "\n");
            }
        } catch (IOException e) {
            // Print an error if an excpetion is caught.
            System.err.println("Error: " + e);
        }
        // Return the string.
        return scores;
    }
    
    /**
     * After the user makes a guess on whether an article is real or fake, shows them an explanation
     * of the correct answer by changing the frame, then incrementing their score if the guess is correct.
     * 
     * @param guess the user's guess of whether the article is real or fake 
     */
    public void displayResults(boolean guess) {
        // Create a new explanation frame and switch to it.
        explanationFrame = new ExplanationFrame(this);
        explanationFrame.setVisible(true);
        articleFrames[currentFrame - 1].setVisible(false);
        // Get the current news article and check whether the user's guess was correct.
        NewsArticle article = articles[currentFrame - 1];
        boolean correct = (guess == articles[currentFrame - 1].isValid());
        // If the guess was correct, increment the score.
        if (correct) {
            score++;
        }
        // Display the proper explanation depending on what type of article it is.
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
    
    /**
     * Goes to the next page of the quiz.
     */
    public void nextPage() {
        // Increment current frame.
        currentFrame++;
        // If the user is on the title page, hide the title page and show the first article.
        if (currentFrame == 1) {
            titlePage.setVisible(false);
            articleFrames[currentFrame - 1].setVisible(true);
            articleFrames[currentFrame - 1].displayArticle(articles[currentFrame - 1], score);
        // If the user is on an article, hide the article and show the next one.
        } else if (currentFrame >= 2 && currentFrame <= numArticles) {
            explanationFrame.setVisible(false);
            articleFrames[currentFrame - 1].setVisible(true);
            articleFrames[currentFrame - 1].displayArticle(articles[currentFrame - 1], score);
        // If the user is on the last article, hid ethe article, show the final page, and write the user's score to the file.
        } else {
            explanationFrame.setVisible(false);
            finalPage.setVisible(true);
            finalPage.displayScore(score, numArticles);
            //writeScoreToFile("scores.txt", score);
        }
    }
    
    /**
     * Gets the number of articles in the quiz.
     * 
     * @return the number of articles in the quiz
     */
    public int getNumArticles() {
        return numArticles;
    }
}
