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

    /**
     * Constructor: Recibe el usuario para mantener la conexión activa.
     */
    public pagina_3(String usuario) {
        // Guardamos el usuario recibido
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

        // --- Filtros de Búsqueda ---
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
        btnBuscar.setBounds(280, 60, 140, 95);
        btnBuscar.addActionListener(e -> ejecutarBusqueda());
        contentPane.add(btnBuscar);

        // --- Botón GESTIONAR (Ir a Página 4) ---
        JButton btnIrP4 = new JButton("Gestionar (Editar/Eliminar)");
        btnIrP4.setBounds(250, 425, 200, 30);
        btnIrP4.addActionListener(e -> {
            new pagina_4(userSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnIrP4);

        // --- Botón VOLVER (Ir a Página 2) ---
        JButton btnVolver = new JButton("← Volver al Menú");
        btnVolver.setBounds(10, 520, 150, 30);
        btnVolver.addActionListener(e -> {
            new pagina_2(userSesion).setVisible(true);
            dispose();
        });
        contentPane.add(btnVolver);
    }

    /**
     * Lógica de filtrado usando tu clase Métodos
     */
    private void ejecutarBusqueda() {
        areaResultados.setText("Consultando base de datos...\n");
        
        Métodos logica = new Métodos();
        logica.conectar(userSesion);
        
        if (logica.conexion == null) {
            areaResultados.setText("ERROR: No se pudo conectar a la DB.\nVerifica que MySQL esté encendido.");
            return;
        }
        
        try {
            // Llamada a tu método buscarPokemon
            ResultSet rs = logica.buscarPokemon(
                txtNom.getText().trim(), 
                txtTip.getText().trim(), 
                txtId.getText().trim()
            );

            if (rs != null) {
                areaResultados.setText("ID\tNOMBRE\t\tTIPO\n");
                areaResultados.append("======================================\n");
                
                boolean hayDatos = false;
                while (rs.next()) {
                    hayDatos = true;
                    // Asegúrate de que estos nombres de columna sean iguales en tu DB
                    int id = rs.getInt("IDpoke");
                    String nombre = rs.getString("pokename");
                    String tipo = rs.getString("typename");
                    
                    areaResultados.append(id + "\t" + nombre + "\t\t" + tipo + "\n");
                }
                
                if (!hayDatos) {
                    areaResultados.append("\nNo se encontraron resultados.");
                }
            }
            
            logica.desconectar();
            
        } catch (SQLException ex) {
            areaResultados.setText("Error de SQL: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            areaResultados.setText("Error crítico: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}