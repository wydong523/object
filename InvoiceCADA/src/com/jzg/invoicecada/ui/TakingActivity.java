package com.jzg.invoicecada.ui;

import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jzg.invoicecada.R;
import com.jzg.invoicecada.app.AppContext;
import com.jzg.invoicecada.app.HttpService;
import com.jzg.invoicecada.utils.ImgUrils;
import com.jzg.invoicecada.utils.MessageUtils;

public class TakingActivity extends BaseActivity
{
	private SurfaceView surfaceView;

	private Button takingPictureBtn;

	private TextView takingUpload;

	private RadioButton flashOnOff;

	private Camera camera;

	private boolean isPreview = false;

	private Activity activity;

	private Handler cameraHandler;

	private AppContext appContext;

	public static boolean isok = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Window window = getWindow();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		init();
		appContext = (AppContext) getApplication();
		cameraHandler = getCameraHandler();
		takingPictureBtn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 拍照并上传
				if (isok && camera != null)
				{

					isok = false;
					// camera.autoFocus(new Camera.AutoFocusCallback()
					// {
					//
					// @Override
					// public void onAutoFocus(boolean success,
					// Camera camera)
					// {
					// if (success)
					// {
					// 检测网络
					if (!appContext.isNetworkConnected())
					{
						MessageUtils.sendMessage(cameraHandler,
								R.id.no_network, null);
						return;
					}

					Parameters parameters = camera.getParameters();

					boolean isFlashOn = configPres.getBoolean("flashOnOff",
							false);

					if (!isFlashOn)
					{
						parameters
								.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
					} else
					{
						parameters
								.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
					}

					camera.setParameters(parameters);

					// 界面更新上传图片中
					MessageUtils.sendMessage(cameraHandler, R.id.camera_upload,
							R.string.upload);

					// 调用拍照方法
					camera.takePicture(null, null, new Camera.PictureCallback()
					{

						@Override
						public void onPictureTaken(final byte[] data,
								Camera camera)
						{
							System.out
									.println("picture data is " + data.length);
							new Thread(new Runnable()
							{

								@Override
								public void run()
								{
									// 压缩图片大小
									Bitmap bitmap = ImgUrils.Bytes2Bimap(data);

									bitmap = ImgUrils.comp(bitmap);

									byte[] d = ImgUrils.Bitmap2Bytes(bitmap);

									// 图片编码
									String img = Base64.encodeToString(d,
											Base64.DEFAULT);
									try
									{
										// 上传图片
										if (HttpService.pictureUpload(img,appContext.getLname()))
										{
											// 成功
											System.out.println("success!!");
											MessageUtils.sendMessage(
													cameraHandler,
													R.id.camera_success, null);

										} else
										{
											// 失败
											System.out
													.println("camera_failure!");
											MessageUtils.sendMessage(
													cameraHandler,
													R.id.camera_failure, null);
										}
									} catch (Exception e)
									{
										e.printStackTrace();
									}

								}
							}).start();
						}
					});
				}
			}
		});
	}

	// }
	// });
	// }

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void init()
	{
		isok = true;
		activity = this;
		// 找到surficeView
		surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
		// 设置它的像素为800x600
		surfaceView.getHolder().setFixedSize(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 下面设置surfaceView不维护自己的缓冲区,而是等待屏幕的渲染引擎将内容推送到用户面前
		surfaceView.getHolder()
				.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// 为surficeView加入回调方法(callBack)
		surfaceView.getHolder().addCallback(new SurfaceCallback());

		takingPictureBtn = (Button) findViewById(R.id.taking_picture_btn);
		takingUpload = (TextView) findViewById(R.id.taking_upload);

		flashOnOff = (RadioButton) findViewById(R.id.flash_on_off);
		flashOnOff.setButtonDrawable(android.R.color.transparent);
		flashOnOff.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				boolean isFlashOn = configPres.getBoolean("flashOnOff", false);
				if (!isFlashOn)
				{
					flashOnOff.setBackground(getResources().getDrawable(
							R.drawable.flash_on));
					configPres.edit().putBoolean("flashOnOff", true).commit();
				} else
				{
					flashOnOff.setBackground(getResources().getDrawable(
							R.drawable.flash_off));
					configPres.edit().putBoolean("flashOnOff", false).commit();
				}
			}
		});
	}

	private Handler getCameraHandler()
	{
		return new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				int id = msg.what;
				switch (id) {
				case R.id.camera_upload:
					takingPictureBtn.setVisibility(View.GONE);
					takingUpload.setVisibility(View.VISIBLE);
					takingUpload.setText(R.string.upload);
					break;
				case R.id.camera_success:
					takingPictureBtn.setVisibility(View.VISIBLE);
					takingUpload.setVisibility(View.GONE);
					showInfo(getResources().getString(R.string.upload_success));
					restartCamera();
					break;
				case R.id.camera_failure:
					takingPictureBtn.setVisibility(View.VISIBLE);
					takingUpload.setVisibility(View.GONE);
					showError(getResources().getString(R.string.upload_failure));
					restartCamera();
					break;
				case R.id.no_network:
					showError(getResources().getString(R.string.no_network));
					restartCamera();
					break;
				default:
					break;
				}
			}

			private void restartCamera()
			{
				isok = true;
				camera.startPreview();
			}
		};
	}

	class SurfaceCallback implements SurfaceHolder.Callback
	{

		@Override
		public void surfaceCreated(SurfaceHolder holder)
		{
			try
			{
				camera = Camera.open();// 打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera
				Camera.Parameters parameters = camera.getParameters();// 得到摄像头的参数
				parameters.setPictureFormat(PixelFormat.JPEG);// 设置照片的格式
				parameters.setJpegQuality(85);// 设置照片的质量
				parameters
						.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// ①自动对焦模式
				boolean isFlashOn = configPres.getBoolean("flashOnOff", false);
				if (!isFlashOn)
				{
					parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);//
				} else
				{
					parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);//
				}

				// 屏幕一样大
				camera.setParameters(parameters);

				camera.setPreviewDisplay(surfaceView.getHolder());// 通过SurfaceView显示取景画面
				camera.startPreview();// 开始预览
				camera.cancelAutoFocus();// ②必须(自动对焦)
				isPreview = true;// 设置是否预览参数为真
			} catch (IOException e)
			{
				Log.e("aaa", e.toString());
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height)
		{
			// 实现自动对焦
			camera.autoFocus(new Camera.AutoFocusCallback()
			{
				@Override
				public void onAutoFocus(boolean success, Camera camera)
				{
					if (success)
					{
						camera.cancelAutoFocus();
					}
				}

			});
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder)
		{
			System.out.println("surfaceDestroyed is method！！！");
			if (camera != null)
			{
				if (isPreview)
				{
					// 如果正在预览
					camera.stopPreview();
					camera.release();
				}
			}
		}

	}

}
