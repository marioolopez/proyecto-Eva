package ParteMario;
import Conexiones.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
public class latizq extends JPanel{
	private JPanel p1, p2;
	private JLabel NomId, NomNom, NomDescr, NomIdAct, NomIdMant;
	private JTextField TxId, TxNom, TxDescr, TxIdAct, TxIdMant;
	private BaseDatos bd;
	public latizq() throws ClassNotFoundException, SQLException {	
		
		bd = new BaseDatos();
		bd.conexionBD();
		
		this.setLayout(new GridLayout(2, 1));
        p1 = new JPanel(new GridLayout(3,2));
	    p1.setBackground(new Color(240, 240, 230)); 

        NomId = new JLabel("Ultimo Id Equipamiento:");
        NomId.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxId = new JTextField();
        int idEq = incrementarEquipamiento();
        TxId.setText(String.valueOf(idEq));
        TxId.setForeground(Color.black);
        TxId.setEnabled(false);;
        
        NomNom = new JLabel("Nombre Equipamiento:");
        NomNom.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxNom = new JTextField();

        NomDescr = new JLabel("Descripción Equipamiento:");
        NomDescr.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxDescr = new JTextField();

        p1.add(NomId);
        p1.add(TxId);
        p1.add(NomNom);
        p1.add(TxNom);
        p1.add(NomDescr);
        p1.add(TxDescr);
        
        //----------------------------------------------------------
        
        p2 = new JPanel(new GridLayout(3,2));
	    p2.setBackground(new Color(240, 240, 230)); 
        
        ImageIcon imgC = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
        JLabel l = new JLabel(imgC);
        
        ImageIcon imgA = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
        JLabel l1 = new JLabel(imgA);
        
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
        int idM = incrementarMantenimiento();
        TxIdMant.setText(String.valueOf(idM));
        TxIdMant.setForeground(Color.black);
        TxIdMant.setEnabled(false);


        p2.add(l);
        p2.add(l1);
        p2.add(NomIdAct);
        p2.add(TxIdAct);
        p2.add(NomIdMant);
        p2.add(TxIdMant);
        
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
		
	public int incrementarMantenimiento() throws SQLException {
			ResultSet rs = bd.ejecutarSQL1("SELECT MAX(id) AS id_Ma FROM mantenimiento");
			if(rs.next()) {
				return rs.getInt("id_Ma") + 1;
			}
			else {
				return 1;
			}
		}
		
	public int incrementarActividad() throws SQLException {
			ResultSet rs = bd.ejecutarSQL1("SELECT MAX(id) AS id_Act FROM actividad");
			if(rs.next()) {
				return rs.getInt("id_Act") + 1;
			}
			else {
				return 1;
			}
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
	public JTextField getTxDescr() {
		return TxDescr;
	}
	public void setTxDescr(JTextField txDescr) {
		TxDescr = txDescr;
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
}