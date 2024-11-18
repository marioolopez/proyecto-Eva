

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class Pedidos extends JInternalFrame{
	private ventanaPrincipal ven;
	private JLabel txt1,txt2,txt3,txt4;
	private JTextField id;
	private JCalendar calendario;
	private JPanel datos,botones, izq, izqArr, izqAba, centro, der;
	private JList listaCliente, listaCompras;
	private JScrollPane barra1,barra2;
	private JButton[] boton;
	private String[] botonesNom= {"Añadir", "mostrar", "modificar", "eliminar"};
	private BaseDatos baseDatos;

	
	public Pedidos(ventanaPrincipal ven) {
		super();
		this.setLayout(new FlowLayout());
		//
		idMax(); //
		//Metodo que añade los elementos
		datosMe();
		botonesMe();
		
		try {
			baseDatos=new BaseDatos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void datosMe() {
		datos=new JPanel();
		
		datos.setLayout(new GridLayout(1,3));
			izq=new JPanel();
			izq.setLayout(new BorderLayout());
				izqArr=new JPanel();
				izqArr.setLayout(new BorderLayout());
					txt1=new JLabel("IdPedido");
					izqArr.add(txt1, BorderLayout.NORTH);
					id=new JTextField();
					id.setEnabled(false);
				izqArr.add(id, BorderLayout.CENTER);
			izq.add(izqArr, BorderLayout.NORTH);
			
			izqAba=new JPanel();
			izqAba.setLayout(new BorderLayout());
				txt2=new JLabel("Cliente");
				izqAba.add(txt2,BorderLayout.NORTH);
					listaCliente=new JList();
					barra1=new JScrollPane(listaCliente,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				izqAba.add(barra1, BorderLayout.CENTER);
			izq.add(izqAba, BorderLayout.CENTER);
		datos.add(izq);
			
			centro=new JPanel();
			centro.setLayout(new BorderLayout());
				txt3=new JLabel("Fecha");
				centro.add(txt3, BorderLayout.NORTH);
				calendario=new JCalendar();
				centro.add(calendario, BorderLayout.CENTER);
		datos.add(centro);
			
			der=new JPanel();
			der.setLayout(new BorderLayout());
				txt4=new JLabel("Id Compras");
				der.add(txt4, BorderLayout.NORTH);
				listaCompras=new JList();
				barra2=new JScrollPane(listaCompras,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				der.add(barra2, BorderLayout.CENTER);
		datos.add(der);
		datos.setPreferredSize(new Dimension(750,350));
		this.add(datos);

	}
	
	public void botonesMe() {
		botones=new JPanel();
		botones.setLayout(new GridLayout(1,4));
		boton=new JButton[botonesNom.length];
		for(int i=0; i<botonesNom.length; i++) {
			boton[i]=new JButton(botonesNom[i]);
			boton[i].addActionListener(new PedidosAccion(this));
			botones.add(boton[i]);
		}
		boton[2].setEnabled(false);
		boton[3].setEnabled(false);
		this.add(botones);
	}
	
	public void anadirMe() {
		
	}
	
	public void idMax() {
		String sql="SELECT MAX(idpedido) AS \"idMax\" FROM compra";
		
	}

}
