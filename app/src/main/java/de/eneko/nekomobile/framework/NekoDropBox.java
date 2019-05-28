package de.eneko.nekomobile.framework;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import de.eneko.nekomobile.GlobalConst;



public class NekoDropBox {
    public static String path = GlobalConst.PATH_NEKOMOBILE_PICTURES;
    public static File dir = new File(path);
    private static final String APP_KEY = "wg2kll96fpshl3t";
    private static final String APP_SECRET = "b7avnoym41yks0w";
    private static final String ACCESS_TOKEN = "KEiL-5TaoSMAAAAAAACM2uj3dM6ScpYJ6CnnTWIqX-DOcqpwiK5MMxMA73j8TXwD";

    public static void main(String filename) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/NekoDB", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        //FullAccount account = client.users().getCurrentAccount();
        //System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream(filename)) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                    .uploadAndFinish(in);
        }
    }
}
