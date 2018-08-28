import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hongjian.chen on 2017/3/31.
 */
public class Main {

    //启动dubbo注册服务
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
