
//一般病人(繼承病人)，掛號費與健保部分負擔額不同
public class normalPatient extends Patient {
  private static final int COPAYFEE=150;//掛號費
  private static final int HEALTHPREMIUM=50;//健保費
  
  public normalPatient(String n, String id, String addr, String tel, String birth, String sec, Detail det) { 
    super(n,id,addr,tel,birth,sec,det);
  }
   // Accessors
  public static int getCopayFee() {
    return COPAYFEE;
  }

  public static int getHealthPremium() {
    return HEALTHPREMIUM;
  }
  public String toString(){
    return "n "+getId()+" "+getSecret()+" "+getName()+
      " "+getBirthDate()+" "+getAddress()+" "+getTel()+" "+getDetailInfo();
  }
  public int calculateCharge() {
    return COPAYFEE + HEALTHPREMIUM;
  }
}
