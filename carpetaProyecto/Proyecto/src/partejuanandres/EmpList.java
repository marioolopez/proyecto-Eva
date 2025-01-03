package partejuanandres;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Conexiones.BaseDatos;
import paqueteprincipal.ventanaPrincipal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
public class EmpList extends JInternalFrame{

	
	private JTable listado;
	private JButton exportar;
	public EmpList () throws ClassNotFoundException, SQLException{
		 super("LISTA DE EMPLEADOS",false,true,false,false);
		 
		
		 
		 
		 
	        this.getContentPane().setLayout(new FlowLayout());
	        this.setPreferredSize(new Dimension(800, 600));

	        listado = new JTable();
	        DefaultTableModel dtm = new DefaultTableModel() {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Desactivamos las celdas para que no sean editables
	            }
	        };

	        listado.setModel(dtm);

	        listado.setSelectionBackground(new Color(128, 128, 119));
	        listado.setSelectionForeground(Color.WHITE);
	        listado.setFont(new Font("Arial Black", Font.BOLD, 12));
	        dtm.addColumn("ID");
	        dtm.addColumn("NOMBRE");
	        dtm.addColumn("DNI");
	        dtm.addColumn("SALARIO");

	        // Cargamos los datos en la tabla
	        CargarTabla(dtm);

	        JScrollPane barras = new JScrollPane(listado);
	        barras.setPreferredSize(new Dimension(750, 400));
	        JTableHeader titulos = listado.getTableHeader();

	        titulos.setFont(new Font("Arial Black", Font.BOLD, 17));
	        titulos.setBackground(new Color(173, 173, 151));

	        exportar = new JButton("EXPORTAR REGISTROS A PDF");
	        exportar.setFont(new Font("Arial Black",Font.BOLD,17));
	        exportar.setBackground(new Color(173, 173, 151));
	        
	      //EXPORTACION A PDF UTILIZANDO LA LIBRERIA ITEXT QUE SE DIVIDE EN VARIOS PLUGINS
	        
	        
	        exportar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    // Elegir donde guardar el PDF
	                    JFileChooser fileChooser = new JFileChooser();
	                    fileChooser.setDialogTitle("Exportar Datos de Empleados");
	                    int userSelection = fileChooser.showSaveDialog(EmpList.this);

	                    if (userSelection == JFileChooser.APPROVE_OPTION) {
	                        String filePath = fileChooser.getSelectedFile().getAbsolutePath(); //Guardamos el PATH elegido por el usuario

	                        // Creamos el documento PDF
	                        PdfWriter writer = new PdfWriter(filePath + ".pdf"); //Creamos el archivo pdf en la ruta elegida por el usuario
	                        PdfDocument pdfDocument = new PdfDocument(writer);
	                        Document document = new Document(pdfDocument); 

	                        // Añadimos un titulo
	                        document.add(new Paragraph("Lista de Empleados").setBold().setFontSize(18).setMarginBottom(20)); 

	                        // Creamos la tabla para pdf
	                        int columnCount = listado.getColumnCount();
	                        Table pdfTable = new Table(columnCount); 

	                        // Añadimos las cabeceras
	                        for (int col = 0; col < columnCount; col++) {
	                            pdfTable.addHeaderCell(listado.getColumnName(col));
	                        }

	                        // Añadir las filas de LISTADO a la tabla que sera exportada
	                        for (int row = 0; row < listado.getRowCount(); row++) {
	                            for (int col = 0; col < columnCount; col++) {
	                                Object cellValue = listado.getValueAt(row, col);
	                                pdfTable.addCell(cellValue != null ? cellValue.toString() : "");
	                            }
	                        }

	                        // Añadimos la tabla al documento
	                        document.add(pdfTable);

	                        // Cerrar documento
	                        document.close();
	                        JOptionPane.showMessageDialog(EmpList.this, "Datos Exportados Correctamente");
	                    }
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(EmpList.this, "Error al guardar el PDF: " + ex.getMessage());
	                }
	            }
	        });
	        	
	        this.getContentPane().add(barras);
	        this.getContentPane().add(exportar);
	        this.pack();
	        this.setVisible(true);
		
	}
	private void CargarTabla (DefaultTableModel modelo) throws ClassNotFoundException, SQLException {
		BaseDatos bd=new BaseDatos();
		bd.conexionBD();
		ResultSet rs=bd.ejecutarSQL1("SELECT * FROM empleado");
		
		while (rs.next()) {
			
			modelo.addRow(new Object [] {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)});
			
			
		}
		
		
		bd.cerrarConex();
		
	}
	
}
