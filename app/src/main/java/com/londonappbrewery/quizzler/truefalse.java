package com.londonappbrewery.quizzler;

public class truefalse {

    private int mQuestionID;
    private Boolean mAnswer;

    public truefalse(int mQuestionResourceID, Boolean trueORfalse){
        mQuestionID = mQuestionResourceID;
        mAnswer = trueORfalse;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public Boolean getAnswer() {
        return mAnswer;
    }

    public void setAnswer(Boolean answer) {
        mAnswer = answer;
    }
}
