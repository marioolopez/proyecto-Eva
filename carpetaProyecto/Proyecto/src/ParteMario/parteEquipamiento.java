package ParteMario;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.JInternalFrame;
public class parteEquipamiento extends JInternalFrame {
	private JPanel p;
	private JLabel NomId, NomNombre, NomDescrp, NomIdAct, NomIdMant;
	private JTextField Txtid, Txtnombre, Txtdescripcion, TxtidAct, TxtidMant;
	public parteEquipamiento() {
		super("EQUIPAMIENTOS GIMNASIO DAM");
		p = new JPanel(new GridLayout(2,1));
	
		JPanel p1 = new JPanel(new GridLayout(3,2));
		NomId = new JLabel("Id Equipamiento:");
		Txtid = new JTextField();
		NomNombre = new JLabel("Nombre del Equipamiento: ");
	    Txtnombre = new JTextField();
	    NomDescrp = new JLabel("Descripcion del Equipamiento:");
	    Txtdescripcion = new JTextField();
		p1.add(NomId);
		p1.add(Txtid);
		p1.add(NomNombre);
		p1.add(Txtnombre);
		p1.add(NomDescrp);
		p1.add(Txtdescripcion);
		
		JPanel p2 = new JPanel(new GridLayout());
		
		
		p.add(p1);
		p.add(p2);
		this.add(p);
	}
}