package denis.servlets;

import denis.model.Note;
import denis.model.NotesList;
import denis.model.NotesListFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/note")
public class NoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String noteName = request.getParameter("name");
        // fetch note and pass onto note.jsp, so note.jsp can render its name and its content
        NotesList notesList = NotesListFactory.getNotesList();
        ArrayList<Note> notes = notesList.getNotes();
        int i;
        for (i = 0 ; i < notes.size() ; i++) {
            if (notes.get(i).getName().equals(noteName)) {
                break;
            }
        }
        Note note = notes.get(i);
        note.readContent();  // save content of note to object so it can be displayed in jsp file
        request.setAttribute("note", note);

        RequestDispatcher rd = request.getRequestDispatcher("note.jsp");
        rd.forward(request, response);
    }
}
