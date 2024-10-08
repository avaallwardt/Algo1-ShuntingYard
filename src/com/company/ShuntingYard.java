package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShuntingYard {

    public static String infixToPostFix(String expression) {
        Queue<String> operands = new LinkedList<String>();
        Stack<String> operators = new Stack<String>();
        String result = "";
        int numOperators = 0;
        int numOperands = 0;

        if(expression.equals("")){
            return "invalid";
        }

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < expression.length(); j++) {
                if (expression.substring(j, j + 1).equalsIgnoreCase(alphabet.substring(i, i + 1))) {
                    return "invalid";
                }
            }
        }

        String previousType = "";
        for(int i = 0; i < expression.length(); i++){
            for(int j = 0; j < operators.size(); j++){
                System.out.print(operators.get(j));
            }
            System.out.println();
            for(String s : operands){
                System.out.print(s.toString());
            }
            System.out.println();

            String string = expression.substring(i, i+1);
            String type = "";
            if(string.equals("0") || string.equals("1") || string.equals("2") || string.equals("3") ||  string.equals("4") || string.equals("5") || string.equals("6") || string.equals("7") || string.equals("8") || string.equals("9")){
                type = "operand";
            }
            else if(string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-")){
                type = "operator";
            }
            if(i != 0){
                if(type.equals(previousType) && !type.equals("")){
                    // this would mean 2 operatrs or 2 nums in a row (nums can only be 0-9)
                    return "invalid";
                }
            }
            else{
                if(type.equals("operator")){
                    return "invalid";
                }
            }
            if(string.equals("0") || string.equals("1") || string.equals("2") || string.equals("3") ||  string.equals("4") || string.equals("5") || string.equals("6") || string.equals("7") || string.equals("8") || string.equals("9")){
                operands.add(string);
                numOperands++;
            }
            else if(string.equals("*") || string.equals("/")){
                while(operators.peek().equals("*") || operators.peek().equals("/")){ // must add equal precedence to the operands so that it evaluates from left and right
                    operands.add(operators.pop()); // must check for greater OR EQUAL precedence and pop off of the operators stack in either case
                }
                operators.push(string);
                numOperators++;
            }
            else if(string.equals("+") || string.equals("-")){
                for(int j = 0; j < operators.size(); j++){
                    if(operators.isEmpty() == false){
                        if(operators.peek().equals("*") || operators.peek().equals("/") || operators.peek().equals("+") || operators.peek().equals("-")){
                            // this is a while loop bc there could bc multiple higher PEMDAS operators below it
                            operands.add(operators.pop());
                        }
                    }
                }
                operators.push(string);
                numOperators++;
            }
            else if(string.equals("(")){
                // will be removed once a closing parenthesis is found
                operators.push(string);
            }
            else if(string.equals(")")){
                boolean openRemoved = false;
                for(int j = 0; j < operators.size() && openRemoved == false; j++){
                    if(operators.isEmpty() == false) {
                        if (!operators.peek().equals("(")) {
                            operands.add(operators.pop());
                        }
                        else if(operators.peek().equals("(")){
                            operators.pop();
                        }
                    }
                }

                if(operators.isEmpty() == false){
                    if(operators.peek().equals("(")){
                        operators.pop();
                    }
                }
                else{
                    // this means no open parentheses was found
                    return "invalid";
                }
            }
            else{
                // it is not an operator or an operand
                return "invalid";
            }
            previousType = type;
            //System.out.println("test");
        }


        if(numOperators != numOperands - 1){
            // this would mean that there were too many operators or operands, so it was an invalid expression
            return "invalid";
        }
        while(operators.empty() == false){
            operands.add(operators.pop());
        }
        if(operators.contains("(")){
            // this means there was no closing bracket
            // check if there are open parentheses left in the final expression because that means that there wasn't a closed to match it which means it's invalid
            return "invalid";
        }

        while(operands.isEmpty() == false){
            result = result + operands.remove();
        }

        return result;
    }

    public static String postFixToInfix(String expression) {
        String result = "";
        Stack<String> equations = new Stack<String>();

        if(expression.contains("(") || expression.contains(")")){
            return "invalid";
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < alphabet.length(); i++){
            for(int j = 0; j < expression.length(); j++){
                if(expression.substring(j, j+1).equalsIgnoreCase(alphabet.substring(i, i + 1))){
                    return "invalid";
                }
            }
        }
        // need to check for letters in the alphabet? - yes see above

        for(int i = 0; i < expression.length(); i++){
            if(expression.substring(i, i+1).equals("0") || expression.substring(i, i+1).equals("1") || expression.substring(i, i+1).equals("2") || expression.substring(i, i+1).equals("3") || expression.substring(i, i+1).equals("4") || expression.substring(i, i+1).equals("5") || expression.substring(i, i+1).equals("6") || expression.substring(i, i+1).equals("7") || expression.substring(i, i+1).equals("8") || expression.substring(i, i+1).equals("9")){
                equations.push(expression.substring(i, i +1));
                // this will put it in the queue in reverse order but that will mean that the first item to come out will be the farthest right in the original expression`
            }
            else if(expression.substring(i, i+1).equals("*") || expression.substring(i, i+1).equals("/") || expression.substring(i, i+1).equals("+") || expression.substring(i, i+1).equals("-")){
                String operand2 = equations.pop();
                String operand1 = "";
                if(equations.isEmpty() == false){
                    operand1 = equations.pop();
                }
                // how do i deal with parentheses? - done see below
                String equation = "(" + operand1 + expression.substring(i, i+1) + operand2 + ")";
                equations.add(equation);
            }
            else{
                return "invalid";
            }
        }
        result = equations.pop();

        // checks the final expression against the other algorithm

        if(infixToPostFix(result).equals("invalid")){
            return "invalid";
        }
        if(equations.isEmpty() == true){
            return result;
        }
        else{
            return "invalid";
        }
    }

    public static boolean isValidPostFixExpression(String expressionToCheck) {
        if(postFixToInfix(expressionToCheck).equals("invalid")){
            return false;
        }
        else{
            return true;
        }

        /*
        if(expressionToCheck.contains("(") || expressionToCheck.contains(")")){

            return false;
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < alphabet.length(); i++){
            for(int j = 0; j < expressionToCheck.length(); j++){
                if(expressionToCheck.substring(j, j+1).equalsIgnoreCase(alphabet.substring(i, i + 1))){
                    return false;
                }
            }
        }
        for(int i = 0; i < expressionToCheck.length(); i++){
            if(expressionToCheck.substring(i, i+1).equals("0") || expressionToCheck.substring(i, i+1).equals("1") || expressionToCheck.substring(i, i+1).equals("2") || expressionToCheck.substring(i, i+1).equals("3") || expressionToCheck.substring(i, i+1).equals("4") || expressionToCheck.substring(i, i+1).equals("5") || expressionToCheck.substring(i, i+1).equals("6") || expressionToCheck.substring(i, i+1).equals("7") || expressionToCheck.substring(i, i+1).equals("8") || expressionToCheck.substring(i, i+1).equals("9")){
            }
            else if(expressionToCheck.substring(i, i+1).equals("*") || expressionToCheck.substring(i, i+1).equals("/") || expressionToCheck.substring(i, i+1).equals("+") || expressionToCheck.substring(i, i+1).equals("-")){
            }
            else{
                return false;
            }
        }

        return false;

         */
    }

    public static boolean isValidInFixExpression(String expressionToCheck){
        if(infixToPostFix(expressionToCheck).equals("invalid")){
            return false;
        }
        else{
            return true;
        }
    }

    // make both an isPostFixValid and isInfixValid checking method (2 total)

    public static double evaluatePostFix(String expression) {
        // if the expression is invalid
        if(isValidPostFixExpression(expression) == false){
            return -1;
        }
        Stack<String> operands = new Stack<String>();
        for(int i = 0; i < expression.length(); i++){
            String string = expression.substring(i, i+1);
            if(expression.substring(i, i+1).equals("0") || expression.substring(i, i+1).equals("1") || expression.substring(i, i+1).equals("2") || expression.substring(i, i+1).equals("3") || expression.substring(i, i+1).equals("4") || expression.substring(i, i+1).equals("5") || expression.substring(i, i+1).equals("6") || expression.substring(i, i+1).equals("7") || expression.substring(i, i+1).equals("8") || expression.substring(i, i+1).equals("9")){
                operands.push(expression.substring(i, i + 1));
            }
            else if (expression.substring(i, i+1).equals("*") || expression.substring(i, i+1).equals("/") || expression.substring(i, i+1).equals("+") || expression.substring(i, i+1).equals("-")) {
                if (string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-")) {
                    int topNum = 0;
                    int nextNum = 0;
                    int total = 0;
                    switch (string) {
                        case "*":
                            topNum = Integer.parseInt(operands.pop());
                            nextNum = Integer.parseInt(operands.pop());
                            total = nextNum * topNum;
                            operands.push(String.valueOf(total));
                            break;
                        case "/":
                            topNum = Integer.parseInt(operands.pop());
                            nextNum = Integer.parseInt(operands.pop());
                            total = nextNum / topNum;
                            operands.push(String.valueOf(total));
                            break;
                        case "+":
                            topNum = Integer.parseInt(operands.pop());
                            nextNum = Integer.parseInt(operands.pop());
                            total = nextNum + topNum;
                            operands.push(String.valueOf(total));
                            break;
                        case "-":
                            topNum = Integer.parseInt(operands.pop());
                            nextNum = Integer.parseInt(operands.pop());
                            total = nextNum - topNum;
                            operands.push(String.valueOf(total));
                            break;
                    }
                }
            }

        }
        return Integer.parseInt(operands.pop());


        //return 0;
    }


}
