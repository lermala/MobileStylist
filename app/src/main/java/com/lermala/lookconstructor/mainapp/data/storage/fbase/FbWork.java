package com.lermala.lookconstructor.mainapp.data.storage.fbase;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;
import com.lermala.lookconstructor.mainapp.data.models.PortfolioItem;
import com.lermala.lookconstructor.mainapp.data.models.User;

import java.util.Map;

public class FbWork {
    private final DatabaseReference userRef;
    private final DatabaseReference porfoliosRef;
    // private final DatabaseReference userRef = FbAuth.getCurUserRef();+
    private String TAG = "FbWork";

    // private FirebaseRecyclerAdapter<Portfolio, PortfolioHolder> mAdapter = FirebaseRecyclerAdapter<Portfolio, PortfolioHolder>;

    class PortfolioHolder extends RecyclerView.ViewHolder {

        public PortfolioHolder(@NonNull View itemView) {
            super(itemView);
            // TextView name = itemView.findViewById();
            // TextView date = itemView.findViewById();
            // CircleImageView photo = itemView.findViewById();
        }
    }

    public FbWork(String uid) {
        this.userRef = FirebaseDatabase.getInstance()
                .getReference(ConstantWorkWithFirebase.USER_KEY)
                .child(uid);
        this.porfoliosRef = userRef.child(ConstantWorkWithFirebase.USER_PORTFOLIOS);
    }

    public Query getPortfolios(){
        Query query = userRef.child(ConstantWorkWithFirebase.USER_PORTFOLIOS);
        return query;
    }

    public Query getPortfolioItemsForPortfolio(String idPortfolio){
        Query query =
                userRef
                .child(ConstantWorkWithFirebase.USER_PORTFOLIOS)
                .child(idPortfolio)
                .child(ConstantWorkWithFirebase.USER_IMAGES);
        return query;
    }

    public void addPortfolio(Portfolio port) {
        porfoliosRef.push();
        String key = porfoliosRef.getKey();
        port.setId(key); // update id for portfolio
        porfoliosRef.setValue(port); // add to db
    }

    public void addPortfolio(String name) {
        porfoliosRef.push();
        String key = porfoliosRef.getKey();
        Portfolio portfolio = new Portfolio(key, name);
        porfoliosRef.setValue(portfolio);
    }

    public void deletePortfolioFromDB(String id) {
        // https://stackoverflow.com/questions/37390864/how-to-delete-from-firebase-realtime-database
        userRef
                .child(ConstantWorkWithFirebase.USER_PORTFOLIOS)
                .child(id).removeValue();
    }

    public void getPortfolioItemCount(String id) {
        // https://stackoverflow.com/questions/37390864/how-to-delete-from-firebase-realtime-database
        DatabaseReference ref = userRef
                .child(ConstantWorkWithFirebase.USER_PORTFOLIOS)
                .child(id);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return;

    }

    public void deletePortfolioItemFromDB(String idPortfolio, String idFileForDeleting) {
        // https://stackoverflow.com/questions/37390864/how-to-delete-from-firebase-realtime-database
        userRef.child(ConstantWorkWithFirebase.USER_PORTFOLIOS)
                .child(idPortfolio)
                .child(ConstantWorkWithFirebase.USER_IMAGES)
                .child(idFileForDeleting)
                .removeValue();
    }

    public void deleteFileFromFirebase() {

    }

    public void addPortfolioItem(String idPortfolio, String photoUri) {
        PortfolioItem portfolioItem = new PortfolioItem(photoUri);
        userRef.child(ConstantWorkWithFirebase.USER_PORTFOLIOS)
                .child(idPortfolio)
                .child(ConstantWorkWithFirebase.USER_IMAGES)
                .push()
                .setValue(portfolioItem)
                ;
    }

    public void editPortfolio(String idPortfolio, String newName) {
        userRef.child(ConstantWorkWithFirebase.USER_PORTFOLIOS)
                .child(idPortfolio)
                .child(ConstantWorkWithFirebase.PORTFOLIO_NAME)
                //.push()
                .setValue(newName)
                ;
    }


    public void changeUserData(String name, String date) {
        userRef.child(ConstantWorkWithFirebase.USER_NAME)
                .setValue(name);

        userRef.child(ConstantWorkWithFirebase.USER_DATE)
                .setValue(date);
    }

    public Query getUserInfo(){
        return userRef;
    }

    public User getUser() {
        final User[] currentUser = {new User()};

        userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        currentUser[0] = dataSnapshot.getValue(User.class);
                        Log.d(TAG, "onDataChange: " + currentUser[0].getName());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
        ;

        return currentUser[0];
    }

    public void saveUser(String name, String secName, String email, String image_id, Map<String, Portfolio> portfolios) {
        //String id = myRef.push().getKey();
        // String id = curUser.getUid();
//
        // User newUser = new User(id, name, secName, email, image_id, portfolios);
//
        // if (id != null) {
        //     usersRef.child(id).setValue(newUser);
        // }
        // myRef.push().setValue(newUser);
    }
}


