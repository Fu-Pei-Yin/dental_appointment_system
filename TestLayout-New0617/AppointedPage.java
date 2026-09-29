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
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AppointedPage extends JFrame {
    // 定義標籤、下拉選單和按鈕
    private final JLabel therapyLabel, dentistLabel, appointmentLabel, therapyWarningLabel, dentistWarningLabel, appointmentWarningLabel;
    private final JComboBox<String> therapyComboBox, dentistComboBox, timeComboBox;
    private final JComboBox<Integer> yearComboBox, monthComboBox, dayComboBox;
    private final JButton enterButton, cancelButton;
    private final Calendar calendar = Calendar.getInstance();
    private final int Year = calendar.get(Calendar.YEAR);
    private final Container cp;
    private final String[] object = {"請選擇治療項目", "牙周治療", "根管治療", "矯正", "牙體復形美學", "義齒補綴", "植牙", "製作假牙"};
    private String warning;

    public AppointedPage() {
        setTitle("牙科預約掛號系統");
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

        // 設置治療項目標籤和下拉選單
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
        g.gridwidth = 4;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(therapyComboBox, g);

        therapyWarningLabel = new JLabel("\t");
        therapyWarningLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 2;
        g.gridwidth = 2;
        g.weightx = 0;
        cp.add(therapyWarningLabel, g);

        // 設置指定醫生標籤和下拉選單
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
        g.gridwidth = 4;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(dentistComboBox, g);

        dentistWarningLabel = new JLabel("\t");
        dentistWarningLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 5;
        g.gridwidth = 2;
        g.weightx = 0;
        cp.add(dentistWarningLabel, g);

        // 設置預約時間標籤和下拉選單
        appointmentLabel = new JLabel("預約時間");
        appointmentLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(appointmentLabel, g);

        yearComboBox = new JComboBox<>();
        yearComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 7;
        g.gridwidth = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(yearComboBox, g);
        for (int i = Year; i <= Year + 2; i++) {
            yearComboBox.addItem(i);
        }

        monthComboBox = new JComboBox<>();
        monthComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 7;
        g.gridwidth = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(monthComboBox, g);
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }

        dayComboBox = new JComboBox<>();
        dayComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 3;
        g.gridy = 7;
        g.gridwidth = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(dayComboBox, g);
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }
        timeComboBox = new JComboBox<>();
        timeComboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 4;
        g.gridy = 7;
        g.gridwidth = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.BOTH;
        cp.add(timeComboBox, g);
        for (int i = 10; i < 23; i++) {
            timeComboBox.addItem(i + ":00");
            timeComboBox.addItem(i + ":30");
        }

        appointmentWarningLabel = new JLabel("\t");
        appointmentWarningLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 8;
        g.gridwidth = 2;
        g.weightx = 0;
        cp.add(appointmentWarningLabel, g);

        // 設置確定和取消按鈕
        enterButton = new JButton("確定");
        enterButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 10;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(enterButton, g);

        cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 10;
        g.gridwidth = 1;
        g.weightx = 0;
        cp.add(cancelButton, g);

        // 註冊按鈕事件
        therapyComboBox.addActionListener(new TherapyComboBoxHandler());
        cancelButton.addActionListener(new CancelButtonHandler());
        enterButton.addActionListener(new EnterButtonHandler());
        
        dentistComboBox.addActionListener(new UpdateDayListener());
        yearComboBox.addActionListener(new UpdateDayListener());
        monthComboBox.addActionListener(new UpdateDayListener());
        dayComboBox.addActionListener(new UpdateTimeListener());

        // 註冊鍵盤事件
        therapyComboBox.addKeyListener(new enterKeyAdapter());
        dentistComboBox.addKeyListener(new enterKeyAdapter());
        timeComboBox.addKeyListener(new enterKeyAdapter());
    }

    // 治療項目下拉選單事件處理
    private class TherapyComboBoxHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateDentistComboBox();
        }
    }

   // 設置Enter鍵事件
    private class enterKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                int selectedYear = (int)yearComboBox.getSelectedItem();
                int selectedMonth = (int)monthComboBox.getSelectedItem();
                int selectedDay  = (int)dayComboBox.getSelectedItem();
                warning=Verification.verifyAppointed(selectedYear,selectedMonth, selectedDay);
                System.out.println(warning);
                if(!warning.isEmpty()){
                JOptionPane.showMessageDialog(null, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
                }else{
                    makeAppointment();
                }
            }
        }
    }
    

    // 確定按鈕事件處理
    private class EnterButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          // 設置JOptionPane的字體大小
          Font OPfont = new Font("微軟正黑體", Font.PLAIN, 18);
          UIManager.put("OptionPane.messageFont", OPfont);
          UIManager.put("OptionPane.buttonFont", OPfont);
        
            int selectedYear = (int)yearComboBox.getSelectedItem();
            int selectedMonth = (int)monthComboBox.getSelectedItem();
            int selectedDay  = (int)dayComboBox.getSelectedItem();
            warning=Verification.verifyAppointed(selectedYear,selectedMonth, selectedDay);
            if(!warning.isEmpty()){
              JOptionPane.showMessageDialog(null, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
            }else{
                makeAppointment();
            }
            
        }
    }

   // 取消按鈕事件處理，不將資料存入檔案
    private class CancelButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicInfoPage BIP = new BasicInfoPage();
            BIP.setVisible(true);
            dispose();
        }
    }

   // 更新日期和時間下拉選單事件處理
    private class UpdateDayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            updateDays();
        }
    }
    private class UpdateTimeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            updateTimeComboBox();
        }
    }

   
    //方法：更新日期選項
    private void updateDays(){
        int year =(int)yearComboBox.getSelectedItem();
        int month =(int)monthComboBox.getSelectedItem();
        int daysInMonth;
        dayComboBox.removeAllItems();
        switch(month){
            case 2:
                if((year%4==0&&year%100!=0)||(year%400==0)){
                    daysInMonth=29;
                }else{
                    daysInMonth=28;
                }
                break;
            case 4: case 6: case 9: case 11:
                daysInMonth=30;
                break;
            default:
                daysInMonth=31;
        }
        for(int i=1;i<=daysInMonth;i++){
            dayComboBox.addItem(i);
        }
      }

    // 更新指定醫生下拉選單
    private void updateDentistComboBox() {
        dentistComboBox.removeAllItems();
        try (BufferedReader p = new BufferedReader(new FileReader("Dentist.txt"))) {
            String input;
            while ((input = p.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(input, " ");
                if (st.hasMoreTokens()) {
                    String getName = st.nextToken();
                    while (st.hasMoreTokens()) {
                        String speciality = st.nextToken();
                        if (speciality.equals(therapyComboBox.getSelectedItem())) {
                            dentistComboBox.addItem(getName);
                            break;
                        }
                    }
                }
            }
        } catch (IOException c) {
            System.out.println("資料讀取錯誤");
        }
    }

    // 更新時間下拉選單
    private void updateTimeComboBox() {
        timeComboBox.removeAllItems();
        for (int i = 10; i < 23; i++) {
            timeComboBox.addItem(i + ":00");
            timeComboBox.addItem(i + ":30");
        }
        if (dentistComboBox.getSelectedItem() != null && yearComboBox.getSelectedItem() != null
                && monthComboBox.getSelectedItem() != null && dayComboBox.getSelectedItem() != null) {
            try (BufferedReader p = new BufferedReader(new FileReader("Register.txt"))) {
                String input;
                while ((input = p.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(input, " ");
                    if (st.countTokens() >= 7) {
                        String getId=st.nextToken();//取得身分證
                        String getRegister = st.nextToken();//取得掛號類型
                        st.nextToken();//取得治療項目
                        String getDentist = st.nextToken();//取得指定醫生
                        String getYear = st.nextToken();//取得年分
                        String getMonth = st.nextToken();//取得月份
                        String getDay = st.nextToken();//取得日期
                        String getTime = st.nextToken();//取得時間
                        
                        //System.out.println(getRegister.equals("Appointed"));
                        //System.out.println(getDentist.equals(dentistComboBox.getSelectedItem()));
                        //System.out.println(getYear.equals(String.valueOf(yearComboBox.getSelectedItem())));
                        //System.out.println(getDay.equals(String.valueOf(dayComboBox.getSelectedItem())));
                        
                        if (getRegister.equals("Appointed")
                                && getDentist.equals(dentistComboBox.getSelectedItem())
                                && getYear.equals(String.valueOf(yearComboBox.getSelectedItem()))
                                && getMonth.equals(String.valueOf(monthComboBox.getSelectedItem()))
                                && getDay.equals(String.valueOf(dayComboBox.getSelectedItem()))) {
                            timeComboBox.removeItem(getTime);//刪除已被選取的時間
                        }
                        if(getId.equals(StartPage.patient.getId())//若該病人的時間已預約
                                &&getYear.equals(String.valueOf(yearComboBox.getSelectedItem()))
                                &&getMonth.equals(String.valueOf(monthComboBox.getSelectedItem()))
                                &&getDay.equals(String.valueOf(dayComboBox.getSelectedItem()))){
                            timeComboBox.removeItem(getTime);//刪除已被選取的時間
                        }
                    }
                }
            } catch (IOException c) {
                System.out.println("資料讀取錯誤");
            }
        }
    }

    // 預約掛號
    private void makeAppointment() {
        String selectedTherapy = (String) therapyComboBox.getSelectedItem();
        String selectedDentist = (String) dentistComboBox.getSelectedItem();
        String selectedYear = String.valueOf(yearComboBox.getSelectedItem());
        String selectedMonth =String.valueOf(monthComboBox.getSelectedItem());
        String selectedDay =String.valueOf(dayComboBox.getSelectedItem());
        String selectedTime = (String) timeComboBox.getSelectedItem();
        // 格式化時間
        String appointmentTime = selectedYear + "年" + selectedMonth + "月" + selectedDay + "日" + selectedTime;

        if (selectedTherapy.equals("請選擇治療項目")) {
            warning = "請選擇治療項目";
            therapyWarningLabel.setText(warning);
        } else if (selectedDentist == null) {
            warning = "請選擇指定醫生";
            dentistWarningLabel.setText(warning);
        } else if (selectedYear == null || selectedMonth == null || selectedDay == null || selectedTime == null) {
            warning = "請輸入預約日期";
            appointmentWarningLabel.setText(warning);
        } else {
            try (BufferedReader p = new BufferedReader(new FileReader("Dentist.txt"))) {
                String input;
                while ((input = p.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(input, " ");
                    if (st.hasMoreTokens()) {
                        String getName = st.nextToken();
                        if (getName.equals(selectedDentist)) {
                            try (FileWriter f = new FileWriter("Register.txt", true);
                                    PrintWriter pw = new PrintWriter(f)) {
                                pw.println(StartPage.patient.getId()+ " Appointed " + selectedTherapy + " " + selectedDentist + " "
                                        + selectedYear + " " + selectedMonth + " " + selectedDay + " " + selectedTime);
                            } catch (IOException c) {
                                System.out.println("檔案開啟錯誤");
                            }
                            break;
                        }
                    }
                }
            } catch (IOException c) {
                System.out.println("資料讀取錯誤");
            }
            dispose();
            AppointedInfoPage AIP = new AppointedInfoPage(appointmentTime, selectedDentist, selectedTherapy);  // 接收參數 時間 醫生 項目
            AIP.setVisible(true);
        }
    }
    public void modify(){
        cp.remove(cancelButton);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
