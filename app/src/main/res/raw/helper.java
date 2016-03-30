package raw;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;

/**
 * Created by Nirjon on 3/7/2016.
 */
public class Helper extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... params) {

        Log.v("NIRJON", "Inside async task");
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect(InetAddress.getByName("ftp.cs.unc.edu"));
            ftpClient.login("anonymous", "anwica@cs.unc.edu");
            FTPFile[] files = ftpClient.listFiles();
            for(int i = 0; i < files.length; i++){
                Log.v("NIRJON", files[i].getName());
            }
            ftpClient.changeWorkingDirectory("incoming/nirjon");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            BufferedInputStream buffIn = null;
            File ff = new File("src/main/java/edu/unc/nirjon/homeftp/MainActivity.java");
            buffIn = new BufferedInputStream(new FileInputStream(ff));
            ftpClient.enterLocalPassiveMode();
            ftpClient.storeFile("upload1.txt", buffIn);
            buffIn.close();
            ftpClient.logout();
            ftpClient.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
