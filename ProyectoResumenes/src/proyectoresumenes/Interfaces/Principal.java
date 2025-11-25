/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectoresumenes.Interfaces;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import proyectoresumenes.AVLTreeAutor;
import proyectoresumenes.AVLTreePalabraClave;
import proyectoresumenes.Resumen;
import proyectoresumenes.Sistema;
import proyectoresumenes.TablaHashResumenes;

/**
 *
 * @author Andres
 */
public class Principal extends javax.swing.JFrame {

    static AVLTreeAutor avlA;
    static AVLTreePalabraClave avlPC;
    static TablaHashResumenes tablahash;
    /**
     * Creates new form Principal
     */

    private Sistema sistema;

    public Principal(AVLTreeAutor avlA, AVLTreePalabraClave avlPC, TablaHashResumenes tablahash) {
        initComponents();
        this.setVisible(true);
        this.avlA = avlA;
        this.avlPC = avlPC;
        this.tablahash = tablahash;
        if (avlA == null) {
            this.avlA = new AVLTreeAutor();
            this.avlPC = new AVLTreePalabraClave();
            this.tablahash = new TablaHashResumenes(101);
            this.sistema = new Sistema(this.avlA, this.avlPC, this.tablahash);

            cargarSistema();
        }else{
        this.sistema = new Sistema(avlA, avlPC, tablahash);
        }
        String[] a = this.avlA.listarAutores();
        for (String b : a) {
            System.out.println(";ldfsm;mf;msd;lfmsd;lmd;lsfms;ld");
            System.out.println(a);
        }
    }

    public boolean agregarResumen(Resumen resumen) {
        if (!tablahash.agregar(resumen)) {
            return false;
        }
        for (String autor : resumen.autores) {
            avlA.insertarOActualizar(autor, resumen);
//            System.out.println("lkjadlka");
        }
        for (String clave : resumen.pclaves) {
            avlPC.insertarOActualizar(clave, resumen);
//            System.out.println("ksdk");
        }
//        String[] a = this.avlA.listarAutores();
//        for (String b : a) {
//            System.out.println(";ldfsm;mf;msd;lfmsd;lmd;lsfms;ld");
//            System.out.println(a);
//        }
        return true;
    }

    public Sistema cargarSistema() {
        File archivo = new File("resumen.txt");

        try (Scanner scanner = new Scanner(archivo, "UTF-8")) { 

            String titulo = "";
            String[] autores = null;
            String cuerpo = "";
            String[] pclaves = null;
            String estado = "TITULO";

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();

                if (linea.isEmpty()) {
                    continue;
                }

                if (estado.equals("TITULO")) {
                    titulo = linea;
                    estado = "AUTORES_ETIQUETA";
                } else if (estado.equals("AUTORES_ETIQUETA") && linea.equals("Autores")) {
                    estado = "AUTORES_LISTA"; 
                } else if (estado.equals("AUTORES_LISTA")) {
                    if (linea.equals("Resumen")) {
                        estado = "RESUMEN_CUERPO";
                    } else {
                        java.util.List<String> listaAutores = new java.util.ArrayList<>();
                        listaAutores.add(linea);
                        while (scanner.hasNextLine()) {
                            linea = scanner.nextLine().trim();
                            if (linea.equals("Resumen")) {
                                break;
                            }
                            listaAutores.add(linea);
                        }
                        autores = listaAutores.toArray(new String[0]);
                        estado = "RESUMEN_CUERPO";
                    }
                } else if (estado.equals("RESUMEN_CUERPO")) {
                    if (linea.startsWith("Palabras claves:")) {
                        String clavesStr = linea.substring("Palabras claves:".length()).trim();

                        pclaves = clavesStr.split(",\\s*");

                        Resumen r = new Resumen(titulo, autores, cuerpo.trim(), pclaves);
                        this.sistema.agregarResumen(r); 
                        titulo = "";
                        autores = null;
                        cuerpo = "";
                        pclaves = null;
                        estado = "TITULO"; 
                    } else {

                        cuerpo += linea + " ";
                    }
                }
            }

            System.out.println("Datos cargados exitosamente desde el archivo de persistencia.");

        } catch (java.io.IOException e) {
            System.err.println("Error al cargar los datos. Iniciando sistema nuevo: " + e.getMessage());
        }
        return sistema;
    }
    
    public Sistema cargarResumenDesdeArchivo(File archivo){
        try (Scanner scanner = new Scanner(archivo, "UTF-8")) { 

            String titulo = "";
            String[] autores = null;
            String cuerpo = "";
            String[] pclaves = null;

            String estado = "TITULO";

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim(); 

                if (linea.isEmpty()) {
                    continue;
                }

                if (estado.equals("TITULO")) {

                    titulo = linea;
                    estado = "AUTORES_ETIQUETA"; 
                } else if (estado.equals("AUTORES_ETIQUETA") && linea.equals("Autores")) {
                    estado = "AUTORES_LISTA"; 
                } else if (estado.equals("AUTORES_LISTA")) {
                    if (linea.equals("Resumen")) {

                        estado = "RESUMEN_CUERPO"; 

                    } else {

                        java.util.List<String> listaAutores = new java.util.ArrayList<>();
                        listaAutores.add(linea); 
                        while (scanner.hasNextLine()) {
                            linea = scanner.nextLine().trim();
                            if (linea.equals("Resumen")) {
                                break;
                            }
                            listaAutores.add(linea);
                        }
                        autores = listaAutores.toArray(new String[0]);
                        estado = "RESUMEN_CUERPO";
                    }
                } else if (estado.equals("RESUMEN_CUERPO")) {
                    if (linea.startsWith("Palabras claves:")) {
                        String clavesStr = linea.substring("Palabras claves:".length()).trim();
                        pclaves = clavesStr.split(",\\s*");
                        Resumen r = new Resumen(titulo, autores, cuerpo.trim(), pclaves);
                        this.sistema.agregarResumen(r);
                        titulo = "";
                        autores = null;
                        cuerpo = "";
                        pclaves = null;
                        estado = "TITULO"; 
                    } else {
                        
                        cuerpo += linea + " ";
                    }
                }
            }

            System.out.println("Datos cargados exitosamente desde el archivo de persistencia.");

        } catch (java.io.IOException e) {
            System.err.println("Error al cargar los datos. Iniciando sistema nuevo: " + e.getMessage());
        }
        return sistema;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yet R", 1, 48)); // NOI18N
        jLabel1.setText("SuperMetroMendeley");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, -1));

        jButton1.setFont(new java.awt.Font("Yet R", 1, 18)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 50, 40));

        jButton2.setFont(new java.awt.Font("Yet R", 1, 18)); // NOI18N
        jButton2.setText("LISTAR PALABRAS CLAVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 290, 40));

        jButton3.setFont(new java.awt.Font("Yet R", 1, 18)); // NOI18N
        jButton3.setText("CARGAR RESUMEN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 290, 40));

        jButton4.setFont(new java.awt.Font("Yet R", 1, 18)); // NOI18N
        jButton4.setText("ANALIZAR RESUMEN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 290, 40));

        jButton5.setFont(new java.awt.Font("Yet R", 1, 18)); // NOI18N
        jButton5.setText("BUSCAR AUTORES");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 290, 40));

        jButton6.setFont(new java.awt.Font("Yet R", 1, 18)); // NOI18N
        jButton6.setText("BUSCAR PALABRAS CLAVE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 290, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Principal p = new Principal(avlA, avlPC, tablahash);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AnalizarResumen ar = new AnalizarResumen(avlA, avlPC, tablahash);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        BuscarAutor ba = new BuscarAutor(avlA, avlPC, tablahash);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        BuscarPalabraClave bp = new BuscarPalabraClave(avlA, avlPC, tablahash);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ListarPC lp = new ListarPC(avlA, avlPC, tablahash);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
    String rutaProyecto = System.getProperty("user.dir"); 
    File directorioInicial = new File(rutaProyecto);
    JFileChooser fileChooser = new JFileChooser(directorioInicial);
    fileChooser.setDialogTitle("Seleccionar Archivo de Resumen (.txt)");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt"));
    
    int userSelection = fileChooser.showOpenDialog(this); 
    
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToLoad = fileChooser.getSelectedFile();
        
        try {
           this.cargarResumenDesdeArchivo(fileToLoad);

           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Ocurrió un error inesperado al procesar el archivo: " + ex.getMessage(),
                "Error Grave", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(avlA, avlPC, tablahash).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
