package com.example.malibupharmacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>{
    Context context;
    ArrayList<User> userArrayList;

    public NotificationsAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.AdditionalInfo.setText(user.AdditionalInfo);
        holder.Allergies.setText(user.Allergies);
        holder.DeliveryInfo.setText(user.DeliveryInfo);
        holder.InsuranceInfo.setText(user.InsuranceInfo);
        holder.Tests.setText(user.Tests);
        holder.UnderlyingConditions.setText(user.UnderlyingConditions);
        holder.UserInformation.setText(user.UserInformation);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView AdditionalInfo, Allergies, DeliveryInfo, InsuranceInfo,Tests,UnderlyingConditions,UserInformation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            AdditionalInfo = itemView.findViewById(R.id.tvAdditionalInfo);
            Allergies = itemView.findViewById(R.id.tvAllergies);
            DeliveryInfo = itemView.findViewById(R.id.tvDeliveryInfo);
            InsuranceInfo = itemView.findViewById(R.id.tvInsuranceInfo);
            Tests = itemView.findViewById(R.id.tvTests);
            UnderlyingConditions = itemView.findViewById(R.id.tvUnderlyingConditions);
            UserInformation = itemView.findViewById(R.id.tvUserInformation);
        }
    }
}
