package com.bassem.persons.ui.person.persondetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.persons.R;
import com.bassem.persons.models.person.PersonDetail;
import com.bassem.persons.ui.person.persondetails.di.DaggerPersonDetailsComponent;
import com.bassem.persons.ui.person.persondetails.di.PersonDetailsModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple Fragment that displays person details
 */
public class PersonDetailsFragment extends Fragment implements PersonDetailsView {
    public static final String TAG = "person_details_fragment";
    private static final String ARG_PERSON_ID = "arg_person_id";
    private static final String SAVED_PERSON_ID = "saved_person_id";
    private String mPersonId;
    @BindView(R.id.txt_person_name)
    TextView nameTextView;
    @BindView(R.id.txt_phones)
    TextView phonesTextView;
    @BindView(R.id.txt_emails)
    TextView emailsTextView;
    @BindView(R.id.img_person)
    ImageView personImageView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    @Inject
    PersonDetailsPresenter mPresenter;

    public PersonDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.               .
     *
     * @param userId
     * @return A new instance of fragment PersonDetailsFragment.
     */
    public static PersonDetailsFragment newInstance(String userId) {
        PersonDetailsFragment fragment = new PersonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PERSON_ID, userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPersonId = getArguments().getString(ARG_PERSON_ID);
        }
        DaggerPersonDetailsComponent.builder().personDetailsModule(new PersonDetailsModule(this, getContext())).build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_details, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeAutoLinks();
        if (savedInstanceState != null) {
            String savedPersonId = savedInstanceState.getString(SAVED_PERSON_ID);
            if (!TextUtils.isEmpty(savedPersonId)) {
                mPersonId = savedPersonId;
            }
        }
        mPresenter.getPersonData(mPersonId);
    }

    private void initializeAutoLinks() {
        phonesTextView.setAutoLinkMask(Linkify.PHONE_NUMBERS);
        phonesTextView.setLinksClickable(true);
        phonesTextView.setMovementMethod(LinkMovementMethod.getInstance());

        emailsTextView.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);
        emailsTextView.setLinksClickable(true);
        emailsTextView.setMovementMethod(LinkMovementMethod.getInstance());
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
    public void updateDetails(PersonDetail data) {

        nameTextView.setText(data.getName());
        phonesTextView.setText(data.getPhonesDisplayText());
        emailsTextView.setText(data.getEmailsDisplayText());
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void loadPersonDetails(String id) {
        mPresenter.getPersonData(id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_PERSON_ID, mPersonId);
    }
}
