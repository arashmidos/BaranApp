package loadimage;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import android.graphics.Bitmap;

public class MemoryCache
{

	private static final String TAG = "MemoryCache";
	private Map<String, Bitmap> cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));//Last argument true for LRU ordering
	private long size = 0;
	private long limit = 1000000;

	public MemoryCache()
	{
		setLimit(Runtime.getRuntime().maxMemory() / 4);
	}

	public void setLimit(long new_limit)
	{
		limit = new_limit;
	}

	public Bitmap get(String id)
	{
		try
		{
			if (!cache.containsKey(id))
				return null;
			return cache.get(id);
		}
		catch (NullPointerException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	public void put(String id, Bitmap bitmap)
	{
		try
		{
			if (cache.containsKey(id))
				size -= getSizeInBytes(cache.get(id));
			cache.put(id, bitmap);
			size += getSizeInBytes(bitmap);
			checkSize();
		}
		catch (Throwable th)
		{
			th.printStackTrace();
		}
	}

	private void checkSize()
	{
		if (size > limit)
		{
			Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();  
			while (iter.hasNext())
			{
				Entry<String, Bitmap> entry = iter.next();
				size -= getSizeInBytes(entry.getValue());
				iter.remove();
				if (size <= limit)
					break;
			}
		}
	}

	public void clear()
	{
		try
		{
			cache.clear();
			size = 0;
		}
		catch (NullPointerException ex)
		{
			ex.printStackTrace();
		}
	}

	long getSizeInBytes(Bitmap bitmap)
	{
		if (bitmap == null)
			return 0;
		return bitmap.getRowBytes() * bitmap.getHeight();
	}
}