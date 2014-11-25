package com.example.BCCT;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//POrque onclick listener es para que la clase principal este pendiente de cada click que hace un usuario sobre un boton
public class MyActivity extends Activity implements OnClickListener{
    /**
     hacemos el llamado a la actividad principal
     */

    //Se declaran las variables scanbtn, contenttxt y formattxt para poder establecer algo similar a una instancia con la vista del xml
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
//responde a los click
        if(v.getId()==R.id.scan_button){
    //escanea
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retorna el resultado del escaneo

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {

            //tenemos el resultado
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se recibio ninguna información sobre el barcode!", Toast.LENGTH_SHORT);
            toast.show();
        }

        String scanContent = scanningResult.getContents();
        String scanFormat = scanningResult.getFormatName();

        formatTxt.setText("FORMAT: " + scanFormat);
        contentTxt.setText("CONTENT: " + scanContent);


    }
}
