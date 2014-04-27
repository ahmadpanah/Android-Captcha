package ir.Hossein.Captcha;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements Button.OnClickListener{
	
	protected static final int UPDATA_CHECKNUM = 0x101; 
	
	CheckAction mCheckView ;
	TextView mShowPassViwe;
	EditText mEditPass;
	Button mSubmit;
	Button mRef;
	
	int [] checkNum = {0,0,0,0};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView();
        initCheckNum();
    }
    
    public void initView()
    {
    	mCheckView = (CheckView) findViewById(R.id.checkView);
    	mShowPassViwe = (TextView) findViewById(R.id.checkpass);
    	mEditPass = (EditText) findViewById(R.id.checkTest);
    	mSubmit = (Button) findViewById(R.id.submit);
    	mRef = (Button) findViewById(R.id.ref);
    	mSubmit.setOnClickListener(this);
    	mRef.setOnClickListener(this);
    }
    
    public void initCheckNum()
    {
    	checkNum = CheckGetUtil.getCheckNum();
    	mCheckView.setCheckNum(checkNum);
    	mCheckView.invaliChenkNum();
    }

    public void onClick(View v) {
		switch (v.getId())
		{		
		case R.id.submit:
			String userInput = mEditPass.getText().toString();
			if(CheckGetUtil.checkNum(userInput, checkNum))
				{
				setPassString("درست است");
				Toast.makeText(this, "درست است", 1200).show();
				}
			else
				{
				setPassString("اشتباه است");
				Toast.makeText(this, "اشتباه است", 1200).show();
				}
			break;
		case R.id.ref:
			initCheckNum();
			break;
		default:
			break;
		}
	}
    
    public void onResume() {
    	new Thread(new myThread()).start();
    	super.onResume();
    	
    }
    
    public void setPassString(String passString)
    {
    	mShowPassViwe.setText(passString);
    }
    
    class myThread implements Runnable { 
         public void run() {
              while (!Thread.currentThread().isInterrupted()) {  
                   Message message = new Message(); 
                   message.what = MainActivity.UPDATA_CHECKNUM;
                   
                   MainActivity.this.myHandler.sendMessage(message);
                   try { 
                       Thread.sleep(ConmentConfig.PTEDE_TIME);  
                   } catch (InterruptedException e) { 
                       Thread.currentThread().interrupt(); 
                   } 
              } 
         } 
    } 
    
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) { 
             switch (msg.what) {
                  case MainActivity.UPDATA_CHECKNUM:
                	   mCheckView.invaliChenkNum();
                       break; 
             }
             super.handleMessage(msg); 
        } 
   };
}