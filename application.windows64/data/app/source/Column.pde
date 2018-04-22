class Column {
  String value;

  Column(String v) {
    value = v;
  }

  // Returns value
  float getValue() {
    return (float(value.replace(",", ".")));
  }

  void show(table t, float x, float y, float w, float h, int in, int oin) {
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
    if (float(value.replace(",", ".")) < 5.5) {
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
      if (!value.equals("") && oin != t.rows[0].cols.length-1) text(nf(float(value.replace(",", ".")), 1, 2).replace(".", ","), x + 2 + plus, y + h/2);
      if (!value.equals("") && oin == t.rows[0].cols.length-1) text(nf(float(value.replace(",", ".")), 1, 2).replace(".", ","), x + 2 + plus, y + h/2);
    } else {
      
      // Align text to the left
      textAlign(LEFT, CENTER);
      text(value.replace(",", "."), x + 5, y + h/2);
    }
  }
}
