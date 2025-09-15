package twibby.game.maker;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import twibby.game.maker.databinding.*;

public class MainActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private MainBinding binding;
	
	private Intent intent = new Intent();
	private TimerTask timer;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
	}
	
	private void initializeLogic() {
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						intent.setClass(getApplicationContext(), MenuActivity.class);
						startActivity(intent);
						finish();
					}
				});
			}
		};
		_timer.schedule(timer, (int)(2000));
	}
	
}