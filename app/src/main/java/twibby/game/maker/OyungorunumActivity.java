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
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import twibby.game.maker.databinding.*;

public class OyungorunumActivity extends Activity {
	
	public final int REQ_CD_FP = 101;
	public final int REQ_CD_FP1 = 102;
	public final int REQ_CD_FP2 = 103;
	public final int REQ_CD_FP3 = 104;
	public final int REQ_CD_FP4 = 105;
	public final int REQ_CD_FP5 = 106;
	public final int REQ_CD_FP6 = 107;
	public final int REQ_CD_FP7 = 108;
	public final int REQ_CD_FP8 = 109;
	
	private Timer _timer = new Timer();
	
	private OyungorunumBinding binding;
	private String solst = "";
	private String sagst = "";
	private String asagist = "";
	private String yukarist = "";
	
	private ArrayList<String> string = new ArrayList<>();
	private ArrayList<String> saghareket = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> LM = new ArrayList<>();
	
	private SharedPreferences sp;
	private Intent intent = new Intent();
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp1 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp2 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp3 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp4 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp5 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp6 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp7 = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent fp8 = new Intent(Intent.ACTION_GET_CONTENT);
	private TimerTask timer;
	private Intent kod = new Intent();
	private AlertDialog.Builder diyalog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = OyungorunumBinding.inflate(getLayoutInflater());
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
		fp1.setType("image/*");
		fp1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp2.setType("image/*");
		fp2.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp3.setType("image/*");
		fp3.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp4.setType("image/*");
		fp4.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp5.setType("image/*");
		fp5.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp6.setType("image/*");
		fp6.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp7.setType("image/*");
		fp7.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		fp8.setType("image/*");
		fp8.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		diyalog = new AlertDialog.Builder(this);
		
		binding.linear4.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		binding.karakter.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
				return true;
			}
		});
		
		binding.karakter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		binding.sol.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				startActivityForResult(fp2, REQ_CD_FP2);
				return true;
			}
		});
		
		binding.sol.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.karakter.setTranslationX((float)(binding.karakter.getTranslationX() - Double.parseDouble(binding.editsol.getText().toString())));
			}
		});
		
		binding.yukari.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				startActivityForResult(fp3, REQ_CD_FP3);
				return true;
			}
		});
		
		binding.yukari.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.karakter.setTranslationY((float)(binding.karakter.getTranslationY() + Double.parseDouble(binding.edityukari.getText().toString())));
			}
		});
		
		binding.asagi.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				startActivityForResult(fp4, REQ_CD_FP4);
				return true;
			}
		});
		
		binding.asagi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.karakter.setTranslationY((float)(binding.karakter.getTranslationY() - Double.parseDouble(binding.editasagi.getText().toString())));
			}
		});
		
		binding.sag.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				startActivityForResult(fp1, REQ_CD_FP1);
				return true;
			}
		});
		
		binding.sag.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.karakter.setTranslationX((float)(binding.karakter.getTranslationX() + Double.parseDouble(binding.editsag.getText().toString())));
			}
		});
		
		binding.imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (binding.editsol.getText().toString().equals("")) {
					((EditText)binding.editsol).setError("Gerekli yerleri doldurunuz");
				} else {
					if (binding.editsag.getText().toString().equals("")) {
						((EditText)binding.editsag).setError("Gerekli yerleri doldurunuz");
					} else {
						if (binding.edityukari.getText().toString().equals("")) {
							((EditText)binding.edityukari).setError("Gerekli yerleri doldurunuz");
						} else {
							if (binding.editasagi.getText().toString().equals("")) {
								((EditText)binding.editasagi).setError("Gerekli yerleri doldurunuz");
							} else {
								if (binding.edittext1.getText().toString().equals("")) {
									((EditText)binding.edittext1).setError("Gerekli yerleri doldurunuz");
								} else {
									if (binding.edittext2.getText().toString().equals("")) {
										((EditText)binding.edittext2).setError("Gerekli yerleri doldurunuz");
									} else {
										if (binding.edittext3.getText().toString().equals("")) {
											((EditText)binding.edittext3).setError("Gerekli yerleri doldurunuz");
										} else {
											SketchwareUtil.showMessage(getApplicationContext(), "Üretiliyor...");
											solst = "//Sol\n\nbinding.karakter.setTranslationX((float)(binding.karakter.getTranslationX() - ".concat(binding.editsol.getText().toString().concat("));"));
											sagst = "//Sağ\n\nbinding.karakter.setTranslationX((float)(binding.karakter.getTranslationX() + ".concat(binding.editsag.getText().toString().concat("));"));
											asagist = "//Aşağı\n\nbinding.karakter.setTranslationY((float)(binding.karakter.getTranslationY() - ".concat(binding.editasagi.getText().toString().concat("));"));
											yukarist = "//Yukarı\n\nbinding.karakter.setTranslationY((float)(binding.karakter.getTranslationY() + ".concat(binding.edityukari.getText().toString().concat("));"));
											timer = new TimerTask() {
												@Override
												public void run() {
													runOnUiThread(new Runnable() {
														@Override
														public void run() {
															diyalog.setTitle("Source Code");
															diyalog.setMessage(solst.concat(sagst.concat(asagist.concat(yukarist))));
															diyalog.setPositiveButton("Okuma Modu", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface _dialog, int _which) {
																	intent.setClass(getApplicationContext(), KodgorunumActivity.class);
																	intent.putExtra("kod", solst.concat(sagst.concat(asagist.concat(yukarist))));
																	startActivity(intent);
																}
															});
															diyalog.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
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
		});
		
		binding.kaydet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (binding.edittext2.getText().toString().equals("")) {
					
				} else {
					if (Double.parseDouble(binding.edittext2.getText().toString()) > 7) {
						((EditText)binding.edittext2).setError("7'den küçük olmalıdır");
					} else {
						binding.karakter.setScaleX((float)(Double.parseDouble(binding.edittext2.getText().toString())));
					}
				}
				if (binding.edittext3.getText().toString().equals("")) {
					
				} else {
					if (Double.parseDouble(binding.edittext3.getText().toString()) > 7) {
						((EditText)binding.edittext3).setError("7'den küçük olmalıdır");
					} else {
						binding.karakter.setScaleX((float)(Double.parseDouble(binding.edittext3.getText().toString())));
					}
				}
				if (binding.edittext1.getText().toString().equals("")) {
					((EditText)binding.edittext1).setError("Boş Olamaz");
				} else {
					binding.karakter.setRotation((float)(binding.karakter.getRotation() + Double.parseDouble(binding.edittext3.getText().toString())));
				}
			}
		});
		
		binding.sagyurume.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Bu Özellik Yakında Gelecektir");
			}
		});
		
		binding.solyurume.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Bu Özellik Yakında Gelecektir");
			}
		});
		
		binding.yukariyurume.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Bu Özellik Yakında Gelecektir");
			}
		});
		
		binding.asagiyurume.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Bu özellik yakında gelecektir");
			}
		});
		
		binding.edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		binding.edittext3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
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
				binding.karakter.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP1:
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
				binding.sag.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP2:
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
				binding.sol.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP3:
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
				binding.yukari.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP4:
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
				binding.asagi.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP5:
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
				binding.sagyurume.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP6:
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
				binding.solyurume.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP7:
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
				binding.yukariyurume.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			
			case REQ_CD_FP8:
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
				binding.asagiyurume.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
}