import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidosAccion implements ActionListener {
	private Pedidos pedidos;
	
	public PedidosAccion(Pedidos pedidos) {
		this.pedidos=pedidos;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		if(comando.equals("Añadir")) {
			System.out.println("");
		}

	}

}
