package com.example.java2lab5;

public class BinaryTask implements Task {
    private int left;
    private int right;
    private BinaryOperation operation;

    public BinaryTask(int left, int right, BinaryOperation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public BinaryOperation getOperation() {
        return operation;
    }
    @Override
    public String toString() {
        return Integer.toBinaryString(left) + " " + operation + " " + Integer.toBinaryString(right);
    }

    @Override
    public int getAnswer() {
        return switch (operation) {
            case OR -> left | right;
            case AND -> left & right;
            case XOR -> left ^ right;
        };
    }

    @Override
    public boolean isCorrectAnswer(int answer) {
        return getAnswer() == answer;
    }
}

