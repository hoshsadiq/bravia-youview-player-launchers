package hosh.io.atv_launchers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class GenericLauncher extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getHbbTvLauncherIntent();
        if (intent != null) {
            startActivity(intent);
        }
        finish();
    }

    private Intent getHbbTvLauncherIntent() {
        String serviceName = getString(R.string.serviceName);
        String serviceUrl = getString(R.string.serviceUrl);

        List<IntentInfo> intents = Arrays.asList(
                // am start -a com.sony.dtv.hbbtvlauncher.LaunchHbbTV -n com.sony.dtv.hbbtvlauncher/.LaunchHbbTV -e HBBTV_LAUNCHER_INFO "type=bi, service=All4, url=https://yvweb.channel4.com/yvwebapp//"
                new IntentInfo("com.sony.dtv.hbbtvlauncher", "com.sony.dtv.hbbtvlauncher.LaunchHbbTV")
                        .withExtra("HBBTV_LAUNCHER_INFO", String.format("type=bi, service=%1$s, url=%2$s", serviceName, serviceUrl)),
                // am start -a com.sony.dtv.intent.action.LAUNCH_HBBTV -n com.sony.dtv.hbbtvplayer/.HbbTvPlayerActivity -d "https://yvweb.channel4.com/yvwebapp//"
                new IntentInfo("com.sony.dtv.hbbtvplayer", "com.sony.dtv.hbbtvplayer.HbbTvPlayerActivity")
                        .withAction("com.sony.dtv.intent.action.LAUNCH_HBBTV")
                        .withData(Uri.parse(serviceUrl))
        );

        Log.d(getClass().getSimpleName(), "Launching serviceName : " + serviceName + " and serviceURL : " + serviceUrl);
        for (IntentInfo intentInfo : intents) {
            Intent intent = intentInfo.getIntent();

            Log.d(getClass().getSimpleName(), "Attempt " + intent.getClass().getName());
            if (isAvailable(this, intent)) {
                Log.d(getClass().getSimpleName(), "Success!");
                return intent;
            }
            Log.d(getClass().getSimpleName(), "Failed");
        }

        Log.d(getClass().getSimpleName(), "Failed to find any valid intents to launch serviceName : " + serviceName + " and serviceURL : " + serviceUrl);
        return null;
    }

    private boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
