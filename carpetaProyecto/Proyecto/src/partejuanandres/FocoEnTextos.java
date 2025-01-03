package partejuanandres;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class FocoEnTextos implements FocusListener {

	//Gestor del evento OnFocus para cambiar estilos de cada campo
	private PanelEmp  pe;
	
	FocoEnTextos (PanelEmp emp)
	{
		pe=emp;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

		JTextField campo=(JTextField)e.getSource();
		if (campo==pe.CampoCodigo())
			cambiarEstilo(pe.CampoCodigo());
		else if (campo==pe.CampoDni())
			cambiarEstilo(pe.CampoDni());
		else if (campo==pe.CampoNombre())
			cambiarEstilo(pe.CampoNombre());
			else if (campo==pe.CampoSalario())
				cambiarEstilo(pe.CampoSalario());
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

		JTextField campo=(JTextField)e.getSource();
		if (campo==pe.CampoCodigo())
			estiloDefecto(pe.CampoCodigo());
		else if (campo==pe.CampoDni())
			estiloDefecto(pe.CampoDni());
		else if (campo==pe.CampoNombre())
			estiloDefecto(pe.CampoNombre());
			else if (campo==pe.CampoSalario())
				estiloDefecto(pe.CampoSalario());
		
	}

	public void cambiarEstilo (Component comp)
	{
		comp.setBackground(new Color(150, 150, 78));
		comp.setForeground(Color.white);
	}
	public void estiloDefecto (Component comp)
	{
		comp.setBackground(Color.white);
		comp.setForeground(Color.black);
		
	}
}
