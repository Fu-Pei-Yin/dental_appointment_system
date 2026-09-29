import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchPage extends JFrame {
    private final JLabel nameTitleLabel, nameLabel, IDLabel, addressLabel, TELLabel, birthLabel, passwordLabel, detailLabel, appointmentLabel;
    private final JTextField nameOutput, IDOutput, addressOutput, TELOutput, birthOutput, passwordOutput;
    private final Container cp;
    private final JButton modifyButton, backButton;
    // 病人資料
    private final ArrayList<ArrayList<String>> regestRecords = new ArrayList<>();// 存病人約診紀錄
    static boolean modifyData = false;//用於調整資料
    public SearchPage() {
        setTitle("搜尋資料");
        // 設置視窗為全螢幕並且不可縮小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        setMinimumSize(new Dimension(screenWidth,screenHeight));

        // 創建主面板並設置為 BorderLayout
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel centerPan = new JPanel(new GridLayout(1, 2));
        // 取得病人資料
        loadPatientRegest("Register.txt", StartPage.patient.getId());


        
        // 左側面版用於放登錄介面相關資訊
        JPanel leftPanel = new JPanel(new GridLayout(1,2));
        JPanel l1Panel = new JPanel(new GridBagLayout());
        JPanel l2Panel = new JPanel(new BorderLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.NORTHEAST;

        // 統一字體
        Font labelFont = new Font("微軟正黑體", Font.PLAIN, 24);

        nameTitleLabel = new JLabel("個人資料");
        nameTitleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30)); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.anchor = GridBagConstraints.NORTHEAST; // 左對齊
        l1Panel.add(nameTitleLabel, g);

        nameLabel = new JLabel("姓名");
        nameLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        l1Panel.add(nameLabel, g);
        nameOutput = new JTextField(StartPage.patient.getName(),60);
        nameOutput.setEditable(false);// 設置為不可編輯
        nameOutput.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 2;
        l1Panel.add(nameOutput, g);

        IDLabel = new JLabel("身分證");
        IDLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        l1Panel.add(IDLabel, g);
        IDOutput = new JTextField(StartPage.patient.getId(),60);
        IDOutput.setFont(labelFont); // 設置字型、粗細和大小
        IDOutput.setEditable(false);// 設置為不可編輯
        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        l1Panel.add(IDOutput, g);

        addressLabel = new JLabel("住址");
        addressLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        l1Panel.add(addressLabel, g);

        addressOutput = new JTextField(StartPage.patient.getAddress(), 60);
        addressOutput.setFont(labelFont); // 設置字型、粗細和大小
        addressOutput.setEditable(false); // 設置為不可編輯

        JScrollPane addressScrollPane = new JScrollPane(addressOutput);
        addressScrollPane.setPreferredSize(new Dimension(200, 60)); // 設置滾動面板的偏好尺寸
        addressScrollPane.setMinimumSize(new Dimension(200, 60)); // 設置滾動面板的最小尺寸
        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 2;
        l1Panel.add(addressScrollPane, g);

        TELLabel = new JLabel("電話號碼");
        TELLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 1;
        l1Panel.add(TELLabel, g);
        TELOutput = new JTextField(StartPage.patient.getTel(),60);
        TELOutput.setFont(labelFont); // 設置字型、粗細和大小
        TELOutput.setEditable(false);// 設置為不可編輯
        g.gridx = 1;
        g.gridy = 8;
        g.gridwidth = 2;
        l1Panel.add(TELOutput, g);

        birthLabel = new JLabel("生日");
        birthLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 9;
        g.gridwidth = 1;
        l1Panel.add(birthLabel, g);
        birthOutput = new JTextField(StartPage.patient.getBirthDate(),60);
        birthOutput.setFont(labelFont); // 設置字型、粗細和大小
        birthOutput.setEditable(false);// 設置為不可編輯
        g.gridx = 1;
        g.gridy = 10;
        g.gridwidth = 2;
        l1Panel.add(birthOutput, g);

        passwordLabel = new JLabel("密碼");
        passwordLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx = 0;
        g.gridy = 11;
        g.gridwidth = 1;
        l1Panel.add(passwordLabel, g);
        
        passwordOutput = new JTextField(StartPage.patient.getSecret(),60);
        passwordOutput.setFont(labelFont); // 設置字型、粗細和大小
        passwordOutput.setEditable(false);// 設置為不可編輯
        g.gridx = 1;
        g.gridy = 12;
        g.gridwidth = 2;
        l1Panel.add(passwordOutput, g);

        nameOutput.setMinimumSize(new Dimension(200, 60));//設定文字框最小值
        IDOutput.setMinimumSize(new Dimension(200, 60));//設定文字框最小值
        TELOutput.setMinimumSize(new Dimension(200, 60));//設定文字框最小值
        birthOutput.setMinimumSize(new Dimension(200, 60));//設定文字框最小值
        passwordOutput.setMinimumSize(new Dimension(200, 60));//設定文字框最小值
        
        modifyButton=new JButton("修改");
        modifyButton.setFont(labelFont);
        modifyButton.setSize(30, 20);// 設置大小
              
        backButton=new JButton("返回");
        backButton.setFont(labelFont);
        backButton.setSize(30, 20);// 設置大小
               
        JPanel actionPan = new JPanel(new GridBagLayout());
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        actionPan.add(backButton,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 1;
        actionPan.add(modifyButton,g);
       
        g.gridx = 0;
        g.gridy = 13;
        g.gridwidth = 3;
        l1Panel.add(actionPan,g);

        // 左一加入
        leftPanel.add(l1Panel);

        //詳細資料
        detailLabel = new JLabel("詳細資料");
        detailLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30)); // 設置字型、粗細和大小
        
        l2Panel.add(detailLabel, BorderLayout.NORTH);

        JPanel detailLeftPanel = new JPanel(new GridLayout(16,1));
        JPanel detailRightPanel = new JPanel(new GridLayout(16,1));
        
        l2Panel.add(detailLeftPanel, BorderLayout.CENTER);
        l2Panel.add(detailRightPanel, BorderLayout.EAST);

        
        String[] details = StartPage.patient.getDetail().showDetail().split(" ");
        
        for (String detail : details) {
          String[] parts = detail.split(":");
          JLabel questionLabel = new JLabel(parts[0]);
            questionLabel.setFont(labelFont); // 設置字型、粗細和大小
            JLabel ansLabel;
            if(StartPage.patient.getDetail().getDetail(parts[0])){
              ansLabel = new JLabel("Yes");
              ansLabel.setForeground(Color.RED);
            }
            else{
              ansLabel = new JLabel("No");
            }
            ansLabel.setFont(labelFont); // 設置字型、粗細和大小
            ansLabel.setHorizontalAlignment(SwingConstants.LEFT);
            detailLeftPanel.add(questionLabel,g);

            detailRightPanel.add(ansLabel,g);
        }

        // 左二加入
        leftPanel.add(l2Panel);

        // 右側面版用於放約診紀錄
        JPanel rightPanel = new JPanel(new BorderLayout());
        

       //約診紀錄
        appointmentLabel=new JLabel("掛號記錄");
        appointmentLabel.setFont(new Font("微軟正黑體", Font.BOLD, 30)); // 設置字型、粗細和大小

        JPanel appointmentPanel=new JPanel(new BorderLayout());
        JPanel appointmentTitlePanel = new JPanel(new GridLayout(0,5));
        JPanel appointmentContentPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 5);
        gridLayout.setVgap(4); // 設置垂直間距
        gridLayout. setHgap(4);//設置水平間距
        appointmentContentPanel.setLayout(gridLayout);
        
        JScrollPane appointmentScrollPane=new JScrollPane(appointmentContentPanel);

        //小標題
        
        JLabel typeLabel = new JLabel("類型", SwingConstants.CENTER);
        typeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        appointmentTitlePanel.add(typeLabel);

        JLabel itemLabel = new JLabel("項目", SwingConstants.CENTER);
        itemLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        appointmentTitlePanel.add(itemLabel);

        JLabel doctorLabel = new JLabel("醫生", SwingConstants.CENTER);
        doctorLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        appointmentTitlePanel.add(doctorLabel);

        JLabel timeLabel = new JLabel("時間", SwingConstants.CENTER);
        timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        appointmentTitlePanel.add(timeLabel);

        JLabel modifyLabel = new JLabel("詳細資料", SwingConstants.CENTER);
        modifyLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        appointmentTitlePanel.add(modifyLabel);

        //排版
        
        appointmentPanel.add(appointmentTitlePanel, BorderLayout.NORTH);
        appointmentPanel.add(appointmentScrollPane, BorderLayout.CENTER);

        rightPanel.add(new JPanel(), BorderLayout.WEST); 
        rightPanel.add(appointmentLabel, BorderLayout.NORTH);
        rightPanel.add(appointmentPanel, BorderLayout.CENTER);
        
        boolean hasData = false;//是否有就診紀錄
        
        if (!regestRecords.isEmpty()){
          
          for (ArrayList<String> r:regestRecords){
            String type=r.get(0);
            if (type.equals("預約看診")){ 
              hasData = true;
              String therapy=r.get(1);
              String dentist=r.get(2);
              String date=r.get(3);
                 
              //自訂方法調整字體
              appointmentContentPanel.add(createJLabel(type, labelFont));
              appointmentContentPanel.add(createJLabel(therapy, labelFont));
              appointmentContentPanel.add(createJLabel(dentist, labelFont));
              appointmentContentPanel.add(createJLabel(date, labelFont));
              JPanel BTNpanel  = new JPanel(new GridBagLayout());
              JButton modifyButton=new JButton("檢視");
              modifyButton.setFont(labelFont);
              modifyButton.setSize(30, 20);// 設置大小
              g.gridx = 1;
              g.gridy = 1;
              g.gridwidth = 1;
              g.anchor= GridBagConstraints.CENTER; // 對其置中
              BTNpanel.add(modifyButton, g);
              appointmentContentPanel.add(BTNpanel);

              //按修改的行為
              modifyButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  AppointedInfoPage AIP=new AppointedInfoPage(date,dentist,therapy);
                  AIP.setVisible(true);   
                  dispose();
                }
              });
            }else if(type.equals("現場掛號")){
              hasData = true;
              String therapy=r.get(1);
              String dentist=r.get(2);
              String number=r.get(3);

              appointmentContentPanel.add(createJLabel(type, labelFont));
              appointmentContentPanel.add(createJLabel(therapy, labelFont));
              appointmentContentPanel.add(createJLabel(dentist, labelFont));
              appointmentContentPanel.add(createJLabel(number, labelFont));
                    
              JPanel BTNpanel = new JPanel(new GridBagLayout());
              JButton modifyButton=new JButton("檢視");
              modifyButton.setFont(labelFont);
              modifyButton.setSize(30, 20);// 設置大小
              g.gridx = 1;
              g.gridy = 1;
              g.gridwidth = 1;
              g.weightx = 0;
              g.anchor= GridBagConstraints.CENTER; // 對齊置中
              BTNpanel.add(modifyButton, g);
              appointmentContentPanel.add(BTNpanel);

              //按修改的行為
              modifyButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  On_siteInfoPage OIP=new On_siteInfoPage(therapy,dentist);
                  OIP.setVisible(true);  
                  dispose();
                }
              });
            }
          }
        }
        if(!hasData){
          appointmentContentPanel.add(createJLabel("目前無就診紀錄", labelFont));
       }
        
        //加入主面板
        centerPan.add(leftPanel);
        centerPan.add(rightPanel);
        cp.add(centerPan, BorderLayout.CENTER);//置中



       //註冊按鈕
        backButton.addActionListener(new BackButtonHandler());
        modifyButton.addActionListener(new ModifyButtonHandler());
    }

   //返回鍵
    private class BackButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            BasicInfoPage BIP=new BasicInfoPage();
            BIP.setVisible(true);
            dispose();
        }
    }
    
   //修改鍵
    private class ModifyButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
          modifyData = true;//設定為已調整
          //讀取並刪除該病人資料，開啟註冊頁面(id與生日不可編輯)
          ArrayList<String> lines=new ArrayList<>();// 創建一個ArrayList來存儲讀取的行
          try (BufferedReader br=new BufferedReader(new FileReader("Patient.txt"))){
            String input;
            while((input=br.readLine())!=null){
                StringTokenizer st=new StringTokenizer(input," ");
                if(st.hasMoreTokens()){
                  st.nextToken();//第一筆是身分別
                    // 如果ID與輸入的ID相符
                    String getID=st.nextToken();
                    if(getID.equals(StartPage.patient.getId())){
                       continue;
                    }
                }
                // 將這一行添加到lines中
                lines.add(input);
            }
        }catch(IOException c){
            System.out.println("資料讀取錯誤");
        }

        try(PrintWriter pw=new PrintWriter(new FileWriter("Patient.txt"))){
            // 將lines中的每一行寫入到文件中
            for(String line:lines){
                pw.println(line);
            }
        }catch(IOException ex){
            System.out.println("資料寫入錯誤");
        }
        
        //開啟主要畫面
        SignUpPage SUP=new SignUpPage();
        SUP.modifyInfo(StartPage.patient.getId(), StartPage.patient.getBirthDate(), StartPage.patient.getDetail().getDetail("是否為特殊身分(榮民、身障)"));//ID、生日、身分別不可改
        SUP.setVisible(true);
        dispose();
        }
    }


    //讀取掛號紀錄
    public void loadPatientRegest(String filename,String ID){
      try (BufferedReader br=new BufferedReader(new FileReader(filename))){
        String line;//直接讀取一整行
        int countNum=0;//計算現掛序號
            
        while ((line=br.readLine()) != null){
          StringTokenizer st=new StringTokenizer(line," ");
          String fileID = st.nextToken();//身分證
          String type = st.nextToken();//類型
                
          ArrayList<String> innerArr=new ArrayList<>();
     
          if (st.hasMoreTokens()){
            if(fileID.equals(ID)){
              //用於計算現掛序號
              if(type.equals("On_site")){
                countNum+=1;
              }
              if (type.equals("Appointed")){
                type="預約看診";
                innerArr.add(type);
                innerArr.add(st.nextToken());//項目
                innerArr.add(st.nextToken());//醫生
                String date=st.nextToken() + "年" + st.nextToken() + "月" + st.nextToken() + "日" + st.nextToken();
                innerArr.add(date);//時間
              } else{
                type="現場掛號";
                innerArr.add(type);
                innerArr.add(st.nextToken());//項目
                innerArr.add(st.nextToken());//醫生
                innerArr.add("看診序號："+countNum);//看診順序
              }
              regestRecords.add(innerArr);//放入regestRecords
            }  
          }
        }
          
      } catch (IOException e){
        System.out.println("檔案讀取錯誤");
      }
    }
    // 方法：自定義方法來創建並設置字體的JLabel
    private JLabel createJLabel(String text, Font font) {
      JLabel label = new JLabel(text, SwingConstants.CENTER);
      label.setFont(font);
      return label;
    }
}
