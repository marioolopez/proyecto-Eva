package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class accionEquipamiento implements ActionListener {
	private latderch d;
	accionEquipamiento(latderch de) throws ClassNotFoundException, SQLException {
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
                try {        	
                	int idE = d.getLatizq().incrementarEquipamiento();
    				int idA = d.getLatizq().incrementarActividad();
    				int idM = d.getLatizq().incrementarMantenimiento();
    				d.getLatizq().getBd().ejecutarSQL2("INSERT INTO equipamiento(id, nombre, descripcion, idactividad, idmantenimiento) VALUES('"+idE+"', '"+d.getLatizq().getTxNom().getText()+"', '"+d.getLatizq().getTxDescr().getText()+"', '"+idA+"', '"+idM+"')");
					//d.getLatizq().getBd().ejecutarSQL2("INSERT INTO mantenimiento(id, nombre) VALUES('"+idM+"', '"++"')");
                } catch (SQLException e1) {
					e1.printStackTrace();
				}
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