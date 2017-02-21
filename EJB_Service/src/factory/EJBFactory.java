package factory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by Seven on 2017/2/20.
 */
public class EJBFactory {

    public static Object getEJB(String jndipath){
        try {
            final Hashtable<String,Object> jndiProps=new Hashtable<String, Object>();
            jndiProps.put("jboss.naming.clien.ejb.context",true);

            jndiProps.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");

            final Context context;
            context = new InitialContext(jndiProps);
            return context.lookup(jndipath);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
