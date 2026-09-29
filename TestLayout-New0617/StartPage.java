import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StartPage extends JFrame {
    // 初始化
    private final JLabel clinicTitleLabel, advTitleLabel, IDLabel, secretLabel, IDWarnInfoLabel, secretWarnInfoLabel;
    private final JTextField IDInput;//輸入身分證
    private final JCheckBox showPasswordCheckBox;//顯示密碼
    private final JPasswordField secretInput; // 密碼用JPasswordField物件
    private final JButton signInButton, signUpButton, forgetSecretButton;
    private final Container cp;
    public static Patient patient;
    private String IDWarning = ""; // 存放警告訊息
    private String secretWarning = ""; // 存放警告訊息
    public ArrayList<Dentist> dentists = new ArrayList<>(); // 存放牙醫資料的列表 泛型Dentist

    public StartPage() {
        setTitle("牙科預約掛號系統"); // 設定視窗標題
        setExtendedState(JFrame.MAXIMIZED_BOTH);//設置為全螢幕
        setResizable(false);//設置為不可縮小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//取得電腦視窗大小
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        setMinimumSize(new Dimension(screenWidth, screenHeight));//將視窗大小最小值設為電腦視窗

        // 創建主面板並設置為 GridBagLayout
        cp = getContentPane();
        cp.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);//每個物件的間隔
        g.fill = GridBagConstraints.HORIZONTAL;//水平填滿

        // 左側面板用於放登錄介面相關資訊
        JPanel leftPanel = new JPanel(new GridBagLayout());

        // LOGO
        ImageIcon logoIcon = new ImageIcon(new ImageIcon("LOGO.png").getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        //默認圖像的縮放算法
        JLabel logoLabel = new JLabel(logoIcon);
        g.gridx = 0;//座標
        g.gridy = 0;//座標
        g.gridwidth = 3;//佔據格數
        g.weightx = 3;//佔據空餘空間的能力
        g.anchor = GridBagConstraints.CENTER; // 對其置中
        leftPanel.add(logoLabel, g);

        // 設置大標題
        clinicTitleLabel = new JLabel("OOO牙醫診所");
        clinicTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 60)); // 設置大標題字型、粗細和大小
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth = 3;
        g.weightx = 0;
        g.anchor = GridBagConstraints.CENTER; // 對其置中
        
        leftPanel.add(clinicTitleLabel, g);

        // 身分證
        IDLabel = new JLabel("身分證");
        IDLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 1;
        g.weightx = 0;
        g.anchor = GridBagConstraints.WEST; // 左對齊
        leftPanel.add(IDLabel, g);
        IDInput = new JTextField(15);
        IDInput.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 7;
        g.gridwidth = 2;
        g.weightx = 0;
        leftPanel.add(IDInput, g);

        // 密碼
        secretLabel = new JLabel("密碼");
        secretLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 9;
        g.gridwidth = 1;
        g.weightx = 0;
        leftPanel.add(secretLabel, g);
        secretInput = new JPasswordField(15);
        secretInput.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 10;
        g.gridwidth = 2;
        g.weightx = 0;
        leftPanel.add(secretInput, g);
        showPasswordCheckBox = new JCheckBox("顯示密碼");
        showPasswordCheckBox.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 9;
        g.gridwidth = 1;
        g.weightx = 0;
        leftPanel.add(showPasswordCheckBox, g);

        forgetSecretButton = new JButton("忘記密碼");
        forgetSecretButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 3;
        g.gridy = 9;
        g.gridwidth = 1;
        g.weightx = 0;
        leftPanel.add(forgetSecretButton, g);
        signInButton = new JButton("登入");
        signInButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 12;
        g.gridwidth = 1;
        g.weightx = 0;
        leftPanel.add(signInButton, g);
        signUpButton = new JButton("註冊");
        signUpButton.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 3;
        g.gridy = 12;
        g.gridwidth = 1;
        g.weightx = 0;
        leftPanel.add(signUpButton, g);

        // 初始化警告訊息標籤
        IDWarnInfoLabel = new JLabel("");
        IDWarnInfoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 8;
        g.gridwidth = 3;
        g.weightx = 0;
        leftPanel.add(IDWarnInfoLabel, g);
        secretWarnInfoLabel = new JLabel("");
        secretWarnInfoLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        g.gridx = 2;
        g.gridy = 11;
        g.gridwidth = 3;
        g.weightx = 0;
        leftPanel.add(secretWarnInfoLabel, g);

        // 右側面板用於放置「就診注意事項」相關文字訊息
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        advTitleLabel = new JLabel("就診注意事項");
        advTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 40)); // 設置標題字型、粗細和大小

        JTextArea medicalAdvice = new JTextArea("1. 預約請提前到達診所報到。\n"
                + "2. 請攜帶健保卡或相關身分證明。\n"
                + "3. 就診前請事先刷牙。\n"
                + "4. 如有特殊需求，請提前告知醫護人員。\n"
                + "5. 請遵守醫生和診所的相關規定。\n");
        medicalAdvice.setEditable(false); // 禁止編輯
        medicalAdvice.setFont(new Font("微軟正黑體", Font.PLAIN, 30)); // 設置字型、粗細和大小
        JScrollPane scrollPane = new JScrollPane(medicalAdvice);

        rightPanel.add(advTitleLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // 將左右兩個面板加到主面板中
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.BOTH;
        cp.add(leftPanel, g);

        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 1;
        cp.add(rightPanel, g);

        // 註冊按鈕事件
        signInButton.addActionListener(new SignInButtonHandler());
        signUpButton.addActionListener(new SignUpButtonHandler());
        forgetSecretButton.addActionListener(new ForgetSecretButtonHandler());
        showPasswordCheckBox.addActionListener(new PassWordHandler());

        // 註冊鍵盤事件
        IDInput.addKeyListener(new EnterKeyAdapter());
        secretInput.addKeyListener(new EnterKeyAdapter());
    }

    // 鍵盤事件
    private class EnterKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                authenticateUser(); // 按下Enter鍵時驗證使用者
            }
        }
    }

    // 顯示密碼按鈕
    private class PassWordHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (showPasswordCheckBox.isSelected()) {
                secretInput.setEchoChar((char) 0); // 顯示密碼
            } else {
                secretInput.setEchoChar('*'); // 隱藏密碼
            }
        }
    }

    // 登入按鈕
    private class SignInButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            authenticateUser(); // 按下登入按鈕時驗證使用者
        }
    }

    // 註冊按鈕
    private class SignUpButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SearchPage.modifyData=false;//設定為初次註冊帳號
          System.out.println("StartPage："+SearchPage.modifyData);
            SignUpPage SUP = new SignUpPage(); // 進入註冊頁面
            SUP.setVisible(true);
            dispose(); // 關閉本頁
        }
    }

    // 忘記密碼按鈕
    private class ForgetSecretButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            AuthenticationPage AP = new AuthenticationPage(); // 進入驗證頁面
            AP.setVisible(true);
        }
    }

    // 驗證使用者並登入開啟預約頁面
    private void authenticateUser() {
        try (BufferedReader p = new BufferedReader(new FileReader("Patient.txt"))) {
            String input;
            StringTokenizer st;
            IDWarnInfoLabel.setText(IDWarning); // 設置ID警告訊息
            secretWarnInfoLabel.setText(secretWarning); // 設置密碼警告訊息
            while ((input = p.readLine()) != null) {
                st = new StringTokenizer(input);
                String identity = st.nextToken(); // 身分
                String id = st.nextToken();//身分證
                String secret = st.nextToken();//密碼
                String name = st.nextToken();//姓名
                String birth = st.nextToken();//生日
                String address = st.nextToken();//住址
                String tel = st.nextToken();//電話
                Detail detail = new Detail();//詳細問題
                String detailInfo;
                StringTokenizer newSt;
                if (IDInput.getText().isEmpty()) {
                    IDWarning = "請輸入身分證"; // 若ID輸入為空則提示
                    IDWarnInfoLabel.setText(IDWarning);
                } else {
                    IDWarning = Verification.verifyID(IDInput.getText()); // 驗證ID是否有格式錯誤
                    IDWarnInfoLabel.setText(IDWarning);
                    if (IDWarning.equals("")) { // 身分證沒有錯誤訊息
                        if (id.equals(IDInput.getText())) {
                            if (secretInput.getText().isEmpty()) {
                                secretWarning = "請輸入密碼"; // 若密碼輸入為空則提示
                                secretWarnInfoLabel.setText(secretWarning);
                            } else {
                                secretWarning = Verification.verifySecret(secretInput.getText()); // 驗證密碼
                                secretWarnInfoLabel.setText(secretWarning);
                                
                                System.out.println("check1");
                                
                                if (secretWarning.equals("")) { // 密碼沒有錯誤訊息
                                  
                                  System.out.println("check1");
                                  
                                    if (secret.equals(secretInput.getText())) {
                                        if (identity.equals("s")) { // 判斷使用者身分
                                            while (st.hasMoreTokens()) {
                                                detailInfo = st.nextToken();
                                                
                                                System.out.println("check1.1");
                                                
                                                newSt = new StringTokenizer(detailInfo, ":");
                                                String key = newSt.nextToken();
                                                Boolean value = newSt.nextToken().equals("yes") ? true : false;
                                                detail.setDetail(key, value);
                                            }
                                            
                                            System.out.println("check1.2");
                                            
                                            patient = new SpecialPatient(name, id, address, tel, birth, secret, detail);
                                            
                                            System.out.println("check1"+patient);
                                        } else {
                                          
                                            while (st.hasMoreTokens()) {
                                                detailInfo = st.nextToken();
                                                newSt = new StringTokenizer(detailInfo, ":");
                                                String key = newSt.nextToken();
                                                Boolean value = newSt.nextToken().equals("yes") ? true : false;
                                                detail.setDetail(key, value);
                                            }
                                            patient = new normalPatient(name, id, address, tel, birth, secret, detail);
                                        }
                                        BasicInfoPage BIP = new BasicInfoPage();
                                        BIP.setVisible(true);
                                        dispose();
                                        break;
                                    } else {
                                        secretWarning = "密碼輸入錯誤"; // 密碼錯誤提示
                                        secretWarnInfoLabel.setText(secretWarning);
                                    }
                                }
                            }
                        } else {
                            IDWarning = "身分證輸入錯誤"; // 身分證錯誤提示
                            IDWarnInfoLabel.setText(IDWarning);
                            continue;
                        }
                    }
                    break;
                }
            }
        } catch (IOException c) {
            System.out.println("資料讀取錯誤"); // 讀取檔案錯誤提示
            System.exit(-2);
        }
    }

    public static void main(String[] args) {
        StartPage sp = new StartPage(); // 建立開始頁面物件
        sp.setVisible(true); // 顯示開始頁面
    }
}
