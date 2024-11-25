package Yoel;

import javax.swing.*;

import PaquetePrincipal.escuchador;
import PaquetePrincipal.ventanaPrincipal;

public class PanelParaBotonestar extends JPanel{
private JButton btaceptar,btcancelar,btpdf,btborrar;
public PanelParaBotonestar(Altatarifas at)
{
	btaceptar=new JButton("Aceptar");
	btcancelar=new JButton("Cancelar");
	btpdf=new JButton("Generar PDF");
	btcancelar.setActionCommand("Cancelartar");
	btcancelar.addActionListener(new escuchador(at));
	btaceptar.setActionCommand("Aceptartar");
	btaceptar.addActionListener(new escuchador(at));
	btpdf.setActionCommand("PDFtar");
	btpdf.addActionListener(new escuchador(at));
	this.add(btaceptar);
	this.add(btcancelar);
}
public PanelParaBotonestar(AltaActividades aa)
{
	btaceptar=new JButton("Aceptar");
	btcancelar=new JButton("Cancelar");
	btpdf=new JButton("Generar PDF");
	btcancelar.setActionCommand("Cancelaract");
	btcancelar.addActionListener(new escuchador(aa));
	btaceptar.setActionCommand("Aceptaract");
	btaceptar.addActionListener(new escuchador(aa));
	btpdf.setActionCommand("PDFalt");
	btpdf.addActionListener(new escuchador(aa));
	this.add(btaceptar);
	this.add(btcancelar);
}
public PanelParaBotonestar(BajaTarifas bt)
{
	
	btborrar=new JButton("Borrar");
	btborrar.setActionCommand("borrartar");
	btborrar.addActionListener(new escuchador(bt));
	
	this.add(btborrar);
}
public PanelParaBotonestar(BajaActividad ba)
{
	
	btborrar=new JButton("Borrar");
	btborrar.setActionCommand("borraract");
	btborrar.addActionListener(new escuchador(ba));
	
	this.add(btborrar);
}
}
