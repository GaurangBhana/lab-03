package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    int position;
    City city;
    interface EditCityDialogListener {
        void editCity(int position, City city);
    }

    EditCityFragment(int position, City city){
        super();
        this.position = position;
        this.city = city;
    }

    private EditCityFragment.EditCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof EditCityFragment.EditCityDialogListener){
            listener = (EditCityFragment.EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + "must implement EditCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityText = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceText = view.findViewById(R.id.edit_text_province_text);
        editCityText.setText(this.city.getName());
        editProvinceText.setText(this.city.getProvince());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit a City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Edit", (DialogInterface dialog, int which) ->  {
                    String cityName = editCityText.getText().toString();
                    String provinceName = editProvinceText.getText().toString();
                    listener.editCity(this.position, new City(cityName, provinceName));
                })
                .create();
    }
}
