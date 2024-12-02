package parteyoel;


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import paqueteprincipal.escuchador;

public class PanelParaContenidoActividades extends JPanel{
private JLabel []lbs;
private JTextField[]tx;
private JComboBox<Integer> cbidsala,cbidemple;
 PanelParaContenidoActividades() {
	 this.setLayout(new FlowLayout());
	 this.setPreferredSize(new Dimension(50,20));
	 lbs=new JLabel[4];
	 tx=new JTextField[2];
	 String []tit= {"Nombre","Descripcion","idsala","idempleado"};
for (int i = 0; i < lbs.length; i++) {
	lbs[i]=new JLabel(tit[i]);
}	
for (int i = 0; i < tx.length; i++) {
	tx[i]=new JTextField(20);
}
cbidsala=new JComboBox<Integer>();
cbidemple=new JComboBox<Integer>();
this.add(lbs[0]);
this.add(tx[0]);
this.add(lbs[1]);
this.add(tx[1]);
this.add(lbs[2]);
this.add(cbidsala);
cbidsala.setActionCommand("idsala");
//cbidsala.addActionListener(new escuchador(a));
this.add(lbs[3]);
this.add(cbidemple);
cbidemple.setActionCommand("idemple");
//cbidemple.addActionListener(new escuchador(a));
}
 public String gettext(int pos)
 {
	 return tx[pos].getText();
 }
 public void settext(int pos,String cad)
 {
	 tx[pos].setText(cad);
 }
public JLabel[] getLbs() {
	return lbs;
}
public void setLbs(JLabel[] lbs) {
	this.lbs = lbs;
}
public JTextField[] getTx() {
	return tx;
}
public void setTx(JTextField[] tx) {
	this.tx = tx;
}
public JComboBox<Integer> getCbidsala() {
	return cbidsala;
}
public void setCbidsala(JComboBox<Integer> cbidsala) {
	this.cbidsala = cbidsala;
}
public JComboBox<Integer> getCbidemple() {
	return cbidemple;
}
public void setCbidemple(JComboBox<Integer> cbidemple) {
	this.cbidemple = cbidemple;
}
 
}
