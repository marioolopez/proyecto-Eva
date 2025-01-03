package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class accionEquipamiento implements ActionListener {
	private latizq izq;
	public accionEquipamiento(latizq i) {
	this.izq = i;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "botonEquip":
			boolean enc = false;
			
            if (izq.getTxNom().getText().isEmpty() || izq.getArea().getText().isEmpty()) {
            	enc = true;
            }  
            if(enc){
            	JOptionPane.showMessageDialog(izq, "Rellene los campos de Equipamiento");
            	enc = false;	
            }
            else {
                try {
                    int idE = izq.incrementarEquipamiento();
                    int idA = izq.incrementarActividad();
                    int idMI = izq.maxMantenimiento();
                    
                    if(izq.getTxNom().getText().matches(".*\\d.*") || izq.getArea().getText().matches(".*\\d.*")) {
                        JOptionPane.showMessageDialog(izq, "No puedes introducir datos numéricos, solo texto.");
                        return;
        			}
                    
                    izq.getBd().ejecutarSQL2("INSERT INTO equipamiento(id, nombre, descripcion, idactividad, idmantenimiento) VALUES('"+idE+"', '"+izq.getTxNom().getText()+"', '"+izq.getArea().getText()+"', '"+idA+"', '"+idMI+"')");
                    JOptionPane.showMessageDialog(izq, "Datos insertados correctamente");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            break;
            
            
		case "mod":
		
			try {
				modificarInformacion modInfo = new modificarInformacion();
				modInfo.setBounds(200, 200, 600, 400);
				modInfo.setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			break;
		}
	}
}