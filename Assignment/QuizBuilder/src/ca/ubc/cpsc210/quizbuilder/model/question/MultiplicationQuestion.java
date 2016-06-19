package ca.ubc.cpsc210.quizbuilder.model.question;

/**
 * Created by Leonard on 2016/2/23.
 */
public class MultiplicationQuestion extends Question {

    private int factor1;
    private int factor2;

    // REQUIRES: maxMark must be >= 0
    // EFFECTS: constructs multiplication question with given maximum mark, factor1
    // and factor2
    public MultiplicationQuestion (double maxMark, int factor1, int factor2) {
        super(maxMark,"");
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    @Override
    public String getQuestionString() {
        return factor1 + "*" + factor2 + "= ?" + " [" + maxMark + " points]";
    }

    @Override
    public boolean isCorrect(String answer) {
        try {
            int multiplicationAnswer = Integer.parseInt(answer);
            int multiplicationResult = factor1 * factor2;
            return multiplicationAnswer == multiplicationResult;
        } catch (NumberFormatException nfe) {
            System.out.print("Wrong format\n");
            return false;
        }
    }

}
