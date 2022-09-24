package model;

import utils.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoCurp {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    private final String GET_OPERATIONS = "SELECT * FROM curps";

    private final String GET_PERSON = "SELECT * FROM curps where curp = ?";
    private final String INSERT_OPERATION = "INSERT INTO curps (curp, nombre, apellido1, apellido2,fecha_de_nacimiento,sexo,estado_de_nacimiento) " +
            "VALUES (?, ?, ?, ?,?,?,?)";


    public List<Object> showperson(String busqueda){
        List<Object> persona = new LinkedList<>();
        Object[] operation ;
        try {
            conn = new conexion().getConnection();
            String query = GET_PERSON;
            pstm = conn.prepareStatement(query);
            pstm.setString(1,busqueda);
            rs = pstm.executeQuery();
            String registro="Ningun registro coincide con esa curp";
            while (rs.next()){
                operation= new Object[7];

                operation[0]=rs.getString("curp");
                operation[1]=rs.getString("nombre");
                operation[2]=rs.getString("apellido1");
                operation[3]=rs.getString("apellido2");
                operation[4]=rs.getString("fecha_de_nacimiento");
                operation[5]=rs.getString("sexo");
                operation[6]=rs.getString("estado_de_nacimiento");

                registro=operation[0]+" | "+operation[1]+" | "+operation[2]+" | "+operation[3]+" | "+operation[4]+" | "+operation[5]+" | "+operation[6];

            }
            persona.add(registro);

        }catch (SQLException e){
            Logger.getLogger(daoCurp.class.getName())
                    .log(Level.SEVERE, "Error en findPerson -> ", e);

        }finally {
            closeConnections();
        }
        return persona;
    }
    public ArrayList<Object> showpersons (){
        ArrayList<Object> personList = new ArrayList<>();
        Object[] operation ;
        try{
            conn = new conexion().getConnection();
            String query = GET_OPERATIONS;
            pstm = conn.prepareStatement(query);

            rs = pstm.executeQuery();

            while (rs.next()){
                operation= new Object[7];
                String registro;
                operation[0]=rs.getString("curp");
                operation[1]=rs.getString("nombre");
                operation[2]=rs.getString("apellido1");
                operation[3]=rs.getString("apellido2");
                operation[4]=rs.getString("fecha_de_nacimiento");
                operation[5]=rs.getString("sexo");
                operation[6]=rs.getString("estado_de_nacimiento");

                registro=operation[0]+" | "+operation[1]+" | "+operation[2]+" | "+operation[3]+" | "+operation[4]+" | "+operation[5]+" | "+operation[6]+"\n";
                personList.add(registro);
            }

        }catch ( SQLException e){
            Logger.getLogger(daoCurp.class.getName())
                    .log(Level.SEVERE, "Error en Historial -> ", e);
        }finally {
            closeConnections();
        }
        return personList;
    }


    public boolean saveOperaciones(String curp ,String nombre, String apellido1, String apellido2, String fecha, String sexo, String estado){
        try {
            conn = new utils.conexion().getConnection();
            String query = INSERT_OPERATION;
            pstm = conn.prepareStatement(query);
            pstm.setString(1,curp);
            pstm.setString(2, nombre);
            pstm.setString(3, apellido1);
            pstm.setString(4, apellido2);
            pstm.setString(5, fecha);
            pstm.setString(6,sexo);
            pstm.setString(7,estado);
            return pstm.executeUpdate()==1;
        }catch (SQLException e){
            Logger.getLogger(daoCurp.class.getName())
                    .log(Level.SEVERE, "Error al generar la curp -> ", e);
            return false;
        } finally {
            closeConnections();
        }
    }
    public void closeConnections(){
        try{
            if (conn!= null){
                conn.close();
            }
            if (pstm!= null){
                pstm.close();
            }
            if (rs!= null){
                rs.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

