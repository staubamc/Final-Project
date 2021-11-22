import java.awt.*; //importing all the classes we need.
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import java.util.ArrayList;

public class Game implements ActionListener 
{
  //initializing GUI elements and arraylist.
  JLabel welcome;
  JLabel categoryLabel; 
  JLabel questionLabel;
  JButton button1;
  JButton button2;
  JButton button3;
  JButton button4;
  JLabel scoreLabel;
  JButton nextButton;
  JLabel feedbackLabel;
  int questionNumber = 0;
  int score = 0;
  boolean questionAnswered = false;
  ArrayList<Question> trivia = new ArrayList<Question>(); { 

  //try statement to read from a file.
  try {
      FileReader r = new FileReader("trivia.txt");
      BufferedReader reader = new BufferedReader(r);

      //read in all the categories in order
      while (reader.ready()) {
        String question = reader.readLine();
        String option1 = reader.readLine();
        String option2 = reader.readLine();
        String option3 = reader.readLine();
        String option4 = reader.readLine();
        int answer = Integer.parseInt(reader.readLine());
        int points = Integer.parseInt(reader.readLine());
        String category = reader.readLine();
        //then add a new question to the trivia arraylist
        trivia.add(new Question(question, option1, option2, option3, option4, answer, points, category));
      }
      reader.close();
    } 
    //catch statement
    catch (IOException exception) {
        System.out.println("There was an error." + exception);
    }

  }

  //Setting the flow layout and initializing the gui
  Game () {
    JFrame frame = new JFrame("Cincy Sports Trivia");
    frame.setLayout(new FlowLayout());
    frame.setSize(540,250);
    
    //setting the first labels equal to question = 0
    welcome = new JLabel("Welcome to the game!");
    welcome.setFont(new Font("Arial", Font.BOLD, 20));
    categoryLabel = new JLabel("In the " + trivia.get(questionNumber).getCategory() + " category, the " + Integer.toString(trivia.get(questionNumber).getPoints()) + " point question is: ");
    
    questionLabel = new JLabel(trivia.get(questionNumber).getQuestion());
    questionLabel.setFont(new Font("Arial", Font.BOLD, 12));
    
    //adding in the first option buttons.
    button1 = new JButton(trivia.get(questionNumber).getOption1());
    button1.addActionListener(this);
    button2 = new JButton(trivia.get(questionNumber).getOption2());
    button2.addActionListener(this);
    button3 = new JButton(trivia.get(questionNumber).getOption3());
    button3.addActionListener(this);
    button4 = new JButton(trivia.get(questionNumber).getOption4());
    button4.addActionListener(this);
    
    //adding the first score and next buttons.
    scoreLabel = new JLabel("Your score is: " + score);
    nextButton = new JButton("<html><i>Next Question</i></html>");
    nextButton.addActionListener(this);
    feedbackLabel = new JLabel("Click an option above to input your answer.");

    //adding everything to the frame.
    frame.add(welcome);
    frame.add(categoryLabel);
    frame.add(questionLabel);
    frame.add(button1);
    frame.add(button2);
    frame.add(button3);
    frame.add(button4);
    frame.add(feedbackLabel);
    frame.add(scoreLabel);
    frame.add(nextButton);
    frame.setVisible(true);
  }
  //ActionListener Function
public void actionPerformed(ActionEvent ae) {
  int playerGuess;
  if(ae.getActionCommand().equals(trivia.get(questionNumber).getOption1())) {
    if(questionAnswered == false) { //if the user hasnt answered the question yet
      playerGuess = 1; //set an integer for the guess
      guess(playerGuess); //go to the guess function
    } else {
      feedbackLabel.setText("<html><font color = 'red'>You already answered this question! Click <i>'Next Question'</i></font></html>"); //if you have answered the question, dont do anything and prompt for the next one.
    }
  } else if(ae.getActionCommand().equals(trivia.get(questionNumber).getOption2())) {
      if(questionAnswered == false) {
        playerGuess = 2;
        guess(playerGuess);
      } else {
        feedbackLabel.setText("<html><font color = 'red'>You already answered this question! Click <i>'Next Question'</i></font></html>");
      }
  } else if(ae.getActionCommand().equals(trivia.get(questionNumber).getOption3())) {
      if(questionAnswered == false) {
        playerGuess = 3;
        guess(playerGuess);
      } else {
        feedbackLabel.setText("<html><font color = 'red'>You already answered this question! Click <i>'Next Question'</i></font></html>");
      }
  } else if(ae.getActionCommand().equals(trivia.get(questionNumber).getOption4())) {
      if(questionAnswered == false) {
        playerGuess = 1;
        guess(playerGuess);
      } else {
        feedbackLabel.setText("<html><font color = 'red'>You already answered this question! Click <i>'Next Question'</i></font></html>");
      }
  } else if(ae.getActionCommand().equals("<html><i>Next Question</i></html>")) {
      if(questionAnswered == true) { //if the user has answered the question
        questionNumber++;
        update(); //update the GUI for the next question
      } else {
        feedbackLabel.setText("You haven't answered the question yet!");
      }
  }
}

public void guess(int pGuess) {
    if(pGuess == trivia.get(questionNumber).getAnswer()) { //if the players guess is correct
      score = score + trivia.get(questionNumber).getPoints(); //add to the score and update
      scoreLabel.setText("Your score is: " + score);
      feedbackLabel.setText("<html><font color = 'green'>Congrats, you got it right! Please move on to the next question.</font><html>");
      questionAnswered = true; //the player has answered the question
    } else {
      feedbackLabel.setText("Sorry, you got it wrong. Try again on the next question!"); //if theyre wrong, tell them, but they have still answered the question.
      questionAnswered = true;
    }
}

public void update(){
  if(questionNumber < 6) { //if there are still questions, reset the gui with the new question and set the questionAnswered back to false.
    categoryLabel.setText("In the " + trivia.get(questionNumber).getCategory() + " category, the " + Integer.toString(trivia.get(questionNumber).getPoints()) + " point question is: ");
    questionLabel.setText(trivia.get(questionNumber).getQuestion());
    
    button1.setText(trivia.get(questionNumber).getOption1());
    button2.setText(trivia.get(questionNumber).getOption2());
    button3.setText(trivia.get(questionNumber).getOption3());
    button4.setText(trivia.get(questionNumber).getOption4());
    feedbackLabel.setText("Click an option above to input your answer.");
    questionAnswered = false;
  } else { //otherwise, we have no more questions so the game is over.
    feedbackLabel.setText("<html> <font color = 'blue', size = 20> The game is <b>over</b>! </font></html>");
  }
}
}