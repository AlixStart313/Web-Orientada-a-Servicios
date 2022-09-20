package cliente;

import model.daoOperaciones;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import sever.operaciones;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ClienteRPC {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        teclado.useDelimiter("\n");

        XmlRpcClientConfigImpl configuracion = new XmlRpcClientConfigImpl();
        configuracion.setServerURL(new URL("http://localhost:1800"));
        XmlRpcClient cliente = new XmlRpcClient();
        cliente.setConfig(configuracion);

        String option = "", firstNumber = "", secondNumber = "";
        double num1, num2;
        String green="\033[32m";
        String yellow="\033[33m";
        String reset="\u001B[0m";
        String red="\033[31m";
        boolean seguir = true;



        do {
            System.out.println("1.suma \n2.Resta \n3.multiplicacion \n4.Division" +
                    " \n5.Exponente \n6.Raiz \n7.Consultar historia \n8.Salir");
            System.out.println("Seleccion una opcion...");
            option = teclado.next();

            if (isNumber(option)) {
                switch (Integer.parseInt(option)) {
                    case 1:
                        System.out.println(yellow+"\n******SUMA*******"+reset);
                        do {
                            System.out.println("ingrese el primer numero");
                            firstNumber = teclado.next();
                            if (!isDouble(firstNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(firstNumber));
                        do {
                            System.out.println("ingrese el segundo numero");
                            secondNumber = teclado.next();
                            if (!isDouble(secondNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(secondNumber));
                        num1 = Double.parseDouble(firstNumber);
                        num2 = Double.parseDouble(secondNumber);
                        Object[] resultado = {num1, num2};
                        Double response = (Double) cliente.execute("operaciones.suma", resultado);
                        System.out.println(green+"\nEl resultado es" + response+reset);


                        break;
                    case 2:
                        System.out.println(yellow+"\n******resta*******"+reset);
                        do {
                            System.out.println("ingrese el primer numero");
                            firstNumber = teclado.next();
                            if (!isDouble(firstNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(firstNumber));
                        do {
                            System.out.println("ingrese el segundo numero");
                            secondNumber = teclado.next();
                            if (!isDouble(secondNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(secondNumber));
                        num1 = Double.parseDouble(firstNumber);
                        num2 = Double.parseDouble(secondNumber);
                        Object[] resultado2 = {num1, num2};
                        Double response2 = (Double) cliente.execute("operaciones.resta", resultado2);
                        System.out.println(green+"\nEl resultado es" + response2+reset);


                        break;
                    case 3:
                        System.out.println(yellow+"\n******MULTPLICACION*******"+reset);
                        do {
                            System.out.println("ingrese el primer numero");
                            firstNumber = teclado.next();
                            if (!isDouble(firstNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(firstNumber));
                        do {
                            System.out.println("ingrese el segundo numero");
                            secondNumber = teclado.next();
                            if (!isDouble(secondNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(secondNumber));
                        num1 = Double.parseDouble(firstNumber);
                        num2 = Double.parseDouble(secondNumber);
                        Object[] resultado3 = {num1, num2};
                        Double response3 = (Double) cliente.execute("operaciones.multiplicacion", resultado3);
                        System.out.println(green+"\nEl resultado es" + response3+reset);



                        break;
                    case 4:
                        System.out.println(yellow+"\n******DIVISION*******"+reset);
                        do {
                            System.out.println("ingrese el primer numero");
                            firstNumber = teclado.next();
                            if (!isDouble(firstNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(firstNumber));
                        do {
                            System.out.println("ingrese el segundo numero");
                            secondNumber = teclado.next();
                            if (!isDouble(secondNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(secondNumber));
                        num1 = Double.parseDouble(firstNumber);
                        num2 = Double.parseDouble(secondNumber);
                        Object[] resultado4 = {num1, num2};
                        Double response4 = (Double) cliente.execute("operaciones.division", resultado4);
                        System.out.println(green+"\nEl resultado es" + response4+reset);



                        break;
                    case 5:
                        System.out.println(yellow+"\n******EXPONENTE*******"+reset);
                        do {
                            System.out.println("ingrese el primer numero");
                            firstNumber = teclado.next();
                            if (!isDouble(firstNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(firstNumber));
                        do {
                            System.out.println("ingrese el segundo numero");
                            secondNumber = teclado.next();
                            if (!isDouble(secondNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(secondNumber));

                        num1 = Double.parseDouble(firstNumber);
                        num2 = Double.parseDouble(secondNumber);
                        Object[] resultado5 = {num1, num2};
                        Double response5 = (Double) cliente.execute("operaciones.exponente", resultado5);
                        System.out.println(green+"\nEl resultado es" + response5+reset);



                        break;
                    case 6:
                        System.out.println(yellow+"\n******RAIZ CUADRADA*******"+reset);
                        do {
                            System.out.println("ingrese el primer numero");
                            firstNumber = teclado.next();
                            if (!isDouble(firstNumber))
                                System.out.println("Ingrese un numero valido.");
                        } while (!isDouble(firstNumber));
                        num1 = Double.parseDouble(firstNumber);

                        Object[] resultado6 = {num1};
                        Double response6 = (Double) cliente.execute("operaciones.raiz", resultado6);
                        System.out.println(green+"\nEl resultado es" + response6+reset);



                        break;
                    case 7:
                        System.out.println(yellow+"\n******************HISTORIAL*********************"+reset);

                        daoOperaciones operaciones= new daoOperaciones();
                        System.out.println(operaciones.showOperations());
                        System.out.println(yellow+"**************************************************"+reset);

                        break;
                    case 8:
                        System.out.println(yellow+"va a salir de nuestra calculadora, gracias por usarla!!"+reset);
                        seguir= false;
                        break;
                    default:
                        System.out.println(red+"no existe esa opcion..."+reset);
                }

            } else {

                System.out.println(red+"opcion incorrecta, intente nuevamente..."+reset);
            }
        } while (seguir);

    }


    public static boolean isNumber(String number) {
        {
            try {
                Integer.parseInt(number);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }

    public static boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

