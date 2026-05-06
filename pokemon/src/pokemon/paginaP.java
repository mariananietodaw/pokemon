package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class paginaP {

    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;
    private Image background;

    public static void main(String[] args) {
        try {
            paginaP window = new paginaP();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        Display display = Display.getDefault();
        createContents();

        shell.open();
        shell.layout();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        // Liberar recursos
        if (background != null && !background.isDisposed()) {
            background.dispose();
        }

        display.dispose();
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(875, 636);
        shell.setText("Pokedex");
        shell.setLayout(null);

        Display display = shell.getDisplay();

        // 📌 Cargar imagen desde recursos (NO ruta absoluta)
        background = new Image(display,
        	    "C:\\Users\\Usuario\\Desktop\\pokemon\\pokemon\\src\\pokemon\\cerrado.png");
        if (background == null) {
            System.out.println("❌ Imagen no cargada");
        } else {
            System.out.println("✅ Imagen cargada");
        }
        // 📌 Dibujar fondo correctamente
        shell.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                if (background != null) {
                    e.gc.drawImage(background, 0, 0);
                }
            }
        });

        // 🔹 Labels
        Label lblBienvenido = new Label(shell, SWT.NONE);
        lblBienvenido.setBounds(435, 174, 220, 20);
        lblBienvenido.setText("INDICA QUE POKEMON BUSCAS");

        Label lblNombre = new Label(shell, SWT.NONE);
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(409, 214, 64, 15);

        Label lblNumero = new Label(shell, SWT.NONE);
        lblNumero.setBounds(348, 241, 130, 15);
        lblNumero.setText("Numero de pokedex:");

        Label lblTipo = new Label(shell, SWT.NONE);
        lblTipo.setText("Tipo:");
        lblTipo.setBounds(424, 268, 35, 15);

        // 🔹 Campos de texto
        text = new Text(shell, SWT.BORDER);
        text.setBounds(479, 211, 172, 21);

        text_1 = new Text(shell, SWT.BORDER);
        text_1.setBounds(479, 238, 172, 21);

        text_2 = new Text(shell, SWT.BORDER);
        text_2.setBounds(479, 265, 172, 21);

        // 🔹 Botón
        Button btnBuscar = new Button(shell, SWT.PUSH);
        btnBuscar.setBounds(581, 318, 75, 25);
        btnBuscar.setText("BUSCAR");
    }
}
