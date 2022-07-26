package aleh.ahiyevich.home_work_6;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NoteDetailsFragment extends Fragment {

    static final String ARG_INDEX = "index";

    // Имитируем нажатие кнопки назад
    // Уничтожаем предыдущий фрагмент при повороте экрана
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_details, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        if (args != null) {
            int index = args.getInt(ARG_INDEX);

            // Делаем поиск поля заголовка заметки
            TextView tvTittle = view.findViewById(R.id.tittle);
            // Изменяем имя заметки в поле  tvTittle
            tvTittle.setText(Note.getNotes()[index].getTittle());

            // Делаем поиск поля описания заметки
            TextView tvDescription = view.findViewById(R.id.description);
            // Изменяем описание заметки в поле  tvDescription
            tvDescription.setText(Note.getNotes()[index].getDescription());
        }

    }

    // Фабричный метод создания фрагмента
    public static NoteDetailsFragment newInstance(int index) {
        // Создание фрагмента
        NoteDetailsFragment detailsFragment = new NoteDetailsFragment();
        // Передача параметра через бандл
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

}