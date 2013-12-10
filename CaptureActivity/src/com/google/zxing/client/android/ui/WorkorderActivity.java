package com.google.zxing.client.android.ui;

import java.net.MalformedURLException;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.adapter.WorkorderFragmentAdapter;
import com.google.zxing.client.android.app.AppContext;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.HandlerUtils;
import com.google.zxing.client.android.utils.StringUtils;
import com.google.zxing.client.android.vo.Workorder;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * 工单控制主界面
 * 
 * @author 汪渝栋
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WorkorderActivity extends FragmentActivity {

	private WorkorderFragmentAdapter workorderAdapter;
	private ViewPager workorderPager;
	private PageIndicator workorderIndicator;

	private Workorder workorder;

	private TextView title;
	private TextView subTitle;

	private Button returnBtn;
	private Button addBtn;// 工单主页添加按钮用于任务、人工、物料、故障公用
	private Button startProBtn;// 启动流程按钮

	private Handler proHandler;// 流程启动控制

	private AppContext appContext;
	private TitlePageIndicator indicator;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workorder_pager);
		workorder = (Workorder) getIntent().getSerializableExtra("workorder");
		appContext = (AppContext) getApplicationContext();
		proHandler = getProHandler();
		init();
		workorderAdapter = new WorkorderFragmentAdapter(
				getSupportFragmentManager(), addBtn, workorder, appContext,
				this);

		workorderPager.setAdapter(workorderAdapter);

		indicator.setViewPager(workorderPager);
		indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
		workorderIndicator = indicator;

		workorderIndicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 对添加按钮进行逻辑操作
				if (position == 2) {
					addBtn.setVisibility(View.VISIBLE);
					startProBtn.setVisibility(View.GONE);
				} else if (position == 1 && appContext.isNetworkConnected()) {
					addBtn.setVisibility(View.VISIBLE);
					startProBtn.setVisibility(View.GONE);
				} else {
					addBtn.setVisibility(View.GONE);
					startProBtn.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	/**
	 * @Title: getProHandler
	 * @Description: TODO
	 * @param @return
	 * @return Handler
	 * @throws
	 */
	private Handler getProHandler() {
		return new Handler() {
			/*
			 * (非 Javadoc) <p>Title: handleMessage</p> <p>Description: </p>
			 * 
			 * @param msg
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case R.id.workorder_start_pro:
					showinfo(msg.obj);
					break;
				default:
					showerror(msg.obj);
					break;
				}
			}
		};
	}

	/**
	 * @Title: showerror
	 * @Description: TODO
	 * @param @param obj
	 * @return void
	 * @throws
	 */
	protected void showerror(Object obj) {
		appContext.showError(obj.toString(), this);
	}

	/**
	 * @Title: showinfo
	 * @Description: TODO
	 * @param @param obj
	 * @return void
	 * @throws
	 */
	protected void showinfo(Object obj) {
		appContext.showInfo(obj.toString(), this, Style.INFO);
	}

	/**
	 * @Title: init
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void init() {
		initWidget();
		initEvent();
	}

	/**
	 * @Title: initEvent
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initEvent() {
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		startProBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPro();
			}
		});
	}

	/**
	 * @Title: startPro
	 * @Description: 启动工单流程
	 * @param
	 * @return void
	 * @throws
	 */
	protected void startPro() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String ownerTable = "WORKORDER";
				String wfName = "WO";
				String pykeyid = workorder.getWorkorderid();
				try {
					String result = appContext.inwfro(ownerTable, wfName,
							pykeyid);
					if (!StringUtils.isEmpty(result)
							&& result.equalsIgnoreCase("true")) {
						HandlerUtils.sendMessage(proHandler,
								"您好，工单流程已经启动成功！！！", R.id.workorder_start_pro);
					} else {
						HandlerUtils.sendMessage(proHandler, result.toString(),
								R.id.workorder_start_pro_error);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (CommunicationException e) {
					e.printStackTrace();
				} catch (AuthorizationException e) {
					e.printStackTrace();
				} catch (AuthenticationException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * @Title: initWidget
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void initWidget() {
		returnBtn = (Button) findViewById(R.id.work_return_btn);
		addBtn = (Button) findViewById(R.id.work_add_btn);
		startProBtn = (Button) findViewById(R.id.work_startPro_btn);
		startProBtn.setVisibility(View.VISIBLE);
		title = (TextView) findViewById(R.id.work_detail_title);
		title.setText("工单操作");
		subTitle = (TextView) findViewById(R.id.work_detail_subtitle);
		subTitle.requestFocus();
		if (workorder != null) {
			subTitle.setText("工单修改/删除");
		} else {
			subTitle.setText("工单添加");
		}
		workorderPager = (ViewPager) findViewById(R.id.workorder_pager);
		indicator = (TitlePageIndicator) findViewById(R.id.workorder_indicator);
	}

}
