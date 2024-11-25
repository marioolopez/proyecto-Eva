package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class escuchaLista implements ActionListener {
	private latizq lizq;
	escuchaLista(latizq lz){
		lizq = lz;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "lista":
			try {
				ListadoEquipMant em = new ListadoEquipMant();
				em.setBounds(200, 200, 600, 200);
				em.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			break;
		}	
	}
}