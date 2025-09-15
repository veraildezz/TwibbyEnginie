package twibby.game.maker;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import twibby.game.maker.databinding.*;

public class MenuActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private MenuBinding binding;
	private boolean gecegunduz = false;
	private HashMap<String, Object> map = new HashMap<>();
	private String key = "";
	private boolean uygkurma = false;
	private String key2 = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private Intent intent = new Intent();
	private TimerTask timer;
	private SharedPreferences sp;
	private AlertDialog.Builder diyalog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = MenuBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		diyalog = new AlertDialog.Builder(this);
		
		binding.listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				intent.setClass(getApplicationContext(), SecimActivity.class);
				startActivity(intent);
			}
		});
		
		binding.imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Yakında");
			}
		});
		
		binding.button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (uygkurma) {
					uygkurma = false;
					binding.linear9.setVisibility(View.VISIBLE);
					
				} else {
					uygkurma = true;
					binding.linear9.setVisibility(View.GONE);
					
				}
			}
		});
		
		binding.edittext1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		binding.button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (binding.edittext1.getText().toString().equals("")) {
					((EditText)binding.edittext1).setError("Lütfen Gerekli Alanları Doldurunuz");
					binding.button3.setVisibility(View.VISIBLE);
					binding.progressbar1.setVisibility(View.GONE);
				} else {
					binding.progressbar1.setVisibility(View.VISIBLE);
					binding.button3.setVisibility(View.GONE);
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									binding.linear9.setVisibility(View.GONE);
									binding.progressbar1.setVisibility(View.GONE);
									map = new HashMap<>();
									map.put(key, binding.edittext1.getText().toString());
									listmap.add(map);
									sp.edit().putString(key, new Gson().toJson(listmap)).commit();
									sp.edit().putString(key2, new Gson().toJson(listmap)).commit();
									binding.edittext1.setText("");
									if (sp.getString(key, "").equals("")) {
										
									} else {
										listmap = new Gson().fromJson(sp.getString(key, ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
									}
									_refresh();
									binding.button3.setVisibility(View.VISIBLE);
									binding.progressbar1.setVisibility(View.GONE);
									SketchwareUtil.showMessage(getApplicationContext(), "Başarıyla Oluşturuldu");
								}
							});
						}
					};
					_timer.schedule(timer, (int)(1500));
				}
			}
		});
	}
	
	private void initializeLogic() {
		if (sp.getString(key, "").equals("")) {
			
		} else {
			listmap = new Gson().fromJson(sp.getString(key, ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		_refresh();
		gecegunduz = false;
		uygkurma = false;
		_RoundAndBorder(binding.linear3, "#FFC107", 0, "#ffffff", 40);
		_RoundAndBorder(binding.linear7, "#FFC107", 0, "#ffffff", 40);
		_RoundAndBorder(binding.button2, "#FFC107", 0, "#ffffff", 40);
		_RoundAndBorder(binding.button3, "#FFFFFF", 0, "#ffffff", 40);
		_RoundAndBorder(binding.linear9, "#FFC107", 0, "#ffffff", 40);
		binding.linear9.setVisibility(View.GONE);
		binding.progressbar1.setVisibility(View.GONE);
	}
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _refresh() {
		binding.listview2.setAdapter(new Listview2Adapter(listmap));
		((BaseAdapter)binding.listview2.getAdapter()).notifyDataSetChanged();
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			ProjegoruntuBinding binding = ProjegoruntuBinding.inflate(getLayoutInflater());
			View _view = binding.getRoot();
			
			binding.oyunismi.setText(listmap.get((int)_position).get(key).toString());
			binding.sayfaismi.setText(listmap.get((int)_position).get(key2).toString());
			_RoundAndBorder(binding.linear1, "#FFFFFF", 0, "#ffffff", 30);
			
			return _view;
		}
	}
}