package sever;

import model.daoOperaciones;

public class operaciones {

    private double resultado;


    public double suma(double num1, double num2) {
        resultado = num1 + num2;

        return resultado;
    }

    public double resta(double num1, double num2) {
        resultado = num1 - num2;

        return resultado;

    }

    public double multiplicacion(double num1, double num2) {
        resultado = num1 * num2;

        return resultado;
    }

    public double division(double num1, double num2) {
        resultado = num1 / num2;

        return resultado;
    }

    public double raiz(double num1) {
        resultado = Math.sqrt(num1);

        return resultado;
    }

    public double exponente(double num1, double num2) {
        resultado = Math.pow(num1, num2);

        return resultado;
    }




}
