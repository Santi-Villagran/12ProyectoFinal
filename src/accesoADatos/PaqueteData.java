/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoADatos;

import entidades.Paquete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class PaqueteData {
    
    Connection con=null;
    PasajeData pasData;
    AlojamientoData aloData;
    CiudadData ciuData;
    
    public PaqueteData(){
        con=Conexion.getConexion();
    }
    
    public void guardarPaquete(Paquete paquete){
        String sql="INSERT INTO Paquete ( idCiudad_origen, idCiudad_destino, idAlojamiento, idPasaje)"
                + "VALUES (?,?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, paquete.getOrigen().getIdCiudad());
            ps.setInt(2, paquete.getDestino().getIdCiudad());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasaje());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                paquete.setIdPaquete(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "El paquete de viaje se creo con exito");
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocurrio un error al acceder a la base de datos");
        }
    }
    
    public void modificarPaquete(Paquete paquete){
        String sql="UPDATE paquete SET idCiudad_origen=?, idCiudad_destino=?, idAlojamiento=?, idPasaje=? "
                + "WHERE idPaquete=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, paquete.getOrigen().getIdCiudad());
            ps.setInt(2, paquete.getDestino().getIdCiudad());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasaje());
            ps.setInt(5,paquete.getIdPaquete());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "El paquete de viaje se modifico correctamente");
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocurrio un error al acceder a la base de datos");
        }
    }
    
    public void eliminarPaquete (int id){
        String sql="UPDATE paquete SET estado=false WHERE idPaquete=?";
        try{
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "El paquete se dio de baja");
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error inesperado");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocurrio un error al acceder a la base de datos");
        }
    }
    
    public Paquete buscarPaquete(int id){
        String sql ="SELECT* FROM paquete WHERE idPaquete=?";
        Paquete paquete=new Paquete();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                /*paquete.setOrigen(ciuData.buscarCiudad(rs.getInt("idCiudad_origen")));
                paquete.setDestino(ciuData.buscarCiudad(rs.getInt("idCiudad_destino")));
                paquete.setAlojamiento(aloData.buscarAlojamiento(rs.getInt("idAlojamiento")));
                paquete.setPasaje(pasData.buscarPasaje(rs.getInt("idPasaje")));*/
            }else{
                JOptionPane.showMessageDialog(null,"No se encontro el paquete");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
        return paquete;
    }
    
    public List<Paquete> listarPaquetesPorOrigen(int idCiudad){
        
        String sql ="SELECT* FROM paquete WHERE idCiudad_origen=?";
        List<Paquete> paquetes = new ArrayList<>();
        Paquete paquete=null;
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, idCiudad);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                paquete=new Paquete();
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                /*paquete.setOrigen(ciuData.buscarCiudad(rs.getInt("idCiudad_origen")));
                paquete.setDestino(ciuData.buscarCiudad(rs.getInt("idCiudad_destino")));
                paquete.setAlojamiento(aloData.buscarAlojamiento(rs.getInt("idAlojamiento")));
                paquete.setPasaje(pasData.buscarPasaje(rs.getInt("idPasaje")));*/
                paquetes.add(paquete);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
        return paquetes;
    }
    
    public List<Paquete> listarPaquetes(){
        String sql ="SELECT* FROM paquete";
        List<Paquete> paquetes = new ArrayList<>();
        Paquete paquete=null;
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                paquete=new Paquete();
                paquete.setIdPaquete(rs.getInt("idPaquete"));
                /*paquete.setOrigen(ciuData.buscarCiudad(rs.getInt("idCiudad_origen")));
                paquete.setDestino(ciuData.buscarCiudad(rs.getInt("idCiudad_destino")));
                paquete.setAlojamiento(aloData.buscarAlojamiento(rs.getInt("idAlojamiento")));
                paquete.setPasaje(pasData.buscarPasaje(rs.getInt("idPasaje")));*/
                paquetes.add(paquete);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos");
        }
        return paquetes;
    }
    
    
}
