package com.leo.cyber2021l07e02;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    private EditText etA, etB, etC;
    private TextView tvSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        etC = findViewById(R.id.etC);
        tvSolutions = findViewById(R.id.tvSolutions);
    }

    public void onClick_Clear(View view) {
        etA.setText("");
        etB.setText("");
        etC.setText("");
    }

    public void onClick_Random(View view) {
        etA.setText(Double.toString(Math.random() * 100 + 1));
        etB.setText(Double.toString(Math.random() * 100 + 1));
        etC.setText(Double.toString(Math.random() * 100 + 1));
    }

    private double getDoubleFromEditText(EditText et, String errmsg) {
        String str = et.getText().toString().trim();
        if (str.isEmpty()) {
            Toast.makeText(this, errmsg, Toast.LENGTH_SHORT).show();
            return NaN;
        }
        double ret = NaN;
        try {
            ret = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            Toast.makeText(this, errmsg, Toast.LENGTH_SHORT).show();
            return NaN;
        }
        return ret;
    }

    public void onClick_Solve(View view) {
        double a, b, c;
        a = getDoubleFromEditText(etA, "Component a is missing or invalid");
        if (isNaN(a)) return;
        b = getDoubleFromEditText(etB, "Component b is missing or invalid");
        if (isNaN(b)) return;
        c = getDoubleFromEditText(etC, "Component c is missing or invalid");
        if (isNaN(c)) return;
        Intent si = new Intent(this, SolutionActivity.class);
        si.putExtra("a", a);
        si.putExtra("b", b);
        si.putExtra("c", c);
        startActivityForResult(si, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK || data == null) {
            Toast.makeText(this, "bad onActivityResult call\nrequestCode = " + requestCode + "\nresultCode = " + resultCode + "\ndata = " + data, Toast.LENGTH_LONG).show();
            return;
        }
        double[] solutions = data.getDoubleArrayExtra("solutions");
        if (solutions.length == 0)
            tvSolutions.setText("None");
        else if (solutions.length == 1)
            tvSolutions.setText(Double.toString(solutions[0]));
        else if (solutions.length == 2)
            tvSolutions.setText(solutions[0] + ", " + solutions[1]);
        else
            Toast.makeText(this, "solutions array had <0 or >2 indexes", Toast.LENGTH_LONG).show();
    }

}
