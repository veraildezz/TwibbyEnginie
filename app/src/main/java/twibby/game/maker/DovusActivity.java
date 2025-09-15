package twibby.game.maker;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import twibby.game.maker.databinding.*;

public class DovusActivity extends Activity {
	
	public final int REQ_CD_FP = 101;
	
	private Timer _timer = new Timer();
	
	private DovusBinding binding;
	private double gecis = 0;
	private double atak = 0;
	private double hiz = 0;
	private String mrp = "";
	
	private ArrayList<String> msm = new ArrayList<>();
	
	private TimerTask timer;
	private Intent intent = new Intent();
	private SharedPreferences sp;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private AlertDialog.Builder diyalog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = DovusBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
			} else {
				initializeLogic();
			}
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		diyalog = new AlertDialog.Builder(this);
		
		binding.linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				TextView textview6 = findViewById(R.id.textview6);
				
				int atak = 0;
				try {
					atak = Integer.parseInt(textview6.getText().toString());
				} catch (NumberFormatException e) {
					atak = 0; // boşsa sıfır kabul et
				}
				
				atak = atak - 10;
				textview6.setText(String.valueOf(atak));
				_mng();
			}
		});
		
		binding.imageview1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
				return true;
			}
		});
		
		binding.imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				TextView textview6 = findViewById(R.id.textview6);
				
				int atak = 0;
				try {
					atak = Integer.parseInt(textview6.getText().toString());
				} catch (NumberFormatException e) {
					atak = 0; // boşsa sıfır kabul et
				}
				
				atak = atak + 10;
				textview6.setText(String.valueOf(atak));
				_mng();
				if (hiz == 0) {
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_mng();
								}
							});
						}
					};
					_timer.schedule(timer, (int)(1000));
				} else {
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_mng();
								}
							});
						}
					};
					_timer.schedule(timer, (int)(hiz));
				}
			}
		});
		
		binding.imageview17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (binding.edittext1.getText().toString().equals("")) {
					((EditText)binding.edittext1).setError("Gerekli yerleri doldurunuz");
				} else {
					if (binding.edittext2.getText().toString().equals("")) {
						((EditText)binding.edittext2).setError("Gerekli yerleri doldurunuz");
					} else {
						if (binding.edittext3.getText().toString().equals("")) {
							((EditText)binding.edittext3).setError("Gerekli yerleri doldurunuz");
						} else {
							if (binding.edittext4.getText().toString().equals("")) {
								((EditText)binding.edittext4).setError("Gerekli yerleri doldurunuz");
							} else {
								if (binding.edittext5.getText().toString().equals("")) {
									((EditText)binding.edittext5).setError("Gerekli yerleri doldurunuz");
								} else {
									if (binding.edittext6.getText().toString().equals("")) {
										((EditText)binding.edittext6).setError("Gerekli yerleri doldurunuz");
									} else {
										if (binding.edittext7.getText().toString().equals("")) {
											((EditText)binding.edittext7).setError("Gerekli yerleri doldurunuz");
										} else {
											if (binding.edittext10.getText().toString().equals("")) {
												((EditText)binding.edittext10).setError("Gerekli yerleri doldurunuz");
											} else {
												if (binding.edittext11.getText().toString().equals("")) {
													((EditText)binding.edittext11).setError("Gerekli yerleri doldurunuz");
												} else {
													SketchwareUtil.showMessage(getApplicationContext(), "Üretiliyor");
													timer = new TimerTask() {
														@Override
														public void run() {
															runOnUiThread(new Runnable() {
																@Override
																public void run() {
																	diyalog.setTitle("Source Code");
																	diyalog.setMessage("");
																	diyalog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
																		@Override
																		public void onClick(DialogInterface _dialog, int _which) {
																			
																		}
																	});
																	diyalog.create().show();
																}
															});
														}
													};
													_timer.schedule(timer, (int)(1000));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		binding.imageview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				hiz = Double.parseDouble(binding.edittext5.getText().toString());
			}
		});
		
		binding.imageview15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (binding.edittext7.getText().toString().equals("0") || binding.edittext7.getText().toString().equals("")) {
					binding.imageview1.setRotation((float)(0));
				} else {
					binding.imageview1.setRotation((float)(Double.parseDouble(binding.edittext7.getText().toString())));
				}
				if (binding.edittext10.getText().toString().equals("0") || binding.edittext10.getText().toString().equals("")) {
					binding.imageview1.setScaleX((float)(1));
				} else {
					binding.imageview1.setScaleX((float)(Double.parseDouble(binding.edittext10.getText().toString())));
				}
				if (binding.edittext11.getText().toString().equals("0") || binding.edittext11.getText().toString().equals("")) {
					binding.imageview1.setScaleY((float)(1));
				} else {
					binding.imageview1.setScaleX((float)(Double.parseDouble(binding.edittext10.getText().toString())));
				}
			}
		});
	}
	
	private void initializeLogic() {
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				binding.imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _mng() {
		gecis = SketchwareUtil.getRandom((int)(1), (int)(4));
		if (gecis == 1) {
			binding.imageview1.setTranslationX((float)(binding.imageview1.getTranslationX() + Double.parseDouble(binding.edittext1.getText().toString())));
		}
		if (gecis == 2) {
			binding.imageview1.setTranslationX((float)(binding.imageview1.getTranslationX() - Double.parseDouble(binding.edittext2.getText().toString())));
		}
		if (gecis == 3) {
			binding.imageview1.setTranslationY((float)(binding.imageview1.getTranslationY() + Double.parseDouble(binding.edittext3.getText().toString())));
		}
		if (gecis == 4) {
			binding.imageview1.setTranslationY((float)(binding.imageview1.getTranslationY() - Double.parseDouble(binding.edittext4.getText().toString())));
		}
	}
	
}