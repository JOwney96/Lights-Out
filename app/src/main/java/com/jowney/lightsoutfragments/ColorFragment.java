package com.jowney.lightsoutfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

public class ColorFragment extends Fragment {
    private int colorId;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_color, container, false);

        SharedPreferences sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE);
        colorId = sharedPrefs.getInt("color", R.color.yellow);

        int radioId = R.id.color_radio_yellow;
        if (colorId == R.color.blue)
            radioId = R.id.color_radio_blue;
        else if (colorId == R.color.green)
            radioId = R.id.color_radio_green;
        else if (colorId == R.color.red)
            radioId = R.id.color_radio_red;

        RadioButton button = rootView.findViewById(radioId);
        button.setChecked(true);

        RadioGroup radioGroup = rootView.findViewById(R.id.color_radio_group);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            button = (RadioButton) radioGroup.getChildAt(i);
            button.setOnClickListener(this::onColorClick);
        }

        return rootView;
    }

    private void onColorClick(@NotNull View view) {
        int id = view.getId();

        if (id == R.id.color_radio_yellow)
            colorId = R.color.yellow;
        else if (id == R.id.color_radio_blue)
            colorId = R.color.blue;
        else if (id == R.id.color_radio_green)
            colorId = R.color.green;
        else if (id == R.id.color_radio_red)
            colorId = R.color.red;

        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("color", colorId);
        editor.apply();
    }
}