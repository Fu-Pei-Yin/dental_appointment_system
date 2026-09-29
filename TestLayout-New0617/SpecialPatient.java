//特殊病人(繼承病人)，掛號費與健保部分負擔額不同
public class SpecialPatient extends Patient {
  private static final int COPAYFEE=0;//掛號費
  private static final int HEALTHPREMIUM=50;//健保費
  
  public SpecialPatient(String n, String id, String addr, String tel, String birth, String sec, Detail det) { 
    super(n,id,addr,tel,birth,sec,det);
  }
  
   // Accessors
  public static int getCopayFee() {
    return COPAYFEE;
  }

  public static int getHealthPremium() {
    return HEALTHPREMIUM;
  }
  public int calculateCharge() {
    return COPAYFEE + HEALTHPREMIUM;
  }
    public String toString(){
    return "s "+getId()+" "+getSecret()+" "+getName()+
      " "+getBirthDate()+" "+getAddress()+" "+getTel()+" "+getDetailInfo();
  }
}
