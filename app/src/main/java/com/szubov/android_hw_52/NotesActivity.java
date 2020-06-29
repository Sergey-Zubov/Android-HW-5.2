package com.szubov.android_hw_52;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity{
    private EditText mEditTextNote;
    private SharedPreferences mNoteSharedPref;
    private final static String NOTE_TEXT = "note_text";
    private final static String MY_NOTE = "my_note";
    private Button mBtnSaveNote;
    private Button mBtnReturnFromNotes;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        initViews();
        getNoteFromSharedPref();
    }

    private void initViews() {
        mEditTextNote = findViewById(R.id.editTextNote);
        mNoteSharedPref = getSharedPreferences(MY_NOTE, MODE_PRIVATE);
        mBtnSaveNote = findViewById(R.id.btnSaveNote);
        mBtnReturnFromNotes = findViewById(R.id.btnReturnFromNotes);

        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor myEditor = mNoteSharedPref.edit();
                Log.i(TAG, "User clicked btn saveNote in NotesActivity");

                try {
                    if (mEditTextNote.getText().toString().trim().length() < 1) {
                        Toast.makeText(NotesActivity.this, getText(R.string.edit_text_is_empty),
                                Toast.LENGTH_LONG).show();
                    } else if (mEditTextNote.getText().toString().trim().length() > 1500) {
                        Toast.makeText(NotesActivity.this, getText(R.string.edit_text_is_too_big),
                                Toast.LENGTH_LONG).show();
                    } else {
                        myEditor.putString(NOTE_TEXT, mEditTextNote.getText().toString().trim());
                        myEditor.apply();
                        mEditTextNote.getText().clear();
                        Toast.makeText(NotesActivity.this, getText(R.string.text_saved),
                                Toast.LENGTH_LONG).show();

                    }
                } catch (Exception ex) {
                    Toast.makeText(NotesActivity.this,
                            R.string.exception_btn_ok_save, Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Btn saveNote exception in NotesActivity", ex);
                }

            }
        });

        mBtnReturnFromNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getNoteFromSharedPref() {
        String noteTxt = mNoteSharedPref.getString(NOTE_TEXT, "");
        mEditTextNote.setText(noteTxt);
    }
}
