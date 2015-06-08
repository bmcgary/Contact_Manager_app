package edu.byu.cs.mycontacts_240.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import edu.byu.cs.mycontacts_240.Database.Database;
import edu.byu.cs.mycontacts_240.Model.Contact;
import edu.byu.cs.mycontacts_240.R;

public class PersonActivity extends ActionBarActivity
{

    Contact contact;
    boolean editable;
    boolean adding;

    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText address;
    EditText city;
    EditText zip;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        editable = false;

        if(getIntent() != null)
        {
            if(getIntent().getExtras() != null)
            {
                contact = (Contact)getIntent().getExtras().get("Person");
                editable = (Boolean)getIntent().getExtras().get("Edit");
                adding = (Boolean)getIntent().getExtras().get("Adding");
            }
            else
                contact = new Contact();
        }
        else
            contact = new Contact();

    }


    @Override
    public void onResume()
    {
        super.onResume();


        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        phone = (EditText)findViewById(R.id.phone_num);
        address = (EditText)findViewById(R.id.address);
        city = (EditText)findViewById(R.id.city);
        zip = (EditText)findViewById(R.id.zip);
        email = (EditText)findViewById(R.id.email);

        firstName.setText(contact.firstName);
        lastName.setText(contact.lastName);
        phone.setText(contact.phone);
        address.setText(contact.address);
        city.setText(contact.city);
        zip.setText(contact.zip);
        email.setText(contact.email);

        if(!editable)
        {
            firstName.setFocusable(false);
            lastName.setFocusable(false);
            phone.setFocusable(false);
            address.setFocusable(false);
            city.setFocusable(false);
            zip.setFocusable(false);
            email.setFocusable(false);

            firstName.setClickable(false);
            lastName.setClickable(false);
            phone.setClickable(false);
            address.setClickable(false);
            city.setClickable(false);
            zip.setClickable(false);
            email.setClickable(false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (adding)
            getMenuInflater().inflate(R.menu.adding_menu, menu);
        else if (editable)
            getMenuInflater().inflate(R.menu.edit_menu, menu);
        else
            getMenuInflater().inflate(R.menu.view_person_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit)
        {
            Intent intent = new Intent(getBaseContext(), PersonActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Person", contact);
            intent.putExtra("Edit", new Boolean(true));
            intent.putExtra("Adding", new Boolean(false));
            this.startActivity(intent);
            return true;
        }
        else if(id == R.id.action_add)
        {
            //get all the data and save it to the db. Return to previous screen
            Database db = new Database(getBaseContext());
            Contact contact = new Contact();
            contact.firstName = firstName.getText().toString();
            contact.lastName = lastName.getText().toString();
            contact.phone = phone.getText().toString();
            contact.address = address.getText().toString();
            contact.city = city.getText().toString();
            contact.zip = zip.getText().toString();
            contact.email = email.getText().toString();

            db.addContact(contact);
            super.onBackPressed();

            return true;
        }
        else if(id == R.id.action_cancel)
        {
            //REturn to previous screen
            super.onBackPressed();
            return true;
        }
        else if (id == R.id.action_delete)
        {
            Database db = new Database(getBaseContext());
            db.deleteContact(contact);

            Intent intent = new Intent(getBaseContext(), PhoneList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            this.startActivity(intent);

            return true;
        }
        else if(id == R.id.action_update)
        {
            Database db = new Database(getBaseContext());
            Contact contact = new Contact();
            contact.rowID = this.contact.rowID;
            contact.firstName = firstName.getText().toString();
            contact.lastName = lastName.getText().toString();
            contact.phone = phone.getText().toString();
            contact.address = address.getText().toString();
            contact.city = city.getText().toString();
            contact.zip = zip.getText().toString();
            contact.email = email.getText().toString();

            db.upDateContact(contact);
            this.contact = contact;
            super.onBackPressed();
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
