package com.example.vardan.sharedpreferenceexercices;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import java.util.Objects;

public class NewDialogFragment extends android.support.v4.app.DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.my_dialog_fragment, container, false);
        final EditText editKey = view.findViewById(R.id.edit_key_id);
        final EditText editValue = view.findViewById(R.id.edit_value_id);
        final Button saveButton = view.findViewById(R.id.save_button_id);

        saveClick(editKey, editValue, saveButton);
        return view;
    }

    private void saveClick(final EditText editKey, final EditText editValue, Button save) {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Model model = new Model(editKey.getText().toString(), editValue.getText().toString());
                ((MainActivity) Objects.requireNonNull(getActivity())).getList().add(model);
                dismiss();
            }
        });
    }
}
