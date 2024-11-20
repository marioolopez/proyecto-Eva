package Yoel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.*;

import PaquetePrincipal.ventanaPrincipal;

public class Altatarifas extends JInternalFrame{
private PanelParaContenidoAltas pa;
private PanelParaBotones pb;
public Altatarifas() {
	this.setLayout(new BorderLayout());
pa=new PanelParaContenidoAltas();
pb=new PanelParaBotones(this);
this.getContentPane().add(pa,BorderLayout.CENTER);
this.getContentPane().add(pb,BorderLayout.SOUTH);
this.setVisible(true);
}
public PanelParaContenidoAltas getPa() {
	return pa;
}
public void setPa(PanelParaContenidoAltas pa) {
	this.pa = pa;
}
public PanelParaBotones getPb() {
	return pb;
}
public void setPb(PanelParaBotones pb) {
	this.pb = pb;
}

}
