package Utils;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class Simple_Okhttp {
    private static OkHttpClient okHttpClient;
    private Simple_Okhttp(){};
    public static OkHttpClient getSingleOkhttp(){
        if(okHttpClient == null){
            synchronized (Simple_Okhttp.class) {//保证了同一时间只能只能有一个对象访问此同步块       
                if(okHttpClient == null){
                     okHttpClient = new OkHttpClient();
                    }
                }
            }
         return okHttpClient; //返回创建好的对象  
        }
}

