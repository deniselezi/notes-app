package denis.model;

public class NotesListFactory {
    private static NotesList notesList;

    public static NotesList getNotesList()
    {
        if (notesList == null)
        {
            notesList = new NotesList();
        }
        return notesList;
    }
}
