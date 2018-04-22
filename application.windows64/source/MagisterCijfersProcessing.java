import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.awt.PSurfaceAWT.SmoothCanvas; 
import java.io.*; 
import java.util.*; 
import javax.swing.*; 
import javax.swing.table.*; 
import javax.imageio.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.net.MalformedURLException; 
import java.net.URL; 
import java.net.URLConnection; 

import org.apache.commons.codec.binary.*; 
import org.apache.commons.codec.*; 
import org.apache.commons.codec.digest.*; 
import org.apache.commons.codec.language.*; 
import org.apache.commons.codec.language.bm.*; 
import org.apache.commons.codec.net.*; 
import org.apache.commons.logging.impl.*; 
import org.apache.commons.logging.*; 
import org.apache.http.client.fluent.*; 
import org.apache.http.auth.*; 
import org.apache.http.auth.params.*; 
import org.apache.http.cookie.*; 
import org.apache.http.client.*; 
import org.apache.http.client.methods.*; 
import org.apache.http.client.params.*; 
import org.apache.http.client.utils.*; 
import org.apache.http.client.protocol.*; 
import org.apache.http.client.config.*; 
import org.apache.http.client.entity.*; 
import org.apache.http.conn.*; 
import org.apache.http.conn.ssl.*; 
import org.apache.http.conn.scheme.*; 
import org.apache.http.impl.auth.*; 
import org.apache.http.impl.client.*; 
import org.apache.http.impl.execchain.*; 
import org.apache.http.impl.conn.*; 
import org.apache.http.impl.conn.tsccm.*; 
import org.apache.http.cookie.params.*; 
import org.apache.http.conn.routing.*; 
import org.apache.http.conn.params.*; 
import org.apache.http.conn.socket.*; 
import org.apache.http.impl.cookie.*; 
import org.apache.http.conn.util.*; 
import org.apache.http.client.cache.*; 
import org.apache.http.impl.client.cache.*; 
import org.apache.http.impl.client.cache.memcached.*; 
import org.apache.http.impl.client.cache.ehcache.*; 
import org.apache.http.impl.auth.win.*; 
import org.apache.http.message.*; 
import org.apache.http.concurrent.*; 
import org.apache.http.*; 
import org.apache.http.annotation.*; 
import org.apache.http.params.*; 
import org.apache.http.protocol.*; 
import org.apache.http.pool.*; 
import org.apache.http.impl.io.*; 
import org.apache.http.impl.entity.*; 
import org.apache.http.impl.*; 
import org.apache.http.io.*; 
import org.apache.http.ssl.*; 
import org.apache.http.entity.*; 
import org.apache.http.config.*; 
import org.apache.http.util.*; 
import org.apache.http.impl.bootstrap.*; 
import org.apache.http.impl.pool.*; 
import org.apache.http.entity.mime.*; 
import org.apache.http.entity.mime.content.*; 
import com.sun.jna.*; 
import com.sun.jna.ptr.*; 
import com.sun.jna.win32.*; 
import com.sun.jna.platform.*; 
import com.sun.jna.platform.dnd.*; 
import com.sun.jna.platform.mac.*; 
import com.sun.jna.platform.unix.*; 
import com.sun.jna.platform.win32.*; 
import com.sun.jna.platform.win32.COM.*; 
import com.sun.jna.platform.win32.COM.tlb.*; 
import com.sun.jna.platform.win32.COM.tlb.imp.*; 
import com.sun.jna.platform.win32.COM.util.*; 
import com.sun.jna.platform.win32.COM.util.annotation.*; 
import com.sun.jna.platform.wince.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MagisterCijfersProcessing extends PApplet {










JFrame jf;
table tb;
PImage img;

public void setup() {
  

  // Load magister logo
  img = loadImage(dataPath("logo.png"));

  // Smooth the canvas
  

  // Make the PApplet a JFrame
  final SmoothCanvas sc = (SmoothCanvas) getSurface().getNative();
  jf = (JFrame) sc.getFrame();
  jf.setMinimumSize(new Dimension(900, 600));

  // Make resizable
  jf.setResizable(true);

  // Set custom title
  jf.setTitle("Cijfers");

  // Set custom icon
  Toolkit toolkit = Toolkit.getDefaultToolkit();
  Image icon = toolkit.getImage(dataPath("icon2.png"));
  jf.setIconImage(icon);

  // Let the user select a file
  selectFile();


  //getURL();

  //try {
  //  String path = dataPath("app/MagisterCijfersProcessing.exe");
  //  String pathd = dataPath("app");
  //  Runtime.getRuntime().exec(path, null, new File(pathd));
  //} 
  //catch(Exception e) {
  //  println(e);
  //};

  //exit();
}

int cursor = ARROW;
public void draw() {
  cursor(cursor);
  cursor = ARROW;
  //background(0, 150, 219);
  background(0);

  // Show the table
  if (tb != null) {
    tb.show();
  } else {
    imageMode(CENTER);
    image(img, width/2, height/2);
  }
  mousePress = false;
}

boolean mousePress = false;
public void mousePressed() {
  mousePress = true;
}








// Copy the file from the url
public void getURL() {
  String url = "https://github.com/KaixoCode/cijfersthingy/raw/master/MagisterCijfersProcessing.exe";
  try {
    copyURLToFile(new URL(url), new File(dataPath("app/MagisterCijfersProcessing.exe")));
  }
  catch (Exception e) {
    println(e);
  }
}

// Method copies the url file to the file
public void copyURLToFile(URL url, File file) {

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



public void getText(String t) {
  String text = t.substring(t.indexOf("code") + 26, t.indexOf("/code") - 1);
  println(text);
}

class Column {
  String value;

  Column(String v) {
    value = v;
  }

  // Returns value
  public float getValue() {
    return (PApplet.parseFloat(value.replace(",", ".")));
  }

  public void show(table t, float x, float y, float w, float h, int in, int oin) {
    stroke(196);

    if (value.equals("")) {

      // If there's no value make the box grey
      fill(237);
    } else {
      if (oin == 0 && in != 0 && in != t.rows.length-2) {

        // Fill the student's name green if meets requirements else red
        if (meetRequirements(in)) {
          fill(200, 255, 200);
        } else {
          fill(255, 200, 200);
        }
      } else {

        // Fill green if it is the average grade
        if (in != 0 && oin == t.rows[0].cols.length-1) {
          fill(200, 255, 200);
        } else { 
          fill(255);
        }
      }
    }
    rect(x, y, w, h);

    // Draw onvoldoendes red and bold
    if (PApplet.parseFloat(value.replace(",", ".")) < 5.5f) {
      textFont(createFont("Arial Bold", 12));
      fill(170, 20, 20);
    } else {
      textFont(createFont("Arial", 12));
      fill(29);
    }

    // Seperate the grades from the text
    if (value.replaceAll("[0-9]", "").replace(",", "").equals("")) {
      
      // Align text to center 
      textAlign(CENTER, CENTER);
      float plus = (w / 2) - 2;
      
      // Show 1 decimal if normal grade and 2 decimals if average grade
      if (!value.equals("") && oin != t.rows[0].cols.length-1) text(nf(PApplet.parseFloat(value.replace(",", ".")), 1, 2).replace(".", ","), x + 2 + plus, y + h/2);
      if (!value.equals("") && oin == t.rows[0].cols.length-1) text(nf(PApplet.parseFloat(value.replace(",", ".")), 1, 2).replace(".", ","), x + 2 + plus, y + h/2);
    } else {
      
      // Align text to the left
      textAlign(LEFT, CENTER);
      text(value.replace(",", "."), x + 5, y + h/2);
    }
  }
}
class Filter {
  String name;
  float stroke = 10;
  float hgth = 30;

  // Filter selected
  boolean selected = false;

  Filter(String n) {
    name = n;
  }

  // Position of filter
  float x;
  float y;
  float w;

  // Visuals for filter
  public void show(float xp, float yp, float wp, float hp, int index) {

    // Calc position of filter on screen
    x = xp + stroke;
    y = yp + (index+1) * hgth + stroke;
    w = wp;

    // Select color
    if (selected) {
      if (overFilter()) {
        cursor = HAND;
        fill(210, 255, 210);
      } else {      
        fill(200, 255, 200);
      }
    } else {
      if (overFilter()) {
        cursor = HAND;
        fill(250);
      } else {
        fill(245);
      }
    }
    rect(x, y, w - stroke * 2, hgth);

    // Draw the name of the filter
    fill(44);
    textAlign(LEFT, CENTER);
    text(name, x + stroke, y + hgth/2);

    // Select or deselect when mousepress
    if (mousePress && overFilter()) {
      selected ^= true;

      // Select a niveau
      if (index < 3) {
        tb.filters[0].selected = false;
        tb.filters[1].selected = false;
        tb.filters[2].selected = false;
        selected = true;
        niveau = index;
      }

      // Different action when selecting doubleren
      if (index == 3) {
        selectDoubleren();
      }
    }
  }

  // Returns true if over this filter
  public boolean overFilter() {
    return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + hgth);
  }
}


// Select a klas
public void chooseKlas(float xp, float yp, float wp, float hp) {
  for (int i = 0; i < 6; i++) {
    float w = ((wp-20)/6);
    float x = xp + i * w + 10;
    float y = yp + 10;

    // Make it the right color
    if (klas == i+1) {
      if (overButton(x, y, w, 30)) {
        cursor = HAND;
        fill(210, 255, 210);
      } else {
        fill(200, 255, 200);
      }
    } else {
      if (overButton(x, y, w, 30)) {
        cursor = HAND;
        fill(245);
      } else {
        fill(250);
      }
    }
    rect(x, y, w, 30);

    // Put the number in the vakje
    fill(44);
    text(i+1, x + w/2, y + 15);

    // Select the klas if pressed
    if (mousePress && overButton(x, y, w, 30)) {
      klas = i+1;
    }
  }
}


// Returns true if over button
public boolean overButton(float x, float y, float w, float h) {
  return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h);
}
class Row {
  Column[] cols;

  Row(String[] values) {
    cols = new Column[values.length];
    for (int i = 0; i < cols.length; i++) {
      cols[i] = new Column(values[i]);
    }
  }


  public void show(table t, int amC, float y, float h, int in) {

    // Show names seperately
    cols[0].show(t, t.x, y, nameW, h, in, 0);

    // Show rest of columns
    int begI = (int) map(t.scrolled, 0, 100, 1, cols.length - t.camt);
    int endI = constrain(amC + begI - 1, 0, cols.length);

    int index = 1;
    for (int i = begI; i < endI; i++) {

      // Calculate the X and W
      float x = map(index, 1, amC, t.x + nameW, t.w + t.x);
      float w = (t.w - nameW) / (amC - 1);

      // Draw the cols
      cols[i].show(t, x, y, w, h, in, i);
      index++;
    }
  }
}
float hw = 100;
float hh = 20;
float nameW = 200;


// Scrolling within the table
public void mouseWheel(MouseEvent e) {
  tb.scrolled = constrain(tb.scrolled + e.getCount() * tb.camt, 0, 100);
}


// The table class with all the grades in it
class table {
  Row[] rows;
  String[][] values = new String[1][1];

  // Position of table on screen
  float stroke = 20;
  float x = stroke;
  float y = 3 * stroke;
  float w;
  float h;

  // Amount of rows showing
  float camt;

  Filter[] filters = new Filter[11];

  table(String[][] v) {
    values = v;

    rows = new Row[values.length];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new Row(values[i]);
    }

    filters[0] = new Filter("Mavo klas");
    filters[1] = new Filter("Havo klas");
    filters[2] = new Filter("Vwo klas");
    filters[niveau].selected = true;
    filters[3] = new Filter("Staat op overgaan");
    filters[4] = new Filter(" - 3 of minder onvoldoendes");
    filters[5] = new Filter(" - Niet lager dan een 4");
    filters[6] = new Filter(" - Gem. CE vakken hoger dan 5.5 (vanaf klas 3)");
    filters[7] = new Filter(" - Gem. alle vakken hoger dan 6");
    filters[8] = new Filter(" - 6 of hoger voor CKV (mavo 3/havo 4/vwo 5)");
    filters[9] = new Filter(" - Max één 5 voor kernvakken (havo/vwo)"); 
    filters[10] = new Filter(" - Min 5 voor netl (mavo)");
  }


  public void show() {
    w = width - 2 * stroke - width/4;
    h = height - 4 * stroke - 20;

    // Grey header
    noStroke();
    fill(44);
    rect(0, 0, width, stroke * 2);

    // Magister logo
    imageMode(CORNER);
    image(img, 10, 0, stroke * 2 * 2.68062827f, stroke * 2);

    // White background
    fill(255);
    noStroke();
    rect(x, y, w, h);

    // All other elements
    showTable();
    showScrollBar();
    showSettings();
  }


  // Show the settings on the side
  public void showSettings() {
    float x = width - width/4;
    float y = 3 * stroke;
    float w = width - x - stroke;
    float h = height - 4 * stroke;

    // Background
    fill(255);
    rect(x, y, w, h);

    // Show all filters
    chooseKlas(x, y, w, h);
    for (int i = 0; i < filters.length; i++) filters[i].show(x, y, w, h, i);
  }


  // Some variables for the scrollbar
  float scrolled = 0;
  boolean pressedBar = false;
  float startX = 0;
  float pscrolled = 0;
  int scrollFill = color(197);

  // Show the scrollbar
  public void showScrollBar() {


    // Draw background
    fill(236);
    rect(x, y + h, w, 20);


    // Calculate the width of the scrollbar
    float scrollWidth = (camt / (rows.length-2)) * w;

    // Calculate the X position of the scrollbar
    float scrolledX = constrain(map(scrolled, 0, 100, 0, w - scrollWidth), 0, w - scrollWidth);


    // Draw the scrollbar
    float s = 2; // Stroke
    fill(scrollFill);
    rect(x + scrolledX + s, y + h + s, scrollWidth - s*2, 20 - s*2);

    // Reset color of scrollbar
    scrollFill = color(197);

    // Change color of scrollbar when hovering over it
    if (overScrollBar(x + scrolledX, y + h, scrollWidth, 20)) {
      scrollFill = color(177);
    }

    // If pressed on scrollbar enable scrolling
    if (mousePress && overScrollBar(x + scrolledX, y + h, scrollWidth, 20)) {
      pressedBar = true;
      pscrolled = scrolled;
      startX = mouseX;
    }

    // If mousepressed use scrollbar
    if (mousePressed && pressedBar) {
      scrolled = constrain(pscrolled + (mouseX - startX) / (5), 0, 100);
      scrollFill = color(147);
    }

    // Reset pressed bar when releases mouse
    if (!mousePressed) {
      pressedBar = false;
    }
  }


  // Returns true if over scrollbar
  public boolean overScrollBar(float x, float y, float w, float h) {
    return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h);
  }


  // Show the table with all the information in it
  public void showTable() {

    // Calculate the amount of rows and cols in the table
    int amR = constrain(ceil(h / hh), 0, rows.length);
    int amC = constrain(ceil(w / hw), 0, rows[0].cols.length);

    // The amount of cols 
    camt = ((w - nameW) / ((w - nameW)/(amC - 1))); 

    // Draw all the rows
    int index = 0;
    int amountofnonrows = 2;
    for (int i = 0; i < amR; i++) {
      float yp = map(index, 0, amR-amountofnonrows, y, h + y);
      float hp = h / (amR-amountofnonrows);
      if (i != 1 && i != rows.length-1) {
        rows[i].show(this, amC, yp, hp, i);
        index++;
      }
    }
  }
}

// Get all CE vakken
String[] noCEVakken = {"ckv-ed", "anw-ed", "bsm-ed", "lo-ed"};
String[] kernvakken = {"netl-ed", "entl-ed", "wisa-ed", "wisb-ed", "wisc-ed"};

// Give certain niveaus a value
int niveau = 0;
int MAVO = 0;
int HAVO = 1;
int VWO = 2;

// All filter variables
int[] onvoldoendes;
boolean[] CEVak;
boolean[] lagerDanVier;
boolean[] lagerDanVijfHalf;
boolean[] lagerDanZes;
boolean[] voldCkv;
boolean[] eenVijfKern;
boolean[] eenVijfNetl;


public void getAllArrays() {
  int begI = 2;
  int endI = tb.rows.length-2;
  
  // Try to guess the klas using the names of the subjects
  getKlas();

  // Init all filters
  onvoldoendes = new int[tb.rows.length];
  lagerDanVier = new boolean[tb.rows.length];
  lagerDanVijfHalf = new boolean[tb.rows.length];
  lagerDanZes = new boolean[tb.rows.length];
  voldCkv = new boolean[tb.rows.length];
  eenVijfKern = new boolean[tb.rows.length];
  eenVijfNetl = new boolean[tb.rows.length];

  // Get values for all filters
  for (int i = begI; i < endI; i++) {
    onvoldoendes[i] = getOnvoldoendes(tb.rows[i]);
    lagerDanVier[i] = getLagerDanVier(tb.rows[i]);
    lagerDanVijfHalf[i] = getLagerDanVijfHalf(tb.rows[i]);
    lagerDanZes[i] = getLagerDanZes(tb.rows[i]);
    voldCkv[i] = getVoldCkv(tb.rows[i]);
    eenVijfKern[i] = getVijfKern(tb.rows[i]);
    eenVijfNetl[i] = getVijfNetl(tb.rows[i]);
  }

  // Get if CE vak per column
  getCEVakken(tb.rows[0]);
}

// Tries to guess the klas
int klas = 0;
public void getKlas() {
  String vak = tb.rows[0].cols[1].value;
  klas = PApplet.parseInt(vak.replace(vak.replaceAll("[0-9]", ""), ""));
}

// Looks at each row and tells if it is a CE vak
public void getCEVakken(Row r) {
  CEVak = new boolean[r.cols.length];

  // Save if CE vak per index
  for (int i = 1; i < r.cols.length-1; i++) {
    boolean NoCEVak = false;

    // Compare to list of non CE vakken
    for (int j = 0; j < noCEVakken.length; j++) {
      if (r.cols[i].value.toLowerCase().contains(noCEVakken[j])) NoCEVak = true;
    }
    CEVak[i] = !NoCEVak;
  }
}

// Returns number of onvoldoendes
public int getOnvoldoendes(Row r) {
  int amt = 0;

  // Get amount of onvoldoendes
  for (int i = 1; i < r.cols.length-1; i++) {
    if (r.cols[i].getValue() < 5.5f) amt++;
  }
  return amt;
}

// Returns true if a grade is lower than 4
public boolean getLagerDanVier(Row r) {
  boolean lager = false;

  // Test if a grade is lower than 4
  for (int i = 1; i < r.cols.length-1; i++) {
    if (r.cols[i].getValue() < 4) lager = true;
  }

  return lager;
}

// Returns true if the average CE grade is lower than 5.5
public boolean getLagerDanVijfHalf(Row r) {
  return r.cols[r.cols.length-1].getValue() <= 5.5f;
}

// Returns true if total average grade is lower than 6
public boolean getLagerDanZes(Row r) {

  // Total grade
  float total = 0;

  // Amount of grades
  int totalIndex = 0;
  for (int i = 1; i < r.cols.length-1; i++) {

    // Only add one to total if there is a grade
    if (!r.cols[i].value.equals("")) {
      total += r.cols[i].getValue();
      totalIndex++;
    }
  }

  // Calculate average grade
  total /= totalIndex;

  // Return true if average is lower than 6
  return total < 6;
}

// Returns true if higher than 6 voor ckv
public boolean getVoldCkv(Row r) {
  boolean voldCkv = false;

  for (int i = 1; i < r.cols.length-1; i++) {

    // If vak is CKV test if lower than 6
    if (tb.rows[0].cols[i].value.toLowerCase().contains("ckv-ed")) {
      if (r.cols[i].getValue() < 6) voldCkv = true;
      break;
    }
  }

  return voldCkv;
}


// Calculate amount of 5s for kernvakken
public boolean getVijfKern(Row r) {
  int total = 0;

  for (int i = 1; i < r.cols.length-1; i++) {

    // Go through all kernvakken
    for (int j = 0; j < kernvakken.length; j++) {

      // If the grade is a kernvak test if lower than 5.5
      if (tb.rows[0].cols[i].value.toLowerCase().contains(kernvakken[j])) {
        if (!r.cols[i].value.equals("") && r.cols[i].getValue() < 5.5f) 
          total++;
      }
    }
  }

  return total > 1;
}

// Min of 5 for netl for mavo
public boolean getVijfNetl(Row r) {
  boolean voldNetl = false;

  for (int i = 1; i < r.cols.length-1; i++) {

    // If vak is CKV test if lower than 5
    if (tb.rows[0].cols[i].value.toLowerCase().contains("netl-ed")) {
      if (r.cols[i].getValue() < 5) voldNetl = true;
      break;
    }
  }

  return voldNetl;
}

// Select all filters
public void selectDoubleren() {
  for (int i = 4; i < tb.filters.length; i++) {
    tb.filters[i].selected = tb.filters[3].selected;
  }
}

// Returns true if student meets all requirements
boolean onvoldoende = true;
boolean lagerVier = false;
public boolean meetRequirements(int index) {
  boolean meets = true;

  // Check all filters
  if (tb.filters[4].selected) meets = meets && !(onvoldoendes[index] > 3);
  if (tb.filters[5].selected) meets = meets && !lagerDanVier[index];
  if (klas >= 3 && tb.filters[6].selected) meets = meets && !lagerDanVijfHalf[index];
  if (tb.filters[7].selected) meets = meets && !lagerDanZes[index];
  if (((niveau == MAVO && klas == 3) || (niveau == HAVO && klas == 4) || (niveau == VWO && klas == 5)) && tb.filters[8].selected) meets = meets && !voldCkv[index];
  if ((niveau == HAVO || niveau == VWO) && tb.filters[9].selected) meets = meets && !eenVijfKern[index];
  if (niveau == MAVO && tb.filters[10].selected) meets = meets && !eenVijfNetl[index];
  
  return meets;
}
// this gives you a 2-dimensional array of strings
String[][] a;

// Select a file
public void selectFile() {
  selectInput("Kies een .csv bestand", "getFile");
}

// This method recieves the file
public void getFile(File file) {
  
  // If there is no file, exit
  if (file == null) exit();

  // Reselect if extension is not csv
  if (!getExtension(file.getPath()).equals("csv")) {
    selectFile();
    return;
  }

  // Just some sneaky check if it's a certain niveau
  if (file.getName().toLowerCase().contains("gymnasium")) niveau = 2;
  if (file.getName().toLowerCase().contains("atheneum")) niveau = 2;
  if (file.getName().toLowerCase().contains("havo")) niveau = 1;
  if (file.getName().toLowerCase().contains("mavo")) niveau = 0;


  // Reset the data
  ArrayList<String[]> temp = new ArrayList<String[]>();

  // Open a reader to read the file
  BufferedReader br;

  // Try to read the file and put all information into temporary variable temp
  try {

    // Load the file into the reader
    br = new BufferedReader(new FileReader(file));

    String line = "";


    while ((line = br.readLine()) != null) {
      boolean inQuotes = false;
      String cline = new String();

      // Replace all commas between brackets with dots
      for (int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == '"') inQuotes ^= true;
        if (line.charAt(i) == ',' && inQuotes) {
          cline += ".";
        } else {
          cline += line.charAt(i);
        }
      }

      // Split the strings in a certain way depending on the way it's split
      String[] values;
      if (cline.contains(";")) {
        values = cline.split(";");
      } else {
        values = cline.replace("\"", "").split(",");
      }

      // Add the String[] to the ArrayList
      temp.add(values);
    }

    // Close the reader
    br.close();
    jf.setTitle("Cijfers - " + file.getName());
  }
  catch (Exception e) {
    e.printStackTrace();
  }

  // Convert the arrayList to a fixed size String[][]
  a = new String[temp.size()][];
  for (int i = 0; i < temp.size(); i++) {
    a[i] = temp.get(i);
  }

  // Create the table
  tb = new table(a);
  
  // Get all arrays of info per student
  getAllArrays();
}
  public void settings() {  size(1280, 720);  smooth(1); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MagisterCijfersProcessing" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
