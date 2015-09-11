package org.baran.activities;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import org.baran.R;
import org.baran.model.Category;
import org.baran.model.Jobs;

import java.util.ArrayList;
import java.util.List;

import CustomListDrawer.CustomGridJobs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JobDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JobDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobDetailFragment extends Fragment
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
    public static JobDetailFragment newInstance(long param1)
    {
        JobDetailFragment fragment = new JobDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    public JobDetailFragment()
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_job_detail, container, false);
TextView tv = (TextView) v.findViewById(R.id.tv);
        tv.setText("Jobe felan");
       Jobs j = Jobs.getJobsById(mParam1);
        tv.append(j.toString());

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



}
