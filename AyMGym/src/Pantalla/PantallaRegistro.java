package Pantalla;

import Alumnos.Alumnos;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaRegistro {

	private JFrame frmAlumnos;
	private JTextField txtFechaNac;
	private JTextField txtId;
	private JTextField txtApellido;
	private JTable tblListaAlumnos;
	private JTextField txtNombre;
	
	int fila = -1;

	
	Alumnos Al = new Alumnos();
	ArrayList<Alumnos> Lista;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	private JTextField txtObservacion;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaRegistro window = new PantallaRegistro();
					window.frmAlumnos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PantallaRegistro() {
		  initialize();
		   txtId.setEnabled(false);
		    
		    txtNombre = new JTextField();
		    txtNombre.setColumns(10);
		    txtNombre.setBounds(331, 39, 114, 19);
		    frmAlumnos.getContentPane().add(txtNombre);	 
		    
		    JLabel lblObservacion = new JLabel("Observacion:");
		    lblObservacion.setBounds(76, 118, 104, 15);
		    frmAlumnos.getContentPane().add(lblObservacion);
		    
		    txtObservacion = new JTextField();
		    txtObservacion.setBounds(185, 116, 505, 54);
		    frmAlumnos.getContentPane().add(txtObservacion);
		    txtObservacion.setColumns(10);
		 
	}
	 

	public void limpiarTabla() {
		txtId.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtFechaNac.setText("");
		txtObservacion.setText("");
	}

	public void actTabla() {
	    //Mostrar Registros de la base de datos
	    while(modeloTabla.getRowCount()>0) {
	    	modeloTabla.removeRow(0);
	    }
	    Lista = Al.consultaUsuarios();
	    for(Alumnos a:Lista) {
	    	Object alumno[] = new Object[5];
	    	//alumno[0] = a.getId();
	    	alumno[0] = a.getNombreA();
	    	alumno[1] = a.getApellidoA();
	    	alumno[2] = a.getFecha();
	    	alumno[3] = a.getObservacion();
	    	modeloTabla.addRow(alumno);
	    }
	    tblListaAlumnos.setModel(modeloTabla);
	    
	}

	private void initialize() {
		frmAlumnos = new JFrame();
		frmAlumnos.setTitle("ALUMNOS");
		frmAlumnos.setBounds(100, 100, 810, 508);
		frmAlumnos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlumnos.getContentPane().setLayout(null);
		
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtNombre.getText().equals("")||txtApellido.getText().equals("")||txtFechaNac.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campos vacios");
						return;
					}
					Alumnos objAl = new Alumnos();
					objAl.insertarAlumnos(txtNombre, txtApellido, txtFechaNac, txtObservacion);
					actTabla();
					limpiarTabla();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(142, 182, 117, 25);
		frmAlumnos.getContentPane().add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alumnos alu = new Alumnos();
					alu.modificarAlumnos(txtId, txtNombre, txtApellido, txtFechaNac, txtObservacion);
					actTabla();
			}
		});
		btnModificar.setBounds(271, 182, 117, 25);
		frmAlumnos.getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Al.eliminarAlumnos(Al.getId());
					actTabla();
				}catch(Exception ex){
					ex.toString();
				}
			}
		});
		btnEliminar.setBounds(400, 182, 117, 25);
		frmAlumnos.getContentPane().add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
			}
		});
		btnLimpiar.setBounds(529, 182, 117, 25);
		frmAlumnos.getContentPane().add(btnLimpiar);
		
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(366, 77, 114, 19);
		frmAlumnos.getContentPane().add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		JLabel lblNombre = new JLabel("*Nombre:");
		lblNombre.setBounds(262, 41, 70, 15);
		frmAlumnos.getContentPane().add(lblNombre);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(100, 39, 114, 19);
		frmAlumnos.getContentPane().add(txtId);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(76, 41, 70, 15);
		frmAlumnos.getContentPane().add(lblId);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(576, 39, 114, 19);
		frmAlumnos.getContentPane().add(txtApellido);
		
		JLabel lblApellido = new JLabel("*Apellido:");
		lblApellido.setBounds(507, 41, 70, 15);
		frmAlumnos.getContentPane().add(lblApellido);
		
		JLabel lblFecha = new JLabel("*Fecha Nacimiento:");
		lblFecha.setBounds(224, 68, 137, 36);
		frmAlumnos.getContentPane().add(lblFecha);
		//Se crea las columnas de la tabla

	    //modeloTabla.addColumn("Id");
	    modeloTabla.addColumn("Nombre");
	    modeloTabla.addColumn("Apellido");
	    modeloTabla.addColumn("Fecha de nacimiento");
	    modeloTabla.addColumn("Observacion");
	    
	    JTable tabla = new JTable(modeloTabla);
	    tabla.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		fila = tabla.getSelectedRow();
	    		Al = Lista.get(fila);
	    		txtId.setText(" " + Al.getId());
	    		txtNombre.setText(Al.getNombreA());
	    		txtApellido.setText(Al.getApellidoA());
	    		txtFechaNac.setText(Al.getFecha().toString());
	    		txtObservacion.setText(Al.getObservacion());
	    	}
	    });
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tabla.setBounds(12, 150, 636, 249);
	    tblListaAlumnos = new JTable();

	    JScrollPane scrollPane = new JScrollPane(tabla);
	    scrollPane.setBounds(36, 219, 735, 249);
	    frmAlumnos.getContentPane().add(scrollPane);
	    //...
	    
		actTabla();
	    
	
	    
	}
}
