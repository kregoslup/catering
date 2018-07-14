package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class EntityFinder {

    private final static Logger logger = LoggerFactory.getLogger(EntityFinder.class);

    /**
     * Searches for classes with entity annotation in given package
     *
     * @param packageName Name of package to search for entities in
     * @return List of entity classes in package
     * @throws ClassNotFoundException
     * @throws URISyntaxException
     */
    static List<Class<?>> getEntityClassesFromPackage(String packageName) throws ClassNotFoundException, URISyntaxException {
        List<String> classNames = getClassNamesFromPackage(packageName);
        List<Class<?>> classes = new ArrayList<>();
        for (String className : classNames) {
            Class<?> cls = Class.forName(packageName + "." + className);
            Annotation[] annotations = cls.getAnnotations();

            for (Annotation annotation : annotations) {

                logger.info(cls.getCanonicalName() + ": " + annotation.toString());

                if (annotation instanceof javax.persistence.Entity) {
                    classes.add(cls);
                }
            }
        }

        return classes;
    }

    private static ArrayList<String> getClassNamesFromPackage(String packageName) throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ArrayList<String> names = new ArrayList<>();

        packageName = packageName.replace(".", "/");
        URL packageURL = classLoader.getResource(packageName);

        URI uri = new URI(packageURL.toString());
        File folder = new File(uri.getPath());
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file: files) {
                String name = file.getName();
                logger.info("Found suitable class, name: {}", name);
                name = name.substring(0, name.lastIndexOf('.'));
                names.add(name);
            }
        } else {
            logger.error("No suitable files found");
            throw new IllegalArgumentException("No entities found");
        }

        return names;
    }
}
