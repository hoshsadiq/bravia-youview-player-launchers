package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.content.pm.PackageManager;

public class GenericLauncher extends Activity {
    // am start -a com.sony.dtv.hbbtvlauncher.LaunchHbbTV -n com.sony.dtv.hbbtvlauncher/.LaunchHbbTV
    // -e HBBTV_LAUNCHER_INFO "type=bi, service=All4, url=https://yvweb.channel4.com/yvwebapp//"

    private String serviceName = "Undefined";
    private String serviceUrl = "Undefined";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        startActivity(getYouViewIntent());
        finish();
    }

    private Intent getYouViewIntent() {
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            if (bundle != null) {
                serviceName = bundle.getString("serviceName");
                serviceUrl = bundle.getString("serviceUrl");
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(this.getClass().getSimpleName(), "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(this.getClass().getSimpleName(), "Failed to load meta-data, NullPointer: " + e.getMessage());
        }

        Log.d(this.getClass().getSimpleName(), "Launching serviceName : " + serviceName + " and serviceURL : " + serviceUrl);

        Intent intent = new Intent();
        intent.setClassName("com.sony.dtv.hbbtvlauncher", "com.sony.dtv.hbbtvlauncher.LaunchHbbTV");
        intent.putExtra("HBBTV_LAUNCHER_INFO", "type=bi, " +
                "service=" + serviceName + ", " +
                "url=" + serviceUrl);

        return intent;
    }
}
