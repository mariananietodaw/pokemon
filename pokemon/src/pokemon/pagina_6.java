package pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import App.Métodos;

public class pagina_6 extends JFrame {

    private JPanel contentPane;
    private JTextField txtId, txtNombre, txtHp, txtAtk, txtDef, txtSpAtk, txtSpDef, txtVel, txtTipo1, txtTipo2;
    private JCheckBox chkDual;
    private String userSesion;

    public pagina_6(String usuario) {
        this.userSesion = usuario;
        setTitle("Registrar Pokémon - Sesión: " + usuario);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Diseño absoluto

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("ALTA DE NUEVO POKÉMON");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(80, 20, 300, 30);
        contentPane.add(lblTitulo);

        // --- FILA 1: ID y NOMBRE ---
        JLabel lId = new JLabel("ID:");
        lId.setBounds(40, 70, 30, 20);
        contentPane.add(lId);
        txtId = new JTextField(); 
        txtId.setBounds(70, 70, 60, 25);
        contentPane.add(txtId);

        JLabel lNom = new JLabel("Nombre:");
        lNom.setBounds(150, 70, 60, 20);
        contentPane.add(lNom);
        txtNombre = new JTextField(); 
        txtNombre.setBounds(210, 70, 200, 25);
        contentPane.add(txtNombre);

        // --- FILA 2: HP, ATK, DEF ---
        JLabel lHp = new JLabel("HP:");
        lHp.setBounds(40, 120, 30, 20);
        contentPane.add(lHp);
        txtHp = new JTextField(); 
        txtHp.setBounds(70, 120, 50, 25);
        contentPane.add(txtHp);

        JLabel lAtk = new JLabel("Atk:");
        lAtk.setBounds(160, 120, 30, 20);
        contentPane.add(lAtk);
        txtAtk = new JTextField(); 
        txtAtk.setBounds(195, 120, 50, 25);
        contentPane.add(txtAtk);

        JLabel lDef = new JLabel("Def:");
        lDef.setBounds(280, 120, 30, 20);
        contentPane.add(lDef);
        txtDef = new JTextField(); 
        txtDef.setBounds(315, 120, 50, 25);
        contentPane.add(txtDef);

        // --- FILA 3: SP.ATK, SP.DEF, VEL ---
        JLabel lSpA = new JLabel("Sp.Atk:");
        lSpA.setBounds(40, 170, 50, 20);
        contentPane.add(lSpA);
        txtSpAtk = new JTextField(); 
        txtSpAtk.setBounds(90, 170, 50, 25);
        contentPane.add(txtSpAtk);

        JLabel lSpD = new JLabel("Sp.Def:");
        lSpD.setBounds(160, 170, 50, 20);
        contentPane.add(lSpD);
        txtSpDef = new JTextField(); 
        txtSpDef.setBounds(215, 170, 50, 25);
        contentPane.add(txtSpDef);

        JLabel lVel = new JLabel("Vel:");
        lVel.setBounds(300, 170, 30, 20);
        contentPane.add(lVel);
        txtVel = new JTextField(); 
        txtVel.setBounds(335, 170, 50, 25);
        contentPane.add(txtVel);

        // --- FILA 4: TIPOS ---
        JLabel lT1 = new JLabel("ID Tipo 1:");
        lT1.setBounds(40, 230, 70, 20);
        contentPane.add(lT1);
        txtTipo1 = new JTextField(); 
        txtTipo1.setBounds(110, 230, 50, 25);
        contentPane.add(txtTipo1);

        JLabel lT2 = new JLabel("ID Tipo 2:");
        lT2.setBounds(200, 230, 70, 20);
        contentPane.add(lT2);
        txtTipo2 = new JTextField(); 
        txtTipo2.setBounds(275, 230, 50, 25);
        txtTipo2.setEnabled(false); // Bloqueado hasta marcar checkbox
        contentPane.add(txtTipo2);

        chkDual = new JCheckBox("Doble Tipo");
        chkDual.setBounds(40, 265, 120, 20);
        chkDual.addActionListener(e -> txtTipo2.setEnabled(chkDual.isSelected()));
        contentPane.add(chkDual);

        // --- BOTÓN GUARDAR (Corregido color y visibilidad) ---
        JButton btnGuardar = new JButton("GUARDAR POKÉMON");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 204, 113)); // Verde
        btnGuardar.setForeground(Color.BLACK); // Texto negro para que se vea
        btnGuardar.setBounds(100, 330, 250, 50);
        btnGuardar.addActionListener(e -> registrar());
        contentPane.add(btnGuardar);

        // --- BOTÓN VOLVER ---
        JButton btnVolver = new JButton("← Volver al Menú");
        btnVolver.setBounds(20, 510, 150, 30);
        btnVolver.addActionListener(e -> {
            new pagina_2(userSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
    }

    private void registrar() {
        try {
            Métodos db = new Métodos();
            db.conectar(userSesion);
            
            // Pasamos los datos a tu método anadirPoke
            db.anadirPoke(
                Integer.parseInt(txtId.getText()),
                "'" + txtNombre.getText() + "'",
                Integer.parseInt(txtHp.getText()),
                Integer.parseInt(txtAtk.getText()),
                Integer.parseInt(txtDef.getText()),
                Integer.parseInt(txtSpAtk.getText()),
                Integer.parseInt(txtSpDef.getText()),
                Integer.parseInt(txtVel.getText()),
                chkDual.isSelected(),
                Integer.parseInt(txtTipo1.getText()),
                chkDual.isSelected() ? Integer.parseInt(txtTipo2.getText()) : null
            );
            
            JOptionPane.showMessageDialog(this, "¡Pokémon Guardado!");
            db.desconectar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}