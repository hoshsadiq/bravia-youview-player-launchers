package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class BbcIplayerActivity extends Activity {
    // am start -a com.sony.dtv.hbbtvlauncher.LaunchHbbTV -n com.sony.dtv.hbbtvlauncher/.LaunchHbbTV
    // -e HBBTV_LAUNCHER_INFO "type=bi, service=BBC_iPlayer, url=https://www.live.bbctvapps.co.uk/tap/iplayer/?origin=portal"

    private String serviceName = "BBC_iPlayer";
    private String serviceUrl = "https://www.live.bbctvapps.co.uk/tap/iplayer/?origin=portal";

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
