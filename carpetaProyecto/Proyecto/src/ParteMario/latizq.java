package ParteMario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
public class latizq extends JPanel{
	private JPanel p1, p2;
	private JLabel NomId, NomNom, NomDescr, NomIdAct, NomIdMant;
	private JTextField TxId, TxNom, TxDescr, TxIdAct, TxIdMant;
	latizq(){
		this.setLayout(new GridLayout(2, 1));
        p1 = new JPanel(new GridLayout(3,2));
	    p1.setBackground(new Color(240, 240, 230)); 

        NomId = new JLabel("Ultimo Id Equipamiento:");
        NomId.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxId = new JTextField();
        
        NomNom = new JLabel("Nombre Equipamiento:");
        NomNom.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxNom = new JTextField();

        NomDescr = new JLabel("Descripción Equipamiento:");
        NomDescr.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxDescr = new JTextField();

        p1.add(NomId);
        p1.add(TxId);
        p1.add(NomNom);
        p1.add(TxNom);
        p1.add(NomDescr);
        p1.add(TxDescr);
        
        //----------------------------------------------------------
        
        p2 = new JPanel(new GridLayout(3,2));
	    p2.setBackground(new Color(240, 240, 230)); 
        
        ImageIcon imgC = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
        JLabel l = new JLabel(imgC);
        
        ImageIcon imgA = new ImageIcon(new ImageIcon("img/cuerda2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
        JLabel l1 = new JLabel(imgA);
        
        NomIdAct = new JLabel("Id Actividad:");
        NomIdAct.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxIdAct = new JTextField();
        
        NomIdMant = new JLabel("Id Equipamiento:");
        NomIdMant.setFont(new Font("Arial Black", Font.BOLD, 12));
        TxIdMant = new JTextField();
        
        p2.add(l);
        p2.add(l1);
        p2.add(NomIdAct);
        p2.add(TxIdAct);
        p2.add(NomIdMant);
        p2.add(TxIdMant);
        
        this.add(p1);
        this.add(p2);
	}
}