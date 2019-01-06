
package com.ben.xmlwiztool.application.context.recent.files;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.ben.xmlwiztool.application.context.AppContext;

public class RecentFilesManager extends CircularFifoQueue<String>
{

      /**
       * 
       */
      private static final long		serialVersionUID = -414543987547750749L;
      private static final int		SIZE		 = Integer.valueOf(AppContext.getInstance().getProperties().get("maxRecentfiles"));
      private static final String	REGEX		 = AppContext.getInstance().getProperties().get("recentFilesPropRegex");
      private static RecentFilesManager	instance;


      private RecentFilesManager()
      {

	    super(SIZE);

	    Map<String, String> prop = filePathProperties();

	    if (!prop.isEmpty())
	    {

		  for (int idx = prop.size(); idx > 0; idx--)
		  {
			add(new File(prop.get("file_" + idx)));
		  }
	    }

      }


      public void add(File file)
      {

	    if (file.exists() && !file.isDirectory())
	    {
		  this.add(file.toString());
	    }
      }


      public File[] getFiles()
      {

	    File[] files = new File[this.size()];

	    int a = 0;
	    for (int i = this.size() - 1; i >= 0; i--)
	    {

		  String path = this.get(i);

		  File file = new File(path);

		  if (file.exists())
		  {
			files[a++] = file;
		  }

	    }

	    return files;

      }


      public void save()
      {

	    Map<String, String> prop = filePathProperties();

	    prop.forEach((key, value) -> AppContext.getInstance().getProperties().remove(key));

	    if (!this.isEmpty())
	    {

		  int idx = this.size();
		  Iterator<String> it = this.iterator();
		  while (it.hasNext())
		  {
			String fileProp = it.next();
			AppContext.getInstance().getProperties().set("file_" + idx--, fileProp);
		  }

	    }

      }


      private Map<String, String> filePathProperties()
      {

	    return AppContext.getInstance().getProperties().properties().entrySet().stream()
			.filter(entry -> isFileKey((String) entry.getKey()))
			.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
      }


      public static boolean isFileKey(String str)
      {

	    return str.matches(REGEX);
      }


      public static RecentFilesManager getInstance()
      {

	    if (instance == null)
	    {
		  instance = new RecentFilesManager();
	    }

	    return instance;
      }

}
