package partejuanandres;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Conexiones.BaseDatos;
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
	private JCheckBox confirmacion;
	
	public PanelEmp (int i,ventanaPrincipal v) throws ClassNotFoundException, SQLException{
		this.getContentPane().setLayout(new GridLayout(5,2,5,5));
		
		
		
		
		
		identificador=i;
		etiquetas=new JLabel[4];
		campos=new JTextField[4];
		buscar=new JButton("Buscar");
		accion=new JButton("");
		confirmacion=new JCheckBox("Los datos son correctos");
		confirmacion.setBackground(new Color(211, 211, 190));
		confirmacion.addItemListener(new Confirmado(this));
		etiquetas[0]=new JLabel("Codigo: ");
		campos[0]=new JTextField();
		campos[0].setBorder(new LineBorder(new Color(122, 108, 67), 5));
		
		this.getContentPane().add(etiquetas[0]);
		this.getContentPane().add(campos[0]);
		
		etiquetas[1]=new JLabel("Nombre: ");
		campos[1]=new JTextField();
		campos[1].setBorder(new LineBorder(new Color(122, 108, 67), 5));
		
		this.getContentPane().add(etiquetas[1]);
		this.getContentPane().add(campos[1]);
		
		etiquetas[2]=new JLabel("DNI: ");
		campos[2]=new JTextField();
		campos[2].setBorder(new LineBorder(new Color(122, 108, 67), 5));
		
		this.getContentPane().add(etiquetas[2]);
		this.getContentPane().add(campos[2]);
		
		etiquetas[3]=new JLabel("Salario");
		campos[3]=new JTextField();
		campos[3].setBorder(new LineBorder(new Color(122, 108, 67), 5));
		this.getContentPane().add(etiquetas[3]);
		this.getContentPane().add(campos[3]);
		
		for (JTextField x:campos) {
			x.setFont(new Font("Arial Black",Font.PLAIN,15));
			x.addFocusListener(new FocoEnTextos(this));
			
		}
		
		
		
		JPanel pbuscar=new JPanel(new FlowLayout());
		
		buscar.setPreferredSize(new Dimension(100,50));
		buscar.setForeground(Color.white);
		
		buscar.setBackground(new Color(122, 108, 67));
		buscar.setActionCommand("search");
		buscar.addActionListener(new ClickEmpleados(v,this));
		buscar.setFont(new Font("Arial Black",Font.BOLD,15));
		pbuscar.add(buscar);
		pbuscar.add(confirmacion);
		pbuscar.setBackground(new Color(211, 211, 190));
		
		this.getContentPane().add(pbuscar);
		
		JPanel paccion=new JPanel(new FlowLayout());
		

		accion.setPreferredSize(new Dimension(250,50));
		accion.setForeground(Color.white);
		accion.setFont(new Font("Arial Black",Font.BOLD,15));
		accion.setBackground(new Color(122, 108, 67));
		accion.setActionCommand("acciones");
		accion.addActionListener(new ClickEmpleados(v,this));
		paccion.add(accion);
		
		
		paccion.setBackground(new Color(211, 211, 190));
		
		this.getContentPane().add(paccion);
		
		
		for (JLabel x:etiquetas)
			{x.setFont(new Font("Arial Black",Font.BOLD,17));
			x.setHorizontalAlignment(JLabel.CENTER);
			}
		
		modTextoBotton();
		
		this.setVisible(true);
		
	}
	public int getIdentificador () {
		return identificador;
	}
	
	/*Este metodo modifica la visibilidad de los botones segun sea el caso. Tambien cambia el texto del boton de accion 
	 * ya que puede ser "INSERTAR EMPLEADO", "BORRAR EMPLEADO", "ACTUALIZAR EMPLEADO"
	 * */
	private void modTextoBotton() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		switch (identificador) {
		case 1:accion.setText("Insertar Empleado");
			this.setTitle("Panel - Agregar Nuevo Empleado");
				campos[0].setEnabled(false);
			   buscar.setVisible(false);
			   buscar.setEnabled(false);
			   confirmacion.setEnabled(false);
			   confirmacion.setVisible(false);
			   activarcampos();
			   ultimoCodigo();
			   
		break;
		case 2:accion.setText("Borrar Empleado");
			   accion.setEnabled(false);
	this.setTitle("Panel - Borrar Empleado");
	campos[0].setEnabled(true);
		 buscar.setVisible(true);
		   buscar.setEnabled(true);
		   confirmacion.setEnabled(false);
		   confirmacion.setVisible(true);
		  
		break;
		case 3:accion.setText("Actualizar Datos");
		this.setTitle("Panel - Modificar datos de Empleado");
		campos[0].setEnabled(true);
		buscar.setVisible(true);
		   buscar.setEnabled(true);
		   confirmacion.setEnabled(false);
		   confirmacion.setVisible(false);
		   desactivarcampos();
		break;
		}
	}
	public JButton activarbtnborrar () {
		return accion;
	}
	public JTextField CampoCodigo () {
		return campos[0];
	}
	public JTextField CampoNombre () {
		return campos[1];
	}
	public JTextField CampoDni () {
		return campos[2];
	}
	public JTextField CampoSalario () {
		return campos[3];
	}
	public JCheckBox CasillaVerificar () {
		return confirmacion;
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
	//Los campos solo se verifican cuando la accion sea insertar o modificar un Empleado
	public void verificarcampos () throws ClassNotFoundException, SQLException {
		
		if (campos[0].getText().isEmpty()||
				campos[1].getText().isEmpty()||
				campos[2].getText().isEmpty()||
				campos[3].getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios");
		}
		else {
				if (dnivalido()) {
					if (!esDecimal(campos[3].getText()))
						JOptionPane.showMessageDialog(this, "El campo SALARIO debe ser un numero");
					else {
						if (identificador==1)
						insertarEmpleado();
						else if (identificador==3)
							actualizarEmpleado();
							
						  vaciarcampos();
						  modTextoBotton();
						  
					}
				
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
		bd.conexionBD();
		ResultSet rs=bd.ejecutarSQL1("SELECT * FROM empleado ORDER BY id DESC LIMIT 1");
		if (rs.next())
			lastid=rs.getInt(1);
		
		lastid++;
		campos[0].setText(String.valueOf(lastid));
		bd.cerrarConex();
		
	}
	public void insertarEmpleado () throws ClassNotFoundException, SQLException {
		BaseDatos bd=new BaseDatos();
		bd.conexionBD();
		bd.ejecutarSQL2("INSERT INTO empleado VALUES ("+Integer.parseInt(campos[0].getText().toString())+",'"+campos[1].getText().toString()+"','"+campos[2].getText().toString().toUpperCase()+"',"+Double.parseDouble(campos[3].getText().toString())+")");
		bd.cerrarConex();
		JOptionPane.showMessageDialog(this, "Empleado Insertado Correctamente");
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
			bd.conexionBD();
			ResultSet rs=bd.ejecutarSQL1("SELECT * FROM empleado WHERE id = "+Integer.parseInt(campos[0].getText().toString())+"");
			if (rs.next())
				{
				rellenarcampos(rs);
				bd.cerrarConex();
				confirmacion.setEnabled(true);
				
				return true;
				}
			else {JOptionPane.showMessageDialog(this,"No existe un Empleado con ese codigo");
				return false;}
			
			
		}
				
		
	}
	public void deshabilitar () {
		for (JTextField i:campos)
			i.setEditable(false);
	}
	public void habilitar () {
		for (JTextField i:campos)
			i.setEditable(true);
		
	}
	public void rellenarcampos(ResultSet rs) throws SQLException {
		
		campos[1].setText(rs.getString(2));
		campos[2].setText(rs.getString(3));
		campos[3].setText(String.valueOf(rs.getDouble(4)) );		
	}
	public void borrarEmpleado () throws ClassNotFoundException, SQLException {
		BaseDatos bd=new BaseDatos();
		bd.conexionBD();
		bd.ejecutarSQL2("DELETE FROM empleado WHERE id = "+Integer.parseInt(campos[0].getText().toString())+"");
		
		
		bd.cerrarConex();
	}
	public void actualizarEmpleado () throws SQLException, ClassNotFoundException {
		BaseDatos bd=new BaseDatos();
		bd.conexionBD();
		bd.ejecutarSQL2("UPDATE empleado SET nombre = '"+campos[1].getText().toString()+"', "
				+ "dni = '"+campos[2].getText().toString().toUpperCase()+"', salario = "+Double.parseDouble(campos[3].getText().toString())+" WHERE id = "+Integer.parseInt(campos[0].getText().toString())+"");
		
		bd.cerrarConex();
		JOptionPane.showMessageDialog(this, "Datos Actualizados Correctamente");
	}
	public boolean dnivalido () { //metodo para controlar el ingreso de un DNI correcto, 8 caracteres, el primero debe ser una letra
		boolean verif=true;
		
		if (campos[2].getText().toString().length()!=8)
			{
				verif=false;
				JOptionPane.showMessageDialog(this, "El campo dni debe contener 8 caracteres");
			}
		else if (esEntero(""+campos[2].getText().toString().charAt(0)))
				{
					verif=false;
					JOptionPane.showMessageDialog(this, "El primer caracter del dni debe ser una letra");
				}
		else {
			String dninumbers="";
			for (int i=1;i<campos[2].getText().toString().length();i++)
				dninumbers=dninumbers+campos[2].getText().toString().charAt(i);
			
			if (!esEntero(dninumbers))
				{
				verif=false;
				JOptionPane.showMessageDialog(this, "El resto de caracteres deben ser numericos");
				}
		}
		
		
		
		return verif;
	}
	
}
