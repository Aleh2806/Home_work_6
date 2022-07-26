package aleh.ahiyevich.home_work_6;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Note {

    static Random random = new Random();
    private static Note[] notes;

    private String tittle;
    private String description;
    private LocalDateTime creationDate;


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public static Note[] getNotes() {
        return notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    static {
        notes = new Note[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.getNote(i);
        }
    }

    public Note(String tittle, String description, LocalDateTime creationDate) {
        this.tittle = tittle;
        this.description = description;
        this.creationDate = creationDate;
    }


    @SuppressLint("DefaultLocale")
    public static Note getNote(int index){
        String tittle = String.format("Заметка %d",index + 1);
        String description = String.format("Описание заметки %d",index + 1);
        LocalDateTime creationDate = LocalDateTime.now().plusDays(-random.nextInt(5));
        return new Note(tittle,description,creationDate);
    }
}
