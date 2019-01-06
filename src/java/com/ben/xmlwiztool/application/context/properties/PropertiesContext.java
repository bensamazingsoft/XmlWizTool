
package com.ben.xmlwiztool.application.context.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesContext
{

      private Properties   defaultProp, properties;
      private File	   propFile		     = new File("config.properties");
      private final String defaultPropertiesFileName = "properties/default.properties";


      public PropertiesContext() throws IOException
      {

	    initProperties();
      }


      private void initProperties() throws IOException
      {

	    // initialize default properties

	    defaultProp = new Properties();
	    properties = new Properties();

	    ClassLoader classloader = getClass().getClassLoader();
	    InputStream is = classloader.getResourceAsStream(defaultPropertiesFileName);
	    defaultProp.load(is);

	    // initialize properties with default in case there are new ones
	    properties = new Properties(defaultProp);

	    if (propFile.exists())
	    {

		  properties.load(new FileInputStream(propFile.toString()));

	    }

      }


      public void save() throws FileNotFoundException, IOException
      {

	    properties.store(new FileOutputStream(propFile), null);
      }


      public void reset()
      {

	    properties = new Properties(defaultProp);
      }


      public void reset(String key)
      {

	    properties.setProperty(key, defaultProp.getProperty(key));
      }


      public String getDefault(String key)
      {

	    return defaultProp.getProperty(key);
      }


      public String get(String key)
      {

	    return properties.getProperty(key);
      }


      public void set(String key, String value)
      {

	    properties.setProperty(key, value);
      }


      public void remove(String key)
      {

	    properties.remove(key);
      }


      public Map<String, String> properties()
      {

	    Map<String, String> map = new HashMap<>();
	    properties.forEach((key, value) -> {
		  map.put((String) key, (String) value);
	    });
	    return map;

      }

}
