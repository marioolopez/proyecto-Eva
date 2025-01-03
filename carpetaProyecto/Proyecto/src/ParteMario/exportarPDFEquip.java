package ParteMario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class exportarPDFEquip implements ActionListener {
    private ListadoEquipMant listado;
    private Paragraph espacio, listados;
    public exportarPDFEquip(ListadoEquipMant listado) {
        this.listado = listado;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if("exportar".equals(e.getActionCommand())) {
            try {
            	descargarpdf(listado.getTabla());
                System.out.println("PDF generado");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private void descargarpdf(JTable tabla) throws Exception {
        Document pdf = new Document(); //creo un archivo PDF
        PdfWriter.getInstance(pdf, new FileOutputStream("Equipamientos.pdf")); //creo el nombrer del archivo
        pdf.open(); //y lo abro

        listados = new Paragraph("Listado de Equipamientos");
        espacio = new Paragraph("\n");
     
        pdf.add(listados);
        pdf.add(espacio);

        
        TableModel modelo = tabla.getModel(); //creo una tabla en el pdf
        PdfPTable pdfTabla = new PdfPTable(modelo.getColumnCount()); //igual al número de columnas

        //agrego encabezados de columna
        for(int col = 0; col < modelo.getColumnCount(); col++){
            pdfTabla.addCell(modelo.getColumnName(col));
        }

        //agrego datos de la tabla
        for(int datos = 0; datos < modelo.getRowCount(); datos++){
            for(int col = 0; col < modelo.getColumnCount(); col++) {
                pdfTabla.addCell(modelo.getValueAt(datos, col).toString());
            }
        }

        pdf.add(pdfTabla); //añadimos en el pdf todo lo que le hemos metido a el tableModel
        pdf.close();
    }
}
