package PaquetePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

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
public static boolean compvaciotar(Altatarifas at)
{
	boolean f=true;
	if(at.getPa().gettext(0).trim().isEmpty()||at.getPa().gettext(1).trim().isEmpty()||at.getPa().getTa().getText().trim().isEmpty())
{
	f=false;
}
	return f;
}
public static boolean compvacioact(AltaActividades aa)
{
	boolean f=true;
	if(aa.getPpa().gettext(0).trim().isEmpty()||aa.getPpa().gettext(1).trim().isEmpty()||aa.getPpa().getCbidsala().getSelectedItem()==null||aa.getPpa().getCbidemple().getSelectedItem()==null)
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
	at.getPa().settext(0);
	at.getPa().settext(1);
	b.cerrarConex();
}
public static void insertaractividad(AltaActividades aa) throws SQLException, ClassNotFoundException
{
	BaseDatos b=new BaseDatos();
	b.abrircon();
	int id,idsala,idemple;
	idsala=Integer.parseInt(aa.getPpa().getCbidsala().getSelectedItem().toString());
	idemple=Integer.parseInt(aa.getPpa().getCbidemple().getSelectedItem().toString());
	ResultSet res;
	res=b.ejecutarSQL1("Select id from actividad order by id desc limit 1");
	if(res.next())
	{
		id=res.getInt("id")+1;
	}else {
		id=1;
	}
	b.ejecutarSQL2("Insert into actividad values("+id+",'"+aa.getPpa().gettext(0)+"','"+aa.getPpa().gettext(1)+"',"+idsala+","+idemple+")");
	aa.getPpa().settext(0);
	aa.getPpa().settext(1);
	aa.getPpa().getCbidsala().setSelectedIndex(-1);
	aa.getPpa().getCbidemple().setSelectedIndex(-1);
	b.cerrarConex();
}
public static boolean compnomb(String nombre)
{boolean f=true;
	for (int i = 0; i < nombre.length()&&f==true; i++) {
		if(Character.isDigit(nombre.charAt(i)))
			f=false;
	}
	return f;
}
public static void ids(AltaActividades aa,int pos) throws ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.abrircon();
	if(pos==1)
	{
		ResultSet res;
		res=b.ejecutarSQL1("Select id from sala ");
		while(res.next())
		{
		
			aa.getPpa().getCbidsala().addItem(res.getInt("id"));
		}
	}else if(pos==2)
	{
		ResultSet res;
		res=b.ejecutarSQL1("Select id from empleado ");
		while(res.next())
		{
			aa.getPpa().getCbidemple().addItem(res.getInt("id"));
		}
	}
	b.cerrarConex();
}
public static void pdf(int pos)
{
	JFileChooser elegir=new JFileChooser();
	elegir.setDialogTitle("Guardar PDF como...");
	elegir.setFileSelectionMode(JFileChooser.FILES_ONLY);
	if(pos==1)
	{
		elegir.setSelectedFile(new File("Tarifas.pdf"));
	}else {
		elegir.setSelectedFile(new File("Salas.pdf"));
	}
	int seleccion=elegir.showSaveDialog(null);
	if(seleccion==JFileChooser.APPROVE_OPTION)
	{
		File guardar=elegir.getSelectedFile();
	}
	Document doc=new Document();
	PdfWriter.getInstance(doc,new FileOutputStream(pdfPath));
	doc.open();
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
		case "Aceptartar":
			if(compvaciotar(at)==false)
			{
				JOptionPane.showMessageDialog(null, "Los campos no han sido rellenados","error",JOptionPane.ERROR_MESSAGE);
			}else if(compnomb(at.getPa().gettext(0))==false)
			{
				JOptionPane.showMessageDialog(null, "El nombre no puede tener numeros","error",JOptionPane.ERROR_MESSAGE);
				at.getPa().settext(0);
			}else {
				try {
					insertartarifa(at);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case "Aceptaract":
			if(compvacioact(altaA)==false)
			{
				JOptionPane.showMessageDialog(null, "Los campos no han sido rellenados correctamente","error",JOptionPane.ERROR_MESSAGE);
			}else if(compnomb(altaA.getPpa().gettext(0))==false)
				{
					JOptionPane.showMessageDialog(null, "El nombre no puede tener numeros","error",JOptionPane.ERROR_MESSAGE);
					altaA.getPpa().settext(0);
				}else {
					try {
						insertaractividad(altaA);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			
			break;
		case "Cancelartar":
			at.getPa().settext(0);
			at.getPa().getTa().setText(	" ");
			at.getPa().settext(1);
			break;
		case "Cancelaract":
			altaA.getPpa().settext(0);
			altaA.getPpa().settext(1);
			altaA.getPpa().getCbidsala().setSelectedIndex(-1);
			altaA.getPpa().getCbidemple().setSelectedIndex(-1);
		}
		
	}

}