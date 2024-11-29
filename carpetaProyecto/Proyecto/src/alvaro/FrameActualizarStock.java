package alvaro;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class FrameActualizarStock extends JFrame{
	private static final long serialVersionUID = 1L;
	private JSpinner spinner;
	
	public FrameActualizarStock() {
		super();
		super.setResizable(false);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(150, 80);
		super.getContentPane().setLayout(new FlowLayout());
		SpinnerModel modeloSpinner = new SpinnerNumberModel(0, 0, 10000, 1);
		spinner=new JSpinner(modeloSpinner);
		spinner.setPreferredSize(new Dimension(100,30));
		JButton botonActualizar=new JButton("Actualizar stock");
		botonActualizar.setPreferredSize(new Dimension(80,30));
		
		
		super.getContentPane().add(spinner);
		super.getContentPane().add(botonActualizar);
		super.setVisible(true);
	}
}

