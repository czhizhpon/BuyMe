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
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Clase para login de un usuario
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class LoginWindow extends JFrame implements ActionListener{
    
    private JPanel mainPanel;
    //private JPanel welcomePanel;
    private JPanel inputPanel;
    
    private JTextField txtUsername;
    private JTextField txtPassword;
    
    
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtLastName;
    private JTextField txtAge;
//    private JTextField txtUsername;
//    private JTextField txtPassword;
    private JTextField txtComfirmPassword;
    
    private TreeController control;
    
    private MainWindow mainWdw;
    
    public LoginWindow () {
        this.control = new TreeController();
        mainComponent();
        
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                updateData();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });
    }
    /**
     * inicia componentes
     */
    public void mainComponent(){
        
        setSize(1000, 500);
        setTitle("Buy-Me");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel midPanel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font;
        
        gbc.insets = new Insets(10, 5, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel name = new JLabel("Buy-Me");
        font = name.getFont();
        name.setFont(new Font(font.getFontName(), font.getStyle(), 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipady = 100;
            
        buttonPanel.add(name, gbc);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setActionCommand("login");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.ipady = 0;
        buttonPanel.add(loginButton, gbc);
        
        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(this);
        registerButton.setActionCommand("register");
            gbc.gridx = 0;
            gbc.gridy = 2;
        buttonPanel.add(registerButton, gbc);
        
        buttonPanel.setBackground(Color.white);
        
        midPanel.add(buttonPanel, BorderLayout.CENTER);
        
        JPanel productPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        
        JButton productButton = new JButton("Productos");
        productButton.addActionListener(this);
        productButton.setActionCommand("product");
            gbc1.anchor = GridBagConstraints.WEST;
            gbc1.weightx = 1;
            gbc1.fill = GridBagConstraints.NONE;
        productPanel.add(productButton, gbc1);
        
        JButton listButton = new JButton("Listar Personas");
        listButton.addActionListener(this);
        listButton.setActionCommand("list");
            gbc1.anchor = GridBagConstraints.EAST;
            gbc1.weightx = 1;
            gbc1.fill = GridBagConstraints.NONE;
            gbc1.gridx =1;
        productPanel.add(listButton, gbc1);
        
        productPanel.setBackground(Color.white);
        midPanel.add(productPanel, BorderLayout.SOUTH);
        
        midPanel.setBackground(Color.WHITE);
        
        this.mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
            
        
            gbcMain.gridx = 0;
            gbcMain.gridy = 0;
            gbcMain.ipadx =150;
            gbcMain.weighty = 1;
            gbcMain.weightx = 0;
            gbcMain.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(midPanel, gbcMain);
        
        this.inputPanel = new JPanel(new GridBagLayout());
            gbcMain.gridx = 1;
            gbcMain.gridy = 0;
            gbcMain.ipadx = 0;
            gbcMain.weightx = 0;
        mainPanel.add(this.inputPanel, gbcMain);
        mainPanel.setBackground(new Color (12,50,89));
        
        JScrollPane src = new JScrollPane(mainPanel);
        
        
        setLayout(new BorderLayout());
        getContentPane().add(src, BorderLayout.CENTER);
        
        
    }
    /**
     * inica login 
     */
    public void addLoginPanel(){
        this.inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
        JLabel username = new JLabel("Nombre de Usuario:", 11);
            gbc.gridx = 0;
            gbc.gridy = 0;
        this.inputPanel.add(username, gbc);
        
        this.txtUsername = new JTextField(20);
            
            gbc.gridx = 1;
            gbc.gridy = 0;
        this.inputPanel.add(this.txtUsername, gbc);
        
        JLabel password = new JLabel("Contraseña:", 11);
            gbc.gridx = 0;
            gbc.gridy = 1;
        this.inputPanel.add(password, gbc);
        
        this.txtPassword = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 1;
        this.inputPanel.add(this.txtPassword, gbc);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setActionCommand("checkLogin");
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.NONE;
        this.inputPanel.add(loginButton, gbc);
        
        this.inputPanel.updateUI();
        
    }
    /**
     * inicia registro
     */
    public void addRegisterPanel(){
        
        this.inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
        JLabel id = new JLabel("Cédula:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 0;
        this.inputPanel.add(id, gbc);
        
        this.txtId = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 0;
        this.inputPanel.add(this.txtId, gbc);
        
        //Casilla nombre
        JLabel name = new JLabel("Nombre:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 1;
        this.inputPanel.add(name, gbc);
        
        this.txtName = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 1;
        this.inputPanel.add(this.txtName, gbc);
        
        //Casilla apellido
        JLabel lastName = new JLabel("Apellido:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 2;
        this.inputPanel.add(lastName, gbc);
        
        this.txtLastName = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 2;
        this.inputPanel.add(this.txtLastName, gbc);
        
        //Casilla Edad
        JLabel age = new JLabel("Edad:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 3;
        this.inputPanel.add(age, gbc);
        
        this.txtAge = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 3;
        this.inputPanel.add(this.txtAge, gbc);
        
        //Casilla nombre de usuario
        JLabel username = new JLabel("Nombre de Usuario:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 4;
        this.inputPanel.add(username, gbc);
        
        this.txtUsername = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 4;
        this.inputPanel.add(this.txtUsername, gbc);
        
        //Casilla Contrasena
        JLabel password = new JLabel("Contraseña:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 5;
        this.inputPanel.add(password, gbc);
        
        this.txtPassword = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 5;
        this.inputPanel.add(this.txtPassword, gbc);
        
        //Casilla Confirmar contrasena
         JLabel cPassword = new JLabel("Confirmar Contraseña:" ,11);
            gbc.gridx = 0;
            gbc.gridy = 6;
        this.inputPanel.add(cPassword, gbc);
        
        this.txtComfirmPassword = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 6;
        this.inputPanel.add(this.txtComfirmPassword, gbc);
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        registerButton.setActionCommand("checkRegister");
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.NONE;
        this.inputPanel.add(registerButton, gbc);
        
        this.inputPanel.updateUI();
    }
    /**
     * lista las personas
     */
    public void addListPeoplePanel(){
        
        this.inputPanel.removeAll();
        
        
        JList list = new JList();
        DefaultListModel model = new DefaultListModel();
        
        List<Person> people = control.listPerson();
        if (people == null) {
            model.addElement("No hay usuarios registrados");
        }else{
            for (int i = 0; i < people.size(); i++) {
                model.add(i, 
                        "Key: "+people.get(i).getKey()
                        + "  Usuario: " + people.get(i).getUsername()
                        + "     "+ people.get(i).getName() + " "
                        + people.get(i).getLastname()
                
                );
            }
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        list.setModel(model);
        JScrollPane src = new JScrollPane(list);
        this.inputPanel.add(src, gbc);
        this.inputPanel.updateUI();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "login":
                addLoginPanel();
                break;
                
            case "register":
                addRegisterPanel();
                break;
            case "checkLogin":
                login();
                break;
                
            case "checkRegister": 
                register();
                break;
                
            case "product":
                openProductSetting();
                break;
            case "list":
                addListPeoplePanel();
                break;
            default:
        }
    }
    /**
     * permite login
     */
    public void login(){
        if (checkFields(txtUsername.getText()) || 
                checkFields(txtPassword.getText()) ) {
            JOptionPane.showMessageDialog(null, "Campos vacios");
            return;
        }
        Person person = control.getPersons().search(txtUsername.getText());
        if (person == null) {
            JOptionPane.showMessageDialog(null, "No está registrado");
            return;
        }
        if (control.validPasword(txtPassword.getText(), person)) {
            
            mainWdw = new MainWindow(person, person.getProducts(), 
                    control);
            super.setVisible(false);
            mainWdw.setVisible(true);
        
        }else{
            JOptionPane.showMessageDialog(null, "El usuario o la contraseña es "
                    + "incorrecta.");
            return;
        }
        
    }
    /**
     * permite registro
     */
    public void register(){
        if (checkFields(txtUsername.getText()) || checkFields(txtName.getText()) 
                || checkFields(txtLastName.getText()) 
                || checkFields(txtId.getText()) 
                || checkFields(txtPassword.getText()) 
                || checkFields(txtUsername.getText()) 
                || checkFields(txtComfirmPassword.getText()) 
                || checkFields(txtAge.getText())) {
            JOptionPane.showMessageDialog(null, "Campos vacios.");
            return;
        }
        
        if (control.validUsername(txtUsername.getText())){
            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
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
        
        try{
            this.control.createPerson(txtPassword.getText(), txtUsername.getText(), 
                    txtId.getText(), txtName.getText(), txtLastName.getText(), 
                    Integer.parseInt(txtAge.getText()), null);
            JOptionPane.showMessageDialog(null, "Se registró correctamente.");
            
            mainWdw = new MainWindow(control.getPersons().search(txtUsername.getText())
                    , null, control);
            super.setVisible(false);
            mainWdw.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad incorrecta.");
        }catch(NullPointerException e){
            
        }
        
        
    }
    /**
     * verifica campos vacios
     * @param text tipo String
     * @return boolean
     */
    public boolean checkFields(String text){
        
        return text.isEmpty();
    }
    /**
     * abre apartado de ajustes de producto
     */
    public void openProductSetting() {
        SettingProductWindow wdw = new SettingProductWindow(control);
        wdw.setVisible(true);
    }
    /**
     * actualiza datos de memoria
     */
    private void updateData() {
        try {
            control.restore();
            //System.out.println("RESTORE");
        } catch (Exception ex) {
        }
    }
    /**
     * guarda datos en memoria
     */
    private void saveData() {
        try {
            control.save();
            //System.out.println("SAVING");
        } catch (Exception ex) {
        }
    }
}
