
// Get all CE vakken
String[] noCEVakken = {"ckv-ed", "anw-ed", "bsm-ed", "lo-ed"};
String[] kernvakken = {"netl-ed", "entl-ed", "wisa-ed", "wisb-ed", "wisc-ed"};

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


void getAllArrays() {
  int begI = 2;
  int endI = tb.rows.length-2;
  
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


int klas = 0;
void getKlas() {
  String vak = tb.rows[0].cols[1].value;
  klas = int(vak.replace(vak.replaceAll("[0-9]", ""), ""));
  println(klas);
}

void getCEVakken(Row r) {
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
int getOnvoldoendes(Row r) {
  int amt = 0;

  // Get amount of onvoldoendes
  for (int i = 1; i < r.cols.length-1; i++) {
    if (r.cols[i].getValue() < 5.5) amt++;
  }
  return amt;
}

// Returns true if a grade is lower than 4
boolean getLagerDanVier(Row r) {
  boolean lager = false;

  // Test if a grade is lower than 4
  for (int i = 1; i < r.cols.length-1; i++) {
    if (r.cols[i].getValue() < 4) lager = true;
  }

  return lager;
}

// Returns true if a grade is lower than 5.5
boolean getLagerDanVijfHalf(Row r) {
  return r.cols[r.cols.length-1].getValue() <= 5.5;
}

// Returns true if average grade is lower than 6
boolean getLagerDanZes(Row r) {

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

boolean getVoldCkv(Row r) {
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
boolean getVijfKern(Row r) {
  int total = 0;

  for (int i = 1; i < r.cols.length-1; i++) {

    // Go through all kernvakken
    for (int j = 0; j < kernvakken.length; j++) {

      // If the grade is a kernvak test if lower than 5.5
      if (tb.rows[0].cols[i].value.toLowerCase().contains(kernvakken[j])) {
        if (!r.cols[i].value.equals("") && r.cols[i].getValue() < 5.5) 
          total++;
      }
    }
  }

  return total > 1;
}

// Min of 5 for netl for mavo
boolean getVijfNetl(Row r) {
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
void selectDoubleren() {
  for (int i = 4; i < tb.filters.length; i++) {
    tb.filters[i].selected = tb.filters[3].selected;
  }
}

// Returns true if student meets all requirements
boolean onvoldoende = true;
boolean lagerVier = false;
boolean meetRequirements(int index) {
  boolean meets = true;

  // Check all filters
  if (tb.filters[4].selected) meets = meets && !(onvoldoendes[index] >= 3);
  if (tb.filters[5].selected) meets = meets && !lagerDanVier[index];
  if (klas >= 3 && tb.filters[6].selected) meets = meets && !lagerDanVijfHalf[index];
  if (tb.filters[7].selected) meets = meets && !lagerDanZes[index];
  if (((niveau == MAVO && klas == 3) || (niveau == HAVO && klas == 4) || (niveau == VWO && klas == 5)) && tb.filters[8].selected) meets = meets && !voldCkv[index];
  if ((niveau == HAVO || niveau == VWO) && tb.filters[9].selected) meets = meets && !eenVijfKern[index];
  if (niveau == MAVO && tb.filters[10].selected) meets = meets && !eenVijfNetl[index];
  
  return meets;
}
