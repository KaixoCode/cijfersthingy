// this gives you a 2-dimensional array of strings
String[][] a;

// Select a file
void selectFile() {
  selectInput("Kies een .csv bestand", "getFile");
}

// This method recieves the file
void getFile(File file) {
  
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
