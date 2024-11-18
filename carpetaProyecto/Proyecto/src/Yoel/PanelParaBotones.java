package Yoel;

import javax.swing.*;

import PaquetePrincipal.escuchador;
import PaquetePrincipal.ventanaPrincipal;

public class PanelParaBotones extends JPanel{
private JButton btaceptar,btcancelar;
public PanelParaBotones(Altatarifas at)
{
	btaceptar=new JButton("Aceptar");
	btcancelar=new JButton("Cancelar");
	btaceptar.setActionCommand("Aceptar");
	btaceptar.addActionListener(new escuchador(at));
	this.add(btaceptar);
	this.add(btcancelar);
}
}
