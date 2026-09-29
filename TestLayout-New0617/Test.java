import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test{
  public static void main(String[] args){
    try(PrintWriter pw=new PrintWriter(new FileWriter("Dentist.txt"))){
            // 將lines中的每一行寫入到文件中
                pw.println("Dr.A 牙周治療 根管治療 矯正");
                pw.println("Dr.B 矯正 牙周治療 根管治療 牙體復形美學");
                pw.println("Dr.C 牙體復形美學 義齒補綴 矯正");
                pw.println("Dr.D 牙體復形美學 義齒補綴 植牙 製作假牙");
                pw.println("Dr.E 義齒補綴 植牙 製作假牙");
                pw.println("Dr.N 檢查 洗牙 補蛀牙");
                pw.println("Dr.O 拔蛀牙 洗牙 補蛀牙");
                pw.println("Dr.P 拔牙 牙痛 補蛀牙");
                pw.println("Dr.Q 檢查 牙痛 拔牙");
                pw.println("Dr.R 檢查 洗牙 牙痛");
                
        }catch(IOException ex){
            System.out.println("資料寫入錯誤");
        }
  }
}