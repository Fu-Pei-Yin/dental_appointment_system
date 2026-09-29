import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class On_sitePage extends JFrame {
    private final JLabel therapyLabel, dentistLabel, therapyWarningLabel, dentistWarningLabel;
    private JComboBox<String> therapyComboBox, dentistComboBox;
    private final JButton enterButton, cancelButton;
    private final Container cp;
    private final String[] object = {"請選擇治療項目", "檢查", "洗牙", "補蛀牙", "拔牙", "牙痛"};
    private String warning;

    public On_sitePage() {
        setTitle("牙科現場掛號系統");
        // 設置視窗為全螢幕並且不可縮小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        setMinimumSize(new Dimension(screenWidth,screenHeight));
        

        // 創建主面板並設置為 GridBagLayout
        cp = getContentPane();
        cp.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        therapyLabel = new JLabel("治療項目");
        therapyLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(therapyLabel, g);

        therapyComboBox = new JComboBox<>(object);
        therapyComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 3;
        g.weightx = 0;
        cp.add(therapyComboBox, g);

        therapyWarningLabel = new JLabel("\t");
        therapyWarningLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 2;
        g.gridwidth = 2;
        g.weightx = 0;
        cp.add(therapyWarningLabel, g);

        dentistLabel = new JLabel("指定醫生");
        dentistLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(dentistLabel, g);

        dentistComboBox = new JComboBox<>();
        dentistComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 3;
        g.weightx = 0;
        cp.add(dentistComboBox, g);

        dentistWarningLabel = new JLabel("\t");
        dentistWarningLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 5;
        g.gridwidth = 2;
        g.weightx = 0;
        cp.add(dentistWarningLabel, g);

        enterButton = new JButton("確定");
        enterButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 7;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(enterButton, g);

        cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 7;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(cancelButton, g);

        try (BufferedReader p = new BufferedReader(new FileReader("Register.txt"))) {
            String input;
            while ((input = p.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(input, " ");
                if (st.hasMoreTokens()) {
                    String getID = st.nextToken();
                    st.nextToken();
                    String getTherapy = st.nextToken();
                    st.nextToken();
                    if (getID.equals(StartPage.patient.getId())) {
                        therapyComboBox.removeItem(getTherapy);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("資料讀取錯誤");
        }

        // 註冊按鈕事件
        therapyComboBox.addActionListener(new TherapyComboBoxHandler());
        enterButton.addActionListener(new EnterButtonHandler());
        cancelButton.addActionListener(new CancelButtonHandler());

        //註冊鍵盤事件
        therapyComboBox.addKeyListener(new enterKeyAdapter());
        dentistComboBox.addKeyListener(new enterKeyAdapter());
    }

    // 治療項目選擇處理程序
    private class TherapyComboBoxHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dentistComboBox.removeAllItems();
            try (BufferedReader p = new BufferedReader(new FileReader("Dentist.txt"))) {
                String input;
                while ((input = p.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(input, " ");
                    String getName = st.nextToken();
                    while (st.hasMoreTokens()) {
                        String speciality = st.nextToken();
                        if (speciality.equals(therapyComboBox.getSelectedItem())) {
                            dentistComboBox.addItem(getName);
                            break;
                        }
                    }
                }
            } catch (IOException c) {
                System.out.println("資料讀取錯誤");
                System.exit(-2);
            }
        }
    }

    // 設置Enter鍵事件
    private class enterKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                makeOn_site();
            }
        }
    }

    // 確定按鈕處理程序
    private class EnterButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            makeOn_site();
        }
    }

    // 取消按鈕處理程序
    private class CancelButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicInfoPage BIP = new BasicInfoPage();
            BIP.setVisible(true);
            dispose();
        }
    }

    private void makeOn_site(){
        String selectedTherapy = (String) therapyComboBox.getSelectedItem();
            String selectedDentist = (String) dentistComboBox.getSelectedItem();
            if (selectedTherapy.equals("請選擇治療項目")) {
                warning = "請選擇治療項目";
                therapyWarningLabel.setText(warning);
            } else {
                if (selectedDentist == null) {
                    warning = "請選擇指定醫生";
                    dentistWarningLabel.setText(warning);
                } else {
                    try (BufferedReader p = new BufferedReader(new FileReader("Dentist.txt"))) {
                        String input;
                        while ((input = p.readLine()) != null) {
                            StringTokenizer st = new StringTokenizer(input, " ");
                            String getName = st.nextToken();
                            if (getName.equals(selectedDentist)) {
                                try (FileWriter f = new FileWriter("Register.txt", true)) {
                                    PrintWriter pw = new PrintWriter(f);
                                    pw.println(StartPage.patient.getId() + " On_site " + selectedTherapy + " " + selectedDentist + " ");
                                    pw.close();
                                } catch (IOException c) {
                                    System.out.println("檔案開啟錯誤");
                                    System.exit(-1);
                                }
                                break;
                            }
                        }
                    } catch (IOException c) {
                        System.out.println("資料讀取錯誤");
                        System.exit(-2);
                    }
                    dispose();
                    On_siteInfoPage OIP = new On_siteInfoPage(selectedTherapy, selectedDentist);
                    OIP.setVisible(true);
                }
            }
    }
    public void modify(){
        cp.remove(cancelButton);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
