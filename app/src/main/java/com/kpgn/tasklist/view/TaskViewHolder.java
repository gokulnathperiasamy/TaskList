package com.kpgn.tasklist.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kpgn.tasklist.R;
import com.kpgn.tasklist.entity.Task;
import com.kpgn.tasklist.utility.DateTimeHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_task_content)
    TextView mTvTaskContent;

    @BindView(R.id.tv_row_updated)
    TextView mTvRowUpdated;

    public TaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(Context context, Task task) {
        mTvTaskContent.setText(task.getTaskContent());
        mTvRowUpdated.setText(DateTimeHelper.getFormattedDateTime(task.getRowUpated()));
    }
}
