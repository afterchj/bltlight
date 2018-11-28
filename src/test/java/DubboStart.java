
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


/**
 * Created by hongjian.chen on 2017/4/1.
 */
public class DubboStart {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationProvider.xml");
        context.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
