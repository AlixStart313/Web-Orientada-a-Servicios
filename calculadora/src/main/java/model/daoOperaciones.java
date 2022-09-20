package model;

import utils.Conexion;
import utils.ResultAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoOperaciones<E> {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    private final String GET_OPERATIONS = "SELECT * FROM operations";

    private final String INSERT_OPERATION = "INSERT INTO operations (operation, first_number, second_number, result,create_at) VALUES (?, ?, ?, ?,curdate())";

    public ArrayList<Object> showOperations (){
        ArrayList<Object> personList = new ArrayList<>();
        Object[] operation ;
        try{
            conn = new Conexion().getConnection();
            String query = GET_OPERATIONS;
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()){
                operation= new Object[5];
                String registro;
                operation[0]=rs.getString("operation");
                operation[1]=rs.getDouble("first_number");
                operation[2]=rs.getDouble("second_number");
                operation[3]=(rs.getDouble("result"));
                operation[4]=rs.getDate("create_at");
                registro=operation[0]+" "+operation[1]+" "+operation[2]+" "+operation[3]+" "+operation[4];
                personList.add(registro);
            }

        }catch (SQLException e){
            Logger.getLogger(daoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error en Historial -> ", e);
        }finally {
            closeConnections();
        }
        return personList;
    }


    public boolean saveOperaciones(String operacion, double num1, double num2, double resutado){
        try {
            conn = new Conexion().getConnection();
            String query = INSERT_OPERATION;
            pstm = conn.prepareStatement(query);
            pstm.setString(1, operacion);
            pstm.setDouble(2, num1);
            pstm.setDouble(3, num2);
            pstm.setDouble(4, resutado);
            return pstm.executeUpdate()==1; //1==1
        }catch (SQLException e){
            Logger.getLogger(daoOperaciones.class.getName())
                    .log(Level.SEVERE, "Error al guardar la operacion -> ", e);
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
