package Pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionCalendario implements ActionListener {
	private PanelCalendario pa;
	private PedidosModificar pedidosModificar;
	
	public AccionCalendario(PanelCalendario pa,PedidosModificar pedidosModificar) {
		this.pa=pa;
		this.pedidosModificar=pedidosModificar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		if(comando.equals("Cancelar")) {
			pa.dispose();
		}else if(comando.equals("Guardar")){
			java.util.Date fecha = pa.getCalendario().getDate();
	        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
			pedidosModificar.getPedidoSeleccionado().setFechaEntrega(fechaSql);
			pedidosModificar.getPedidoSeleccionado().actualizaFechaEntrega();
			pedidosModificar.buscarPedidos();
			pedidosModificar.getPedidoSeleccionado().getListaComprasTotal().clear();
			pedidosModificar.getPedidoSeleccionado().getListaPedidos().clear();
			pa.dispose();
		}

	}

}
