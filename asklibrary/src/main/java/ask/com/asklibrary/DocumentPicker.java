package ask.com.asklibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by flatmind on 9/5/18.
 */

public class DocumentPicker {
    public static String DOWNLOAD_FILE_DESTINATION = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    private Context context;
    private String fileName;

    public DocumentPicker(Context context) {
        this.context = context;
    }

    public void startPicker(int requestCode) {
        Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent1.setType("file/*");
        intent1.addCategory(Intent.CATEGORY_OPENABLE);
        Intent chooserIntent = Intent.createChooser(intent1, "Open file");
        ((Activity) context).startActivityForResult(chooserIntent, requestCode);
    }

    public String filePath(Intent data) throws Exception {
        String localFile;
        if (data.getData().getAuthority().startsWith("com.google")) {
            localFile = getDriveFile(data);
        } else {
            localFile = data.getData().getPath();
            fileName = localFile.substring(localFile.lastIndexOf("/") + 1, localFile.length());
        }
        return localFile;
    }

    public String fileName() {
        return fileName;
    }

    private String getDriveFile(Intent data) {
        try {
            final int MEGABYTE = 1024 * 1024;
            int count;
            Uri returnUri = data.getData();
            Cursor returnCursor = context.getContentResolver().query(returnUri, null, null, null, null);
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();
            InputStream inputStream = context.getContentResolver().openInputStream(returnUri);
            fileName = returnCursor.getString(nameIndex);
            String doc_path = DOWNLOAD_FILE_DESTINATION + File.separator + fileName;
            File file = new File(doc_path);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(doc_path);
            byte dataBytes[] = new byte[MEGABYTE];
            while ((count = inputStream.read(dataBytes)) != -1) {
                fileOutputStream.write(dataBytes, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            return doc_path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}