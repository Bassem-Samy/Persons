package com.bassem.persons.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bassem.persons.R;
import com.bassem.persons.database.models.Person;
import com.bassem.persons.models.person.PersonData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bassem on 7/3/2017.
 */

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder> {
    private List<Person> mDataset;
    private View.OnClickListener mOnPersonClickListener;

    public PersonRecyclerViewAdapter(List<Person> items, View.OnClickListener onPersonClickListener) {
        this.mDataset = items;
        this.mOnPersonClickListener = onPersonClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        view.setOnClickListener(mOnPersonClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personNameTextView.setText(mDataset.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public Person getItemByPosition(int position) {
        if (mDataset != null) {
            return mDataset.get(position);
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_person_name)
        TextView personNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
