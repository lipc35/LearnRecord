package com.example.jetpackdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;

/**
 * @author : tiny
 * @version :
 * @description : MonitorUtils
 * @date : 2020/6/24 12:05 AM
 */
public class MonitorUtils {
    public static boolean isBlank(View view) {
        Bitmap bitmap = createBitmapFromView(view);
        if (bitmap == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > 0 && height > 0) {
            int originPix = bitmap.getPixel(0, 0);
            int[] target = new int[width];
            Arrays.fill(target, originPix);
            int[] source = new int[width];
            boolean isWhiteScreen = true;
            for (int col = 0; col < height; col++) {
                bitmap.getPixels(source, 0, width, 0, col, width, 1);
                if (!Arrays.equals(target, source)) {
                    isWhiteScreen = false;
                    break;
                }
            }
            return isWhiteScreen;
        }
        return false;
    }

    private static Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.clearFocus();
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        if (bitmap != null) {
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            canvas.setBitmap(null);
        }
        return bitmap;
    }
}
