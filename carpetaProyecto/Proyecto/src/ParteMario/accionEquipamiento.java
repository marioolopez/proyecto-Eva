package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class accionEquipamiento implements ActionListener{
	private latderch d;
	accionEquipamiento(latderch de){
		d = de;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "botonBorrar":
			d.getLatizq().getTxDescr().setText("");
			d.getLatizq().getTxNom().setText("");
			break;
		}
	}
	public latderch getD() {
		return d;
	}
	public void setD(latderch d) {
		this.d = d;
	}
}