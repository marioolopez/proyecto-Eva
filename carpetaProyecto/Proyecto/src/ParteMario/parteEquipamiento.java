package ParteMario;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
public class parteEquipamiento extends JInternalFrame {
	private JPanel p, p1, p2;
	public parteEquipamiento() throws ClassNotFoundException, SQLException {
		 p = new JPanel(new GridLayout(1,2));
		 p.setBorder(BorderFactory.createTitledBorder("ALTA DE EQUIPAMIENTO"));
	     latizq izquierda = new latizq(); 
	     latderch derecha = new latderch(izquierda);
	     p.add(izquierda);
	     p.add(derecha);
	     this.add(p);
	     this.setVisible(true);
	}
	public JPanel getP() {
		return p;
	}
	public void setP(JPanel p) {
		this.p = p;
	}
	public JPanel getP1() {
		return p1;
	}
	public void setP1(JPanel p1) {
		this.p1 = p1;
	}
	public JPanel getP2() {
		return p2;
	}
	public void setP2(JPanel p2) {
		this.p2 = p2;
	}
}