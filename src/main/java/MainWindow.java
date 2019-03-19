import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;

public class MainWindow extends JDialog {
    private JPanel contentPane;
    private JFormattedTextField dateField;
    private JButton OKButton;
    private JLabel resultLabel;
    private JLabel signIcon;
    private JPanel resultPanel;
    private JLabel singLabel;

    private MainWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(OKButton);
        setTitle("Horoscope v2");
        resultPanel.setVisible(false);
        OKButton.setEnabled(false);

        try{
            //формат даты для поля
            MaskFormatter maskFormatter = new MaskFormatter("##.##.####");
            maskFormatter.install(dateField);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        dateField.addKeyListener(new KeyListener() {
            //получение данных по нажатию на Enter

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !dateField.getText().contains(" ")) {
                    onOK();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dateField.getDocument().addDocumentListener(new DocumentListener() {

            //активация кнопки после ввода
            @Override
            public void insertUpdate(DocumentEvent e) {
                buttonActivator();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buttonActivator();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buttonActivator();
            }
        });

        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void buttonActivator() {
        if (!dateField.getText().contains(" ")) {
            OKButton.setEnabled(true);
        } else {
            OKButton.setEnabled(false);
        }
    }

    private void onOK() {
        resultPanel.setVisible(true);
        String sign = new Horoscope().spotSign(12, 22);

        String iconDir = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\" + sign + ".png";
        System.out.println(iconDir);
        ImageIcon imageIcon = new ImageIcon(iconDir);
        signIcon.setIcon(imageIcon);

        singLabel.setText(sign);
        resultLabel.setText(DataFile.getFileData(sign));
        setSize(800, 400);
    }

    public static void main(String[] args) {
        DataFile.getOnlineData();
        MainWindow dialog = new MainWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}