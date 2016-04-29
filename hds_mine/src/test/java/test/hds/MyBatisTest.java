//package test.hds;
//
//
//import java.util.Date;
//import java.util.UUID;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.hds.api.excpetion.HDSServiceException;
//import com.hds.api.sys.service.UserService;
//import com.hds.api.util.R;
//import com.hds.api.vo.User;
//import com.hds.api.util.R;
//import com.hds.api.util.DateUtils;
//
//public class MyBatisTest {
//
//    private UserService userService;
//    
//    /**
//     * 这个before方法在所有的测试方法之前执行，并且只执行一次
//     * 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
//     * 比如在before方法里面初始化ApplicationContext和userService
//     */
//    @Before
//    public void before(){
//        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
//        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring/spring-context.xml"});
//        //从Spring容器中根据bean的id取出我们要使用的userService对象
//        userService = (UserService) ac.getBean("UserService");
//    }
//    
//    @Test
//    public void testAddUser() throws HDSServiceException{
//        //ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
//        //UserServiceI userService = (UserServiceI) ac.getBean("userService");
//        User user = new User();
//        user.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
//        user.setUser_name("kkk");
//        user.setUser_type(R.CIFConstant.SUPER_ADMIN);
//        user.setIsnew(R.CIFConstant.ISNEW);
//        user.setCreate_time(new Date());
//        user.setUpdate_time(new Date());
//        userService.addUser(user);
//        
//        
//        User user1 = new User();
//        user1.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
//        user1.setUser_name("ccc");
//        user1.setUser_type(R.CIFConstant.SUPER_ADMIN);
//        user1.setIsnew(R.CIFConstant.ISNEW);
//        user1.setCreate_time(new Date());
//        user1.setUpdate_time(new Date());
//        userService.addUser(user1);
//        
//    }
//    
//}