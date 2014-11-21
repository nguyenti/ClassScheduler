package hu.ait.tiffanynguyen.classscheduler;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import hu.ait.tiffanynguyen.classscheduler.adapter.ClassAdapter;
import hu.ait.tiffanynguyen.classscheduler.data.DayItem;
import hu.ait.tiffanynguyen.classscheduler.data.MyClass;
import hu.ait.tiffanynguyen.classscheduler.day.DayContent;

/**
 * A fragment representing a single Day detail screen.
 * This fragment is either contained in a {@link DayListActivity}
 * in two-pane mode (on tablets) or a {@link DayDetailActivity}
 * on handsets.
 */
public class DayDetailFragment extends ListFragment implements CreateNewClass.NewClassDialogListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DayItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DayDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DayContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_day_detail, container, false);
//
//        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.day_detail)).setText(mItem.getDay());
//        }

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("ListFragment", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setTextFilterEnabled(true);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i("LOG_CLICK", "clicked!");

            }
        });

        refreshList();
    }

    private void displayListView() {

        //Array list of countries
        //ArrayList<MyClass> al = new ArrayList<MyClass>();
        //al.add(new MyClass("1title", "", "", 0, 0, DayItem.DayType.MONDAY));
        //al.add(new MyClass("title2", "", "", 0, 0, DayItem.DayType.TUESDAY));

//        MyClass m1 = new MyClass("title2", "", "", 0, 0, DayItem.DayType.TUESDAY);
//        m1.save();
//        MyClass m2 = new MyClass("1title", "", "", 0, 0, DayItem.DayType.MONDAY);
//        m2.save();

        //Select.from(MyClass.class).where(Condition.prop("day").eq(mItem.getDay())).orderBy("startTime").list();

        //create an ArrayAdaptar from the String Array


        //enables filtering for the contents of the given ListView


    }

    private void refreshList() {
        List<MyClass> classList = MyClass.listAll(MyClass.class);
        ClassAdapter adapter = new ClassAdapter(getActivity().getApplicationContext(),
                classList);
        // Assign adapter to ListView
        getListView().setAdapter(adapter);
    }

    @Override
    public void onFinishNewDialog() {
        refreshList();
    }
}