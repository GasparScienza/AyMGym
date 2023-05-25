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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.DropMode;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;


public class PantallaRegistro {

	private JFrame frmAlumnos;
	private JTextField txtFechaNac;
	private JTextField txtId;
	private JTextField txtApellido;
	private JTable tblListaAlumnos;
	private JTextField txtNombre;
	private JTextArea txtObservacion;
	private TableRowSorter trsFiltro;
	String filtro;
	
	
	int fila = -1;

	
	Alumnos Al = new Alumnos();
	ArrayList<Alumnos> Lista;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	private JTextField txtFechaIngr;
	

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
		    
		    txtNombre = new JTextField();
		    txtNombre.setBackground(new Color(240, 240, 240));
		    txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		    txtNombre.setBounds(337, 94, 114, 19);
		    txtNombre.setColumns(10);
		    frmAlumnos.getContentPane().add(txtNombre);	 
		    
		    JLabel lblObservacion = new JLabel("Observacion:");
		    lblObservacion.setBounds(20, 256, 147, 90);
		    lblObservacion.setHorizontalAlignment(SwingConstants.RIGHT);
		    lblObservacion.setFont(new Font("Arial", Font.PLAIN, 17));
		    frmAlumnos.getContentPane().add(lblObservacion);
		    
		    JLabel lblFechaIngr = new JLabel("*Fecha de Ingreso:");
		    lblFechaIngr.setBounds(180, 179, 147, 19);
		    lblFechaIngr.setHorizontalAlignment(SwingConstants.RIGHT);
		    lblFechaIngr.setFont(new Font("Arial", Font.PLAIN, 17));
		    frmAlumnos.getContentPane().add(lblFechaIngr);
		    
		    txtFechaIngr = new JTextField();
		    txtFechaIngr.setBackground(new Color(240, 240, 240));
		    txtFechaIngr.setFont(new Font("Arial", Font.PLAIN, 15));
		    txtFechaIngr.setBounds(337, 180, 114, 19);
		    txtFechaIngr.addFocusListener(new FocusAdapter() {
		    	@Override
		    	public void focusLost(FocusEvent e) {
		    		String fechaingr = txtFechaIngr.getText();
				    if (fechaingr.length() == 8) {
				        String formattedFecha = fechaingr.substring(0, 4) + "-" + fechaingr.substring(4, 6) + "-" + fechaingr.substring(6,8);
				        txtFechaIngr.setText(formattedFecha);
				    }
		    	}
		    });
		    txtFechaIngr.setColumns(10);
		    frmAlumnos.getContentPane().add(txtFechaIngr);
		    
		    JLabel lblRegistrodeALumnos = new JLabel("Registro de alumnos");
		    lblRegistrodeALumnos.setBounds(0, 11, 777, 53);
		    lblRegistrodeALumnos.setHorizontalAlignment(SwingConstants.CENTER);
		    lblRegistrodeALumnos.setFont(new Font("Arial", Font.PLAIN, 24));
		    frmAlumnos.getContentPane().add(lblRegistrodeALumnos);
		    
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setBounds(177, 256, 479, 90);
		    frmAlumnos.getContentPane().add(scrollPane);
		    
		    txtObservacion = new JTextArea();
		    txtObservacion.setBackground(new Color(240, 240, 240));
		    scrollPane.setViewportView(txtObservacion);
		    txtObservacion.setLineWrap(true);
		    txtObservacion.setFont(new Font("Arial", Font.PLAIN, 17));
		    
	}
	 

	public void limpiarTabla() {
		txtId.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtFechaNac.setText("");
		txtObservacion.setText("");
		txtFechaIngr.setText("");
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
	    	alumno[4] = a.getFechaIgr();
	    	modeloTabla.addRow(alumno);
	    }
	    tblListaAlumnos.setModel(modeloTabla);
	    
	}

	private void initialize() {
		frmAlumnos = new JFrame();
		frmAlumnos.setResizable(false);
		frmAlumnos.getContentPane().setBackground(new Color(192, 192, 192));
		frmAlumnos.setTitle("ALUMNOS");
		frmAlumnos.setBounds(100, 100, 791, 650);
		frmAlumnos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(476, 94, 117, 25);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtNombre.getText().equals("")||txtApellido.getText().equals("")||txtFechaNac.getText().equals("")||txtFechaIngr.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campos vacios");
						return;
					}
					Alumnos objAl = new Alumnos();
					objAl.insertarAlumnos(txtNombre, txtApellido, txtFechaNac, txtObservacion, txtFechaIngr);
					actTabla();
					limpiarTabla();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		frmAlumnos.getContentPane().setLayout(null);
		frmAlumnos.getContentPane().add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(476, 126, 117, 25);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alumnos alu = new Alumnos();
					alu.modificarAlumnos(txtId, txtNombre, txtApellido, txtFechaNac, txtObservacion, txtFechaIngr);
					actTabla();
			}
		});
		frmAlumnos.getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(476, 158, 117, 25);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Al.eliminarAlumnos(Al.getId());
					actTabla();
				}catch(Exception ex){
					ex.toString();
				}
				limpiarTabla();
			}
		});
		frmAlumnos.getContentPane().add(btnEliminar);
		
		JButton btnLimpiar = new JButton("");
		btnLimpiar.setIcon(new ImageIcon(PantallaRegistro.class.getResource("/Imagenes/Icono Limpiar.png")));
		btnLimpiar.setBounds(476, 190, 117, 50);
		btnLimpiar.setMargin(new Insets(0, 0, 0, 0));
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
			}
		});
		frmAlumnos.getContentPane().add(btnLimpiar);
		
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBackground(new Color(240, 240, 240));
		txtFechaNac.setFont(new Font("Arial", Font.PLAIN, 15));
		txtFechaNac.setBounds(337, 222, 114, 19);
		txtFechaNac.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String fecha = txtFechaNac.getText();
			    if (fecha.length() == 8) {
			        String formattedFecha = fecha.substring(0, 4) + "-" + fecha.substring(4, 6) + "-" + fecha.substring(6,8);
			        txtFechaNac.setText(formattedFecha);
			    }
			}
		});
		frmAlumnos.getContentPane().add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		JLabel lblNombre = new JLabel("*Nombre:");
		lblNombre.setBounds(180, 95, 147, 15);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 17));
		frmAlumnos.getContentPane().add(lblNombre);
		
		txtId = new JTextField();
		txtId.setVisible(false);
		txtId.setEnabled(false);
		txtId.setBounds(787, 10, 114, 19);
		txtId.setColumns(10);
		frmAlumnos.getContentPane().add(txtId);
		
		txtApellido = new JTextField();
		txtApellido.setBackground(new Color(240, 240, 240));
		txtApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		txtApellido.setBounds(337, 136, 114, 19);
		txtApellido.setColumns(10);
		frmAlumnos.getContentPane().add(txtApellido);
		
		JLabel lblApellido = new JLabel("*Apellido:");
		lblApellido.setBounds(180, 137, 147, 15);
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 17));
		frmAlumnos.getContentPane().add(lblApellido);
		
		JLabel lblFecha = new JLabel("*Fecha Nacimiento:");
		lblFecha.setBounds(180, 221, 147, 19);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 17));
		frmAlumnos.getContentPane().add(lblFecha);
		//Se crea las columnas de la tabla

	    //modeloTabla.addColumn("Id");
	    modeloTabla.addColumn("Nombre");
	    modeloTabla.addColumn("Apellido");
	    modeloTabla.addColumn("Fecha de nacimiento");
	    modeloTabla.addColumn("Observacion");
	    modeloTabla.addColumn("Fecha de Ingreso");
	    
	    JTable tabla = new JTable(modeloTabla);
	    tabla.setBackground(new Color(240, 240, 240));
	    tabla.setFont(new Font("Arial", Font.PLAIN, 15));
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
	    		txtFechaIngr.setText(Al.getFechaIgr().toString());

	    	}
	    });
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tabla.setBounds(12, 150, 636, 249);
	    tblListaAlumnos = new JTable();

	    JScrollPane scrollPane = new JScrollPane(tabla);
	    scrollPane.setBounds(20, 367, 735, 229);
	    frmAlumnos.getContentPane().add(scrollPane);
	    //...
	    
		actTabla();
	    
	
	    
	}
}
