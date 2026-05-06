package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class paginaPrincipal {

    protected Shell shell;

    public static void main(String[] args) {
        try {
            paginaPrincipal window = new paginaPrincipal();
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
        shell.setSize( 875, 689);
        shell.setText("SWT Application");

        Display display = shell.getDisplay();

        // 📌 Cargar imagen
      // Image background = new Image(display, "Pokedex.jpg");
        Image background = new Image(display, "C:\\Users\\Usuario\\eclipse-workspace\\pokemon\\src\\pokemon\\Pokedex.jpg");

        // 📌 Label como fondo
        Label fondo = new Label(shell, SWT.NONE);
        fondo.setImage(background);
        fondo.setBounds(-341, -60, 1252, 710);

        // (opcional) para que otros controles se vean encima
        fondo.moveBelow(null);
    }
}
