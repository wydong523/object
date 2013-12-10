package com.google.zxing.client.android.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.zxing.client.android.dao.UserDao;
import com.google.zxing.client.android.exceptions.AuthenticationException;
import com.google.zxing.client.android.exceptions.AuthorizationException;
import com.google.zxing.client.android.exceptions.CommunicationException;
import com.google.zxing.client.android.utils.ImageUtils;
import com.google.zxing.client.android.utils.StringUtils;
import com.google.zxing.client.android.vo.AppPermissionsList;
import com.google.zxing.client.android.vo.Asset;
import com.google.zxing.client.android.vo.AssetSelectList;
import com.google.zxing.client.android.vo.ClassstructureSelectList;
import com.google.zxing.client.android.vo.FailurecodeSelectList;
import com.google.zxing.client.android.vo.HomeworkPlanSelectList;
import com.google.zxing.client.android.vo.Inbox;
import com.google.zxing.client.android.vo.LocationsSelectList;
import com.google.zxing.client.android.vo.PMSelectList;
import com.google.zxing.client.android.vo.PRLine;
import com.google.zxing.client.android.vo.PRList;
import com.google.zxing.client.android.vo.PersonSelectList;
import com.google.zxing.client.android.vo.SafetyplanSelectList;
import com.google.zxing.client.android.vo.Workorder;
import com.google.zxing.client.android.vo.WorkorderList;
import com.google.zxing.client.android.vo.WorkorderPlanTask;
import com.google.zxing.client.android.vo.WorkorderPlanTaskList;
import com.google.zxing.client.android.vo.WorkorderPlanWplaborList;
import com.google.zxing.client.android.webservice.ApiService;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * ȫ��Ӧ�ó����ࣺ���ڱ���͵���ȫ��Ӧ�����ü�������������
 * 
 * @author ���嶰
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class AppContext extends Application
{

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	private final static int RETRY_TIME = 3;// ���Դ���

	public static final int PAGE_SIZE = 20;// Ĭ�Ϸ�ҳ��С
	private static final int CACHE_TIME = 60 * 60000;// ����ʧЧʱ��

	private Activity SMSActivity;// ����sms���յ�activity

	private String serverIp;// ���������ӵ�ַ

	private String address = null;

	private int prFlag;// PR�б��������ɾ���ı��

	private int prCurPosition;// PR�б����item��λ��

	private int prLineCurPostion;// Prline�б����λ��

	private int listClickPosition;// list�б����λ�� (�Ժ��п��滻PR���λ�ã�ͳһ��һ��ȫ�ֱ�����ʶ)

	private int listLineClickPosition;// list�б��¼��б����λ��
										// (�Ժ��п��滻PRLine���λ�ã�ͳһ��һ��ȫ�ֱ�����ʶ)

	private String prlineid;// �������prlineid

	private PRLine curPrLine;// ��ǰPRline

	private String prId;// �����PR�б�item�����ݿ�id

	private int pageIndex = 1;// ��ǰҳ��

	private boolean login = false; // ��¼״̬

	private String loginUid = null; // ��¼�û���id

	private HashMap<String, TreeSet<String>> appOperateAuth;// �û�����Ȩ��

	public String getLoginUid()
	{
		return loginUid;
	}

	public void setLoginUid(String loginUid)
	{
		this.loginUid = loginUid;
	}

	private boolean isDJ = false;// ��ǰ�˻��Ƿ񶳽�

	private Handler jcdjHanlder;

	private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();

	// �û�����Ӧ�ó���Ȩ�޼���
	private TreeSet<String> apps = new TreeSet<String>();

	@Override
	public void onCreate()
	{
		super.onCreate();
		// ע��App�쳣����������
		// Thread.setDefaultUncaughtExceptionHandler(AppException
		// .getAppExceptionHandler());
	}

	/**
	 * ����PR
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String addPrData(String parmas) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String result = null;
		int time = 0;
		// String key = "tweetlist_" + catalog + "_" + pageIndex + "_" +
		// PAGE_SIZE;������
		if (isNetworkConnected())
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			result = service.addPrData(url, parmas);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
		}
		return result;
	}

	/**
	 * ɾ��pr
	 * 
	 * @param prid
	 * @return
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String delPrData(String prid) throws MalformedURLException
	{
		String result = null;
		int time = 0;
		if (isNetworkConnected())
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			do
			{
				try
				{
					result = service.delPrData(url, prid);
					break;
				} catch (CommunicationException e)
				{
					e.printStackTrace();
					time++;
					continue;
				} catch (AuthorizationException e)
				{
					e.printStackTrace();
					time++;
					continue;
				} catch (AuthenticationException e)
				{
					e.printStackTrace();
					time++;
					continue;
				}
			} while (time < RETRY_TIME);
		} else
		{
			// TODO ���洦��
		}
		return result;
	}

	/**
	 * �޸�pr
	 * 
	 * @param string
	 * @return
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String modifyPrData(String parmas) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String result = null;
		// String key = "tweetlist_" + catalog + "_" + pageIndex + "_" +
		// PAGE_SIZE;������
		if (isNetworkConnected())
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			result = service.modifyPrData(url, parmas, prId);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
		}
		return result;
	}

	/**
	 * ��ѯprline
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public ArrayList<PRLine> queryPrLineList() throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		ArrayList<PRLine> prlines = null;

		if (isNetworkConnected())
		{
			URL url = new URL(address);
			prlines = ApiService.queryPrLineData(url, prId);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
			// list = (TweetList) readObject(key);
			// if (list == null)
			// list = new TweetList();
		}
		return prlines;
	}

	/**
	 * 
	 * @Title: queryWorktypeSelectValue
	 * @Description: ��ȡ��������ѡ��ֵ�б�
	 * @param @param className vo
	 * @param @param tableName ����
	 * @param @param tableParma ����
	 * @param @param whereStrParma ����
	 * @param @return
	 * @param @throws MalformedURLException
	 * @param @throws CommunicationException
	 * @param @throws AuthorizationException
	 * @param @throws AuthenticationException
	 * @return Serializable �����
	 * @throws
	 */
	public Serializable queryWorktypeSelectValue(String className,
			String tableName, String tableParma, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable worktypeValues = null;
		String key = "WorktypeSelectValues_" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			worktypeValues = ApiService.querySelectValue(url, className,
					tableName, tableParma, whereStrParma);
			saveObject(worktypeValues, key);
		} else
		{
			worktypeValues = (Serializable) readObject(key);
		}
		return worktypeValues;
	}

	/**
	 * ɾ��prline����
	 * 
	 * @param prlineid
	 * @return
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String delPrLine() throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String result = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			result = ApiService.delPrLine(url, prId, prlineid);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
			// list = (TweetList) readObject(key);
			// if (list == null)
			// list = new TweetList();
		}
		return result;
	}

	public String modifyPrline(String parmas) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String result = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			result = ApiService.modifyPrline(url, parmas, prlineid, prId);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
			// list = (TweetList) readObject(key);
			// if (list == null)
			// list = new TweetList();
		}
		return result;
	}

	public String addPrLine(String parmas) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String result = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			result = ApiService.addPrLine(url, parmas, prId);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
			// list = (TweetList) readObject(key);
			// if (list == null)
			// list = new TweetList();
		}
		return result;
	}

	/**
	 * ��֤�豸Ψһ��
	 * 
	 * @param phoneNum
	 * @param deviceId
	 * @return
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public String checkDevice(String phoneNum, String deviceId)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		String result = null;
		URL url = new URL(address);
		result = ApiService.checkDevice(url, phoneNum, deviceId);
		return result;
	}

	/**
	 * ��������
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws CommunicationException
	 * @throws AuthorizationException
	 * @throws AuthenticationException
	 */
	public String inwfro(String ownerTable, String wfName, String pykeyid)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		String result = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			result = ApiService.inwfro(url, ownerTable, wfName, pykeyid);
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
			// list = (TweetList) readObject(key);
			// if (list == null)
			// list = new TweetList();
		}
		return result;
	}

	/**
	 * ����
	 * 
	 * @param inbox
	 * @return
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 */
	public String spwfro(Inbox inbox) throws CommunicationException,
			AuthorizationException, AuthenticationException,
			MalformedURLException
	{
		String result = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);

			result = ApiService.spwfro(url, inbox.getOwnerid());
			// TODO ���洦��
		} else
		{
			// TODO ���洦��
			// list = (TweetList) readObject(key);
			// if (list == null)
			// list = new TweetList();
		}
		return result;
	}

	/**
	 * ��ȡpr����
	 * 
	 * @return
	 * @throws CommunicationException
	 * @throws AuthorizationException
	 * @throws AuthenticationException
	 * @throws MalformedURLException
	 */
	public synchronized PRList getPRPage() throws CommunicationException,
			AuthorizationException, AuthenticationException,
			MalformedURLException
	{
		PRList result = null;
		String key = "prlist_" + loginUid + "_" + pageIndex + "_" + PAGE_SIZE;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			result = ApiService.getPRPage(url, PAGE_SIZE,
					String.valueOf(pageIndex));
			result.setCacheKey(key);
			// ���洦��
			saveObject(result, key);
		} else
		{
			result = (PRList) readObject(key);
			if (result == null)
				result = new PRList();
		}
		return result;

	}

	/**
	 * ��ѯ��ǰ�û���Ӧ��Ӧ�ó���Ȩ��
	 * 
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 */
	public AppPermissionsList queryApp(String username)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		AppPermissionsList result = null;
		String key = "AppPermissionsList_" + username;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			result = ApiService.queryApp(url, username);
		} else
		{
			result = (AppPermissionsList) readObject(key);
		}
		return result;
	}

	public void showError(String result)
	{
		Toast.makeText(getApplicationContext(), (CharSequence) result,
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * ��ʾ�����ı���Ϣ
	 * 
	 * @param result
	 * @param activity
	 */
	public void showError(String result, Activity activity)
	{
		Style croutonStyle = Style.ALERT;

		final Crouton crouton;
		crouton = Crouton.makeText(activity, result, croutonStyle);
		crouton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				crouton.cancel();
			}
		}).setConfiguration(Configuration.DEFAULT).show();
	}

	/**
	 * ��ʾ�Զ����ı���Ϣ
	 * 
	 * @param result
	 * @param activity
	 */
	public void showInfo(String result, Activity activity, Style style)
	{
		Style croutonStyle = style;

		final Crouton crouton;
		crouton = Crouton.makeText(activity, result, croutonStyle);
		crouton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				crouton.cancel();
			}
		}).setConfiguration(Configuration.DEFAULT).show();
	}

	public WorkorderList queryWork(int pageIndex) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		WorkorderList works = null;
		String key = "workorderlist_" + loginUid + "_" + pageIndex + "_"
				+ PAGE_SIZE;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			works = ApiService.queryWorkorder(url, pageIndex, PAGE_SIZE);
			saveObject(works, key);
		} else
		{
			works = (WorkorderList) readObject(key);
			if (works == null)
				works = new WorkorderList();
		}
		return works;
	}

	/**
	 * ��⵱ǰϵͳ�����Ƿ�Ϊ����ģʽ
	 * 
	 * @return
	 */
	public boolean isAudioNormal()
	{
		AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
	}

	/**
	 * ��������Ƿ����
	 * 
	 * @return
	 */
	public boolean isNetworkConnected()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * ����Ƿ�������
	 * 
	 * @param context
	 *            ��ҳ����
	 * @return
	 */
	public boolean doWeHaveInternet(Context context)
	{

		if (context == null)
		{
			return false;
		}
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = manager.getActiveNetworkInfo();
		if (netInfo == null)
		{
			return false;
		}

		return netInfo.isConnected();
	}

	/**
	 * ��ȡ��ǰ��������
	 * 
	 * @return 0��û������ 1��WIFI���� 2��WAP���� 3��NET����
	 */
	public int getNetworkType()
	{
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null)
		{
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE)
		{
			String extraInfo = networkInfo.getExtraInfo();
			if (!StringUtils.isEmpty(extraInfo))
			{
				if (extraInfo.toLowerCase().equals("cmnet"))
				{
					netType = NETTYPE_CMNET;
				} else
				{
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI)
		{
			netType = NETTYPE_WIFI;
		}
		return netType;
	}

	/**
	 * �жϵ�ǰ�汾�Ƿ����Ŀ��汾�ķ���
	 * 
	 * @param VersionCode
	 * @return
	 */
	public static boolean isMethodsCompat(int VersionCode)
	{
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		return currentVersion >= VersionCode;
	}

	/**
	 * �Ƿ�����������
	 * 
	 * @return
	 */
	public boolean isCheckUp()
	{
		String perf_checkup = getProperty(AppConfig.CONF_CHECKUP);
		// Ĭ���ǿ���
		if (StringUtils.isEmpty(perf_checkup))
			return true;
		else
			return StringUtils.toBool(perf_checkup);
	}

	/**
	 * ��ȡApp��װ����Ϣ
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo()
	{
		PackageInfo info = null;
		try
		{
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e)
		{
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	/**
	 * �ϴ�����
	 */
	public void upload()
	{
		// TODO ��ȡ��������
		readObject("");
	}

	/**
	 * �û��Ƿ��¼
	 * 
	 * @return
	 */
	public boolean isLogin()
	{
		return login;
	}

	/**
	 * �����¼��Ϣ
	 * 
	 * @param username
	 * @param pwd
	 */
	// public void saveLoginInfo(final User user) {
	// this.loginUid = user.getUid();
	// this.login = true;
	// setProperties(new Properties() {
	// {
	// setProperty("user.uid", String.valueOf(user.getUid()));
	// setProperty("user.name", user.getName());
	// setProperty("user.face", FileUtils.getFileName(user.getFace()));//
	// �û�ͷ��-�ļ���
	// setProperty("user.account", user.getAccount());
	// setProperty("user.pwd",
	// CyptoUtils.encode("oschinaApp", user.getPwd()));
	// setProperty("user.location", user.getLocation());
	// setProperty("user.followers",
	// String.valueOf(user.getFollowers()));
	// setProperty("user.fans", String.valueOf(user.getFans()));
	// setProperty("user.score", String.valueOf(user.getScore()));
	// setProperty("user.isRememberMe",
	// String.valueOf(user.isRememberMe()));// �Ƿ��ס�ҵ���Ϣ
	// }
	// });
	// }

	/**
	 * �����¼��Ϣ
	 */
	// public void cleanLoginInfo() {
	// this.loginUid = 0;
	// this.login = false;
	// removeProperty("user.uid", "user.name", "user.face", "user.account",
	// "user.pwd", "user.location", "user.followers", "user.fans",
	// "user.score", "user.isRememberMe");
	// }

	/**
	 * ��ȡ��¼��Ϣ
	 * 
	 * @return
	 */
	// public User getLoginInfo() {
	// User lu = new User();
	// lu.setUid(StringUtils.toInt(getProperty("user.uid"), 0));
	// lu.setName(getProperty("user.name"));
	// lu.setFace(getProperty("user.face"));
	// lu.setAccount(getProperty("user.account"));
	// lu.setPwd(CyptoUtils.decode("oschinaApp", getProperty("user.pwd")));
	// lu.setLocation(getProperty("user.location"));
	// lu.setFollowers(StringUtils.toInt(getProperty("user.followers"), 0));
	// lu.setFans(StringUtils.toInt(getProperty("user.fans"), 0));
	// lu.setScore(StringUtils.toInt(getProperty("user.score"), 0));
	// lu.setRememberMe(StringUtils.toBool(getProperty("user.isRememberMe")));
	// return lu;
	// }

	/**
	 * �����û�ͷ��
	 * 
	 * @param fileName
	 * @param bitmap
	 */
	public void saveUserFace(String fileName, Bitmap bitmap)
	{
		try
		{
			ImageUtils.saveImage(this, fileName, bitmap);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�û�ͷ��
	 * 
	 * @param key
	 * @return
	 * @throws AppException
	 */
	public Bitmap getUserFace(String key)
	{
		FileInputStream fis = null;
		try
		{
			fis = openFileInput(key);
			return BitmapFactory.decodeStream(fis);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				fis.close();
			} catch (Exception e)
			{
			}
		}
		return null;
	}

	/**
	 * �жϻ����Ƿ����
	 * 
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile)
	{
		boolean exist = false;
		File data = getFileStreamPath(cachefile);
		if (data.exists())
			exist = true;
		return exist;
	}

	/**
	 * �жϻ����Ƿ�ʧЧ
	 * 
	 * @param cachefile
	 * @return
	 */
	public boolean isCacheDataFailure(String cachefile)
	{
		boolean failure = false;
		File data = getFileStreamPath(cachefile);
		if (data.exists()
				&& (System.currentTimeMillis() - data.lastModified()) > CACHE_TIME)
			failure = true;
		else if (!data.exists())
			failure = true;
		return failure;
	}

	/**
	 * ���app����
	 */
	public void clearAppCache()
	{
		// ���webview����
		// File file = CacheManager.getCacheFileBaseDir();
		File file = null;
		if (file != null && file.exists() && file.isDirectory())
		{
			for (File item : file.listFiles())
			{
				item.delete();
			}
			file.delete();
		}
		deleteDatabase("webview.db");
		deleteDatabase("webview.db-shm");
		deleteDatabase("webview.db-wal");
		deleteDatabase("webviewCache.db");
		deleteDatabase("webviewCache.db-shm");
		deleteDatabase("webviewCache.db-wal");
		// ������ݻ���
		clearCacheFolder(getFilesDir(), System.currentTimeMillis());
		clearCacheFolder(getCacheDir(), System.currentTimeMillis());
		// 2.2�汾���н�Ӧ�û���ת�Ƶ�sd���Ĺ���
		if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO))
		{
			clearCacheFolder(getExternalCacheDir(), System.currentTimeMillis());
		}
	}

	/**
	 * �������Ŀ¼
	 * 
	 * @param dir
	 *            Ŀ¼
	 * @param numDays
	 *            ��ǰϵͳʱ��
	 * @return
	 */
	private int clearCacheFolder(File dir, long curTime)
	{
		int deletedFiles = 0;
		if (dir != null && dir.isDirectory())
		{
			try
			{
				for (File child : dir.listFiles())
				{
					if (child.isDirectory())
					{
						deletedFiles += clearCacheFolder(child, curTime);
					}
					if (child.lastModified() < curTime)
					{
						if (child.delete())
						{
							deletedFiles++;
						}
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}

	/**
	 * �����󱣴浽�ڴ滺����
	 * 
	 * @param key
	 * @param value
	 */
	public void setMemCache(String key, Object value)
	{
		memCacheRegion.put(key, value);
	}

	/**
	 * ���ڴ滺���л�ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public Object getMemCache(String key)
	{
		return memCacheRegion.get(key);
	}

	/**
	 * ������̻���
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void setDiskCache(String key, String value) throws IOException
	{
		FileOutputStream fos = null;
		try
		{
			fos = openFileOutput("cache_" + key + ".data", Context.MODE_PRIVATE);
			fos.write(value.getBytes());
			fos.flush();
		} finally
		{
			try
			{
				fos.close();
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * ��ȡ���̻�������
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDiskCache(String key) throws IOException
	{
		FileInputStream fis = null;
		try
		{
			fis = openFileInput("cache_" + key + ".data");
			byte[] datas = new byte[fis.available()];
			fis.read(datas);
			return new String(datas);
		} finally
		{
			try
			{
				fis.close();
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * �������
	 * 
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file)
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			fos = openFileOutput(file, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		} finally
		{
			try
			{
				oos.close();
			} catch (Exception e)
			{
			}
			try
			{
				fos.close();
			} catch (Exception e)
			{
			}
		}
	}

	/**
	 * ��ȡ����
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file)
	{
		if (!isExistDataCache(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try
		{
			fis = openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable) ois.readObject();
		} catch (FileNotFoundException e)
		{
		} catch (Exception e)
		{
			e.printStackTrace();
			// �����л�ʧ�� - ɾ�������ļ�
			if (e instanceof InvalidClassException)
			{
				File data = getFileStreamPath(file);
				data.delete();
			}
		} finally
		{
			try
			{
				ois.close();
			} catch (Exception e)
			{
			}
			try
			{
				fis.close();
			} catch (Exception e)
			{
			}
		}
		return null;
	}

	public boolean delCache(String key)
	{
		if (!isExistDataCache(key))
			return false;

		File data = getFileStreamPath(key);
		return data.delete();
	}

	/**
	 * @return the prFlag
	 */
	public int getPrFlag()
	{
		return prFlag;
	}

	/**
	 * @param prFlag
	 *            the prFlag to set
	 */
	public void setPrFlag(int prFlag)
	{
		this.prFlag = prFlag;
	}

	/**
	 * @return the prId
	 */
	public String getPrId()
	{
		return prId;
	}

	/**
	 * @param prId
	 *            the prId to set
	 */
	public void setPrId(String prId)
	{
		this.prId = prId;
	}

	/**
	 * @return the prCurPosition
	 */
	public int getPrCurPosition()
	{
		return prCurPosition;
	}

	/**
	 * @param prCurPosition
	 *            the prCurPosition to set
	 */
	public void setPrCurPosition(int prCurPosition)
	{
		this.prCurPosition = prCurPosition;
	}

	/**
	 * @return the prLineCurPostion
	 */
	public int getPrLineCurPostion()
	{
		return prLineCurPostion;
	}

	/**
	 * @param prLineCurPostion
	 *            the prLineCurPostion to set
	 */
	public void setPrLineCurPostion(int prLineCurPostion)
	{
		this.prLineCurPostion = prLineCurPostion;
	}

	/**
	 * @return the prlineid
	 */
	public String getPrlineid()
	{
		return prlineid;
	}

	/**
	 * @param prlineid
	 *            the prlineid to set
	 */
	public void setPrlineid(String prlineid)
	{
		this.prlineid = prlineid;
	}

	/**
	 * @return the curPrLine
	 */
	public PRLine getCurPrLine()
	{
		return curPrLine;
	}

	/**
	 * @param curPrLine
	 *            the curPrLine to set
	 */
	public void setCurPrLine(PRLine curPrLine)
	{
		this.curPrLine = curPrLine;
	}

	/**
	 * @return the pageIndex
	 */
	public int getPageIndex()
	{
		return pageIndex;
	}

	/**
	 * @param pageIndex
	 *            the pageIndex to set
	 */
	public void setPageIndex(int pageIndex)
	{
		this.pageIndex = pageIndex;
	}

	public boolean containsProperty(String key)
	{
		Properties props = getProperties();
		return props.containsKey(key);
	}

	public void setProperties(Properties ps)
	{
		AppConfig.getAppConfig(this).set(ps);
	}

	public Properties getProperties()
	{
		return AppConfig.getAppConfig(this).get();
	}

	public void setProperty(String key, String value)
	{
		AppConfig.getAppConfig(this).set(key, value);
	}

	public String getProperty(String key)
	{
		return AppConfig.getAppConfig(this).get(key);
	}

	public void removeProperty(String... key)
	{
		AppConfig.getAppConfig(this).remove(key);
	}

	/**
	 * @return the serverIp
	 */
	public String getServerIp()
	{
		return serverIp;
	}

	/**
	 * @param serverIp
	 *            the serverIp to set
	 */
	public void setServerIp(String serverIp)
	{
		this.serverIp = serverIp;
		address = "http://" + serverIp
				+ ":7001/meaweb/services/MOBILESERVICEINTER";
		// address = "http://" + serverIp + ":9080/meaweb/services/UDUSER2";

	}

	/**
	 * @return the sMSActivity
	 */
	public Activity getSMSActivity()
	{
		return SMSActivity;
	}

	/**
	 * @param sMSActivity
	 *            the sMSActivity to set
	 */
	public void setSMSActivity(Activity sMSActivity)
	{
		SMSActivity = sMSActivity;
	}

	/**
	 * @return the isDJ
	 */
	public boolean isDJ()
	{
		return isDJ;
	}

	/**
	 * @param isDJ
	 *            the isDJ to set
	 */
	public void setDJ(boolean isDJ)
	{
		this.isDJ = isDJ;
	}

	/**
	 * @return the jcdjHanlder
	 */
	public Handler getJcdjHanlder()
	{
		return jcdjHanlder;
	}

	/**
	 * @param jcdjHanlder
	 *            the jcdjHanlder to set
	 */
	public void setJcdjHanlder(Handler jcdjHanlder)
	{
		this.jcdjHanlder = jcdjHanlder;
	}

	/**
	 * @return the apps
	 */
	public TreeSet<String> getApps()
	{
		return apps;
	}

	/**
	 * @param apps
	 *            the apps to set
	 */
	public void setApps(TreeSet<String> apps)
	{
		this.apps = apps;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	public String addWorkorder(boolean isNetwork, String parmas,
			Workorder workorder) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String result = "";
		if (isNetwork)
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			result = service.addWorkorder(url, parmas);
		} else
		{
			showError(result);
		}
		return result;
	}

	public String modifyWorkorder(boolean isNetwork, String parmas,
			Workorder workorder) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String key = "workorder_modify" + System.currentTimeMillis();
		String result = "";
		if (isNetwork)
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			result = service.modifyWorkorder(url, parmas,
					workorder.getWorkorderid());
		} else
		{
			boolean isSave = saveObject(workorder, key);
			if (isSave)
			{
				result = key;
				showError("����ɹ�!");
			} else
			{
				result = "FALSE";
				showError("����ʧ��!");
			}
		}
		return result;
	}

	/**
	 * @return the listClickPosition
	 */
	public int getListClickPosition()
	{
		return listClickPosition;
	}

	/**
	 * @param listClickPosition
	 *            the listClickPosition to set
	 */
	public void setListClickPosition(int listClickPosition)
	{
		this.listClickPosition = listClickPosition;
	}

	/**
	 * @return the listLineClickPosition
	 */
	public int getListLineClickPosition()
	{
		return listLineClickPosition;
	}

	/**
	 * @param listLineClickPosition
	 *            the listLineClickPosition to set
	 */
	public void setListLineClickPosition(int listLineClickPosition)
	{
		this.listLineClickPosition = listLineClickPosition;
	}

	public String delWorkorder(String workorderid) throws MalformedURLException
	{
		String result = null;
		int time = 0;
		if (isNetworkConnected())
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			do
			{
				try
				{
					result = service.delWorkorder(url, workorderid);
					break;
				} catch (CommunicationException e)
				{
					e.printStackTrace();
					time++;
					continue;
				} catch (AuthorizationException e)
				{
					e.printStackTrace();
					time++;
					continue;
				} catch (AuthenticationException e)
				{
					e.printStackTrace();
					time++;
					continue;
				}
			} while (time < RETRY_TIME);
		} else
		{
			// TODO ���洦��
		}
		return result;
	}

	public HashMap<String, TreeSet<String>> getAppOperateAuth()
	{
		return this.appOperateAuth;
	}

	public void queryAppOperateAuth() throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String key = "app_operate_auth_" + loginUid;
		if (isNetworkConnected())
		{
			ApiService service = new ApiService();
			URL url = new URL(address);
			appOperateAuth = service.queryAppOperateAuth(url, loginUid);
			saveObject(appOperateAuth, key);
		} else
		{
			appOperateAuth = (HashMap<String, TreeSet<String>>) readObject(key);
		}
	}

	/**
	 * ��ȡӦ�ó����Ӧ�Ĳ���Ȩ��(��:��ɾ�Ĳ�)
	 * 
	 * @param appName
	 *            Ӧ�ó�������
	 * @return
	 */
	public TreeSet<String> getAppAuth(String appName)
	{
		HashMap<String, TreeSet<String>> authObj = getAppOperateAuth();
		TreeSet<String> auths = authObj.get(appName);
		return auths;
	}

	/**
	 * @Title: queryUserSite
	 * @Description: ��ѯ�û�λ��
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String queryUserSite(Activity activity)
	{
		UserDao userDao = new UserDao(activity);
		return userDao.queryUserSite(loginUid);
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryLocationsSelectValue
	 * @Description: TODO
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableParma
	 * @param @param whereStrParma
	 * @param @return
	 * @return LocationsSelectList
	 * @throws
	 */
	public Serializable queryLocationsSelectValue(String className,
			String tableName, String tableParma, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable locationValues = null;
		String key = "queryLocationsSelectValue_" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			locationValues = ApiService.querySelectValue(url, className,
					tableName, tableParma, whereStrParma);
			saveObject(locationValues, key);
		} else
		{
			locationValues = (Serializable) readObject(key);
		}
		return locationValues;
	}

	/**
	 * @throws MalformedURLException
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @Title: queryAssetSelectValue
	 * @Description: ��ѯ�ʲ�
	 * @param @param className
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return AssetSelectList
	 * @throws
	 */
	public Serializable queryAssetSelectValue(String className,
			String tableName, String tableName2, String whereStrParma)
			throws CommunicationException, AuthorizationException,
			AuthenticationException, MalformedURLException
	{
		Serializable assetValues = null;
		String key = "queryAssetSelectValue" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			assetValues = ApiService.querySelectValue(url, className,
					tableName, tableName2, whereStrParma);
			saveObject(assetValues, key);
		} else
		{
			assetValues = (Serializable) readObject(key);
		}
		return assetValues;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: querySecurityPlanSelectValue
	 * @Description: ��ѯ��ȫ�ƻ�
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return SafetyplanSelectList
	 * @throws
	 */
	public Serializable querySecurityPlanSelectValue(String className,
			String tableName, String tableName2, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{

		Serializable securityPlanValues = null;
		String key = "querySecurityPlanSelectValue" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			securityPlanValues = ApiService.querySelectValue(url, className,
					tableName, tableName2, whereStrParma);
			saveObject(securityPlanValues, key);
		} else
		{
			securityPlanValues = (Serializable) readObject(key);
		}
		return securityPlanValues;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryResponsibilityPersonPlanSelectValue
	 * @Description: �����˲�ѯ
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return PersonSelectList
	 * @throws
	 */
	public Serializable queryResponsibilityPersonSelectValue(String className,
			String tableName, String tableName2, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable responsibilityPersonValues = null;
		String key = "queryResponsibilityPersonPlanSelectValue_" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			responsibilityPersonValues = ApiService.querySelectValue(url,
					className, tableName, tableName2, whereStrParma);
			saveObject(responsibilityPersonValues, key);
		} else
		{
			responsibilityPersonValues = (Serializable) readObject(key);
		}
		return responsibilityPersonValues;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryPmSelectValue
	 * @Description: TODO
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return PMSelectList
	 * @throws
	 */
	public Serializable queryPmSelectValue(String className, String tableName,
			String tableName2, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable pmValues = null;
		String key = "queryPmSelectValue" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			pmValues = ApiService.querySelectValue(url, className, tableName,
					tableName2, whereStrParma);
			saveObject(pmValues, key);
		} else
		{
			pmValues = (Serializable) readObject(key);
		}
		return pmValues;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryFailurecodeSelectValue
	 * @Description: TODO
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return FailurecodeSelectList
	 * @throws
	 */
	public Serializable queryFailurecodeSelectValue(String className,
			String tableName, String tableName2, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{

		Serializable fsValues = null;
		String key = "queryFailurecodeSelectValue" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			fsValues = ApiService.querySelectValue(url, className, tableName,
					tableName2, whereStrParma);
			saveObject(fsValues, key);
		} else
		{
			fsValues = (Serializable) readObject(key);
		}
		return fsValues;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryHomeworkPlanSelectValue
	 * @Description: TODO
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return HomeworkPlanSelectList
	 * @throws
	 */
	public Serializable queryHomeworkPlanSelectValue(String className,
			String tableName, String tableName2, String whereStrParma)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable hpValues = null;
		String key = "" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			hpValues = ApiService.querySelectValue(url, className, tableName,
					tableName2, whereStrParma);
			saveObject(hpValues, key);
		} else
		{
			hpValues = (Serializable) readObject(key);
		}
		return hpValues;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryClassstructureSelectValue
	 * @Description: TODO
	 * @param @param name
	 * @param @param tableName
	 * @param @param tableName2
	 * @param @param whereStrParma
	 * @param @return
	 * @return ClassstructureSelectList
	 * @throws
	 */
	public Serializable querySelectValue(String className, String tableName,
			String tableName2, String whereStrParma, String flag)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable values = null;
		String key = flag + "_" + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			values = ApiService.querySelectValue(url, className, tableName,
					tableName2, whereStrParma);
			saveObject(values, key);
		} else
		{
			values = (Serializable) readObject(key);
		}
		return values;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryPlanTaskList
	 * @Description: TODO
	 * @param @param planTaskPageindex
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable queryPlanTaskList(String wonum, int pageIndex)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable tasks = null;
		String key = "PlanTaskList_" + loginUid + "_" + pageIndex + "_"
				+ PAGE_SIZE;
		String whereStr = "parent='" + wonum + "'";
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			tasks = ApiService.queryList("", WorkorderPlanTask.class.getName(),
					url, "woactivity", whereStr, pageIndex, PAGE_SIZE);
			saveObject(tasks, key);
		} else
		{
			tasks = (WorkorderPlanTaskList) readObject(key);
			if (tasks == null)
				tasks = new WorkorderPlanTaskList();
		}
		return tasks;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: queryPlanTaskList
	 * @Description: TODO
	 * @param @param planTaskPageindex
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable queryPlanWplaborList(String wonum, int pageIndex)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable wplabors = null;
		String key = "PlanWplaborList" + loginUid + "_" + pageIndex + "_"
				+ PAGE_SIZE;
		String whereStr = "wonum='" + wonum + "'";
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			wplabors = ApiService.queryList("",
					WorkorderPlanTask.class.getName(), url, "wplabor",
					whereStr, pageIndex, PAGE_SIZE);
			saveObject(wplabors, key);
		} else
		{
			wplabors = (WorkorderPlanWplaborList) readObject(key);
			if (wplabors == null)
				wplabors = new WorkorderPlanTaskList();
		}
		return wplabors;
	}

	/**
	 * 
	 * @Title: queryList
	 * @Description: ��ѯ�б�����
	 * @param @param flag
	 * @param @param whereStr
	 * @param @param appName
	 * @param @param table
	 * @param @param pageIndex
	 * @param @return
	 * @param @throws MalformedURLException
	 * @param @throws CommunicationException
	 * @param @throws AuthorizationException
	 * @param @throws AuthenticationException
	 * @return Serializable
	 * @throws
	 */
	public Serializable queryList(String flag, String className,
			String appName, String table, String whereStr, int pageIndex)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable wplabors = null;
		String key = flag + loginUid + "_" + pageIndex + "_" + PAGE_SIZE;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			wplabors = ApiService.queryList(appName, className, url, table,
					whereStr, pageIndex, PAGE_SIZE);
			saveObject(wplabors, key);
		} else
		{
			wplabors = readObject(key);
		}
		return wplabors;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: getValueByAnother
	 * @Description: TODO
	 * @param @param table
	 * @param @param attr
	 * @param @param value
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public String[] getValueByAnother(String table, String relationship,
			String attr, String value) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		String[] contents = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			String result = ApiService.getValueByAnother(url,
					Asset.class.getName(), table, relationship, attr, value);
			if (result != null)
			{
				contents = result.split("&");
			}
		} else
		{

		}
		return contents;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: add
	 * @Description: TODO
	 * @param @param table
	 * @param @param appName
	 * @param @param listValue
	 * @param @param reListAttribute
	 * @return void
	 * @throws
	 */
	public Serializable add(String flag, String className, String table,
			String appName, String pyKeyid, String relationShip,
			String listValue) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		Serializable obj = null;
		String key = flag + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.add(url, className, table, appName, pyKeyid,
					relationShip, listValue);
			saveObject(obj, key);
		} else
		{
			obj = readObject(key);
		}
		return obj;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: add
	 * @Description: TODO
	 * @param @param table
	 * @param @param appName
	 * @param @param listValue
	 * @param @param reListAttribute
	 * @return void
	 * @throws
	 */
	public Serializable modify(String flag, String className, String table,
			String pyKeyid, String relationShip, String tableLine,
			String tableLineId, String listValue) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		Serializable obj = null;
		String key = flag + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.modify(url, className, table, pyKeyid,
					relationShip, tableLine, tableLineId, listValue);
			saveObject(obj, key);
		} else
		{
			obj = readObject(key);
		}
		return obj;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: delete
	 * @Description: TODO
	 * @param @param flag
	 * @param @param className
	 * @param @param table
	 * @param @param pyKeyid
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable delete(String flag, String className, String table,
			String pyKeyid, String tableLine, String tableLineid,
			String relationShip) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		Serializable obj = null;
		String key = flag + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.delete(url, className, table, pyKeyid, tableLine,
					tableLineid, relationShip);
			saveObject(obj, key);
		} else
		{
			obj = readObject(key);
		}
		return obj;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: getResult
	 * @Description: TODO
	 * @param @param table
	 * @param @param listValue
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable getResult(String flag, String className, String table,
			String listValue) throws MalformedURLException,
			CommunicationException, AuthorizationException,
			AuthenticationException
	{
		Serializable obj = null;
		String key = flag + loginUid;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.getResult(url, className, table, listValue);
			saveObject(obj, key);
		} else
		{
			obj = readObject(key);
		}
		return obj;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: selectFuzzyQuery
	 * @Description: TODO
	 * @param @param table
	 * @param @param listValue
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable selectFuzzyQuery(String table, String listValue)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable obj = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.selectFuzzyQuery(url, table, listValue);
		} else
		{
		}
		return obj;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: getAssignNodeActions
	 * @Description: ��ȡ������������
	 * @param @param className
	 * @param @param userid
	 * @param @param pykeyid
	 * @param @param appName
	 * @param @param ownerTable
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable getAssignNodeActions(String className, String userid,
			String pykeyid, String appName, String ownerTable)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable obj = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.getAssignNodeActions(url, className, userid,
					pykeyid, appName, ownerTable);
			// saveObject(obj, key);
		} else
		{
			// obj = readObject(key);
		}
		return obj;
	}

	/**
	 * @throws AuthenticationException
	 * @throws AuthorizationException
	 * @throws CommunicationException
	 * @throws MalformedURLException
	 * @Title: approvePro
	 * @Description: TODO
	 * @param @param userid
	 * @param @param memo
	 * @param @param pykeyid
	 * @param @param appName
	 * @param @param ownerTable
	 * @param @param selectWhat
	 * @param @return
	 * @return Serializable
	 * @throws
	 */
	public Serializable approvePro(String userid, String memo, String pykeyid,
			String appName, String ownerTable, boolean selectWhat)
			throws MalformedURLException, CommunicationException,
			AuthorizationException, AuthenticationException
	{
		Serializable obj = null;
		if (isNetworkConnected())
		{
			URL url = new URL(address);
			obj = ApiService.approvePro(url, userid, memo, pykeyid, appName,
					ownerTable, selectWhat);
			// saveObject(obj, key);
		} else
		{
			// obj = readObject(key);
		}
		return obj;
	}
}
