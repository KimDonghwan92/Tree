import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;


/**
 * Created by DongDong on 2016-02-04.
 */
public class parsing {


    public static void main(String...args) throws JSONException
    {
        List<String> strs;
        List<Node> nodes;
        String fileName = "C:\\Users\\DongDong\\Downloads\\3_Entrez100_ADA.html";
        String str = "";
        strs = new ArrayList<>();
        nodes = new ArrayList<>();
        String[] strArr;
        Stack<String> stack = new Stack<>();

        int levelCnt = 1;
        String srcName = "";

        try {

            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String tmpStr;

            while ((tmpStr = in.readLine()) != null)
            {
                str += tmpStr.replaceAll("<br/>","\n");
                str = str.replaceAll("1-Step","1");
                str = str.replaceAll("2-Step","2");
                str = str.replaceAll("3-Step","3");
                str = str.replaceAll("4-Step","4");
                str = str.replaceAll("5-Step","5");
                str = str.replaceAll("6-Step","6");
                str = str.replaceAll("7-Step","6");
                str = str.replaceAll("8-Step","6");
                str = str.replaceAll("-","");
                str = str.replaceAll("  ",":");
                str = str.replaceAll(" ",":");
                str = str.replace('[','*');
                str = str.replace(']','*');
                str = str.replace('(','*');
                str = str.replace(')','*');
                str = str.replace("*","");

            }
            in.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
            System.exit(1);
        }

        strArr = str.split("\\n");
        for(String each : strArr)
        {
            strs.add(each);
        }

        strs.remove(0);
        strs.remove(0);

        for(int i = 0 ; i < strs.size() ; i++)

        {
            nodes.add(new Node());

            String[] tmp;
            int tmpLevel;
            String tmpName;
            int tmpSize;
            tmp = strs.get(i).split(":");
            tmpLevel = Integer.parseInt(tmp[0]);
            tmpName = tmp[1].toString();
            tmpSize = Integer.parseInt(tmp[2]);

            nodes.get(i).setNode(tmpLevel,tmpName,tmpSize);
        }

        for(int i = 0 ; i < nodes.size() ; i++ )
        {
            if(i < nodes.size()-1)
            {
                if( nodes.get(i).level < nodes.get(i + 1).level)
                {
                    stack.push(nodes.get(i).name);

                    nodes.get(i+1).source = stack.peek(); // 소스 이름 세팅.
                }
                else if(nodes.get(i).level == nodes.get(i + 1).level)
                {
                    nodes.get(i+1).source = stack.peek(); // 소스 이름 세팅.
                }
                else
                {
                    int tmp = nodes.get(i).level - nodes.get(i + 1).level;

                    for(int j = 0 ; j < tmp ; j++)
                    {
                        String trash = stack.pop();
                    }

                    if(!stack.isEmpty())
                    {
                        nodes.get(i+1).source = stack.peek();
                    }

                }
            }
        }


/*
        JSONObject jsonObject = JSONObject.fromObject(JSONSerializer.toJSON(nodes))
        JSONArray cell = new JSONArray();

        HashMap map = new HashMap();

        for(int i = 0 ; i < nodes.size() ; i++)
        {
            map.put("source",nodes.get(i).source);
            map.put("target",nodes.get(i).target);
            cell.put(map);
        }

        jsonObject.put("raw",cell);

*/


        JSONArray cell = new JSONArray();
        JSONObject JsonObj = new JSONObject();

        for(int i = 0 ; i < nodes.size() ; i++)
        {
            JSONObject obj = new JSONObject();

            obj.put("source",nodes.get(i).source);
            obj.put("target",nodes.get(i).target);
            cell.put(obj);
        }

        JsonObj.put("result",cell);

        String data = JsonObj.toString();

        System.out.println(data);












    }
}
