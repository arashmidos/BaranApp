package org.baran.model;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Arashmidos on 9/10/2015.
 */
@Table(name = "Category")
public class Category extends Model
{
    private static final String TAG = "BaranApp";
    @Column(name = "cat_id")
    private long catId;

    @Column(name = "cat_title")
    private String cattitle;

    @Column(name = "cat_code")
    private int catCode;

    @Column(name = "cat_picture")
    private String catPicture;


    public Category(String cattitle)
    {
        this.cattitle = cattitle;
    }

    public Category()
    {

    }

    public Category(long id, String title, int code, String image)
    {
        this.catId = id;
        this.cattitle = title;
        this.catCode = code;
        this.catPicture = image;
    }

    /**
     * @return List of all category names
     */
    public static CharSequence[] allCategoriesNames()
    {
        List<Category> list = (new Select()).from(Category.class).execute();
        CharSequence acharsequence[] = new CharSequence[list.size()];
        int i = 0;
        for (Category c : list)
        {
            acharsequence[i] = c.getCattitle();
            i++;
        }

        return acharsequence;
    }

    /**
     * @return List of all category objects
     */
    public static List<Category> allCategories()
    {
        List<Category> list = (new Select()).from(Category.class).execute();

        return list;
    }

    /**
     * @param cat Category name
     * @return Category Object
     */
    public static Category getCategoryByName(String cat)
    {
        Category returnCat = new Select().from(Category.class).where("cat_title = ?", cat).executeSingle();
        return returnCat;
    }

    /**
     * @param cat_id Category name
     * @return Category Object
     */
    public static Category getCategoryById(long cat_id)
    {
        Category returnCat = new Select().from(Category.class).where("cat_id = ?", cat_id).executeSingle();
        return returnCat;
    }

    /**
     * Rename and update category
     *
     * @param currentName
     * @param newName
     * @return
     */
    public static long rename(String currentName, String newName)
    {
        Category targetCat = getCategoryByName(currentName);
        targetCat.setCattitle(newName);
        long l = 0;
        try
        {
            l = targetCat.save();
        } catch (Exception s)
        {
            Log.d(TAG, "Error in db with code: " + l);
        }
        return l;
    }

    /**
     * Update a category
     *
     * @param id
     * @param catName
     * @param catPicture
     * @param catCode
     * @return
     */
    public static long update(long id, String catName, String catPicture, int catCode)
    {
        Category targetCat = getCategoryById(id);
        targetCat.setCatPicture(catPicture);
        targetCat.setCatCode(catCode);
        targetCat.setCattitle(catName);
        long l = 0;
        try
        {
            l = targetCat.save();
        } catch (Exception s)
        {
            Log.d(TAG, "Error in db with code: " + l);
        }
        return l;
    }

    /**
     * Delete a category by name
     *
     * @param catId
     */
    public static void delete(long catId)
    {
        new Delete().from(Category.class).where("cat_id=?", catId).execute();
    }

    /**
     * Add a new category
     *
     * @param catName
     * @return
     */
    public static boolean add(String catName)
    {
        Category temp = getCategoryByName(catName);
        if (temp == null)
        {
            //is not repetative
            temp = new Category((catName));
            temp.save();


            return true;
        }
        return false;
    }

    public static boolean add(String catName, String catPicture)
    {
        Category temp = getCategoryByName(catName);
        if (temp == null)
        {
            //is not repetative
            temp = new Category((catName));
            temp.setCatPicture(catPicture);
            temp.save();

            return true;
        }
        return false;
    }

    /**
     * @return All items in the current category
     */
    public List<Jobs> items()
    {
        return getMany(Jobs.class, "Category");
    }

    public long getCatId()
    {
        return catId;
    }

    public void setCatId(long catId)
    {
        this.catId = catId;
    }

    public String getCattitle()
    {
        return cattitle;
    }

    public void setCattitle(String cattitle)
    {
        this.cattitle = cattitle;
    }

    public int getCatCode()
    {
        return catCode;
    }

    public void setCatCode(int catCode)
    {
        this.catCode = catCode;
    }

    public String getCatPicture()
    {
        return catPicture;
    }

    public void setCatPicture(String catPicture)
    {
        this.catPicture = catPicture;
    }

    public int compareTo(Object o)
    {
        Category aThat = (Category) o;
        //this optimization is usually worthwhile, and can
        //always be added
        if (this == aThat) return 0;

        return this.getCattitle().compareTo(aThat.getCattitle());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        return cattitle.equals(category.cattitle);

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + cattitle.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Category{" +
                "catId=" + catId +
                ", cattitle='" + cattitle + '\'' +
                ", catCode=" + catCode +
                ", catPicture='" + catPicture + '\'' +
                '}';
    }

    public static String allCategoriesId()
    {
        List<Category> list = (new Select()).from(Category.class).execute();
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < list.size(); i++)
        {
            st.append(list.get(i).getCatId());
            if (i != list.size() - 1)
            {
                st.append(",");
            }
        }

        Log.d("Array", st.toString());
        return st.toString();
    }

    public static String allCategoriesCode()
    {
        List<Category> list = (new Select()).from(Category.class).execute();
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < list.size(); i++)
        {
            st.append(list.get(i).getCatCode());
            if (i != list.size() - 1)
            {
                st.append(",");
            }
        }

        Log.d("Array", st.toString());
        return st.toString();
    }

    /**
     * Add all of the jobs inside the current category to favourite list
     */
    public void addToFavourite()
    {
        List<Jobs> items = items();
        ActiveAndroid.beginTransaction();

        try
        {
            //Bulk Insert
            for (int i = 0; i < items.size(); i++)
            {
                Jobs o = items.get(i);
                Favourite f = new Favourite(o);
                f.save();
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally
        {
            ActiveAndroid.endTransaction();
        }
    }
}
