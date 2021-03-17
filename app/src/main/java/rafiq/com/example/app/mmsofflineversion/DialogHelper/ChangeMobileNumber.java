package rafiq.com.example.app.mmsofflineversion.DialogHelper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import rafiq.com.example.app.mmsofflineversion.Profile;
import rafiq.com.example.app.mmsofflineversion.R;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;

public class ChangeMobileNumber extends AppCompatDialogFragment {
    private AutoCompleteTextView mobile;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_change_mobile,null);
        mobile=view.findViewById(R.id.new_mobile);
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        update();
                    }
                }).setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setTitle("Update number number ");
        return builder.create();
    }
    public void update(){
        String m=mobile.getText().toString().trim();
        if(new DatabaseHelper(getActivity()).changeMobile(m)){
            Toast.makeText(getActivity(),"Mobile number changed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), Profile.class));
        }
        else {
            Toast.makeText(getActivity(),"wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
