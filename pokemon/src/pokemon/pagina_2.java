package pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class pagina_2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String usuarioSesion;

    public pagina_2(String usuario) {
        this.usuarioSesion = usuario;
        
        setTitle("Menú Principal - Pokémon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450); // Aumenté un poco el alto
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("PANEL DE CONTROL");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(110, 20, 230, 30);
        contentPane.add(lblTitulo);

        JLabel lblBienvenida = new JLabel("Bienvenido, entrenador: " + usuarioSesion);
        lblBienvenida.setForeground(new Color(0, 102, 204));
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lblBienvenida.setBounds(50, 60, 350, 25);
        contentPane.add(lblBienvenida);

        // --- BOTÓN 1: BUSCAR ---
        JButton btnBuscar = new JButton("Buscar Pokémon");
        btnBuscar.setBounds(120, 110, 200, 45);
        btnBuscar.addActionListener(e -> {
            new pagina_3(usuarioSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnBuscar);

        // --- BOTÓN 2: MODIFICAR / ELIMINAR (Página 4) ---
        JButton btnGestionar = new JButton("Modificar / Eliminar");
        btnGestionar.setBounds(120, 175, 200, 45);
        btnGestionar.addActionListener(e -> {
            new pagina_4(usuarioSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnGestionar);

        // --- BOTÓN 3: CREAR NUEVO (Página 6) ---
        JButton btnCrear = new JButton("Crear Pokémon");
        btnCrear.setBounds(120, 240, 200, 45);
        btnCrear.addActionListener(e -> {
            // VERIFICADO: Abre la página 6 correctamente
            new pagina_6(usuarioSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnCrear);

        // --- BOTÓN SALIR ---
        JButton btnVolver = new JButton("← Cerrar Sesión");
        btnVolver.setForeground(new Color(153, 0, 0));
        btnVolver.setBounds(10, 370, 140, 30);
        btnVolver.addActionListener(e -> {
            new pagina_1().setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
    }
}