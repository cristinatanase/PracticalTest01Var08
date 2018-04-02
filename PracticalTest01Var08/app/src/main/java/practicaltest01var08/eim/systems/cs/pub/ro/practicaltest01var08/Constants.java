package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

/**
 * Created by cristina on 4/2/2018.
 */
public interface Constants {

    final public static String TAG                  = "activitylifecycle";

    final public static String CREDENTIAL_FILE      = "credentials.xml";
    final public static String CREDENTIAL_TAG       = "credential";
    final public static String USERNAME_TAG         = "username";
    final public static String PASSWORD_TAG         = "password";

    final public static String TEXT1   = "t1";
    final public static String TEXT2   = "t2";
    final public static String REMEMBER_ME_CHECKBOX = "rememberMeCheckbox";

    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;
    final public static boolean DEBUG               = true;

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.arithmetic",
            "ro.pub.cs.systems.eim.practicaltest01.geometric"
    };
}
