package org.baran.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Arashmidos on 9/10/2015.
 */
@Table(name="Notification")
public class Notification extends Model
{
    @Column(name = "type")
    private int type;

    @Column(name="content")
    private String content;

    @Column(name = "Jobs",onDelete = Column.ForeignKeyAction.CASCADE)
    private Jobs job;


}
