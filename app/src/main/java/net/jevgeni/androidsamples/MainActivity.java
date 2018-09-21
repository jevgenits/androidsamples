package net.jevgeni.androidsamples;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import net.jevgeni.androidsamples.toasts.ToastManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToastManager toastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastManager = new ToastManager();
        toastManager.printText("Hello world!", MainActivity.this);

        setupSeekBar();
        setupListView();
    }

    public void imageClicked(View view) {
        ImageView image = findViewById(R.id.imageView);
        if (isSameImage(image, android.R.drawable.btn_star_big_on)) {
            animateImage(image, android.R.drawable.btn_star_big_off);
        } else {
            animateImage(image, android.R.drawable.btn_star_big_on);
        }
    }

    private void setupSeekBar() {
        final ImageView image = findViewById(R.id.imageView);
        SeekBar bar = findViewById(R.id.rotationSeekBar);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress,
                                          final boolean fromUser) {
                image.animate().rotation(progress);
            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }
        });
    }

    private void animateImage(ImageView image, int imageId) {
        final ImageView animatedImage = image;
        final int animatedImageId = imageId;
        animatedImage.animate()
            .alpha(0f)
            .setDuration(1000);
        animatedImage.animate()
            .rotationBy(360f)
            .scaleX(-0.5f)
            .scaleY(-0.5f)
            .setDuration(500);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                animatedImage.animate()
                    .rotationBy(360f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(500);
                animatedImage.setImageResource(animatedImageId);
                animatedImage.animate()
                    .alpha(1f)
                    .setDuration(1000);
            }
        }, 1000);
    }

    private boolean isSameImage(ImageView imageView, int imageResource) {
        if (imageView == null) {
            return false;
        }

        Drawable.ConstantState constantState = MainActivity.this.getResources()
            .getDrawable(imageResource, MainActivity.this.getTheme())
            .getConstantState();
        return imageView.getDrawable().getConstantState() ==  constantState;
    }

    private void setupListView(){
        ListView myListView = findViewById(R.id.myListView);
        final ArrayList<String> items = new ArrayList<>();
        items.add("one");
        items.add("two");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = "Item tapped " + items.get(i);
                Log.i("App", text);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                adapterView.setVisibility(View.INVISIBLE);
            }
        });
    }
}
