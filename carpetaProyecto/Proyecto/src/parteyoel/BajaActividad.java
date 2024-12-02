package parteyoel;

import java.awt.BorderLayout;

import javax.swing.*;

public class BajaActividad extends JInternalFrame{
private Buscar bb;
private PanelParaContenidoActividades pa;
private PanelParaBotonestar bt;
public BajaActividad()
{
	this.setLayout(new BorderLayout());
	bb=new Buscar(this);
	pa=new PanelParaContenidoActividades();
	bt=new PanelParaBotonestar(this);
	this.getContentPane().add(bb,BorderLayout.NORTH);
	this.getContentPane().add(pa,BorderLayout.CENTER);
	this.getContentPane().add(bt,BorderLayout.SOUTH);
	this.setVisible(true);
}
public Buscar getBb() {
	return bb;
}
public void setBb(Buscar bb) {
	this.bb = bb;
}
public PanelParaContenidoActividades getPa() {
	return pa;
}
public void setPa(PanelParaContenidoActividades pa) {
	this.pa = pa;
}
public PanelParaBotonestar getBt() {
	return bt;
}
public void setBt(PanelParaBotonestar bt) {
	this.bt = bt;
}

}
