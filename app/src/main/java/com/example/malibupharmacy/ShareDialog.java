package com.example.malibupharmacy;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ShareDialog extends DialogFragment {
    EditText emailText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.share_dialog, null);
        emailText = view.findViewById(R.id.editTextTextEmailAddress);

        builder.setView(view).setCancelable(false);

        builder.setMessage("Enter email")
                .setPositiveButton("Share", (dialog, id) -> {
                    if (emailText.getText().toString().equals("")) {
                        Toast.makeText(requireContext(), "Email is required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //share the data
                    ((ProfileActivity) getActivity()).shareData(emailText.getText().toString());

                    this.dismiss();
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    this.dismiss();
                });

        return builder.create();
    }
}
