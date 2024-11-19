package PaquetePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Yoel.AltaActividades;
import Yoel.Altatarifas;

public class escuchador implements ActionListener {
private static ventanaPrincipal vp;
private Altatarifas at;
private AltaActividades altaA;
 
public escuchador(ventanaPrincipal v) 
{
vp=v;
}
public escuchador(Altatarifas tarifas)
{
	at=tarifas;
}
public escuchador(AltaActividades aa)
{
	altaA=aa;
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
public static void insertartarifa(Altatarifas at) throws ClassNotFoundException, SQLException
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
public static void insertaractividad(AltaActividades aa)
{
	
}
public static void ids(AltaActividades aa,int pos) throws ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.abrircon();
	if(pos==1)
	{
		ResultSet res;
		res=b.ejecutarSQL1("Select idsala from actividad ");
		while(res.next())
		{
		
			aa.getPpa().getCbidsala().addItem(res.getInt("idsala"));
		}
	}else if(pos==2)
	{
		ResultSet res;
		res=b.ejecutarSQL1("Select idempleado from actividad ");
		while(res.next())
		{
			aa.getPpa().getCbidemple().addItem(res.getInt("idempleado"));
		}
	}
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
		case "Actividades":
			AltaActividades aac=new AltaActividades();
			vp.getContentPane().removeAll();
			vp.getContentPane().add(aac);
			
			try {
				ids(aac,1);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				ids(aac,2);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Aceptar":
			if(compvacio(at)==false)
			{
				JOptionPane.showMessageDialog(null, "Los campos no han sido rellenados","error",JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					insertartarifa(at);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		
		}
		
	}

}
