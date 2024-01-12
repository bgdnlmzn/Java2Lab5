package com.example.java2lab5;

public class LogicalTask implements Task {

    private String expression;
    public LogicalTask(String expression){
        this.expression = expression;
    };

    @Override
    public String toString(){
        return expression;
    }

    @Override
    public boolean isCorrectAnswer(int answer){
        return getAnswer() == answer;
    }
    @Override
    public int getAnswer() {
        String answer = expression.replace("¬0", "1").replace("¬1", "0");
        while (answer.contains("∧")){
            answer = answer.replace("0∧0", "0");
            answer = answer.replace("0∧1", "0");
            answer = answer.replace("1∧0", "0");
            answer = answer.replace("1∧1", "1");;
        }
        while (answer.contains("∨")){
            answer = answer.replace("0∨0", "0");
            answer = answer.replace("0∨1", "1");
            answer = answer.replace("1∨0", "1");
            answer = answer.replace("1∨1", "1");
        }

        return Integer.parseInt(answer);
    }

}

