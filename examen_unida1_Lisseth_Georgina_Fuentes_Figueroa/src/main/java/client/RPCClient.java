package client;

import model.daoCurp;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class RPCClient {
    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        //lo vamos a utilizar para decirle que utilice nuestro servidor
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        //con esto ya tenemos acceso al servidor


        daoCurp curp = new daoCurp();
        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter("\n");

        boolean seguir= true;
        String dia, mes, anio;
        String opc;
        String green="\033[32m";
        String yellow="\033[33m";
        String reset="\u001B[0m";
        String red="\033[31m";



        do {
            System.out.println(green+"Registro civil"+reset);
            System.out.println("Ingrese la opcion que desea realizar \n 1.Registrar una persona \n 2.Consultar los datos de una persona \n 3.visualizar todos los registros \n4.salir");
            opc= teclado.next();
            switch (opc){
                case "1":
                    System.out.println("Ingrese su nombre");
                    String nombre = teclado.next();
                    System.out.println("Ingrese su Apellido Paterno");
                    String apellido1 = teclado.next();
                    System.out.println("Ingrese su Apellido Materno \n en caso de no tener apellido materno, ingrese una X");
                    String apellido2 = teclado.next();
                    do {
                        System.out.println("ingrese el mes de nacimiento formato de 2 numeros");
                        mes = teclado.next();
                        if (!isNumber(mes))
                            System.out.println("Ingrese un mes del año valido.");
                    } while (!isNumber(mes));
                    do {
                        System.out.println("ingrese el dia de nacimiento en formato de 2 numeros");
                        dia = teclado.next();
                        if (!isNumber(dia))
                            System.out.println("Ingrese un dia del mes valido.");
                    } while (!isNumber(dia));
                    do {
                        System.out.println("ingrese el año de nacimiento formato de 2 numeros");
                        anio = teclado.next();
                        if (!isNumber(anio))
                            System.out.println("Ingrese un año valido.");
                    } while (!isNumber(anio) );
                    System.out.println("Ingrese el codigo  del estado en el que nacio " +
                            "\n1.Aguascalientes #AS " +
                            "\n2.Baja California #BC" +
                            "\n3.Baja California Sur #BCS" +
                            "\n4.Campeche #CC" +
                            "\n5.Chiapas #CS" +
                            "\n6.Ciudad de Mexico #DF" +
                            "\n7.Coahuila #CL" +
                            "\n8.Chihuahua #CH" +
                            "\n9.Colima #CM" +
                            "\n10.Durango #DG" +
                            "\n11.Guanajuato #GT" +
                            "\n12.Guerrero #GR" +
                            "\n13.Hidalgo #HG" +
                            "\n14.Jalisco #JC" +
                            "\n15.Estado de Mexico #MC" +
                            "\n16.Michoacan #MN" +
                            "\n17.Morelos MS" +
                            "\n18.Nayarit #NT" +
                            "\n19.Nuevo Leon #NL" +
                            "\n20.Oaxaca #OC" +
                            "\n21.Puebla #PL" +
                            "\n22.Queretaro #QO" +
                            "\n23.Quintana ROO #QR" +
                            "\n24.San Luis Potosi #SP" +
                            "\n25.Sinaloa #SL" +
                            "\n26.Sonora #SR" +
                            "\n27.Tabasco #TC" +
                            "\n28.Tamaulipas #TS" +
                            "\n29.Tlaxcala #TL" +
                            "\n30.Veracruz #VZ" +
                            "\n31.Yucatan #YN" +
                            "\n32.Zacatecas #ZS" +
                            "\n en caso de haber nacido en el extranjero, escribe NE");
                    String estado = teclado.next();
                    System.out.println("Ingrese su sexo");
                    String sexo= teclado.next();


                    Object[] data = {nombre, apellido1, apellido2, dia, mes, anio,sexo,estado};
                    String response = (String) client.execute("Methods.curp", data);
                    System.out.println("Su CURP es " + response);
                    String fecha=dia+"-"+mes+"-"+anio;
                    boolean registro = curp.saveOperaciones(response,nombre,apellido1,apellido1,fecha,sexo,estado);
                    if (registro)
                        System.out.println(green + "Curp guardada en el historial" + reset);


                    break;
                case "2":
                    System.out.println("Ingrese la curp de la persona");
                    String busquedas= teclado.next();
                    busquedas=busquedas.toUpperCase();
                    Object[] busqueda = {busquedas};
                    String respuesta= (String) client.execute("Methods.busquedas", busqueda);
                    System.out.println(green+"**********REGISTRO***************"+reset);
                    System.out.println(green+"        CURP        |   Nombre    |  Apellido Paterno  |  Apellido Materno |  Fecha de Nacimiento |   Sexo   | Estado de nacimiento\n"+reset);
                    System.out.println(respuesta);
                    break;
                case "3":
                    System.out.println(yellow+"******************HISTORIAL*********************"+reset);
                    System.out.println(green+"         CURP        |   Nombre    |  Apellido Paterno  |  Apellido Materno |  Fecha de Nacimiento |   Sexo   | Estado de nacimiento\n"+reset);
                    Object[] registros = {""};
                    String registrosCurps = (String) client.execute("Methods.consultar", registros);
                    System.out.println( registrosCurps);
                    break;
                case "4":
                    seguir=false;
                    System.out.println("Gracias por usar nuestro registro civil");
                    break;
                default:
                    System.out.println(red+"opcion invalida"+reset);

            }
            if (!isNumber(opc))
                System.out.println(red+"Ingrese un numero valido"+reset);
        } while (!isNumber(opc) || seguir);


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

}
