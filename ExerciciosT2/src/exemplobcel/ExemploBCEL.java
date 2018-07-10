/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplobcel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.bcel.Const;
import static org.apache.bcel.Const.ACC_PUBLIC;
import static org.apache.bcel.Const.ACC_STATIC;
import static org.apache.bcel.Const.ACC_SUPER;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionConst;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

/**
 *
 * @author rudieri
 */
public class ExemploBCEL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClassGen cg = new ClassGen("exerciciost2.HelloWorld", "java.lang.Object",
                "<generated>", ACC_PUBLIC | ACC_SUPER, null);
        ConstantPoolGen cp = cg.getConstantPool(); // cg creates constant pool
        InstructionList il = new InstructionList();

        MethodGen mg = new MethodGen(ACC_STATIC | ACC_PUBLIC, // access flags
                Type.VOID, // return type
                new Type[]{ // argument types
                    new ArrayType(Type.STRING, 1)},
                new String[]{"argv"}, // arg names
                "main", "HelloWorld", // method, class
                il, cp);
        InstructionFactory factory = new InstructionFactory(cg);

        ObjectType i_stream = new ObjectType("java.io.InputStream");
        ObjectType p_stream = new ObjectType("java.io.PrintStream");

        il.append(factory.createNew("java.io.BufferedReader"));
        il.append(InstructionConst.DUP); // Use predefined constant
        il.append(factory.createNew("java.io.InputStreamReader"));
        il.append(InstructionConst.DUP);
        il.append(factory.createFieldAccess("java.lang.System", "in", i_stream, Const.GETSTATIC));
        il.append(factory.createInvoke("java.io.InputStreamReader", "<init>",
                Type.VOID, new Type[]{i_stream},
                Const.INVOKESPECIAL));
        il.append(factory.createInvoke("java.io.BufferedReader", "<init>", Type.VOID,
                new Type[]{new ObjectType("java.io.Reader")},
                Const.INVOKESPECIAL));

        LocalVariableGen lg = mg.addLocalVariable("in",
                new ObjectType("java.io.BufferedReader"), null, null);
        int in = lg.getIndex();
        lg.setStart(il.append(new ASTORE(in))); // "i" valid from here

        lg = mg.addLocalVariable("name", Type.STRING, null, null);
        int name = lg.getIndex();
        il.append(InstructionConst.ACONST_NULL);
        lg.setStart(il.append(new ASTORE(name))); // "name" valid from here

        // trycatch
        InstructionHandle try_start
                = il.append(factory.createFieldAccess("java.lang.System", "out", p_stream, Const.GETSTATIC));

        il.append(new org.apache.bcel.generic.PUSH(cp, "Please enter your name> "));
        il.append(factory.createInvoke("java.io.PrintStream", "print", Type.VOID,
                new Type[]{Type.STRING},
                Const.INVOKEVIRTUAL));
        il.append(new ALOAD(in));
        il.append(factory.createInvoke("java.io.BufferedReader", "readLine",
                Type.STRING, Type.NO_ARGS,
                Const.INVOKEVIRTUAL));
        il.append(new ASTORE(name));

        GOTO g = new GOTO(null);
        InstructionHandle try_end = il.append(g);

        InstructionHandle handler = il.append(InstructionConst.RETURN);
        mg.addExceptionHandler(try_start, try_end, handler, new ObjectType("java.io.IOException"));

        InstructionHandle ih = il.append(factory.createFieldAccess("java.lang.System", "out", p_stream, Const.GETSTATIC));
        g.setTarget(ih);

        il.append(factory.createNew(Type.STRINGBUFFER));
        il.append(InstructionConst.DUP);
        il.append(new PUSH(cp, "Hello, "));
        il.append(factory.createInvoke("java.lang.StringBuffer", "<init>",
                Type.VOID, new Type[]{Type.STRING},
                Const.INVOKESPECIAL));
        il.append(new ALOAD(name));
        il.append(factory.createInvoke("java.lang.StringBuffer", "append",
                Type.STRINGBUFFER, new Type[]{Type.STRING},
                Const.INVOKEVIRTUAL));
        il.append(factory.createInvoke("java.lang.StringBuffer", "toString",
                Type.STRING, Type.NO_ARGS,
                Const.INVOKEVIRTUAL));

        il.append(factory.createInvoke("java.io.PrintStream", "println",
                Type.VOID, new Type[]{Type.STRING},
                Const.INVOKEVIRTUAL));
        il.append(InstructionConst.RETURN);

        //Finalization
        mg.setMaxStack();
        cg.addMethod(mg.getMethod());
        il.dispose(); // Allow instruction handles to be reused
        cg.addEmptyConstructor(ACC_PUBLIC);

        try {
            URL res = ExemploBCEL.class.getResource("");
            File file = new File(res.getFile(), "HelloWorld.class");
            if (!file.exists()) {
                file.createNewFile();
            }
            cg.getJavaClass().dump(file);
            
            Class<?> loadClass = ExemploBCEL.class.getClassLoader().loadClass("exerciciost2.HelloWorld");
            Method method = loadClass.getMethod("main", String[].class);
            method.invoke(null, (Object)new String[0]);
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExemploBCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ExemploBCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ExemploBCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExemploBCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExemploBCEL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ExemploBCEL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
