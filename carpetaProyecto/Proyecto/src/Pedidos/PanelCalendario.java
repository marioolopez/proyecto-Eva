package Pedidos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

public class PanelCalendario extends JFrame{
	private JCalendar calendario;
	private PedidosModificar pedidosModificar;
	private JPanel botones;
	private JButton cancelar, guardar;
	
	public PanelCalendario(PedidosModificar pedidosModificar) {
		this.pedidosModificar=pedidosModificar;
		this.setLayout(new BorderLayout());
		this.setTitle("FECHA DE ENTREGA");
		this.setSize(250,300);
		this.setLocationRelativeTo(pedidosModificar);
		
		JLabel txt1=new JLabel("Pon una fecha",JLabel.CENTER);
		txt1.setFont(new Font("Arial", Font.BOLD, 17));
		this.add(txt1, BorderLayout.NORTH);
		
		calendario = new JCalendar();
		Date fechaEntrega=pedidosModificar.getPedidoSeleccionado().getFechaEntrega();
		calendario.setDate(fechaEntrega);
		this.add(calendario,BorderLayout.CENTER);
		
		botones=new JPanel();
		botones.setLayout(new FlowLayout());
		cancelar=new JButton("Cancelar");
		cancelar.addActionListener(new AccionCalendario(this,pedidosModificar));
		botones.add(cancelar);
		guardar=new JButton("Guardar");
		guardar.addActionListener(new AccionCalendario(this,pedidosModificar));
		botones.add(guardar);
		this.add(botones, BorderLayout.SOUTH);
		
		
	}

	public JCalendar getCalendario() {
		return calendario;
	}

	public void setCalendario(JCalendar calendario) {
		this.calendario = calendario;
	}
	
	

}
