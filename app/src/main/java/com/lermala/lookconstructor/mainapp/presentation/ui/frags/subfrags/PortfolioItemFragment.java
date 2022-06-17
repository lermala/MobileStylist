package com.lermala.lookconstructor.mainapp.presentation.ui.frags.subfrags;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.Query;
import com.lermala.lookconstructor.mainapp.presentation.presenters.MainPresenter;
import com.lermala.lookconstructor.mainapp.presentation.ui.PhotoLoadActivity;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.mainapp.presentation.ui.Constant;
import com.lermala.lookconstructor.mainapp.data.models.PortfolioItem;
import com.squareup.picasso.Picasso;

public class PortfolioItemFragment extends Fragment {
    MainPresenter presenter;

    String TAG = "PortfolioItemFragment"; // for debugging

    private GridView grdPortfolioItem;
    private FloatingActionButton fabAdd;

    String idPortfolio; // id of portfolio that contains current files (images)

    public PortfolioItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio_item, container, false);

        presenter = new MainPresenter();

        init(view);
        design(view);

        return view;
    }

    public void showMessage(String msg){
        Toast.makeText(getContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }

    private void init(View view){
        fabAdd = (FloatingActionButton) view.findViewById(R.id.btn_add_portfolio_item);
        grdPortfolioItem = view.findViewById(R.id.grd_portfolio_items);

        // getting data (bundle)
        // String myValue = this.getArguments().getString(Constant.MESSAGE);
        //int idPortfolio = this.getArguments().getInt(Constant.PORTFOLIO_ID);
        idPortfolio = this.getArguments().getString(Constant.PORTFOLIO_ID);

        grdPortfolioItem.setAdapter(getAdapterToShowItems(presenter.getAllItemsFor(idPortfolio)));

        registerForContextMenu(grdPortfolioItem);// todo
        setOnClickImage(); // event 4 opening image in another fragment // todo

        // adding photo or file
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // забиваем данные для передачи в actvity
                Intent intent = new Intent(getActivity(), PhotoLoadActivity.class);
                intent.putExtra(Constant.PORTFOLIO_ID, idPortfolio);
                intent.putExtra(Constant.REQUEST_CODE, PhotoLoadActivity.REQUEST_CODE_LOAD_IMAGE_TO_FB);
                startActivity(intent);
                // addPortfolioItem(idPortfolio);
            }
        });

    }

    public FirebaseListAdapter getAdapterToShowItems(Query query){
        FirebaseListAdapter mAdapter;
        mAdapter = new FirebaseListAdapter<PortfolioItem>(
                getActivity(),
                PortfolioItem.class,
                R.layout.lst_portfolio_item,
                query
        )   {
            @Override
            protected void populateView(View v, PortfolioItem item, int position) {
                ImageView imageView = v.findViewById(R.id.img_portfolio_item);
                Log.d(TAG, "img: " + item.image_id);
                Picasso.get().load(item.image_id).into(imageView);
            }
        };
        return mAdapter;
    }

    private void design(View view){
        fabAdd.setColorFilter(Color.WHITE); // doing fab icon (+) white
        selectMenuItem(); // выделяем пункт меню
    }

    // this need for getting back
    private void selectMenuItem(){
        BottomNavigationView navigation = (BottomNavigationView) getActivity()
                .findViewById(R.id.bottomNavigationView);
        navigation.getMenu().getItem(0).setChecked(true);
    }

    // context menu items:
    public static final int IDM_DEL = 101; // deleting
    public static final int IDM_EDIT = 102; // редактирование
    private String contextMenuClickedImageId; // ID фото (в БД). появляется при длительном нажатии

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, IDM_EDIT, Menu.NONE, "Редактировать");
        menu.add(Menu.NONE, IDM_DEL, Menu.NONE, "Удалить");

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // Get the list
        GridView list = (GridView) v;

        // Get the list item position
        int position = info.position;

        Toast.makeText(getActivity(), "position= " + position, Toast.LENGTH_LONG)
                .show();

        contextMenuClickedImageId = ((PortfolioItem) list.getItemAtPosition(position)).getImage_id();
        Toast.makeText(getActivity(), "img_id= " + contextMenuClickedImageId, Toast.LENGTH_LONG)
                .show();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case IDM_DEL:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Удаление")
                        .setMessage("Вы уверены, что хотите удалить этот файл?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // WorkWithFirebase.deletePortfolioItemFromDB(idPortfolio, contextMenuClickedImageId); // удаление из бд
                                presenter.deleteItem(idPortfolio, contextMenuClickedImageId);
                                // удалить из хранилища TODO

                                Toast.makeText(getActivity(), "Удаление завершено", Toast.LENGTH_LONG)
                                        .show();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;

            case IDM_EDIT: // редактировать

                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void setOnClickImage(){
        grdPortfolioItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // getting clicked image
                PortfolioItem clicked = (PortfolioItem) adapterView.getItemAtPosition(i);

                String clickedId = clicked.getImage_id();
                Log.d(TAG, "onItemClick: " + clickedId);

                // забиваем данные для передачи во фрагмент
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PORTFOLIO_ITEM_IMAGE_ID, clickedId);

                // show new fragment with detailed image
                DetailedImage nextFrag = new DetailedImage(); // todo
                nextFrag.setArguments(bundle); // передаем данные (image id = libk on image)
                replaceFragment(nextFrag, getActivity());

                // show new fragment with detailed image in ALERT

            }
        });
    }

    public void getImage(){
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intentChooser, 1);
    }

    private void replaceFragment(Fragment fragment, FragmentActivity curFrag){
        // FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();//getSupportFragmentManager();
        FragmentManager fragmentManager = curFrag.getSupportFragmentManager();//getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_fl, fragment);
        fragmentTransaction.commit();
    }


}