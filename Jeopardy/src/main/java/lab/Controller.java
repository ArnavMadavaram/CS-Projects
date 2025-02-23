package lab;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Name: Arnav Madavaram
 * Username: madaa01
 */

public class Controller {

    //Game variables
    private Question[][] questions;
    private Question currentQuestion;
    private ImageView currentImageView;
    private double score;

    //JavaFX variables
    @FXML
    private GridPane gameGrid;
    @FXML
    private Label questionLbl;
    @FXML
    private TextField answerTxt;
    @FXML
    private Button submitBtn;
    @FXML
    private Label resultLbl;
    @FXML
    private Label scoreLbl;

    //Image size variables
    private double imgHeight = 120;
    private double imgAspectRatio = 1.8116;

    @FXML
    void initialize() {
        //Load images
        Image img4 = new Image("file:images/400.png");
        Image img8 = new Image("file:images/800.png");
        Image img12 = new Image("file:images/1200.png");
        
        //Initialize the score
        score = 0;
        
        //Read in question data from file
        //Two file options: animal-questions.txt and geography-questions.txt
        try {
            questions = readQuestions("animal-questions.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
         * Populate the game grid with images
         * and add an event that shows and sets the 
         * current question that corresponds to the 
         * image clicked
         */
        for (int r = 0; r < questions.length; r++) {
            for (int c = 0; c < questions[r].length; c++) {
                Question question = questions[r][c];
                ImageView imageView = new ImageView();
                
                // Assign the correct image based on the question value
                if (question.getValue() == 400) {
                    imageView.setImage(img4);
                } else if (question.getValue() == 800) {
                    imageView.setImage(img8);
                } else if (question.getValue() == 1200) {
                    imageView.setImage(img12);
                }
                
                //Set Image size
                imageView.setFitHeight(imgHeight);
                imageView.setFitWidth(imgHeight * imgAspectRatio);
                
                //lambda event variables must be final
                final int row = r;
                final int col = c;
                imageView.setOnMouseClicked(e -> {
                    currentQuestion = questions[row][col];
                    currentImageView = imageView;
                    questionLbl.setText(currentQuestion.toString());
                });

                //Add the image to the grid
                gameGrid.add(imageView, c, r);
            }
        }
    }

    @FXML
    void submitAnswer(ActionEvent event) {
        //Get answer from GUI
        String answer = answerTxt.getText().trim();
        
        //Check the answer begins with "What is" and ends with a question mark
        if (!answer.startsWith("What is") || !answer.endsWith("?")) {
            resultLbl.setText("Not a question!");
            return;
        }
        
        //Check the answer only if the question has not been answered yet
        String cleanedAnswer = answer.substring(8, answer.length() - 1).trim();
        if (!currentQuestion.isAnswered()) {
            //Take off the "what is " and "?" and check the answer
            if (currentQuestion.checkAnswer(cleanedAnswer)) {
                //Display results and score
                score += currentQuestion.getValue();
                scoreLbl.setText(":" + score);
                resultLbl.setText("Correct!");
            } else {
                resultLbl.setText("Incorrect!");
            }

            //Set question as answered and hide the $ image
            currentQuestion.setAnswered(true);
            currentImageView.setVisible(false);
        }
        
        //Clear the answer
        answerTxt.clear();
        
        /*
         * If the game is over, show "Game Over!"
         * and disable the submit Button and answer TextField
         */
        if (gameIsOver()) {
            questionLbl.setText("Game Over!");
            submitBtn.setDisable(true);
            answerTxt.setDisable(true);
        }
    }

    /**
     * Reads a question file of $400,$800,$1200 questions
     * and puts them in respective rows 0,1,2 of a 2D array
     * @return 2D array of Question objects
     * @throws FileNotFoundException 
     */
    public Question[][] readQuestions(String filename) throws FileNotFoundException {
        //Initialize the 2D array and Scanner
        Question[][] questions = new Question[3][4];
        Scanner reader = new Scanner(Controller.class.getResourceAsStream(filename));

        //Read the file to build Question objects
        int row = 0, col = 0; // Track position in the 2D array
        while (reader.hasNextLine() && row < 3) {
            String line = reader.nextLine();

            String[] parts = line.split("_");

            double value = Double.parseDouble(parts[0].trim());
            String question = parts[1].trim();
            String answer = parts[2].trim();

            questions[row][col] = new Question(question, answer, value);

            col++;
            
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        reader.close();
        return questions;
    }
    
    /**
     * Checks if all questions have been answered
     * @return true if all questions have been answered
     */
    public boolean gameIsOver() {
        for (int r = 0; r < questions.length; r++) {
            for (int c = 0; c < questions[r].length; c++) {
                if (!questions[r][c].isAnswered()) {
                    return false;
                }
            }
        }
        return true;
    }
}
