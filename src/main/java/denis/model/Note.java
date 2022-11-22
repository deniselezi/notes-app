package denis.model;

import static denis.util.FileUtil.*;
import static denis.util.StringUtil.isValid;

public class Note implements Comparable<Note> {
    private String name;
    private final String dateAdded;
    private String content;
    Summary summary;

    public Note(String name, String dateAdded) {
        this.name = name;
        this.dateAdded = dateAdded;
        summary = fetchSummary();
    }

    @Override
    public int compareTo(Note note) {
        return name.compareTo(note.getName());
    }

    private static class Summary {
        private String name;
        private String content;

        public Summary(String name) {
            this.name = name;
            readSummary();
        }

        public void setName(String newSummaryPath) {
            String currentPath = this.name;
            this.name = newSummaryPath;
            renameFile(currentPath, newSummaryPath);
        }

        private void readSummary() {
            content = readFile(name);
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
            overwriteFile(name, content);
        }
    }

    public Summary fetchSummary() {
        String summaryName = "notes/summaries/" + name + "Summary.txt";  // path to txt file of summary
        return new Summary(summaryName);
    }

    // returns content of note txt file, whether it be an image, plain text or url
    public void readContent() {
        String path = "notes/" + name + ".txt";
        content = readFile(path);
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        String invalidCharacters = "< >:/|?*\\";
        if (!isValid(name, invalidCharacters) || name.length() > 30) {
            return false;
        }
        String currentNotePath = "notes/" + this.name + ".txt";
        String newNotePath = "notes/" + name + ".txt";
        this.name = name;
        String newSummaryPath = "notes/summaries/" + name + "Summary.txt";
        renameFile(currentNotePath, newNotePath);
        summary.setName(newSummaryPath);
        return true;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        String path = "notes/" + name + ".txt";
        overwriteFile(path, content);
    }

    public String getSummary() {
        return summary.getContent();
    }

    public void setSummary(String summaryText) {
        summary.setContent(summaryText);
    }
}
