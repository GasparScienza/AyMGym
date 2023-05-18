package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	Connection cn;
	Statement st;
	ResultSet rs;
	
	public Connection obtConexion() {
		try {
			Class.forName("org.h2.Driver");
			cn = DriverManager.getConnection("jdbc:h2:./lib/AyMGym","sa","");
			st = cn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectar la base de datos: " + e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cn;
		
	}
	public void desconectar() {
		try {
			cn.close();
		} catch (SQLException e) {
			System.out.println("Error al desconectar la base de datos: " + e);
		}
	}
	
	public void probar() {
		cn = obtConexion();
	    try {
	        rs = st.executeQuery("SELECT * FROM alumnos");
	        while(rs.next()) {
	            System.out.println("ID: "+rs.getInt("id")+"\t"+"Nombre: "+rs.getString("nombre")+"\t"+"Apellido: "+rs.getString("apellido")+"\t"+"Fecha: "+rs.getString("fecha_nac"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (cn != null) {
	                cn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
			
