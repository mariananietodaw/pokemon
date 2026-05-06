package pokemon;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

public class pagina2 {

    protected Shell shell;

    /**
     * @wbp.parser.entryPoint
     */
    public void open(Display display) {

        shell = new Shell(display);
        shell.setSize(400, 300);
        shell.setText("Menú Principal");
        shell.setLayout(null);

        Button btnBuscar = new Button(shell, SWT.PUSH);
        btnBuscar.setText("Buscar Pokémon");
        btnBuscar.setBounds(120, 70, 160, 40);

        btnBuscar.addListener(SWT.Selection, e -> {
            shell.close();
            pagina3 buscar = new pagina3();
            buscar.open(display);
        });

        Button btnCrear = new Button(shell, SWT.PUSH);
        btnCrear.setText("Crear Pokémon");
        btnCrear.setBounds(120, 140, 160, 40);

        btnCrear.addListener(SWT.Selection, e -> {
            shell.close();
            pagina6 crear = new pagina6();
            crear.open(display);
        });

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
