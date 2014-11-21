package hu.ait.tiffanynguyen.classscheduler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import hu.ait.tiffanynguyen.classscheduler.adapter.FormatDate;
import hu.ait.tiffanynguyen.classscheduler.data.DayItem;
import hu.ait.tiffanynguyen.classscheduler.data.MyClass;


public class CreateNewClass extends DialogFragment {

    public static final String TAG = "CreateClassFragment";
//    final Resources res = getResources();

    public interface NewClassDialogListener {
        void onFinishNewDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Resources res = getResources();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_create_new_class, null);
        boolean editing = false;
        MyClass myClass = null;

        if (getArguments().containsKey(DayDetailFragment.KEY_CLASS_ITEM)) {
            editing = true;
            myClass = MyClass.findById(MyClass.class,
                    getArguments().getLong(DayDetailFragment.KEY_CLASS_ITEM));
        }

        final Spinner spinnerClassDay = (Spinner) dialoglayout.findViewById(R.id.spinnerClassDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassDay.setAdapter(adapter);

        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");

        final Button btnStartDate = (Button) dialoglayout.findViewById(R.id.btnStartTime);
        final Calendar initStartDate = new GregorianCalendar();
        if (editing) {
            initStartDate.setTimeInMillis(myClass.getStartTime());
        } else {
            // reset hour, minutes, seconds and millis
            initStartDate.set(Calendar.HOUR_OF_DAY, 0);
            initStartDate.set(Calendar.MINUTE, 0);
            initStartDate.set(Calendar.SECOND, 0);
            initStartDate.set(Calendar.MILLISECOND, 0);
        }
        btnStartDate.setText(date_format.format(initStartDate.getTime()));
        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnStartDate.setText(FormatDate.format(selectedHour, selectedMinute));
                        initStartDate.setTimeInMillis((selectedMinute * 60 + selectedHour * 3600) * 1000);
                    }
                }, initStartDate.get(Calendar.HOUR_OF_DAY), initStartDate.get(Calendar.MINUTE), true);
                mTimePicker.setTitle(res.getString(R.string.title_select_time));
//                    mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        final Button btnEndDate = (Button) dialoglayout.findViewById(R.id.btnEndTime);
        final Calendar initEndDate = new GregorianCalendar();
        if (editing) {
            initEndDate.setTimeInMillis(myClass.getEndTime());
        } else {
            // reset hour, minutes, seconds and millis
            initEndDate.set(Calendar.HOUR_OF_DAY, 0);
            initEndDate.set(Calendar.MINUTE, 0);
            initEndDate.set(Calendar.SECOND, 0);
            initEndDate.set(Calendar.MILLISECOND, 0);
        }
        btnEndDate.setText(date_format.format(initEndDate.getTime()));
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                btnEndDate.setText(FormatDate.format(selectedHour, selectedMinute));
                                initEndDate.setTimeInMillis((selectedMinute * 60 + selectedHour * 3600) * 1000);
                            }
                        }, initEndDate.get(Calendar.HOUR_OF_DAY), initEndDate.get(Calendar.MINUTE), true);
//                mTimePicker.setTitle("Select Time");
                mTimePicker.setTitle(res.getString(R.string.title_select_time));
                mTimePicker.show();
            }
        });

        final EditText etTitle = (EditText) dialoglayout.findViewById(R.id.etClassTitle);
        final EditText etDesc = (EditText) dialoglayout.findViewById(R.id.etClassDesc);
        final EditText etLocation = (EditText) dialoglayout.findViewById(R.id.etClassLocation);

        // deal with presets if editing
        if (editing) {
            spinnerClassDay.setSelection(myClass.getDay().getIntValue());
            etTitle.setText(myClass.getTitle());
            etDesc.setText(myClass.getDescription());
            etLocation.setText(myClass.getLocation());
        }

        final AlertDialog builder = new AlertDialog.Builder(getActivity())
                .setView(dialoglayout)
//                .setTitle("Add New Class")
//                .setPositiveButton("Save", null)
                .setTitle(res.getString(R.string.title_add_new_class))
                .setPositiveButton(res.getString(R.string.label_save), null)
                .create();

        final boolean finalEditing = editing;
        builder.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = builder.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        try {
                            if (etTitle.getText().toString().matches(""))
                                throw new Exception();
//                            Log.i("LOG_INFO", FormatDate.unformat(btnStartDate.getText().toString())+" "+
//                                    FormatDate.unformat(btnEndDate.getText().toString()));

                            if (finalEditing) {
                                MyClass it = MyClass.findById(MyClass.class,
                                        getArguments().getLong(DayDetailFragment.KEY_CLASS_ITEM));
                                it.setTitle(etTitle.getText().toString());
                                it.setDescription(etDesc.getText().toString());
                                it.setLocation(etLocation.getText().toString());
                                it.setStartTime(FormatDate.unformat(btnStartDate.getText().toString()));
                                it.setEndTime(FormatDate.unformat(btnEndDate.getText().toString()));
                                it.setDay(DayItem.DayType.fromInt(spinnerClassDay.getSelectedItemPosition()));
                                it.save();
                            } else {
                                new MyClass(etTitle.getText().toString(), etDesc.getText().toString(),
                                        etLocation.getText().toString(), FormatDate.unformat(btnStartDate.getText().toString()),
                                        FormatDate.unformat(btnEndDate.getText().toString()),
                                        DayItem.DayType.fromInt(spinnerClassDay.getSelectedItemPosition())).save();
                            }
                            ((NewClassDialogListener)getTargetFragment()).onFinishNewDialog();

                            builder.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getActivity().getApplicationContext(), res.getString(R.string.toast_empty_title), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        return builder;
    }
}
