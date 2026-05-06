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
        shell.setSize( 875, 689);
        shell.setText("SWT Application");

        Display display = shell.getDisplay();

        // 📌 Cargar imagen
      // Image background = new Image(display, "Pokedex.jpg");
        Image background = new Image(display, "C:\\Users\\Usuario\\Desktop\\pokemon\\pokemon\\src\\pokemon\\cerrado.png");
        
                // 📌 Label como fondo
                Label fondo = new Label(shell, SWT.NONE);
                fondo.setImage(background);
                fondo.setBounds(-220, -53, 1366, 768);
                
                Label lblBienvenido = new Label(shell, SWT.NONE);
                lblBienvenido.setBounds(435, 174, 177, 15);
                lblBienvenido.setText("INDICA QUE POKEMON BUSCAS");
                
                text = new Text(shell, SWT.BORDER);
                text.setBounds(479, 211, 172, 21);
                
                Button btnNewButton = new Button(shell, SWT.NONE);
                btnNewButton.setBounds(581, 318, 75, 25);
                btnNewButton.setText("BUSCAR");
                
                Label lblIndicaQuePokemon = new Label(shell, SWT.NONE);
                lblIndicaQuePokemon.setText("Nombre:");
                lblIndicaQuePokemon.setBounds(409, 214, 64, 15);
                
                Label lblIndicaQuePokemon_1 = new Label(shell, SWT.NONE);
                lblIndicaQuePokemon_1.setText("Nombre:");
                lblIndicaQuePokemon_1.setBounds(409, 217, 64, 15);
                
                Label lblNewLabel = new Label(shell, SWT.NONE);
                lblNewLabel.setBounds(348, 241, 110, 15);
                lblNewLabel.setText("Numero de pokedex:");
                
                Label lblTipo = new Label(shell, SWT.NONE);
                lblTipo.setText("Tipo:");
                lblTipo.setBounds(424, 268, 35, 15);
                
                text_1 = new Text(shell, SWT.BORDER);
                text_1.setBounds(479, 238, 172, 21);
                
                text_2 = new Text(shell, SWT.BORDER);
                text_2.setBounds(479, 265, 172, 21);
                
                        // (opcional) para que otros controles se vean encima
                        fondo.moveBelow(null);
    }
}

