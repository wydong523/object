/**
 * Project Name:JingZhenGu
 * File Name:CarDamageSerivce.java
 * Package Name:com.gc.jingzhengu.service
 * Date:2014-6-22下午10:49:27
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:CarDamageSerivce <br/>
 * Function: 处理车损界面选项业务逻辑. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-22 下午10:49:27 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarDamageSerivce
{
	/**
	 * 界面1标示
	 */
	public static final int ONE_PAGE = 1;

	/**
	 * 界面2标示
	 */
	public static final int TWO_PAGE = 2;

	/**
	 * 界面3标示
	 */
	public static final int THREE_PAGE = 3;

	/**
	 * 界面4标示
	 */
	public static final int FOUR_PAGE = 4;

	/**
	 * 列表位置1
	 */
	public static final int POSITION_1 = 0;

	/**
	 * 列表位置2
	 */
	public static final int POSITION_2 = 1;

	/**
	 * 列表位置3
	 */
	public static final int POSITION_3 = 2;

	/**
	 * 列表位置4
	 */
	public static final int POSITION_4 = 3;

	/**
	 * 列表位置5
	 */
	public static final int POSITION_5 = 4;

	/**
	 * 列表位置6
	 */
	public static final int POSITION_6 = 5;

	/**
	 * 列表位置7
	 */
	public static final int POSITION_7 = 6;

	/**
	 * 列表位置8
	 */
	public static final int POSITION_8 = 7;

	/**
	 * 列表位置9
	 */
	public static final int POSITION_9 = 8;

	/**
	 * 列表位置10
	 */
	public static final int POSITION_10 = 9;

	/**
	 * 列表位置11
	 */
	public static final int POSITION_11 = 10;

	/**
	 * 列表位置12
	 */
	public static final int POSITION_12 = 11;

	/**
	 * 列表位置13
	 */
	public static final int POSITION_13 = 12;

	/**
	 * 列表位置14
	 */
	public static final int POSITION_14 = 13;

	/**
	 * 列表位置15
	 */
	public static final int POSITION_15 = 14;

	/**
	 * 列表位置16
	 */
	public static final int POSITION_16 = 15;

	/**
	 * 列表位置17
	 */
	public static final int POSITION_17 = 16;

	/**
	 * 前三页列表中所有·车损数据 key:滑动page标示 value:每个page下面对应的元素
	 */
	private static final Map<Integer, Map> mAllDamageData = new HashMap<Integer, Map>();

	/**
	 * 第一个车损页面对应的元素 key:当前页面点击位置的位置 value:当前位置下对应的所有元素标示
	 */
	private static final Map<Integer, List<String>> mOnePageData = new HashMap<Integer, List<String>>();

	/**
	 * 第二个车损页面对应的元素 key:当前页面点击位置的位置 value:当前位置下对应的所有元素标示
	 */
	private static final Map<Integer, List<String>> mTwoPageData = new HashMap<Integer, List<String>>();

	/**
	 * 第三个车损页面对应的元素 key:当前页面点击位置的位置 value:当前位置下对应的所有元素标示
	 */
	private static final Map<Integer, List<String>> mThreePageData = new HashMap<Integer, List<String>>();

	/**
	 * 第4个车损页面集合1对应的元素 key:当前元素的id value:当前元素的是否被选中false/true
	 */
	public static final Map<String, Boolean> mFourPageData1 = new HashMap<String, Boolean>();

	/**
	 * 第4个车损页面集合2对应的元素 key:当前元素的id value:当前元素的是否被选中false/true
	 */
	public static final Map<String, Boolean> mFourPageData2 = new HashMap<String, Boolean>();

	/**
	 * 第4个车损页面集合3对应的元素 key:当前元素的id value:当前元素的是否被选中false/true
	 */
	public static final Map<String, Boolean> mFourPageData3 = new HashMap<String, Boolean>();


	/**
	 * 页面1中列表1位置对应的所有元素(左前轮)
	 */
	private static final List<String> mPage1Position1Data = new ArrayList<String>();

	/**
	 * 页面1中列表2位置对应的所有元素(左后轮)
	 */
	private static final List<String> mPage1Position2Data = new ArrayList<String>();

	/**
	 * 页面1中列表3位置对应的所有元素(右前轮)
	 */
	private static final List<String> mPage1Position3Data = new ArrayList<String>();

	/**
	 * 页面1中列表4位置对应的所有元素(右后轮)
	 */
	private static final List<String> mPage1Position4Data = new ArrayList<String>();

	/**
	 * 页面1中列表5位置对应的所有元素(车辆顶棚)
	 */
	private static final List<String> mPage1Position5Data = new ArrayList<String>();

	/**
	 * 页面1中列表6位置对应的所有元素(后备箱盖)
	 */
	private static final List<String> mPage1Position6Data = new ArrayList<String>();

	/**
	 * 页面1中列表7位置对应的所有元素(发动机盖)
	 */
	private static final List<String> mPage1Position7Data = new ArrayList<String>();

	/**
	 * 页面1中列表8位置对应的所有元素(前保险杠)
	 */
	private static final List<String> mPage1Position8Data = new ArrayList<String>();

	/**
	 * 页面1中列表9位置对应的所有元素(后保险杠)
	 */
	private static final List<String> mPage1Position9Data = new ArrayList<String>();

	/**
	 * 页面1中列表10位置对应的所有元素(左前翼子板)
	 */
	private static final List<String> mPage1Position10Data = new ArrayList<String>();

	/**
	 * 页面1中列表11位置对应的所有元素(左后翼子板)
	 */
	private static final List<String> mPage1Position11Data = new ArrayList<String>();

	/**
	 * 页面1中列表12位置对应的所有元素(右前翼子板)
	 */
	private static final List<String> mPage1Position12Data = new ArrayList<String>();

	/**
	 * 页面1中列表13位置对应的所有元素(右后翼子板)
	 */
	private static final List<String> mPage1Position13Data = new ArrayList<String>();

	/**
	 * 页面1中列表14位置对应的所有元素(左前门)
	 */
	private static final List<String> mPage1Position14Data = new ArrayList<String>();

	/**
	 * 页面1中列表15位置对应的所有元素(左后门)
	 */
	private static final List<String> mPage1Position15Data = new ArrayList<String>();

	/**
	 * 页面1中列表16位置对应的所有元素(右前门)
	 */
	private static final List<String> mPage1Position16Data = new ArrayList<String>();

	/**
	 * 页面1中列表17位置对应的所有元素(右后门)
	 */
	private static final List<String> mPage1Position17Data = new ArrayList<String>();

	/**
	 * 页面2中列表1位置对应的所有元素(正驾驶台)
	 */
	private static final List<String> mPage2Position1Data = new ArrayList<String>();

	/**
	 * 页面2中列表2位置对应的所有元素(副驾驶台)
	 */
	private static final List<String> mPage2Position2Data = new ArrayList<String>();

	/**
	 * 页面2中列表3位置对应的所有元素(左前门)
	 */
	private static final List<String> mPage2Position3Data = new ArrayList<String>();

	/**
	 * 页面2中列表4位置对应的所有元素(左后门)
	 */
	private static final List<String> mPage2Position4Data = new ArrayList<String>();

	/**
	 * 页面2中列表5位置对应的所有元素(右前门)
	 */
	private static final List<String> mPage2Position5Data = new ArrayList<String>();

	/**
	 * 页面2中列表6位置对应的所有元素(右后门)
	 */
	private static final List<String> mPage2Position6Data = new ArrayList<String>();

	/**
	 * 页面2中列表7位置对应的所有元素(左前座椅)
	 */
	private static final List<String> mPage2Position7Data = new ArrayList<String>();

	/**
	 * 页面2中列表8位置对应的所有元素(右前座椅)
	 */
	private static final List<String> mPage2Position8Data = new ArrayList<String>();

	/**
	 * 页面2中列表9位置对应的所有元素(左后座椅)
	 */
	private static final List<String> mPage2Position9Data = new ArrayList<String>();

	/**
	 * 页面2中列表10位置对应的所有元素(右后座椅)
	 */
	private static final List<String> mPage2Position10Data = new ArrayList<String>();

	/**
	 * 页面2中列表11位置对应的所有元素(后备箱)
	 */
	private static final List<String> mPage2Position11Data = new ArrayList<String>();

	/**
	 * 页面2中列表12位置对应的所有元素(中央扶手)
	 */
	private static final List<String> mPage2Position12Data = new ArrayList<String>();

	/**
	 * 页面2中列表13位置对应的所有元素(顶棚、天窗)
	 */
	private static final List<String> mPage2Position13Data = new ArrayList<String>();

	/**
	 * 页面3中列表1位置对应的所有元素(发动机)
	 */
	private static final List<String> mPage3Position1Data = new ArrayList<String>();

	/**
	 * 页面3中列表2位置对应的所有元素(变速箱)
	 */
	private static final List<String> mPage3Position2Data = new ArrayList<String>();

	/**
	 * 我的车行驶状况良好，无故障
	 */
	public static final String CHECKBOX_1 = "04-01-00";
	/**
	 * 行驶中有异响
	 */
	public static final String CHECKBOX_2 = "04-01-01";
	/**
	 * 行驶中有跑偏
	 */
	public static final String CHECKBOX_3 = "04-01-02";
	/**
	 * 刹车有问题
	 */
	public static final String CHECKBOX_4 = "04-01-03";

	/**
	 * 我的车底盘没有任何问题
	 */
	public static final String CHECKBOX_5 = "04-02-00";
	/**
	 * 我的车辆有托底
	 */
	public static final String CHECKBOX_6 = "04-02-01";
	/**
	 * 前后悬挂有问题
	 */
	public static final String CHECKBOX_7 = "04-02-02";
	/**
	 * 减震器有问题
	 */
	public static final String CHECKBOX_8 = "04-02-03";

	/**
	 * 我的电子设备没有任何问题
	 */
	public static final String CHECKBOX_9 = "04-03-00";
	/**
	 * 仪表盘有问题
	 */
	public static final String CHECKBOX_10 = "04-03-01";
	/**
	 * 中控大屏有问题
	 */
	public static final String CHECKBOX_11 = "04-03-02";
	/**
	 * 空调有问题
	 */
	public static final String CHECKBOX_12 = "04-03-03";
	/**
	 * 窗户（天窗和四窗）控制有问题
	 */
	public static final String CHECKBOX_13 = "04-03-04";
	/**
	 * 音响或音频部件有问题
	 */
	public static final String CHECKBOX_14 = "04-03-05";
	/**
	 * 倒车雷达或影像有问题
	 */
	public static final String CHECKBOX_15 = "04-03-06";
	/**
	 * 电动座椅调整有问题
	 */
	public static final String CHECKBOX_16 = "04-03-07";
	/**
	 * 外部照明（大灯、雾灯、尾灯）有问题
	 */
	public static final String CHECKBOX_17 = "04-03-08";
	/**
	 * 内部照明（车内照明灯）有问题
	 */
	public static final String CHECKBOX_18 = "04-03-09";
	/**
	 * 喇叭有问题
	 */
	public static final String CHECKBOX_19 = "04-03-10";
	/**
	 * 电路控制（各类开关）有问题
	 */
	public static final String CHECKBOX_20 = "04-03-11";
	/**
	 * 雨刮器和周边仪器有问题
	 */
	public static final String CHECKBOX_21 = "04-03-12";

	/**
	 * 初始化所有数据
	 */
	static
	{
		// ======================初始化page2数据=================================
		/**
		 * 左前轮
		 */
		mPage1Position1Data.add("01-14-01");
		mPage1Position1Data.add("01-14-02");
		mPage1Position1Data.add("01-14-03");
		mOnePageData.put(POSITION_1, mPage1Position1Data);

		/**
		 * 左后轮
		 */
		mPage1Position2Data.add("01-15-01");
		mPage1Position2Data.add("01-15-02");
		mPage1Position2Data.add("01-15-03");
		mOnePageData.put(POSITION_2, mPage1Position2Data);

		/**
		 * 右前轮
		 */
		mPage1Position3Data.add("01-16-01");
		mPage1Position3Data.add("01-16-02");
		mPage1Position3Data.add("01-16-03");
		mOnePageData.put(POSITION_3, mPage1Position3Data);

		/**
		 * 右后轮
		 */
		mPage1Position4Data.add("01-17-01");
		mPage1Position4Data.add("01-17-02");
		mPage1Position4Data.add("01-17-03");
		mOnePageData.put(POSITION_4, mPage1Position4Data);

		/**
		 * 车辆顶棚
		 */
		mPage1Position5Data.add("01-13-01");
		mPage1Position5Data.add("01-13-02");
		mPage1Position5Data.add("01-13-03");
		mOnePageData.put(POSITION_5, mPage1Position5Data);

		/**
		 * 后备箱盖
		 */
		mPage1Position6Data.add("01-12-01");
		mPage1Position6Data.add("01-12-02");
		mPage1Position6Data.add("01-12-03");
		mPage1Position6Data.add("01-12-04");
		mPage1Position6Data.add("01-12-05");
		mPage1Position6Data.add("01-12-06");
		mOnePageData.put(POSITION_6, mPage1Position6Data);

		/**
		 * 发动机盖
		 */
		mPage1Position7Data.add("01-11-01");
		mPage1Position7Data.add("01-11-02");
		mPage1Position7Data.add("01-11-03");
		mPage1Position7Data.add("01-11-04");
		mPage1Position7Data.add("01-11-05");
		mPage1Position7Data.add("01-11-06");
		mOnePageData.put(POSITION_7, mPage1Position7Data);

		/**
		 * 前保险杠
		 */
		mPage1Position8Data.add("01-09-01");
		mPage1Position8Data.add("01-09-02");
		mPage1Position8Data.add("01-09-03");
		mPage1Position8Data.add("01-09-04");
		mPage1Position8Data.add("01-09-05");
		mPage1Position8Data.add("01-09-06");
		mOnePageData.put(POSITION_8, mPage1Position8Data);

		/**
		 * 后保险杠
		 */
		mPage1Position9Data.add("01-10-01");
		mPage1Position9Data.add("01-10-02");
		mPage1Position9Data.add("01-10-03");
		mPage1Position9Data.add("01-10-04");
		mPage1Position9Data.add("01-10-05");
		mPage1Position9Data.add("01-10-06");
		mOnePageData.put(POSITION_9, mPage1Position9Data);

		/**
		 * 左前翼子板
		 */
		mPage1Position10Data.add("01-03-01");
		mPage1Position10Data.add("01-03-02");
		mPage1Position10Data.add("01-03-03");
		mPage1Position10Data.add("01-03-04");
		mPage1Position10Data.add("01-03-05");
		mPage1Position10Data.add("01-03-06");
		mOnePageData.put(POSITION_10, mPage1Position10Data);

		/**
		 * 左后翼子板
		 */
		mPage1Position11Data.add("01-04-01");
		mPage1Position11Data.add("01-04-02");
		mPage1Position11Data.add("01-04-03");
		mPage1Position11Data.add("01-04-04");
		mPage1Position11Data.add("01-04-05");
		mPage1Position11Data.add("01-04-06");
		mOnePageData.put(POSITION_11, mPage1Position11Data);

		/**
		 * 右前翼子板
		 */
		mPage1Position12Data.add("01-07-01");
		mPage1Position12Data.add("01-07-02");
		mPage1Position12Data.add("01-07-03");
		mPage1Position12Data.add("01-07-04");
		mPage1Position12Data.add("01-07-05");
		mPage1Position12Data.add("01-07-06");
		mOnePageData.put(POSITION_12, mPage1Position12Data);

		/**
		 * 右后翼子板
		 */
		mPage1Position13Data.add("01-08-01");
		mPage1Position13Data.add("01-08-02");
		mPage1Position13Data.add("01-08-03");
		mPage1Position13Data.add("01-08-04");
		mPage1Position13Data.add("01-08-05");
		mPage1Position13Data.add("01-08-06");
		mOnePageData.put(POSITION_13, mPage1Position13Data);

		/**
		 * 左前门
		 */
		mPage1Position14Data.add("01-01-01");
		mPage1Position14Data.add("01-01-02");
		mPage1Position14Data.add("01-01-03");
		mPage1Position14Data.add("01-01-04");
		mPage1Position14Data.add("01-01-05");
		mPage1Position14Data.add("01-01-06");
		mOnePageData.put(POSITION_14, mPage1Position14Data);

		/**
		 * 左后门
		 */
		mPage1Position15Data.add("01-02-01");
		mPage1Position15Data.add("01-02-02");
		mPage1Position15Data.add("01-02-03");
		mPage1Position15Data.add("01-02-04");
		mPage1Position15Data.add("01-02-05");
		mPage1Position15Data.add("01-02-06");
		mOnePageData.put(POSITION_15, mPage1Position15Data);

		/**
		 * 右前门
		 */
		mPage1Position16Data.add("01-05-01");
		mPage1Position16Data.add("01-05-02");
		mPage1Position16Data.add("01-05-03");
		mPage1Position16Data.add("01-05-04");
		mPage1Position16Data.add("01-05-05");
		mPage1Position16Data.add("01-05-06");
		mOnePageData.put(POSITION_16, mPage1Position16Data);

		/**
		 * 右后门
		 */
		mPage1Position17Data.add("01-06-01");
		mPage1Position17Data.add("01-06-02");
		mPage1Position17Data.add("01-06-03");
		mPage1Position17Data.add("01-06-04");
		mPage1Position17Data.add("01-06-05");
		mPage1Position17Data.add("01-06-06");
		mOnePageData.put(POSITION_17, mPage1Position17Data);

		mAllDamageData.put(ONE_PAGE, mOnePageData);

		// ======================初始化page2数据=================================
		/**
		 * 正驾驶台
		 */
		mPage2Position1Data.add("02-08-01");
		mPage2Position1Data.add("02-08-02");
		mPage2Position1Data.add("02-08-03");
		mTwoPageData.put(POSITION_1, mPage2Position1Data);

		/**
		 * 副驾驶台
		 */
		mPage2Position2Data.add("02-09-01");
		mPage2Position2Data.add("02-09-02");
		mPage2Position2Data.add("02-09-03");
		mTwoPageData.put(POSITION_2, mPage2Position2Data);

		/**
		 * 左前门
		 */
		mPage2Position3Data.add("02-01-01");
		mPage2Position3Data.add("02-01-02");
		mPage2Position3Data.add("02-01-03");
		mTwoPageData.put(POSITION_3, mPage2Position3Data);

		/**
		 * 左后门
		 */
		mPage2Position4Data.add("02-11-01");
		mPage2Position4Data.add("02-11-02");
		mPage2Position4Data.add("02-11-03");
		mTwoPageData.put(POSITION_4, mPage2Position4Data);

		/**
		 * 右前门
		 */
		mPage2Position5Data.add("02-03-01");
		mPage2Position5Data.add("02-03-02");
		mPage2Position5Data.add("02-03-03");
		mTwoPageData.put(POSITION_5, mPage2Position5Data);

		/**
		 * 右后门
		 */
		mPage2Position6Data.add("02-05-01");
		mPage2Position6Data.add("02-05-02");
		mPage2Position6Data.add("02-05-03");
		mTwoPageData.put(POSITION_6, mPage2Position6Data);

		/**
		 * 左前座椅
		 */
		mPage2Position7Data.add("02-02-01");
		mPage2Position7Data.add("02-02-02");
		mPage2Position7Data.add("02-02-03");
		mTwoPageData.put(POSITION_7, mPage2Position7Data);

		/**
		 * 右前座椅
		 */
		mPage2Position8Data.add("02-04-01");
		mPage2Position8Data.add("02-04-02");
		mPage2Position8Data.add("02-04-03");
		mTwoPageData.put(POSITION_8, mPage2Position8Data);

		/**
		 * 左后座椅
		 */
		mPage2Position9Data.add("02-07-01");
		mPage2Position9Data.add("02-07-02");
		mPage2Position9Data.add("02-07-03");
		mTwoPageData.put(POSITION_9, mPage2Position9Data);

		/**
		 * 右后座椅
		 */
		mPage2Position10Data.add("02-06-01");
		mPage2Position10Data.add("02-06-02");
		mPage2Position10Data.add("02-06-03");
		mTwoPageData.put(POSITION_10, mPage2Position10Data);

		/**
		 * 后备箱
		 */
		mPage2Position11Data.add("02-13-01");
		mPage2Position11Data.add("02-13-02");
		mPage2Position11Data.add("02-13-03");
		mTwoPageData.put(POSITION_11, mPage2Position11Data);

		/**
		 * 中央扶手
		 */
		mPage2Position12Data.add("02-10-01");
		mPage2Position12Data.add("02-10-02");
		mPage2Position12Data.add("02-10-03");
		mTwoPageData.put(POSITION_12, mPage2Position12Data);

		/**
		 * 顶棚、天窗
		 */
		mPage2Position13Data.add("02-12-01");
		mPage2Position13Data.add("02-12-02");
		mPage2Position13Data.add("02-12-03");
		mTwoPageData.put(POSITION_13, mPage2Position13Data);

		mAllDamageData.put(TWO_PAGE, mTwoPageData);

		// ======================初始化page3数据=================================
		/**
		 * 发动机
		 */
		mPage3Position1Data.add("03-01-01");
		mPage3Position1Data.add("03-01-02");
		mPage3Position1Data.add("03-01-03");
		mPage3Position1Data.add("03-01-04");
		mThreePageData.put(POSITION_1, mPage3Position1Data);

		/**
		 * 变速箱
		 */
		mPage3Position2Data.add("03-02-01");
		mPage3Position2Data.add("03-02-02");
		mPage3Position2Data.add("03-02-03");
		mPage3Position2Data.add("03-02-04");
		mThreePageData.put(POSITION_2, mPage3Position2Data);

		mAllDamageData.put(THREE_PAGE, mThreePageData);

		// ======================初始化page4数据=================================
		mFourPageData1.put(CHECKBOX_1, true);
		mFourPageData1.put(CHECKBOX_2, false);
		mFourPageData1.put(CHECKBOX_3, false);
		mFourPageData1.put(CHECKBOX_4, false);

		mFourPageData2.put(CHECKBOX_5, true);
		mFourPageData2.put(CHECKBOX_6, false);
		mFourPageData2.put(CHECKBOX_7, false);
		mFourPageData2.put(CHECKBOX_8, false);

		mFourPageData3.put(CHECKBOX_9, true);
		mFourPageData3.put(CHECKBOX_10, false);
		mFourPageData3.put(CHECKBOX_11, false);
		mFourPageData3.put(CHECKBOX_12, false);
		mFourPageData3.put(CHECKBOX_13, false);
		mFourPageData3.put(CHECKBOX_14, false);
		mFourPageData3.put(CHECKBOX_15, false);
		mFourPageData3.put(CHECKBOX_16, false);
		mFourPageData3.put(CHECKBOX_17, false);
		mFourPageData3.put(CHECKBOX_18, false);
		mFourPageData3.put(CHECKBOX_19, false);
		mFourPageData3.put(CHECKBOX_20, false);
		mFourPageData3.put(CHECKBOX_21, false);

	}

	/**
	 * 根据当前page位置、page列表的点击位置、滑出列表的点击位置获得滑出列表中选中位置的id值 getSelectId: <br/>
	 * 
	 * @author wang
	 * @param pagePosition
	 * @param pageListPosition
	 * @param contentListPostion
	 * @return
	 * @since JDK 1.6
	 */
	public String getSelectId(int pagePosition, int pageListPosition,
			int contentListPostion)
	{
		Map<Integer, List<String>> pagedata = mAllDamageData.get(pagePosition);
		List<String> list = pagedata.get(pageListPosition);
		String selectid = list.get(contentListPostion);
		System.out.println("select id is  " + selectid);
		return selectid;
	}

	/**
	 * 根据当前page位置和点击的列表位置，获取当前列表位置下所有元素 getElementsByPosition: <br/>
	 * 
	 * @author wang
	 * @param pagePosition
	 * @param pageListPosition
	 * @return
	 * @since JDK 1.6
	 */
	public List<String> getElementsByPosition(int pagePosition,
			int pageListPosition)
	{
		Map<Integer, List<String>> pagedata = mAllDamageData.get(pagePosition);
		List<String> list = pagedata.get(pageListPosition);
		return list;
	}
	
	/**
	 * 改变checkbox选项列表1的原始结果集
	 * modifyData1Checked: <br/>
	 *
	 * @author wang
	 * @param one
	 * @param two
	 * @param three
	 * @param four
	 * @since JDK 1.6
	 */
	public void modifyData1Checked(String key,boolean value){
		mFourPageData1.put(CarDamageSerivce.CHECKBOX_1,
				false);
		mFourPageData1.put(key,
				value);
	}

}
