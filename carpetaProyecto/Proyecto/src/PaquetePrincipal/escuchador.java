package PaquetePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Yoel.Altatarifas;

public class escuchador implements ActionListener {
private static ventanaPrincipal vp;
private Altatarifas at;
 
public escuchador(ventanaPrincipal v) 
{
vp=v;
}
public escuchador(Altatarifas tarifas)
{
	at=tarifas;
}
public static boolean compvacio(Altatarifas at)
{
	boolean f=true;
	if(at.getPa().gettext(0).trim().isEmpty()||at.getPa().gettext(1).trim().isEmpty()||at.getPa().getTa().getText().trim().isEmpty())
{
	f=false;
}
	return f;
}
public static void insertar(Altatarifas at) throws ClassNotFoundException, SQLException
{
	int id;
	BaseDatos b=new BaseDatos();
	b.abrircon();
	ResultSet res;
	res=b.ejecutarSQL1("Select id from tarifa order by id desc limit 1");
	if(res.next())
	{
		id=res.getInt("id")+1;
	}else {
		id=1;
	}
	b.ejecutarSQL2("Insert into tarifa values("+id+",'"+at.getPa().gettext(0)+"','"+at.getPa().getTa().getText()+"',"+Double.parseDouble(at.getPa().gettext(1))+")");
	b.cerrarConex();
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand())
		{
		case "Tarifa": Altatarifas alta=new Altatarifas();
		vp.getContentPane().removeAll();
		vp.getContentPane().add(alta);
		break;
		case "Aceptar":
			if(compvacio(at)==false)
			{
				JOptionPane.showMessageDialog(null, "Los campos no han sido rellenados","error",JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					insertar(at);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}

}