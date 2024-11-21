package Pedidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComprasAccion implements ActionListener {
	private Compras compras;
	
	public ComprasAccion(Compras compras) {
		this.compras=compras;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		//Compras
		if(comando.equals("Añadir")) {
			compras.compraRealizadaMet();
			compras.reseteoMet();
		}

	}

}
