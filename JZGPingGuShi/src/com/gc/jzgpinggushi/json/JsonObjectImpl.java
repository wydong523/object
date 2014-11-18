/**
 * Project Name:JZGPingGuShi
 * File Name:JsonObjectImpl.java
 * Package Name:com.gc.jzgpinggushi.json
 * Date:2014-9-1上午10:52:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.gc.jzgpinggushi.json;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import com.gc.jzgpinggushi.vo.CarSource;
import com.gc.jzgpinggushi.vo.Make;
import com.gc.jzgpinggushi.vo.MakeList;
import com.gc.jzgpinggushi.vo.Model;
import com.gc.jzgpinggushi.vo.ModelCategory;
import com.gc.jzgpinggushi.vo.ModelList;
import com.gc.jzgpinggushi.vo.NewCar;
import com.gc.jzgpinggushi.vo.NewCarList;
import com.gc.jzgpinggushi.vo.Offer;
import com.gc.jzgpinggushi.vo.OfferList;
import com.gc.jzgpinggushi.vo.OfferSuccess;
import com.gc.jzgpinggushi.vo.OfferSuccessList;
import com.gc.jzgpinggushi.vo.QueryCar;
import com.gc.jzgpinggushi.vo.QueryCarList;
import com.gc.jzgpinggushi.vo.Style;
import com.gc.jzgpinggushi.vo.StyleCategory;
import com.gc.jzgpinggushi.vo.StyleList;
import com.gc.jzgpinggushi.vo.User;
import com.gc.jzgpinggushi.vo.UserApply;
import com.gc.jzgpinggushi.vo.UserApplyList;

/**
 * ClassName:JsonObjectImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:52:01 <br/>
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
					make.setFontColor(Color.rgb(51, 51, 51));
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
						model.setFontColor(Color.rgb(51, 51, 51));
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
						style.setYear(modelObj.getInt("Year"));
						style.setFontColor(Color.rgb(51, 51, 51));
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
	public QueryCarList parserQueryCarList(String jsonData)
	{
		QueryCarList cars = new QueryCarList();
		ArrayList<QueryCar> carList = new ArrayList<QueryCar>();
		QueryCar queryCar = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			int flag = jsonObject.getInt("flag");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray carLists = jsonObject.getJSONArray("CarsourceList");
				System.out.println("carLists.length() is " + carLists.length());
				for (int i = 0; i < carLists.length(); i++)
				{
					JSONObject object = carLists.getJSONObject(i);
					queryCar = new QueryCar();
					queryCar.setCarLevel(object.getInt("carLevel"));
					queryCar.setFullName(object.getString("fullName"));
					queryCar.setImgUrl(object.getString("imgUrl"));
					queryCar.setMarketPrice(object.getString("marketPrice"));
					queryCar.setMileage(object.getInt("mileage"));
					queryCar.setRegistDate(object.getString("registDate"));
					queryCar.setReportUrl(object.getString("reportUrl"));
					queryCar.setCarsourceid(object.getInt("carsourceid"));
					carList.add(queryCar);
				}
			}
			cars.setStatus(status);
			cars.setMsg(msg);
			cars.setFlag(flag);
			cars.setCars(carList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return cars;
	}

	@Override
	public String generateJson(Serializable object)
	{
		return null;
	}

	private int getresult(JSONObject jsonObject) throws JSONException
	{
		return jsonObject.getInt("status");
	}

	@Override
	public OfferList parserOfferList(String jsonData)
	{
		OfferList offers = new OfferList();
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		Offer offer = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray offerLists = jsonObject.getJSONArray("CarsourceList");
				System.out.println("carLists.length() is "
						+ offerLists.length());
				for (int i = 0; i < offerLists.length(); i++)
				{
					JSONObject object = offerLists.getJSONObject(i);
					offer = new Offer();
					offer.setCarLevel(String.valueOf(object.getInt("carLevel")));
					offer.setFullName(object.getString("fullName"));
					offer.setImgUrl(object.getString("imgUrl"));
					offer.setMarketPrice(object.getString("marketPrice"));
					offer.setMileage(String.valueOf(object.getInt("mileage")));
					offer.setRegistDate(object.getString("registDate"));
					offer.setMyBidPrice(object.getString("myBidPrice"));
					offer.setCarsourceid(object.getInt("carsourceid"));
					offerList.add(offer);
				}
			}
			offers.setStatus(status);
			offers.setMsg(msg);
			offers.setOfferCars(offerList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public OfferSuccessList parserOfferSuccessList(String jsonData)
	{
		OfferSuccessList offerSuccesss = new OfferSuccessList();
		ArrayList<OfferSuccess> offerSuccessList = new ArrayList<OfferSuccess>();
		OfferSuccess offerSuccess = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray offerSuccessLists = jsonObject
						.getJSONArray("CarsourceList");
				System.out.println("offerSuccessLists.length() is "
						+ offerSuccessLists.length());
				for (int i = 0; i < offerSuccessLists.length(); i++)
				{
					JSONObject object = offerSuccessLists.getJSONObject(i);
					offerSuccess = new OfferSuccess();
					offerSuccess.setCarLevel(String.valueOf(object
							.getInt("carLevel")));
					offerSuccess.setFullName(object.getString("fullName"));
					offerSuccess.setImgUrl(object.getString("imgUrl"));
					offerSuccess.setMileage(String.valueOf(object
							.getInt("mileage")));
					offerSuccess.setRegistDate(object.getString("registDate"));
					offerSuccess
							.setMarketPrice(object.getString("marketPrice"));
					offerSuccess.setMyBidPrice(object.getString("myBidPrice"));
					offerSuccess.setSellerPhoneNum(object
							.getString("sellerPhoneNum"));
					offerSuccessList.add(offerSuccess);
				}
			}
			offerSuccesss.setStatus(status);
			offerSuccesss.setMsg(msg);
			offerSuccesss.setOfferSuccesses(offerSuccessList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return offerSuccesss;
	}

	@Override
	public NewCarList parserNewCarList(String jsonData)
	{
		NewCarList newCars = new NewCarList();
		ArrayList<NewCar> newCarList = new ArrayList<NewCar>();
		NewCar newCar = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");

			// 成功
			if (SUCCESS == status)
			{
				JSONArray newCarLists = jsonObject
						.getJSONArray("CarsourceList");
				System.out.println("newCarLists.length() is "
						+ newCarLists.length());
				for (int i = 0; i < newCarLists.length(); i++)
				{
					JSONObject object = newCarLists.getJSONObject(i);
					newCar = new NewCar();
					newCar.setCarLevel(String.valueOf(object.getInt("carLevel")));
					newCar.setFullName(object.getString("fullName"));
					newCar.setImgUrl(object.getString("imgUrl"));
					newCar.setMileage(String.valueOf(object.getInt("mileage")));
					newCar.setRegistDate(object.getString("registDate"));
					newCar.setMarketPrice(object.getString("marketPrice"));
					newCar.setReportUrl(object.getString("reportUrl"));
					newCar.setCarsourceid(Integer.valueOf(object
							.getString("carsourceid")));

					newCarList.add(newCar);
				}
			}
			newCars.setStatus(status);
			newCars.setMsg(msg);
			newCars.setNewCars(newCarList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return newCars;
	}

	public User parserUser(String result)
	{
		User user = new User();
		try
		{
			jsonObject = new JSONObject(result);
			int status = getresult(jsonObject);
			if (SUCCESS == status)
			{
				int pgsid = jsonObject.getInt("pgsid");
				user.setPgsid(pgsid);
			}
			String msg = jsonObject.getString("msg");
			user.setStatus(status);
			user.setMsg(msg);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return user;
	}

	public CarSource parserCarSource(String result)
	{
		CarSource carSource = new CarSource();
		try
		{
			jsonObject = new JSONObject(result);
			int status = getresult(jsonObject);
			if (SUCCESS == status)
			{
				int carLevel = jsonObject.getInt("carLevel");
				String carLevelUrl = jsonObject.getString("carLevelUrl");
				String imgUrl = jsonObject.getString("imgUrl");
				String fullName = jsonObject.getString("fullName");
				String registDate = jsonObject.getString("registDate");
				String insuranceExpireDate = jsonObject
						.getString("insuranceExpireDate");
				int mileage = jsonObject.getInt("mileage");
				String inspectionExpireDate = jsonObject
						.getString("inspectionExpireDate");
				String bodyColor = jsonObject.getString("bodyColor");
				String priceRange = jsonObject.getString("priceRange");
				int carsourceid = jsonObject.getInt("carsourceid");

				carSource.setCarLevel(carLevel);
				carSource.setImgUrl(imgUrl);
				carSource.setFullName(fullName);
				carSource.setRegistDate(registDate);
				carSource.setInsuranceExpireDate(insuranceExpireDate);
				carSource.setMileage(mileage);
				carSource.setInspectionExpireDate(inspectionExpireDate);
				carSource.setBodyColor(bodyColor);
				carSource.setPriceRange(priceRange);
				carSource.setCarsourceid(carsourceid);
				carSource.setCarLevelUrl(carLevelUrl);
			}
			String msg = jsonObject.getString("msg");
			carSource.setStatus(status);
			carSource.setMsg(msg);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return carSource;
	}

	public boolean parserUploadResult(String result)
	{
		try
		{
			jsonObject = new JSONObject(result);
			int status = getresult(jsonObject);
			if (SUCCESS == status)
			{
				return true;
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public UserApplyList parserUserApplyList(String result)
	{
		UserApplyList userApplyList = new UserApplyList();
		ArrayList<UserApply> userApplys = new ArrayList<UserApply>();
		UserApply userApply = null;
		try
		{
			jsonObject = new JSONObject(result);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray userApplyLists = jsonObject
						.getJSONArray("CarsourceList");
				System.out.println("userApplyLists.length() is "
						+ userApplyLists.length());
				for (int i = 0; i < userApplyLists.length(); i++)
				{
					JSONObject object = userApplyLists.getJSONObject(i);
					userApply = new UserApply();
					userApply.setCarLevel(String.valueOf(object
							.getInt("carLevel")));
					userApply.setFullName(object.getString("fullName"));
					userApply.setImgUrl(object.getString("imgUrl"));
					userApply.setMileage(String.valueOf(object
							.getInt("mileage")));
					userApply.setRegistDate(object.getString("registDate"));
					userApply.setMarketPrice(object.getString("marketPrice"));
					userApply.setSellerPhoneNum(object
							.getString("sellerPhoneNum"));
					userApplys.add(userApply);
				}
			}
			userApplyList.setStatus(status);
			userApplyList.setMsg(msg);
			userApplyList.setUserApplys(userApplys);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return userApplyList;
	}

}
