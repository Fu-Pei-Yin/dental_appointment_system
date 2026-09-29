
//醫生(父類別) 抽象類別
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Dentist {
    private String name;
    private ArrayList<String> speciality;
    private TreeMap sequence;
    public void setSpeciality(ArrayList<String> speciality){
        this.speciality=speciality;
    }
    public ArrayList<String> getSpeciality(){
        return speciality;
    }
    public void setSequence(TreeMap sequence){
        this.sequence=sequence;
    }
    public TreeMap getSequence(){
        return sequence;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void addSpeciality(String speciality){//添加專業
        getSpeciality().add(speciality);
    }

    public abstract void deleteAppointedPatient(String ID,String selectedTherapyOutput, String selectedDentistOutput, String selectedTimeOutput);
    public abstract void deleteOn_sitePatient(String ID,String therapy,String dentist);
    public abstract void modifyAppointedPatient(String ID,String selectedTherapyOutput, String selectedDentistOutput, String selectedTimeOutput);
    public abstract void modifyOn_sitePatient(String ID,String selectedTherapyOutput, String selectedDentistOutput);
}