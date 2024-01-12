package com.example.java2lab5;

public class TaskGenerator {
    private static final int MAX_NUMBER_OF_BITS = 5;
    public static Task generateRandomTask() {
        int type = (int) (Math.random() * 2);
        return switch (type) {
            case 0 -> generateRandomBinaryOperationTask();
            case 1 -> generateRandomLogicalOperationTask();
            default -> throw new RuntimeException("Unknown task type");
        };
    }

    public static BinaryTask generateRandomBinaryOperationTask() {
        int left = (int) (Math.random() * (1 << MAX_NUMBER_OF_BITS));
        int right = (int) (Math.random() * (1 << MAX_NUMBER_OF_BITS));
        BinaryOperation operation = BinaryOperation.values()[(int) (Math.random() * BinaryOperation.values().length)];
        return new BinaryTask(left, right, operation);
    }

    public static LogicalTask generateRandomLogicalOperationTask() {
        StringBuilder expression = new StringBuilder();
        char[] operations = {'∧', '∨'};
        for(int i = 0; i < 1 + (int)(Math.random() * (MAX_NUMBER_OF_BITS - 1)); i++) {
            if(i != 0){
                char operation = operations[(int) (Math.random() * operations.length)];
                expression.append(operation);
            }
            int symbol = (int) (Math.random() * 2);

            if((int)(Math.random() * 2) == 1){ // Invert
                expression.append('¬');
            }

            expression.append(symbol);
        }
        return new LogicalTask(expression.toString());
    }
}

