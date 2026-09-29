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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;


public class SignUpPage extends JFrame{
    private final JLabel nameLabel,IDLabel,addressLabel,TELLabel,birthLabel,secretLabel,detailLabel;
    private final JTextField nameInput,IDInput,TELInput,secretInput,addressInput;
    private final JComboBox<Integer> yearInput,monthInput,dayInput;
    private final JButton submitButton,clearButton,backtoStartPageButton;
    private final Container cp;
    private static final Calendar calendar=Calendar.getInstance();
    private final int Year=calendar.get(Calendar.YEAR);
    private final Detail detail=new Detail();
    private final Map<String,JRadioButton[]> detailRadioButtons;
    private String warning;
    
    //紀錄身分別
    private boolean identity;

    public SignUpPage(){
        setTitle("註冊");
        // 設置視窗為全螢幕並且不可縮小
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = dim.width;
        int screenHeight = dim.height;
        setMinimumSize(new Dimension(screenWidth,screenHeight));
        
        Font labelFont = new Font("微軟正黑體", Font.PLAIN, 30);

        //創建主面板並設置為 GridLayout
        cp=getContentPane();
        cp.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets=new Insets(6,6,6,6);
        g.fill = GridBagConstraints.HORIZONTAL;

        nameLabel=new JLabel("姓名");
        nameLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=0;
        g.gridy=0;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(nameLabel,g);
        nameInput=new JTextField(20);
        nameInput.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=1;
        g.gridy=1;
        g.gridwidth=3;
        g.weightx=0;
        g.fill = GridBagConstraints.HORIZONTAL;
        cp.add(nameInput,g);
        
        IDLabel=new JLabel("身分證");
        IDLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=0;
        g.gridy=3;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(IDLabel,g);
        IDInput=new JTextField(20);
        IDInput.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=1;
        g.gridy=4;
        g.gridwidth=3;
        g.weightx=0;
        g.fill = GridBagConstraints.HORIZONTAL;
        cp.add(IDInput,g);
        
        addressLabel=new JLabel("住址");
        addressLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=0;
        g.gridy=6;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(addressLabel,g);
        addressInput=new JTextField(20);
        addressInput.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=1;
        g.gridy=7;
        g.gridwidth=3;
        g.weightx=0;
        g.fill = GridBagConstraints.HORIZONTAL;
        cp.add(addressInput,g);

        TELLabel=new JLabel("電話號碼");
        TELLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=0;
        g.gridy=9;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(TELLabel,g);
        TELInput=new JTextField(20);
        TELInput.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=1;
        g.gridy=10;
        g.gridwidth=3;
        g.weightx=0;
        g.fill = GridBagConstraints.HORIZONTAL;
        cp.add(TELInput,g);

        birthLabel=new JLabel("生日");
        birthLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=0;
        g.gridy=12;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(birthLabel,g);
        yearInput=new JComboBox<>();
        yearInput.setFont(labelFont);
        g.gridx=1;
        g.gridy=13;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(yearInput,g);
        for(int i=Year;i > Year - 120;i--){
            yearInput.addItem(i);
        }
        monthInput=new JComboBox<>();
        monthInput.setFont(labelFont);
        g.gridx=2;
        g.gridy=13;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(monthInput,g);
        for(int i=1;i <= 12;i++){
            monthInput.addItem(i);
        }
        dayInput=new JComboBox<>();
        dayInput.setFont(labelFont);
        g.gridx=3;
        g.gridy=13;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(dayInput,g);
        updateDays();

        monthInput.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updateDays();
            }
        });

        secretLabel=new JLabel("密碼");
        secretLabel.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=0;
        g.gridy=15;
        g.gridwidth=1;
        g.weightx=0;
        g.weighty=0;
        cp.add(secretLabel,g);
        secretInput=new JTextField(20);
        secretInput.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=1;
        g.gridy=16;
        g.gridwidth=3;
        g.weightx=0;
        g.weighty=0;
        g.fill = GridBagConstraints.HORIZONTAL;
        cp.add(secretInput,g);
        detailLabel=new JLabel("請回答下列問題");
        detailLabel.setFont(new Font("微軟正黑體", Font.BOLD, 40)); // 設置字型、粗細和大小
        g.gridx=4;
        g.gridy=0;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(detailLabel,g);
        JPanel detailPanel=new JPanel();
        detailPanel.setLayout(new GridLayout(0,3));
        JScrollPane scrollPane=new JScrollPane(detailPanel);
        g.gridx=4;
        g.gridy=1;
        g.gridwidth=3;
        g.gridheight=18;
        g.weightx=1;
        g.weighty=1;
        g.fill = GridBagConstraints.BOTH;
        cp.add(scrollPane,g);
        detailRadioButtons=new HashMap<>();//存yes/No按鈕
        int i=0;
        for(String key : detail.getKeys()){
            JLabel questionLabel=new JLabel(key);
            questionLabel.setFont(labelFont); // 設置字型、粗細和大小
            JRadioButton yesButton=new JRadioButton("Yes");
            yesButton.setFont(labelFont);
            JRadioButton noButton=new JRadioButton("No");
            noButton.setFont(labelFont);
            
            //按鈕放同一個面板，只能二擇一
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(yesButton);
            buttonGroup.add(noButton);
        
            
            detailRadioButtons.put(key,new JRadioButton[]{yesButton,noButton});
            detailPanel.add(questionLabel);
            detailPanel.add(yesButton);
            detailPanel.add(noButton);
            //如果是從searchPage來的，則身分別不可修改
            if(i==0&&SearchPage.modifyData){
              yesButton.setEnabled(false);
              noButton.setEnabled(false);
              if(identity){
                yesButton.setSelected(true);
              }else{
                noButton.setSelected(true);
              }
            }
            i++;
        }
        
        
        submitButton=new JButton("送出");
        submitButton.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=1;
        g.gridy=20;
        g.gridwidth=1;
        g.weightx=0;
        g.fill = GridBagConstraints.NONE;
        cp.add(submitButton,g);
        clearButton=new JButton("清除");
        clearButton.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=3;
        g.gridy=20;
        g.gridwidth=1;
        g.weightx=0;
        g.fill = GridBagConstraints.NONE;
        cp.add(clearButton,g);
        backtoStartPageButton=new JButton("回到登入");
        backtoStartPageButton.setFont(labelFont); // 設置字型、粗細和大小
        g.gridx=5;
        g.gridy=20;
        g.gridwidth=0;
        g.weightx=0;
        g.fill = GridBagConstraints.NONE;
        cp.add(backtoStartPageButton,g);

        // 註冊按鈕事件
        submitButton.addActionListener(new SubmitButtonHandler());
        clearButton.addActionListener(new ClearButtonHandler());
        backtoStartPageButton.addActionListener(new BacktoStartPageButtonHandler());
        
        // 註冊鍵盤事件
        IDInput.addKeyListener(new enterKeyAdapter());
        nameInput.addKeyListener(new enterKeyAdapter());
        TELInput.addKeyListener(new enterKeyAdapter());
        addressInput.addKeyListener(new enterKeyAdapter());
        secretInput.addKeyListener(new enterKeyAdapter());

        //註冊滑鼠事件
        addressInput.addMouseListener(new AddressMouseHandler());
        secretInput.addMouseListener(new SecretMouseHandler());
    }
    
    //設置滑鼠事件
    private class AddressMouseHandler extends MouseAdapter {
      public void mouseEntered(MouseEvent e) {
        if(addressInput.getText().isEmpty()){//當地址未被使用者輸入時
          addressInput.setText("輸入格式:OO縣/市");
          addressInput.setFont(new Font("微軟正黑體", Font.PLAIN, 24)); // 設置字型、粗細和大小
        }
      }
      public void mouseClicked(MouseEvent e){
        if(addressInput.getText().equals("輸入格式:OO縣/市")){
          addressInput.setText("");
        }
      }
      public void mouseExited(MouseEvent e){
        if(addressInput.getText().equals("輸入格式:OO縣/市")){
          addressInput.setText("");
        }
      }
  }

  private class SecretMouseHandler extends MouseAdapter{
    public void mouseEntered(MouseEvent e){
      if(secretInput.getText().isEmpty()){//當密碼未被使用者輸入時
        secretInput.setText("須包含大小寫英文各一與至少四位數字");
        secretInput.setFont(new Font("微軟正黑體", Font.PLAIN, 24)); // 設置字型、粗細和大小
      }
    }
    public void mouseClicked(MouseEvent e){
      if(secretInput.getText().equals("須包含大小寫英文各一與至少四位數字")){
        secretInput.setText("");
      }
    }
    public void mouseExited(MouseEvent e){
      if(secretInput.getText().equals("須包含大小寫英文各一與至少四位數字")){
        secretInput.setText("");
      }
    }
  }

    // 設置Enter鍵事件
    private class enterKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){//當使用者在輸入格按下enter鍵
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                submit();
            }
        }
    }
    
    //送出按鈕
    private class SubmitButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e){
        submit();
      }
    }
    
    //清除按鈕
    private class ClearButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e){//清除輸入內容
        nameInput.setText("");
        addressInput.setText("");
        secretInput.setText("");
        TELInput.setText("");
       
        if (!SearchPage.modifyData){//新用戶
            IDInput.setText("");
        }
      }
    }
    
    // 回到登入按鈕
    private class BacktoStartPageButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e){
        System.out.println("SignUpPage："+SearchPage.modifyData);
        //如果是SearchPage來的，按回到登入時將原本資料寫回檔案
        if(SearchPage.modifyData){
          try(FileWriter fr=new FileWriter("Patient.txt",true);// 用來將資料寫入檔案
              PrintWriter p=new PrintWriter(fr)){//用來將資料寫入檔案
                    p.println(StartPage.patient.toString());
            }catch(IOException ex){
              System.out.println("檔案寫入失敗");
              System.exit(-1);
            }
        }
        
          StartPage SP=new StartPage();
          SP.setVisible(true);

          dispose();
        
      }
    }
    
    private void submit(){
        boolean isValid;
        for(String key : detailRadioButtons.keySet()){
          JRadioButton[] buttons=detailRadioButtons.get(key);//取得所點選的內容
          if(buttons[0].isSelected()){
            detail.setDetail(key,true);
          }else{
            detail.setDetail(key,false);
          }
        }
        String name=nameInput.getText();//取得姓名
        String id=IDInput.getText();//取得身分證
        String address=addressInput.getText();//取得地址

        String tel=TELInput.getText();//取得電話號碼
        tel=tel.replace(" ","");//將電話號碼中的空字串移除
        tel=tel.replace("-","");//將電話號碼中的-移除
        tel=tel.replace("_","");//將電話號碼中的_移除
        tel=tel.replace("(","");//將電話號碼中的(移除
        tel=tel.replace(")","");//將電話號碼中的)移除
        tel=tel.replace("#","");//將電話號碼中的#移除
        tel=tel.replace("+886","0");//將電話號碼中的+886改為0
        String birthDate=yearInput.getSelectedItem()+"-"+monthInput.getSelectedItem()+"-"+dayInput.getSelectedItem();//取得出生日期
        String secret=secretInput.getText();//取得密碼
        isValid=validateInputs(name,id,address,tel,secret);//判斷是否有輸入錯誤
        
        
        if(isValid){            
            try(FileWriter fr=new FileWriter("Patient.txt",true);// 用來將資料寫入檔案
              PrintWriter p=new PrintWriter(fr)){//用來將資料寫入檔案
                String patinetInfo=id+" "+secret+" "+name+" "+birthDate+" "+address+" "+tel+" "+detail.showDetail();
                
                System.out.println(patinetInfo);
                
                if(detail.getDetail("是否為特殊身分(榮民、身障)")==false){
                    p.println("n "+patinetInfo);//一般病人
                    }
                  else{
                    p.println("s "+patinetInfo);//特殊病人
                  }
            }catch(IOException ex){
              System.out.println("檔案寫入失敗");
              System.exit(-1);
            }
              StartPage SP=new StartPage();
              SP.setVisible(true);
              dispose();
            }
    }

    // 方法：讓ID、生日不可修改的方法，for 更新個人資料(固定ID、生日)
    public void modifyInfo(String id, String birth, boolean identity){
      this.identity = identity;
      // ID設置為不可編輯
      IDInput.setText(id);
      IDInput.setEditable(false);
      
      // 生日設置為不可編輯
      String[] dateParts = birth.split("-");
      yearInput.addItem(Integer.parseInt(dateParts[0]));
      yearInput.setSelectedItem(Integer.parseInt(dateParts[0]));
      yearInput.setEnabled(false); // 禁用編輯
      
      monthInput.addItem(Integer.parseInt(dateParts[1]));
      monthInput.setSelectedItem(Integer.parseInt(dateParts[1]));
      monthInput.setEnabled(false); // 禁用編輯
      
      dayInput.addItem(Integer.parseInt(dateParts[2]));
      dayInput.setSelectedItem(Integer.parseInt(dateParts[2]));
      dayInput.setEnabled(false); // 禁用編輯   
      
      
 
    }
    

    //方法：更新日期選項
    private void updateDays(){
        int year =(int)yearInput.getSelectedItem();
        int month =(int)monthInput.getSelectedItem();
        int daysInMonth;
        dayInput.removeAllItems();
        switch(month){//判斷該月份天數
            case 2:
                if((year%4==0&&year%100!=0)||(year%400==0)){//判斷是否為閏年
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
            dayInput.addItem(i);
        }
      }

    //方法：判別輸入是否合理
    private boolean validateInputs(String name,String id,String address,String tel,String secret){
      boolean isValid=true;
      
      // 設置JOptionPane的字體大小
      Font OPfont = new Font("微軟正黑體", Font.PLAIN, 18);
        UIManager.put("OptionPane.messageFont", OPfont);
        UIManager.put("OptionPane.buttonFont", OPfont);
        
      //如果是空值，提示需輸入資料
      if(name.isEmpty()||id.isEmpty()||address.isEmpty()||tel.isEmpty()||secret.isEmpty()){
          JOptionPane.showMessageDialog(this, "輸入值不可為空", "錯誤", JOptionPane.ERROR_MESSAGE);//將圖標設置為錯誤提示
          isValid=false;
      }else{
        warning=Verification.verifyName(name);
        if(!warning.isEmpty()){
          JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
          isValid=false;
        }else{
          warning=Verification.verifyID(id);
          if(!warning.isEmpty()){
            JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
            isValid=false;
          }else{
            
            try(BufferedReader p=new BufferedReader(new FileReader("Patient.txt"))){
              StringTokenizer st;
              String input;  
              while((input=p.readLine())!=null){
                 
                    st=new StringTokenizer(input);
                    if (st.hasMoreTokens()) {
                      st.nextToken(); // 身分
                      String getID=st.nextToken();
                      System.out.println(getID);
                      if(id.equals(getID)){
                        warning="身分證已註冊";
                        JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
                        isValid=false;
                        break;
                      }
                    }
                }
            } catch (IOException ex) {
            }
            warning=Verification.verifyAddress(address);
            if(!warning.isEmpty()){
              JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
              isValid=false;
            }else{
              warning=Verification.verifyTEL(tel);
              if(!warning.isEmpty()){
                JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
                isValid=false;
              }else{
                warning=Verification.verifyBirth((int)yearInput.getSelectedItem()+"-"+(int)monthInput.getSelectedItem()+"-"+(int)dayInput.getSelectedItem());
                if(!warning.isEmpty()){
                  JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
                  isValid=false;
                }else{
                  warning=Verification.verifySecret(secret);
                  if(!warning.isEmpty()){
                    JOptionPane.showMessageDialog(this, warning, "錯誤", JOptionPane.ERROR_MESSAGE);
                    isValid=false;
                  }
                }
              }

            }

          }  
      }
    }
    return isValid;
  }
    }
