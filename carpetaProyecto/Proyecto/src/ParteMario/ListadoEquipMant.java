package ParteMario;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ListadoEquipMant extends JDialog {
	private JButton exportarPdf;
	private latizq lizq;
	private DefaultTableModel tablaDef;
	private JTable tabla;
	public ListadoEquipMant() throws SQLException, ClassNotFoundException {
		lizq = new latizq();
		this.setTitle("LISTADO DE LOS EQUIPAMIENTOS EN BASE DE DATOS");
		
		String []nombreCampos = {"IDEq", "NombreEq", "Descrp", "IDAct", "IDMant"};
		tablaDef = new DefaultTableModel(nombreCampos, 0); //para crear tantas filas como quieras
		tabla = new JTable(tablaDef);
        JScrollPane scrollPane = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        cargarInfo();
        
        this.add(scrollPane, BorderLayout.CENTER);
        
               
        JPanel pboton = new JPanel();
        exportarPdf = new JButton("Imprimir Equipamietos PDF");
        exportarPdf.setActionCommand("exportar");
        exportarPdf.addActionListener(new exportarPDFEquip(this));
        pboton.add(exportarPdf);
        this.add(pboton, BorderLayout.SOUTH);      
	}

	private void cargarInfo() throws SQLException {
		 ResultSet rs = lizq.getBd().ejecutarSQL1("SELECT id, nombre, descripcion, idactividad, idmantenimiento FROM equipamiento");
	        while(rs.next()) {
	        	tablaDef.addRow(new Object[] { //obligatoriamente tienes que meterlo en un objeto
	        			rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("idactividad"),
                        rs.getInt("idmantenimiento")
	        	});
	        }
	        rs.close();
	        lizq.getBd().cerrarConex();
	}
	public JButton getExportarPdf() {
		return exportarPdf;
	}
	public void setExportarPdf(JButton exportarPdf) {
		this.exportarPdf = exportarPdf;
	}
	public latizq getLizq() {
		return lizq;
	}
	public void setLizq(latizq lizq) {
		this.lizq = lizq;
	}
	public DefaultTableModel getTablaDef() {
		return tablaDef;
	}
	public void setTablaDef(DefaultTableModel tablaDef) {
		this.tablaDef = tablaDef;
	}
	public JTable getTabla() {
		return tabla;
	}
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
}