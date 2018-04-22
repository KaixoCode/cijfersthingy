import processing.awt.PSurfaceAWT.SmoothCanvas;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

JFrame jf;
table tb;
PImage img;

void setup() {
  size(1280, 720);

  // Load magister logo
  img = loadImage(dataPath("logo.png"));

  // Smooth the canvas
  smooth(1);

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
void draw() {
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
void mousePressed() {
  mousePress = true;
}
