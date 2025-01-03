package ParteMario;
import Conexiones.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
public class latizq extends JPanel {
	private JPanel p1, p2;
	private JLabel NomId, NomNom, NomDescr, NomIdAct, NomIdMant;
	private JTextField TxId, TxNom, TxIdAct, TxIdMant;
	private JTextArea area;
	private BaseDatos bd;
	private JScrollPane barraDespliz;
	private JButton seguimiento, botonEq, modificar;
	public latizq() throws ClassNotFoundException, SQLException {	
		bd = new BaseDatos();
		bd.conexionBD();
		
		this.setLayout(new GridLayout(2, 1));
        p1 = new JPanel(new GridLayout(4,2)); 
	    p1.setBackground(new Color(240, 240, 230));

        NomId = new JLabel("Ultimo Id Equipamiento:");
        NomId.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxId = new JTextField();
        int idEq = incrementarEquipamiento();
        TxId.setText(String.valueOf(idEq));
        TxId.setForeground(Color.black);
        TxId.setEnabled(false);
        
        NomNom = new JLabel("Nombre Equipamiento:");
        NomNom.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxNom = new JTextField();

        
        NomIdAct = new JLabel("Id Actividad:");
        NomIdAct.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxIdAct = new JTextField();
        int idA = incrementarActividad();
        TxIdAct.setText(String.valueOf(idA));
        TxIdAct.setForeground(Color.black);
        TxIdAct.setEnabled(false);
        
        
        NomIdMant = new JLabel("Id Mantenimiento:");
        NomIdMant.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxIdMant = new JTextField();
        int idM = maxMantenimiento();
        TxIdMant.setText(String.valueOf(idM));
        TxIdMant.setForeground(Color.black);
        TxIdMant.setEnabled(false);
   

        p1.add(NomId);
        p1.add(TxId);
        p1.add(NomNom);
        p1.add(TxNom);
        p1.add(NomIdAct);
        p1.add(TxIdAct);
        p1.add(NomIdMant);
        p1.add(TxIdMant);
        
        //------------------------------------------------------------------
        
        p2 = new JPanel();
	    p2.setBackground(new Color(240, 240, 230)); 
	    NomDescr = new JLabel("Descripción del Equipamiento:");
	    NomDescr.setFont(new Font("Arial Black", Font.BOLD, 12));
	    area = new JTextArea();
        area.setPreferredSize(new Dimension(280, 150));
        barraDespliz = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        p2.add(NomDescr); 
        p2.add(barraDespliz);
       
        
        seguimiento = new JButton("Listado");
        seguimiento.setActionCommand("lista");
        seguimiento.setPreferredSize(new Dimension(100, 20));
        seguimiento.addActionListener(new escuchaLista(this));
        p2.add(seguimiento);
        
        
        botonEq = new JButton("Insertar Equipamiento");
        botonEq.setActionCommand("botonEquip");
        botonEq.setPreferredSize(new Dimension(160, 20));
        botonEq.addActionListener(new accionEquipamiento(this));
        p2.add(botonEq);
        
        
        modificar = new JButton("Modificar");
        modificar.setActionCommand("mod");
        modificar.setPreferredSize(new Dimension(90, 20));
        modificar.addActionListener(new accionEquipamiento(this));
        p2.add(modificar);
        
        this.add(p1);
        this.add(p2);
	}
	
	//metodos incrementar en bbdd
	public int incrementarEquipamiento() throws SQLException {
			ResultSet rs = bd.ejecutarSQL1("SELECT MAX(id) AS id_Eq FROM equipamiento");
			if(rs.next()) {
				return rs.getInt("id_Eq") + 1;
			}
			else {
				return 1;
			}
	}
		
	public int maxMantenimiento() throws SQLException {
			ResultSet rs = bd.ejecutarSQL1("SELECT MAX(id) AS id_Ma FROM mantenimiento");
			if(rs.next()) {
				return rs.getInt("id_Ma");
			}
			else {
				return 1;
			}
	}
		
	public int incrementarActividad() throws SQLException {
			ResultSet rs = bd.ejecutarSQL1("SELECT MAX(id) AS id_Act FROM actividad");
			if(rs.next()) {
				return rs.getInt("id_Act");
			}
			else {
				return 1;
			}
	}
	
	public int incrementarMantenimientoTablaMantenimiento() throws SQLException {
		ResultSet rs = bd.ejecutarSQL1("SELECT MAX(id) AS id_Ma FROM mantenimiento");
		if(rs.next()) {
			return rs.getInt("id_Ma") + 1;
		}
		else {
			return 1;
		}
	}

	public JButton getModificar() {
		return modificar;
	}
	public void setModificar(JButton modificar) {
		this.modificar = modificar;
	}
	public JScrollPane getBarraDespliz() {
		return barraDespliz;
	}
	public void setBarraDespliz(JScrollPane barraDespliz) {
		this.barraDespliz = barraDespliz;
	}
	public JPanel getP1() {
		return p1;
	}
	public void setP1(JPanel p1) {
		this.p1 = p1;
	}
	public JPanel getP2() {
		return p2;
	}
	public void setP2(JPanel p2) {
		this.p2 = p2;
	}
	public JLabel getNomId() {
		return NomId;
	}
	public void setNomId(JLabel nomId) {
		NomId = nomId;
	}
	public JLabel getNomNom() {
		return NomNom;
	}
	public JButton getBotonEq() {
		return botonEq;
	}
	public void setBotonEq(JButton botonEq) {
		this.botonEq = botonEq;
	}
	public void setNomNom(JLabel nomNom) {
		NomNom = nomNom;
	}
	public JLabel getNomDescr() {
		return NomDescr;
	}
	public void setNomDescr(JLabel nomDescr) {
		NomDescr = nomDescr;
	}
	public JLabel getNomIdAct() {
		return NomIdAct;
	}
	public void setNomIdAct(JLabel nomIdAct) {
		NomIdAct = nomIdAct;
	}
	public JLabel getNomIdMant() {
		return NomIdMant;
	}
	public void setNomIdMant(JLabel nomIdMant) {
		NomIdMant = nomIdMant;
	}
	public JTextField getTxId() {
		return TxId;
	}
	public void setTxId(JTextField txId) {
		TxId = txId;
	}
	public JTextField getTxNom() {
		return TxNom;
	}
	public void setTxNom(JTextField txNom) {
		TxNom = txNom;
	}	
	public JTextField getTxIdAct() {
		return TxIdAct;
	}
	public void setTxIdAct(JTextField txIdAct) {
		TxIdAct = txIdAct;
	}
	public JTextField getTxIdMant() {
		return TxIdMant;
	}
	public void setTxIdMant(JTextField txIdMant) {
		TxIdMant = txIdMant;
	}
	public BaseDatos getBd() {
		return bd;
	}
	public void setBd(BaseDatos bd) {
		this.bd = bd;
	}
	public JTextArea getArea() {
		return area;
	}
	public void setArea(JTextArea area) {
		this.area = area;
	}
	public JButton getSeguimiento() {
		return seguimiento;
	}
	public void setSeguimiento(JButton seguimiento) {
		this.seguimiento = seguimiento;
	}	
}