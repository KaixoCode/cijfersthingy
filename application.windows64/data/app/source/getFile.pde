// this gives you a 2-dimensional array of strings
String[][] a;

// Select a file
void selectFile() {
  selectInput("Kies een .csv bestand", "getFile");
}

void getFile(File file) {
  if (file == null) exit();

  // Reselect if extension is wrong
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

  // Try to read the file and put all information into variable a
  try {

    // Load the file into the reader
    br = new BufferedReader(new FileReader(file));

    String line = "";


    while ((line = br.readLine()) != null) {
      boolean inQuotes = false;
      String cline = new String();
      for (int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == '"') inQuotes ^= true;
        if (line.charAt(i) == ',' && inQuotes) {
          cline += ".";
        } else {
          cline += line.charAt(i);
        }
      }

      String[] values = cline.replace("\"", "").split(",");

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
  getAllArrays();
}
