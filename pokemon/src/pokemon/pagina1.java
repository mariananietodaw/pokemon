package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class pagina1 {

    protected Shell shell;

    /**
     * @wbp.parser.entryPoint
     */
    public void open(Display display) {

        shell = new Shell(display);
        shell.setSize(400, 250);
        shell.setText("Login Pokémon");
        shell.setLayout(null);

        Label lblUser = new Label(shell, SWT.NONE);
        lblUser.setText("Usuario:");
        lblUser.setBounds(50, 60, 100, 25);

        Text txtUser = new Text(shell, SWT.BORDER);
        txtUser.setBounds(150, 60, 180, 25);

        Button btnConectar = new Button(shell, SWT.PUSH);
        btnConectar.setText("Conectar");
        btnConectar.setBounds(150, 120, 120, 35);

        btnConectar.addListener(SWT.Selection, e -> {
            shell.close();
            pagina2 menu = new pagina2();
            menu.open(display);
        });

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
