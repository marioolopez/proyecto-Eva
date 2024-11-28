package Pedidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionCompras implements ActionListener {
	private Compras compras;
	
	public AccionCompras(Compras compras) {
		this.compras=compras;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		if(comando.equals("Añadir")) {
			compras.guardarCompra();
		}
	}

}
