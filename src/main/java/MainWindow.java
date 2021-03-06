import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;

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

        OKButton.addActionListener(e -> onOK());
    }

    private void buttonActivator() {
        if (!dateField.getText().contains(" ")) {
            OKButton.setEnabled(true);
        } else {
            OKButton.setEnabled(false);
        }
    }

    private void onOK() {
        int day = Integer.parseInt(dateField.getText().substring(0, 2)); //получение введенного дня
        int month = Integer.parseInt(dateField.getText().substring(3, 5)); //получение введенного месяца
        int year = Integer.parseInt(dateField.getText().substring(6, 10)); //получение введенного года

        if (checkValue(day, month, year)){
            String sign = new Horoscope().spotSign(month, day);

            String iconDir = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\" + sign + ".png";
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(iconDir).getImage().getScaledInstance(80,80, Image.SCALE_DEFAULT));
            signIcon.setIcon(imageIcon);

            String[] singTitle = DataFile.getSignTitle(sign).split(";");
            singLabel.setText("<html>" + singTitle[0] + "<br>" + singTitle[1] + "</br></html>");

            LocalDate date = LocalDate.now();
            resultLabel.setText("<html><body style='width: 370px'>" + date + " | " + DataFile.getSingText(sign) + "</body></html>");

            resultPanel.setVisible(true);
            setSize(new Dimension(870, 470));
        } else {
            new ErrorForm().run();
        }
    }

    private boolean checkValue(int day, int month, int year) {
        boolean flag = true;
        if (year < 1 || month < 1 || month > 12) {
            flag = false;
        } else {
            YearMonth yearMonth = YearMonth.of(year, month);
            if (day < 1 || day > yearMonth.lengthOfMonth()) {
                flag = false;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        MainWindow dialog = new MainWindow();
        dialog.pack();
        new DataFile().start();
        dialog.setVisible(true);
        System.exit(0);
    }
}