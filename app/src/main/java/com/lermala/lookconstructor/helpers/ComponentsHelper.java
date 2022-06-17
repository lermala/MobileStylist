package com.lermala.lookconstructor.helpers;

import android.graphics.Color;
import android.text.method.KeyListener;
import android.widget.EditText;

public class ComponentsHelper {
    // https://stackoverflow.com/questions/10933056/edit-text-key-listener
    private KeyListener listener;
    private EditText editText;

    public ComponentsHelper(EditText editText) {
        this.editText = editText;
        listener = editText.getKeyListener(); // Save the default KeyListener!!!
    }

    public void disableEditText() {
        //editText.setFocusable(false);
        // editText.setEnabled(false);
        //editText.setCursorVisible(false);
        editText.setKeyListener(null); // this
        //editText.setBackgroundColor(Color.TRANSPARENT);
        // editText.setInputType(InputType.TYPE_NULL);
    }

    public void ableEditText(){
        editText.setKeyListener(listener);
    }
}
