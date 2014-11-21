package hu.ait.tiffanynguyen.classscheduler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import hu.ait.tiffanynguyen.classscheduler.data.MyClass;


/**
 * An activity representing a list of Days. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DayDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link DayListFragment} and the item details
 * (if present) is a {@link DayDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link DayListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class DayListActivity extends Activity
        implements DayListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private DayDetailFragment dayDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);

        if (findViewById(R.id.day_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-land and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((DayListFragment) getFragmentManager()
                    .findFragmentById(R.id.day_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link DayListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DayDetailFragment.ARG_ITEM_ID, id);
            dayDetailFragment = new DayDetailFragment();
            dayDetailFragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.day_detail_container, dayDetailFragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, DayDetailActivity.class);
            detailIntent.putExtra(DayDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mTwoPane) {
            getMenuInflater().inflate(R.menu.activity_day_list_twopane, menu);
        } else {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_day_list, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_all) {
            new AlertDialog.Builder(DayListActivity.this)
                    .setTitle(getString(R.string.title_delete_all_classes))
                    .setMessage(getString(R.string.message_reaffirm_delete))
                    .setPositiveButton(getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            MyClass.deleteAll(MyClass.class);
                        }
                    })
                    .setNegativeButton(getString(R.string.label_cancel), null)
                    .show();
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
        dialog.setTargetFragment(dayDetailFragment, DayDetailActivity.REQUEST_CODE_NEW_CLASS);
        dialog.show(getFragmentManager(), CreateNewClass.TAG);
    }
}
