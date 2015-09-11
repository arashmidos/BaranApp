package org.baran;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.baran.activities.JobDetailFragment;
import org.baran.activities.JobListFragment;
import org.baran.activities.MainFragment;
import org.baran.model.Category;
import org.baran.model.Jobs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import UIActionBarDrawer.ActionBarSetup;

public class MainActivity extends ActionBarSetup implements MainFragment.OnFragmentInteractionListener, JobListFragment.OnFragmentInteractionListener, JobDetailFragment.OnFragmentInteractionListener
{

    private int n = 0;

    private Context context;
    private String urls = "http://baran.kaprog.ir/index.php/webservice/get_cat_and_job";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDummyData();
        setupactionbar();
        drawer();

        MainFragment frag = new MainFragment();
        getFragmentManager().beginTransaction().add(R.id.container, frag).commit();

    }

    private void addDummyData()
    {
        if (Category.allCategories().size() <= 0)
        {
            for (int i = 0; i < 5; i++)
            {
                Category c = new Category(i, "Title" + i, i * 5, "path" + i);
                c.setCatId(c.save());

                for (int j = 0; j < 4; j++)
                {
                    Jobs jj = new Jobs("Jobs" + j, c);
                    jj.save();
                }
            }
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    public void showJobs(Category cat)
    {
        getFragmentManager().popBackStackImmediate("0", 0);
        int count = getFragmentManager().getBackStackEntryCount();

        getFragmentManager().beginTransaction()
                .replace(R.id.container, JobListFragment.newInstance(cat.getCatId()), "JobsList")
                .addToBackStack(String.valueOf(count))
                .commit();
    }

    public void showJobsDetail(Jobs temp)
    {
        getFragmentManager().popBackStackImmediate("0", 0);
        int count = getFragmentManager().getBackStackEntryCount();
        getFragmentManager().beginTransaction()
                .replace(R.id.container, JobDetailFragment.newInstance(temp.getJobsId()), "MainFragment")

                .addToBackStack(String.valueOf(count))
                .commit();
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0)
        {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else
        {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // action with ID action_refresh was selected
            case R.id.action_register:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }

    private class LongOperation extends AsyncTask<String, Void, Void>
    {

        ProgressDialog Dialog = new ProgressDialog(context);
        String data = "";
        private String Content;
        private String Error = null;

        protected void onPreExecute()
        {


            Dialog.setMessage("Load Products ...");
            Dialog.show();


            try
            {
                //cat_id cat_code job_id job_code
                data = "&" + URLEncoder.encode("cat_id[]", "UTF8") + "=" + "";//(Category.allCategoriesId());
                data += "&" + URLEncoder.encode("cat_code[]", "UTF8") + "=" + "";//(Category.allCategoriesCode());
//                data += "&" + URLEncoder.encode("job_id[]", "UTF8") + "=" + (Jobs.allJobsId());
//                data += "&" + URLEncoder.encode("job_code[]", "UTF8") + "=" + (Jobs.allJobsCode());

            } catch (UnsupportedEncodingException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // data += datasource.getGalleryData();

        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls)
        {
            Log.w("vafa", "hello");
            /************ Make Post Call To Web Server ***********/
            BufferedReader reader = null;

            // Send data
            try
            {

                // Defined URL where to send data
                URL url = new URL(urls[0]);


                Log.w("login", data);
                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);

                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "");
                }

                // Append Server Response To Content String
                Content = sb.toString();
                // Content=Content.substring(3);

                Log.w("herecontenttttt", Content);
            } catch (Exception ex)
            {
                Error = ex.getMessage();
            } finally
            {
                try
                {

                    reader.close();
                } catch (Exception ex)
                {
                }
            }

            /*****************************************************/
            return null;
        }

        protected void onPostExecute(Void unused)
        {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();
            if (Error != null)
            {

                Toast.makeText(getApplicationContext(), Error, Toast.LENGTH_LONG).show();

                // uiUpdate.setText("Output : "+Error);

            } else
            {

                // Show Response Json On Screen (activity)

                /****************** Start Parse Response JSON Data *************/

                JSONObject jsonResponse;

                try
                {

                    /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                    jsonResponse = new JSONObject(Content);


                    /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                    /******* Returns null otherwise. *******/
                    Log.i("arash", jsonResponse.toString());

                    JSONArray catAdd = jsonResponse.getJSONArray("catAdd");
                    //TODO: check json array
                    for (int i = 0; i < catAdd.length(); i++)
                    {
                        JSONObject ob = catAdd.getJSONObject(i);
                        long id = ob.getLong("id");
                        String image = ob.getString("image");
                        String title = ob.getString("title");
                        int code = ob.getInt("code");

                        Category c = new Category(id, title, code, image);
                        c.save();
                    }

                    JSONArray catUpdate = jsonResponse.getJSONArray("catUpdate");
                    //TODO: check json array
                    for (int i = 0; i < catUpdate.length(); i++)
                    {
                        JSONObject ob = catUpdate.getJSONObject(i);
                        long id = ob.getLong("id");
                        String image = ob.getString("image");
                        String title = ob.getString("title");
                        int code = ob.getInt("code");

                        Category.update(id, title, image, code);
                    }

                    JSONArray catDelete = jsonResponse.getJSONArray("catDelete");
                    //TODO: check json array
                    for (int i = 0; i < catDelete.length(); i++)
                    {
                        JSONObject ob = catDelete.getJSONObject(i);
                        long id = ob.getLong("id");

                        Category.delete(id);
                    }

                    //--------------------------------------------------------------------------------------
                    JSONArray jobAdd = jsonResponse.getJSONArray("jobAdd");
                    //TODO: check json array
                    for (int i = 0; i < jobAdd.length(); i++)
                    {
                        JSONObject ob = jobAdd.getJSONObject(i);

                        long id = ob.getLong("id");
                        String image = ob.getString("image");
                        String webAddress = ob.getString("web_address");
                        String email = ob.getString("email");
                        String address = ob.getString("address");
                        String name = ob.getString("name");
                        long longitude = ob.getLong("longitude");
                        long latitude = ob.getLong("latitude");
                        int code = ob.getInt("code");
                        String tel1 = ob.getString("tel1");
                        String tel2 = ob.getString("tel2");

                        Jobs j = new Jobs(id, name, address, tel1, tel2, webAddress, email, image, latitude, longitude, code);
                        j.save();
                    }

                    JSONArray jobUpdate = jsonResponse.getJSONArray("jobUpdate");
                    //TODO: check json array
                    for (int i = 0; i < jobUpdate.length(); i++)
                    {
                        JSONObject ob = jobUpdate.getJSONObject(i);
                        long id = ob.getLong("id");
                        String image = ob.getString("image");
                        String webAddress = ob.getString("web_address");
                        String email = ob.getString("email");
                        String address = ob.getString("address");
                        String name = ob.getString("name");
                        long longitude = ob.getLong("longitude");
                        long latitude = ob.getLong("latitude");
                        int code = ob.getInt("code");
                        String tel1 = ob.getString("tel1");
                        String tel2 = ob.getString("tel2");

                        Jobs.edit(id, image, webAddress, email, address, name, longitude, latitude, code, tel1, tel2);
                    }

                    JSONArray jobDelete = jsonResponse.getJSONArray("jobDelete");
                    //TODO: check json array
                    for (int i = 0; i < jobDelete.length(); i++)
                    {
                        JSONObject ob = jobDelete.getJSONObject(i);
                        long id = ob.getLong("id");

                        Jobs.delete(id);
                    }

                } catch (JSONException e)
                {

                    e.printStackTrace();
                }
            }
        }
    }
}




