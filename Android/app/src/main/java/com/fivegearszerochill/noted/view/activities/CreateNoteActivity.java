package com.fivegearszerochill.noted.view.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.ActivityCreateNoteBinding;
import com.fivegearszerochill.noted.editor.EditorControlBar;
import com.fivegearszerochill.noted.editor.datatypes.DraftDataItemModel;
import com.fivegearszerochill.noted.editor.models.DraftModel;
import com.fivegearszerochill.noted.editor.utils.FilePathUtils;
import com.fivegearszerochill.noted.repository.OnNoteInsertedCall;
import com.fivegearszerochill.noted.util.app.ModelHelper;
import com.fivegearszerochill.noted.viewmodel.NoteViewModel;
import com.fivegearszerochill.noted.viewmodel.factory.ViewModelParameterizedProvider;

import java.util.ArrayList;

import static com.fivegearszerochill.noted.editor.components.TextComponentItem.MODE_PLAIN;
import static com.fivegearszerochill.noted.editor.styles.TextComponentStyle.NORMAL;

public class CreateNoteActivity extends AppCompatActivity implements EditorControlBar.EditorControlListener, OnNoteInsertedCall {
    private final int REQUEST_IMAGE_SELECTOR = 110;

    private ActivityCreateNoteBinding binding;
    private NoteViewModel viewModel;
    private long notebookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_IMAGE_SELECTOR) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission not granted for image access", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onInsertImageClicked() {
        openGallery();
    }

    @Override
    public void onInsertLinkClicked() {
        Toast.makeText(this, "Fake link", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CreateNoteActivity.this, NotebookActivity.class));
    }

    @Override
    public void updateFailure() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        getIntentExtras();
        viewModel = ViewModelParameterizedProvider
                .ofActivity(this, getApplicationContext())
                .get(NoteViewModel.class);
    }

    private void initDraftHandler() {
        binding.cnFab.setOnClickListener(view -> {
            setNoteTitle();
            setNotebookId();
            viewModel.insertNote(ModelHelper.constructNote(binding.mdEditor.getDraft()), this);
        });
    }

    private void setNotebookId() {
        binding.mdEditor.getDraft().setNotebookId(this.notebookId);
    }

    private void setNoteTitle() {
        String title = binding.cnTitleEditText.getText().toString();
        binding.mdEditor.getDraft().setDraftTitle(title);
    }

    private void initEditor() {
        binding.controlBar.setEditorControlListener(this);
        binding.mdEditor.configureEditor(
                "",
                "",
                true,
                getString(R.string.type_here),
                NORMAL
        );
        binding.mdEditor.loadDraft(getDraftContent());
        binding.controlBar.setEditor(binding.mdEditor);
    }

    private void openGallery() {
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_IMAGE_SELECTOR);
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
        text.setContent("");
        text.setMode(MODE_PLAIN);
        text.setStyle(NORMAL);
        return new DraftModel(contentTypes);
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        this.notebookId = intent.getLongExtra("notebookId", 0);
    }
}