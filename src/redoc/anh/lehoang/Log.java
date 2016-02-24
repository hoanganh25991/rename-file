package redoc.anh.lehoang;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;

public class Log extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    public int recursive_depth = 0;
    public String filePath = "";

    public Log() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //when user copy file-path, get this one, set it to textfield2
        TextTransfer textTransfer = new TextTransfer();
        String filePath = textTransfer.getClipboardContents();
        textField2.setText(filePath);
    }

    private void onOK() {
    // add your code here
        recursive_depth = Integer.parseInt(textField1.getText());
        filePath = textField2.getText();
        RenameFileInDirectory renameFileInDirectory = new RenameFileInDirectory(this.filePath, recursive_depth);
        renameFileInDirectory.run();
        dispose();
    }

    private void onCancel() {
    // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Log dialog = new Log();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
