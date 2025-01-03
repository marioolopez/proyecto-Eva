package paqueteprincipal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import com.toedter.calendar.JCalendar;

import marcos.VentanaCliente;
public class ventanaPrincipal extends JFrame {
    private JLabel fotoGym;
    private ImageIcon img;
    private JMenuBar barra;
    private JMenuItem cli1, cli2, cli3, emp1, emp2, emp3,emp4, equip1, prod1, act1, act2,tar1,tar2,ped1, ped2;
    private JMenu menu1, menu2, menu3, menu4, menu5, menu6, menu7;
    
    private VentanaCliente ventanaCliente;
    ventanaPrincipal() throws ClassNotFoundException, SQLException {
        setTitle("BIENVENIDO A NUESTRA WEB");
        this.setBounds(200, 200, 800, 600);
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //es la barra superior
        barra = new JMenuBar();
        barra.setBackground(new Color(211, 211, 190));
        barra.setPreferredSize(new Dimension(120, 60));

         
        img = new ImageIcon("img/gym.jpg");
        Image tamano = img.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        fotoGym = new JLabel(new ImageIcon(tamano));
        barra.add(fotoGym);
        
        
        menu1 = new JMenu("CLIENTES");
        menu1.setFont(new Font("Arial Black", Font.BOLD, 12));
        cli1 = new JMenuItem("Clientes");
        cli1.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu1.add(cli1);

        
        
        menu2 = new JMenu("EMPLEADOS");
        menu2.setFont(new Font("Arial Black", Font.BOLD, 12));
        emp1 = new JMenuItem("Alta de Empleados");
        emp1.setActionCommand("alt");
        emp1.addActionListener(new ClickEmpleados(this));
        emp1.setFont(new Font("Arial Black", Font.BOLD, 12));
        emp2 = new JMenuItem("Baja de Empleados");
        emp2.setActionCommand("baj");
        emp2.addActionListener(new ClickEmpleados(this));
        emp2.setFont(new Font("Arial Black", Font.BOLD, 12));
        emp3 = new JMenuItem("Modificaciones de Empleados");
        emp3.setActionCommand("mod");
        emp3.addActionListener(new ClickEmpleados(this));
        emp3.setFont(new Font("Arial Black", Font.BOLD, 12));
        emp4=new JMenuItem("Mostrar lista de Empleados");
        emp4.setFont(new Font("Arial Black",Font.BOLD,12));
        emp4.setActionCommand("showlistemp");
        emp4.addActionListener(new ClickEmpleados(this));
        menu2.add(emp1);
        menu2.addSeparator();
        menu2.add(emp2);
        menu2.addSeparator();
        menu2.add(emp3);
        menu2.addSeparator();
        menu2.add(emp4);

        
        
        menu3 = new JMenu("EQUIPAMIENTO");
        menu3.setFont(new Font("Arial Black", Font.BOLD, 12));
        equip1 = new JMenuItem("Alta Equipamiento y Mantenimiento");
        equip1.addActionListener(new escuchaEquipamiento(this));
        equip1.setActionCommand("escuchaEquipamiento");
        equip1.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu3.add(equip1);

        
        
        menu4 = new JMenu("PRODUCTOS");
        menu4.setFont(new Font("Arial Black", Font.BOLD, 12));       
        prod1 = new JMenuItem("Agregar Producto");
        prod1.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu4.add(prod1);
        prod1.addActionListener(new MenuAccion(this));
        prod1.setActionCommand("Productos");
        
        
        menu5 = new JMenu("ACTIVIDADES");
        menu5.setFont(new Font("Arial Black", Font.BOLD, 12));
        act1 = new JMenuItem("Agegar Nueva Actividad");
        act1.setFont(new Font("Arial Black", Font.BOLD, 12));
        act2=new JMenuItem("Eliminar actividad");
        act2.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu5.add(act1);
        menu5.add(act2);
        act1.setActionCommand("Actividades");
        act1.addActionListener(new escuchador(this));
        act2.setActionCommand("BajasAct");
        act2.addActionListener(new escuchador(this));
        
        
        
        menu6 = new JMenu("TARIFA");
        tar1 = new JMenuItem("Alta de Tarifa");
        tar1.setFont(new Font("Arial Black", Font.BOLD, 12));
        tar2=new JMenuItem("Baja Tarifa");
        tar2.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu6.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu6.add(tar1);
        menu6.add(tar2);
        tar1.setActionCommand("Tarifa");
        tar1.addActionListener(new escuchador(this));
        tar2.setActionCommand("BajaTar");
        tar2.addActionListener(new escuchador(this));
        
        menu7 = new JMenu("PEDIDOS");
        ped1 = new JMenuItem("Pedidos Añandir");
        ped1.setActionCommand("pedidoA");
        ped1.addActionListener(new MenuAccion(this));
        ped1.setFont(new Font("Arial Black", Font.BOLD, 12));
        ped2 = new JMenuItem("Pedidos Modificar/Ver");
        ped2.setActionCommand("pedidoM");
        ped2.addActionListener(new MenuAccion(this));
        ped2.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu7.setFont(new Font("Arial Black", Font.BOLD, 12));
        menu7.add(ped1);
        menu7.add(ped2);
        
        
        barra.add(menu1);
        barra.add(menu2);
        barra.add(menu3);
        barra.add(menu4);
        barra.add(menu5);
        barra.add(menu6);
        barra.add(menu7);
        this.setJMenuBar(barra); //hasta aqui todo son opciones en el JMenuBar
        //-----------------------------------------------------------
       
        
        
   
       
        
        
        
        //------------------------------------------------------------
        //panel izquierdo "GIMNASIO DAM"
        JPanel panelIzquierda = new JPanel(new GridLayout(12, 1));
        panelIzquierda.setBackground(new Color(211, 211, 190));
        panelIzquierda.setPreferredSize(new Dimension(60, 600));
        //Las etiquetas con las letras de "GIMNASIO DAM"
        JLabel uno = new JLabel("G", SwingConstants.CENTER);
        uno.setFont(new Font("Arial Black", Font.BOLD, 24));
        uno.setForeground(new Color(70, 70, 70));
        JLabel dos = new JLabel("I", SwingConstants.CENTER);
        dos.setFont(new Font("Arial Black", Font.BOLD, 24));
        dos.setForeground(new Color(70, 70, 70));
        JLabel tres = new JLabel("M", SwingConstants.CENTER);
        tres.setFont(new Font("Arial Black", Font.BOLD, 24));
        tres.setForeground(new Color(70, 70, 70));
        JLabel cuatro = new JLabel("N", SwingConstants.CENTER);
        cuatro.setFont(new Font("Arial Black", Font.BOLD, 24));
        cuatro.setForeground(new Color(70, 70, 70));
        JLabel cinco = new JLabel("A", SwingConstants.CENTER);
        cinco.setFont(new Font("Arial Black", Font.BOLD, 24));
        cinco.setForeground(new Color(70, 70, 70));
        JLabel seis = new JLabel("S", SwingConstants.CENTER);
        seis.setFont(new Font("Arial Black", Font.BOLD, 24));
        seis.setForeground(new Color(70, 70, 70));
        JLabel siete = new JLabel("I", SwingConstants.CENTER);
        siete.setFont(new Font("Arial Black", Font.BOLD, 24));
        siete.setForeground(new Color(70, 70, 70));
        JLabel ocho = new JLabel("O", SwingConstants.CENTER);
        ocho.setFont(new Font("Arial Black", Font.BOLD, 24));
        ocho.setForeground(new Color(70, 70, 70));
        JLabel nueve = new JLabel();
        nueve.setFont(new Font("Arial Black", Font.BOLD, 24));
        nueve.setForeground(new Color(70, 70, 70));
        JLabel diez = new JLabel("D", SwingConstants.CENTER);
        diez.setFont(new Font("Arial Black", Font.BOLD, 24));
        diez.setForeground(new Color(70, 70, 70));
        JLabel once = new JLabel("A", SwingConstants.CENTER);
        once.setFont(new Font("Arial Black", Font.BOLD, 24));
        once.setForeground(new Color(70, 70, 70));
        JLabel doce = new JLabel("M", SwingConstants.CENTER);
        doce.setFont(new Font("Arial Black", Font.BOLD, 24));
        doce.setForeground(new Color(70, 70, 70));
        panelIzquierda.add(uno);
        panelIzquierda.add(dos);
        panelIzquierda.add(tres);
        panelIzquierda.add(cuatro);
        panelIzquierda.add(cinco);
        panelIzquierda.add(seis);
        panelIzquierda.add(siete);
        panelIzquierda.add(ocho);
        panelIzquierda.add(nueve);
        panelIzquierda.add(diez);
        panelIzquierda.add(once);
        panelIzquierda.add(doce);
        this.add(panelIzquierda, BorderLayout.WEST);
        //------------------------------------------------------------------
        
        
        
        

        
        //-----------------------------------------------------------------
        //este es el panel centro que tiene las fotos y demás
        JPanel panelCentro = new JPanel(new GridLayout(2, 2));
       
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(240, 240, 230)); 
  
        JLabel msj2 = new JLabel("¡Le dejamos nuestro calendario!");
        msj2.setFont(new Font("Arial Black", Font.BOLD, 13));
   
        
        JCalendar calendario = new JCalendar();
        calendario.setBackground(Color.white); 
        calendario.setPreferredSize(new Dimension(200, 160)); 
        calendario.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        p1.add(msj2,BorderLayout.NORTH);
        p1.add(calendario); 
        
 
        JPanel p2 = new JPanel();
        p2.setBackground(new Color(240, 240, 230)); 
        JLabel t2 = new JLabel("¡CON NUEVOS EQUIPAMIENTOS!");
        t2.setFont(new Font("Arial Black", Font.BOLD, 12));
        ImageIcon imgEntrenamiento2 = new ImageIcon(new ImageIcon("img/img2.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
        JLabel labelEntrenamiento2 = new JLabel(imgEntrenamiento2);
        p2.add(t2, BorderLayout.NORTH);
        p2.add(labelEntrenamiento2);
        
       
        
        JPanel p3 = new JPanel();
        p3.setBackground(new Color(240, 240, 230)); 
        JLabel t = new JLabel("¡PRUEBA NUESTRAS ACTIVIDADES!");
        t.setFont(new Font("Arial Black", Font.BOLD, 12));
        ImageIcon imgEntrenamiento1 = new ImageIcon(new ImageIcon("img/img1.jpg").getImage().getScaledInstance(330, 200, Image.SCALE_SMOOTH));
        JLabel labelEntrenamiento1 = new JLabel(imgEntrenamiento1);
        p3.add(t, BorderLayout.NORTH);
        p3.add(labelEntrenamiento1);
        
       
        
        JPanel p4 = new JPanel(new BorderLayout());
        p4.setBackground(new Color(240, 240, 230)); 
        JLabel imgmapa = new JLabel(new ImageIcon(new ImageIcon("img/mapa.png").getImage().getScaledInstance(130, 110, Image.SCALE_SMOOTH)));
        imgmapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try { //utilizamos desktop.browse para abrir un enlace en el navegador
                    Desktop.getDesktop().browse(new URI("https://www.google.com/maps/place/Gimnasio+Kronos/@40.9655002,-5.6690048,17z/data=!3m1!4b1!4m6!3m5!1s0xd3f263e7bcc58b9:0xfcd86771c948bcd3!8m2!3d40.9655002!4d-5.6690048!16s%2Fg%2F1q65cxh3j?entry=ttu&g_ep=EgoyMDI0MTExMi4wIKXMDSoASAFQAw%3D%3D"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        p4.add(imgmapa, BorderLayout.CENTER);
        
        panelCentro.add(p1);
        panelCentro.add(p2);
        panelCentro.add(p3);
        panelCentro.add(p4);
        this.add(panelCentro, BorderLayout.CENTER);
        //------------------------------------------------------------------------
        

        
     
        
        
        
        
        //-----------------------------------------------------------------------
        //logos de las redes sociales 
        JPanel derechos = new JPanel(new GridBagLayout()); //utilizo esto para que dentro del panel los componentes esten alineados naturalmente
        derechos.setBackground(new Color(50, 50, 50));
        
        JLabel derch = new JLabel("© 2024 Gimnasio DAM. Todos los derechos reservados --");
        JLabel nom = new JLabel("Calle del Deporte, Nº23 -- Nuestras Redes Sociales:");
        derch.setForeground(Color.WHITE);
        nom.setForeground(Color.WHITE);

        
        //panel para el texto y logos
        JPanel conInf = new JPanel();
        conInf.setBackground(new Color(50, 50, 50));
        conInf.add(derch);
        conInf.add(nom);

        //panel de logos
        JPanel panelLogos = new JPanel(new GridBagLayout());
        panelLogos.setBackground(new Color(211, 211, 190));
        
     	//para añadir separacion entre una fila, en este caso haciendo una separacion entre un logo y otro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);
        
        ImageIcon iconoFace = new ImageIcon(new ImageIcon("img/face.png").getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        JLabel titFacebook = new JLabel(iconoFace);
        panelLogos.add(titFacebook, gbc);
        
        ImageIcon iconoInsta = new ImageIcon(new ImageIcon("img/insta.png").getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        JLabel titInstagram = new JLabel(iconoInsta);
        panelLogos.add(titInstagram, gbc);
        
        ImageIcon iconYout = new ImageIcon(new ImageIcon("img/yt.png").getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        JLabel titYoutube = new JLabel(iconYout);
        panelLogos.add(titYoutube, gbc);
        
        ImageIcon iconTwi = new ImageIcon(new ImageIcon("img/tw.png").getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH));
        JLabel titTwitter = new JLabel(iconTwi);
        panelLogos.add(titTwitter, gbc);
        
        cli1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	instanciarVentanaCliente();
            }
        });
        
        
        conInf.add(panelLogos);
        derechos.add(conInf);
        this.add(derechos, BorderLayout.SOUTH);
        
        ventanaCliente = new VentanaCliente(this);//lo instancio aqui para que ventana cliente no este NULL.
        ventanaCliente.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//no hacer nada al cerrar por defecto, porque lo configuro yo dependiendo de si le da a que si o no.
        ventanaCliente.addInternalFrameListener(new InternalFrameAdapter() {//hacer escuchador a la ventanaCliente, para que cuando se cierre, reactive otra vez la ventanaPrincipal.
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {//cuando le des al boton de cerrar, que te salga un JOptionPane con opciones de si o no.
                int opcion = JOptionPane.showConfirmDialog(null,"¿Estás seguro de que deseas cerrar esta ventana?","Confirmar cierre",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                if (opcion == JOptionPane.YES_OPTION) {//si el usuario selecciona si, se cierra la ventana
                    ventanaCliente.dispose();//cerrar ventana
                    //RESETEAR EL TODO LO DE LAS VENTANAS DEL CLIENTE
                    ventanaCliente.getVentanaMultipleCliente().getVentanaAltaCliente().getBordeFormularioAltaCliente().resetearVentanaAlta();
                	ventanaCliente.getVentanaMultipleCliente().getVentanaBajaCliente().getBordeFormularioBajaCliente().resetearVentanaBaja();
                	ventanaCliente.getVentanaMultipleCliente().getVentanaModificacionCliente().getBordeFormularioModificacionCliente().resetearVentanaModificacion();
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {//cuando la ventana del menu del cliente este cerrada, añadir todos los elementos antes removidos.
            	reactivarVentanaPrincipal();
                panelIzquierda.add(uno);
                panelIzquierda.add(dos);
                panelIzquierda.add(tres);
                panelIzquierda.add(cuatro);
                panelIzquierda.add(cinco);
                panelIzquierda.add(seis);
                panelIzquierda.add(siete);
                panelIzquierda.add(ocho);
                panelIzquierda.add(nueve);
                panelIzquierda.add(diez);
                panelIzquierda.add(once);
                panelIzquierda.add(doce);
                add(panelIzquierda, BorderLayout.WEST);
                p1.add(calendario);
                p2.add(t2, BorderLayout.NORTH);
                p2.add(labelEntrenamiento2);
                p3.add(t, BorderLayout.NORTH);
                p3.add(labelEntrenamiento1);
                p4.add(imgmapa, BorderLayout.CENTER);
                panelCentro.add(p1);
                panelCentro.add(p2);
                panelCentro.add(p3);
                panelCentro.add(p4);
                add(panelCentro, BorderLayout.CENTER);
                conInf.add(derch);
                conInf.add(nom);
                panelLogos.add(titFacebook, gbc);
                panelLogos.add(titInstagram, gbc);
                panelLogos.add(titYoutube, gbc);
                panelLogos.add(titTwitter, gbc);
                conInf.add(panelLogos);
                derechos.add(conInf);
                add(derechos, BorderLayout.SOUTH);
            }
        });
        
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void instanciarVentanaCliente() {
        this.getContentPane().removeAll();
        this.setSize(800, 600);
        this.getContentPane().add(ventanaCliente);
        ventanaCliente.setClosable(true);
        ventanaCliente.setVisible(true);
    }
    
    public void reactivarVentanaPrincipal() {
    	this.setJMenuBar(barra);
    	
    }
    
	public VentanaCliente getVentanaCliente() {
		return ventanaCliente;
	}

	public void setVentanaCliente(VentanaCliente ventanaCliente) {
		this.ventanaCliente = ventanaCliente;
	}
    
	public JLabel getFotoGym() {
		return fotoGym;
	}
	public void setFotoGym(JLabel fotoGym) {
		this.fotoGym = fotoGym;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public JMenuBar getBarra() {
		return barra;
	}
	public void setBarra(JMenuBar barra) {
		this.barra = barra;
	}
	public JMenuItem getCli1() {
		return cli1;
	}
	public void setCli1(JMenuItem cli1) {
		this.cli1 = cli1;
	}
	public JMenuItem getCli2() {
		return cli2;
	}
	public void setCli2(JMenuItem cli2) {
		this.cli2 = cli2;
	}
	public JMenuItem getCli3() {
		return cli3;
	}
	public void setCli3(JMenuItem cli3) {
		this.cli3 = cli3;
	}
	public JMenuItem getEmp1() {
		return emp1;
	}
	public void setEmp1(JMenuItem emp1) {
		this.emp1 = emp1;
	}
	public JMenuItem getEmp2() {
		return emp2;
	}
	public void setEmp2(JMenuItem emp2) {
		this.emp2 = emp2;
	}
	public JMenuItem getEmp3() {
		return emp3;
	}
	public void setEmp3(JMenuItem emp3) {
		this.emp3 = emp3;
	}
	public JMenuItem getEquip1() {
		return equip1;
	}
	public void setEquip1(JMenuItem equip1) {
		this.equip1 = equip1;
	}
	public JMenuItem getProd1() {
		return prod1;
	}
	public void setProd1(JMenuItem prod1) {
		this.prod1 = prod1;
	}
	public JMenuItem getAct1() {
		return act1;
	}
	public void setAct1(JMenuItem act1) {
		this.act1 = act1;
	}
	public JMenuItem getTar1() {
		return tar1;
	}
	public void setTar1(JMenuItem tar1) {
		this.tar1 = tar1;
	}
	public JMenu getMenu1() {
		return menu1;
	}
	public void setMenu1(JMenu menu1) {
		this.menu1 = menu1;
	}
	public JMenu getMenu2() {
		return menu2;
	}
	public void setMenu2(JMenu menu2) {
		this.menu2 = menu2;
	}
	public JMenu getMenu3() {
		return menu3;
	}
	public void setMenu3(JMenu menu3) {
		this.menu3 = menu3;
	}
	public JMenu getMenu4() {
		return menu4;
	}
	public void setMenu4(JMenu menu4) {
		this.menu4 = menu4;
	}
	public JMenu getMenu5() {
		return menu5;
	}
	public void setMenu5(JMenu menu5) {
		this.menu5 = menu5;
	}
	public JMenu getMenu6() {
		return menu6;
	}
	public void setMenu6(JMenu menu6) {
		this.menu6 = menu6;
	}   
}