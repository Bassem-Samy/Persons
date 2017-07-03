package com.bassem.persons.ui.person.personslisting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bassem.persons.R;
import com.bassem.persons.adapters.PersonRecyclerViewAdapter;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.ui.person.personslisting.di.DaggerPersonsListingComponent;
import com.bassem.persons.ui.person.personslisting.di.PersonsListingModule;
import com.bassem.persons.utils.Constants;
import com.bassem.persons.utils.PersonsSortFields;
import com.bassem.persons.utils.SharedPreferencesHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
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

    @BindView(R.id.rclr_person)
    RecyclerView personRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    @BindString(R.string.general_error)
    String generalError;
    PersonRecyclerViewAdapter mAdapter;

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
        mainProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateData(List<Person> items) {
        if (mAdapter == null) {
            mAdapter = new PersonRecyclerViewAdapter(items, mOnPersonClickedListener);
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            personRecyclerView.setLayoutManager(manager);
            personRecyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), generalError, Toast.LENGTH_SHORT).show();

    }

    View.OnClickListener mOnPersonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = personRecyclerView.getChildLayoutPosition(view);
            if (mListener != null) {
                mListener.onFragmentInteraction(mAdapter.getItemByPosition(position));
            }

        }
    };

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity when a person from the list is clicked
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Person person);
    }
}
