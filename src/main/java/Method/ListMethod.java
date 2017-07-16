package Method;

import java.util.ArrayList;

/**
 * Created by miygagi on 17/05/28. <br>
 * Method.MyUtil.ListMethod class is just wrap ArrayList<Method.MyUtil.MyMethod>
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
