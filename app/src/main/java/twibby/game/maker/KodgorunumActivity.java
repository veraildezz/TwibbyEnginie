package twibby.game.maker;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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

public class KodgorunumActivity extends Activity {
	
	private KodgorunumBinding binding;
	private String solst = "";
	private String sagst = "";
	private String asagist = "";
	private String yukarist = "";
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = KodgorunumBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		binding.imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", binding.textview1.getText().toString()));
			}
		});
		
		binding.imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", binding.textview2.getText().toString()));
			}
		});
		
		binding.imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", binding.textview3.getText().toString()));
			}
		});
		
		binding.imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", binding.textview4.getText().toString()));
			}
		});
	}
	
	private void initializeLogic() {
		binding.textview1.setText(getIntent().getStringExtra(solst));
		binding.textview2.setText(getIntent().getStringExtra(sagst));
		binding.textview3.setText(getIntent().getStringExtra(asagist));
		binding.textview4.setText(getIntent().getStringExtra(yukarist));
	}
	
}