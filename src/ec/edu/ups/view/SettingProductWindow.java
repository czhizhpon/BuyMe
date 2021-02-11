/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.view;

import ec.edu.ups.controller.TreeController;
import ec.edu.ups.model.Product;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Clase para ver productos y ajustes
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class SettingProductWindow extends JFrame implements ActionListener{

    private TreeController control;
    private JTextField txtId;
    private JTextField txtName;
    private JTextArea txtADescription;
    private JButton update;
    private String id;
    public SettingProductWindow (TreeController control) {
       this.control = control;
       iniComponents();
    }
    /**
     * inicia componentes
     */
    public void iniComponents(){
        setSize(500,300);
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel id = new JLabel("Id:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 0;
        mainPanel.add(id, gbc);
        
        this.txtId = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 0;
        mainPanel.add(this.txtId, gbc);
            
        JLabel name = new JLabel("Nombre:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 1;
        mainPanel.add(name, gbc);
        
        this.txtName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
        mainPanel.add(this.txtName, gbc);
            
        JLabel desc = new JLabel("Descripción:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 2;
        mainPanel.add(desc, gbc);
        
        this.txtADescription = new JTextArea();
        txtADescription.setColumns(50);//Maximo de columnas
                txtADescription.setLineWrap(true);//Salto Automatico
        JScrollPane scrProduct = new JScrollPane(txtADescription);
        scrProduct.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrProduct, gbc);
        
        
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        gbc =new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton create = new JButton("Crear");
        create.addActionListener(this);
        create.setActionCommand("create");
        
            gbc.gridx = 0;
            gbc.gridy = 0;
        buttonPanel.add(create, gbc);
        
        JButton read = new JButton("Buscar");
        read.addActionListener(this);
        read.setActionCommand("read");
        
            gbc.gridx = 1;
            gbc.gridy = 0;
        buttonPanel.add(read, gbc);
        
        update = new JButton("Editar");
        update.addActionListener(this);
        update.setActionCommand("update");
        
            gbc.gridx = 2;
            gbc.gridy = 0;
        buttonPanel.add(update, gbc);
        
        JButton delete = new JButton("Eliminar");
        delete.addActionListener(this);
        delete.setActionCommand("delete");
        
            gbc.gridx = 3;
            gbc.gridy = 0;
        buttonPanel.add(delete, gbc);
        
        
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        switch(e.getActionCommand()){
            case "create":
                createProduct();
                break;
                
            case "read":
                readProduct();
                break;
                
            case "update":
                if (readProduct()) {
                    
                    searchProductToUpdate();
                }
                break;
                
            case "delete":
                deleteProduct();
                break;
                
            case "updateProduct":
                updateProduct();
                break;
                
        }
    }
    /**
     * envia datos para crear producto
     */
    public void createProduct() {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() 
                || txtADescription.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios.");
            return;
        }
        
        if (control.validProduct(txtId.getText())) {
            JOptionPane.showMessageDialog(null, "El producto ya existe.");
            return;
        }
        
        control.createProduct(txtId.getText(), txtName.getText(), 
                txtADescription.getText());
        JOptionPane.showMessageDialog(null, "Se creó correctamente.");
        
    }
    /**
     * lee datos
     * @return boolean
     */
    public boolean readProduct() {
        Product product = control.getProducts().search(txtId.getText());
        try{
        txtId.setText(product.getId());
        this.id = product.getId();
        txtName.setText(product.getName());
        txtADescription.setText(product.getDescription());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el producto.");
            return false;
        }
        return true;
    }
    /**
     * actualiza estado de botones
     */
    public void searchProductToUpdate() {
        
        update.setText("Aceptar");
        update.setActionCommand("updateProduct");
        this.txtId.setEditable(false);
        
    }
    /**
     * envia datos para editar producto
     */
    public void updateProduct(){
        try{
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty() 
                    || txtADescription.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campos vacios.");
                return;
            }

            control.getProducts().update(this.id, txtName.getText(), 
                    txtADescription.getText());
            JOptionPane.showMessageDialog(null, "Se actualizó correctamente.");

            update.setText("Editar");
            update.setActionCommand("update");
            this.txtId.setEditable(true);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, 
                    "No coincide con el id original.");
        }
    }
    /**
     * envia datos para eliminar producto
     */
    public void deleteProduct() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un id válido");
            return;
        }
        
        if (!control.getProducts().delete(txtId.getText())) {
            JOptionPane.showMessageDialog(null, "No se encontró el producto");
        }else{
            JOptionPane.showMessageDialog(null, "Se eliminó el producto");
        }

    }
    
}
