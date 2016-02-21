package ua.skillsup.calculator.exception;

/**
 * Created by Artem on 20.02.2016.
 */
public class Exceptions extends RuntimeException {

    public static Exceptions cantDivideByZeroException(){
        throw new RuntimeException(" You can't divide by zero ");
    }
}
