package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class exportarPDFEquip implements ActionListener {
	private ListadoEquipMant listEq;
	public exportarPDFEquip(ListadoEquipMant list) {
	listEq = list;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "exportar":	
			
			//hacer aqui todo el prog
			
			break;
		}
	}

}
