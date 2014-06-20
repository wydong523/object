/**
 * Project Name:JingZhenGu
 * File Name:JsonObjectImpl.java
 * Package Name:com.gc.jingzhengu.json
 * Date:2014-6-12下午1:39:51
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jingzhengu.json;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import com.gc.jingzhengu.vo.City;
import com.gc.jingzhengu.vo.CityList;
import com.gc.jingzhengu.vo.Make;
import com.gc.jingzhengu.vo.MakeList;
import com.gc.jingzhengu.vo.Model;
import com.gc.jingzhengu.vo.ModelCategory;
import com.gc.jingzhengu.vo.ModelList;
import com.gc.jingzhengu.vo.PriceRange;
import com.gc.jingzhengu.vo.Province;
import com.gc.jingzhengu.vo.ProvinceList;
import com.gc.jingzhengu.vo.Style;
import com.gc.jingzhengu.vo.StyleCategory;
import com.gc.jingzhengu.vo.StyleList;
import com.google.gson.Gson;

/**
 * ClassName:JsonObjectImpl <br/>
 * Function: json数据操作类. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-12 下午1:39:51 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JsonObjectImpl implements JsonObject
{
	private JSONObject jsonObject;

	private int SUCCESS = 100;

	@Override
	public MakeList parserMakeList(String jsonData)
	{
		MakeList makes = new MakeList();
		ArrayList<Make> makeList = new ArrayList<Make>();
		Make make = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray makelists = jsonObject.getJSONArray("MakeList");
				System.out.println("makelists.length() is "
						+ makelists.length());
				for (int i = 0; i < makelists.length(); i++)
				{
					make = new Make();
					make.setMakeId(makelists.getJSONObject(i).getInt("MakeId"));
					make.setMakeLogo(makelists.getJSONObject(i).getString(
							"MakeLogo"));
					make.setGroupName(makelists.getJSONObject(i).getString(
							"GroupName"));
					make.setMakeName(makelists.getJSONObject(i).getString(
							"MakeName"));
					make.setFontColor(Color.BLACK);
					makeList.add(make);
				}
			}
			makes.setStatus(status);
			makes.setMsg(msg);
			makes.setMakes(makeList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return makes;
	}

	@Override
	public ModelList parserModelList(String jsonData)
	{
		ModelList models = new ModelList();
		ArrayList<ModelCategory> modelCategoryList = new ArrayList<ModelCategory>();
		ModelCategory modelCategory = null;
		Model model = null;
		try
		{
			JSONObject jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			if (SUCCESS == status)
			{
				JSONArray manufacturerList = jsonObject
						.getJSONArray("ManufacturerList");
				System.out.println("manufacturerList.length() is "
						+ manufacturerList.length());
				for (int i = 0; i < manufacturerList.length(); i++)
				{
					JSONObject object = manufacturerList.getJSONObject(i);
					modelCategory = new ModelCategory(
							object.getString("ManufacturerName"));
					JSONArray modellists = object.getJSONArray("ModelList");
					for (int j = 0; j < modellists.length(); j++)
					{
						JSONObject modelObj = (JSONObject) modellists
								.getJSONObject(j);
						model = new Model();
						model.setId(modelObj.getInt("Id"));
						model.setName(modelObj.getString("Name"));
						model.setFontColor(Color.BLACK);
						modelCategory.addItem(model);
					}
					modelCategoryList.add(modelCategory);
				}

				models.setModels(modelCategoryList);
			}
			models.setStatus(status);
			models.setMsg(msg);
		} catch (JSONException e)
		{

			e.printStackTrace();

		}
		return models;
	}

	public StyleList parserStyleList(String jsonData)
	{
		StyleList styles = new StyleList();
		ArrayList<StyleCategory> styleCategoryList = new ArrayList<StyleCategory>();
		StyleCategory styleCategory = null;
		Style style = null;
		try
		{
			JSONObject jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			if (SUCCESS == status)
			{
				JSONArray yearGroupList = jsonObject
						.getJSONArray("YearGroupList");
				System.out.println("yearGroupList.length() is "
						+ yearGroupList.length());
				for (int i = 0; i < yearGroupList.length(); i++)
				{
					JSONObject object = yearGroupList.getJSONObject(i);
					styleCategory = new StyleCategory(object.getString("Year"));
					JSONArray modellists = object.getJSONArray("StyleList");
					for (int j = 0; j < modellists.length(); j++)
					{
						JSONObject modelObj = (JSONObject) modellists
								.getJSONObject(j);
						style = new Style();
						style.setId(modelObj.getInt("Id"));
						style.setName(modelObj.getString("Name"));
						style.setFontColor(Color.BLACK);
						styleCategory.addItem(style);
					}
					styleCategoryList.add(styleCategory);
				}

				styles.setCarStyles(styleCategoryList);
			}
			styles.setStatus(status);
			styles.setMsg(msg);
		} catch (JSONException e)
		{

			e.printStackTrace();

		}
		return styles;
	}

	@Override
	public ProvinceList parserProvinceList(String jsonData)
	{

		ProvinceList provinces = new ProvinceList();
		ArrayList<Province> provinceList = new ArrayList<Province>();
		Province province = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray provincelists = jsonObject.getJSONArray("content");
				System.out.println("provincelists.length() is "
						+ provincelists.length());
				for (int i = 0; i < provincelists.length(); i++)
				{
					JSONObject object = provincelists.getJSONObject(i);
					province = new Province();
					province.setId(object.getInt("Id"));
					province.setName(object.getString("Name"));
					provinceList.add(province);
				}
			}
			provinces.setStatus(status);
			provinces.setMsg(msg);
			provinces.setProvinces(provinceList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return provinces;
	}

	@Override
	public CityList parserCityList(String jsonData)
	{

		CityList citys = new CityList();
		ArrayList<City> cityList = new ArrayList<City>();
		City city = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray citylists = jsonObject.getJSONArray("content");
				System.out.println("provincelists.length() is "
						+ citylists.length());
				for (int i = 0; i < citylists.length(); i++)
				{
					JSONObject object = citylists.getJSONObject(i);
					city = new City();
					city.setId(object.getInt("Id"));
					city.setName(object.getString("Name"));
					cityList.add(city);
				}
			}
			citys.setStatus(status);
			citys.setMsg(msg);
			citys.setCitys(cityList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return citys;
	}

	@Override
	public PriceRange parserPriceRange(String jsonData)
	{
		PriceRange priceRange = new PriceRange();
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			if (SUCCESS == status)
			{
				int appraiseReportId = jsonObject.getInt("AppraiseReportId");
				String price = jsonObject.getString("PriceRange");
				priceRange.setAppraiseReportId(appraiseReportId);
				priceRange.setPriceRange(price);
			}
			priceRange.setMsg(msg);
			priceRange.setStatus(status);

		} catch (JSONException e)
		{

			e.printStackTrace();

		}
		return priceRange;
	}

	private int getresult(JSONObject jsonObject) throws JSONException
	{
		return jsonObject.getInt("status");
	}

	@Override
	public String generateJson(Serializable object)
	{
		Gson gson = new Gson();
		return gson.toJson(object);
	}

}
