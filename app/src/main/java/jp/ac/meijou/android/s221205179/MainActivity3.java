package jp.ac.meijou.android.s221205179;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijou.android.s221205179.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s221205179.databinding.ActivitySubBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    private final ActivityResultLauncher<Intent>getActivityResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult() ,
            result -> {
                switch(result.getResultCode()){
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data->data.getStringExtra("ret"))
                                .map(text->"Result :"+text)
                                .ifPresent(text->binding.textView2.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.textView2.setText("Result: Canceled");
                        break;
                    default:
                        binding.textView2.setText("Result: Unknown("+result.getResultCode()+")");
                        break;
                }

            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //明示的インテント
        binding.button.setOnClickListener(view->{
            var intent=new Intent(this,SubActivity.class);
            startActivity(intent);
        });

        //暗黙的インテント
        binding.button17.setOnClickListener(view->{
            var intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        //文字を送信
        binding.button18.setOnClickListener(view->{
            var intent=new Intent(this,SubActivity.class);
            intent.putExtra("text",binding.editTextText2.getText().toString());
            startActivity(intent);
        });

        //結果を取得
        binding.button19.setOnClickListener(view->{
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });

    }

}