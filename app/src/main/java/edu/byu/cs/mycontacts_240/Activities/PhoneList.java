package edu.byu.cs.mycontacts_240.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.byu.cs.mycontacts_240.Adapters.ContactsAdapter;
import edu.byu.cs.mycontacts_240.Database.Database;
import edu.byu.cs.mycontacts_240.Model.Contact;
import edu.byu.cs.mycontacts_240.R;


public class PhoneList extends ActionBarActivity
{

    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);

    }

    @Override
    public void onResume()
    {

        super.onResume();

        db = new Database(getBaseContext());
        ListView listView = (ListView) findViewById(R.id.list_o_contacts);
        ListAdapter list = new ContactsAdapter(getBaseContext(), db.getAllContacts());
        listView.setAdapter(list);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add)
        {
            Intent i = new Intent(getBaseContext(), PersonActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Person", new Contact());
            i.putExtra("Edit", new Boolean(true));
            i.putExtra("Adding", new Boolean (true));
            getApplicationContext().startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
