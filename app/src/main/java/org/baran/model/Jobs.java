package org.baran.model;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Arashmidos on 9/10/2015.
 */
@Table(name = "Jobs")
public class Jobs extends Model
{
    @Column(name = "jobs_id")
    private long jobsId;

    @Column(name = "jobs_name")
    private String jobsName;

    @Column(name = "jobs_address")
    private String jobsAddress;

    @Column(name = "jobs_tel1")
    private String jobsTel1;

    @Column(name = "jobs_tel2")
    private String jobsTel2;

    @Column(name = "jobs_web")
    private String jobsWeb;

    @Column(name = "jobs_email")
    private String jobsEmail;

    @Column(name = "jobs_picture")
    private String jobPicture;

    @Column(name = "latitude")
    private long latitude;

    @Column(name = "longitude")
    private long longitude;

    @Column(name = "job_code")
    private int jobCode;

    @Column(name = "Category", onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Category category;

    public Jobs(String jobsName, Category category)
    {
        this.jobsName = jobsName;
        this.category = category;
    }

    public Jobs()
    {

    }

    public Jobs(long jobsId, String jobsName, String jobsAddress, String jobsTel1, String jobsTel2, String jobsWeb, String jobsEmail, String jobPicture, long latitude, long longitude, int jobCode)
    {
        this.jobsId = jobsId;
        this.jobsName = jobsName;
        this.jobsAddress = jobsAddress;
        this.jobsTel1 = jobsTel1;
        this.jobsTel2 = jobsTel2;
        this.jobsWeb = jobsWeb;
        this.jobsEmail = jobsEmail;
        this.jobPicture = jobPicture;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jobCode = jobCode;
    }

    /**
     * @return List of all category objects
     */
    public static List<Jobs> allJobs()
    {
        List<Jobs> list = (new Select()).from(Jobs.class).execute();

        return list;
    }

    /**
     * @return List of all category names
     */
    public static CharSequence[] allJobsNames()
    {
        List<Jobs> list = (new Select()).from(Jobs.class).execute();
        CharSequence acharsequence[] = new CharSequence[list.size()];
        int i = 0;
        for (Jobs j : list)
        {
            acharsequence[i] = j.getJobsName();
            i++;
        }

        return acharsequence;
    }

    /**
     * Edit job with new name and new picture
     *
     *  @param id
     * @param name
     * @param image    @return Job object
     * @param id
     * @param image
     * @param webAddress
     * @param email
     * @param address
     * @param name
     * @param longitude
     * @param latitude
     * @param code
     * @param tel1
     * @param tel2
     * */
    public static Jobs edit(long id, String image, String webAddress, String email, String address,
                            String name, long longitude, long latitude, int code, String tel1, String tel2)
    {
        Jobs j = getJobsById(id);
        j.setJobPicture(image);
        j.setJobsWeb(webAddress);
        j.setJobsEmail(email);
        j.setJobsAddress(address);
        j.setJobsName(name);
        j.setLongitude(longitude);
        j.setLatitude(latitude);
        j.setJobCode(code);
        j.setJobsTel1(tel1);
        j.setJobsTel2(tel2);
        j.save();
        return j;
    }

    public static String allJobsId()
    {
        List<Jobs> list = (new Select()).from(Jobs.class).execute();
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < list.size(); i++)
        {
            st.append(list.get(i).getJobsId());
            if (i != list.size() - 1)
            {
                st.append(",");
            }
        }

        Log.d("Array", st.toString());
        return st.toString();
    }

    public static String allJobsCode()
    {
        List<Jobs> list = (new Select()).from(Jobs.class).execute();
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < list.size(); i++)
        {
            st.append(list.get(i).getJobCode());
            if (i != list.size() - 1)
            {
                st.append(",");
            }
        }

        Log.d("Array", st.toString());
        return st.toString();
    }

    /**
     * Delete a category by name
     *
     * @param jobsId
     */
    public static void delete(long jobsId)
    {
        new Delete().from(Jobs.class).where("jobs_id=?", jobsId).execute();
    }

    /**
     * @param jobsId Category name
     * @return Category Object
     */
    public static Jobs getJobsById(long jobsId)
    {
        Jobs returnCat = new Select().from(Jobs.class).where("jobs_id = ?", jobsId).executeSingle();
        return returnCat;
    }
    /**
     * Move a job from one category to another
     *
     * @param j
     * @param targetCat
     * @return
     */
    public static Jobs move(Jobs j, String targetCat)
    {

        if (j.getCategory().getCattitle().equals(targetCat))
        {
            //sare karie
            return j;
        }
        Category c = Category.getCategoryByName(targetCat);
        j.setCategory(c);
        j.save();
        return j;
    }

    /**
     * Add a new Job to a Categoy
     *
     * @param jobName
     * @param jobCat
     * @return
     */
    public static Jobs add(String jobName, String jobCat)
    {
        Category c = Category.getCategoryByName(jobCat);
        Jobs job = new Jobs(jobName, c);
        Long returnCode = job.save();
        if (returnCode < 0)
        {
            //error in db
            return null;
        }
        return job;
    }

    public long getJobsId()
    {
        return jobsId;
    }

    public void setJobsId(long jobsId)
    {
        this.jobsId = jobsId;
    }

    public String getJobsName()
    {
        return jobsName;
    }

    public void setJobsName(String jobsName)
    {
        this.jobsName = jobsName;
    }

    public String getJobsAddress()
    {
        return jobsAddress;
    }

    public void setJobsAddress(String jobsAddress)
    {
        this.jobsAddress = jobsAddress;
    }

    public String getJobsTel1()
    {
        return jobsTel1;
    }

    public void setJobsTel1(String jobsTel1)
    {
        this.jobsTel1 = jobsTel1;
    }

    public String getJobsTel2()
    {
        return jobsTel2;
    }

    public void setJobsTel2(String jobsTel2)
    {
        this.jobsTel2 = jobsTel2;
    }

    public String getJobsWeb()
    {
        return jobsWeb;
    }

    public void setJobsWeb(String jobsWeb)
    {
        this.jobsWeb = jobsWeb;
    }

    public String getJobsEmail()
    {
        return jobsEmail;
    }

    public void setJobsEmail(String jobsEmail)
    {
        this.jobsEmail = jobsEmail;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getJobPicture()
    {
        return jobPicture;
    }

    public void setJobPicture(String jobPicture)
    {
        this.jobPicture = jobPicture;
    }

    public long getLatitude()
    {
        return latitude;
    }

    public void setLatitude(long latitude)
    {
        this.latitude = latitude;
    }

    public long getLongitude()
    {
        return longitude;
    }

    public void setLongitude(long longitude)
    {
        this.longitude = longitude;
    }

    public int getJobCode()
    {
        return jobCode;
    }

    public void setJobCode(int jobCode)
    {
        this.jobCode = jobCode;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Jobs)) return false;
        if (!super.equals(o)) return false;

        Jobs jobs = (Jobs) o;

        return getId() == jobs.getId();

    }


    @Override
    public String toString()
    {
        return getJobsId()+":"+getJobsName()+":"+category;
    }

    public void addToFavourite()
    {
        Favourite f = new Favourite(this);
        f.save();
    }
}
