package rafiq.com.example.app.mmsofflineversion.DialogHelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import rafiq.com.example.app.mmsofflineversion.R;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.DatabaseHelper;
import rafiq.com.example.app.mmsofflineversion.SQLiteHelper.Member;

public class ShowDialog {
    public ShowDialog(final Context context, String title, final Member member, final Button btn){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(new DatabaseHelper(context).deleteMember(member)){
                            new ShowDialog(context,"Member deleted");
                            btn.setEnabled(false);
                            btn.setText("Member deleted");
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
    public ShowDialog(final Context context, final View view, final int id, final Button btn){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle(R.string.prompt_due)
                .setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AutoCompleteTextView money=view.findViewById(R.id.taka);
                        if(money.getText().toString().isEmpty()){
                            new ShowDialog(context,"Please, enter amount");
                        }
                        else {
                            int taka = Integer.parseInt(money.getText().toString());
                            if (!new DatabaseHelper(context).payDuePayment(id, taka)) {
                                new ShowDialog(context, "Something wrong, Try Again");
                            } else {
                                ShowDialog s=new ShowDialog(context, "Payment Complete");
                                btn.setText("paid Done");
                                btn.setEnabled(false);
                            }
                        }
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();

    }
    public ShowDialog(final Context context, String error){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage(error)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
    public ShowDialog(Context context,String msg, int balance){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(msg)
                .setMessage("Balance = " + String.valueOf(balance) + " TK")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
    public ShowDialog(final Context context, final Object obj, String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(context, (Class<?>) obj));
                    }
                });
        builder.create().show();
    }
}
