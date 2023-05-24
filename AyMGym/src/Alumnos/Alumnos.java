package Alumnos;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Conexion.Conexion;

public class Alumnos {
	int id;
	String nombreA;
	String apellidoA;
	Date fecha;
	String observacion;
	Date fechaIgr;
	
	
	
	public Date getFechaIgr() {
		return fechaIgr;
	}
	public void setFechaIgr(Date fechaIgr) {
		this.fechaIgr = fechaIgr;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreA() {
		return nombreA;
	}
	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}
	public String getApellidoA() {
		return apellidoA;
	}
	public void setApellidoA(String apellidoA) {
		this.apellidoA = apellidoA;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void insertarAlumnos(JTextField paramNombres, JTextField paramApellidos, JTextField paramFechaNac, JTextField paramObservacion, JTextField paramFechaIngr) throws ParseException{          
	        setNombreA(paramNombres.getText()); 
	        setApellidoA(paramApellidos.getText()); 	        
	        String fechaStr = paramFechaNac.getText(); 
	        LocalDate fecha = LocalDate.parse(fechaStr); 
	        java.sql.Date sqlDate = java.sql.Date.valueOf(fecha); 	
	        setObservacion(paramObservacion.getText()); 	
	        String fechaIn = paramFechaIngr.getText(); 
	        LocalDate fechai = LocalDate.parse(fechaIn); 
	        java.sql.Date sqlDate1 = java.sql.Date.valueOf(fechai);

	        Conexion objConexion = new Conexion(); 	         
	        String consulta = "INSERT INTO alumnos VALUES (default,?,?,?,?,?);"; 	       
	        try {  
	            CallableStatement cs = objConexion.obtConexion().prepareCall(consulta); 	             
	            cs.setString(1, getNombreA()); 
	            cs.setString(2, getApellidoA()); 
	            cs.setDate(3, sqlDate); 
	            cs.setString(4, getObservacion()); 
	            cs.setDate(5, sqlDate1); 

	            cs.execute(); 
	            JOptionPane.showMessageDialog(null, "Se inserto correctamente");        

	        } catch (Exception e) { 
	        	JOptionPane.showMessageDialog(null, "Error: " + e.toString()); 
	        } 
	        objConexion.desconectar();
	    }
	public void modificarAlumnos(JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramFechaNac, JTextField paramObservacion, JTextField paramFechaIngr) {
	    try {
	        setId(Integer.parseInt(paramId.getText().trim()));
	        setNombreA(paramNombres.getText());
	        setApellidoA(paramApellidos.getText());

	        String fechaStr = paramFechaNac.getText();
	        LocalDate fecha = LocalDate.parse(fechaStr.trim());
	        java.sql.Date sqlDate = java.sql.Date.valueOf(fecha);

	        setObservacion(paramObservacion.getText());
	        
	        String fechaIn = paramFechaIngr.getText();
	        LocalDate fechai = LocalDate.parse(fechaIn.trim());
	        java.sql.Date sqlDate1 = java.sql.Date.valueOf(fechai);
	        
	        Conexion objConexion = new Conexion();
	        String consulta = "UPDATE alumnos SET nombre=?, apellido=?, fecha_nac=?, observacion=?, fecha_ingr=? WHERE id=?;";

	        try (PreparedStatement ps = objConexion.obtConexion().prepareStatement(consulta)) {
	            ps.setString(1, getNombreA());
	            ps.setString(2, getApellidoA());
	            ps.setDate(3, sqlDate);
	            ps.setString(4, getObservacion());
	            ps.setDate(5, sqlDate1);
	            ps.setInt(6, getId());
	            ps.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Se modificó correctamente");
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + ex.toString());
	        } finally {
	            objConexion.desconectar();
	        }
	    } catch (NumberFormatException ex) {
	        
	        System.out.println(ex.toString());
	    }
	}

	public void eliminarAlumnos(int Id) throws ParseException{          	            	          
        Conexion objConexion = new Conexion(); 	         
        String consulta = "DELETE FROM alumnos WHERE id=?;"; 	       
        try {  
            CallableStatement cs = objConexion.obtConexion().prepareCall(consulta); 	             
            cs.setInt(1, getId());   
            cs.execute(); 
            objConexion.desconectar();
            JOptionPane.showMessageDialog(null, "Se eliminno correctamente");        

        } catch (Exception e) { 
        	JOptionPane.showMessageDialog(null, "Error: " + e.toString()); 
        } 
 
    }
	public ArrayList<Alumnos> consultaUsuarios(){
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		 Conexion objConexion = new Conexion(); 	         
	     String consulta = "SELECT*FROM alumnos;"; 	  
		 ResultSet rs = null;
		try {
			CallableStatement cs = objConexion.obtConexion().prepareCall(consulta);
			rs =cs.executeQuery();
			while(rs.next()) {
				Alumnos Al = new Alumnos();
				Al.setId(rs.getInt("id"));
				Al.setNombreA(rs.getString("nombre"));
				Al.setApellidoA(rs.getString("apellido"));
				Al.setFecha(rs.getDate("fecha_nac"));
				Al.setObservacion(rs.getString("observacion"));
				Al.setFechaIgr(rs.getDate("fecha_ingr"));
				lista.add(Al);
			}
			objConexion.desconectar();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return lista;
	}
}
