package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ItvHubActivity extends Activity {
    // am start -a com.sony.dtv.hbbtvlauncher.LaunchHbbTV -n com.sony.dtv.hbbtvlauncher/.LaunchHbbTV
    // -e HBBTV_LAUNCHER_INFO "type=bi, service=ITV_Hub, url=http://app.10ft.itv.com/youviewsony"

    private String serviceName = "ITV_Hub";
    private String serviceUrl = "http://app.10ft.itv.com/youviewsony";

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
