package partejuanandres;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Confirmado implements ItemListener {

	//Gestor del evento ItemListener para controlar la activacion de un boton al hacer click en un checkbox de confirmacion
	
	private PanelEmp pe;
	Confirmado (PanelEmp emp)
	{
		pe=emp;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange()==ItemEvent.SELECTED)
			pe.activarbtnborrar().setEnabled(true);
		else if (e.getStateChange()==ItemEvent.DESELECTED)
			pe.activarbtnborrar().setEnabled(false);

	}

}
