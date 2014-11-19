package hu.ait.tiffanynguyen.classscheduler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_create_new_class, null);

        final Spinner spinnerClassDay = (Spinner) dialoglayout.findViewById(R.id.spinnerClassDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassDay.setAdapter(adapter);

        SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");

        final Button btnStartDate = (Button) dialoglayout.findViewById(R.id.btnStartTime);
        final Calendar initDate = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        initDate.set(Calendar.HOUR_OF_DAY, 0);
        initDate.set(Calendar.MINUTE, 0);
        initDate.set(Calendar.SECOND, 0);
        initDate.set(Calendar.MILLISECOND, 0);
        btnStartDate.setText(date_format.format(initDate.getTime()));
        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnStartDate.setText(String.format("%02d", selectedHour) + ":" +
                                String.format("%02d", selectedMinute));
                    }
                }, initDate.get(Calendar.HOUR_OF_DAY), initDate.get(Calendar.MINUTE), true);
//                mTimePicker.setTitle(res.getString(R.string.title_select_time));
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        final Button btnEndDate = (Button) dialoglayout.findViewById(R.id.btnEndTime);
        btnEndDate.setText(date_format.format(initDate.getTime()));
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                btnEndDate.setText(String.format("%02d", selectedHour) + ":" +
                                        String.format("%02d", selectedMinute));
                            }
                        }, initDate.get(Calendar.HOUR_OF_DAY), initDate.get(Calendar.MINUTE), true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        final EditText etTitle = (EditText) dialoglayout.findViewById(R.id.etClassTitle);
        final EditText etDesc = (EditText) dialoglayout.findViewById(R.id.etClassDesc);
        final EditText etLocation = (EditText) dialoglayout.findViewById(R.id.etClassLocation);

        final AlertDialog builder = new AlertDialog.Builder(getActivity())
                .setView(dialoglayout)
                .setTitle("Add New Class")
                .setPositiveButton("Save", null)
//                .setTitle(res.getString(R.string.title_add_new_class))
//                .setPositiveButton(res.getString(R.string.label_save), null)
                .create();

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

                            new MyClass(etTitle.getText().toString(), etDesc.getText().toString(),
                                    etLocation.getText().toString(), FormatDate.unformat(btnStartDate.getText().toString()),
                                    FormatDate.unformat(btnEndDate.getText().toString()),
                                    DayItem.DayType.fromInt(spinnerClassDay.getSelectedItemPosition())).save();
                            builder.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getActivity().getApplicationContext(), "Title must not be empty", Toast.LENGTH_LONG).show();
//                            Toast.makeText(getActivity().getApplicationContext(), res.getString(R.string.toast_empty_title), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        return builder;
    }
}
