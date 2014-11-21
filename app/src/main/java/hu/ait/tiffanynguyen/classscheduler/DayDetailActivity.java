package hu.ait.tiffanynguyen.classscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.MenuItem;


/**
 * An activity representing a single Day detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link DayListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link DayDetailFragment}.
 */
public class DayDetailActivity extends Activity {

    public static final int REQUEST_CODE_NEW_CLASS = 101;
    private DayDetailFragment dayDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DayDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(DayDetailFragment.ARG_ITEM_ID));
            dayDetailFragment = new DayDetailFragment();
            dayDetailFragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.day_detail_container, dayDetailFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, DayListActivity.class));
            return true;
        } else if (id == R.id.action_new_class) {
            makeNewClass();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeNewClass() {
        // display dialog fragment with the message
        CreateNewClass dialog = new CreateNewClass();
        Bundle b = new Bundle();
        // eventually generalize to edit
        dialog.setArguments(b);
        dialog.setTargetFragment(dayDetailFragment, REQUEST_CODE_NEW_CLASS);
        dialog.show(getFragmentManager(), CreateNewClass.TAG);
    }
}
