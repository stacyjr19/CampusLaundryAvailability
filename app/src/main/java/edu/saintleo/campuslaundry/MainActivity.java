package edu.saintleo.campuslaundry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;

/** Offline demonstration: availability is entered on this device, not read from real machines. */
public class MainActivity extends Activity {
    private static final String PREFS = "machine_status_v1";
    private final String[] machines = {"Washer 1", "Washer 2", "Washer 3", "Dryer 1", "Dryer 2", "Dryer 3"};
    private final boolean[] available = {true, false, true, true, false, true};
    private SharedPreferences preferences;
    private LinearLayout machineList;
    private TextView summary, lastUpdated;
    private int navy = Color.rgb(24, 43, 72), green = Color.rgb(19, 116, 76), amber = Color.rgb(154, 91, 0);

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        for (int i = 0; i < machines.length; i++) available[i] = preferences.getBoolean("machine_" + i, available[i]);
        buildScreen();
        refreshDisplay();
    }

    private void buildScreen() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(Color.rgb(246, 248, 252));
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(28), dp(20), dp(24));
        scroll.addView(root);

        TextView heading = text("Campus Laundry", 28, navy, true);
        root.addView(heading);
        TextView subtitle = text("Machine availability • Local demo", 14, Color.DKGRAY, false);
        root.addView(subtitle);
        addSpace(root, 22);

        summary = text("", 18, navy, true);
        root.addView(summary);
        lastUpdated = text("", 12, Color.DKGRAY, false);
        root.addView(lastUpdated);
        addSpace(root, 18);

        TextView instruction = text("Tap a machine to change its status", 14, Color.DKGRAY, false);
        root.addView(instruction);
        addSpace(root, 8);
        machineList = new LinearLayout(this);
        machineList.setOrientation(LinearLayout.VERTICAL);
        root.addView(machineList);
        addSpace(root, 14);

        Button refresh = button("Refresh Status");
        refresh.setOnClickListener(v -> {
            for (int i = 0; i < machines.length; i++) available[i] = preferences.getBoolean("machine_" + i, available[i]);
            refreshDisplay();
            Toast.makeText(this, "Status refreshed from saved data", Toast.LENGTH_SHORT).show();
        });
        root.addView(refresh);
        addSpace(root, 10);
        Button reset = button("Reset Demo Data");
        reset.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setTitle("Reset machine status?")
            .setMessage("This will restore the six sample machine statuses on this device.")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Reset", (dialog, which) -> {
                boolean[] defaults = {true, false, true, true, false, true};
                SharedPreferences.Editor editor = preferences.edit();
                for (int i = 0; i < machines.length; i++) { available[i] = defaults[i]; editor.putBoolean("machine_" + i, available[i]); }
                editor.apply(); refreshDisplay();
            }).show());
        root.addView(reset);
        addSpace(root, 16);
        TextView note = text("Demo only: statuses are saved on this device. They are not connected to campus machines or shared between users.", 12, Color.DKGRAY, false);
        root.addView(note);
        setContentView(scroll);
    }

    private void refreshDisplay() {
        machineList.removeAllViews();
        int free = 0;
        for (boolean state : available) if (state) free++;
        summary.setText(free + " of " + machines.length + " machines available");
        lastUpdated.setText("Checked " + DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date()));
        for (int i = 0; i < machines.length; i++) {
            final int index = i;
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(dp(14), dp(14), dp(14), dp(14));
            GradientDrawable background = new GradientDrawable();
            background.setColor(Color.WHITE); background.setCornerRadius(dp(12));
            background.setStroke(dp(1), Color.rgb(222, 227, 235));
            row.setBackground(background);
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(-1, -2);
            rowParams.bottomMargin = dp(10);
            machineList.addView(row, rowParams);
            TextView name = text(machines[i], 17, navy, true);
            row.addView(name, new LinearLayout.LayoutParams(0, -2, 1));
            TextView status = text(available[i] ? "Available" : "In Use", 14, available[i] ? green : amber, true);
            row.addView(status);
            row.setContentDescription(machines[i] + ", " + (available[i] ? "Available" : "In Use") + ". Tap to change status.");
            row.setClickable(true);
            row.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle(machines[index])
                .setItems(new String[]{"Available", "In Use"}, (dialog, which) -> {
                    available[index] = which == 0;
                    preferences.edit().putBoolean("machine_" + index, available[index]).apply();
                    refreshDisplay();
                }).show());
        }
    }
    private TextView text(String value, int sp, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value); view.setTextSize(sp); view.setTextColor(color);
        if (bold) view.setTypeface(null, Typeface.BOLD);
        return view;
    }
    private Button button(String label) {
        Button view = new Button(this); view.setText(label); view.setAllCaps(false);
        view.setTextSize(16); view.setMinHeight(dp(48)); return view;
    }
    private void addSpace(LinearLayout parent, int height) {
        View spacer = new View(this); parent.addView(spacer, new LinearLayout.LayoutParams(1, dp(height)));
    }
    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}
