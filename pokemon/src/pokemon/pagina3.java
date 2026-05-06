package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.JFaceResources;

public class pagina3 {

    protected Shell shell;
    private LocalResourceManager localResourceManager;

    /**
     * @wbp.parser.entryPoint
     */
    public void open(Display display) {

        shell = new Shell(display);
        createResourceManager();
        shell.setSize(520, 450);
        shell.setText("Crear Pokémon");
        shell.setLayout(null);

        // ⭐ Fondo con imagen Pokédex
        Image fondo = new Image(display, pagina3.class.getResourceAsStream("/pokemon/Pokedex.jpg"));

        shell.addListener(SWT.Paint, e -> {
            e.gc.drawImage(
                fondo,
                0, 0, fondo.getBounds().width, fondo.getBounds().height,
                0, 0, shell.getSize().x, shell.getSize().y
            );
        });

        Color blanco = display.getSystemColor(SWT.COLOR_WHITE);

        int xLabel = 50;
        int xField = 200;
        int w = 200;
        int h = 25;

        // -----------------------------
        // CAMPOS DEL FORMULARIO
        // -----------------------------

        Label l1 = new Label(shell, SWT.NONE);
        l1.setText("ID:");
        l1.setBounds(xLabel, 30, 120, h);
        l1.setBackground(null);

        Text t1 = new Text(shell, SWT.BORDER);
        t1.setBounds(xField, 30, w, h);
        t1.setBackground(blanco);

        Label l2 = new Label(shell, SWT.NONE);
        l2.setText("Nombre:");
        l2.setBounds(xLabel, 70, 120, h);
        l2.setBackground(null);

        Text t2 = new Text(shell, SWT.BORDER);
        t2.setBounds(xField, 70, w, h);
        t2.setBackground(blanco);

        Label l3 = new Label(shell, SWT.NONE);
        l3.setText("HP:");
        l3.setBounds(xLabel, 110, 120, h);
        l3.setBackground(null);

        Text t3 = new Text(shell, SWT.BORDER);
        t3.setBounds(xField, 110, w, h);
        t3.setBackground(blanco);

        Label l4 = new Label(shell, SWT.NONE);
        l4.setText("Attack:");
        l4.setBounds(xLabel, 150, 120, h);
        l4.setBackground(null);

        Text t4 = new Text(shell, SWT.BORDER);
        t4.setBounds(xField, 150, w, h);
        t4.setBackground(blanco);

        Label l5 = new Label(shell, SWT.NONE);
        l5.setText("Defense:");
        l5.setBounds(xLabel, 190, 120, h);
        l5.setBackground(null);

        Text t5 = new Text(shell, SWT.BORDER);
        t5.setBounds(xField, 190, w, h);
        t5.setBackground(blanco);

        Label l6 = new Label(shell, SWT.NONE);
        l6.setText("Sp Attack:");
        l6.setBounds(xLabel, 230, 120, h);
        l6.setBackground(null);

        Text t6 = new Text(shell, SWT.BORDER);
        t6.setBounds(xField, 230, w, h);
        t6.setBackground(blanco);

        Label l7 = new Label(shell, SWT.NONE);
        l7.setText("Sp Defense:");
        l7.setBounds(xLabel, 270, 120, h);
        l7.setBackground(null);

        Text t7 = new Text(shell, SWT.BORDER);
        t7.setBounds(xField, 270, w, h);
        t7.setBackground(blanco);

        Label l8 = new Label(shell, SWT.NONE);
        l8.setText("Speed:");
        l8.setBounds(xLabel, 310, 120, h);
        l8.setBackground(null);

        Text t8 = new Text(shell, SWT.BORDER);
        t8.setBounds(xField, 310, w, h);
        t8.setBackground(blanco);

        // -----------------------------
        // BOTÓN GUARDAR
        // -----------------------------
        Button guardar = new Button(shell, SWT.PUSH);
        guardar.setText("Guardar Pokémon");
        guardar.setBounds(300, 360, 160, 40);

        guardar.addListener(SWT.Selection, e -> {
            String id = t1.getText();
            String nombre = t2.getText();
            String hp = t3.getText();
            String atk = t4.getText();
            String def = t5.getText();
            String spAtk = t6.getText();
            String spDef = t7.getText();
            String speed = t8.getText();

            // Aquí va tu lógica SQL
            // Ejemplo:
            // ConexionBD.insertarPokemon(id, nombre, hp, atk, def, spAtk, spDef, speed);

            MessageBox msg = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
            msg.setMessage("Pokémon guardado correctamente");
            msg.open();
        });

        // -----------------------------
        // BOTÓN VOLVER AL MENÚ
        // -----------------------------
        Button volver = new Button(shell, SWT.PUSH);
        volver.setText("Volver al Menú");
        volver.setBounds(50, 360, 160, 40);

        volver.addListener(SWT.Selection, e -> {
            shell.close();
            pagina1 menu = new pagina1();  // ← Cambia esto si tu menú tiene otro nombre
            menu.open(display);
        });

        // -----------------------------
        // LOOP PRINCIPAL
        // -----------------------------
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        fondo.dispose();
    }

    private void createResourceManager() {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shell);
    }
}

