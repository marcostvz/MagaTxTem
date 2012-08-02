package magatzem.txt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class CercaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MagaTxTem - Cerca");
        setContentView(R.layout.cerca);
    }
    
    public void push_enrere(View view){
    	Intent myIntent = new Intent(view.getContext(), MagaTxTemActivity.class);
        startActivityForResult(myIntent, 0);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){ //Fa que el botó back desde el main vagi al menu principal	
    	if (keyCode == KeyEvent.KEYCODE_SEARCH) {
        	Intent nextIntent = new Intent(this, CercaActivity.class);
            startActivityForResult(nextIntent, 1);
            //canviar a la finestra de cerca
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}