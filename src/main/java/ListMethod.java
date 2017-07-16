import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by miygagi on 17/05/28. <br>
 * ListMethod class is just wrap ArrayList<MyMethod>
 * @author miyagi
 */
public class ListMethod {
    private ArrayList<MyMethod> methodArray = new ArrayList<>();

    public MyMethod serchFromName(String methodName){
        for(MyMethod method : methodArray){
            if(method.getMethodName().equals(methodName)){
                return method;
            }
        }
        return null;
    }

    public void add(MyMethod myMethod){
        methodArray.add(myMethod);
    }

    public MyMethod get(int i){
        return methodArray.get(i);
    }

    public int length(){
        return methodArray.size();
    }
}
