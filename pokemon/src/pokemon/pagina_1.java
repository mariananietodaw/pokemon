package pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import App.Métodos;

public class pagina_1 extends JFrame {
    private JPanel contentPane;
    private JTextField txtUsuario;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new pagina_1().setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public pagina_1() {
        setTitle("Conexión Pokémon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lbl = new JLabel("Usuario SQL:");
        lbl.setBounds(50, 50, 100, 20);
        contentPane.add(lbl);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 150, 25);
        contentPane.add(txtUsuario);

        JButton btnConectar = new JButton("Conectar");
        btnConectar.setBounds(130, 120, 120, 30);
        btnConectar.addActionListener(e -> {
            String user = txtUsuario.getText();
            Métodos db = new Métodos();
            db.conectar(user); // Lógica de tu clase Métodos

            if (db.conexion != null) {
                // ÉXITO: Pasamos a la página 2 con el usuario
                new pagina_2(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se pudo conectar a la DB");
            }
        });
        contentPane.add(btnConectar);
    }
}