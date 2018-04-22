import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// Copy the file from the url
void getURL() {
  String url = "https://github.com/KaixoCode/cijfersthingy/raw/master/MagisterCijfersProcessing.exe";
  try {
    copyURLToFile(new URL(url), new File(dataPath("app/MagisterCijfersProcessing.exe")));
  }
  catch (Exception e) {
    println(e);
  }
}

// Method copies the url file to the file
void copyURLToFile(URL url, File file) {

  try {
    InputStream input = url.openStream();
    if (file.exists()) {
      if (file.isDirectory())
        throw new IOException("File '" + file + "' is a directory");

      if (!file.canWrite())
        throw new IOException("File '" + file + "' cannot be written");
    } else {
      File parent = file.getParentFile();
      if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
        throw new IOException("File '" + file + "' could not be created");
      }
    }

    FileOutputStream output = new FileOutputStream(file);

    byte[] buffer = new byte[4096];
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
    }

    input.close();
    output.close();

    System.out.println("File '" + file + "' downloaded successfully!");
    //String path = dataPath("app/MagisterCijfersProcessing.exe");
    //String pathd = dataPath("app");
    //Runtime.getRuntime().exec("MagisterCijfersProcessing.exe", null, new File(pathd));
  }
  catch(IOException ioEx) {
    println(ioEx);
    ioEx.printStackTrace();
  }
}



void getText(String t) {
  String text = t.substring(t.indexOf("code") + 26, t.indexOf("/code") - 1);
  println(text);
}
