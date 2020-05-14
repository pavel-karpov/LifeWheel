package com.pavel_karpov.first.myapp1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class EyeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editPurpose, editAdd;
    private ImageView makePhoto;
    private EditText textPercent;
    private LinearLayout linAdd;
    private ListView listTask;
    private TextView textTask;
    Float percentAll;
    File photoFile = null;
    String currentPhotoPath = "";
    private ListViewAdapter listViewAdapter;
    private ArrayList<Task> tasks;
    int identifier;
    Display display;
    DisplayMetrics displayMetrics;
    WindowManager windowManager;
    private Observable<Bitmap> observable;
    private Observable<ArrayList<Task>> observable1;
    private Observable<Purpose> observablePurpose;
    private Disposable disposablePurpose;
    private Disposable disposable, disposable1;
    private String mPurpose, mDate, mRating, mCategory, mUUID, mPurposeDescription;
    private int mIsCompleted, mFlagCompleted;
    protected String uuidPurpose;
    protected Purpose mmPurpose = new Purpose(null, null, null, null, null, 0, 0, null,
            null);
    protected UUID uuidTask;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye);
        uuidPurpose = getIntent().getStringExtra("uuid");
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        identifier = getIntent().getIntExtra("eye", -1);
        observable1 = Observable.just(TasksLab.getInstance(EyeActivity.this).readTasksUUID(uuidPurpose));
        disposable1 = observable1.subscribeWith(new DisposableObserver<ArrayList<Task>>() {

            @Override
            public void onNext(ArrayList<Task> mTasks) {

                tasks = mTasks;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        disposable1.dispose();
        toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        final TextView textView = toolbar.findViewById(R.id.text_toolbar_add);
        if (displayMetrics.widthPixels >= 1200) {
            textView.setTextSize(26);
        } else {
            textView.setTextSize(20);
        }
        editPurpose = findViewById(R.id.edit_purpose);
        editAdd = findViewById(R.id.edit_add);
        makePhoto = findViewById(R.id.image_photo);
        textPercent = findViewById(R.id.text_percent);
        linAdd = findViewById(R.id.linear_add);
        listTask = findViewById(R.id.list_task);
        textTask = findViewById(R.id.text_add_task);
        if (identifier != 1) {
            mPurpose = getIntent().getStringExtra("purpose");
            mDate = getIntent().getStringExtra("date");
            mRating = getIntent().getStringExtra("rating");
            mCategory = getIntent().getStringExtra("category");
            mUUID = getIntent().getStringExtra("uuid");
            mIsCompleted = getIntent().getIntExtra("iscompleted", -1);
            mFlagCompleted = getIntent().getIntExtra("flagcompleted", -1);
            currentPhotoPath = getIntent().getStringExtra("photo_path");
            mPurposeDescription = getIntent().getStringExtra("purpose_description");
            editAdd.setText(mPurposeDescription);
            textTask.setText("Добавить задачу");
            textView.setText(mCategory);
            if (currentPhotoPath.equals("")) {
                makePhoto.setImageResource(android.R.drawable.ic_menu_camera);
            } else {
                makePhoto.setImageBitmap(BitmapFactory.decodeFile(currentPhotoPath));
            }
            linAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String t = editPurpose.getText().toString();
                    String t1 = editAdd.getText().toString();
                    String p = "0";
                    uuidTask = UUID.randomUUID();
                    TasksLab.getInstance(EyeActivity.this).addTask(new Task(t, t1, p, 0, 0, uuidPurpose, uuidTask.toString()));
                    tasks = TasksLab.getInstance(EyeActivity.this).readTasksUUID(uuidPurpose);
                    listViewAdapter.setAdapter(tasks);
                    listViewAdapter.notifyDataSetChanged();
                }
            });

        }
        listViewAdapter = new ListViewAdapter(EyeActivity.this, tasks);
        listTask.setAdapter(listViewAdapter);
        makePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* try {
                    captureImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //  ActivityCompat.checkSelfPermission(EyeActivity.this,Manifest.permission.CAMERA,)
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });
        observablePurpose = Observable.just(PurposeLab.getInstance(EyeActivity.this).getPurpose(mUUID));
        disposablePurpose = observablePurpose.subscribeWith(new DisposableObserver<Purpose>() {
            @Override
            public void onNext(Purpose purpose) {
                mmPurpose = purpose;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        disposablePurpose.dispose();
        Observable<Float> observableFloat = Observable.just(TasksLab.getInstance(EyeActivity.this).readTasksChecked(uuidPurpose));
        Disposable disposableFloat = observableFloat.subscribeWith(new DisposableObserver<Float>() {
            @Override
            public void onNext(Float aFloat) {
                percentAll = aFloat;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        disposableFloat.dispose();
        if (percentAll < 100) {
            textPercent.setText(percentAll.toString());
        } else if (percentAll == 100.0) {
            textPercent.setText(percentAll.toString());
            mmPurpose.setIsCompleted(1);
            PurposeLab.getInstance(EyeActivity.this).updatePurpose(mmPurpose);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
            makePhoto.setImageBitmap(bitmap);
        } else if (requestCode == 2) {
            observable = Observable.create(new ObservableOnSubscribe<Bitmap>() {
                @Override
                public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                    InputStream imageStream = getContentResolver().openInputStream(data.getData());
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    photoFile = createImageFile();
                    FileOutputStream out = new FileOutputStream(photoFile);
                    selectedImage.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.flush();
                    out.close();
                    emitter.onNext(selectedImage);
                }
            });
            disposable = observable.subscribeWith(new DisposableObserver<Bitmap>() {

                @Override
                public void onNext(Bitmap bitmap) {

                    makePhoto.setImageBitmap(BitmapFactory.decodeFile(photoFile.getAbsolutePath()));
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      /*  switch (item.getItemId()) {
            case R.id.toolbar_add_menu:
                if (percentAll < 100) {
                    textPercent.setText(percentAll.toString());
                    //   mPurpose = editPurpose.getText().toString();
                    //   mPurposeDescription = editAdd.getText().toString();
                    //    String percent = textPercent.getText().toString();
                    PurposeLab.getInstance(EyeActivity.this).updatePurpose(new Purpose(mPurpose, mDate, mRating, mCategory, mUUID, mIsCompleted, mFlagCompleted, currentPhotoPath, mPurposeDescription));
                    listViewAdapter.setAdapter(tasks);
                    listViewAdapter.notifyDataSetChanged();
                    finish();
                }
                else if (percentAll == 100.0) {
                    textPercent.setText(percentAll.toString());
                    PurposeLab.getInstance(EyeActivity.this).updatePurpose(new Purpose(mPurpose, mDate, mRating, mCategory, mUUID, 1, mFlagCompleted, currentPhotoPath, mPurposeDescription));
                    listViewAdapter.setAdapter(tasks);
                    listViewAdapter.notifyDataSetChanged();
                    finish();
                }
                break;
        }*/
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void captureImage() throws IOException {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.pavel_karpov.first.lifewheel.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                List<ResolveInfo> act = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo activity : act) {
                    grantUriPermission(activity.activityInfo.packageName, photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(intent, 1);
            }
        }
    }

    public File loadImage(Uri uriPhoto) {
        @SuppressLint("SimpleDateFormat") String temp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String imageFileName = "JPEG_" + temp + ".jpg";
        File dir = getFilesDir();
        File image = new File(dir, Objects.requireNonNull(uriPhoto.getPath()));
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String temp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String imageFileName = "JPEG_" + temp + ".jpg";
        File dir = getFilesDir();
        File image = new File(dir, imageFileName);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public class ListViewAdapter extends BaseAdapter implements Filterable {
        Context mContext;
        ArrayList<Task> mTasks;
        ArrayList<Task> mTaskFull;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, ArrayList<Task> tasks) {
            mTasks = tasks;
            this.mTaskFull = new ArrayList<>(mTasks);
            mContext = context;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Object getItem(int position) {
            return mTasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public Filter getFilter() {
            return exampleFilter;
        }

        public Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<Task> item = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    item.addAll(mTaskFull);
                } else {
                    String filter_pattern = constraint.toString().toLowerCase().trim();
                    for (Task data : mTaskFull) {
                        if (data.getmTaskDescription().contains(filter_pattern)) {
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
                mTasks.clear();
                mTasks.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };

        public class ViewHolder {
            ConstraintLayout constraintLayout;
            TextView textNumber;
            TextView editTextPercent;
            CheckBox checkBox;
        }

        ViewHolder viewHolder = null;

        @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.list_view_item, null);
                viewHolder.constraintLayout = view.findViewById(R.id.constraint_list_layout);
                viewHolder.textNumber = view.findViewById(R.id.text_listview_item1);
                viewHolder.editTextPercent = view.findViewById(R.id.text_listview_item2);
                viewHolder.checkBox = view.findViewById(R.id.check_list_target);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.textNumber.setText(position + 1 + ". " + mTasks.get(position).getmTask());
            viewHolder.editTextPercent.setText(mTasks.get(position).getmPercent());
            viewHolder.checkBox.setChecked(mTasks.get(position).getmIsCompleted() == 1);
            viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editPurpose.setText(tasks.get(position).getmTask());
                    editAdd.setText(tasks.get(position).getmTaskDescription());
                    textPercent.setText(tasks.get(position).getmPercent());
                }
            });
            viewHolder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    TasksLab.getInstance(EyeActivity.this).deleteTask(tasks.get(position));
                    tasks = TasksLab.getInstance(EyeActivity.this).readTasksUUID(uuidPurpose);
                    listViewAdapter.setAdapter(tasks);
                    listViewAdapter.notifyDataSetChanged();
                    return false;
                }
            });
            viewHolder.editTextPercent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(EyeActivity.this).inflate(R.layout.dialog_edit_percent, null);
                    final EditText editDialog = view.findViewById(R.id.editTextDialog);
                    new AlertDialog.Builder(EyeActivity.this).setTitle("Добавьте процент").setView(view).setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String text = editDialog.getText().toString();
                            if (text.length() <= 3) {
                                tasks.get(position).setmPercent(text.concat("%"));
                                TasksLab.getInstance(EyeActivity.this).updateTask(tasks.get(position));
                                Observable<ArrayList<Task>> observableTask = Observable.just(TasksLab.getInstance(EyeActivity.this).readTasksUUID(uuidPurpose));
                                Disposable disposableTask = observableTask.subscribeWith(new DisposableObserver<ArrayList<Task>>() {
                                    @Override
                                    public void onNext(ArrayList<Task> mtasks) {
                                        tasks = mtasks;
                                        listViewAdapter.setAdapter(tasks);
                                        listViewAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                                disposableTask.dispose();
                            } else {
                                Toast.makeText(EyeActivity.this, "Неверные данные", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).setNegativeButton("Отмена", null).show();
                }
            });
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mTasks.get(position).setmIsCompleted(1);
                        TasksLab.getInstance(EyeActivity.this).updateTask(tasks.get(position));
                        Float percent = Float.parseFloat(textPercent.getText().toString());
                        String percentItem = mTasks.get(position).getmPercent();
                        if (percentItem.length() == 2) {
                            float item = Float.parseFloat(percentItem.substring(0, 1));
                            if (percent + item < 100.0) {
                                textPercent.setText(String.valueOf(percent + item));
                            } else if (percent + item > 100.0) {
                                Toast.makeText(EyeActivity.this, "Процент > 100", Toast.LENGTH_SHORT).show();
                            } else if (percent + item == 100.0) {
                                textPercent.setText(String.valueOf(percent + item));
                                mmPurpose.setIsCompleted(1);
                                PurposeLab.getInstance(EyeActivity.this).updatePurpose(mmPurpose);
                            }
                        } else if (percentItem.length() == 3) {
                            float item = Float.parseFloat(percentItem.substring(0, 2));
                            if (percent + item < 100.0) {
                                textPercent.setText(String.valueOf(percent + item));
                            } else if (percent + item > 100.0) {
                                Toast.makeText(EyeActivity.this, "Процент > 100", Toast.LENGTH_SHORT).show();
                            } else if (percent + item == 100.0) {
                                textPercent.setText(String.valueOf(percent + item));
                                mmPurpose.setIsCompleted(1);
                                PurposeLab.getInstance(EyeActivity.this).updatePurpose(mmPurpose);
                            }
                        } else if (percentItem.length() == 4) {
                            float item = Float.parseFloat(percentItem.substring(0, 3));
                            if (percent + item < 100.0) {
                                textPercent.setText(String.valueOf(percent + item));
                            } else if (percent + item > 100.0) {
                                Toast.makeText(EyeActivity.this, "Процент > 100", Toast.LENGTH_SHORT).show();
                            } else if (percent + item == 100.0) {
                                textPercent.setText(String.valueOf(percent + item));
                                mmPurpose.setIsCompleted(1);
                                PurposeLab.getInstance(EyeActivity.this).updatePurpose(mmPurpose);
                            }
                        }
                    } else {
                        mTasks.get(position).setmIsCompleted(0);
                        TasksLab.getInstance(EyeActivity.this).updateTask(mTasks.get(position));
                        Float percentMinus = Float.parseFloat(textPercent.getText().toString());
                        String percentItemMinus = mTasks.get(position).getmPercent();
                        if (percentItemMinus.length() <= 2) {
                            float item = Float.parseFloat(percentItemMinus.substring(0, 1));
                            if (percentMinus - item >= 0) {
                                textPercent.setText(String.valueOf(percentMinus - item));
                            } else {
                                Toast.makeText(EyeActivity.this, "Процент < 0", Toast.LENGTH_SHORT).show();
                            }
                        } else if (percentItemMinus.length() <= 3) {
                            float item = Float.parseFloat(percentItemMinus.substring(0, 2));
                            if (percentMinus - item >= 0) {
                                textPercent.setText(String.valueOf(percentMinus - item));
                            } else {
                                Toast.makeText(EyeActivity.this, "Процент < 0", Toast.LENGTH_SHORT).show();
                            }
                        } else if (percentItemMinus.length() <= 4) {
                            float item = Float.parseFloat(percentItemMinus.substring(0, 3));
                            if (percentMinus - item >= 0) {
                                textPercent.setText(String.valueOf(percentMinus + item));
                            } else {
                                Toast.makeText(EyeActivity.this, "Процент < 0", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (mTasks.get(position).getmIsCompleted() == 1 && mTasks.get(position).getmFlagCompleted() == 0) {

                        }
                    }
                }
            });
            return view;
        }

        public void setAdapter(ArrayList<Task> task) {
            mTasks = task;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (percentAll < 100) {
            textPercent.setText(percentAll.toString());
        } else if (percentAll == 100.0) {
            textPercent.setText(percentAll.toString());
            PurposeLab.getInstance(EyeActivity.this).updatePurpose(new Purpose(mPurpose, mDate, mRating, mCategory, mUUID, 1, mFlagCompleted, currentPhotoPath, mPurposeDescription));
            listViewAdapter.setAdapter(tasks);
            listViewAdapter.notifyDataSetChanged();
        }
    }
}