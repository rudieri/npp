/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.huehue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.bcel.Const;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.DUP;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.InstructionConst;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.TargetLostException;
import org.apache.bcel.util.InstructionFinder;

/**
 *
 * @author rudieri
 */
public class JHueHue extends javax.swing.JFrame {

    private ImageIcon alert2;

    /**
     * Creates new form JHueHue
     */
    public JHueHue() {
        init();
    }

    private void init() {
        initComponents();

        try {
            alert2 = new ImageIcon(ImageIO.read(getClass().getResource("/br/huehue/res/img/alert2.png")));
        } catch (IOException ex) {
            Logger.getLogger(JHueHue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void hueLocal() {
        try {
            JavaClass clazz = Repository.lookupClass("br.huehue.JTesteV1");
            Method[] methods = clazz.getMethods();
            ConstantPoolGen cp = new ConstantPoolGen(clazz.getConstantPool());
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                MethodGen mg = new MethodGen(methods[i], clazz.getClassName(), cp);
//                FieldGen fg = new FieldGen(Const.ACC_PRIVATE, new ObjectType("javax.swing.ImageIcon"), "ueepaGif", cp);
                InstructionList il = mg.getInstructionList();
                InstructionFinder f = new InstructionFinder(il);
                InstructionFactory factory = new InstructionFactory(cp);
                Iterator<InstructionHandle[]> search = f.search("getfield invokestatic");
                for (; search.hasNext();) {
                    InstructionHandle[] next = search.next();
                    InstructionHandle handleGetField = next[0];
                    InstructionHandle handleInvokeSt = next[1];

                    GETFIELD getField = (GETFIELD) handleGetField.getInstruction();

                    org.apache.bcel.generic.Type type = getField.getType(cp);
                    if (!type.toString().equals("javax.swing.ImageIcon")) {
                        continue;
                    }

                    INVOKESTATIC invStatic = (INVOKESTATIC) handleInvokeSt.getInstruction();
                    Constant c = cp.getConstantPool().getConstant(invStatic.getIndex());
                    String signature = invStatic.getSignature(cp);
                    if (c instanceof ConstantMethodref) {
                        String clName = ((ConstantMethodref) c).getClass(cp.getConstantPool());
                        if (clName.equals("javax.swing.JOptionPane")) {
                            int idxFileUepa = cp.addString("/br/huehue/res/img/alert2.png");


                            InstructionList nil = new InstructionList();
                            // Nova variável para o gif da zoeira infinita
//                  

                            InstructionHandle append = nil.append(factory.createNew(new ObjectType("javax.swing.ImageIcon")));
                            nil.append(InstructionConst.DUP);
//                            nil.append(new DUP());
                            LocalVariableGen lvgUepa = mg.addLocalVariable("uepaGif", new ObjectType("javax.swing.ImageIcon"), null, null);

                            // Referencia para o JTesteV1
                            nil.append(new ALOAD(0));
                            // Get class
                            String sigGetClass = createMethodSig(org.apache.bcel.generic.Type.CLASS, new org.apache.bcel.generic.Type[0]);
                            int getClassRef = cp.addMethodref("br.huehue.JTesteV1", "getClass", sigGetClass);
                            nil.append(new INVOKEVIRTUAL(getClassRef));
                            nil.append(new LDC(idxFileUepa));
                            
                            // get resource
                            String sigGetResource = createMethodSig(new ObjectType("java.net.URL"), org.apache.bcel.generic.Type.STRING);
                            int getResourceRef = cp.addMethodref("java.lang.Class", "getResource", sigGetResource);
                            nil.append(new INVOKEVIRTUAL(getResourceRef));

                            String sigRead = createMethodSig(new ObjectType("java.awt.image.BufferedImage"), new ObjectType("java.net.URL"));
                            int imageIORef = cp.addMethodref("javax.imageio.ImageIO", "read", sigRead);
                            nil.append(new INVOKESTATIC(imageIORef));
                            
                            
                            // Depois: init
                            InvokeInstruction initImage = factory.createInvoke("javax.swing.ImageIcon", "<init>", org.apache.bcel.generic.Type.VOID, new org.apache.bcel.generic.Type[]{new ObjectType("java.awt.Image")}, Const.INVOKESPECIAL);
                            nil.append(initImage);

                            // Depois do init, guarda para a variável
                            lvgUepa.setStart(nil.append(new ASTORE(lvgUepa.getIndex())));

                            
                            // Load antes da mensagem



//                            int imageIORef = cp.lookupMethodref("ImageIO", "read", "javax/imageio/ImageIO.read:(Ljava/net/URL;)Ljava/awt/image/BufferedImage");
                            
//                            mg.addLocalVariable("abacate", new ObjectType("java.lang.Integer"), null, null);
                            il.insert(il.getStart(), nil);
//                            il.insert(getField, nil);
                            InstructionHandle prev = handleGetField.getPrev();
                            
                            il.insert(getField, new ALOAD(lvgUepa.getIndex()));
                            il.delete(getField);
                            il.delete(prev.getInstruction());


//                            il.dispose();
                            mg.setMaxStack();
                            mg.setMaxLocals();
                            methods[i] = mg.getMethod();
                            clazz.setConstantPool(cp.getFinalConstantPool());
//                            ByteArrayOutputStream out = new ByteArrayOutputStream();
//                            clazz.dump(out);
                            URL res = getClass().getResource("");
                            File file = new File(res.getFile(), "JTesteV1.class");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            clazz.dump(file);

                            break;
                        }
                    }

                }

            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(this, "Classe não encontrada: " + ex.toString());
        } catch (TargetLostException ex) {
            Logger.getLogger(JHueHue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JHueHue.class.getName()).log(Level.SEVERE, null, ex);
        }
//        JTesteV1 jTeste = new JTesteV1(this, false);
//        jTeste.setVisible(true);

    }
    
    private void executarNovaClasse(){
        try {
            ProcessBuilder pb = new ProcessBuilder("pwd");
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            pb.start();
            
            
            pb = new ProcessBuilder("java", "-cp", "build/classes", "br.huehue.JTesteV1");
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            pb.start();
        } catch (IOException ex) {
            Logger.getLogger(JHueHue.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Deu crepz, olha o log...");
        }
    }

    private String createMethodSig(org.apache.bcel.generic.Type retType, org.apache.bcel.generic.Type... params){
        String sig = org.apache.bcel.generic.Type.getMethodSignature(retType, params);
        return sig;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Hue Local");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);

        setSize(new java.awt.Dimension(378, 203));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hueLocal();
        executarNovaClasse();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(JHueHue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JHueHue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JHueHue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JHueHue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JHueHue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
