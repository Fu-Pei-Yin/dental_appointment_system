import java.util.Calendar;
public class Verification{
    static public String verifyName(String name){
         String specialChars = "_'~!@#$%^&*()+={}':;',[]";//特殊字元
        if(name.length()<2){
            return "姓名長度不可小於2";
        }
        else if(name.length()>10){
            return "姓名長度不可大於10";
        }
        else{ 
            for (int i = 0; i < name.length(); i++) {
              if (specialChars.contains(String.valueOf(name.charAt(i)))) {
                return "姓名只能包含中文、英文字符和 - 符號";
              }
            }
        }
        return "";
    }
    static public String verifyID(String ID){
        char firstChar=ID.charAt(0);
        if(ID.length()!=10){
            return "身分證長度須為10";
        }
        //檢查身分證號碼的第一個字元是否為大寫字母
        else if(!Character.isLetter(firstChar)||!Character.isUpperCase(firstChar)){
            return "身分證格式錯誤，請重新輸入";
        }
        else if (ID.charAt(1) != '1' && ID.charAt(1) != '2') {//檢查第一碼數字是否為1或2
            return "身分證格式錯誤，請重新輸入";
        }
        else {
            for(int i=2;i<=9;i++){
                if(!Character.isDigit(ID.charAt(i))){
                    return "後九碼需為數字，請重新輸入";
                }
            }
        }
        return "";
    }
    static public String verifyAddress(String address){
        String specialChars = "_'~!@#$%^&*()+={}':;',[]"; // 特殊字元
        if (!address.contains("市") && !address.contains("縣")) {
          return "地址格式輸入錯誤，請包含縣/市";
        } else {
          for (int i = 0; i < address.length(); i++) {
            if (specialChars.contains(String.valueOf(address.charAt(i)))) {
                return "地址不可包含特殊字元";
            }
          }
        }
    return "";
    }
    static public String verifyTEL(String TEL){
        if(TEL.length()!=10){
            return "電話號碼輸入錯誤，請重新輸入";
        }
        else if (!TEL.substring(0, 2).equals("09")) {
            return "輸入值須為手機號碼";
        }
        return "";
    }
    static public String verifyBirth(String birth){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        String[] Date=birth.split("-");
        int birthYear=Integer.parseInt(Date[0]);
        int birthMonth=Integer.parseInt(Date[1]);
        int birthDay=Integer.parseInt(Date[2]);
        if(birthYear>year){
            return "請勿選擇未來日期，請重新輸入";
        }
        else if(birthYear==year&&birthMonth>month){
            return "請勿選擇未來日期，請重新輸入";
        }
        else if(birthYear==year&&birthMonth==month&&birthDay>day){
            return "請勿選擇未來日期，請重新輸入";
            
        }
        return "";
    }
    static public String verifyAppointed(int y, int m, int d){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        if(y==year && m<month){
            return "日期已過，請重新輸入";
        }
        else if(y==year && m<=month && d<=day){
            return "日期已過，請重新輸入";
        }
        return "";
    }
    static public String verifySecret(String secret){
        if(secret.equals("")){
            return "請輸入密碼";
        // 使用正則表達式檢查密碼是否符合要求
        }else if (!secret.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{4,}).*$")) {
            //^ 和 $：分別匹配字串的開始和結束。
            //(?=.*[A-Z])：檢查字串中是否至少包含一個大寫字母。
            //(?=.*[a-z])：檢查字串中是否至少包含一個小寫字母
            //(?=.*\\d{4,})：檢查字串中是否至少包含四個數字
            return "密碼必須包含至少一個大寫英文、一個小寫英文和四位數字";
        }
        return "";
    }
}
