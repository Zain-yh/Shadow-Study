package com.young.plugin2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PluginService extends Service {
    public PluginService() {
        Log.e("zyhTest", "PluginService  init");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("zyhTest", "onTransact: PluginService");
        return new Binder() {
            @Override
            protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
                return super.onTransact(code, data, reply, flags);
            }
        };
    }
}