package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessToC {

    private File file;

    public ProcessToC(File file) {
        this.file = file;
    }

    public void generateToC() {
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        int lineBreak = 10;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            int counter = 0;
            while ((text = bufferedReader.readLine()) != null) {
                counter++;
                sb.append("'"+text+"',");
                if (counter % lineBreak == 0) {
                    sb.append("\r\n");
                }
            }
            createFile(sb);
        } catch (FileNotFoundException e) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
        }catch (IOException e) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private void createFile(StringBuilder sb) {
        File path = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path+"/toc.txt"), StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        }
        catch (IOException e) {
            Logger.getLogger(ProcessToC.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
