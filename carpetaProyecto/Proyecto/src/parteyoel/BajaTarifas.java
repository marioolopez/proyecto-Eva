package parteyoel;


import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class BajaTarifas extends JInternalFrame{
	private Buscar bb;
	private PanelParaContenidoAltas pbaja;
	private PanelParaBotonestar pb;
public BajaTarifas() {
	this.setLayout(new BorderLayout());
	bb=new Buscar(this);
	pbaja=new PanelParaContenidoAltas();
	pb=new PanelParaBotonestar(this);
	this.getContentPane().add(bb,BorderLayout.NORTH);
	this.getContentPane().add(pbaja,BorderLayout.CENTER);
	this.getContentPane().add(pb,BorderLayout.SOUTH);
	this.setVisible(true);
}
	public Buscar getBb() {
		return bb;
	}
	public void setBb(Buscar bb) {
		this.bb = bb;
	}
	
	public PanelParaContenidoAltas getPbaja() {
		return pbaja;
	}
	public void setPbaja(PanelParaContenidoAltas pbaja) {
		this.pbaja = pbaja;
	}
	public PanelParaBotonestar getPb() {
		return pb;
	}
	public void setPb(PanelParaBotonestar pb) {
		this.pb = pb;
	}
	
}