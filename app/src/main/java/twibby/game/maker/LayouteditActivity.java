package twibby.game.maker;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import twibby.game.maker.databinding.*;

public class LayouteditActivity extends Activity {
	
	private LayouteditBinding binding;
	private String xnd = "";
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = LayouteditBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		binding.imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.lineareditor.setBackgroundColor(0xFFD2691E);
			}
		});
		
		binding.button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
	}
	
}