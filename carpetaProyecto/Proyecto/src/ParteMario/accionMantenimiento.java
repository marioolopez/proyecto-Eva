package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class accionMantenimiento implements ActionListener {
	private latderch d;
	public accionMantenimiento(latderch de) {
	    this.d = de;
	}
	  @Override
	    public void actionPerformed(ActionEvent e) {
	        switch (e.getActionCommand()) {
	            case "botonBorrar":
	                if (d != null && d.getIzq() != null) {
	                    d.getIzq().getTxNom().setText("");
	                    d.getIzq().getArea().setText("");
	                    d.getNomMantTx().setText("");
	                }
	                break;
	                
	            case "botonInsertar":
	                if (d.getNomMantTx().getText().isEmpty()) {
	                    JOptionPane.showMessageDialog(d, "Rellena el campo Mantenimiento");
	                } else {
	                    try {
	                        int idMI = d.getIzq().incrementarMantenimientoTablaMantenimiento();
	                        
	                        if(d.getNomMantTx().getText().matches(".*\\d.*")) {
	                            JOptionPane.showMessageDialog(d, "No puedes introducir datos numéricos, solo texto.");
	                            return;
	            			}
	                        
	                        d.getIzq().getBd().ejecutarSQL2("INSERT INTO mantenimiento(id, nombre) VALUES('"+idMI +"', '"+d.getNomMantTx().getText()+"')");
	                        d.getIzq().getTxNom().setText("");
	                        d.getIzq().getArea().setText("");
	                        d.getNomMantTx().setText("");
	                        JOptionPane.showMessageDialog(d, "Datos insertados correctamente");
	                    } catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
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