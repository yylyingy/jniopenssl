package demo.rsa.gkbn.rsademo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Yangyl on 2017/5/21.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(this,"708c503dea",true);
    }
}
