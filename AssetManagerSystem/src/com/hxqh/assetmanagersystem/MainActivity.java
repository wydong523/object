package com.hxqh.assetmanagersystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.hxqh.assetmanagersystem.R.style;
import com.hxqh.assetmanagersystem.core.RelativeViewAttrManager;
import com.hxqh.assetmanagersystem.core.ViewManager;
import com.hxqh.assetmanagersystem.core.WidgetProducer;
import com.hxqh.assetmanagersystem.core.XmlManager;
import com.hxqh.assetmanagersystem.core.XmlTags;
import com.hxqh.assetmanagersystem.vo.EditTextAttr;
import com.hxqh.assetmanagersystem.vo.RelativeLayoutAttr;
import com.hxqh.assetmanagersystem.vo.TextViewAttr;

import android.R.integer;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements XmlTags
{
	XmlPullParser xmlPullParser;

	LayoutParams layoutParams;

	// private static final LinkedHashMap<String, Integer> styles = new
	// LinkedHashMap<String, Integer>();
	//
	// static
	// {
	// styles.put("0x7f070005", R.style.RelativeLayout);
	// styles.put("0x7f070003", R.style.editView);
	// styles.put("0x7f070002", R.style.wyd);
	// }

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

//		RelativeLayout.LayoutParams params;
//		RelativeLayout relativeLayout = new RelativeLayout(this, null,
//				R.style.RelativeLayout);
//		// params = new RelativeLayout.LayoutParams(
//		// RelativeLayout.LayoutParams.MATCH_PARENT,
//		// RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//		TextView textView = new TextView(this);
//		textView.setId(R.id.text);
//		textView.setText("汪渝栋");
//		textView.setTextSize(18);
//		textView.setTypeface(Typeface.DEFAULT, 0x7f070002);// 默认参数写死
//		relativeLayout.addView(textView);
//
//		EditText editText = new EditText(this);
//		editText.setBackgroundResource(R.drawable.edit_bg2);
//		// editText.setTypeface(Typeface.MONOSPACE, 0x7f070003);
//		editText.setHint("请输入");
//		editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
//		editText.setSingleLine(true);
//		editText.setId(R.id.edittext);
//		relativeLayout.addView(editText);
//
//		params = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//		params.addRule(RelativeLayout.ALIGN_BASELINE);
//		params.addRule(RelativeLayout.ALIGN_BOTTOM);
//		params.addRule(RelativeLayout.CENTER_VERTICAL);
//		params.setMargins(0, 4, 7, 0);
//		textView.setLayoutParams(params);
//
//		params = new RelativeLayout.LayoutParams(
//				RelativeLayout.LayoutParams.WRAP_CONTENT,
//				RelativeLayout.LayoutParams.WRAP_CONTENT);
//		params.addRule(RelativeLayout.RIGHT_OF, R.id.text);
//		editText.setLayoutParams(params);

		setContentView(R.layout.activity_main);
		ViewManager viewManager = new ViewManager(this, "NewFile.xml");

		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pr_detail_layout);
		// linearLayout.addView(relativeLayout);
		viewManager.createLinearLayout(linearLayout);

		// ============================================================
		// XmlManager xmlManager = new XmlManager();
		//
		// ArrayList<LinkedHashMap<String, String>> elements = xmlManager
		// .parserXmlByPath(this, "NewFile.xml");
		//
		// RelativeViewAttrManager relativeViewAttrManager = new
		// RelativeViewAttrManager();
		// boolean isAssembly = relativeViewAttrManager
		// .assemblyAttributes(elements);
		// if (isAssembly)
		// {
		// WidgetProducer producer = WidgetProducer.getInstance();
		// if (RelativeViewAttrManager.layoutAttrs.size() <= 0
		// || RelativeViewAttrManager.editTextAttrs.size() <= 0
		// || RelativeViewAttrManager.textViewAttrs.size() <= 0)
		// return;
		//
		// for (Serializable layoutAttr : RelativeViewAttrManager.layoutAttrs)
		// {
		// RelativeLayoutAttr attr = (RelativeLayoutAttr) layoutAttr;
		// RelativeLayout layout = producer.createRelativeLayout(this,
		// attr);
		// }
		//
		// for (Serializable editTextAttr :
		// RelativeViewAttrManager.editTextAttrs)
		// {
		// EditTextAttr attr = (EditTextAttr) editTextAttr;
		// EditText edit = producer.createEditText(this, attr);
		// }
		//
		// for (Serializable textViewAttr :
		// RelativeViewAttrManager.textViewAttrs)
		// {
		// TextViewAttr attr = (TextViewAttr) textViewAttr;
		// TextView view = producer.createTextView(this, attr);
		// }
		// }

		// =====================================================
		// for (LinkedHashMap<String, String> element : elements)
		// {
		// String type = element.get("type");
		// com.hxqh.assetmanagersystem.vo.RelativeLayoutAttr relativelayout =
		// null;
		// com.hxqh.assetmanagersystem.vo.EditTextAttr edittext = null;
		// com.hxqh.assetmanagersystem.vo.TextViewAttr textview = null;
		// if (type.equals("RelativeLayout"))
		// {
		// relativelayout = new
		// com.hxqh.assetmanagersystem.vo.RelativeLayoutAttr();
		// relativelayout.setStyle(styles.get(element.get("style")));
		// relativelayout.setId(Integer.valueOf(element.get("id")));
		// } else if (type.equals("EditText"))
		// {
		// edittext = new com.hxqh.assetmanagersystem.vo.EditTextAttr();
		// edittext.setId(Integer.valueOf(element.get("id")));
		// edittext.setHint(element.get("hint"));
		// edittext.setStyle(styles.get(element.get("style")));
		// } else if (type.equals("TextView"))
		// {
		// textview = new com.hxqh.assetmanagersystem.vo.TextViewAttr();
		// textview.setId(Integer.valueOf(element.get("id")));
		// textview.setParentId(Integer.valueOf(element.get("parentId")));
		// textview.setStyle(styles.get(element.get("style")));
		// textview.setText(element.get("text"));
		// }
		// }

	}
	// private void parserStartTag(String tagName)
	// {
	// if (LINEARLAYOUT.equalsIgnoreCase(tagName))
	// {
	// int count = xmlPullParser.getAttributeCount();
	// for (int i = 0; i < count; i++)
	// {
	// String attributeName = xmlPullParser.getAttributeName(i);
	// System.out.println("attributeName is " + attributeName);
	// String attributeValue = xmlPullParser.getAttributeValue(
	// xmlPullParser.getNamespace(), attributeName);
	// System.out.println("attributeValue is " + attributeValue);
	// }
	//
	// }
	//
	// =========================================
	// try
	// {
	// Method method = editText.getClass().getMethod(
	// "setBackgroundResource", int.class);
	// method.invoke(editText, R.drawable.edit_bg2);
	// } catch (NoSuchMethodException e1)
	// {
	//
	// e1.printStackTrace();
	//
	// } catch (IllegalAccessException e)
	// {
	//
	// e.printStackTrace();
	//
	// } catch (IllegalArgumentException e)
	// {
	//
	// e.printStackTrace();
	//
	// } catch (InvocationTargetException e)
	// {
	//
	// e.printStackTrace();
	//
	// }
}
