package rafiq.com.example.app.mmsofflineversion.DialogHelper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import rafiq.com.example.app.mmsofflineversion.R;

public class ProfileUpdateOption extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose_your_option)
                .setItems(R.array.update_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                changePassword();
                                break;
                            case 1:
                                changeMobileNumber();
                                break;
                            default:
                                 // nothing
                        }
                    }
                });
        return builder.create();
    }
    public void changePassword(){
        ChangeUserPassword c=new ChangeUserPassword();
        c.show(getFragmentManager().beginTransaction(),"update");
        //Toast.makeText(getActivity(),"Password Changed",Toast.LENGTH_SHORT).show();
    }
    public void changeMobileNumber(){
        new ChangeMobileNumber().show(getFragmentManager().beginTransaction(),"Update");
        //Toast.makeText(getActivity(),"Mobile number Changed",Toast.LENGTH_SHORT).show();
    }
}
