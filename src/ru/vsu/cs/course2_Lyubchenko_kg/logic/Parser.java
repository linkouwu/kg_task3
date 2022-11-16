package ru.vsu.cs.course2_Lyubchenko_kg.logic;

import net.objecthunter.exp4j.*;

public class Parser{
    private String func;
    private final Expression expression;

    public Parser(String func) {
        this.func = func;
        this.expression = new ExpressionBuilder(func).build();
    }

    public double solve(double x) {
        try{
            expression.setVariable("x", x);
            return expression.evaluate();
        } catch (Throwable e){
            if (e instanceof ArithmeticException && "Division by zero!".equals((e.getMessage()))){
                return Double.MAX_VALUE;
            }
            else{
                return Double.NaN;
            }
        }
    }

    public double solve(double x, double a) {
        try{
            expression.setVariable("x", x).setVariable("a", a);
            return expression.evaluate();
        } catch (Throwable e){
            if (e instanceof ArithmeticException && "Division by zero!".equals((e.getMessage()))){
                return Double.MAX_VALUE;
            }
            else{
                return Double.NaN;
            }
        }
    }
}