package server;

import model.daoCurp;

public class Methods {
    private String curp;

    daoCurp curps = new daoCurp();

    public String curp(String nombre, String apellido1, String apellido2, String dia, String mes, String anio, String sexo, String estado) {
        String letras,letra1,letra2,letra3;
        int x=1;

        boolean consonante=true;
        do{
            letra1=nombre.charAt(x)+"";
            letra1=letra1.toUpperCase();
            System.out.println("nombre"+letra1);
            switch (letra1){
                case "A":
                case "E":
                case "I":
                case "O":
                case "U":
                    x++;
                    letra1=nombre.charAt(x)+"";
                    System.out.println("nombre"+letra1);
                    break;
                default:consonante=false;
            }
        }while(consonante);

        x=1;

         consonante=true;
        do{
            letra2=apellido1.charAt(x)+"";
            letra2=letra2.toUpperCase();
            System.out.println("apellido 1"+letra2);
            switch (letra2){
                case "A":
                case "E":
                case "I":
                case "O":
                case "U":
                    x++;
                    letra2=apellido1.charAt(x)+"";
                    System.out.println("apellido 1"+letra2);
                    break;
                default:consonante=false;
            }
        }while(consonante );

        x=1;

        do{
            apellido2=apellido2+"X";
            letra3=apellido2.charAt(x)+"";
            letra3=letra3.toUpperCase();
            System.out.println("apellido 2"+letra3);
            consonante=true;
            switch (letra3){
                case "A":
                case "E":
                case "I":
                case "O":
                case "U":
                    x++;
                    letra3=apellido2.charAt(x)+"";
                    System.out.println("apellido 2"+letra3);
                    break;
                default:consonante=false;
            }
        }while(consonante);

        letras=letra2+letra3+letra1;
        String[] abc = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        int numero = (int) (Math.random() * 26) + 1;
        int numero2 = (int) (Math.random() * 9) + 1;
        sexo=sexo.toUpperCase();
        if (sexo.equals("MUJER")) {
            sexo = "M";
        } else {
            sexo = "H";
        }

        String minusculas = apellido1.substring(0, 2) + apellido2.charAt(0)+nombre.charAt(0) + anio + mes + dia +sexo+estado+letras+abc[numero]+numero2;

        curp=minusculas.toUpperCase();
        return curp;
    }

    public String busquedas(String busqueda){
        String persona;
        persona= String.valueOf(curps.showperson(busqueda));
        return persona;
    }

}
