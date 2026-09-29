//預約醫生(繼承Dentist)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AppointedDentist extends Dentist{
    
    //刪除預約紀錄方法
    public void deleteAppointedPatient(String ID,String therapy, String dentist, String time){
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
                        if(getRegister.equals("Appointed")){
                            String getSpeciality=st.nextToken();
                            String getDentist=st.nextToken();
                            String getTime=st.nextToken()+"年"+st.nextToken()+"月"+st.nextToken()+"日"+st.nextToken();
                            // 如果找到匹配的記錄，跳過該行（即刪除該行）
                            if(getSpeciality.equals(therapy)&&getDentist.equals(dentist)&&getTime.equals(time)){
                                continue;
                            }
                        }                        
                    }
                }
                lines.add(input);//將需儲存的資料暫存到lines中
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
        //開啟主要畫面
        BasicInfoPage BIP=new BasicInfoPage();
        BIP.setVisible(true);
        
    }
    
    //修改預約紀錄方法：：從檔案中刪除符合指定 ID、治療項目、醫生和時間，然後重新寫回其餘的記錄。開啟預約畫面
    public void modifyAppointedPatient(String ID,String therapy, String dentist, String time){
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
                        if(getRegister.equals("Appointed")){
                            String getSpeciality=st.nextToken();
                            String getDentist=st.nextToken();
                            String getTime=st.nextToken()+"年"+st.nextToken()+"月"+st.nextToken()+"日"+st.nextToken();
                            // 如果找到匹配的記錄，跳過該行（即刪除該行）
                            if(getSpeciality.equals(therapy)&&getDentist.equals(dentist)&&getTime.equals(time)){
                                continue;
                            }
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
        //開啟預約畫面
        AppointedPage AP = new AppointedPage();
        AP.setVisible(true);
        AP.modify();
    }
    public void deleteOn_sitePatient(String ID,String therapy,String dentist){}
    public void modifyOn_sitePatient(String ID,String theray,String denist){}
    
}
