package denis.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static denis.util.FileUtil.*;

public class NotesList {
    public static final String path = "notes/notes.csv";
    private static final String COMMA_DELIMITER = ",";
    private final ArrayList<Note> notes;

    public NotesList() {
        notes = new ArrayList<>();
        populateList();
    }

    private void populateList() {
        // reads filenames in notes.csv and creates note objects for each file,
        // appends them to notes array
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Note note = new Note(values[0], values[1]);
                notes.add(note);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addNote() {
        String noteName = getRandomNoteName();
        String notePath = "notes/" + noteName + ".txt";
        String summaryPath = "notes/summaries/" + noteName + "Summary.txt";
        createFile(notePath);  // create note and summary
        createFile(summaryPath);
        Date currentDate = new Date();
        SimpleDateFormat strFormat = new SimpleDateFormat("dd/MM/yy");
        String currentDateFormatted = strFormat.format(currentDate);
        String newCSVLine = noteName + "," + currentDateFormatted + "\n";
        appendToFile(path, newCSVLine);
        notes.add(new Note(noteName, currentDateFormatted));
    }

    public void editLine(int lineNumber, String newLine) throws IOException {
        replaceLine(path, newLine, lineNumber);
    }

    private String getRandomNoteName() {
        boolean foundAName = false;
        String name = null;
        while (!foundAName) {
            Random rand = new Random();
            name = "note" + rand.nextInt(1000);
            foundAName = true;
            for (Note note : notes) {
                if (note.getName().equals(name)) {
                    foundAName = false;
                    break;
                }
            }
        }
        return name;
    }

    public ArrayList<Note> getSearchResult(String searchInput) {
        ArrayList<Note> searchResult = new ArrayList<>();
        for (Note note : notes) {
            if (note.getName().contains(searchInput)) {
                searchResult.add(note);
            }
        }
        return searchResult;
    }

    public void deleteNote(int lineNumber) {
        String noteName = getNoteName(lineNumber);
        String notePath = "notes/" + noteName + ".txt";
        String summaryPath = "notes/summaries/" + noteName + "Summary.txt";
        String newCSV = readFileWithException(path, lineNumber + 1);
        overwriteFile(path, newCSV);
        deleteFile(notePath);  // delete both note file and summary file
        deleteFile(summaryPath);
        notes.remove(lineNumber);
    }

    private String readFileWithException(String path, int lineNumber) {
        // reads all lines from a file except for the line at lineNumber
        int counter = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if (lineNumber != counter) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
                line = br.readLine();
                counter++;
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNoteName(int lineNumber) {
        String listLocation = "notes/notes.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(listLocation))) {
            for (int i = 0 ; i < lineNumber ; i++) {
                br.readLine();
            }
            String line = br.readLine();
            return line.split(COMMA_DELIMITER)[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
