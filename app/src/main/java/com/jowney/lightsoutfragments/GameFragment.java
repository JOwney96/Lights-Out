package com.jowney.lightsoutfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

public class GameFragment extends Fragment {
    private final String GAME_STATE_TAG = "GAME_STATE";
    private final LightsOutGame lightsOutGame = new LightsOutGame();
    private GridLayout lightsOutGrid;
    private int onColor;
    private int offColor;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString(GAME_STATE_TAG);
            lightsOutGame.setState(state);
        } else
            lightsOutGame.newGame();

        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        onColor = sharedPreferences.getInt("color", R.color.yellow);
        offColor = R.color.black;

        lightsOutGrid = rootView.findViewById(R.id.game_board_layout);
        for (int i = 0; i < lightsOutGrid.getChildCount(); i++) {
            Button button = (Button) lightsOutGrid.getChildAt(i);
            button.setOnClickListener(this::onLightButtonClick);
        }

        Button newGameButton = rootView.findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(v -> startGame());

        setButtonColors();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE_TAG, lightsOutGame.getState());
    }
    
    private void startGame() {
        lightsOutGame.newGame();
        setButtonColors();
    }

    private void onLightButtonClick(View view) {
        int buttonIndex = lightsOutGrid.indexOfChild(view);
        int row = buttonIndex / LightsOutGame.GRID_SIZE;
        int col = buttonIndex % LightsOutGame.GRID_SIZE;

        lightsOutGame.selectLight(row, col);
        setButtonColors();

        if (lightsOutGame.isGameOver()) {
            Toast.makeText(this.requireActivity(), "Congrats!\n You Won", Toast.LENGTH_SHORT).show();
        }
    }

    private void setButtonColors() {
        for (int i = 0; i < lightsOutGrid.getChildCount(); i++) {
            int row = i / LightsOutGame.GRID_SIZE;
            int col = i % LightsOutGame.GRID_SIZE;

            Button button = (Button) lightsOutGrid.getChildAt(i);
            if (lightsOutGame.isLightOn(row, col))
                button.setBackgroundColor(ContextCompat.getColor(this.requireActivity(), onColor));
            else
                button.setBackgroundColor(ContextCompat.getColor(this.requireActivity(), offColor));
        }
    }
}