package partejuanandres;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import paqueteprincipal.BaseDatos;
import paqueteprincipal.ClickEmpleados;
import paqueteprincipal.ventanaPrincipal;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PanelEmp extends JInternalFrame{

	private int identificador;
	
	private  JLabel [] etiquetas;
	private JTextField [] campos;
	private JButton buscar;
	private JButton accion;
	
	public PanelEmp (int i,ventanaPrincipal v) throws ClassNotFoundException, SQLException{
		this.getContentPane().setLayout(new GridLayout(5,2));
		
		
		identificador=i;
		etiquetas=new JLabel[4];
		campos=new JTextField[4];
		buscar=new JButton("Buscar");
		accion=new JButton("");
		
		etiquetas[0]=new JLabel("Codigo: ");
		campos[0]=new JTextField();
		this.getContentPane().add(etiquetas[0]);
		this.getContentPane().add(campos[0]);
		etiquetas[1]=new JLabel("Nombre: ");
		campos[1]=new JTextField();
		this.getContentPane().add(etiquetas[1]);
		this.getContentPane().add(campos[1]);
		etiquetas[2]=new JLabel("DNI: ");
		campos[2]=new JTextField();
		this.getContentPane().add(etiquetas[2]);
		this.getContentPane().add(campos[2]);
		etiquetas[3]=new JLabel("Salario");
		campos[3]=new JTextField();
		this.getContentPane().add(etiquetas[3]);
		this.getContentPane().add(campos[3]);
		
		JPanel pbuscar=new JPanel(new FlowLayout());
		
		buscar.setPreferredSize(new Dimension(100,50));
		buscar.setForeground(Color.white);
		
		buscar.setBackground(new Color(122, 108, 67));
		buscar.setActionCommand("search");
		buscar.addActionListener(new ClickEmpleados(v,this));
		pbuscar.add(buscar);
		pbuscar.setBackground(new Color(211, 211, 190));
		
		this.getContentPane().add(pbuscar);
		
		JPanel paccion=new JPanel(new FlowLayout());
		

		accion.setPreferredSize(new Dimension(200,50));
		accion.setForeground(Color.white);
		accion.setFont(new Font("Arial Black",Font.BOLD,15));
		accion.setBackground(new Color(122, 108, 67));
		accion.setActionCommand("acciones");
		accion.addActionListener(new ClickEmpleados(v,this));
		paccion.add(accion);
		paccion.setBackground(new Color(211, 211, 190));
		
		this.getContentPane().add(paccion);
		
		
		for (JLabel x:etiquetas)
			{x.setFont(new Font("Arial Black",Font.BOLD,14));
			x.setHorizontalAlignment(JLabel.CENTER);
			}
		
		modTextoBotton();
		
		this.setVisible(true);
		
	}
	public int getIdentificador () {
		return identificador;
	}
	private void modTextoBotton() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		switch (identificador) {
		case 1:accion.setText("Insertar Empleado");
			this.setTitle("Panel - Agregar Nuevo Empleado");
				campos[0].setEnabled(false);
			   buscar.setVisible(false);
			   buscar.setEnabled(false);
			   activarcampos();
			   ultimoCodigo();
			   
		break;
		case 2:accion.setText("Borrar Empleado");
	this.setTitle("Panel - Borrar Empleado");
	campos[0].setEnabled(true);
		 buscar.setVisible(true);
		   buscar.setEnabled(true);
		   desactivarcampos();
		break;
		case 3:accion.setText("Modificar Empleado");
		this.setTitle("Panel - Modificar datos de Empleado");
		campos[0].setEnabled(true);
		buscar.setVisible(true);
		   buscar.setEnabled(true);
		   desactivarcampos();
		break;
		}
	}
	public void desactivarcampos () {
		for (int i=1;i<campos.length;i++)
			campos[i].setEnabled(false);
	}
	public void activarcampos () {
		for (int i=1;i<campos.length;i++)
			campos[i].setEnabled(true);
	}
	public void vaciarcampos () {
		for (JTextField i:campos)
			i.setText("");
	}
	public void verificarcampos () throws ClassNotFoundException, SQLException {
		
		if (campos[0].getText().isEmpty()||
				campos[1].getText().isEmpty()||
				campos[2].getText().isEmpty()||
				campos[3].getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios");
		}
		else {
			if (!esDecimal(campos[3].getText()))
				JOptionPane.showMessageDialog(this, "El campo SALARIO debe ser un numero");
			else {insertarEmpleado();
				  vaciarcampos();
				  modTextoBotton();
				  
			}
		}
		
	}
	public boolean esDecimal (String n) {
		try {
			Double.parseDouble(n);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
		
	}
	public boolean esEntero (String n) {
		try {
			Integer.parseInt(n);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
		
	}
	
	public void ultimoCodigo () throws ClassNotFoundException, SQLException {
		int lastid=0;
		
		BaseDatos bd=new BaseDatos();
		ResultSet rs=bd.ejecutarSQL1("SELECT * FROM empleado ORDER BY id DESC LIMIT 1");
		if (rs.next())
			lastid=rs.getInt(1);
		
		lastid++;
		campos[0].setText(String.valueOf(lastid));
		bd.cerrarConex();
		
	}
	public void insertarEmpleado () throws ClassNotFoundException, SQLException {
		BaseDatos bd=new BaseDatos();
		bd.ejecutarSQL2("INSERT INTO empleado VALUES ("+Integer.parseInt(campos[0].getText().toString())+",'"+campos[1].getText().toString()+"','"+campos[2].getText().toString()+"',"+Double.parseDouble(campos[3].getText().toString())+")");
		bd.cerrarConex();
	}
	public boolean buscarempleado () throws ClassNotFoundException, SQLException {
		
		if (campos[0].getText().isEmpty())
			{JOptionPane.showMessageDialog(this,"El campo CODIGO no puede estar vacio");
			return false;}
		else if (!esEntero(campos[0].getText().toString()))
			{JOptionPane.showMessageDialog(this,"El campo CODIGO debe ser un numero");
			return false;}
		else {

			BaseDatos bd=new BaseDatos();
			ResultSet rs=bd.ejecutarSQL1("SELECT * FROM empleado WHERE id = "+Integer.parseInt(campos[0].getText().toString())+"");
			if (rs.next())
				{rellenarcampos(rs);
				return true;
				}
			else {JOptionPane.showMessageDialog(this,"No existe un Empleado con ese codigo");
				return false;}
			

		}
				
		
	}
	public void rellenarcampos(ResultSet rs) throws SQLException {
		
		campos[1].setText(rs.getString(2));
		campos[2].setText(rs.getString(3));
		campos[3].setText(String.valueOf(rs.getDouble(4)) );
		
		
		// TODO Auto-generated method stub
		
	}
}
