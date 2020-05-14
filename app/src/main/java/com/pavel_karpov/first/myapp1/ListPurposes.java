package com.pavel_karpov.first.myapp1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ListPurposes extends AppCompatActivity {
    ArrayList<Purpose> mCategory,mDate;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private EditText editText;
    private RecyclerPurposeAdapter adapter;
    private TextView textView;
    Display display;
    DisplayMetrics displayMetrics;
    WindowManager windowManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_purposes);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.purpose_recycler_book);
        editText = findViewById(R.id.purpose_search_notes);
        bottomNavigationView = findViewById(R.id.purpose_bottom_book_id);
        textView = toolbar.findViewById(R.id.text_toolbar);
        final TextView textView = toolbar.findViewById(R.id.text_toolbar);
        if(displayMetrics.widthPixels>=1200){
            textView.setTextSize(26);
        }
        else {textView.setTextSize(20);}
        setSupportActionBar(toolbar);
        mCategory = new ArrayList<>();
        mDate = new ArrayList<>();
        mCategory = (ArrayList<Purpose>) getIntent().getSerializableExtra("PurposeCategory");
        if (mCategory!=null){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerPurposeAdapter(mCategory);
            recyclerView.setAdapter(adapter);
            bottomNavigationView.setItemIconTintList(null);
            bottomNavigationView.setItemRippleColor(new ColorStateList(new int[][]{{}},new int[]{R.color.colorAccent}));
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId()==R.id.action_circle){
                        Intent intent = new Intent(ListPurposes.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if (item.getItemId()==R.id.action_eye){
                        Intent intent = new Intent(ListPurposes.this,RecyclerActivity.class);
                        startActivity(intent);
                    }
                    else if (item.getItemId()==R.id.action_book){
                        Intent intent = new Intent(ListPurposes.this,BookActivity.class);
                        startActivity(intent);
                    }
                    return false;
                }
            });
        }
        mDate = (ArrayList<Purpose>) getIntent().getSerializableExtra("TodayCategory");
        if (mDate!=null){

            textView.setText("Цели на сегодня");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerPurposeAdapter(mDate);
            recyclerView.setAdapter(adapter);
            bottomNavigationView.setItemIconTintList(null);
            bottomNavigationView.setItemRippleColor(new ColorStateList(new int[][]{{}},new int[]{R.color.colorAccent}));
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId()==R.id.action_circle){
                        Intent intent = new Intent(ListPurposes.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if (item.getItemId()==R.id.action_eye){
                        Intent intent = new Intent(ListPurposes.this,RecyclerActivity.class);
                        startActivity(intent);
                    }
                    return false;
                }
            });
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
            }
        });

    }


    private class RecyclerPurposeHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView textDate;
        TextView textNote;
        CheckBox checkPorpose;

        RecyclerPurposeHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.relative_layout_note);
            textDate = itemView.findViewById(R.id.item_note_date);
            textNote = itemView.findViewById(R.id.item_note);
            checkPorpose = itemView.findViewById(R.id.check_purpose);
        }

        public void bind(Purpose purpose) {
            textDate.setText(String.format("%s :  %s" ,purpose.getmDate().substring(6,15), purpose.getmCategory()));
            textNote.setText(purpose.getmPurpose());
            checkPorpose.setChecked(purpose.getIsCompleted()==1);
        }


    }

    public class RecyclerPurposeAdapter extends RecyclerView.Adapter<RecyclerPurposeHolder> implements Filterable {
        ArrayList<Purpose> mPurposes;
        ArrayList<Purpose>mPurposesFull;
        Observable<ArrayList<Purpose>>observable;
        Observable observable1;
        Disposable disposable;
        RecyclerPurposeAdapter(ArrayList<Purpose> purposes) {
            mPurposes = purposes;
            mPurposesFull = new ArrayList<>(mPurposes);
        }

        @NonNull
        @Override
        public RecyclerPurposeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ListPurposes.this).inflate(R.layout.note_item, parent, false);
            return new RecyclerPurposeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerPurposeHolder holder, final int position) {
            holder.bind(mPurposes.get(position));
            holder.checkPorpose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {mPurposes.get(position).setIsCompleted(1);}
                    else mPurposes.get(position).setIsCompleted(0);
                    observable1 = Observable.just(PurposeLab.getInstance(ListPurposes.this).updatePurpose(mPurposes.get(position)));
                    Disposable disposable = (Disposable) observable1.subscribeWith(new DisposableObserver() {
                        @Override
                        public void onNext(Object o) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                disposable.dispose();
                }
            });
            holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   PurposeLab.getInstance(ListPurposes.this).deletePurpose(mPurposes.get(position));
                   observable = Observable.just(PurposeLab.getInstance(ListPurposes.this).readPurposeseCategory(mCategory.get(position).getmCategory()));
                   disposable = observable.subscribeWith(new DisposableObserver<ArrayList<Purpose>>() {
                       @Override
                       public void onNext(ArrayList<Purpose> purposes) {
                           mCategory=purposes;
                       }

                       @Override
                       public void onError(Throwable e) {

                       }

                       @Override
                       public void onComplete() {

                       }
                   });
                   disposable.dispose();
                   adapter.setAdapter(mCategory);
                   adapter.notifyDataSetChanged();
                    return false;
                }
            });
        }
        public void setAdapter(ArrayList<Purpose>data){ mPurposes=data; }

        @Override
        public int getItemCount() {
            return mPurposes.size();
        }
        public Filter getFilter() {
            return exampleFilter;
        }
        public Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Purpose>item = new ArrayList<>();
                if (constraint==null || constraint.length()==0)
                {
                    item.addAll(mPurposesFull);
                }
                else
                {
                    String filter_pattern = constraint.toString().toLowerCase().trim();
                    for (Purpose data:mPurposesFull) {
                        if (data.getmPurpose().contains(filter_pattern)) {
                            item.add(data);
                        }

                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = item;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mPurposes.clear();
                mPurposes.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}

