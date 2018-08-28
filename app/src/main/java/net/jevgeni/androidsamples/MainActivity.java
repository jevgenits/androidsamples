package net.jevgeni.androidsamples;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import net.jevgeni.androidsamples.toast.ToastManager;

public class MainActivity extends AppCompatActivity {

    private ToastManager toastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastManager = new ToastManager();
        toastManager.printText("Hello world!", MainActivity.this);
    }

    public void imageClicked(View view) {
        final ImageView image = findViewById(R.id.imageView);
        if (isSameImage(image, android.R.drawable.btn_star_big_on)) {
            animateImage(image, android.R.drawable.btn_star_big_off);
        } else {
            animateImage(image, android.R.drawable.btn_star_big_on);
        }
    }

    void animateImage(ImageView image, int imageId) {
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

    boolean isSameImage(ImageView imageView, int imageResource) {
        if (imageView == null) {
            return false;
        }

        Drawable.ConstantState constantState = MainActivity.this.getResources()
            .getDrawable(imageResource, MainActivity.this.getTheme())
            .getConstantState();
        return imageView.getDrawable().getConstantState() ==  constantState;
    }
}
