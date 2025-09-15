package twibby.game.maker;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import twibby.game.maker.databinding.*;

public class SecimActivity extends Activity {
	
	private SecimBinding binding;
	
	private Intent intent = new Intent();
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = SecimBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		
		binding.button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), DovusActivity.class);
				startActivity(intent);
			}
		});
		
		binding.button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Yakında");
			}
		});
		
		binding.button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), OyungorunumActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void initializeLogic() {
		_RoundAndBorder(binding.button1, "#FF9800", 0, "#ffffff", 40);
		_RoundAndBorder(binding.button2, "#FF9800", 0, "#ffffff", 40);
		_RoundAndBorder(binding.button4, "#FF9800", 0, "#ffffff", 40);
	}
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
}