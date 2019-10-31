package jp.ac.shohoku.hguremetal.tese;

import androidx.appcompat.app.AppCompatActivity;
//AndroidX

import android.graphics.Bitmap;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;


public class MainActivity extends AppCompatActivity {
    private final static int RESULT_CAMERA = 1000;
    private ImageView imageView;

    // サウンドの部分
    SoundPool soundPool;
    int mp3a;
    int mp3b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // カメラの部分
        imageView = findViewById(R.id.image_view);

        Button cameraButton = findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RESULT_CAMERA);
                // ボタン押すとサウンドがなるよ
                soundPool.play(mp3a, 1f, 1f, 0, 0, 1f);
            }
        });

        // サウンドの部分
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }else{
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(5)
                    .build();
        }
        mp3a = soundPool.load(this, R.raw.a, 1);
        mp3b = soundPool.load(this, R.raw.a, 1);
    }

    // カメラの部分
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == RESULT_CAMERA){
            Bitmap bitmap;
            // cancelしたケースも含む
            if(data.getExtras()== null){
                Log.d("debug","cancel ?");
                return;
            }
            else{
                bitmap = (Bitmap) data.getExtras().get("data");
                if(bitmap != null){
                    // 画面サイズを計測
                    int bmpWidth = bitmap.getWidth();
                    int bmpHeight = bitmap.getHeight();
                    Log.d("debug",String.format("w= %d",bmpWidth));
                    Log.d("debug",String.format("h= %d",bmpHeight));
                }
            }

            imageView.setImageBitmap(bitmap);

        }
    }





}
