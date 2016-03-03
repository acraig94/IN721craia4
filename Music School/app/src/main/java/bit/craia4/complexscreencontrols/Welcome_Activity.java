package bit.craia4.complexscreencontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button enterButton = (Button) findViewById(R.id.button_Enter);

        enterButton.setOnClickListener(new welcomeEnterButton());
    }

    public class welcomeEnterButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent goMainActivityIntend = new Intent(Welcome_Activity.this, MainActivity.class);
            startActivity(goMainActivityIntend);
        }
    }
}
