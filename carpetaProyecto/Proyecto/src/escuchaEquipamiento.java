import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import ParteMario.parteEquipamiento;
public class escuchaEquipamiento implements ActionListener {
	private ventanaPrincipal vp;
	private parteEquipamiento pe;
	escuchaEquipamiento(ventanaPrincipal vpp) throws ClassNotFoundException, SQLException{
		vp = vpp;
		pe = new parteEquipamiento();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "escuchaEquipamiento":
			vp.getContentPane().removeAll();
			vp.getContentPane().add(pe);
			pe.setVisible(true);
			break;
		}
	}
}