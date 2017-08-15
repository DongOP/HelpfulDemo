package com.dong.data.processing.util;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.dong.data.processing.data.Person;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2017/8/15 0015.
 */

public class XMLUtils {

    /**
     * 获取 XML文件信息
     *
     * @param xml 需要解析的xml文件
     * @return ArrayList<Person>
     */
    public static ArrayList<Person> getPersons(InputStream xml) throws Exception {
        ArrayList<Person> persons = null;
        Person person = null;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(xml, "UTF-8");
        // 获得事件的类型
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    persons = new ArrayList<Person>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("person".equals(parser.getName())) {
                        person = new Person();
                        // 取出属性值
                        int id = Integer.parseInt(parser.getAttributeValue(0));
                        person.setId(id);
                    } else if ("name".equals(parser.getName())) {
                        String name = parser.nextText();// 获取该节点的内容
                        person.setName(name);
                    } else if ("age".equals(parser.getName())) {
                        int age = Integer.parseInt(parser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("person".equals(parser.getName())) {
                        persons.add(person);
                        person = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return persons;
    }

    //获取文件资源返回输入流对象
    public static ArrayList<Person> readXml(Context context) {
        ArrayList<Person> persons = null;

        try {
            InputStream is = context.getAssets().open("persons.xml");
            persons = getPersons(is);
            if (persons.equals(null)) {
                Toast.makeText(context, "无数据...", Toast.LENGTH_SHORT).show();
            }
            for (Person p1 : persons) {
                Log.d("XmlPull", "XmlPull数据" + p1.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }

    /**
     * 保存 Person对象为 XML文件
     *
     * @param persons Person对象
     * @param out     输出位置
     */
    public static void saveXML(List<Person> persons, OutputStream out) throws Exception {
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(out, "UTF-8");
        serializer.startDocument("UTF-8", true);
        serializer.startTag(null, "persons");
        for (Person p : persons) {
            serializer.startTag(null, "person");
            serializer.attribute(null, "id", p.getId() + "");
            serializer.startTag(null, "name");
            serializer.text(p.getName());
            serializer.endTag(null, "name");
            serializer.startTag(null, "age");
            serializer.text(p.getAge() + "");
            serializer.endTag(null, "age");
            serializer.endTag(null, "person");
        }

        serializer.endTag(null, "persons");
        serializer.endDocument();
        out.flush();
        out.close();
    }

    public static void startSaveXML(Context context, String fileName, List<Person> persons) {
        File xmlFile = new File(context.getFilesDir(), fileName);// data/data/
        Log.e("Export", "dir=" + context.getFilesDir());
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(xmlFile);
            saveXML(persons, fos);
            Toast.makeText(context, "文件写入完毕!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
