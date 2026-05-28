package kr.ac.kopo.dialogtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    boolean[] checkArr = {true,false,false};
    String[] itemArr = {"pizza", "chicken", "pasta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(showDialogListener);
    }
    View.OnClickListener showDialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("change button label");
            dialog.setIcon(R.drawable.icon2);
            // 항목으로 구성된 대화상자(체크박스)
            // 체크박스가 선택된 항목이 모두 버튼의 텍스트로 보이게
            dialog.setMultiChoiceItems(itemArr, checkArr, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    btn.setText("");
                    if(isChecked)
                        checkArr[which] = true;

                    for(int i = 0; i<checkArr.length; i++){
                        if(checkArr[i])
                            btn.append(itemArr[i] + "::");
                    }

                }
            });
//             항목으로 구성된 대화상자(라디오버튼)
//            dialog.setSingleChoiceItems(itemArr,0, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    btn.setText(itemArr[which]);
//                }
//            });
//             항목으로 구성된 대화상자(
//            dialog.setItems(itemArr, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    btn.setText(itemArr[which]);
//                }
//            });
            dialog.setNegativeButton("close",null);
            dialog.show();


        }
    };
}