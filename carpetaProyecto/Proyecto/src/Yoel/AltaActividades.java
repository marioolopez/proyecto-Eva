package Yoel;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

public class AltaActividades extends JInternalFrame{
private PanelParaContenidoActividades ppa;
private PanelParaBotonestar pb;
public AltaActividades()
{
	this.setLayout(new BorderLayout());
	ppa=new PanelParaContenidoActividades(this);
	pb=new PanelParaBotonestar(this);
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
public PanelParaBotonestar getPb() {
	return pb;
}
public void setPb(PanelParaBotonestar pb) {
	this.pb = pb;
}

}
