package paqueteprincipal;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import partejuanandres.EmpList;
import partejuanandres.PanelEmp;

public class ClickEmpleados implements ActionListener {

	private ventanaPrincipal v;
	private PanelEmp pel;
	public ClickEmpleados (ventanaPrincipal vp){
		v=vp;
	}
	public ClickEmpleados (ventanaPrincipal vp,PanelEmp e) {
		v=vp;
		pel=e;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		switch (e.getActionCommand()) {
		
		case "alt": PanelEmp pe;
			try {
				pe = new PanelEmp(1,v);
				v.getContentPane().removeAll();
				v.getContentPane().add(pe);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			break;
		case "baj": PanelEmp pe2;
			try {
				pe2 = new PanelEmp(2,v);
				v.getContentPane().removeAll();
				v.getContentPane().add(pe2);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "mod": PanelEmp pe3;
			try {
				pe3 = new PanelEmp(3,v);
				v.getContentPane().removeAll();
				v.getContentPane().add(pe3);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "showlistemp": try {
				EmpList listaempleados=new EmpList();
				v.getContentPane().removeAll();
				v.getContentPane().add(listaempleados);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break; 
		case "acciones":  int identi=pel.getIdentificador();
					switch (identi) {
					
					case 1: try {
							pel.verificarcampos();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case 2:
						int opcionuser=JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar este Empleado de la Base de Datos?","Escoja una opcion",JOptionPane.YES_NO_CANCEL_OPTION);
						if (opcionuser==0)
							try {
								pel.borrarEmpleado();
								pel.vaciarcampos();
								pel.CampoCodigo().setEnabled(true);
								pel.CampoCodigo().setEditable(true);
								pel.activarbtnborrar().setEnabled(false);
								pel.CasillaVerificar().setEnabled(false);
								pel.CasillaVerificar().setSelected(false);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						break;
					case 3: try {
							pel.verificarcampos();
							
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
					
					
					}
			
			break;
		case "search":  try {
				if (pel.buscarempleado())
				{
					if (pel.getIdentificador()==2)
					{
						System.out.println("Estoy buscando para borrar");
						pel.deshabilitar();
					}
					else if (pel.getIdentificador()==3)
					{
						System.out.println("Estoy buscando para modificar");
						pel.activarcampos();
					}
					pel.CampoCodigo().setEnabled(false);
					JOptionPane.showMessageDialog(pel, "Se han cargado los datos del Empleado");
				}
					
			} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		
		}
		
		
	}

}
