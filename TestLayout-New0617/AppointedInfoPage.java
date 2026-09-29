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

public class AppointedInfoPage extends JFrame {
    // 定義標籤、文本框和按鈕
    private final JLabel Label, therapyLabel, dentistLabel, timeLabel, chargeLabel;
    private final JTextField selectedTherapyOutput, selectedDentistOutput, selectedTimeOutput, chargeOutput;
    private final JButton enterButton, modifyButton, deleteButton;
    private final Container cp;
    private final AppointedDentist appointedDentist = new AppointedDentist();

    public AppointedInfoPage(String date, String dentist, String therapy) { // 接收參數：時間、醫生、項目
        setTitle("預約成功");
        // 設置視窗為全螢幕並且不可縮小
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        setMinimumSize(new Dimension(screenWidth,screenHeight));

        // 創建主面板並設置為 GridLayout
        cp = getContentPane();
        cp.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        // 創建並設置標籤和文本框
        therapyLabel = new JLabel("治療項目");
        therapyLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        cp.add(therapyLabel, g);

        selectedTherapyOutput = new JTextField(therapy);
        selectedTherapyOutput.setEditable(false);
        selectedTherapyOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth = 4;
        g.fill = GridBagConstraints.BOTH;
        cp.add(selectedTherapyOutput, g);

        dentistLabel = new JLabel("指定醫生");
        dentistLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        cp.add(dentistLabel, g);

        selectedDentistOutput = new JTextField(dentist);
        selectedDentistOutput.setEditable(false);
        selectedDentistOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 4;
        cp.add(selectedDentistOutput, g);

        timeLabel = new JLabel("預約時間");
        timeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        cp.add(timeLabel, g);

        selectedTimeOutput = new JTextField(date);
        selectedTimeOutput.setEditable(false);
        selectedTimeOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 4;
        cp.add(selectedTimeOutput, g);

        chargeLabel = new JLabel("費用(NT$)");
        chargeLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 1;
        cp.add(chargeLabel, g);

        chargeOutput = new JTextField(String.valueOf(StartPage.patient.calculateCharge()));
        chargeOutput.setEditable(false);
        chargeOutput.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 1;
        g.gridy = 8;
        g.gridwidth = 4;
        cp.add(chargeOutput, g);

        Label = new JLabel("請提前至前台報到");
        Label.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 0;
        g.gridy = 9;
        g.gridwidth = 3;
        cp.add(Label, g);

        enterButton = new JButton("確定");
        enterButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 1;
        g.gridy = 11;
        g.gridwidth = 1;
        cp.add(enterButton, g);

        modifyButton = new JButton("修改");
        modifyButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 2;
        g.gridy = 11;
        g.gridwidth = 1;
        cp.add(modifyButton, g);

        deleteButton = new JButton("刪除");
        deleteButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30));
        g.gridx = 3;
        g.gridy = 11;
        g.gridwidth = 1;
        cp.add(deleteButton, g);

        // 註冊按鈕事件
        enterButton.addActionListener(new EnterButtonHandler());
        modifyButton.addActionListener(new ModifyButtonHandler());
        deleteButton.addActionListener(new DeleteButtonHandler());

        // 從檔案讀取註冊資料並更新介面
        try (BufferedReader p = new BufferedReader(new FileReader("Register.txt"))) {
            String input;
            while ((input = p.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(input, " ");
                if (st.hasMoreTokens()) {
                    String getID = st.nextToken();
                    if (getID.equals(StartPage.patient.getId())) {
                        String getRegister = st.nextToken();
                        if (getRegister.equals("Appointed")) {
                            String getTherapy = st.nextToken();//治療項目
                            String getDentist = st.nextToken();//指定醫生
                            String getYear = st.nextToken();//年份
                            String getMonth = st.nextToken();//月份
                            String getDay = st.nextToken();//日期
                            String getTime = st.nextToken();//時間
                            String getDate = getYear + "年" + getMonth + "月" + getDay + "日" + getTime; // 格式化
                            if (therapy.equals(getTherapy) && dentist.equals(getDentist) && date.equals(getDate)) {
                                selectedTherapyOutput.setText(getTherapy);
                                selectedDentistOutput.setText(getDentist);
                                selectedTimeOutput.setText(getDate);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("資料讀取錯誤");
        }
    }

    // 確定按鈕事件處理器
    private class EnterButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicInfoPage BIP = new BasicInfoPage();
            BIP.setVisible(true);
            dispose();
        }
    }

    // 修改按鈕事件處理器
    private class ModifyButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String sTO = selectedTherapyOutput.getText();
            String sDO = selectedDentistOutput.getText();
            String sTimeO = selectedTimeOutput.getText();

            // 使用預約醫生類別的修改方法，刪除當前資料並開啟預約畫面
            appointedDentist.modifyAppointedPatient(StartPage.patient.getId(), sTO, sDO, sTimeO);
            
            // 關閉視窗
            dispose();
        }
    }

    // 刪除按鈕事件處理器
    private class DeleteButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String sTO = selectedTherapyOutput.getText();
            String sDO = selectedDentistOutput.getText();
            String sTimeO = selectedTimeOutput.getText();
            // 使用預約醫生類別的刪除方法，刪除當前資料並開啟主要畫面
            appointedDentist.deleteAppointedPatient(StartPage.patient.getId(), sTO, sDO, sTimeO);

            // 關閉視窗
            dispose();
        }
    }
}
