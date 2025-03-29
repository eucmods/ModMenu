package com.as.instagram;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.IBinder;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
/*

Powered By CMODs

*/
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import com.as.instagram.utils.NativeFuncs;
import android.view.View.OnTouchListener;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.as.instagram.ESPView;
import android.widget.TextView;
import android.widget.SeekBar;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.widget.RadioGroup;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import android.widget.RadioButton;
import android.text.Html;
import android.content.res.ColorStateList;
import android.animation.ArgbEvaluator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.widget.CheckBox;
import android.annotation.TargetApi;

public class FloaterModMenu{
	
    protected WindowManager mWindowManager;
	protected FrameLayout RootLayout;
    protected View mFloatingView;
    protected RelativeLayout CollapsedRelative, RootRelative, ButtonsRelative;
    protected LinearLayout MenuLayout, BannerLayout, FuncsLayout, CloseLayout;
    protected WindowManager.LayoutParams WindowParams;
    protected ImageView LogoImagem, BannerCloseImagem;
    protected Bitmap LogoPNG, BannerPNG, BannerClosePNG;
    protected GradientDrawable MenuBackground, BannerLayoutBackground, BannerImagemBackground, BannerCloseLayoutBackground, BannerCloseImagemBackground, CloseBackground;
    protected ScrollView FuncsScroll;
    protected WebView CreditosText;
    protected Button CloseButton;
	protected TextView BannerImagem;
	protected Context context;

	private int progress;

	int secondaryColor;
	private GradientDrawable gdMenuBody;

	private RadioGroup radioGroup;

	private LinearLayout CMODs;
	
	public native void Changes(int feature, int value);
	
	///START PARA MENU SEM PERMISSO
	public static void Start(final Context context){
		System.loadLibrary("pid");
        Handler handler = new Handler();
	   	handler.postDelayed(new Runnable() {
                @Override
                public void run() {
					new FloaterModMenu().init(context);
                }
            }, 1000);
	}
	protected void init(Context context){
		this.context = context;
		CarregarImagens();
        CriarBackgrounds();
		CriarLayout(context);
	}
	//FIM DO Start

	//********** ESP DRAW ***********//
	private ESPView esp;
	public static native void DrawOn(ESPView espView, Canvas canvas);
	//**************//
	
    public void CriarLayout(Context context) {
		try {
			esp = new ESPView(context);
		} catch (Exception e) {
			Toast.makeText(context, e.toString(), 1).show();
		}
        RootLayout = new FrameLayout(context);
        FrameLayout.LayoutParams rootLayout = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        RootLayout.setLayoutParams(rootLayout);

        RootRelative = new RelativeLayout(context);
        ViewGroup.LayoutParams rootRelative = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        RootRelative.setLayoutParams(rootRelative);

        CollapsedRelative = new RelativeLayout(context);
        CollapsedRelative.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        CollapsedRelative.setVisibility(View.VISIBLE);

        LogoImagem = new ImageView(context);
        LinearLayout.LayoutParams logoImagem = new LinearLayout.LayoutParams(ConvertDP(80), ConvertDP(80));
        LogoImagem.setLayoutParams(logoImagem);
        LogoImagem.setImageBitmap(LogoPNG);

        MenuLayout = new LinearLayout(context);
        LinearLayout.LayoutParams menuLayout = new LinearLayout.LayoutParams(ConvertDP(290), WRAP_CONTENT);
        MenuLayout.setLayoutParams(menuLayout);
        MenuLayout.setVisibility(View.GONE);
        MenuLayout.setBackground(MenuBackground);
        MenuLayout.setOrientation(LinearLayout.VERTICAL);
        MenuLayout.setAlpha(0.8f);

        BannerLayout = new LinearLayout(context);
        LinearLayout.LayoutParams bannerLayout = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        BannerLayout.setLayoutParams(bannerLayout);
		
        BannerLayout.setOrientation(LinearLayout.VERTICAL);
		BannerLayout.setGravity(Gravity.CENTER);
			
			
		/*
		ValueAnimator animator = ValueAnimator.ofArgb(Color.RED,Color.BLUE,Color.YELLOW);
		animator.setDuration(5000);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					int color = (int) animation.getAnimatedValue();
					BannerLayout.setBackgroundColor(color);
				}
			});
		animator.start();
*/
        BannerImagem = new TextView(context);
        LinearLayout.LayoutParams bannerImagem = new LinearLayout.LayoutParams(ConvertDP(250), ConvertDP(47));
        BannerImagem.setLayoutParams(bannerImagem);
        BannerImagem.setTypeface(null,Typeface.BOLD);
        BannerImagem.setTextColor(Color.WHITE);
		BannerImagem.setText("CMODS Developer");
		BannerImagem.setTextSize(14);
        BannerImagem.setClipToOutline(true);
		BannerImagem.setPadding(ConvertDP(3),ConvertDP(6),ConvertDP(3),ConvertDP(3));
		BannerImagem.setGravity(Gravity.CENTER_HORIZONTAL);

		View LineViewCima = new View(context);
        LinearLayout.LayoutParams lineViewC = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(2));
        LineViewCima.setLayoutParams(lineViewC);
        LineViewCima.setBackgroundColor(Color.parseColor("#A3FFFFFF"));
		
        FuncsScroll = new ScrollView(context);
        LinearLayout.LayoutParams funcsScroll = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(248));
        FuncsScroll.setLayoutParams(funcsScroll);

        FuncsLayout = new LinearLayout(context);
        LinearLayout.LayoutParams funcsLayout = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        FuncsLayout.setLayoutParams(funcsLayout);
        FuncsLayout.setOrientation(LinearLayout.VERTICAL);
		
        ButtonsRelative = new RelativeLayout(context);
        LinearLayout.LayoutParams buttonsLayout = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        ButtonsRelative.setLayoutParams(buttonsLayout);

		View LineViewBaixo = new View(context);
        LinearLayout.LayoutParams lineViewB = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(2));
        LineViewBaixo.setLayoutParams(lineViewB);
        LineViewBaixo.setBackgroundColor(Color.parseColor("#A3FFFFFF"));
		
        CloseLayout = new LinearLayout(context);
        LinearLayout.LayoutParams closeLayout = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(40));
        CloseLayout.setLayoutParams(closeLayout);
        CloseLayout.setOrientation(LinearLayout.HORIZONTAL);
        CloseLayout.setGravity(Gravity.CENTER);
		
        CloseButton = new Button(context);
        LinearLayout.LayoutParams closeButton = new LinearLayout.LayoutParams(ConvertDP(100), ConvertDP(29));
        closeButton.topMargin = ConvertDP(1);
        CloseButton.setLayoutParams(closeButton);
        CloseButton.setText("Fechar");
        CloseButton.setTextColor(Color.WHITE);
        CloseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11f);
        CloseButton.setAllCaps(false);
        CloseButton.setBackground(CloseBackground);
        CloseButton.setPadding(ConvertDP(3),ConvertDP(3),ConvertDP(3),ConvertDP(3));

        RootLayout.addView(RootRelative);
        RootRelative.addView(CollapsedRelative);
        RootRelative.addView(MenuLayout);
        CollapsedRelative.addView(LogoImagem);

        MenuLayout.addView(BannerLayout);
        BannerLayout.addView(BannerImagem);
	    BannerLayout.addView(LineViewCima);
		
        MenuLayout.addView(FuncsScroll);
        FuncsScroll.addView(FuncsLayout);
        MenuLayout.addView(ButtonsRelative);
		
        ButtonsRelative.addView(CloseLayout);
        CloseLayout.addView(CloseButton);
		ButtonsRelative.addView(LineViewBaixo);
		
        mFloatingView = RootLayout;

        final View collapsedView = CollapsedRelative;
        final View expandedView = MenuLayout;

        mFloatingView.setOnTouchListener(onTouchListener());
        LogoImagem.setOnTouchListener(onTouchListener());
        initMenuButton(collapsedView, expandedView);
		AniC_Start();
        modMenu();	
		//ESTRUTURA DO FLUTUANTE SEM PERMISSO SOBRE POSISÃO
		int aditionalFlags = 0;
		if (Build.VERSION.SDK_INT >= 11)
			aditionalFlags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
		if (Build.VERSION.SDK_INT >=  3)
			aditionalFlags = aditionalFlags | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
		WindowParams = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,1,155,
			WindowManager.LayoutParams.TYPE_APPLICATION,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
			aditionalFlags,

			PixelFormat.TRANSPARENT
		);
		WindowParams.gravity = Gravity.TOP | Gravity.START;

		mWindowManager = ((Activity)context).getWindowManager();
		mWindowManager.addView(mFloatingView, WindowParams);
		
		//ESP MANAGER VIEW
		WindowManager.LayoutParams layoutParamsx = new WindowManager.LayoutParams(-1, -1, 2, 56 | WindowManager.LayoutParams.FLAG_FULLSCREEN, -3); 
		layoutParamsx.gravity = 51; 
		layoutParamsx.x = 0; 
		layoutParamsx.y = 0;
		esp.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		mWindowManager.addView(esp, layoutParamsx);//////END ESPVIEW
		//FIM DO CODIGO
    }
	
	
	//USAR PARA MOVE O FLUTUANTE
	private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
		final View collapsedView = CollapsedRelative;
		final View expandedView = MenuLayout;
		private float initialX;          
		private float initialY;
		private float initialTouchX;
		private float initialTouchY;
		double clock = 0;
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent){
			switch (motionEvent.getAction()){
				case MotionEvent.ACTION_DOWN:
					initialX = WindowParams.x;
					initialY = WindowParams.y;
					initialTouchX = motionEvent.getRawX();
					initialTouchY = motionEvent.getRawY();			
					clock = System.currentTimeMillis();
					break;
				case MotionEvent.ACTION_MOVE:
				    WindowParams.x = (int)initialX + (int)(motionEvent.getRawX() - initialTouchX);
					WindowParams.y = (int)initialY + (int)(motionEvent.getRawY() - initialTouchY);
					mWindowManager.updateViewLayout(mFloatingView, WindowParams);
					break;
				case MotionEvent.ACTION_UP:
					if ((System.currentTimeMillis() < (clock + 200))){
						expandedView.setVisibility(View.VISIBLE);
						collapsedView.setVisibility(View.GONE);
					}
					break;
			}
			return true;
		}
	};
	}//END
	private  void AniC_Start() {
        int duration = 3000;
		ValueAnimator animator = ValueAnimator.ofArgb(Color.RED,Color.BLUE,Color.BLACK);
		animator.setDuration(duration);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					int color = (int) animation.getAnimatedValue();
					gdMenuBody = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{color, secondaryColor});
					gdMenuBody.setCornerRadius(4f); //Set corner
					BannerLayout.setBackground(gdMenuBody);
					ButtonsRelative.setBackground(gdMenuBody);
				}
			});
		animator.start();
		//YELLOW, GREEN, BLUE, MAGENTA, RED, YELLOW
		ValueAnimator animator2 = ValueAnimator.ofArgb(Color.YELLOW,Color.RED,Color.BLUE);
		animator2.setDuration(duration);
		animator2.setRepeatCount(ValueAnimator.INFINITE);
		animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					secondaryColor = (int) animation.getAnimatedValue();
				}
			});
		animator2.start();
    }
	
    private void initMenuButton(final View collapsedView, final View expandedView) {
        LogoImagem.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapsedView.setVisibility(View.GONE);
                expandedView.setVisibility(View.VISIBLE);
            }
        });
        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);
            }
        });
    }
    private void modMenu() {
        AddSwitch("Aim Activate", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(0, 0);
				}
			});
		AddSwitch("Aim TP", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(1, 0);
				}
			});
		addSeekBar("Aim FOV (Angle)", 12 ,65, new InterfaceInt(){
				@Override
				public void OnWrite(int progress) {
					Changes(2, progress);//Changes(2, progress); esse 2 eo if
				}
			});
		AddSwitch("ESP", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(3, 0);
				}
			});
        AddSwitch("ESP Line", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(4, 0);
				}
			});

		addRadioButton(5,"ESP Box", "OFF,BOX,3D,CORNER");

		AddSwitch("ESP CrossHair / ESPName", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(6, 0);
				}
			});
		AddSwitch("ESP Random", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(7, 0);
				}
			});
		AddSwitch("ESP Player Distance", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(8, 0);
				}
			});
		addSeekBar("ESP Color ", progress ,8, new InterfaceInt(){
				@Override
				public void OnWrite(int progress) {
					Changes(9, progress);//Changes(2, progress); esse 2 eo if
				}
			});
		addSeekBar("ESP Width (all)",progress,25, new InterfaceInt(){
				@Override
				public void OnWrite(int progress) {
					Changes(10, progress);//Changes(2, progress); esse 2 eo if
				}
			});

		addRadioButton(11,"ESP Line", "OFF,LINE,CENTER,BELOW");

		AddSwitch("ESP HP", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(12, 0);
				}
			});
		AddSwitch("ESP isHumano?",new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(13, 0);
				}
			});
		AddSwitch("FLY", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(14, 0);
				}
			});
		addSeekBar("Fly Hack", progress ,100, new InterfaceInt(){
				@Override
				public void OnWrite(int progress) {
					Changes(15, progress);//Changes(2, progress); esse 2 eo if
				}
			});
		AddSwitch("AimKill", new SW() {
				@Override
				public void OnWrite(boolean isActive) {
					Changes(16, 0);
				}
			});
    }
	
    @SuppressLint("NewApi")
    private void AddSwitch(String text, final SW listener) {
        LinearLayout SwitchLayout = new LinearLayout(context);
        LinearLayout.LayoutParams switchLayout = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        SwitchLayout.setLayoutParams(switchLayout);
        SwitchLayout.setOrientation(LinearLayout.HORIZONTAL);
		
        final GradientDrawable SwitchDrawable = new GradientDrawable();
        SwitchDrawable.setShape(GradientDrawable.OVAL);
        SwitchDrawable.setColor(Color.BLACK);
        SwitchDrawable.setStroke(ConvertDP(2), Color.WHITE);
        SwitchDrawable.setSize(ConvertDP(20), ConvertDP(20));
		
        final CheckBox FuncSwitch = new CheckBox(context);
        LinearLayout.LayoutParams funcSwitch = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(35));
        funcSwitch.leftMargin = ConvertDP(5);
        funcSwitch.rightMargin = ConvertDP(5);
        FuncSwitch.setLayoutParams(funcSwitch);
		FuncSwitch.setPadding(ConvertDP(5),ConvertDP(3),ConvertDP(3),ConvertDP(3));
        FuncSwitch.setText(text);
        FuncSwitch.setTextColor(Color.WHITE);
		FuncSwitch.setTypeface(Typeface.SERIF,Typeface.ITALIC);
        FuncSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
		FuncSwitch.setButtonDrawable(SwitchDrawable);
		FuncSwitch.setShadowLayer(15, 0, 0, Color.CYAN);
        FuncSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				FuncSwitch.setTextSize(15.5f);
				//FuncSwitch.setTextColor(Color.parseColor("#ffffff"));
                listener.OnWrite(isChecked);
                if (isChecked) {
					final  Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								SwitchDrawable.setColor(Color.parseColor("#FFFF0000"));
								FuncSwitch.setTextColor(Color.parseColor("#FFFFFFFF"));
								//sw.setText(string2 + " : ON");
								FuncSwitch.setTextSize(13.5f);
								//sw.setBackgroundColor(Color.parseColor("#ff00b900"));
								//sw.getButtonDrawable().setColorFilter(Color.parseColor("#e2e2e2"), PorterDuff.Mode.SRC_IN);
							}
						},75);
					//SwitchDrawable.setColor(Color.parseColor("#FFFF0000"));
                } else {
					final  Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								SwitchDrawable.setColor(Color.BLACK);
								FuncSwitch.setTextColor(Color.parseColor("#FFFFFFFF"));
								//FuncSwitch.setText(string2 + " : OFF");
								FuncSwitch.setTextSize(13.5f);
								//sw.setBackgroundColor(Color.parseColor("#ffff0000"));
								//sw.getButtonDrawable().setColorFilter(Color.parseColor("#e2e2e2"), PorterDuff.Mode.SRC_IN);
							}
						},75);
					
                  //  SwitchDrawable.setColor(Color.BLACK);
                }
            }
        });
        View LineView = new View(context);
        LinearLayout.LayoutParams lineView = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(1));
        LineView.setLayoutParams(lineView);
        LineView.setBackgroundColor(Color.parseColor("#A3FFFFFF"));

        FuncsLayout.addView(LineView);
        FuncsLayout.addView(SwitchLayout);
        SwitchLayout.addView(FuncSwitch);
    }

	@SuppressLint("WrongConstant")
    private void addSeekBar(final String feature, final int progress, int max, final InterfaceInt interInt) {
		
		final GradientDrawable Background_seek = new GradientDrawable();
        Background_seek.setShape(GradientDrawable.RECTANGLE);
      //Background_seek.setColor(Color.WHITE);
        Background_seek.setStroke(ConvertDP(2), Color.WHITE);
        Background_seek.setCornerRadius(ConvertDP(20));
        Background_seek.setSize(ConvertDP(20), ConvertDP(20));
		
		final TextView sbTxtSkeebar = new TextView(context);
		int textColor = Color.WHITE;
		LinearLayout.LayoutParams btns_params2 = new LinearLayout.LayoutParams(-2, ConvertDP(29));
        btns_params2 = new LinearLayout.LayoutParams(-1, ConvertDP(29));
        btns_params2.topMargin = ConvertDP(4);
        btns_params2.leftMargin = ConvertDP(5);
        btns_params2.rightMargin = ConvertDP(5);
		
		LinearLayout.LayoutParams collapsed_ivparams = new LinearLayout.LayoutParams(-2, -2);
        collapsed_ivparams.topMargin = ConvertDP(6);
		collapsed_ivparams.leftMargin = ConvertDP(15);
        collapsed_ivparams.rightMargin = ConvertDP(15);
		
		sbTxtSkeebar.setLayoutParams(collapsed_ivparams);
        sbTxtSkeebar.setText(feature + " :  " + progress + "x");
        sbTxtSkeebar.setTextSize((float) 12);
        sbTxtSkeebar.setTextColor(textColor);
		sbTxtSkeebar.setTypeface(Typeface.SERIF,Typeface.ITALIC);
		
        final SeekBar sbSkeebar = new SeekBar(context);
        sbSkeebar.setMax(max);
        sbSkeebar.setProgress(progress);
        sbSkeebar.setLayoutParams(btns_params2);
		
		sbSkeebar.setThumb(Background_seek);
        sbSkeebar.getProgressDrawable().setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.MULTIPLY);
		
	    final int progress2 = progress;
        final SeekBar sbSkeebar2 = sbSkeebar;
        final InterfaceInt interInt2 = interInt;
        final TextView sbTxtSkeebar2 = sbTxtSkeebar;
        sbSkeebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onStartTrackingTouch(SeekBar seekBar) {}
				public void onStopTrackingTouch(SeekBar seekBar) {}
				public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
					if (i < progress2) {
						sbSkeebar2.setProgress(progress);
						interInt2.OnWrite(progress);
						sbTxtSkeebar2.setText(feature + " : " + progress2 + "x");
						return;
                    }
                    interInt2.OnWrite(i);
				    sbTxtSkeebar2.setText(feature + " : " + i + "x");
                }
            });
			
		View LineView1 = new View(context);
        LinearLayout.LayoutParams lineView1 = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(1));
        LineView1.setLayoutParams(lineView1);
        LineView1.setBackgroundColor(Color.parseColor("#A3FFFFFF"));
		
		FuncsLayout.addView(LineView1);
		FuncsLayout.addView(sbTxtSkeebar);
        FuncsLayout.addView(sbSkeebar);
		//FuncsLayout.addView(LineView2);
	}
	
	private void addRadioButton(final int featNum, String featName, final String list) {
		
		final CheckBox textView3 = new CheckBox(context);
		textView3.setText("Type Selection "+featName);
        textView3.setTextColor(Color.parseColor("#FFFFFF"));
		textView3.getButtonDrawable().setColorFilter(Color.parseColor("#e2e2e2"), PorterDuff.Mode.SRC_IN);
		textView3.setTypeface(Typeface.SERIF,Typeface.ITALIC);
		textView3.setShadowLayer(15, 0, 0, Color.CYAN);
		textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13f);
		
        final List<String> lists = new LinkedList<>(Arrays.asList(list.split(",")));
        final TextView textView = new TextView(context);
        textView.setText(featName + ":");
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        final RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setPadding(10, 5, 10, 5);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.addView(textView);
		radioGroup.setVisibility(View.GONE);
        for (int i = 0; i < lists.size(); i++) {
            final RadioButton Radioo = new RadioButton(context);
            final String finalfeatName = featName, radioName = lists.get(i);
            View.OnClickListener first_radio_listener = new View.OnClickListener() {
                public void onClick(View v) {
                    textView.setText(Html.fromHtml(finalfeatName + ": <font color='#41c300'>" + radioName));
					Changes(featNum, radioGroup.indexOfChild(Radioo));
	            }
            };
            System.out.println(lists.get(i));
            Radioo.setText(lists.get(i));
            Radioo.setTextColor(Color.LTGRAY);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                Radioo.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            Radioo.setOnClickListener(first_radio_listener);
            radioGroup.addView(Radioo);
        }
        int index = Preferences.loadPrefInt(featName, featNum);
        if (index > 0) {
            textView.setText(Html.fromHtml(featName + ": <font color='#41c300'>" + lists.get(index - 1)));
            ((RadioButton) radioGroup.getChildAt(index)).setChecked(true);
        }
		//BUTTON ON OFF VISIBILIDAD3
		textView3.setOnClickListener(new View.OnClickListener() {
				private boolean isActive = false;
				@Override public void onClick(View view) {
					if (isActive){
						//Toast.makeText(context,"LIST OFF",Toast.LENGTH_SHORT).show();
						radioGroup.setVisibility(View.GONE);
						isActive = false;
					}else{
						//Toast.makeText(context,"LIST ON",Toast.LENGTH_SHORT).show();
						radioGroup.setVisibility(View.VISIBLE);
						isActive = true;
					}
					//END

				}
			});
		View LineView1 = new View(context);
        LinearLayout.LayoutParams lineView1 = new LinearLayout.LayoutParams(MATCH_PARENT, ConvertDP(1));
        LineView1.setLayoutParams(lineView1);
        LineView1.setBackgroundColor(Color.parseColor("#A3FFFFFF"));
		FuncsLayout.addView(LineView1);
		FuncsLayout.addView(textView3);
		FuncsLayout.addView(radioGroup);
	}

	
	
    private void CarregarImagens() {
        byte[] imageBytesLogo = Base64.decode(NativeFuncs.ObterImagens(0), Base64.DEFAULT);
        LogoPNG = BitmapFactory.decodeByteArray(imageBytesLogo, 0, imageBytesLogo.length);

        byte[] imageBytesBanner = Base64.decode(NativeFuncs.ObterImagens(8), Base64.DEFAULT);
        BannerPNG = BitmapFactory.decodeByteArray(imageBytesBanner, 0, imageBytesBanner.length);

        byte[] imageBytesBannerClose = Base64.decode(NativeFuncs.ObterImagens(9), Base64.DEFAULT);
        BannerClosePNG = BitmapFactory.decodeByteArray(imageBytesBannerClose, 0, imageBytesBannerClose.length);
    }

    private void CriarBackgrounds() {
        MenuBackground = new GradientDrawable();
        MenuBackground.setShape(GradientDrawable.RECTANGLE);
        MenuBackground.setColor(Color.parseColor("#FF000000"));
        MenuBackground.setCornerRadius(ConvertDP(5));

        BannerLayoutBackground = new GradientDrawable();
        BannerLayoutBackground.setShape(GradientDrawable.RECTANGLE);
        BannerLayoutBackground.setColor(Color.BLACK);
        setCornerRadius(BannerLayoutBackground, ConvertDP(5),ConvertDP(5),0,0);

        BannerImagemBackground = new GradientDrawable();
        BannerImagemBackground.setCornerRadius(ConvertDP(5));

        BannerCloseLayoutBackground = new GradientDrawable();
        BannerCloseLayoutBackground.setShape(GradientDrawable.RECTANGLE);
        BannerCloseLayoutBackground.setColor(Color.BLACK);
        setCornerRadius(BannerCloseLayoutBackground, 0,0,ConvertDP(5),ConvertDP(5));

        BannerCloseImagemBackground = new GradientDrawable();
        BannerCloseImagemBackground.setCornerRadius(ConvertDP(5));

        CloseBackground = new GradientDrawable();
        CloseBackground.setShape(GradientDrawable.RECTANGLE);
        CloseBackground.setColor(Color.TRANSPARENT);
        CloseBackground.setStroke(ConvertDP(1), Color.WHITE);
        setCornerRadius(CloseBackground, ConvertDP(5),ConvertDP(5),ConvertDP(15),ConvertDP(15));
    }

	
	public static interface SW {
        void OnWrite(boolean isActive);
    }
	public static interface InterfaceBtn {
        void OnWrite();
    }

    public static interface InterfaceInt {
        void OnWrite(int i);
    }

    public static interface InterfaceBool {
        void OnWrite(boolean z);
    }
	public static interface BTN {
        void OnWrite(boolean z);
    }
	
	
    private boolean isViewCollapsed() {
        return mFloatingView == null || CollapsedRelative.getVisibility() == View.VISIBLE;
    }

    static void setCornerRadius(GradientDrawable gradientDrawable, float f1, float f2, float f3, float f4) {
        gradientDrawable.setCornerRadii(new float[]{f1, f1, f2, f2, f3, f3, f4, f4});
    }

    private int ConvertDP(int value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
