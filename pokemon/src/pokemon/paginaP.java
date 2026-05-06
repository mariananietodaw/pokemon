package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class paginaP {

    protected Shell shell;
    private Text text;
    private Text text_1;
    private Text text_2;

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

        display.dispose();
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(875, 689);
        shell.setText("Pokedex");

        Display display = shell.getDisplay();

        //  Imagen de fondo
        Image background = new Image(display,
                "C:\\Users\\Usuario\\Desktop\\pokemon\\pokemon\\src\\pokemon\\cerrado.png");

        Label fondo = new Label(shell, SWT.NONE);
        fondo.setImage(background);
        fondo.setBounds(-205, -62, 1366, 768);

        //  Labels 
        Label lblBienvenido = new Label(shell, SWT.NONE);
        lblBienvenido.setBounds(435, 174, 220, 20);
        lblBienvenido.setText("INDICA QUE POKEMON BUSCAS");
        lblBienvenido.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));

        Label lblNombre = new Label(shell, SWT.NONE);
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(409, 214, 64, 15);
        lblNombre.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));

        Label lblNumero = new Label(shell, SWT.NONE);
        lblNumero.setBounds(348, 241, 130, 15);
        lblNumero.setText("Numero de pokedex:");
        lblNumero.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));

        Label lblTipo = new Label(shell, SWT.NONE);
        lblTipo.setText("Tipo:");
        lblTipo.setBounds(424, 268, 35, 15);
        lblTipo.setBackground(display.getSystemColor(SWT.COLOR_TRANSPARENT));

        //  Text fields 
        text = new Text(shell, SWT.BORDER);
        text.setBounds(479, 211, 172, 21);

        text_1 = new Text(shell, SWT.BORDER);
        text_1.setBounds(479, 238, 172, 21);

        text_2 = new Text(shell, SWT.BORDER);
        text_2.setBounds(479, 265, 172, 21);

        //  Botón
        Button btnBuscar = new Button(shell, SWT.NONE);
        btnBuscar.setBounds(581, 318, 75, 25);
        btnBuscar.setText("BUSCAR");

        // 📌 Asegurar que el fondo quede detrás
        fondo.moveBelow(null);
    }
}
