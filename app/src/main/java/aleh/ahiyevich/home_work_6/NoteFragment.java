package aleh.ahiyevich.home_work_6;


import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NoteFragment extends Fragment {

    // переменные CURRENT_NOTES и currentNotes нам нужны в качестве ключа и значения
    //для сохранения текущей позиции в списке
    private static final String CURRENT_NOTES = "Current_notes";
    // Текущая позиция (Выбраныная заметка)
    private int currentNotes = 0;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTES, currentNotes);
        super.onSaveInstanceState(outState);
    }

    // Указываем макет фрагмента при создании
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Восстановление текущей позиции (Выбранной заметки)
        if (savedInstanceState != null) {
            currentNotes = savedInstanceState.getInt(CURRENT_NOTES, 0);
        }

        //Выводим на экран проинициализированные элементы массива класса Note
        initNotes(view);

        // ЕСли ориентация ландшафтная, выводим сохраненный ранее выбранную заметку
        if (isLand()) {
            showDetailsLand(currentNotes);
        }
    }


    // Метод инициализации и вывода на экран элементов массива класса Note
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initNotes(View view) {
        LinearLayout layout = (LinearLayout) view;

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        for (int i = 0; i < Note.getNotes().length; i++) {
            TextView tvTittle = new TextView(getContext());
            tvTittle.setText(Note.getNotes()[i].getTittle());
            tvTittle.setTextSize(25);
            layout.addView(tvTittle);

            final int position = i;
            tvTittle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentNotes = position;
                    // Обработка нажатия и проверка ориентации экрана, после проверки запускается тот
                    // или иной метод вывода фрагмента на экран
                    if (isLand()) {
                        showDetailsLand(position);
                    } else {
                        showDetails(position);
                    }
                }
            });
        }
    }


    // Проверка на ориентацию экрана
    private boolean isLand() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    // Метод вывода фрагмента на экран, если ориентация портретная
    private void showDetails(int index) {
        NoteDetailsFragment detailsFragment = NoteDetailsFragment.newInstance(index);
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_fragment, detailsFragment);
        ft.addToBackStack("");
        ft.commit();
    }

    // Метод вывода фрагмента на экран, если ориентация ландшафтная
    private void showDetailsLand(int index) {
        NoteDetailsFragment detailsFragment = NoteDetailsFragment.newInstance(index);
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_fragment2, detailsFragment);
        ft.addToBackStack("");
        ft.commit();
    }


}