package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class My5Activity extends Activity {
    // am start -a com.sony.dtv.hbbtvlauncher.LaunchHbbTV -n com.sony.dtv.hbbtvlauncher/.LaunchHbbTV
    // -e HBBTV_LAUNCHER_INFO "type=bi, service=My5, url=https://youview.my5static.com"

    private String serviceName = "My5";
    private String serviceUrl = "https://youview.my5static.com";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        startActivity(getYouViewIntent());
        finish();
    }

    private Intent getYouViewIntent() {
        Intent intent = new Intent();
        intent.setClassName("com.sony.dtv.hbbtvlauncher", "com.sony.dtv.hbbtvlauncher.LaunchHbbTV");
        intent.putExtra("HBBTV_LAUNCHER_INFO", "type=bi, " +
                "service=" + serviceName + ", " +
                "url=" + serviceUrl);

        return intent;
    }
}
