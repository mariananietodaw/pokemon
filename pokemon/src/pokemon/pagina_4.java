package pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import App.Métodos;

public class pagina_4 extends JFrame {
    private JPanel contentPane;
    private JTextField txtId, txtNuevoNombre, txtNuevoTipo;
    private String userSesion;

    public pagina_4(String usuario) {
        this.userSesion = usuario;
        setTitle("Gestión de Pokémon (Modificar/Eliminar)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("EDITAR O ELIMINAR POKÉMON");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(100, 20, 250, 25);
        contentPane.add(lblTitulo);

        // Sección ID (Clave para buscar)
        JLabel lblId = new JLabel("ID del Pokémon a afectar:");
        lblId.setBounds(30, 70, 180, 20);
        contentPane.add(lblId);
        txtId = new JTextField();
        txtId.setBounds(210, 70, 100, 25);
        contentPane.add(txtId);

        JSeparator separator = new JSeparator();
        separator.setBounds(30, 115, 370, 2);
        contentPane.add(separator);

        // Sección Modificar
        JLabel lblNombre = new JLabel("Nuevo Nombre:");
        lblNombre.setBounds(30, 140, 100, 20);
        contentPane.add(lblNombre);
        txtNuevoNombre = new JTextField();
        txtNuevoNombre.setBounds(140, 140, 150, 25);
        contentPane.add(txtNuevoNombre);

        JButton btnModificar = new JButton("Modificar Nombre");
        btnModificar.setBounds(140, 180, 150, 30);
        btnModificar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Introduce un ID");
                return;
            }
            Métodos db = new Métodos();
            db.conectar(userSesion);
            // Usamos tu método: pasamos ID, nombre y nulos para el resto de stats
            db.modificarPokemon(Integer.parseInt(txtId.getText()), txtNuevoNombre.getText(), 
                               null, null, null, null, null, null, false, null, null);
            db.desconectar();
            JOptionPane.showMessageDialog(this, "Operación de modificación enviada.");
        });
        contentPane.add(btnModificar);

        // Sección Eliminar
        JButton btnEliminar = new JButton("ELIMINAR POKÉMON");
        btnEliminar.setBackground(new Color(255, 102, 102));
        btnEliminar.setBounds(120, 250, 180, 40);
        btnEliminar.addActionListener(e -> {
            int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar este Pokémon?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                Métodos db = new Métodos();
                db.conectar(userSesion);
                db.eliminarPokemon(Integer.parseInt(txtId.getText()));
                db.desconectar();
                JOptionPane.showMessageDialog(this, "Eliminado correctamente.");
            }
        });
        contentPane.add(btnEliminar);

        // Botón Volver
        JButton btnVolver = new JButton("← Volver");
        btnVolver.setBounds(10, 320, 90, 25);
        btnVolver.addActionListener(e -> {
            new pagina_2(userSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
    }
}