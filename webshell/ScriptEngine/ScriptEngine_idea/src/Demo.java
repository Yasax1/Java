import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 测试脚本引擎Rhino执行javascript代码
 */
public class Demo {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
        //https://blog.csdn.net/liuxiangke0210/article/details/77620325
        //获取脚本引擎对象
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");//或"js"

      /*  //定义变量，存储到引擎上下方中，java和javascript都可获取
        engine.put("msg", "test is a good man!");
        String str = "var user = {name:'test',age:18,shcools:['清华','北大']};";
        str += "print(user.name);";

        //执行脚本
        engine.eval(str);
        engine.eval("msg = 'beida is a godd school';");//javascript修改变量值

        System.out.println(engine.get("msg"));
        System.out.println("###############################");

        //定义函数
        engine.eval("function add(a,b){var sum = a + b; return sum;}");
        //取得调用接口
        Invocable jsInvoke = (Invocable) engine;
        //执行脚本中定义的方法
        Object result1 = jsInvoke.invokeFunction("add", new Object[]{13,20});
        System.out.println(result1);

        //导入其它java包，使用其它包中的java类
        //注：本例所用的环境java8已不支持Rhino，已改为Nashorn。添加脚本：load(\"nashorn:mozilla_compat.js\"); 运行
        String jsCode = "load(\"nashorn:mozilla_compat.js\"); importPackage(java.util); var list = Arrays.asList([\"北大\",\"清华\",\"复旦\"]);";

        engine.eval(jsCode);

        List<String> list2 = (List<String>) engine.get("list");
        for (String temp : list2) {
            System.out.println(temp);
        }*/
        String Calc="var a=java.lang.Runtime.getRuntime().exec('calc');";
        engine.eval(Calc);
        //执行js文件(a.js放到项目src下)
      /*  URL url = Demo.class.getClassLoader().getResource("a.js");
        FileReader fr = new FileReader("g:/java/test/a.js");//(url.getPath());
        engine.eval(fr);
        fr.close();*/
    }
}