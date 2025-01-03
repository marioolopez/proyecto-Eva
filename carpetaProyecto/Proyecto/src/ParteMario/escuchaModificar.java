package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class escuchaModificar implements ActionListener {
	private modificarInformacion modInfo;
	public escuchaModificar(modificarInformacion m) {
		modInfo = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "pulsarCombo":	
			String recojoCombo = (String) modInfo.getC().getSelectedItem();
			String[] pos = recojoCombo.split("-");
			String pos1 = pos[0]; //id
			String pos2 = pos[1]; //nombre
			
			try {
				ResultSet rs = modInfo.getLizq().getBd().ejecutarSQL1("SELECT * FROM equipamiento WHERE id like "+pos1);
				if(rs.next()) {
					modInfo.getId().setText(rs.getString("id"));			
					modInfo.getNombre().setText(rs.getString("nombre"));
					modInfo.getDescrip().setText(rs.getString("descripcion"));
					modInfo.getIdact().setText(rs.getString("idactividad"));
					modInfo.getIdmante().setText(rs.getString("idmantenimiento"));
				}
				rs.close();
				
				modInfo.getId().setEditable(false);
				modInfo.getIdact().setEditable(false);
				modInfo.getIdmante().setEditable(false);
				
				modInfo.getNombre().setEnabled(true);
				modInfo.getDescrip().setEnabled(true);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			break;
		case "borrar":
			modInfo.getNombre().setText("");
			modInfo.getDescrip().setText("");
			break;
		case "mod":
			boolean enc = false;
			if(modInfo.getNombre().getText().isEmpty() || modInfo.getDescrip().getText().isEmpty()) {
				enc = true;
			}
			if(enc) {
				JOptionPane.showMessageDialog(modInfo, "¡Pulsar tu selección en el comboBox!");
				enc = false;
			}
			else {
				try {
					modInfo.getLizq().getBd().ejecutarSQL2("UPDATE equipamiento SET id = '"+modInfo.getId().getText()+"', nombre = '"+modInfo.getNombre().getText()+"', descripcion = '"+modInfo.getDescrip().getText()+"', idactividad = '"+modInfo.getIdact().getText()+"', idmantenimiento = '"+modInfo.getIdmante().getText()+"' WHERE id = '"+modInfo.getId().getText()+"'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(modInfo, "Datos modificados!");
			}
			break;
		case "salir":
			modInfo.dispose();
			break;
		}
	}
}