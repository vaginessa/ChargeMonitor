package com.practice.vlad.chargemonitor.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.practice.vlad.chargemonitor.R;
import com.practice.vlad.chargemonitor.helpers.Color;
import com.practice.vlad.chargemonitor.managers.SettingsManager;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private boolean wasCreated = false;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        initChargingColor(layout);
        initChargedColor(layout);
        initChargePercentagePicker(layout);
        wasCreated = true;
        return layout;
    }

    private void initChargingColor(View layout) {
        Spinner spinnerChargingColor = (Spinner) layout.findViewById(R.id.spChargingColor);
        spinnerChargingColor.setAdapter(new ArrayAdapter<Color>(getActivity(), android.R.layout.simple_spinner_item, Color.values()));
        int color = SettingsManager.getInstance(getActivity()).getLedChargingColor();
        if(color == 0) {
            color = Color.RED.getValue();
        }
        spinnerChargingColor.setSelection(Color.getPosition(color));
        spinnerChargingColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (wasCreated) {
                    Color color = (Color) parent.getItemAtPosition(position);
                    SettingsManager.getInstance(getActivity()).saveLedChargingColor(color.getValue());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initChargedColor(View layout) {
        Spinner spinnerChargedColor = (Spinner) layout.findViewById(R.id.spChargedColor);
        spinnerChargedColor.setAdapter(new ArrayAdapter<Color>(getActivity(), android.R.layout.simple_spinner_item, Color.values()));
        int color = SettingsManager.getInstance(getActivity()).getLedChargedColor();
        if(color == 0) {
            color = Color.GREEN.getValue();
        }
        spinnerChargedColor.setSelection(Color.getPosition(color));
        spinnerChargedColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (wasCreated) {
                    Color color = (Color) parent.getItemAtPosition(position);
                    SettingsManager.getInstance(getActivity()).saveLedChargedColor(color.getValue());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initChargePercentagePicker(View layout) {
        NumberPicker percentageNumberPicker = (NumberPicker) layout.findViewById(R.id.npChargePercentage);
        percentageNumberPicker.setMaxValue(100);
        percentageNumberPicker.setMinValue(1);
        percentageNumberPicker.setValue(SettingsManager.getInstance(getActivity()).getLowBatteryWarningThreshold());
        percentageNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                SettingsManager.getInstance(getActivity()).saveLowBatteryWarningThreshold(i);
            }
        });
    }
}
