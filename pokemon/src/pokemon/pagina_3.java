package pokemon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import App.Métodos;

public class pagina_3 extends JFrame {

    private JPanel contentPane;
    private JTextField txtNom, txtTip, txtId;
    private JTextArea areaResultados;
    private String userSesion;

    public pagina_3(String usuario) {
        this.userSesion = usuario;
        
        setTitle("Buscador de Pokémon - Sesión: " + usuario);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- Título ---
        JLabel lblTitulo = new JLabel("BUSCAR EN LA POKÉDEX");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(100, 10, 300, 30);
        contentPane.add(lblTitulo);

        // --- Filtros ---
        JLabel l1 = new JLabel("Nombre:");
        l1.setBounds(30, 60, 80, 20);
        contentPane.add(l1);
        txtNom = new JTextField();
        txtNom.setBounds(100, 60, 150, 25);
        contentPane.add(txtNom);

        JLabel l2 = new JLabel("Tipo:");
        l2.setBounds(30, 95, 80, 20);
        contentPane.add(l2);
        txtTip = new JTextField();
        txtTip.setBounds(100, 95, 150, 25);
        contentPane.add(txtTip);

        JLabel l3 = new JLabel("ID:");
        l3.setBounds(30, 130, 80, 20);
        contentPane.add(l3);
        txtId = new JTextField();
        txtId.setBounds(100, 130, 150, 25);
        contentPane.add(txtId);

        // --- Área de Resultados ---
        areaResultados = new JTextArea();
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(30, 180, 420, 230);
        contentPane.add(scroll);

        // --- Botón BUSCAR ---
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBuscar.setBounds(307, 124, 120, 30);
        btnBuscar.addActionListener(e -> ejecutarBusqueda());
        contentPane.add(btnBuscar);

        // --- Botón GESTIONAR ---
        JButton btnIrP4 = new JButton("Gestionar (Editar/Eliminar)");
        btnIrP4.setBounds(250, 425, 200, 30);
        btnIrP4.addActionListener(e -> {
            // Asegúrate de tener creada la clase pagina_4
            // new pagina_4(userSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnIrP4);

        // --- Botón VOLVER ---
        JButton btnVolver = new JButton("← Volver al Menú");
        btnVolver.setBounds(10, 520, 150, 30);
        btnVolver.addActionListener(e -> {
            // new pagina_2(userSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
    }

    private void ejecutarBusqueda() {
        // 1. Cambiamos el texto para indicar que empezó
        areaResultados.setText("Consultando base de datos... Por favor espere.\n");
        
        // 2. Usamos un hilo secundario para no bloquear la ventana
        Thread hiloBusqueda = new Thread(() -> {
            Métodos logica = new Métodos();
            
            try {
                System.out.println("Intentando conectar...");
                logica.conectar(userSesion);
                
                if (logica.conexion == null) {
                    SwingUtilities.invokeLater(() -> 
                        areaResultados.setText("ERROR: No se pudo conectar. Verifica XAMPP/MySQL."));
                    return;
                }

                // Capturamos datos de los campos
                String nombre = txtNom.getText().trim();
                String tipo = txtTip.getText().trim();
                String id = txtId.getText().trim();

                System.out.println("Ejecutando SQL con filtros: " + nombre + ", " + tipo + ", " + id);
                ResultSet rs = logica.buscarPokemon(nombre, tipo, id);

                // 3. Procesamos los resultados
                StringBuilder sb = new StringBuilder();
                sb.append("ID\tNOMBRE\t\tTIPO\n");
                sb.append("======================================\n");
                
                boolean hayDatos = false;
                if (rs != null) {
                    while (rs.next()) {
                        hayDatos = true;
                        sb.append(rs.getInt("IDpoke")).append("\t")
                          .append(rs.getString("pokename")).append("\t\t")
                          .append(rs.getString("typename")).append("\n");
                    }
                }

                if (!hayDatos) sb.append("\nNo se encontraron resultados.");

                // 4. Actualizamos la interfaz desde el hilo principal
                String resultadoFinal = sb.toString();
                SwingUtilities.invokeLater(() -> areaResultados.setText(resultadoFinal));

                logica.desconectar();
                System.out.println("Busqueda finalizada con éxito.");

            } catch (Exception ex) {
                System.err.println("Error en la búsqueda: " + ex.getMessage());
                SwingUtilities.invokeLater(() -> 
                    areaResultados.setText("Error crítico: " + ex.getMessage()));
            }
        });

        hiloBusqueda.start(); // Arranca el proceso en segundo plano
    }
}