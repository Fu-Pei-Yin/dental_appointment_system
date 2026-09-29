
//現掛醫生(繼承Dentist)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class On_siteDentist extends Dentist{
    
    //修改方法：從檔案中刪除符合指定 ID、治療項目（therapy）和醫生（dentist）的記錄，然後重新寫回其餘的記錄。並且開啟現掛頁面讓使用者重新掛號
    public void modifyOn_sitePatient(String ID,String therapy,String dentist){
        ArrayList<String> lines=new ArrayList<>();
        try(BufferedReader p=new BufferedReader(new FileReader("Register.txt"))){
            String input;
            // 從檔案中逐行讀取
            while((input=p.readLine())!=null){
                StringTokenizer st=new StringTokenizer(input," ");
                if(st.hasMoreTokens()){
                    String getID=st.nextToken();
                    if(getID.equals(ID)){
                        String getRegister=st.nextToken();
                        String getSpeciality=st.nextToken();
                        String getDentist=st.nextToken();
                        // 如果找到匹配的記錄，跳過該行（即刪除該行）
                        if(getRegister.equals("On_site")&&getSpeciality.equals(therapy)&&getDentist.equals(dentist)){
                            continue;
                        }
                    }
                }
                lines.add(input);
            }
        }catch(IOException c){
            System.out.println("資料讀取錯誤");
        }

        try(PrintWriter pw=new PrintWriter(new FileWriter("Register.txt"))){
            for(String line:lines){
                pw.println(line);
            }
        }catch(IOException e){
            System.out.println("資料寫入錯誤");
        }
        On_sitePage OP=new On_sitePage();
        OP.setVisible(true);
        OP.modify();
    }
    
    //刪除方法
    public void deleteOn_sitePatient(String ID,String therapy,String dentist){
        //暫存非刪除資料的數據
        ArrayList<String> lines=new ArrayList<>();

        // 使用 try-with-resources 讀取檔案，確保自動關閉 BufferedReader
        try(BufferedReader p=new BufferedReader(new FileReader("Register.txt"))){
            String input;
            while((input=p.readLine())!=null){
                StringTokenizer st=new StringTokenizer(input," ");
                if(st.hasMoreTokens()){
                    String getID=st.nextToken();
                    if(getID.equals(ID)){
                        String getRegister=st.nextToken();
                        String getSpeciality=st.nextToken();
                        String getDentist=st.nextToken();
                        if(getRegister.equals("On_site")&&getSpeciality.equals(therapy)&&getDentist.equals(dentist)){
                            continue;
                        }
                    }
                }
                lines.add(input);
            }
        }catch(IOException c){
            System.out.println("資料讀取錯誤");
        }

        // 使用 try-with-resources 寫入檔案，確保自動關閉 PrintWriter
        try(PrintWriter pw=new PrintWriter(new FileWriter("Register.txt"))){
            for(String line:lines){
                pw.println(line);
            }
        }catch(IOException e){
            System.out.println("資料寫入錯誤");
        }
        BasicInfoPage BIP=new BasicInfoPage();
        BIP.setVisible(true);
    }
    public void addAppointedDentist(String time,String ID){}
    public void modifyAppointedPatient(String ID,String selectedTherapyOutput, String selectedDentistOutput, String selectedTimeOutput){}
    public void deleteAppointedPatient(String ID,String selectedTherapyOutput, String selectedDentistOutput, String selectedTimeOutput){}
}
