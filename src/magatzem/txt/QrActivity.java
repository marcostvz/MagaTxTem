package magatzem.txt;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class QrActivity extends Activity {
	String root_url = "http://txt.upc.edu/magatxtem/";
    String uuid;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MagaTxTem - Captura QR");
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.qr);
        if(extras !=null)
        {
            String url = extras.getString("url");
            if (url.indexOf(root_url)!=-1){
	            uuid = url.substring(root_url.length(), url.length()); //agafem el numeret!
	            //test
	            	EditText txt = (EditText) findViewById(R.id.equip);
	            	txt.setText(uuid);
	            //fi_test
	            /*en aquest punt hauriem de:
	             * cridar a la web per a que ens retorni les dades de l'equip
	             * botó de modificar estat -> (Ubicació -> estanteria -> pis o bé a Reparació o Instalat o Reciclat)
	            */
            }
            else{
            	//el qr escannejat no es de magaTxTem, enviar a navegador
            	Intent i = new Intent("android.intent.action.VIEW", Uri.parse(url));
                startActivity(i);
            }
        }
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
