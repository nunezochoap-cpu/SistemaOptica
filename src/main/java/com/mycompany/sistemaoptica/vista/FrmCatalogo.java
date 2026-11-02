/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemaoptica.vista;

import com.mycompany.sistemaoptica.dao.impl.AroApartadoDAOImpl;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.mycompany.sistemaoptica.dao.impl.ProductoDAOImpl;
import com.mycompany.sistemaoptica.model.Producto;
import java.util.List;


/**
 *
 * @author Dell
 */
public class FrmCatalogo extends javax.swing.JFrame {
    
    private int clienteID;
    private int idAro1;
    private int idAro2;
    private int idAro3;

    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmCatalogo.class.getName());

    /**
     * Creates new form FrmCatalogo
     */
    public FrmCatalogo(int clienteID) {
        initComponents();
        
         this.clienteID = clienteID;
         cargarFiltros();
        
         btnBuscarActionPerformed(null);
         
        cargarImagen(lblFotoAro1, "aro1.png");
        cargarImagen(lblFotoAro, "aro2.png");
        cargarImagen(lblFotoAro3, "aro3.png");
        
        
        java.net.URL recurso = getClass().getResource(
            "/imagenes/encabezado_catalogo.png"     
        );
          if (recurso != null) {

        javax.swing.ImageIcon imagen =
                new javax.swing.ImageIcon(recurso);

        java.awt.Image imagenEscalada =
                imagen.getImage().getScaledInstance(
                        lblEncabezado.getWidth(),
                        150,
                        java.awt.Image.SCALE_SMOOTH
                );

        lblEncabezado.setIcon(
                new javax.swing.ImageIcon(imagenEscalada)
        );

        lblEncabezado.setText("");

     } else {
        System.out.println("No se encontro el encabezado.");
        }
        
                
    }
    
    private void apartarAro(int productoID) {

    
    AroApartadoDAOImpl dao = new AroApartadoDAOImpl();

    boolean resultado = dao.registrarApartado(clienteID, productoID);

    if (resultado) {
        JOptionPane.showMessageDialog(this,
                "Aro apartado correctamente.");
    } else {
        JOptionPane.showMessageDialog(this,
                "No se pudo apartar el aro.");
    }
}
    
    private void cargarImagen(JLabel etiqueta, String archivo) {

    URL ruta = getClass().getResource("/imagenes/" + archivo);

    if (ruta != null) {

        ImageIcon icono = new ImageIcon(ruta);

        Image imagen = icono.getImage().getScaledInstance(
                200, 130, Image.SCALE_SMOOTH);

        etiqueta.setIcon(new ImageIcon(imagen));
        etiqueta.setText("");

    } else {
        JOptionPane.showMessageDialog(this,
                "No se encontró la imagen: " + archivo);
    }
    
    }
    
   
   private void mostrarAros(List<Producto> lista) {

    // Ocultar las tres tarjetas inicialmente
    jPanel1.setVisible(false);
    jPanel4.setVisible(false);
    jPanel6.setVisible(false);

    if (lista.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "No se encontraron aros con esos filtros.");
        return;
    }

    if (lista.size() > 0) {
        Producto p = lista.get(0);

        idAro1 = p.getProductoID();
        lblNombre.setText(p.getNombre());
        lblMarca.setText("Marca: " + p.getMarca());
        lblMaterial.setText("Material: " + p.getMaterial());
        lblForma.setText("Forma: " + p.getForma());
        lblPrecio.setText("L. " + p.getPrecio());

        jPanel1.setVisible(true);
    }

    if (lista.size() > 1) {
        Producto p = lista.get(1);

        idAro2 = p.getProductoID();
        lblNombre4.setText(p.getNombre());
        lblMarca4.setText("Marca: " + p.getMarca());
        lblMaterial4.setText("Material: " + p.getMaterial());
        lblForma4.setText("Forma: " + p.getForma());
        lblPrecio4.setText("L. " + p.getPrecio());

        jPanel4.setVisible(true);
    }

    if (lista.size() > 2) {
        Producto p = lista.get(2);

        idAro3 = p.getProductoID();
        lblNombre5.setText(p.getNombre());
        lblMarca5.setText("Marca: " + p.getMarca());
        lblMaterial5.setText("Material: " + p.getMaterial());
        lblForma5.setText("Forma: " + p.getForma());
        lblPrecio5.setText("L. " + p.getPrecio());

        jPanel6.setVisible(true);
    }
    
   }
    
    
    private void cargarFiltros() {

    ProductoDAOImpl dao = new ProductoDAOImpl();
    List<Producto> lista = dao.listar();

    cbmMarca.removeAllItems();
    cbmMaterial.removeAllItems();
    cbmForma.removeAllItems();

    cbmMarca.addItem("Todos");
    cbmMaterial.addItem("Todos");
    cbmForma.addItem("Todos");

    for (Producto p : lista) {

        if (p.getTipo().equalsIgnoreCase("ARO") && p.getStock() > 0) {

            String marca = p.getMarca();
            String material = p.getMaterial();
            String forma = p.getForma();

            if (marca != null && !marca.trim().isEmpty()
                    && !contiene(cbmMarca, marca)) {
                cbmMarca.addItem(marca);
            }

            if (material != null && !material.trim().isEmpty()
                    && !contiene(cbmMaterial, material)) {
                cbmMaterial.addItem(material);
            }

            if (forma != null && !forma.trim().isEmpty()
                    && !contiene(cbmForma, forma)) {
                cbmForma.addItem(forma);
            }
        }
    }
    
    }

    
    
    private boolean contiene(javax.swing.JComboBox<String> combo, String valor) {

    for (int i = 0; i < combo.getItemCount(); i++) {
        if (combo.getItemAt(i).equalsIgnoreCase(valor)) {
            return true;
        }
    }

    return false;
  
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCatalogo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnCatalogo = new javax.swing.JButton();
        btnApartado = new javax.swing.JButton();
        btnMediciones = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        lblEncabezado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbmMarca = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbmMaterial = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbmForma = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPrecioMin = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtPrecioMax = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblFotoAro1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblMaterial = new javax.swing.JLabel();
        lblForma = new javax.swing.JLabel();
        btnApartar = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblFotoAro = new javax.swing.JLabel();
        lblNombre4 = new javax.swing.JLabel();
        lblMarca4 = new javax.swing.JLabel();
        lblMaterial4 = new javax.swing.JLabel();
        lblForma4 = new javax.swing.JLabel();
        lblPrecio4 = new javax.swing.JLabel();
        btnApartar4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblFotoAro3 = new javax.swing.JLabel();
        lblForma5 = new javax.swing.JLabel();
        lblMaterial5 = new javax.swing.JLabel();
        lblMarca5 = new javax.swing.JLabel();
        lblNombre5 = new javax.swing.JLabel();
        lblPrecio5 = new javax.swing.JLabel();
        btnApartar5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCatalogo.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));

        btnInicio.setBackground(new java.awt.Color(153, 153, 255));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.addActionListener(this::btnInicioActionPerformed);

        btnCatalogo.setBackground(new java.awt.Color(153, 153, 255));
        btnCatalogo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCatalogo.setText("Catalogo de Aros");

        btnApartado.setBackground(new java.awt.Color(153, 153, 255));
        btnApartado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApartado.setText("Mis Apartados");
        btnApartado.addActionListener(this::btnApartadoActionPerformed);

        btnMediciones.setBackground(new java.awt.Color(153, 153, 255));
        btnMediciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMediciones.setText("Mis Mediciones");
        btnMediciones.addActionListener(this::btnMedicionesActionPerformed);

        btnPerfil.setBackground(new java.awt.Color(153, 153, 255));
        btnPerfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPerfil.setText("Mi Perfil");

        btnCerrarSesion.setBackground(new java.awt.Color(153, 153, 255));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnApartado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMediciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnApartado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnMediciones, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros de Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Marca");

        cbmMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Material");

        cbmMaterial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Forma");

        cbmForma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Precio");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbmMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbmMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cbmForma, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPrecioMin, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecioMax, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbmMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblFotoAro1.setText("jLabel6");

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombre.setText(" Aro Clasico 101");

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMarca.setText("Marca: Ray-Ban");

        lblMaterial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaterial.setText("Material: Acetato");

        lblForma.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblForma.setText("Forma: Rectangular");

        btnApartar.setBackground(new java.awt.Color(102, 153, 255));
        btnApartar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApartar.setText("Apartar");
        btnApartar.addActionListener(this::btnApartarActionPerformed);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrecio.setText("L.1,250.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMarca)
                    .addComponent(lblForma)
                    .addComponent(lblMaterial))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNombre)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblPrecio)
                        .addGap(26, 26, 26)
                        .addComponent(btnApartar, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFotoAro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFotoAro1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaterial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblForma)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnApartar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNombre4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombre4.setText("Aro Transparente");

        lblMarca4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMarca4.setText("Marca: Vogue");

        lblMaterial4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaterial4.setText("Material: Policarbonato");

        lblForma4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblForma4.setText("Forma: Rectangular");

        lblPrecio4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrecio4.setText("L.1,100.00");

        btnApartar4.setBackground(new java.awt.Color(102, 153, 255));
        btnApartar4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApartar4.setText("Apartar");
        btnApartar4.addActionListener(this::btnApartar4ActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFotoAro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre4)
                            .addComponent(lblMarca4)
                            .addComponent(lblMaterial4)
                            .addComponent(lblForma4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblPrecio4)
                        .addGap(34, 34, 34)
                        .addComponent(btnApartar4, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblFotoAro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMarca4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaterial4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblForma4)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecio4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnApartar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblForma5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblForma5.setText("Forma: Aviador");

        lblMaterial5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaterial5.setText("Material: Metal");

        lblMarca5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMarca5.setText("Marca: Calvin Klein");

        lblNombre5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNombre5.setText("Aro Dorado");

        lblPrecio5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPrecio5.setText("L.1,850.00");

        btnApartar5.setBackground(new java.awt.Color(102, 153, 255));
        btnApartar5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApartar5.setText("Apartar");
        btnApartar5.addActionListener(this::btnApartar5ActionPerformed);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFotoAro3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre5)
                            .addComponent(lblMarca5)
                            .addComponent(lblMaterial5)
                            .addComponent(lblForma5))
                        .addGap(81, 81, 81))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblPrecio5)
                        .addGap(34, 34, 34)
                        .addComponent(btnApartar5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblFotoAro3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMarca5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaterial5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblForma5)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecio5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnApartar5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout pnlCatalogoLayout = new javax.swing.GroupLayout(pnlCatalogo);
        pnlCatalogo.setLayout(pnlCatalogoLayout);
        pnlCatalogoLayout.setHorizontalGroup(
            pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCatalogoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(pnlCatalogoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCatalogoLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                                .addComponent(lblEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        pnlCatalogoLayout.setVerticalGroup(
            pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCatalogoLayout.createSequentialGroup()
                        .addComponent(lblEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
         try {
        String marca = cbmMarca.getSelectedItem().toString();
        String material = cbmMaterial.getSelectedItem().toString();
        String forma = cbmForma.getSelectedItem().toString();

        Double precioMin = null;
        Double precioMax = null;

        if (!txtPrecioMin.getText().trim().isEmpty()) {
            precioMin = Double.parseDouble(txtPrecioMin.getText().trim());
        }

        if (!txtPrecioMax.getText().trim().isEmpty()) {
            precioMax = Double.parseDouble(txtPrecioMax.getText().trim());
        }

        if (precioMin != null && precioMax != null && precioMin > precioMax) {
            JOptionPane.showMessageDialog(this,
                    "El precio mínimo no puede ser mayor que el máximo.");
            return;
        }

        ProductoDAOImpl dao = new ProductoDAOImpl();
        List<Producto> lista = dao.buscarAros(
        marca, material, forma, precioMin, precioMax);

        System.out.println("Cantidad de aros encontrados: " + lista.size());

        for (Producto p : lista) {
           System.out.println(
            p.getProductoID() + " - "
            + p.getNombre() + " - L. " + p.getPrecio());
        }

         mostrarAros(lista);
         
         } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Ingrese precios válidos.");
         }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnApartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApartarActionPerformed
        // TODO add your handling code here:
         apartarAro(idAro1);
    }//GEN-LAST:event_btnApartarActionPerformed

    private void btnApartar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApartar4ActionPerformed
        // TODO add your handling code here:
          apartarAro(idAro2);
    }//GEN-LAST:event_btnApartar4ActionPerformed

    private void btnApartar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApartar5ActionPerformed
        // TODO add your handling code here:
          apartarAro(idAro3);
    }//GEN-LAST:event_btnApartar5ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        cbmMarca.setSelectedIndex(0);
        cbmMaterial.setSelectedIndex(0);
        cbmForma.setSelectedIndex(0);

        txtPrecioMin.setText("");
        txtPrecioMax.setText("");

        btnBuscarActionPerformed(evt);
       
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        FrmClientePrincipal menu = new FrmClientePrincipal(clienteID);
    menu.setLocationRelativeTo(null);
    menu.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnApartadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApartadoActionPerformed
        // TODO add your handling code here:
         FrmMisApartados ventana = new FrmMisApartados(clienteID);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    this.dispose();

    }//GEN-LAST:event_btnApartadoActionPerformed

    private void btnMedicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicionesActionPerformed
        // TODO add your handling code here:
        
        FrmMediciones ventana = new FrmMediciones(clienteID);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    this.dispose();
         
    }//GEN-LAST:event_btnMedicionesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
          FrmLogin login = new FrmLogin();
    login.setLocationRelativeTo(null);
    login.setVisible(true);
    this.dispose();

        
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmCatalogo(1).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApartado;
    private javax.swing.JButton btnApartar;
    private javax.swing.JButton btnApartar4;
    private javax.swing.JButton btnApartar5;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCatalogo;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMediciones;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JComboBox<String> cbmForma;
    private javax.swing.JComboBox<String> cbmMarca;
    private javax.swing.JComboBox<String> cbmMaterial;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblForma;
    private javax.swing.JLabel lblForma4;
    private javax.swing.JLabel lblForma5;
    private javax.swing.JLabel lblFotoAro;
    private javax.swing.JLabel lblFotoAro1;
    private javax.swing.JLabel lblFotoAro3;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMarca4;
    private javax.swing.JLabel lblMarca5;
    private javax.swing.JLabel lblMaterial;
    private javax.swing.JLabel lblMaterial4;
    private javax.swing.JLabel lblMaterial5;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre4;
    private javax.swing.JLabel lblNombre5;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecio4;
    private javax.swing.JLabel lblPrecio5;
    private javax.swing.JPanel pnlCatalogo;
    private javax.swing.JTextField txtPrecioMax;
    private javax.swing.JTextField txtPrecioMin;
    // End of variables declaration//GEN-END:variables
}
