package ui;

import analizadores.Lexer;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.StringReader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java_cup.runtime.Symbol;
import analizadores.Parser;
import ast.Instruccion;
import entorno.Entorno;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import reportes.ReporteT;
import reportes.ReporteE;
import reportes.ErrorC;
import entorno.Consola;

public class Principal extends JFrame {

    private JTextArea editor;
    private JTextArea consola;
    private JTable tablaTokens;
    private JButton btnEjecutar;
    private JButton btnCargarArchivo;
    private JTable tablaErrores;
    

    public Principal() {
        setTitle("GoLite - OLC1 Fase 1");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        editor = new JTextArea();
        consola = new JTextArea();
        consola.setEditable(false);

        btnEjecutar = new JButton("Ejecutar Análisis");
        btnCargarArchivo = new JButton("Cargar Archivo");

        tablaTokens = new JTable();
        tablaTokens.setModel(new DefaultTableModel(
                new Object[]{"No.", "Token", "Lexema", "Línea", "Columna"}, 0
        ));
        tablaErrores = new JTable();
        tablaErrores.setModel(new DefaultTableModel(
                new Object[]{"No.", "Tipo", "Descripción", "Línea", "Columna"}, 0
        ));

        editor.setText("""

""");

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(btnEjecutar);
        panelSuperior.add(btnCargarArchivo);
        
         add(panelSuperior, BorderLayout.NORTH);

        JSplitPane splitEditorConsola = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(editor),
                new JScrollPane(consola)
        );
        splitEditorConsola.setDividerLocation(600);

        JPanel panelCentro = new JPanel(new GridLayout(2, 1));
        panelCentro.add(splitEditorConsola);
        JTabbedPane pestañasReportes = new JTabbedPane();
        //coloca los dos paneles a la par
        pestañasReportes.addTab("Tokens", new JScrollPane(tablaTokens));
        pestañasReportes.addTab("Errores", new JScrollPane(tablaErrores));

panelCentro.add(pestañasReportes);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        btnEjecutar.addActionListener(e -> ejecutarAnalisis());
        btnCargarArchivo.addActionListener(e -> cargarArchivo());
    }

    private void ejecutarAnalisis() {
        try {
            consola.setText("");
            
            DefaultTableModel modelo = (DefaultTableModel) tablaTokens.getModel();
            modelo.setRowCount(0);
            
            //para limiar tabla de tokens para que no los acumule si hay una nueva declaracion
            ReporteT.limpiar();
            ReporteE.limpiar();
            Consola.limpiar();

            String entrada = editor.getText();
            Lexer lexer = new Lexer(new StringReader(entrada));
            Parser parser = new Parser(lexer);

            //Symbol token;
            
            java_cup.runtime.Symbol resultadoParser = parser.parse();
            
            cargarTablaTokens();
            
            if (resultadoParser == null || resultadoParser.value == null) {
            consola.setText("El análisis sintáctico falló.");
            return;
            }

            Object resultado = resultadoParser.value;
            
            if (!(resultado instanceof LinkedList)) {
                TablaErrores();
                consola.setText("El análisis sintáctico falló.");
                return;
            }
            
            LinkedList<Instruccion> lista = (LinkedList<Instruccion>) resultado;

            Entorno global = new Entorno();

            for (Instruccion ins : lista) {
                ins.ejecutar(global);
            }
            
            
            
           // System.out.println("Cantidad errores: " + ReporteE.getErrores().size());
            
            TablaErrores();
            
            consola.setText(
                    Consola.obtenerTexto()
            );

            if (ReporteE.hayErrores()) {
                consola.append("\nSe encontraron errores.");
                return;
            }

            consola.append("\nAnálisis y ejecución finalizados correctamente.");

        } catch (Exception ex) {
                cargarTablaTokens();
                TablaErrores();
                consola.setText("Error: " + ex.getMessage());
        }
    }
    
    private void cargarTablaTokens() {
        DefaultTableModel modelo = (DefaultTableModel) tablaTokens.getModel();
        modelo.setRowCount(0);

        int contador = 1;

        for (reportes.Token token : ReporteT.getTokens()) {
            modelo.addRow(new Object[]{
                contador++,
                token.getTipo(),
                token.getLexema(),
                token.getLinea(),
                token.getColumna()
            });
        }
    }
    
    private void cargarArchivo() {

        JFileChooser chooser = new JFileChooser();

        int opcion = chooser.showOpenDialog(this);

        if(opcion == JFileChooser.APPROVE_OPTION){

            File archivo = chooser.getSelectedFile();

            try(BufferedReader br = new BufferedReader(new FileReader(archivo))){

                StringBuilder contenido = new StringBuilder();
                String linea;

                while((linea = br.readLine()) != null){
                    contenido.append(linea).append("\n");
                }

                editor.setText(contenido.toString());

            }catch(Exception e){
                JOptionPane.showMessageDialog(this,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void TablaErrores() {
        DefaultTableModel modelo = (DefaultTableModel) tablaErrores.getModel();
        modelo.setRowCount(0);

        int contador = 1;

        for (reportes.ErrorC error : ReporteE.getErrores()) {
            modelo.addRow(new Object[]{
                contador++,
                error.getTipo(),
                error.getDescripcion(),
                error.getLinea(),
                error.getColumna()
            });
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
}
