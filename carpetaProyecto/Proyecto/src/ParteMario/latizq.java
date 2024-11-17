package ParteMario;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
public class latizq extends JPanel{
	private JPanel p1, p2;
	private JLabel NomId, NomNom, NomDescr, NomIdAct, NomIdMant;
	private JTextField TxId, TxNom, TxDescr, TxIdAct, TxIdMant;
	latizq(){
		this.setLayout(new GridLayout(2, 1));
        p1 = new JPanel(new GridLayout(3,2));
	    p1.setBackground(new Color(240, 240, 230)); 

        NomId = new JLabel("Ultimo Id Equipamiento:");
        NomId.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxId = new JTextField();
        
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
        
        NomIdMant = new JLabel("Id Equipamiento:");
        NomIdMant.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxIdMant = new JTextField();
        
        p2.add(l);
        p2.add(l1);
        p2.add(NomIdAct);
        p2.add(TxIdAct);
        p2.add(NomIdMant);
        p2.add(TxIdMant);
        
        this.add(p1);
        this.add(p2);
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
}