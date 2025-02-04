package de.eneko.nekomobile.framework.ftp;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
public class FTPClientFunctions {

    // Now, declare a public FTP client object.

    private static final String TAG = "FTPClientFunctions";
    public FTPClient mFTPClient = null;

    // Method to connect to FTP server:
    public boolean ftpConnect(String host, String username, String password,
                              int port) {
        try {
            mFTPClient = new FTPClient();
            // connecting to the host
            String ip =  Ip(host);

            mFTPClient.connect(ip, port);

            // now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                // login using username & password
                boolean status = mFTPClient.login(username, password);

                /*
                 * Set File Transfer Mode
                 *
                 * To avoid corruption issue you must specified a correct
                 * transfer mode, such as ASCII_FILE_TYPE, BINARY_FILE_TYPE,
                 * EBCDIC_FILE_TYPE .etc. Here, I use BINARY_FILE_TYPE for
                 * transferring text, image, and compressed files.
                 */
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();

                return status;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error: could not connect to host " + host);
        }

        return false;
    }

    public String Ip(String hostname) {
        try {

            try {
                InetAddress SW[] = InetAddress.getAllByName("eneko-rdp.dynv6.net");
                for (int i=0; i<SW.length; i++) {
                    String myIp;
                    myIp = String.valueOf(SW[i]).replace("eneko-rdp.dynv6.net/","");
                    if (! myIp.contains(":")){
                        return myIp;
                    }
                }
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            InetAddress ipaddress = InetAddress.getByName(hostname);

            return "79.227.233.93";
        } catch ( UnknownHostException e ) {
           return "79.227.233.93";
        }
    }

        public static String getIP() {
            String line = "";
            BufferedReader in = null;
            int i = 0;
            try {
                URL getyouripurl = new URL( "ftp://nekoadmin@eneko-rdp.dynv6.net" );
                in = new BufferedReader( new InputStreamReader( getyouripurl
                        .openStream() ) );
            } catch( MalformedURLException e ) {
                System.err.println( e );
            } catch( IOException e ) {
                System.err.println( e );
            }
            try {
                while( ( line = in.readLine() ) != null ) {
                    if( line.startsWith( "<h1>Your IP is " ) ) {
                        return line.substring( 15, ( line.length() - 10 ) );
                    }
                    i++ ;
                }
            } catch( IOException e ) {
                System.err.println( e );
            }
            return "Fehler";
        }



    // Method to disconnect from FTP server:

    public boolean ftpDisconnect() {
        try {
            mFTPClient.logout();
            mFTPClient.disconnect();
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Error occurred while disconnecting from ftp server.");
        }

        return false;
    }

    // Method to get current working directory:

    public String ftpGetCurrentWorkingDirectory() {
        try {
            String workingDir = mFTPClient.printWorkingDirectory();
            return workingDir;
        } catch (Exception e) {
            Log.d(TAG, "Error: could not get current working directory.");
        }

        return null;
    }

    // Method to change working directory:

    public boolean ftpChangeDirectory(String directory_path) {
        try {
            mFTPClient.changeWorkingDirectory(directory_path);
            if (mFTPClient.getReplyCode() == 550){
                mFTPClient.makeDirectory(directory_path);
            }
            return mFTPClient.changeWorkingDirectory(directory_path);
       } catch (Exception e) {
            try {
                mFTPClient.makeDirectory(directory_path);
                mFTPClient.changeWorkingDirectory(directory_path);
                return true;
            } catch (Exception ex) {
                Log.d(TAG, "Error: could not change directory to " + directory_path);
            }
        }

        return false;
    }





    // Method to list all files in a directory:

    public String[] ftpPrintFilesList(String dir_path) {
        String[] fileList = null;
        try {
            FTPFile[] ftpFiles = mFTPClient.listFiles(dir_path);
            int length = ftpFiles.length;
            fileList = new String[length];
            for (int i = 0; i < length; i++) {
                String name = ftpFiles[i].getName();
                boolean isFile = ftpFiles[i].isFile();

                if (isFile) {
                    fileList[i] = "File :: " + name;
                    Log.i(TAG, "File : " + name);
                } else {
                    fileList[i] = "Directory :: " + name;
                    Log.i(TAG, "Directory : " + name);
                }
            }
            return fileList;
        } catch (Exception e) {
            e.printStackTrace();
            return fileList;
        }
    }

    public String[] ftpPrintDirectoryList(String dir_path) {
        String[] fileList = null;
        try {
            FTPFile[] ftpFiles = mFTPClient.listFiles(dir_path);
            int length = ftpFiles.length;
            fileList = new String[length];
            for (int i = 0; i < length; i++) {
                String name = ftpFiles[i].getName();
                boolean isFile = ftpFiles[i].isFile();
                if (isFile) {
                    fileList[i] = "File :: " + name;
                    Log.i(TAG, "File : " + name);
                } else {
                    fileList[i] = "Directory :: " + name;
                    Log.i(TAG, "Directory : " + name);
                }
            }
            return fileList;
        } catch (Exception e) {
            e.printStackTrace();
            return fileList;
        }
    }


    // Method to create new directory:

//    public boolean ftpMakeDirectory(String new_dir_path) {
//        try {
//            boolean status = mFTPClient.makeDirectory(new_dir_path);
//            return status;
//        } catch (Exception e) {
//            Log.d(TAG, "Error: could not create new directory named "
//                    + new_dir_path);
//        }
//
//        return false;
//    }

    // Method to delete/remove a directory:

    public boolean ftpRemoveDirectory(String dir_path) {
        try {
            boolean status = mFTPClient.removeDirectory(dir_path);
            return status;
        } catch (Exception e) {
            Log.d(TAG, "Error: could not remove directory named " + dir_path);
        }

        return false;
    }

    // Method to delete a file:

    public boolean ftpRemoveFile(String filePath) {
        try {
            boolean status = mFTPClient.deleteFile(filePath);
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method to rename a file:

    public boolean ftpRenameFile(String from, String to) {
        try {
            boolean status = mFTPClient.rename(from, to);
            return status;
        } catch (Exception e) {
            Log.d(TAG, "Could not rename file: " + from + " to: " + to);
        }

        return false;
    }

    // Method to download a file from FTP server:

    /**
     * mFTPClient: FTP client connection object (see FTP connection example)
     * srcFilePath: path to the source file in FTP server desFilePath: path to
     * the destination file to be saved in sdcard
     */
    public boolean ftpDownload(String srcFilePath, String desFilePath) {
        boolean status = false;
        try {
            FileOutputStream desFileStream = new FileOutputStream(desFilePath + "/" + srcFilePath);
            ;
            status = mFTPClient.retrieveFile(srcFilePath, desFileStream);
            desFileStream.close();

            return status;
        } catch (Exception e) {
            Log.d(TAG, "download failed");
        }

        return status;
    }

    // Method to upload a file to FTP server:

    /**
     * mFTPClient: FTP client connection object (see FTP connection example)
     * srcFilePath: source file path in sdcard desFileName: file name to be
     * stored in FTP server desDirectory: directory path where the file should
     * be upload to
     */
    public boolean ftpUpload(String srcFilePath, String desFileName) {
        boolean status = false;
        try {
            FileInputStream srcFileStream = new FileInputStream(srcFilePath);

            // change working directory to the destination directory
            // if (ftpChangeDirectory(desDirectory)) {
            status = mFTPClient.storeFile(desFileName, srcFileStream);
            // }

            srcFileStream.close();

            return status;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "upload failed: " + e);
        }

        return status;
    }
}