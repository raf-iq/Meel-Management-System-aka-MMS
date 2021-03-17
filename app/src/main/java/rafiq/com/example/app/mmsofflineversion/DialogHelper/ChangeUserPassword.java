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

public class ChangeUserPassword extends AppCompatDialogFragment {
    AutoCompleteTextView cPass,nPass,rnPass;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_change_password,null);
        //View view=getLayoutInflater().inflate(R.layout.fragment_change_password,null);
        cPass=view.findViewById(R.id.c_pass);
        nPass=view.findViewById(R.id.n_pass);
        rnPass=view.findViewById(R.id.rn_pass);
        builder.setView(view);
        builder.setTitle("Change your password");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edit();
            }
        }).setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }
    public void edit(){
        String c_pass=cPass.getText().toString().trim();
        String n_pass=nPass.getText().toString().trim();
        String rn_pass=rnPass.getText().toString().trim();
        boolean err=false;
        if(c_pass.isEmpty()){
            cPass.setError(getString(R.string.error_field_required));
            err=true;
        }
        if(n_pass.isEmpty()){
            nPass.setError(getString(R.string.error_field_required));
            err=true;
        }
        if(rn_pass.isEmpty()){
            rnPass.setError(getString(R.string.error_field_required));
            err=true;
        }
        if(!n_pass.equals(rn_pass)){
            rnPass.setError(getString(R.string.password_mismatched));
            err=true;
        }
        if(!err){
            if(new DatabaseHelper(getActivity()).changePassword(c_pass,n_pass)){
                startActivity(new Intent(getActivity(), Profile.class));
                Toast.makeText(getActivity(),"Password Upadated", Toast.LENGTH_SHORT).show();
            }
            else {
                AlertDialog.Builder b=new AlertDialog.Builder(getActivity());
                b.setMessage("Your current password is wrong");
                b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog d=b.create();
                d.show();
               // Toast.makeText(getActivity(),"Password is not Updated",Toast.LENGTH_SHORT).show();
                cPass.setError(getString(R.string.error_current_password));
                err=true;
            }
        }
        else {
            AlertDialog.Builder b=new AlertDialog.Builder(getActivity());
            b.setMessage("New Password mismatched, Try again");
            b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog d=b.create();
            d.show();
            //Toast.makeText(getActivity(),"Error somewhere",Toast.LENGTH_SHORT).show();
        }
    }
}
