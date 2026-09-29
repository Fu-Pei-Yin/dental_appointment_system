import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AuthenticationPage extends JFrame{
    private final Container cp;
    private final JLabel IDLabel,birthLabel;
    private final JLabel birthWarnLabel,IDWarnLabel;
    private final JTextField IDInput;
    private final JComboBox<Integer> yearInput,monthInput,dayInput;
    private final JButton authenticateButton,cancelButton;
    private static final Calendar calendar=Calendar.getInstance();
    private final int Year=calendar.get(Calendar.YEAR);
    public static String ID;
    private String warning;

    public AuthenticationPage(){
        setTitle("驗證");
        setSize(500,500);
        setLocationRelativeTo(null);
        cp=getContentPane();
        cp.setLayout(new GridBagLayout());
        GridBagConstraints g=new GridBagConstraints();
        g.insets=new Insets(5,5,5,5);

        
        // 統一字體
        Font labelFont = new Font("微軟正黑體", Font.PLAIN, 30);
        
        
        IDLabel=new JLabel("身分證");
        IDLabel.setFont(labelFont);
        g.gridx=0;
        g.gridy=0;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(IDLabel,g);
        IDInput=new JTextField();
        IDInput.setFont(labelFont);
        g.gridx=1;
        g.gridy=1;
        g.gridwidth=4;
        g.weightx=0;
        g.fill=GridBagConstraints.BOTH;
        cp.add(IDInput,g);
        IDWarnLabel=new JLabel("\t");
        IDWarnLabel.setFont(labelFont);
        g.gridx=2;
        g.gridy=2;
        g.gridwidth=2;
        g.weightx=0;
        cp.add(IDWarnLabel,g);
        birthLabel=new JLabel("生日");
        birthLabel.setFont(labelFont);
        g.gridx=0;
        g.gridy=3;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(birthLabel,g);
        yearInput=new JComboBox<>();
        yearInput.setFont(labelFont);
        g.gridx=1;
        g.gridy=4;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(yearInput,g);
        for(int i=Year;i > Year - 120;i--){
            yearInput.addItem(i);
        }
        monthInput=new JComboBox<>();
        monthInput.setFont(labelFont);
        g.gridx=2;
        g.gridy=4;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(monthInput,g);
        for(int i=1;i < 13;i++){
            monthInput.addItem(i);
        }
        dayInput=new JComboBox<>();
        dayInput.setFont(labelFont);
        g.gridx=3;
        g.gridy=4;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(dayInput,g);
        updateDays();
        birthWarnLabel=new JLabel("\t");
        birthWarnLabel.setFont(labelFont);
        g.gridx=2;
        g.gridy=5;
        g.gridwidth=2;
        g.weightx=0;
        cp.add(birthWarnLabel,g);

        authenticateButton=new JButton("驗證");
        authenticateButton.setFont(labelFont);
        g.gridx=1;
        g.gridy=7;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(authenticateButton,g);
        cancelButton=new JButton("取消");
        cancelButton.setFont(labelFont);
        g.gridx=2;
        g.gridy=7;
        g.gridwidth=1;
        g.weightx=0;
        cp.add(cancelButton,g);

        
        
        // 註冊按鈕事件
        authenticateButton.addActionListener(new AuthenticateButtonHandler());
        cancelButton.addActionListener(new CancelButtonHandler());
        monthInput.addActionListener(new MonthInputHandler());
  
        //註冊鍵盤事件
        IDInput.addKeyListener(new enterKeyAdapter());
        yearInput.addKeyListener(new enterKeyAdapter());
        monthInput.addKeyListener(new enterKeyAdapter());
        dayInput.addKeyListener(new enterKeyAdapter());
    }

    // 設置Enter鍵事件
    private class enterKeyAdapter extends KeyAdapter{
      public void keyPressed(KeyEvent e){
          if(e.getKeyCode() == KeyEvent.VK_ENTER) {
              authenticate();
          }
      }
  }
    
    //驗證按鈕
    private class AuthenticateButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e){
        authenticate();
      }
    }
    
    //取消按鈕
    private class CancelButtonHandler implements ActionListener {
      public void actionPerformed(ActionEvent e){
        dispose();
      }
    }
    
    //月份combobox
    private class MonthInputHandler implements ActionListener {
      public void actionPerformed(ActionEvent e){
        updateDays();
      }
    }
    
    private void updateDays(){
        int year =(int)yearInput.getSelectedItem();
        int month =(int)monthInput.getSelectedItem();
        int daysInMonth;
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
            dayInput.addItem(i);
        }
    }
    private void authenticate(){
      try (BufferedReader p = new BufferedReader(new FileReader("Patient.txt"))) {
        String input;
        StringTokenizer st;
        while ((input = p.readLine()) != null) {
          st = new StringTokenizer(input);
          st.nextToken();//charge
          String ID = st.nextToken();
          st.nextToken();//secret
          st.nextToken();//name
          String birth = st.nextToken();
          if(IDInput.getText().isEmpty()){
            warning="請輸入身分證";
            IDWarnLabel.setText(warning);
          }
          else{
            warning=Verification.verifyID(IDInput.getText());
            if (warning.equals("")){
              if(ID.equals(AuthenticationPage.ID=IDInput.getText())){
                if(birth.equals(yearInput.getSelectedItem()+"-"+monthInput.getSelectedItem()+"-"+dayInput.getSelectedItem())){
                  ReviewPage CP=new ReviewPage();
                  CP.setVisible(true);
                  dispose();
                }
                else{
                  birthWarnLabel.setText("日期輸入錯誤");
                }
                break;
              }
            }
            else{
              IDWarnLabel.setText(warning);
            }
          }
        }
     }catch (IOException c) {
        System.out.println("資料讀取錯誤");
        System.exit(-2);
     }
    }
}