/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoADatos;

import entidades.Paquete;
import entidades.Pasaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class PasajeData {
    private Connection con=null;
    private Pasaje pasaje;
    private CiudadData ciudata;

    public PasajeData() {
        con=Conexion.getConexion();
    }
    
    public void guardarPasaje(Pasaje pasaje){
        String sql="INSERT INTO paquete (idCiudad, costo, tipo, estado)VALUES(?,?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pasaje.getCiudadOrigen().getIdCiudad());
            ps.setDouble(2, pasaje.getCosto());
            ps.setString(3, pasaje.getTipoTransporte());
            ps.setBoolean(4,pasaje.isActivo());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                pasaje.setIdPasaje(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El pasaje ingresado se creo con exito");
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado");
            }
            ps.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
    }
    
    public void modificarPasaje(Pasaje pasaje){
         String sql="UPDATE pasaje SET idCiudad=?, costo=?, tipo=?, estado=? "
                + "WHERE idPasaje=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pasaje.getCiudadOrigen().getIdCiudad());
            ps.setDouble(2, pasaje.getCosto());
            ps.setString(3, pasaje.getTipoTransporte());
            ps.setBoolean(4, pasaje.isActivo());
            ps.setInt(5,pasaje.getIdPasaje());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "El pasaje se modifico correctamente");
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocurrio un error al acceder a la base de datos");
        }
    }
    
    public void eliminarPasaje(int id){
        String sql="UPDATE pasaje SET estado=false WHERE idPasaje=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Se dio de baja el pasaje");
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocurrio un error al acceder a la base de datos");
        }
    }
    
    public Pasaje buscarPasaje(int id){
        String sql ="SELECT* FROM paquete WHERE idPaquete=?";
        Pasaje pasaje=new Pasaje();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                pasaje.setIdPasaje(rs.getInt("idPasaje"));
                //pasaje.setCiudadOrigen(ciudata.buscarCiudad(rs.getInt("idCiudad")));
                pasaje.setCosto(rs.getDouble("costo"));
                pasaje.setTipoTransporte(rs.getString("tipo"));
                pasaje.setActivo(rs.getBoolean("estado"));
            }else{
                JOptionPane.showMessageDialog(null,"No se encontro el pasaje");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
        return pasaje;
    }
    
    public List<Pasaje> listarPasajes(){
        String sql ="SELECT* FROM pasaje";
        List<Pasaje> pasajes = new ArrayList<>();
        
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                pasaje=new Pasaje();
                pasaje.setIdPasaje(rs.getInt("idPasaje"));
               // pasaje.setCiudadOrigen(ciudata.buscarCiudad(rs.getInt("idCiudad")));
                pasaje.setCosto(rs.getDouble("costo"));
                pasaje.setTipoTransporte(rs.getString("tipo"));
                pasaje.setActivo(rs.getBoolean("estado"));
                pasajes.add(pasaje);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
        return pasajes;
    }
}
