package driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class PageObjectFactory {

    // TODO: map instance to List<Object> instead of Strings to List<Object>
    private static Map<String, List<Object>> classToInstanceMap = new HashMap<>();

    /**
     * Creates a new instance of the class passed as parameter and maps that instance to the
     * class that created it, and returns the same instance upon the next invocations
     *
     * @param classType the Class from which the instance is going to be created
     * @param <T>       classType
     * @return a new instance of classType
     */
    protected static <T> T getInstance(Class classType) {
        T page = null;

        try {
            /*
             * new Throwable().getStackTrace()[2].getClassName() will always resolve to the TestClass which is trying
             * to create the new instance
             */
            String testClass = new Throwable().getStackTrace()[2].getClassName();

            List<Object> instances = classToInstanceMap.get(testClass);
            if (instances == null) {
                instances = new ArrayList<>();
            }

            for (Object instance : instances) {
                if (classType.isAssignableFrom(instance.getClass())) {
                    page = (T) instance;
                }
            }

            if (page == null) {
                page = (T) classType.newInstance();
                instances.add(page);
                classToInstanceMap.put(testClass, instances);
            }

        } catch (Exception e) {
            page = null;
        }
        return page;

    }
}