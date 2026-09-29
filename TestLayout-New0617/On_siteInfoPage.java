import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class On_siteInfoPage extends JFrame {
    private final JLabel therapyLabel, dentistLabel, numberLabel, chargeLabel;
    private final JTextField therapyOutput, dentistOutput, numberOutput, chargeOutput;
    private final JButton enterButton, modifyButton, deleteButton;
    private final Container cp;
    private On_siteDentist on_siteDentist = new On_siteDentist();

    public On_siteInfoPage(String therapy, String dentist) {
        setTitle("掛號成功");
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

        // 設置治療項目標籤和輸出
        therapyLabel = new JLabel("治療項目");
        therapyLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(therapyLabel, g);

        therapyOutput = new JTextField(therapy);
        therapyOutput.setEditable(false);
        therapyOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 3;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(therapyOutput, g);

        // 設置指定醫生標籤和輸出
        dentistLabel = new JLabel("指定醫生");
        dentistLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(dentistLabel, g);

        dentistOutput = new JTextField(dentist);
        dentistOutput.setEditable(false);
        dentistOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 3;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(dentistOutput, g);

        // 設置看診順序標籤和輸出
        numberLabel = new JLabel("看診順序");
        numberLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(numberLabel, g);

        numberOutput = new JTextField();
        numberOutput.setEditable(false);
        numberOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 3;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(numberOutput, g);

        // 設置費用標籤和輸出
        chargeLabel = new JLabel("費用(NT$)");
        chargeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(chargeLabel, g);

        chargeOutput = new JTextField(String.valueOf(StartPage.patient.calculateCharge()));
        chargeOutput.setEditable(false);
        chargeOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 7;
        g.gridwidth = 3;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(chargeOutput, g);

        // 設置按鈕
        enterButton = new JButton("確定");
        enterButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 9;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(enterButton, g);

        modifyButton = new JButton("修改");
        modifyButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 9;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(modifyButton, g);

        deleteButton = new JButton("刪除");
        deleteButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 3;
        g.gridy = 9;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(deleteButton, g);

        // 計算看診順序
        int number = 0;
        try (BufferedReader p = new BufferedReader(new FileReader("Register.txt"))) {
            String input;
            while ((input = p.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(input, " ");
                if (st.hasMoreTokens()) {
                    st.nextToken();
                    String getRegister=st.nextToken();
                    if (getRegister.equals("On_site")) {
                        number += 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("資料讀取錯誤");
        }
        numberOutput.setText(String.valueOf(number));

        

        // 註冊按鈕事件
        enterButton.addActionListener(new EnterButtonHandler());
        modifyButton.addActionListener(new ModifyButtonHandler());
        deleteButton.addActionListener(new DeleteButtonHandler());
    }

    // 確定按鈕處理程序
    private class EnterButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicInfoPage BIP = new BasicInfoPage();
            BIP.setVisible(true);
            dispose();
        }
    }

    // 修改按鈕處理程序
    private class ModifyButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            on_siteDentist.modifyOn_sitePatient(StartPage.patient.getId(), therapyOutput.getText(), dentistOutput.getText()); // 傳入參數 ID 項目 醫生
            dispose();
        }
    }

    // 刪除按鈕處理程序
    private class DeleteButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            on_siteDentist.deleteOn_sitePatient(StartPage.patient.getId(), therapyOutput.getText(), dentistOutput.getText()); // 傳入參數 ID 項目 醫生
            dispose();
        }
    }
}
