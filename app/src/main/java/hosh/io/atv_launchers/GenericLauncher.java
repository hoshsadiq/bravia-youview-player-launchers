package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GenericLauncher extends Activity {
    // am start -a com.sony.dtv.hbbtvlauncher.LaunchHbbTV -n com.sony.dtv.hbbtvlauncher/.LaunchHbbTV
    // -e HBBTV_LAUNCHER_INFO "type=bi, service=All4, url=https://yvweb.channel4.com/yvwebapp//"

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        startActivity(getYouViewIntent());
        finish();
    }

    private Intent getYouViewIntent() {
        String serviceName = getString(R.string.serviceName);
        String serviceUrl = getString(R.string.serviceUrl);

        Log.d(getClass().getSimpleName(), "Launching serviceName : " + serviceName + " and serviceURL : " + serviceUrl);

        Intent intent = new Intent();
        intent.setClassName("com.sony.dtv.hbbtvlauncher", "com.sony.dtv.hbbtvlauncher.LaunchHbbTV");
        intent.putExtra("HBBTV_LAUNCHER_INFO", "type=bi, " +
                "service=" + serviceName + ", " +
                "url=" + serviceUrl);

        return intent;
    }
}
