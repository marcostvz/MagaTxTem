package magatzem.txt;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MagaTxTemActivity extends Activity {
    String url = "null";
    Intent nextIntent;
    Bundle sis;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sis = savedInstanceState;
        setTitle("MagaTxTem");
        setContentView(R.layout.main);
    }
    
    public void push_inventari(View view){
    	nextIntent = new Intent(view.getContext(), InventariActivity.class);
        startActivityForResult(nextIntent, 0);
    }
    
    public void push_cerca(View view){
    	nextIntent = new Intent(view.getContext(), CercaActivity.class);
        startActivityForResult(nextIntent, 1);
    }
    
    public void push_qr(View view){
    	IntentIntegrator integrator = new IntentIntegrator(this);
    	nextIntent = new Intent(view.getContext(), QrActivity.class);
    	integrator.initiateScan();
    }    
    
    public void refresh(View view){
    	Intent myIntent = new Intent(view.getContext(), MagaTxTemActivity.class);
        startActivityForResult(myIntent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	if (scanResult != null) {
    	    url = scanResult.getContents();
    	}
      	if (url != null){
    	    Intent i = new Intent(this, QrActivity.class);
    	    i.putExtra("url", url);
    	    startActivityForResult(i, 3);
        }
      	else setContentView(R.layout.main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){ //Fa que el botó back desde el main vagi al menu principal
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }    	
    	if (keyCode == KeyEvent.KEYCODE_SEARCH) {
        	nextIntent = new Intent(this, CercaActivity.class);
            startActivityForResult(nextIntent, 1);
            //canviar a la finestra de cerca
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}