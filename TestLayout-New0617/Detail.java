
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Detail{
    private final LinkedHashMap<String,Boolean> detailLinkedHashMap;

    public Detail(){
        detailLinkedHashMap = new LinkedHashMap<>();
         // 將預設的項目加入 LinkedHashMap 中，每個項目預設值為 false
        add("是否為特殊身分(榮民、身障)");
        add("藥物過敏反應");
        add("麻藥不良反應");
        add("外傷血流不止");
        add("痙攣神智不清");
        add("曾有腫瘤癌症");
        add("曾有拔牙困難");
        add("上顎竇有問題");
        add("抽菸、喝酒、檳榔");
        add("高血壓");
        add("心臟病、風濕熱");
        add("糖尿病、黃疸病");
        add("肝臟病、腎臟病");
        add("肺結核");
        add("內科治療中");
        add("骨質疏鬆藥物");
    }

    // 新增項目到 detailLinkedHashMap 中，預設值為 false
    public void add(String key){
        detailLinkedHashMap.put(key,false);
    }

    //設置特定 key 的值為 value，並返回之前的值
    public Boolean setDetail(String key,boolean value){
        return detailLinkedHashMap.replace(key,value);
    }

    // 獲取特定 key 的值
    public Boolean getDetail(String key){
        return detailLinkedHashMap.get(key);
    }

    // 獲取所有 key，並返回一個包含這些 key 的 ArrayList
    public ArrayList<String> getKeys(){
        return new ArrayList<>(detailLinkedHashMap.keySet());
    }

    // 以字串形式顯示 detailLinkedHashMap 的所有 key 和對應的值
    public String showDetail(){
        StringBuilder details = new StringBuilder();//StringBuilder 是建立和修改字串的類別 比 String 更高效
        // 遍歷 detailLinkedHashMap 的每個 key
        //建立一個迭代器（Iterator）。keySet() 方法返回一個包含所有鍵的 Set，iterator() 方法則返回這個集合的迭代器。
        Iterator<String>st1 = detailLinkedHashMap.keySet().iterator();
        while (st1.hasNext()){
            String key = st1.next();
            // 如果 key 的值為 true，顯示 "yes"，否則顯示 "no"
            if(detailLinkedHashMap.get(key)==true){
                details.append(key).append(":yes ");
            }
            else{
                details.append(key).append(":no ");
            }
        }
        return details.toString();
    }
}
