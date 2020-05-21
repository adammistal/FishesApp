package pl.codeApp4u.fishesapp.ui.content;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import pl.codeApp4u.fishesapp.R;
import pl.codeApp4u.fishesapp.domain.DictionaryItem;

public class ContentFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Inject
    protected ContentViewModel contentViewModel;

    private DictionaryItemAdapter adapter;


    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hasPermissions()) {
            requestPermission();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindSynButton(view);
        bindList(view);
        contentViewModel.items.observe(getViewLifecycleOwner(), this::updateList);
    }

    private void bindList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.content_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DictionaryItemAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void bindSynButton(View view) {
        view.findViewById(R.id.content_sync_btn).setOnClickListener(v -> {
            if (!hasPermissions()) {
                requestPermission();
                return;
            }
            contentViewModel.sync();
        });
    }

    private void updateList(List<DictionaryItem> dictionaryItems) {
        adapter.updateItems(dictionaryItems);
    }

    private boolean hasPermissions() {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void requestPermission() {
        requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                contentViewModel.sync();
            }
        }
    }
}
