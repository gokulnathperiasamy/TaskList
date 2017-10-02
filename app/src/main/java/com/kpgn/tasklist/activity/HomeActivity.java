package com.kpgn.tasklist.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kpgn.tasklist.R;
import com.kpgn.tasklist.entity.Task;
import com.kpgn.tasklist.test.TestData;
import com.kpgn.tasklist.view.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.fab_new_task)
    FloatingActionButton mFabNewTask;

    @BindView(R.id.rv_task_list)
    RecyclerView mRvTaskList;

    @BindView(R.id.task_list_empty_container)
    View mTaskListEmpty;

    private List<Task> taskList = new ArrayList<>();
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setupToolbar();
        setupData();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupData() {
        taskList = TestData.getTaskListData();
    }

    private void setupRecyclerView() {
        tasksAdapter = new TasksAdapter(this, taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRvTaskList.setLayoutManager(mLayoutManager);
        mRvTaskList.setItemAnimator(new DefaultItemAnimator());
        mRvTaskList.setAdapter(tasksAdapter);
        checkIfDataAvailable();
        setRVSwipeListener();
    }

    private void checkIfDataAvailable() {
        if (taskList == null || taskList.size() == 0) {
            mTaskListEmpty.setVisibility(View.VISIBLE);
            mRvTaskList.setVisibility(View.GONE);
        } else {
            mTaskListEmpty.setVisibility(View.GONE);
            mRvTaskList.setVisibility(View.VISIBLE);
        }
        tasksAdapter.notifyDataSetChanged();
    }

    private void setRVSwipeListener() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                confirmAndDelete(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRvTaskList);
    }

    private void confirmAndDelete(final int position) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.confirm_delete, taskList.get(position).getTaskContent()))
                .setIcon(R.mipmap.ic_warning)
                .setPositiveButton(getString(R.string.dialog_action_delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        deleteItem(position);
                    }
                })
                .setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        checkIfDataAvailable();
                    }
                })
                .show();
    }

    private void deleteItem(int position) {
        taskList.remove(position);
        checkIfDataAvailable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.fab_new_task)
    public void addNewTask(View view) {
        showCustomDialogForNewTask();
    }

    private void showCustomDialogForNewTask() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        editText.setLayoutParams(layoutParams);
        linearLayout.addView(editText);
        linearLayout.setPadding(60, 20, 60, 0);

        alert.setView(linearLayout);
        alert.setTitle(getString(R.string.add_new_task));
        alert.setPositiveButton(getString(R.string.dialog_action_save), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String taskString = editText.getText().toString().trim();
                if (taskString.length() > 0) {
                    updateTask(taskString);
                }
            }
        });
        alert.show();
    }

    private void updateTask(String taskString) {
        Task task = new Task();
        task.setTaskID(taskString + "|" + System.currentTimeMillis());
        task.setTaskContent(taskString);
        task.setRowUpated(System.currentTimeMillis());
        taskList.add(task);
        tasksAdapter.notifyDataSetChanged();
        checkIfDataAvailable();
    }
}
