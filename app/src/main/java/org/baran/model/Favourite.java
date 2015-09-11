package org.baran.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Arashmidos on 9/10/2015.
 */
@Table(name="Favourite")
public class Favourite extends Model
{
    @Column(name="Jobs")
    private Jobs job;

    public Favourite(Jobs job)
    {
        this.job = job;
    }

    public Favourite()
    {

    }

    public Jobs getJob()
    {
        return job;
    }

    public void setJob(Jobs job)
    {
        this.job = job;
    }

    @Override
    public String toString()
    {
        return getId()+":"+job;
    }
}
