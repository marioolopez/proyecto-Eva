package parteyoel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelParaContenidoAltas extends JPanel{
	private JLabel []lb;
	private JTextArea ta;
	private JTextField []tx;
PanelParaContenidoAltas()
{
	this.setLayout(new FlowLayout(FlowLayout.CENTER));
		lb=new JLabel[3];
		ta=new JTextArea();
		tx=new JTextField[2];
		String []titulos= {"Nombre:","Descripcion:","Precio:"};
		for (int i = 0; i < lb.length; i++) {
			lb[i]=new JLabel(titulos[i]);
			lb[i].setPreferredSize(new Dimension(350,20));
		}
		for (int i = 0; i < tx.length; i++) {
			tx[i]=new JTextField();
			tx[i].setPreferredSize(new Dimension(350,20));
		}
		ta.setPreferredSize(new Dimension(350,200));
		this.add(lb[0]);
		this.add(tx[0]);
		this.add(lb[1]);
		this.add(ta);
		this.add(lb[2]);
		this.add(tx[1]);
}
public String gettext(int pos)
{
	return tx[pos].getText();
}
public void settext(int pos,String cad)
{
	 tx[pos].setText(cad);
}
public JLabel[] getLb() {
	return lb;
}
public void setLb(JLabel[] lb) {
	this.lb = lb;
}
public JTextArea getTa() {
	return ta;
}
public void setTa(JTextArea ta) {
	this.ta = ta;
}
public JTextField[] getTx() {
	return tx;
}
public void setTx(JTextField[] tx) {
	this.tx = tx;
}

}
