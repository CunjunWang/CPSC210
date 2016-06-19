package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Created by Leonard on 2016/2/23.
 */

public class DecrementMarksQuiz extends EachAnswerMustBeRightQuiz {

    public DecrementMarksQuiz (QuestionsList questions){
        super(questions);
    }

    @Override
    public String submitAnswer(String answer) throws RetryAnswerException, OutOfTriesException {

        double maxMark = curQuestion.getMaxMark();

        if(maxMark == 1.0)
            throw new OutOfTriesException("You are out of tries!");

        boolean correct = super.checkAnswer(answer);
        if (!correct) {
            maxMark = maxMark - 1.0;
            curQuestion.setMaxMark(maxMark);
            throw new RetryAnswerException("Wrong answer, please retry.");
        }
        return "";
    }
}
