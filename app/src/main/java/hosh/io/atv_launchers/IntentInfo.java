package hosh.io.atv_launchers;

import android.content.Intent;
import android.net.Uri;

public class IntentInfo {
    private Intent intent = new Intent();

    public IntentInfo(String packageName, String className) {
        intent.setClassName(packageName, className);
    }

    public IntentInfo withExtra(String name, String value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentInfo withAction(String action) {
        intent.setAction(action);
        return this;
    }


    public IntentInfo withData(Uri data) {
        intent.setData(data);
        return this;
    }

    public Intent getIntent() {
        return intent;
    }
}
