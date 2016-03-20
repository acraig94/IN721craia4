package bit.craia4.complexscreencontrols;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EnrollConfirm confirmEnroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button confirmButton = (Button) findViewById(R.id.button_Confirm);

        confirmButton.setOnClickListener(new ConfirmButtonClickHandle());

    }

    public class ConfirmButtonClickHandle implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            RadioGroup InstrumentGroup = (RadioGroup) findViewById(R.id.rdoGroup_Instrument);
            int selectedID = InstrumentGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = (RadioButton) findViewById(selectedID);

            String radioSelection = new String();
            
            //Toast.makeText(MainActivity.this, "You have enrolled for " + selectedRadio.getText() + " lessons", Toast.LENGTH_LONG).show();

            confirmEnroll = new EnrollConfirm();
            FragmentManager fm = getFragmentManager();
            confirmEnroll.show(fm, "confirm");
        }
    }

    public void getConfirmData(boolean result)
    {
        confirmEnroll.dismiss();

        if (result)
        {
            Toast.makeText(MainActivity.this, "Enrollment success", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Enrollment cancelled", Toast.LENGTH_LONG).show();
        }

    }


}
