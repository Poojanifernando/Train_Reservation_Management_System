package com.example.reservationmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservations;

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        // Notify that the dataset has changed, specifying the entire range
        notifyItemRangeChanged(0, reservations.size());
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        if (reservations != null && position < reservations.size()) {
            Reservation reservation = reservations.get(position);
            holder.bind(reservation);
        }
    }

    @Override
    public int getItemCount() {
        return reservations != null ? reservations.size() : 0;
    }

    static class ReservationViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;
        private TextView timeTextView;
        private TextView fromTextView;
        private TextView toTextView;
        private TextView seatsTextView;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.textViewDate);
            timeTextView = itemView.findViewById(R.id.textViewTime);
            fromTextView = itemView.findViewById(R.id.textViewdestifrom);
            toTextView = itemView.findViewById(R.id.textViewdestito);
            seatsTextView = itemView.findViewById(R.id.textViewnumseats);
        }

        public void bind(Reservation reservation) {
            dateTextView.setText("Date: " + reservation.getDate());
            timeTextView.setText("Time: " + reservation.getTime());
            fromTextView.setText("Destination From: " + reservation.getDestinationFrom());
            toTextView.setText("Destination To: " + reservation.getDestinationTo());
            seatsTextView.setText("Seats: " + reservation.getSeats());
        }
    }
}
