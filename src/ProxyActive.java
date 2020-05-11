import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyActive {
    public static void main(String[] args) {
        //获取被代理者的对象
        UserDaoImpl u=new UserDaoImpl();

        //创建处理器
        ProxyInvoke proxy=new ProxyInvoke(u);

        UserDAO ud = (UserDAO) Proxy.newProxyInstance(u.getClass().getClassLoader(), u.getClass().getInterfaces(), proxy);
        ud.insert();
        ud.delete();
        ud.select();
    }
}
interface UserDAO
{
    public void insert();
    public void delete();
    public void update();
    public void select();
}
class UserDaoImpl implements UserDAO
{

    @Override
    public void insert() {
        System.out.println("执行insert方法");
    }

    @Override
    public void delete() {
        System.out.println("执行delete方法");
    }

    @Override
    public void update() {
        System.out.println("执行update方法");
    }
    @Override
    public void select() {
        System.out.println("执行select方法");
    }
}
class ProxyInvoke implements InvocationHandler
{
    private Object obj=null;
    public ProxyInvoke(Object obj) {
        this.obj=obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行开始");
        Object invoke = method.invoke(obj, null);
        System.out.println("代理执行结束");
        return invoke;
    }
}