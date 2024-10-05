package dev.jlkeesh.spel_programatic_approach;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("{1,2,3,4}");
        Integer[] integers = expression.getValue(Integer[].class);
        for (Integer integer : integers) {
            System.out.println("integer = " + integer);
        }
        Expression expression1 = parser.parseExpression("{{'a','b'},{'x','y'}}");
        Character[][] matrix = expression1.getValue(Character[][].class);
        for (Character[] characters : matrix) {
            for (Character character : characters) {
                System.out.print("character = " + character + " : ");
            }
            System.out.println();
        }

        StandardEvaluationContext context = new StandardEvaluationContext(new Main());
        //String message = parser.parseExpression("hi()").getValue(context, String.class);
        String message = parser.parseExpression("'saLOm'.toLowerCase()").getValue(context, String.class);
        System.out.println("message = " + message);
        
    }

    public static String hi() {
        return "Hello from hi : time is " + LocalTime.now();
    }
}
