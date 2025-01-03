package paqueteprincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Conexiones.BaseDatos;

import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfWriter;

import parteyoel.AltaActividades;
import parteyoel.Altatarifas;
import parteyoel.BajaActividad;
import parteyoel.BajaTarifas;
import parteyoel.Buscar;

public class escuchador implements ActionListener {
private static ventanaPrincipal vp;
private Altatarifas at;
private AltaActividades altaA;
private BajaTarifas bt;
private BajaActividad ba;
private Buscar bs;
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
public escuchador(BajaTarifas btarifa)
{
	bt=btarifa;
}
public escuchador(Buscar bb)
{
bs=bb;	
}
public escuchador(BajaActividad bactividad)
{
	ba=bactividad;
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
	b.conexionBD();
	ResultSet res;
	res=b.ejecutarSQL1("Select id from tarifa order by id desc limit 1");
	if(res.next())
	{
		id=res.getInt("id")+1;
	}else {
		id=1;
	}
	b.ejecutarSQL2("Insert into tarifa values("+id+",'"+at.getPa().gettext(0)+"','"+at.getPa().getTa().getText()+"',"+Double.parseDouble(at.getPa().gettext(1))+")");
	at.getPa().settext(0," ");
	at.getPa().settext(1," ");
	b.cerrarConex();
}
public static void insertaractividad(AltaActividades aa) throws SQLException, ClassNotFoundException
{
	BaseDatos b=new BaseDatos();
	b.conexionBD();
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
	aa.getPpa().settext(0," ");
	aa.getPpa().settext(1," ");
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
	b.conexionBD();
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
public static void idsbajatar(BajaTarifas bt) throws ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.conexionBD();
	
		ResultSet res;
		res=b.ejecutarSQL1("Select id from tarifa ");
		while(res.next())
		{
		
			bt.getBb().getCb().addItem(res.getInt("id"));
		}

	b.cerrarConex();
}
public static void idsbajaact(BajaActividad bact) throws ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.conexionBD();
	
		ResultSet res;
		res=b.ejecutarSQL1("Select id from actividad ");
		while(res.next())
		{
		
			bact.getBb().getCb().addItem(res.getInt("id"));
		}

	b.cerrarConex();
}
public static void pdf(int pos) throws FileNotFoundException, ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.conexionBD();
	JFileChooser elegir=new JFileChooser();
	elegir.setDialogTitle("Guardar PDF como...");
	int eleccion=elegir.showSaveDialog(vp);
	if(eleccion==elegir.APPROVE_OPTION)
	{
		String ruta=elegir.getSelectedFile().getAbsolutePath();//guardo la ruta que halla elegido el usuario
		PdfWriter lapiz=new PdfWriter(ruta + ".pdf");
		PdfDocument boli=new PdfDocument(lapiz); //esto es como los objetos de escritura en txt
		Document doc=new Document(boli);
		if(pos==1)//simplemente diferenciacion de los dos tipos de pdf que quiero crear
		{doc.add(new Paragraph("Tarifas"));
			ResultSet res;
			res=b.ejecutarSQL1("Select * from tarifa");//aqui se añade el rsultado de la consulta en unparrafo dentro del documento
		
			while(res.next())
			{
				doc.add(new Paragraph("Nombre: "+res.getString("nombre")+"Descripcion: "+res.getString("descripcion")+"Precio: "+res.getDouble("precio")));
			}
			doc.close();//una vez acabado cierro el documento
		}else if(pos==2){
			doc.add(new Paragraph("Actividades"));
			ResultSet res;
			res=b.ejecutarSQL1("Select * from actividad");
			doc.add(new Paragraph("Nombre"+ "\t"+"Descripcion"+"\t"+"idsala"+"\t"+"idempleado"));
			while(res.next())
			{
				doc.add(new Paragraph(res.getString("nombre")+"\t"+res.getString("descripcion")+"\t"+res.getInt("idsala")+"\t"+res.getInt("idempleado")));
			}
			doc.close();//una vez acabado cierro el documento
		}
		
	}

}


private void buscar(BajaTarifas bs2,BajaActividad ba,int pos) throws ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.conexionBD();
	ResultSet res;
	if(pos==1)
	{
		
		res=b.ejecutarSQL1("Select * from tarifa where id like "+bs2.getBb().getCb().getSelectedItem()+"");
		if(res.next())
		{
			bs2.getPbaja().settext(0,res.getString("nombre"));
			bs2.getPbaja().settext(1,""+res.getDouble("precio"));
			bs2.getPbaja().getTa().setText(res.getString("descripcion"));
		}
		
	}else {
		res=b.ejecutarSQL1("Select * from actividad where id like "+ba.getBb().getCb().getSelectedItem()+"");
		if(res.next())
		{
			ba.getPa().settext(0,res.getString("nombre"));
			ba.getPa().settext(1,res.getString("descripcion"));
			ba.getPa().getCbidsala().addItem(res.getInt("idsala"));
			ba.getPa().getCbidemple().addItem(res.getInt("idempleado"));
		}
	}
	
}
public void borrar(BajaTarifas bt,BajaActividad ba,int select) throws ClassNotFoundException, SQLException
{
	BaseDatos b=new BaseDatos();
	b.conexionBD();
	if(select==1)
	{
		int opc=JOptionPane.showConfirmDialog(null,"Seguro desea eliminar?","Confirmacion",JOptionPane.YES_NO_OPTION );
		if(opc==0)
		{
			b.ejecutarSQL2("delete from tarifa where id like "+bt.getBb().getCb().getSelectedItem()+"");
			JOptionPane.showInternalMessageDialog(null,"Tarifa eliminada");
			bt.getBb().getCb().setSelectedItem(-1);
			bt.getPbaja().settext(0, " ");
			bt.getPbaja().getTa().setText(" ");
			bt.getPbaja().settext(1," ");
			bt.getBb().getCb().setSelectedIndex(-1);
			
		}else {
			
		}
	}else {
		int opc=JOptionPane.showConfirmDialog(null,"Seguro desea eliminar?","Confirmacion",JOptionPane.YES_NO_OPTION );
		if(opc==0)
		{
			b.ejecutarSQL2("delete from actividad where id like "+ba.getBb().getCb().getSelectedItem()+"");
			JOptionPane.showInternalMessageDialog(null,"Actividad eliminada");
			ba.getBb().getCb().setSelectedItem(-1);
			ba.getPa().settext(0, " ");
			ba.getPa().settext(1," ");
			ba.getPa().getCbidemple().setSelectedIndex(-1);
			ba.getPa().getCbidsala().setSelectedIndex(-1);
			ba.getBb().getCb().setSelectedIndex(-1);
		}else {
			
		}
	}
	
	
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
				at.getPa().settext(0," ");
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
					altaA.getPpa().settext(0," ");
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
			at.getPa().settext(0," ");
			at.getPa().getTa().setText(	" ");
			at.getPa().settext(1," ");
			break;
		case "Cancelaract":
			altaA.getPpa().settext(0," ");
			altaA.getPpa().settext(1," ");
			altaA.getPpa().getCbidsala().setSelectedIndex(-1);
			altaA.getPpa().getCbidemple().setSelectedIndex(-1);
			break;
		case "PDFtar":
			try {
				pdf(1);
			} catch (FileNotFoundException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		case "PDFalt":
			try {
				pdf(2);
			} catch (FileNotFoundException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "BajasAct":
			BajaActividad bac=new BajaActividad();
			vp.getContentPane().removeAll();
			vp.getContentPane().add(bac);
			bac.getPa().getTx()[0].setEnabled(false);
			bac.getPa().getTx()[1].setEnabled(false);
			bac.getPa().getCbidemple().setSelectedItem(-1);
			bac.getPa().getCbidsala().setSelectedItem(-1);
			try {
				idsbajaact(bac);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "BajaTar":
			BajaTarifas btar=new BajaTarifas();
			vp.getContentPane().removeAll();
			vp.getContentPane().add(btar);
			btar.getPbaja().getTx()[0].setEnabled(false);
			btar.getPbaja().getTx()[1].setEnabled(false);
			btar.getPbaja().getTa().setEnabled(false);
			try {
				idsbajatar(btar);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Buscartar":
			try {
				buscar(bt,ba,1);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "borrartar":
			try {
				borrar(bt,ba,1);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Buscaract":
			try {
				buscar(bt,ba,2);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "borraract":
			try {
				borrar(bt,ba,2);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		}
	}

}
