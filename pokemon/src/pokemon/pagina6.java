package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class pagina6 {

    protected Shell shell;

    /**
     * @wbp.parser.entryPoint
     */
    public void open(Display display) {

        shell = new Shell(display);
        shell.setSize(500, 500);
        shell.setText("Crear Pokémon");
        shell.setLayout(null);

        Label l1 = new Label(shell, SWT.NONE);
        l1.setText("Nombre:");
        l1.setBounds(50, 50, 120, 25);

        Text t1 = new Text(shell, SWT.BORDER);
        t1.setBounds(180, 50, 200, 25);

        Label l2 = new Label(shell, SWT.NONE);
        l2.setText("HP:");
        l2.setBounds(50, 100, 120, 25);

        Text t2 = new Text(shell, SWT.BORDER);
        t2.setBounds(180, 100, 200, 25);

        Label l3 = new Label(shell, SWT.NONE);
        l3.setText("Attack:");
        l3.setBounds(50, 150, 120, 25);

        Text t3 = new Text(shell, SWT.BORDER);
        t3.setBounds(180, 150, 200, 25);

        Button btnGuardar = new Button(shell, SWT.PUSH);
        btnGuardar.setText("Guardar Pokémon");
        btnGuardar.setBounds(180, 250, 160, 40);

        btnGuardar.addListener(SWT.Selection, e -> {
            MessageBox msg = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
            msg.setMessage("Pokémon guardado correctamente");
            msg.open();
        });

        Button btnVolver = new Button(shell, SWT.PUSH);
        btnVolver.setText("Volver");
        btnVolver.setBounds(50, 400, 120, 35);

        btnVolver.addListener(SWT.Selection, e -> {
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
