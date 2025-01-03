package marcos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Conexiones.BaseDatos;
import paqueteprincipal.onClick;
import paqueteprincipal.ventanaPrincipal;

public class BordeFormulario extends JPanel {
    private Border bordeFormularioCliente;
    private Border bordeFormularioCliente2;
    private Border bordeFormularioCliente3;
    private JLabel labelIdCliente;
    private JTextField cajaIdCliente;
    private JLabel labelNombreCliente;
    private JTextField cajaNombreCliente;
    private JLabel labelTelefonoCliente;
    private JTextField cajaTelefonoCliente;
    private JLabel labelDniCliente;
    private JTextField cajaDniCliente;
    private JLabel labelEdadCliente;
    private JSlider barraEdadCliente;
    private JLabel labelIdTarifaCliente;
    private JTextField cajaIdTarifaCliente;
    private JLabel lblSexo;
    private JRadioButton BtnHombre,BtnMujer;
    private ButtonGroup grupoBtns;
    private JButton botonAltaCliente;
    private JButton botonBajaCliente;
    private JButton botonModificacionCliente;
    private JButton botonBuscarCliente;
    
    private Color colorPorDefectoBotones;
    public BordeFormulario(ventanaPrincipal ventanaPrincipal) throws SQLException, ClassNotFoundException {
        this.setLayout(new FlowLayout());
        //instanciar mis componentes
        bordeFormularioCliente = BorderFactory.createTitledBorder("DAR DE ALTA");
        bordeFormularioCliente2 = BorderFactory.createTitledBorder("DAR DE BAJA");
        bordeFormularioCliente3 = BorderFactory.createTitledBorder("MODIFICAR");
        labelIdCliente = new JLabel("introduzca su id:");
        cajaIdCliente = new JTextField();
        labelNombreCliente = new JLabel("introduzca su nombre:");
        cajaNombreCliente = new JTextField();
        labelTelefonoCliente = new JLabel("introduzca su telefono:");
        cajaTelefonoCliente = new JTextField();
        labelDniCliente = new JLabel("introduzca su dni:");
        cajaDniCliente = new JTextField();
        labelEdadCliente = new JLabel("introduzca su edad:");
        barraEdadCliente = new JSlider(10, 80);//tiene desde 10 años hasta 80
        barraEdadCliente.setMajorTickSpacing(5);//espacio entre numeros grandes
        barraEdadCliente.setMinorTickSpacing(1);//espacio entre las barras pequeñas
        barraEdadCliente.setPaintTicks(true);//habilita las barras.
        barraEdadCliente.setPaintLabels(true);//Habilita los numeros.
        labelIdTarifaCliente = new JLabel("introduzca su tarifa:");
        cajaIdTarifaCliente = new JTextField();
        lblSexo = new JLabel("Sexo:");
        BtnHombre = new JRadioButton("Hombre");
        BtnMujer = new JRadioButton("Mujer");
        grupoBtns = new ButtonGroup();
        grupoBtns.add(BtnHombre);
        grupoBtns.add(BtnMujer);
        botonAltaCliente = new JButton("ALTA");
        botonBajaCliente = new JButton("BAJA");
        botonModificacionCliente = new JButton("MODIFICACION");
        botonBuscarCliente = new JButton("BUSCAR");
        colorPorDefectoBotones = botonBuscarCliente.getBackground();
        
        //dar tamaño a mis componentes y alineacion
        labelIdCliente.setPreferredSize(new Dimension(150, 25));
        cajaIdCliente.setPreferredSize(new Dimension(150, 25));
        labelNombreCliente.setPreferredSize(new Dimension(150, 25));
        cajaNombreCliente.setPreferredSize(new Dimension(150, 25));
        labelTelefonoCliente.setPreferredSize(new Dimension(150, 25));
        cajaTelefonoCliente.setPreferredSize(new Dimension(150, 25));
        labelDniCliente.setPreferredSize(new Dimension(150, 25));
        cajaDniCliente.setPreferredSize(new Dimension(150, 25));
        labelEdadCliente.setPreferredSize(new Dimension(300, 25));
        labelEdadCliente.setHorizontalAlignment(0);
        barraEdadCliente.setPreferredSize(new Dimension(300, 50));
        labelIdTarifaCliente.setPreferredSize(new Dimension(150, 25));
        cajaIdTarifaCliente.setPreferredSize(new Dimension(150, 25));
        lblSexo.setPreferredSize(new Dimension(150, 25));
        BtnHombre.setPreferredSize(new Dimension(75, 25));
        BtnMujer.setPreferredSize(new Dimension(75, 25));
        botonAltaCliente.setPreferredSize(new Dimension(150, 25));
        botonBajaCliente.setPreferredSize(new Dimension(150, 25));
        botonModificacionCliente.setPreferredSize(new Dimension(150, 25));
        botonBuscarCliente.setPreferredSize(new Dimension(150, 25));
        this.setPreferredSize(new Dimension(400, 350));
        
        //asignar escuchadores a los botones
        botonAltaCliente.addActionListener(new onClick(ventanaPrincipal));
        botonAltaCliente.setActionCommand("botonAltaCliente");
        botonBajaCliente.addActionListener(new onClick(ventanaPrincipal));
        botonBajaCliente.setActionCommand("botonBajaCliente");
        botonModificacionCliente.addActionListener(new onClick(ventanaPrincipal));
        botonModificacionCliente.setActionCommand("botonModificacionCliente");
        botonBuscarCliente.addActionListener(new onClick(ventanaPrincipal));
        botonBuscarCliente.setActionCommand("botonBuscarCliente");
        
        //cambiar de colores para que aparezca segun empieza la ventana.
        botonBajaCliente.setBackground(Color.RED);
        cajaIdCliente.setBackground(Color.LIGHT_GRAY);
        cajaNombreCliente.setBackground(Color.LIGHT_GRAY);
        cajaTelefonoCliente.setBackground(Color.LIGHT_GRAY);
        cajaDniCliente.setBackground(Color.LIGHT_GRAY);
        cajaIdTarifaCliente.setBackground(Color.LIGHT_GRAY);
        
        cajaIdCliente.setForeground(Color.BLACK);
        cajaNombreCliente.setForeground(Color.BLACK);
        cajaTelefonoCliente.setForeground(Color.BLACK);
        cajaDniCliente.setForeground(Color.BLACK);
        cajaIdTarifaCliente.setForeground(Color.BLACK);
        
        
        cajaIdCliente.setText(sacarUltimoId() + "");//poner el id de la caja de texto
        cajaIdCliente.setEditable(false);
        cajaIdCliente.setForeground(Color.BLACK);
        //añadir todos mis componentes
        this.setBorder(bordeFormularioCliente);
        this.add(labelIdCliente);
        this.add(cajaIdCliente);
        this.add(labelNombreCliente);
        this.add(cajaNombreCliente);
        this.add(labelTelefonoCliente);
        this.add(cajaTelefonoCliente);
        this.add(labelDniCliente);
        this.add(cajaDniCliente);
        this.add(labelEdadCliente);
        this.add(barraEdadCliente);
        this.add(labelIdTarifaCliente);
        this.add(cajaIdTarifaCliente);
        this.add(lblSexo);
        this.add(BtnHombre);
        this.add(BtnMujer);
        this.add(botonAltaCliente);
    }
    
    public void añadirBordeAlta() {
    	this.setBorder(bordeFormularioCliente);
    }
    
    public void añadirBordeBaja() {
    	this.setBorder(bordeFormularioCliente2);
    }
    
    public void añadirBordeModificacion() {
    	this.setBorder(bordeFormularioCliente3);
    }
    
    public boolean validarFormulario() {
    	
    	if(!validarNombre() || !validarTelefono() || !validarDni() || !validarIdTarifaCliente() || !validarSexo()) {
    		return false;
    	}
    	
    	return true;
    }
    
    public boolean validarNombre() {
    	boolean validado = true;
    	String nombre = cajaNombreCliente.getText().toLowerCase();
    	
    	if(nombre.isEmpty() || nombre.isBlank()) {
    		JOptionPane.showMessageDialog(null, "el nombre no puede estar vacio","error",JOptionPane.ERROR_MESSAGE);
    		validado = false;
    	}
    	for (int i = 0; i < nombre.length() && validado == true; i++) {
			if(nombre.charAt(i) < 'a' || nombre.charAt(i) > 'z') {
				validado = false;
			}
		}
    	if(validado == false) {
    		JOptionPane.showMessageDialog(null, "el nombre solo puede contener letras","error",JOptionPane.ERROR_MESSAGE);
    	}
		return validado;
    }
    
    public boolean validarTelefono() {
    	boolean validado = true;
    	String telefono = cajaTelefonoCliente.getText();
    	
    	if(telefono.isEmpty() || telefono.isBlank()) {
    		JOptionPane.showMessageDialog(null, "el telefono no puede estar vacio","error",JOptionPane.ERROR_MESSAGE);
    		validado = false;
    	}
    	
    	if(telefono.length() != 9) {
    		JOptionPane.showMessageDialog(null, "el telefono debe contener 9 carácteres","error",JOptionPane.ERROR_MESSAGE);
    		validado = false;
    	}
    	
    	for (int i = 0; i < telefono.length() && validado == true; i++) {
			if(telefono.charAt(i) < '0' || telefono.charAt(i) > '9') {
				validado = false;
			}
		}
    	
    	if(validado == false) {
    		JOptionPane.showMessageDialog(null, "el telefono solo puede contener carácteres numéricos","error",JOptionPane.ERROR_MESSAGE);
    	}
		return validado;
    }
    
    public boolean validarDni() {
    	boolean validado = true;
    	String dni = cajaDniCliente.getText().toUpperCase();
    	
    	if(dni.isEmpty() || dni.isBlank()) {
    		JOptionPane.showMessageDialog(null, "el dni no puede estar vacio","error",JOptionPane.ERROR_MESSAGE);
    		validado = false;
    	}
    	
    	if(dni.length() != 9) {
    		JOptionPane.showMessageDialog(null, "el dni debe contener 9 carácteres","error",JOptionPane.ERROR_MESSAGE);
    		validado = false;
    	}
    	
    	int ultimoCaracter = dni.length() - 1; // Cambiar para obtener el índice correcto
        for (int i = 0; i < dni.length() - 1; i++) {  // Solo validar los primeros 8 caracteres
            if(dni.charAt(i) < '0' || dni.charAt(i) > '9') {
                validado = false;
                break;
            }
        }

        if (validado && (dni.charAt(ultimoCaracter) < 'A' || dni.charAt(ultimoCaracter) > 'Z')) {//validar el ultimo caracter(la letra).
            validado = false;
            JOptionPane.showMessageDialog(null, "El último carácter debe ser una letra", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!validado) {
            JOptionPane.showMessageDialog(null, "El DNI solo puede contener caracteres numéricos, excepto el último carácter que debe ser una letra", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
		return validado;
    }
    
    public boolean validarIdTarifaCliente() {//validar si esta entre los rangos, de los idTarifas que existen en la base de datos en la tabla tarifa
        boolean validado = true;
        String idTarifa = cajaIdTarifaCliente.getText();
        
        int cantidadDeIds = obtenerCantidadDeIdsDesdeBaseDeDatos();
        if(cantidadDeIds == 0) {
			JOptionPane.showMessageDialog(null, "no existe ningun id tarifa en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			validado = false;
        }
        try {
            int idTarifaInt = Integer.parseInt(idTarifa);
            
            if (idTarifaInt <= 0 || idTarifaInt > cantidadDeIds) {
            	JOptionPane.showMessageDialog(null, "El id de tarifa está fuera de los límites permitidos. Debe estar entre 1 y " + cantidadDeIds, "Error", JOptionPane.ERROR_MESSAGE);
                validado = false;
            }
        } catch (NumberFormatException e) {
        	 JOptionPane.showMessageDialog(null, "El ID de tarifa debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            validado = false;
        }

        return validado;
    }
    
    public boolean validarSexo() {
    	 boolean validado = true;
    	 if(!BtnHombre.isSelected() && !BtnMujer.isSelected()) {
    		 JOptionPane.showMessageDialog(null, "El sexo debes rellenarlo", "Error", JOptionPane.ERROR_MESSAGE);
    		 validado = false;
    	 }
    	 return validado;
    }
    
    public void rellenarTextoVentanaBaja() {//autocompleta las cajas de texto si existe el id al darle al boton de buscar.
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int id = 0;
    	String idTexto = cajaIdCliente.getText();
 	    if (!idTexto.isEmpty()) {
 	        try {
 	            id = Integer.parseInt(idTexto);
 	        } catch (NumberFormatException e) {
 	        	JOptionPane.showMessageDialog(null, "El texto en la caja de ID no es un número válido", "error", JOptionPane.ERROR_MESSAGE);
 	        }
 	    } else {
 	    	JOptionPane.showMessageDialog(null, "La caja de ID está vacía en la pestaña Baja Cliente", "error", JOptionPane.ERROR_MESSAGE);
 	    }
    	
 	    ResultSet rs = null;
		try {
			rs = bd.ejecutarSQL1("SELECT * from cliente where id = '"+id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    try {
			if(rs.next()) {
				try {//CAMBIAR LAS CAJAS DE TEXTO A LOS VALORES DE LA BASE DE DATOS
					cajaNombreCliente.setText(rs.getString(2));
					cajaTelefonoCliente.setText(rs.getInt(3) + "");
					cajaDniCliente.setText(rs.getString(4));
					barraEdadCliente.setValue(rs.getInt(5));
					cajaIdTarifaCliente.setText(rs.getInt(6) + "");
					if(rs.getInt(7) == 1) {//el sexo es hombre.
						BtnHombre.setSelected(true);
					}else {//el sexo es mujer.
						BtnMujer.setSelected(true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(null, "No se ha encontrado ese id en la base de datos", "error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void rellenarTextoVentanaModificacion() {//autocompleta las cajas de texto si existe el id al darle al boton de buscar.
    	BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	int id = 0;
    	String idTexto = cajaIdCliente.getText();
 	    if (!idTexto.isEmpty()) {
 	        try {
 	            id = Integer.parseInt(idTexto);
 	        } catch (NumberFormatException e) {
 	        	JOptionPane.showMessageDialog(null, "El texto en la caja de ID no es un número válido", "error", JOptionPane.ERROR_MESSAGE);
 	        }
 	    } else {
 	    	JOptionPane.showMessageDialog(null, "La caja de ID está vacía en la pestaña Modificacion Cliente", "error", JOptionPane.ERROR_MESSAGE);
 	    }
    	
 	    ResultSet rs = null;
		try {
			rs = bd.ejecutarSQL1("SELECT * from cliente where id = '"+id+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    try {
			if(rs.next()) {
				try {//CAMBIAR LAS CAJAS DE TEXTO A LOS VALORES DE LA BASE DE DATOS
					cajaNombreCliente.setText(rs.getString(2));
					cajaTelefonoCliente.setText(rs.getInt(3) + "");
					cajaDniCliente.setText(rs.getString(4));
					barraEdadCliente.setValue(rs.getInt(5));
					cajaIdTarifaCliente.setText(rs.getInt(6) + "");
					if(rs.getInt(7) == 1) {//el sexo es hombre.
						BtnHombre.setSelected(true);
					}else {//el sexo es mujer.
						BtnMujer.setSelected(true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(null, "No se ha encontrado ese id en la base de datos", "error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private int obtenerCantidadDeIdsDesdeBaseDeDatos() {//en este metodo obtengo la cantidad de ids de tarifa que hay en la base de datos
        int cantidadDeIds = 0;
        
        BaseDatos bd = null;
		try {
			bd = new BaseDatos();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bd.conexionBD();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			bd.crearStm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ResultSet rs = null;
		try {
			rs = bd.ejecutarSQL1("SELECT count(id) from tarifa");
			if(rs.next()) {
				cantidadDeIds = rs.getInt(1);
			}else {
				cantidadDeIds = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			bd.cerrarConex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return cantidadDeIds;
    }

    
    public int sacarUltimoId() throws SQLException, ClassNotFoundException {//busco el ultimo id, para actualizar la caja de texto
    	int id = 0;
    	BaseDatos bd = new BaseDatos();
		bd.conexionBD();
		bd.crearStm();
		ResultSet rs = bd.ejecutarSQL1("SELECT max(id) FROM cliente");
		if(rs.next()) {
			id = rs.getInt(1) + 1;
		}else {
			id = 1;
		}
		bd.cerrarConex();
    	return id;
    }
    
    public void cambiarVentanaModificacion() {//cuando cambias a modificacion
    	cajaIdCliente.setEditable(true);
    	cajaNombreCliente.setEditable(false);
        cajaTelefonoCliente.setEditable(false);
        cajaDniCliente.setEditable(false);
        barraEdadCliente.setEnabled(false);
        cajaIdTarifaCliente.setEditable(false);
        BtnHombre.setEnabled(false);
        BtnMujer.setEnabled(false);
        grupoBtns.clearSelection();
        botonModificacionCliente.setEnabled(false);
        
        cajaIdCliente.setForeground(Color.BLACK);
        cajaNombreCliente.setForeground(Color.BLACK);
        cajaTelefonoCliente.setForeground(Color.BLACK);
        cajaDniCliente.setForeground(Color.BLACK);
        cajaIdTarifaCliente.setForeground(Color.BLACK);
        botonBajaCliente.setForeground(Color.BLACK);
        
        cajaNombreCliente.setBackground(Color.RED);
        cajaTelefonoCliente.setBackground(Color.RED);
        cajaDniCliente.setBackground(Color.RED);
        cajaIdTarifaCliente.setBackground(Color.RED);
        botonAltaCliente.setBackground(Color.RED);
        resetearCajasYBarra();
        this.remove(botonBuscarCliente);
        this.add(botonBuscarCliente);
        this.add(botonModificacionCliente);
        this.remove(botonBajaCliente);
        this.remove(botonAltaCliente);
    }

    public void cambiarVentanaBaja() {//cuando cambias a baja
      	cajaIdCliente.setEditable(true);
    	cajaNombreCliente.setEditable(false);
        cajaTelefonoCliente.setEditable(false);
        cajaDniCliente.setEditable(false);
        barraEdadCliente.setEnabled(false);
        BtnHombre.setEnabled(false);
        BtnMujer.setEnabled(false);
        cajaIdTarifaCliente.setEditable(false);
        botonBajaCliente.setEnabled(false);
        cajaNombreCliente.setBackground(Color.RED);
        cajaTelefonoCliente.setBackground(Color.RED);
        cajaDniCliente.setBackground(Color.RED);
        cajaIdTarifaCliente.setBackground(Color.RED);
        grupoBtns.clearSelection();
        botonBajaCliente.setBackground(Color.RED);
        
        cajaIdCliente.setForeground(Color.BLACK);
        cajaNombreCliente.setForeground(Color.BLACK);
        cajaTelefonoCliente.setForeground(Color.BLACK);
        cajaDniCliente.setForeground(Color.BLACK);
        cajaIdTarifaCliente.setForeground(Color.BLACK);
        botonBajaCliente.setForeground(Color.BLACK);
        
        cajaIdCliente.setText("");
        this.remove(botonBuscarCliente);
        this.add(botonBuscarCliente);
        this.add(botonBajaCliente);
        this.remove(botonAltaCliente);
        this.remove(botonModificacionCliente);
    }
    
    public void cambiarVentanaAlta() {//cuando cambias a alta
     	cajaIdCliente.setEditable(true);
    	cajaNombreCliente.setEditable(true);
        cajaTelefonoCliente.setEditable(true);
        cajaDniCliente.setEditable(true);
        barraEdadCliente.setEnabled(true);
        cajaIdTarifaCliente.setEditable(true);
        grupoBtns.clearSelection();
        
        cajaIdCliente.setForeground(Color.BLACK);
     	cajaNombreCliente.setForeground(Color.BLACK);
        cajaTelefonoCliente.setForeground(Color.BLACK);
        cajaDniCliente.setForeground(Color.BLACK);
        cajaIdTarifaCliente.setForeground(Color.BLACK);
        botonAltaCliente.setForeground(Color.BLACK);
        
    	try {
			cajaIdCliente.setText(sacarUltimoId() + "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	 this.remove(botonBuscarCliente);
         this.add(botonAltaCliente);
         this.remove(botonBajaCliente);
         this.remove(botonModificacionCliente);
    }
    
    public void cambiarColoresBaja() {//si existe un id en la base de datos(al darle al boton buscar en baja).
    	cajaIdCliente.setBackground(Color.GREEN);
       	cajaNombreCliente.setBackground(Color.GREEN);
     	cajaTelefonoCliente.setBackground(Color.GREEN);
     	cajaDniCliente.setBackground(Color.GREEN);
     	cajaIdTarifaCliente.setBackground(Color.GREEN);
     	botonBajaCliente.setBackground(Color.GREEN);
     	botonBuscarCliente.setBackground(Color.RED);
    }
    
    public void cambiarColoresModificacion() {//si existe un id en la base de datos(al darle al boton buscar en modificacion).
    	cajaIdCliente.setBackground(Color.GREEN);
       	cajaNombreCliente.setBackground(Color.GREEN);
     	cajaTelefonoCliente.setBackground(Color.GREEN);
     	cajaDniCliente.setBackground(Color.GREEN);
     	cajaIdTarifaCliente.setBackground(Color.GREEN);
     	botonModificacionCliente.setBackground(Color.GREEN);
     	botonBuscarCliente.setBackground(Color.RED);
    }
    
    public void activarComponentesModificacion() {
       	cajaNombreCliente.setEditable(true);
     	cajaTelefonoCliente.setEditable(true);
     	cajaDniCliente.setEditable(true);
     	barraEdadCliente.setEnabled(true);
     	cajaIdTarifaCliente.setEditable(true);
    }
    
    public void resetearVentanaAlta() {//una vez ya se haya dado de alta
        resetearCajasYBarra();
        try {
			cajaIdCliente.setText(sacarUltimoId() + "");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetearVentanaBaja() {//una vez ya se haya dado de baja
    	cajaIdCliente.setEditable(true);
    	cajaNombreCliente.setEditable(false);
        cajaTelefonoCliente.setEditable(false);
        cajaDniCliente.setEditable(false);
        barraEdadCliente.setEnabled(false);
        cajaIdTarifaCliente.setEditable(false);
        cajaIdCliente.setBackground(Color.LIGHT_GRAY);
        cajaNombreCliente.setBackground(Color.RED);
        cajaTelefonoCliente.setBackground(Color.RED);
        cajaDniCliente.setBackground(Color.RED);
        cajaIdTarifaCliente.setBackground(Color.RED);
        botonBajaCliente.setBackground(Color.RED);
        resetearCajasYBarra();
        botonBajaCliente.setEnabled(false);
        botonBajaCliente.setBackground(Color.RED);
        botonBuscarCliente.setEnabled(true);
        botonBuscarCliente.setBackground(colorPorDefectoBotones);
    }
    
    public void resetearVentanaModificacion() {//una vez ya se haya modificado
    	cajaIdCliente.setEditable(true);
    	cajaNombreCliente.setEditable(false);
        cajaTelefonoCliente.setEditable(false);
        cajaDniCliente.setEditable(false);
        barraEdadCliente.setEnabled(false);
        cajaIdTarifaCliente.setEditable(false);
        cajaIdCliente.setBackground(Color.LIGHT_GRAY);
        cajaNombreCliente.setBackground(Color.RED);
        cajaTelefonoCliente.setBackground(Color.RED);
        cajaDniCliente.setBackground(Color.RED);
        cajaIdTarifaCliente.setBackground(Color.RED);
        botonModificacionCliente.setBackground(Color.RED);
        resetearCajasYBarra();
        botonBajaCliente.setEnabled(false);
        botonBajaCliente.setBackground(Color.RED);
        botonBuscarCliente.setEnabled(true);
        botonBuscarCliente.setBackground(colorPorDefectoBotones);
    }
    
    public void resetearCajasYBarra() {//vaciar las cajas y dejar la barra por defecto
    	 cajaIdCliente.setText("");
         cajaNombreCliente.setText("");
         cajaTelefonoCliente.setText("");
         cajaDniCliente.setText("");
         barraEdadCliente.setValue(45);//porque es asi por defecto
         grupoBtns.clearSelection();
         cajaIdTarifaCliente.setText("");
    }
   
    public JLabel getLblSexo() {
		return lblSexo;
	}

	public void setLblSexo(JLabel lblSexo) {
		this.lblSexo = lblSexo;
	}

	public JRadioButton getBtnHombre() {
		return BtnHombre;
	}

	public void setBtnHombre(JRadioButton btnHombre) {
		BtnHombre = btnHombre;
	}

	public JRadioButton getBtnMujer() {
		return BtnMujer;
	}

	public void setBtnMujer(JRadioButton btnMujer) {
		BtnMujer = btnMujer;
	}

	public ButtonGroup getGrupoBtns() {
		return grupoBtns;
	}

	public void setGrupoBtns(ButtonGroup grupoBtns) {
		this.grupoBtns = grupoBtns;
	}

	public Border getBordeFormularioCliente2() {
		return bordeFormularioCliente2;
	}

	public void setBordeFormularioCliente2(Border bordeFormularioCliente2) {
		this.bordeFormularioCliente2 = bordeFormularioCliente2;
	}

	public Border getBordeFormularioCliente3() {
		return bordeFormularioCliente3;
	}

	public void setBordeFormularioCliente3(Border bordeFormularioCliente3) {
		this.bordeFormularioCliente3 = bordeFormularioCliente3;
	}

	public JButton getBotonModificacionCliente() {
		return botonModificacionCliente;
	}

	public void setBotonModificacionCliente(JButton botonModificacionCliente) {
		this.botonModificacionCliente = botonModificacionCliente;
	}

	public Color getColorPorDefectoBotones() {
		return colorPorDefectoBotones;
	}

	public void setColorPorDefectoBotones(Color colorPorDefectoBotones) {
		this.colorPorDefectoBotones = colorPorDefectoBotones;
	}

	public JButton getBotonBajaCliente() {
		return botonBajaCliente;
	}

	public void setBotonBajaCliente(JButton botonBajaCliente) {
		this.botonBajaCliente = botonBajaCliente;
	}

	public JButton getBotonBuscarCliente() {
		return botonBuscarCliente;
	}


	public void setBotonBuscarCliente(JButton botonBuscarCliente) {
		this.botonBuscarCliente = botonBuscarCliente;
	}


	public Border getBordeFormularioCliente() {
        return this.bordeFormularioCliente;
    }

    public void setBordeFormularioCliente(Border bordeFormularioCliente) {
        this.bordeFormularioCliente = bordeFormularioCliente;
    }

    public JLabel getLabelIdCliente() {
        return this.labelIdCliente;
    }

    public void setLabelIdCliente(JLabel labelIdCliente) {
        this.labelIdCliente = labelIdCliente;
    }

    public JTextField getCajaIdCliente() {
        return this.cajaIdCliente;
    }

    public void setCajaIdCliente(JTextField cajaIdCliente) {
        this.cajaIdCliente = cajaIdCliente;
    }

    public JLabel getLabelNombreCliente() {
        return this.labelNombreCliente;
    }

    public void setLabelNombreCliente(JLabel labelNombreCliente) {
        this.labelNombreCliente = labelNombreCliente;
    }

    public JTextField getCajaNombreCliente() {
        return this.cajaNombreCliente;
    }

    public void setCajaNombreCliente(JTextField cajaNombreCliente) {
        this.cajaNombreCliente = cajaNombreCliente;
    }

    public JLabel getLabelTelefonoCliente() {
        return this.labelTelefonoCliente;
    }

    public void setLabelTelefonoCliente(JLabel labelTelefonoCliente) {
        this.labelTelefonoCliente = labelTelefonoCliente;
    }

    public JTextField getCajaTelefonoCliente() {
        return this.cajaTelefonoCliente;
    }

    public void setCajaTelefonoCliente(JTextField cajaTelefonoCliente) {
        this.cajaTelefonoCliente = cajaTelefonoCliente;
    }

    public JLabel getLabelDniCliente() {
        return this.labelDniCliente;
    }

    public void setLabelDniCliente(JLabel labelDniCliente) {
        this.labelDniCliente = labelDniCliente;
    }

    public JTextField getCajaDniCliente() {
        return this.cajaDniCliente;
    }

    public void setCajaDniCliente(JTextField cajaDniCliente) {
        this.cajaDniCliente = cajaDniCliente;
    }

    public JLabel getLabelEdadCliente() {
        return this.labelEdadCliente;
    }

    public void setLabelEdadCliente(JLabel labelEdadCliente) {
        this.labelEdadCliente = labelEdadCliente;
    }

    public JSlider getBarraEdadCliente() {
        return this.barraEdadCliente;
    }

    public void setBarraEdadCliente(JSlider barraIdEdadCliente) {
        this.barraEdadCliente = barraIdEdadCliente;
    }

    public JLabel getLabelIdTarifaCliente() {
        return this.labelIdTarifaCliente;
    }

    public void setLabelIdTarifaCliente(JLabel labelIdTarifaCliente) {
        this.labelIdTarifaCliente = labelIdTarifaCliente;
    }

    public JTextField getCajaIdTarifaCliente() {
        return this.cajaIdTarifaCliente;
    }

    public void setCajaIdTarifaCliente(JTextField cajaIdTarifaCliente) {
        this.cajaIdTarifaCliente = cajaIdTarifaCliente;
    }

    public JButton getBotonAltaCliente() {
        return this.botonAltaCliente;
    }

    public void setBotonAltaCliente(JButton botonAltaCliente) {
        this.botonAltaCliente = botonAltaCliente;
    }
}