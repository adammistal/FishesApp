package pl.codeApp4u.fishesapp.ui;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import pl.codeApp4u.fishesapp.R;
import pl.codeApp4u.fishesapp.domain.SyncUseCase;
import pl.codeApp4u.fishesapp.ui.content.ContentFragment;

public class MainActivity extends AppCompatActivity implements HasAndroidInjector {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    protected SyncUseCase syncUseCase;

    @Inject
    protected DispatchingAndroidInjector<Object> androidInjector;

    private ProgressBar loader;

    private boolean startSync = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.loader);
        if (savedInstanceState == null) {
            sync();
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    public void sync() {
        startSync = true;
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (startSync) {
            compositeDisposable.add(
                    syncUseCase.execute()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::successSync, this::failureSync));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void failureSync(Throwable throwable) {
        Log.e("Error:", "syncUseCase: " + throwable);
        loader.setVisibility(View.GONE);
        Toast.makeText(this, "Error:" + throwable, Toast.LENGTH_SHORT).show();
    }

    private void successSync() {
        loader.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new ContentFragment())
                .commit();
    }
}
