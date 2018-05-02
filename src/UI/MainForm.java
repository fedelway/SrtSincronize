package UI;

import Model.SimpleFileFilter;
import Model.SrtFile;
import Model.SrtParser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainForm{

    private JButton btnLoad;
    private JPanel panel;
    private JButton btnSave;
    private JTextField txtSincronization;
    private JLabel lblSubName;
    private JCheckBox chkWriteBOM;
    private SrtFile srt;

    public MainForm() {
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSrt();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSrt();
            }
        });
    }

    public void show(){
        JFrame frame = new JFrame("Srt Sincronize");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void saveSrt(){
        Long sync;

        if(srt == null){
            JOptionPane.showMessageDialog(panel, "Primero debe cargar un archivo");
            return;
        }

        try{
            sync = Long.parseLong(txtSincronization.getText());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(panel, "Debe introducir un número");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new SimpleFileFilter(".srt","Subtitle File (.srt)");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        int ret = chooser.showSaveDialog(panel);

        if (ret == JFileChooser.APPROVE_OPTION) {

            try{
                srt.writeToFile(chooser.getSelectedFile(),sync, chkWriteBOM.isSelected());
            }catch(IOException ex){
                JOptionPane.showMessageDialog(panel, "Error al guardar archivo");
            }
        }

    }

    private void loadSrt(){

        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new SimpleFileFilter(".srt","Subtitle File (.srt)");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);

        int ret = chooser.showOpenDialog(panel);

        if( ret == JFileChooser.APPROVE_OPTION )
        {
            File file = chooser.getSelectedFile();

            SrtParser parser = new SrtParser();

            try{
                srt = parser.parseFile(file);

                //Si esto no fallo entonces se parseo correctamente
                lblSubName.setText(file.getAbsoluteFile().getName());
            }catch(IOException ex){
                JOptionPane.showMessageDialog(panel, "Error al intentar leer el archivo");
            }
        }
    }

}
