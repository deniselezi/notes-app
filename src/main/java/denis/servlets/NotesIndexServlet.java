package denis.servlets;

import denis.model.Note;
import denis.model.NotesList;
import denis.model.NotesListFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static denis.util.StringUtil.isValid;

@WebServlet("/notes.html")
public class NotesIndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NotesList notesList = NotesListFactory.getNotesList();
        ArrayList<Note> notes = notesList.getNotes();

        if (request.getParameter("sort") != null) {
            if (request.getParameter("sort").equals("name")) {
                ArrayList<Note> notesCopy = new ArrayList<>(notes);
                Collections.sort(notesCopy);
                request.setAttribute("notes", notesCopy);
            } else if (request.getParameter("sort").equals("date")) {
                request.setAttribute("notes", notes);
            }
        } else if (request.getParameter("searchbar") != null) {
            String search = request.getParameter("searchbar");
            ArrayList<Note> searchResult = notesList.getSearchResult(search);
            request.setAttribute("notes", searchResult);
        } else {
            request.setAttribute("notes", notes);
        }

        RequestDispatcher rd = request.getRequestDispatcher("notesIndex.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NotesList notesList = NotesListFactory.getNotesList();

        if (request.getParameter("button") != null) {
            if (request.getParameter("button").equals("add")) {
                notesList.addNote();
            }
            else {
                String noteName = request.getParameter("button");
                ArrayList<Note> notes = notesList.getNotes();
                int i = getIndex(notes, noteName);
                notesList.deleteNote(i);
            }
        } else {  // request must've been 'save'
            String oldName = request.getParameter("save");
            String newName = request.getParameter("name");
            String newContent = request.getParameter("content");
            String newSummary = request.getParameter("summary");
            ArrayList<Note> notes = notesList.getNotes();

            boolean nameAlreadyExists = false;
            for (Note note : notes) {
                if (note.getName().equals(newName)) {
                    nameAlreadyExists = true;
                    break;
                }
            }

            int i = getIndex(notes, oldName);
            Note note = notes.get(i);

            if (!nameAlreadyExists) {
                if (note.setName(newName)) {
                    // find and update note in csv
                    Date currentDate = new Date();
                    SimpleDateFormat strFormat = new SimpleDateFormat("dd/MM/yy");
                    String currentDateFormatted = strFormat.format(currentDate);
                    String newCSVLine = newName + "," + currentDateFormatted;
                    notesList.editLine(i + 1, newCSVLine);
                }
            }

            note.setContent(newContent);
            note.setSummary(newSummary);
        }

        ArrayList<Note> notes = notesList.getNotes();
        request.setAttribute("notes", notes);  // pass list of Note objects to index, so they can be rendered

        RequestDispatcher rd = request.getRequestDispatcher("notesIndex.jsp");
        rd.forward(request, response);
    }

    private int getIndex(ArrayList<Note> notes, String noteName) {
        int i;
        for (i = 0 ; i < notes.size() ; i++) {
            if (notes.get(i).getName().equals(noteName)) {
                break;
            }
        }
        return i;
    }
}
