package be.digitalia.fosdem.utils;

import android.app.ActivityManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import be.digitalia.fosdem.model.Track;

public class ThemeUtils {

	@SuppressWarnings("deprecation")
	public static void setActionBarTrackColor(@NonNull AppCompatActivity activity, @NonNull Track.Type trackType) {
		ActionBar actionBar = activity.getSupportActionBar();
		final int color = ContextCompat.getColor(activity, trackType.getColorResId());
		actionBar.setBackgroundDrawable(new ColorDrawable(color));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			final int darkColor = ContextCompat.getColor(activity, trackType.getDarkColorResId());
			activity.getWindow().setStatusBarColor(darkColor);
			final ActivityManager.TaskDescription taskDescription;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
				taskDescription = new ActivityManager.TaskDescription(null, 0, color | 0xFF000000);
			} else {
				taskDescription = new ActivityManager.TaskDescription(null, null, color | 0xFF000000);
			}
			activity.setTaskDescription(taskDescription);
		}
	}
}
