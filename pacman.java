import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// A pac-man trivia game where the player answers open-ended questions
public class pacman {

    // Arrays of the questions and answers, later taken from pre-made text files.
    static ArrayList<String> questions = new ArrayList<String>();
    static ArrayList<String> answers = new ArrayList<String>();
    // The number of questions
    static int numOfQuestions;

    // Loads the questions from two text files: questions.txt and answers.txt.
    public static void loadQuestions() {

        // Counters used to gather the number of questions and also to check that
        // there are at least the same number of questions to answers.
        int questionCounter = 0;
        int answerCounter = 0;

        try {
            // Set up the readers and grab the first question and answer. There should
            // at least be one question and answer available.
            BufferedReader questionReader = new BufferedReader(new FileReader("questions.txt"));
            String questionLine = questionReader.readLine();
            BufferedReader answerReader = new BufferedReader(new FileReader("answers.txt"));
            String answerLine = answerReader.readLine();

            // Read through the question file and set them in the arraylist.
            while (questionLine != null) {
                questions.add(questionLine);
                questionCounter++;
                questionLine = questionReader.readLine();
            }
            // Close the reader for safety's sake.
            questionReader.close();

            // Read through the answer file and set them in the arraylist.
            while (answerLine != null) {
                answers.add(answerLine);
                answerCounter++;
                answerLine = answerReader.readLine();
            }
            // Close the reader for safety's sake.
            answerReader.close();

            // If there aren't the same number of questions to answers then something
            // is wrong. Exit the program and display an error message.
            if (questionCounter != answerCounter) {
                System.out.println("Question or answer file is formatted incorrectly. Please " +
                                   "contact Namco to fix this.");
                System.exit(0);
            }

            // Set the number of questions for later.
            numOfQuestions = questionCounter;

        // I should put something here but idk what. Let's just hope the reader
        // always works all the time, ok? :D
        } catch (IOException e) {

        } 
    }

    // Runs through the questions and gathers the user's answers. Also checks if they're
    // wrong and displays a score at the end.
    public static void questions() {

        // Scanner for user input.
        Scanner scanner = new Scanner(System.in);
        // A counter for how many questions the user gets correct.
        int correct = 0;
        
        for (int i = 1; i <= numOfQuestions; i++) {
            System.out.println("Question " + i);
            System.out.println(questions.get(i - 1));
            String answer = scanner.nextLine();

            if (!answer.equals(answers.get(i - 1))) {
                System.out.println("Wrong!");
            } else {
                System.out.println("Correct!");
                correct++;
            }
        }

        scanner.close();

        System.out.println("You got " + correct + " out of " + numOfQuestions + " right!");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Pac-Man trivia game!");
        loadQuestions();
        questions();
        System.out.println("Well, we're all out of questions! Thanks for playing! :D");
    }
    
}
