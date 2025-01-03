package ParteMario;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
public class modificarInformacion extends JDialog{
	private JPanel p;
	private JButton modificar, salir, borrar;
	private JTextField id, nombre, descrip, idact, idmante;
	private JComboBox<String>c;
	private latizq lizq;
	modificarInformacion() throws ClassNotFoundException, SQLException{
		lizq = new latizq();
		
		JPanel parriba = new JPanel();
		ResultSet rs = lizq.getBd().ejecutarSQL1("SELECT * FROM equipamiento");
		c = new JComboBox<String>();
		c.setPreferredSize(new Dimension(130, 20));
		while(rs.next()) {
			String idnombre = rs.getInt("id") + "-" + rs.getString("nombre");
			c.addItem(idnombre);
		}
		parriba.add(c);
		c.setActionCommand("pulsarCombo");
		c.addActionListener(new escuchaModificar(this));
		this.add(parriba, BorderLayout.NORTH);
		
		
		
		
		
		p = new JPanel(new GridLayout(5,2));
		p.setBorder(BorderFactory.createTitledBorder("MODIFICACIÓN DEL EQUIPAMIENTO"));
		
		JLabel idEq = new JLabel("Id Equipamiento:");
		id = new JTextField();
		id.setPreferredSize(new Dimension(120, 20));
		id.setEnabled(false);
		p.add(idEq);
		p.add(id);
		
		JLabel nombreEq = new JLabel("Nombre Equipamiento:");
		nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(120, 20));
		nombre.setEnabled(false);
		p.add(nombreEq);
		p.add(nombre);
		
		JLabel descripcion = new JLabel("Descripcion Equipamiento:");
		descrip = new JTextField();
		descrip.setPreferredSize(new Dimension(120, 20));
		descrip.setEnabled(false);
		p.add(descripcion); 
		p.add(descrip);
		
		JLabel idAct = new JLabel("Id Actividad:");
		idact = new JTextField();
		idact.setPreferredSize(new Dimension(120, 20));
		idact.setEnabled(false);
		p.add(idAct);
		p.add(idact);
		
		
		JLabel idMant = new JLabel("Id Mantenimiento:");
		idmante = new JTextField();
		idmante.setPreferredSize(new Dimension(120, 20));
		idmante.setEnabled(false);
		p.add(idMant);
		p.add(idmante);
		this.add(p, BorderLayout.CENTER);	
		
		
		
		
		
		JPanel pabajo = new JPanel();
		modificar = new JButton("Modificar");
		modificar.setActionCommand("mod");
		modificar.addActionListener(new escuchaModificar(this));
		
		borrar = new JButton("Borrar");
		borrar.setActionCommand("borrar");
		borrar.addActionListener(new escuchaModificar(this));
		pabajo.add(borrar);
		
		salir = new JButton("Salir");
		salir.setActionCommand("salir");
		salir.addActionListener(new escuchaModificar(this));
		
		pabajo.add(modificar);
		pabajo.add(salir);
		this.add(pabajo, BorderLayout.SOUTH);
	}
	public JPanel getP() {
		return p;
	}
	public void setP(JPanel p) {
		this.p = p;
	}
	public JButton getModificar() {
		return modificar;
	}
	public void setModificar(JButton modificar) {
		this.modificar = modificar;
	}
	public JButton getSalir() {
		return salir;
	}
	public void setSalir(JButton salir) {
		this.salir = salir;
	}
	public JTextField getId() {
		return id;
	}
	public void setId(JTextField id) {
		this.id = id;
	}
	public JTextField getNombre() {
		return nombre;
	}
	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}
	public JTextField getDescrip() {
		return descrip;
	}
	public void setDescrip(JTextField descrip) {
		this.descrip = descrip;
	}
	public JTextField getIdact() {
		return idact;
	}
	public void setIdact(JTextField idact) {
		this.idact = idact;
	}
	public JTextField getIdmante() {
		return idmante;
	}
	public void setIdmante(JTextField idmante) {
		this.idmante = idmante;
	}
	public JComboBox<String> getC() {
		return c;
	}
	public void setC(JComboBox<String> c) {
		this.c = c;
	}
	public latizq getLizq() {
		return lizq;
	}
	public void setLizq(latizq lizq) {
		this.lizq = lizq;
	}
}