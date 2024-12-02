package parteyoel;


import javax.swing.*;

import paqueteprincipal.escuchador;

public class Buscar extends JPanel{
private JComboBox<Integer>cb;
private JButton bt;
Buscar(BajaTarifas bajatarifas)
{
	cb=new JComboBox<Integer>();
	bt=new JButton("Buscar");
	bt.setActionCommand("Buscartar");
	bt.addActionListener(new escuchador(bajatarifas));
	this.add(cb);
	this.add(bt);
}
Buscar(BajaActividad bactividad)
{
	cb=new JComboBox<Integer>();
	bt=new JButton("Buscar");
	bt.setActionCommand("Buscaract");
	bt.addActionListener(new escuchador(bactividad));
	this.add(cb);
	this.add(bt);
}
public JComboBox<Integer> getCb() {
	return cb;
}
public void setCb(JComboBox<Integer> cb) {
	this.cb = cb;
}
public JButton getBt() {
	return bt;
}
public void setBt(JButton bt) {
	this.bt = bt;
}

}
