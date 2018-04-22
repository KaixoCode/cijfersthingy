float hw = 100;
float hh = 20;
float nameW = 200;

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
    filters[4] = new Filter(" - Minder dan 3 onvoldoendes");
    filters[5] = new Filter(" - Niet lager dan een 4");
    filters[6] = new Filter(" - Gem. CE vakken hoger dan 5.5 (vanaf klas 3)");
    filters[7] = new Filter(" - Gem. alle vakken hoger dan 6");
    filters[8] = new Filter(" - 6 of hoger voor CKV (mavo 3/havo 4/vwo 5)");
    filters[9] = new Filter(" - Max één 5 voor kernvakken (havo/vwo)"); 
    filters[10] = new Filter(" - Min 5 voor netl (mavo)");
  }


  void show() {
    w = width - 2 * stroke - width/4;
    h = height - 4 * stroke - 20;

    // Grey header
    noStroke();
    fill(44);
    rect(0, 0, width, stroke * 2);

    // Magister logo
    imageMode(CORNER);
    image(img, 10, 0, stroke * 2 * 2.68062827, stroke * 2);

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
  void showSettings() {
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
  color scrollFill = color(197);

  // Show the scrollbar
  void showScrollBar() {


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
  boolean overScrollBar(float x, float y, float w, float h) {
    return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h);
  }


  // Show the table with all the information in it
  void showTable() {

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
