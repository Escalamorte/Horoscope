import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ErrorForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel errorIcon;
    private JLabel errorField;

    ErrorForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Error!");

        String errorIconDir = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\error.png";
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(errorIconDir).getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
        errorIcon.setIcon(imageIcon);
        errorField.setText("Check value!");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
    }

    void run() {
        ErrorForm dialog = new ErrorForm();
        dialog.pack();
        dialog.setVisible(true);
    }
}
