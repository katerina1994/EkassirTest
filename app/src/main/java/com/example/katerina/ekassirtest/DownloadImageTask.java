package com.example.katerina.ekassirtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import static android.webkit.MimeTypeMap.getFileExtensionFromUrl;


public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private final Context context;
    ImageView bmImage;


    public DownloadImageTask(ImageView bmImage, Context context) {
        this.bmImage = bmImage;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];

        String fileName = Uri.parse(urldisplay).getLastPathSegment();
        File cacheDir = context.getCacheDir();
        File[] savedFiles = cacheDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return getFileExtensionFromUrl(pathname.getName()).compareTo("jpg") == 0;
            }
        });

        for (File savedFile : savedFiles){
            if (savedFile.lastModified() + 600000 < (new Date()).getTime()) {
                Log.i("Removing from cache", savedFile.getName());
                savedFile.delete();

            }
        }

        String path = context.getCacheDir() + "/" + fileName;
        Bitmap bitmap = null;
        try {
            Log.i("Loading from cache", fileName);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeFile(path, options);
            if (bitmap != null){
                Log.i("Image found in cache", fileName);
                return bitmap;
            }
            Log.i("Loading Image from http", fileName);
            InputStream networkStream = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(networkStream);
            FileOutputStream out = new FileOutputStream(path);
            Log.i("Caching image", fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
