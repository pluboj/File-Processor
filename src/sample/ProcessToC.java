package sample;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessToC {

    private File file;

    public ProcessToC(File file) {
        this.file = file;
    }

    public void generateToC() {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                String modifiedText = "'"+text+"',";
                System.out.println(modifiedText);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
        }catch (IOException ex) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
