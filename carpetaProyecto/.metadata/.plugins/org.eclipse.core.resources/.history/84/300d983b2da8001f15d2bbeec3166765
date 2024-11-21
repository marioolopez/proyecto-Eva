package Yoel;

import javax.swing.*;

import PaquetePrincipal.escuchador;
import PaquetePrincipal.ventanaPrincipal;

public class PanelParaBotonestar extends JPanel{
private JButton btaceptar,btcancelar;
public PanelParaBotonestar(Altatarifas at)
{
	btaceptar=new JButton("Aceptar");
	btcancelar=new JButton("Cancelar");
	btcancelar.setActionCommand("Cancelartar");
	btcancelar.addActionListener(new escuchador(at));
	btaceptar.setActionCommand("Aceptartar");
	btaceptar.addActionListener(new escuchador(at));
	this.add(btaceptar);
	this.add(btcancelar);
}
public PanelParaBotonestar(AltaActividades aa)
{
	btaceptar=new JButton("Aceptar");
	btcancelar=new JButton("Cancelar");
	btcancelar.setActionCommand("Cancelaract");
	btcancelar.addActionListener(new escuchador(aa));
	btaceptar.setActionCommand("Aceptaract");
	btaceptar.addActionListener(new escuchador(aa));
	this.add(btaceptar);
	this.add(btcancelar);
}
}
