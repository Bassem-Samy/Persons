package com.bassem.persons.ui.personslisting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bassem.persons.R;
import com.bassem.persons.models.person.PersonData;
import com.bassem.persons.ui.personslisting.di.DaggerPersonsListingComponent;
import com.bassem.persons.ui.personslisting.di.PersonsListingComponent;
import com.bassem.persons.ui.personslisting.di.PersonsListingModule;
import com.bassem.persons.utils.Constants;
import com.bassem.persons.utils.PersonsSortFields;
import com.bassem.persons.utils.SharedPreferencesHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * A simple Fragment that displays list of persons
 */
public class PersonsListingFragment extends Fragment implements PersonsListingView {

    public static final String TAG = "persons_listing_fragment";
    private OnFragmentInteractionListener mListener;
    @Inject
    PersonsListingPresenter mPresenter;
    @Inject
    SharedPreferencesHelper mHelper;


    public PersonsListingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     */

    public static PersonsListingFragment newInstance() {
        PersonsListingFragment fragment = new PersonsListingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPersonsListingComponent.builder().personsListingModule(new PersonsListingModule(this, getContext(), Constants.BASE_URL))
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_persons_listing, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getPersons(mHelper.getApiToken(), PersonsSortFields.NAME_ASC.toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updateData(List<PersonData> items) {

    }

    @Override
    public void showError() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity when a person from the list is clicked
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(PersonData person);
    }
}
