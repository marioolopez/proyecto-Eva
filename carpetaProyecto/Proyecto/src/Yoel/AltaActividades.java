package Yoel;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

public class AltaActividades extends JInternalFrame{
private PanelParaContenidoActividades ppa;
private PanelParaBotones pb;
public AltaActividades()
{
	this.setLayout(new BorderLayout());
	ppa=new PanelParaContenidoActividades(this);
	pb=new PanelParaBotones(this);
	this.getContentPane().add(ppa,BorderLayout.CENTER);
	this.getContentPane().add(pb,BorderLayout.SOUTH);
	this.setVisible(true);
}
public PanelParaContenidoActividades getPpa() {
	return ppa;
}
public void setPpa(PanelParaContenidoActividades ppa) {
	this.ppa = ppa;
}
public PanelParaBotones getPb() {
	return pb;
}
public void setPb(PanelParaBotones pb) {
	this.pb = pb;
}

}
