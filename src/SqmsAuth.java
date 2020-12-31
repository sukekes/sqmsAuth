import com.hdec.api.vo.Result;
import com.hdec.sso.controller.SsoController;
import com.hdec.user.controller.UserController;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.hdec.mdmclient.PlatformClient;

import java.util.*;

public class SqmsAuth {

    public SqmsAuth() {
        String host = "http://hdecauth.ecidi.com/hdec-mdm";
        String platformCode = "testingbusiness";
        String platformKey = "3ac021a6daab49bebee4fd0e85d2b91e";

        boolean result = PlatformClient.setConnectInfo(host, platformCode, platformKey);

        System.out.println("统一认证初始化结果： " + result);
    }

    // 登录账号
    public void Login(String userName, String pwd){
        Result login = UserController.checkUserValid(0,userName, pwd);
        System.out.println("是否登录成功：" + login.isSuccess());
        System.out.println();
    }

    // 查看账号信息
    public void viewUserInfo(String userId){

        Result userInfo = UserController.viewUserAccount(0,userId);
        System.out.println("结果：" + userInfo);
    }
    //列出域账号所有人员
    public void ListAllUsers(String digName, String pageNo, String pageSize ){
        Map<String, String> map = new HashMap<>();
        map.put("dimName", digName);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);

//        Result allUsers = UserController.userList()
    }

    // 列出有平台登录权限人员
    public void listUsers(String digName, String pageNo, String pageSize){
        Map<String, String> map = new HashMap<>();
        map.put("dimName", digName);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);

        Result users = UserController.userList(0,map);
        System.out.println("查询结果：" + users);
    }

    // 设置用户访问测试平台权限
    public void addPermission(){
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        System.out.println("请输入要开通权限用户的账号信息，多个信息以英文,隔开：");
        Scanner scanner = new Scanner(System.in);
        String userNames = "";
        if (scanner.hasNext()) {
            userNames = scanner.next();
        }
        scanner.close();
        nameValuePairs.add(new BasicNameValuePair("users", userNames));

        Result result = PlatformClient.post(0, "/sso/addUser", nameValuePairs);

        System.out.println("权限设置结果：" + result);
    }

    //删除用户登录权限
    public void delPermission(int hostIndex, String user){
        Result result = SsoController.deleteUser(hostIndex, user);
        System.out.println("权限移除结果：" + result);
    }

    //登录
//    public void Login(){
//        Result result = SsoController.ssoKey(0, "testingbusiness", "2f2692ef3e08251f86a00684d1deaec9");
////        Result userInfo = SsoController.ssoLogin(result.getMessage());
//
//
//    }


    public static void main(String[] args) {
        SqmsAuth sqmsAuth = new SqmsAuth();
        // 查看账号信息
//        sqmsAuth.viewUserInfo("2f2692ef3e08251f86a00684d1deaec9");
        // 登录
//        sqmsAuth.Login("xu_j22", "Xjin2020");
        // 用户列表
//        sqmsAuth.listUsers("", "2", "20");
        //删除权限
//        sqmsAuth.delPermission(0,"xu_j22");
        //添加权限
        sqmsAuth.addPermission();
//        sqmsAuth.Login();
    }
}
