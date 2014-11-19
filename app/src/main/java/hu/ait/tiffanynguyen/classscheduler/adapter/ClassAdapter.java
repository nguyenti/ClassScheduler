package hu.ait.tiffanynguyen.classscheduler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hu.ait.tiffanynguyen.classscheduler.R;
import hu.ait.tiffanynguyen.classscheduler.data.MyClass;

/**
 * Created by tiffanynguyen on 11/11/14.
 */
public class ClassAdapter extends BaseAdapter {

    private Context context;
    private List<MyClass> myClassList;

    public ClassAdapter(Context context, List<MyClass> myClassList) {
        this.context = context;
        this.myClassList = myClassList;
    }

    @Override
    public int getCount() {
        return myClassList.size();
    }

    @Override
    public Object getItem(int position) {
        return myClassList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addClass(MyClass p) {
        myClassList.add(p);
    }

    public void removeClass(int position) {
        if (position < myClassList.size())
            myClassList.remove(position);
    }

    public static class ViewHolder {
        TextView classTitle;
        TextView classTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.row_class, null);
            ViewHolder holder = new ViewHolder();
            holder.classTitle = (TextView) v.findViewById(R.id.classTitle);
            holder.classTime = (TextView) v.findViewById(R.id.classTime);
            v.setTag(holder);
        }

        final MyClass c = myClassList.get(position);

        if (c != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.classTitle.setText(c.getTitle());
            holder.classTime.setText(FormatDate.format(c.getStartTime(), c.getEndTime()));
        }

        return v;
    }
}
