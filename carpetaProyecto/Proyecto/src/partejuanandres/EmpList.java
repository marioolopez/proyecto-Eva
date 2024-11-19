package partejuanandres;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import paqueteprincipal.BaseDatos;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EmpList extends JInternalFrame{

	
	private JTable listado;
	
	public EmpList () throws ClassNotFoundException, SQLException{
		super("LISTA DE EMPLEADOS");
		this.getContentPane().setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(800,600));
		
		listado=new JTable();
		DefaultTableModel dtm=new DefaultTableModel();
		listado.setModel(dtm);
		
		listado.setSelectionBackground(new Color(128, 128, 119));
		listado.setSelectionForeground(Color.WHITE);
		listado.setFont(new Font("Arial Black",Font.BOLD,12));
		dtm.addColumn("ID");
		dtm.addColumn("NOMBRE");
		dtm.addColumn("DNI");
		dtm.addColumn("SALARIO");
		
		
		CargarTabla(dtm);
		
		JScrollPane barras=new JScrollPane(listado);
		barras.setPreferredSize(new Dimension(750,400));
		JTableHeader titulos=listado.getTableHeader();
		
		titulos.setFont(new Font("Arial Black",Font.BOLD,17));
		titulos.setBackground(new Color(173, 173, 151));
		
		this.getContentPane().add(barras);
		this.setVisible(true);
		
	}
	private void CargarTabla (DefaultTableModel modelo) throws ClassNotFoundException, SQLException {
		BaseDatos bd=new BaseDatos();
		
		ResultSet rs=bd.ejecutarSQL1("SELECT * FROM empleado");
		
		while (rs.next()) {
			
			modelo.addRow(new Object [] {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)});
			
			
		}
		
		
		bd.cerrarConex();
		
	}
	
}
