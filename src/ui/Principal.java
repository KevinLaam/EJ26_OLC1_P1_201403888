package ui;

import analizadores.Lexer;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.StringReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java_cup.runtime.Symbol;

public class Principal extends JFrame {

    private JTextArea editor;
    private JTextArea consola;
    private JTable tablaTokens;
    private JButton btnEjecutar;

    public Principal() {
        setTitle("GoLite - OLC1 Fase 1");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        editor = new JTextArea();
        consola = new JTextArea();
        consola.setEditable(false);

        btnEjecutar = new JButton("Ejecutar análisis léxico");

        tablaTokens = new JTable();
        tablaTokens.setModel(new DefaultTableModel(
                new Object[]{"No.", "Token", "Lexema", "Línea", "Columna"}, 0
        ));

        editor.setText("""
func main() {
    var a int = 100
    var b float64 = 3.14
    cadena := "Hola"
    letra := 'A'
    bandera := true
}
""");

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(btnEjecutar, BorderLayout.WEST);

        JSplitPane splitEditorConsola = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(editor),
                new JScrollPane(consola)
        );
        splitEditorConsola.setDividerLocation(600);

        JPanel panelCentro = new JPanel(new GridLayout(2, 1));
        panelCentro.add(splitEditorConsola);
        panelCentro.add(new JScrollPane(tablaTokens));

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        btnEjecutar.addActionListener(e -> ejecutarLexico());
    }

    private void ejecutarLexico() {
        try {
            consola.setText("");
            DefaultTableModel modelo = (DefaultTableModel) tablaTokens.getModel();
            modelo.setRowCount(0);

            String entrada = editor.getText();
            Lexer lexer = new Lexer(new StringReader(entrada));
            Symbol token;

            int contador = 1;

            while ((token = lexer.next_token()).sym != analizadores.sym.EOF) {
                modelo.addRow(new Object[]{
                    contador++,
                    token.sym,
                    token.value,
                    token.left,
                    token.right
                });
            }

            consola.setText("Análisis léxico finalizado correctamente.");

        } catch (Exception ex) {
            consola.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
}
