package org.baran.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.baran.R;
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
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private List<Jobs> listi = new ArrayList<>();
    private GridView jobList;
    // TODO: Rename and change types of parameters
    private long mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(long param1)
    {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getLong(ARG_PARAM1);

        }
    }


    private    TextView labelemail,labelname,labelpass,labelrepass,labelcell,labelage;
    private    EditText email,name,pass,repass,cell,age;
    private Button register;
    private RadioGroup radiogroup;
    private String urls= "http://baran.kaprog.ir/index.php/webservice/register_user";
    private RadioButton female;
    private RadioButton male;
    private int gender=0;

    // email pass re_password   name mobile age gender 1 ya 2

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_signup, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "homa.ttf");



        radiogroup = (RadioGroup)v.findViewById(R.id.radioGroup1);
        male = (RadioButton)v.findViewById(R.id.radio0);
        female = (RadioButton)v.findViewById(R.id.radio1);


        labelemail = (TextView)v.findViewById(R.id.labelmailregister);
        labelemail.setTypeface(font);
        labelname = (TextView)v.findViewById(R.id.labelname);
        labelname.setTypeface(font);
        labelpass = (TextView)v.findViewById(R.id.labelpassregister);
        labelpass.setTypeface(font);
        labelrepass = (TextView)v.findViewById(R.id.labelrepassregister);
        labelrepass.setTypeface(font);
        labelage = (TextView)v.findViewById(R.id.labelageregister);
        labelage.setTypeface(font);
        labelcell = (TextView)v.findViewById(R.id.labelmobileregister);
        labelcell.setTypeface(font);

        register = (Button)v.findViewById(R.id.btnregister);

        email = (EditText)v.findViewById(R.id.txtmailregister);
        email.setTypeface(font);
        name = (EditText)v.findViewById(R.id.txtname);
        name.setTypeface(font);
        pass = (EditText)v.findViewById(R.id.txtpassregister);
        pass.setTypeface(font);
        repass = (EditText)v.findViewById(R.id.txtrepassregister);
        repass.setTypeface(font);
        cell = (EditText)v.findViewById(R.id.txtmobileregister);
        cell.setTypeface(font);
        age = (EditText)v.findViewById(R.id.txtageregister);
        age.setTypeface(font);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SignUp().execute(urls);
            }
        });



        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(male.isChecked()){
                    gender = 0;
                }
                else if (female.isChecked()){
                    gender = 1;
                }
            }
        });





//        adding();
//
//        CustomGridJobs adapter = new
//                CustomGridJobs(getActivity(), listi);
//        jobList.setAdapter(adapter);

        return v;

    }
//
//    private void adding()
//    {
//
//        Category c = Category.getCategoryById(mParam1);
//        List<Jobs> jobs = c.items();
//        listi = jobs;
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    private class SignUp extends AsyncTask<String, Void, Void>
    {

        private String Content;
        private String Error = null;
        ProgressDialog Dialog = new ProgressDialog(getActivity());

        String data = "";


        protected void onPreExecute()
        {


            Dialog.setMessage("در حال ثبت اطلاعات...");
            Dialog.show();


            try
            {

            data = "&" + URLEncoder.encode("email", "UTF8") + "=" +(email.getText().toString());
            data += "&" + URLEncoder.encode("name", "UTF8") + "=" +(name.getText().toString());
            data += "&" + URLEncoder.encode("password", "UTF8") + "=" + (pass.getText().toString());
            data += "&" + URLEncoder.encode("re_password", "UTF8") + "=" + (repass.getText().toString());
            data += "&" + URLEncoder.encode("mobile", "UTF8") + "=" + (cell.getText().toString());
            data += "&" + URLEncoder.encode("age", "UTF8") + "=" + (age.getText().toString());
            data += "&" + URLEncoder.encode("gender", "UTF8") + "=" + (gender);

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

                Toast.makeText(getActivity(), Error, Toast.LENGTH_LONG).show();

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



                    if(jsonResponse.get("status").equals("error")){


                        Toast.makeText(getActivity(),jsonResponse.getString("error"),Toast.LENGTH_LONG).show();

                    }
                    else {

                        Toast.makeText(getActivity(),"ثبت نام شما با موفقیت انجام شد.",Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e)
                {

                    e.printStackTrace();
                }
            }
        }
    }

}
