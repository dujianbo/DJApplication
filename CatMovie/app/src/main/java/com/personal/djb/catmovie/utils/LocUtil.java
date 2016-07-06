package com.personal.djb.catmovie.utils;

import android.content.Context;
import android.media.AudioManager;
import android.os.storage.StorageManager;
import android.view.Display;

import com.personal.djb.catmovie.activity.VideoActivity;
import com.personal.djb.catmovie.bean.ScreenBean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocUtil {
	/**
	 * 获取所有存储路径
	 * 
	 * @param context
	 * @return
	 */
	public static List<String> getDirs(Context context) {
		List<String> dirs = new ArrayList<String>();
		StorageManager storageManager = (StorageManager) context
				.getSystemService(Context.STORAGE_SERVICE);
		try {
			Class[] paramClasses = {};
			Method getVolumePathsMethod = StorageManager.class.getMethod(
					"getVolumePaths", paramClasses);
			getVolumePathsMethod.setAccessible(true);
			Object[] params = {};
			Object invoke = getVolumePathsMethod.invoke(storageManager, params);
			for (int i = 0; i < ((String[]) invoke).length; i++) {
				// System.out.println(((String[])invoke)[i]);
				dirs.add(((String[]) invoke)[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dirs;
	}

	/**
	 * 获取最大音量
	 * 
	 * @param context
	 * @return
	 */
	public static int getMaxVolume(Context context) {
		return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	}

	/**
	 * 获取当前音量
	 * 
	 * @param context
	 * @return
	 */
	public static int getCurVolume(Context context) {
		return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
				.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	/**
	 * 设置当前音量
	 * 
	 * @param context
	 * @param index
	 */
	public static void setCurVolume(Context context, int index) {
		((AudioManager) context.getSystemService(Context.AUDIO_SERVICE))
				.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
	}

	/**
	 * 获取屏幕像素
	 * 
	 * @param context
	 * @return
	 */
	public static ScreenBean getScreenPix(Context context) {
		Display dplay = ((VideoActivity) context).getWindowManager()
				.getDefaultDisplay();
		return new ScreenBean(dplay.getWidth(),dplay.getHeight());
	}
}
