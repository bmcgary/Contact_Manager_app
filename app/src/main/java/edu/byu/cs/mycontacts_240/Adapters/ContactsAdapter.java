package edu.byu.cs.mycontacts_240.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.byu.cs.mycontacts_240.Activities.PersonActivity;
import edu.byu.cs.mycontacts_240.Model.Contact;
import edu.byu.cs.mycontacts_240.R;

/**
 * Created by blaine on 5/27/15.
 */
public class ContactsAdapter extends ArrayAdapter<Contact>
{
    public ContactsAdapter(Context context, List<Contact> objects)
    {
        super(context, 0, objects);
    }


    @Override
    public View getView(final int pos, View view, ViewGroup parent)
    {
        if(view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.contact_row, null);
        }
        TextView name = (TextView)view.findViewById(R.id.row_text);
        name.setText(getItem(pos).toString());

        name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), PersonActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Person", getItem(pos));
                intent.putExtra("Edit", new Boolean(false));
                intent.putExtra("Adding", new Boolean(false));
                getContext().startActivity(intent);
            }
        });

        return view;

    }
}
