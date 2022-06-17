package com.lermala.lookconstructor.mainapp.presentation.ui.frags;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.Query;
import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;
import com.lermala.lookconstructor.mainapp.presentation.presenters.MainPresenter;
import com.lermala.lookconstructor.mainapp.presentation.ui.Constant;
import com.lermala.lookconstructor.mainapp.presentation.ui.frags.subfrags.PortfolioItemFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class PortfoliosListFragment extends Fragment {
    MainPresenter presenter;

    private ListView listView;
    private GridView gridPortfolio;
    private FloatingActionButton fabAdd;
    private ImageButton btnChangeView;

    private final int VIEW_OF_PORTFOLIO_LIST = 101;
    private final int VIEW_OF_PORTFOLIO_GRID = 102;

    private int typeOfView;

    String TAG = "PORTFOLIO"; // for debugging

    public PortfoliosListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolios_list, container, false);

        presenter = new MainPresenter();

        init(view);
        design(view);

        return view;
    }

    private void init(View view){
        // init objects
        listView = view.findViewById(R.id.lv_portfolio);
        gridPortfolio = view.findViewById(R.id.grd_portfolio);

        typeOfView = VIEW_OF_PORTFOLIO_LIST; // todo change on SharedPrefences
        setAdapter(typeOfView); // listView.setAdapter(WorkWithFirebase.getAdapterForPortfolioGrid(getActivity()));

        setOnClickItem(listView); // event for clicking item of portfolio
        setOnClickItem(gridPortfolio); // event for clicking item of portfolio
        registerForContextMenu(listView); // event for long clicking item of portfolio // TODO
        registerForContextMenu(gridPortfolio); // event for long clicking item of portfolio // TODO

        fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_portfolio);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAlertForPortfolio("Новое портфолио", true);
            }
        });

        btnChangeView = getActivity().findViewById(R.id.top_view_portfolio);
        btnChangeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo
                if (typeOfView == VIEW_OF_PORTFOLIO_GRID){
                    typeOfView = VIEW_OF_PORTFOLIO_LIST;
                } else {
                    typeOfView = VIEW_OF_PORTFOLIO_GRID;
                }

                setAdapter(typeOfView);
            }
        });

    }

    private void setAdapter(int type){
        if (type == VIEW_OF_PORTFOLIO_LIST){
            gridPortfolio.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            // gridPortfolio.setAdapter(null); // todo
            listView.setAdapter(getAdapterForPortfolio(presenter.getAllPortfolios()));
        } else if (type == VIEW_OF_PORTFOLIO_GRID) {
            // listView.setAdapter(null);
            // gridPortfolio.setVisibility(View.VISIBLE);
            // listView.setVisibility(View.GONE);
            // gridPortfolio.setAdapter(WorkWithFirebase.getAdapterForPortfolioGrid(getActivity())); // todo
        }
    }


    public FirebaseListAdapter getAdapterForPortfolio(Query query){
        FirebaseListAdapter mAdapter;
        mAdapter = new FirebaseListAdapter<Portfolio>(
                getActivity(),
                Portfolio.class,
                R.layout.lst_portfolio,
                query) {
            @Override
            protected void populateView(View v, Portfolio portfolio, int position) {
                TextView text = v.findViewById(R.id.lst_portf_name);
                text.setText(portfolio.name);

                CircleImageView circleImageView = v.findViewById(R.id.lst_potfolio_img);

                String imageId = portfolio.image_id;
                if (imageId != null) {
                    if (!imageId.equals("")){
                        Picasso.get().load(imageId).into(circleImageView);
                    }
                } else {
                    // circleImageView.setCircleBackgroundColor(Color.BLUE);
                }

            }
        };
        return mAdapter;
    }

    private void design(View view){
        fabAdd.setColorFilter(Color.WHITE); // doing fab white
        // fabAdd.setBackground(Color.); // doing fab white

        selectMenuItem(); // выделяем пункт меню
    }

    // this is need for getting back
    private void selectMenuItem(){
        BottomNavigationView navigation = (BottomNavigationView) getActivity()
                .findViewById(R.id.bottomNavigationView);
        navigation.getMenu().getItem(0).setChecked(true);
    }

    /**
     * @param isNewPortfolio true - добавление портфолио / false - редактирование портфолио
     */
    private void doAlertForPortfolio(String title, boolean isNewPortfolio){
        AlertDialog.Builder builderEdit = new AlertDialog.Builder(getContext());
        LayoutInflater li = LayoutInflater.from(getContext());
        View dialogView = li.inflate(R.layout.dialog_portfolio_add, null);

        builderEdit.setView(dialogView); // load created view

        EditText editText = dialogView.findViewById(R.id.ed_portfolio_name); // load editText from created view
        builderEdit.setTitle(title);
        // builderEdit.setMessage("Введите название портфолио");

        builderEdit.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editText.getText().toString();

                        // checking input name
                        if (TextUtils.isEmpty(name)){ // todo
                            Toast.makeText(getContext(), "Введите название портфолио", Toast.LENGTH_SHORT).show();
                        } else {
                            if (isNewPortfolio){ // добавление
                                // addPortfolio(name); // todo
                                // PortfolioUseCase.addPortfolioWithName(name);
                                presenter.addPortfolioWithName(name);
                                showMessage("Портфолио " + name + " создано");
                            } else { // изменение
                                presenter.editPortfolio(contextMenuClickedItemListId, name);
                                // PortfolioUseCase.editPortfolio(contextMenuClickedItemListId, name);
                                showMessage("Портфолио " + name + " изменено");
                            }
                        }
                    }
                });

        builderEdit.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog dialogEdit = builderEdit.create();
        dialogEdit.show();
    }



    public void showMessage(String msg){
        Toast.makeText(getContext(),
                msg,
                Toast.LENGTH_SHORT).show();
    }

    // context menu items:
    public static final int IDM_DEL = 101; //deleting
    public static final int IDM_EDIT = 102; // редактирование
    private String contextMenuClickedItemListId; // номер элемента в gridview. появляется при длительном нажатии

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(Menu.NONE, IDM_EDIT, Menu.NONE, "Редактировать");
        menu.add(Menu.NONE, IDM_DEL, Menu.NONE, "Удалить");

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // Get the list
        ListView list = (ListView) v;

        int position = info.position;
        list.getItemAtPosition(position);

        contextMenuClickedItemListId = ((Portfolio) list.getItemAtPosition(position)).getId();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case IDM_DEL:
                // TODO: добавить "Точно ли хотите удалить?" да/нет
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Удаление")
                        .setMessage("Вы уверены, что хотите удалить это портфолио?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // PortfolioUseCase.delete(contextMenuClickedItemListId);
                                presenter.deletePortfolio(contextMenuClickedItemListId);
                                showMessage("Удаление завершено");
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
                doAlertForPortfolio("Редактировать", false); // todo
                return true;
        }

        return super.onContextItemSelected(item);
    }

    /**
     * opening ability for changing result
     */
    private void setOnClickItem(AbsListView absListView){
        absListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // https://overcoder.net/q/49823/%D0%BA%D0%B0%D0%BA-%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%B0%D1%82%D1%8C-%D0%BF%D0%B5%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%BD%D1%83%D1%8E-%D0%B8%D0%B7-activity-%D0%B2%D0%BE-fragment-%D0%B8-%D0%B2%D0%B5%D1%80%D0%BD%D1%83%D1%82%D1%8C-%D0%B5%D0%B5-%D0%BE%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%BE

                // getting clicked portfolio
                Portfolio clicked = (Portfolio) adapterView.getItemAtPosition(i);
                String clickedId = clicked.getId();
                Log.d(TAG, "onItemClick: " + clickedId);

                // забиваем данные для передачи во фрагмент
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PORTFOLIO_ID, clickedId); // TODO: change name on ID

                // show new fragment with items (images/files)
                PortfolioItemFragment nextFrag = new PortfolioItemFragment();
                nextFrag.setArguments(bundle); // передаем данные (номер кликнутого элемента)

                replaceFragment(nextFrag, getActivity());
            }
        });
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