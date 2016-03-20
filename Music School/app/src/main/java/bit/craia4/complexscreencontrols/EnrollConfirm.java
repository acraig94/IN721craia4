package bit.craia4.complexscreencontrols;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class EnrollConfirm extends DialogFragment
{
    public EnrollConfirm(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Are you sure you want to enroll?");
        builder.setPositiveButton("Yes", new PositiveButtonHandler());
        builder.setNegativeButton("No", new NegativeButtonHandler());

        Dialog myDialog = builder.create();
        return myDialog;
    }

    public class PositiveButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.getConfirmData(true);
        }
    }

    public class NegativeButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.getConfirmData(false);
        }
    }

}
