package partejuanandres;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Confirmado implements ItemListener {

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
