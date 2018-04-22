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
  void show(float xp, float yp, float wp, float hp, int index) {

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
  boolean overFilter() {
    return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + hgth);
  }
}


// Select a klas
void chooseKlas(float xp, float yp, float wp, float hp) {
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
boolean overButton(float x, float y, float w, float h) {
  return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h);
}
