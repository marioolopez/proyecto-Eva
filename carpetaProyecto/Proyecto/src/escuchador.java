import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Yoel.Altatarifas;

public class escuchador implements ActionListener {
private ventanaPrincipal vp;
escuchador(ventanaPrincipal v) 
{
vp=v;
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Altatarifas at=new Altatarifas();
		vp.getContentPane().removeAll();
		vp.getContentPane().add(at);
	}

}
