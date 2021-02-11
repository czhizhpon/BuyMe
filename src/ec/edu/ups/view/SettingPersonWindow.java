/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.view;

import ec.edu.ups.controller.TreeController;
import ec.edu.ups.model.Person;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase para registro y ajustes
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class SettingPersonWindow extends JFrame implements ActionListener{

    private TreeController control;
    private JTextField txtUsername;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtLastName;
    private JTextField txtAge;
    private JTextField txtPassword;
    private JTextField txtComfirmPassword;
    
    private JButton update;
    private String username;

    public SettingPersonWindow(TreeController control) {
        this.control = control;
        initComponents();
        
    }
    /**
     * inicia componentes
     */
    public void initComponents(){
        setSize(500,400);
        setTitle("Personas");
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
        //
        JLabel last = new JLabel("Apellido:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 2;
        mainPanel.add(last, gbc);
        
        this.txtLastName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 2;
        mainPanel.add(this.txtLastName, gbc);
        //
        
        //
        JLabel age = new JLabel("Edad:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 3;
        mainPanel.add(age, gbc);
        
        this.txtAge = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 3;
        mainPanel.add(this.txtAge, gbc);
        //
        
        //
        JLabel username = new JLabel("Nombre de Usuario:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 4;
        mainPanel.add(username, gbc);
        
        this.txtUsername = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 4;
        mainPanel.add(this.txtUsername, gbc);
        //
        
        //
        JLabel pass = new JLabel("Contraseña:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 5;
        mainPanel.add(pass, gbc);
        
        this.txtPassword = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 5;
        mainPanel.add(this.txtPassword, gbc);
        //
        
        //
        JLabel cpass = new JLabel("Confirmar contraseña:" , 11);
            gbc.gridx = 0;
            gbc.gridy = 6;
        mainPanel.add(cpass, gbc);
        
        this.txtComfirmPassword = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 6;
        mainPanel.add(this.txtComfirmPassword, gbc);
        //
        
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
                createPerson();
                break;
                
            case "read":
                readPerson();
                break;
                
            case "update":
                if (readPerson()) {
                    
                    searchPersonToUpdate();
                }
                break;
                
            case "delete":
                deletePerson();
                break;
                
            case "updatePerson":
                updatePerson();
                break;
                
        }
    }
    /**
     * envia datos para crear persona
     */
    private void createPerson() {
        if (txtUsername.getText().isEmpty()
                ||(txtName.getText().isEmpty()) 
                || (txtLastName.getText().isEmpty()) 
                || (txtId.getText().isEmpty()) 
                || (txtPassword.getText().isEmpty()) 
                || (txtComfirmPassword.getText().isEmpty()) 
                || (txtAge.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Campos vacios.");
            return;
        }
        
        if (!control.validCreatePaswword(txtPassword.getText(), 
                txtComfirmPassword.getText())) {
            JOptionPane.showMessageDialog(null, "La contraseña no coincide.");
            return;
        }
        
        if (!control.validId(txtId.getText())) {
            JOptionPane.showMessageDialog(null, "La cédula es incorrecta.");
            return;
        }
        
        try {
                control.createPerson(txtPassword.getText(), txtUsername.getText(), 
                txtId.getText(), txtName.getText(), txtLastName.getText(), 
                Integer.parseInt(txtAge.getText()), null);
                JOptionPane.showMessageDialog(null, "Se agregó correctamente.");
                emptyFields();
        } catch (Exception e) {
            
        }
    }
    /**
     * lee datos de perosna
     * @return boolean
     */
    private boolean readPerson() {
        Person person = control.getPersons().search(txtUsername.getText());
        try {
            txtId.setText(person.getId());
            txtName.setText(person.getName());
            txtLastName.setText(person.getLastname());
            txtAge.setText("" + person.getAge());
            txtUsername.setText(person.getUsername());
            this.username = person.getUsername();
            txtPassword.setText("");
            txtComfirmPassword.setText("");
            
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el usuario."
                    + "\n Asegurece de colocar el nombre de usuario correcto");
            return false;
        }
        return true;
    }
    /**
     * verificar busqueda para actualizar
     */
    private void searchPersonToUpdate() {
        update.setText("Aceptar");
        update.setActionCommand("updatePerson");
        this.txtUsername.setEditable(false);
        this.txtId.setEditable(false);
    }
    /**
     * envia datos para eliminar persona
     */
    private void deletePerson() {
        try{
            if (txtUsername.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario");
                return;
            }
            
            if (control.getPersons().delete(txtUsername.getText())) {
                JOptionPane.showMessageDialog(null, "Se eliminó el usuario");
                emptyFields();
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "No encontró el usuario.");
        }
    }
    /**
     * envia datos para actualizar datos de persona
     */
    private void updatePerson() {
        
        if (txtUsername.getText().isEmpty()
                ||(txtName.getText().isEmpty()) 
                || (txtLastName.getText().isEmpty()) 
                || (txtId.getText().isEmpty()) 
                || (txtPassword.getText().isEmpty()) 
                || (txtComfirmPassword.getText().isEmpty()) 
                || (txtAge.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Campos vacios.");
            return;
        }
        
        
        if (!control.validCreatePaswword(txtPassword.getText(), 
                txtComfirmPassword.getText())) {
            JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden.");
            return;
        }
        try{
            Person person = control.getPersons().search(username);

            control.updatePerson(this.username, txtPassword.getText(), 
                    Integer.parseInt(txtAge.getText()), txtLastName.getText(), 
                    txtName.getText(), person.getProducts());
            
            JOptionPane.showMessageDialog(null, "Se editó correctamente");
            emptyFields();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error /n"
                    + e.toString());
        }finally{
            this.txtUsername.setEditable(true);
            this.txtId.setEditable(true);
            update.setText("Editar");
            update.setActionCommand("update");
        }
    }
    /**
     * verifica campos vacios
     */
    public void emptyFields(){
        this.txtId.setText("");
        this.txtAge.setText("");
        this.txtLastName.setText("");
        this.txtName.setText("");
        this.txtUsername.setText("");
        this.txtPassword.setText("");
        this.txtComfirmPassword.setText("");
    }
    
}
