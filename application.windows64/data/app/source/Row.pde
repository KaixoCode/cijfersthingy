class Row {
  Column[] cols;

  Row(String[] values) {
    cols = new Column[values.length];
    for (int i = 0; i < cols.length; i++) {
      cols[i] = new Column(values[i]);
    }
  }


  void show(table t, int amC, float y, float h, int in) {

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
