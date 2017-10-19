package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Input: text file with variables copied from shell.spss
* Output: new text file with variables formatted for pasting
* into CopyRecallData for chart studies
* */

public class ProcessCopyRecall {

    private File file;

    public ProcessCopyRecall(File file) {
        this.file = file;
    }

    public void generateCopyRecallVars() {
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
            Logger.getLogger(ProcessCopyRecall.class.getName()).log(Level.SEVERE, null, e);
        }catch (IOException e) {
            Logger.getLogger(ProcessCopyRecall.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                Logger.getLogger(ProcessCopyRecall.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(ProcessCopyRecall.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
