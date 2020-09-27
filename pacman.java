import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// A pac-man trivia game where the player answers open-ended questions.
public class pacman {

    // Array of questions, later taken from a pre-made text file.
    static ArrayList<String> questions = new ArrayList<String>();
    // 2d array of answers, later taken from a pre-made text file. It's 2d because
    // there can be multiple correct answers for one question. Answers must be broken
    // by spaces, this means no answers which contain spaces!!!
    static ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
    // The number of questions.
    static int numOfQuestions;

    // Loads the questions from two text files: questions.txt and answers.txt.
    public static void loadQuestions() {

        // Counters used to gather the number of questions and also to check that
        // there are at least the same number of questions to answers.
        int questionCounter = 0;
        int answerCounter = 0;

        // Have to put readers in a try/catch in case of an error.
        try {
            // Set up the readers and grab the first question and answer. There should
            // at least be one question and answer available.
            final BufferedReader questionReader = new BufferedReader(new FileReader("questions.txt"));
            String questionLine = questionReader.readLine();
            final BufferedReader answerReader = new BufferedReader(new FileReader("answers.txt"));
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
                final StringTokenizer answer = new StringTokenizer(answerLine);
                final ArrayList<String> specificAnswers = new ArrayList<String>();
                while (answer.hasMoreTokens()) {
                    final String specificAnswer = answer.nextToken();
                    specificAnswers.add(specificAnswer);
                }
                answers.add(specificAnswers);
                answerCounter++;
                answerLine = answerReader.readLine();
            }
            // Close the reader for safety's sake.
            answerReader.close();

            // If there aren't the same number of questions to answers then something
            // is wrong. Exit the program and display an error message.
            if (questionCounter != answerCounter) {
                System.out.println(
                        "Question or answer file is formatted incorrectly. Please " + "contact Namco to fix this.");
                System.exit(0);
            }

            // Set the number of questions for later.
            numOfQuestions = questionCounter;

            // I should put something here but idk what. Let's just hope the reader
            // always works all the time, ok? :D
        } catch (final IOException e) {

        }
    }

    // Runs through the questions and gathers the user's answers. Also checks if
    // they're wrong and displays a score at the end.
    public static void questions() {

        // Scanner for user input.
        final Scanner scanner = new Scanner(System.in);
        // A counter for how many questions the user gets correct.
        int correct = 0;

        // Run through the questions and get the user's input.
        for (int i = 1; i <= numOfQuestions; i++) {
            System.out.println("Question " + i);
            // Print the question.
            System.out.println(questions.get(i - 1));
            // Get the user's input.
            final String answer = scanner.nextLine();

            // Flag for if the user answered correctly
            boolean correctAnswer = false;

            // Because multiple answers can be correct, we need to run through every
            // single correct answer in the 2d arraylist.
            for (int j = 0; j < answers.get(i - 1).size(); j++) {
                // Check if the user's input matches the selected input.
                if (answer.equals(answers.get(i - 1).get(j))) {
                    System.out.println("Correct!");
                    correctAnswer = true;
                    correct++;
                    // Breaks out of the for loop.
                    j = answers.get(i - 1).size();
                }
            }

            // Prints if the user's input didn't match any of the answers.
            if (!correctAnswer) {
                System.out.println("Wrong!");
            }
        }

        // Closer the scanner for safety's sake.
        scanner.close();

        // Print the user's score.
        System.out.println("You got " + correct + " out of " + numOfQuestions + " right!");
    }

    // The main method, which simply runs the other methods.
    public static void main(final String[] args) {
        System.out.println("Welcome to the Pac-Man trivia game!");
        loadQuestions();
        questions();
        // Prints after the game is finished.
        System.out.println("Well, we're all out of questions! Thanks for playing! :D");
    }
    
}
