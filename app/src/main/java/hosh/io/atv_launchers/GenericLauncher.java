package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.net.Uri;

public class GenericLauncher extends Activity {
    // am start -a com.sony.dtv.intent.action.LAUNCH_HBBTV -n com.sony.dtv.hbbtvplayer/.HbbTvPlayerActivity
    // -d serviceUrl

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
        intent.setAction("com.sony.dtv.intent.action.LAUNCH_HBBTV");
        intent.setData(Uri.parse(serviceUrl));
        intent.setClassName("com.sony.dtv.hbbtvplayer", "com.sony.dtv.hbbtvplayer.HbbTvPlayerActivity");

        return intent;
    }
}
