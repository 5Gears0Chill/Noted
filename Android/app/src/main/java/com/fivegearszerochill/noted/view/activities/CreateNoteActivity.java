package com.fivegearszerochill.noted.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fivegearszerochill.noted.databinding.ActivityCreateNoteBinding;
import com.fivegearszerochill.noted.editor.EditorControlBar;
import com.fivegearszerochill.noted.editor.datatypes.DraftDataItemModel;
import com.fivegearszerochill.noted.editor.models.DraftModel;
import com.fivegearszerochill.noted.editor.utils.FilePathUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_PLAIN;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.NORMAL;

public class CreateNoteActivity extends AppCompatActivity implements EditorControlBar.EditorControlListener {
    private final int REQUEST_IMAGE_SELECTOR = 110;

    private ActivityCreateNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initEditor();
        initDraftHandler();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_SELECTOR) {
            if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();
                String filePath = FilePathUtils.getPath(this, uri);
                addImage(filePath);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_IMAGE_SELECTOR:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                    //Toast.makeText()"Permission not granted to access images.");
                    Toast.makeText(this, "Permission not granted for image access", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onInsertImageClicked() {
        openGallery();
    }

    @Override
    public void onInsertLinkClicked() {
        Toast.makeText(this, "I WAS Clicked", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    private void initDraftHandler() {
        binding.cnFab.setOnClickListener(view -> getDraft());
    }

    private void initEditor() {
        binding.controlBar.setEditorControlListener(this);
        binding.mdEditor.configureEditor(
                "",//server url for image upload
                "",              //serverToken
                true,           // isDraft: set true when you are loading draft
                "Type here...", //default hint of input box
                NORMAL
        );
        binding.mdEditor.loadDraft(getDraftContent());
        binding.controlBar.setEditor(binding.mdEditor);
    }

    private void openGallery() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_SELECTOR);
            } else {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_IMAGE_SELECTOR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addImage(String filePath) {
        binding.mdEditor.insertImage(filePath);
    }

    private DraftModel getDraftContent() {
        ArrayList<DraftDataItemModel> contentTypes = new ArrayList<>();
        DraftDataItemModel text = new DraftDataItemModel();
        text.setItemType(DraftModel.ITEM_TYPE_TEXT);
        text.setContent("Your note...");
        text.setMode(MODE_PLAIN);
        text.setStyle(NORMAL);

        return new DraftModel(contentTypes);
    }

    private void getDraft() {
        DraftModel dm = binding.mdEditor.getDraft();
        String json = new Gson().toJson(dm);
        Log.d("MarkDEditor", json);
    }
}