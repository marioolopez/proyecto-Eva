package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class accionEquipamiento implements ActionListener{
	private latderch d;
	accionEquipamiento(latderch de) throws ClassNotFoundException, SQLException{
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
		case "botonInsertar":
			
			boolean enc = false;
			if(d.getLatizq().getTxDescr().getText().isEmpty() || d.getLatizq().getTxNom().getText().isEmpty()) {
				enc = true;
			}
			if(enc) {
				JOptionPane.showMessageDialog(d, "Porfavor, rellene los campos");
			}
			else {
                JOptionPane.showMessageDialog(d, "Datos insertados correctamente");
			}
			
			
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