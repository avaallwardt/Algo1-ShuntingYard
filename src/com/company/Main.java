package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {


    public static void main(String[] args) {

        System.out.println(ShuntingYard.infixToPostFix("1+(1-1)*2*(3-3)"));

    }

// Please disregard all of this commented out code in Main
    // need to run this
    /*
    public static String convertToPostFix(String expression){

        Queue<String> operands = new LinkedList<String>();
        Stack<String> operators = new Stack<String>();
        String result = "";
        int numOperators = 0;
        int numOperands = 0;

        if(expression.equals("")){
            return "invalid";
        }

        String previousType = "";
        for(int i = 0; i < expression.length(); i++){
            String string = expression.substring(i, i+1);
            String type = "";
            if(string.equals("0") || string.equals("1") || string.equals("2") || string.equals("3") ||  string.equals("4") || string.equals("5") || string.equals("6") || string.equals("7") || string.equals("8") || string.equals("9")){
                type = "operand";
            }
            else if(string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-")){
                type = "operator";
            }
            if(i != 0){
               if(type.equals(previousType)){
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
                operators.push(string);
                numOperators++;
            }
            else if(string.equals("+") || string.equals("-")){
                for(int j = 0; j < operators.size(); j++){
                    while(operators.isEmpty() == false){
                        if(operators.peek().equals("*") || operators.peek().equals("/") || operators.peek().equals("+") || operators.peek().equals("-")){
                            // this is a while loop bc there could bc multiple higher or equal PEMDAS operators below it
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
                for(int j = 0; j < operators.size(); j++){
                    if(operators.isEmpty() == false) {
                        if (!operators.peek().equals("(")) {
                            operands.add(operators.pop());
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

     */

/*
    public static String convertToPostFix(String expression){
        Queue<String> operands = new LinkedList<String>();
        Stack<String> operators = new Stack<String>();
        String result = "";
        int numOperators = 0;
        int numOperands = 0;

        if(expression.equals("")){
            return "invalid";
        }

        String previousType = "";
        for(int i = 0; i < expression.length(); i++){
            String string = expression.substring(i, i+1);
            String type = "";
            if(string.equals("0") || string.equals("1") || string.equals("2") || string.equals("3") ||  string.equals("4") || string.equals("5") || string.equals("6") || string.equals("7") || string.equals("8") || string.equals("9")){
                type = "operand";
            }
            else if(string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-")){
                type = "operator";
            }
            if(i != 0){
                if(type.equals(previousType)){
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
                for(int j = 0; j < operators.size(); j++){
                    if(operators.isEmpty() == false) {
                        if (!operators.peek().equals("(")) {
                            operands.add(operators.pop());
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


    public static String convertToInfix(String expression){
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
        if(equations.isEmpty() == true){
            return result;
        }
        else{
            return "invalid";
        }
    }


    /*
    public double evaluatePostFix(String postFixExpression){
        // better to evaluate as postFix (use the queue method discussed)



        if(convertToInfix(postFixExpression).equals("invalid")){
            return -1;
        }
        double result = 0;
        String infix = convertToInfix(postFixExpression);
        for(int i = 0; i < infix.length(); i++) {
            String string = infix.substring(i, i+1);
            if (string.equals("*") || string.equals("/") || string.equals("+") || string.equals("-")) {
                switch (string) {
                    case "*":
                        int total = Integer.parseInt(infix.substring(i-1, i)) * Integer.parseInt(infix.substring(i+1, i+2));
                        infix = infix.substring();
                        break;
                    case "/":
                        System.out.println("Tuesday");
                        break;
                    case "+":
                        System.out.println("Wednesday");
                        break;
                    case "-":
                        System.out.println("Thursday");
                        break;
                }
            }
        }

        return 0.0;
    }

     */










}
