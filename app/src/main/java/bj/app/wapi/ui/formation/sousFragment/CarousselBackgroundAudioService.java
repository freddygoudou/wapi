package bj.app.wapi.ui.formation.sousFragment;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import bj.app.wapi.R;

public class CarousselBackgroundAudioService extends Service {

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        player = MediaPlayer.create(this, Uri.parse("android.resource://" + "bj.app.wapi/" + R.raw.caroussel_audio));
        player.setLooping(false);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
    }
}
