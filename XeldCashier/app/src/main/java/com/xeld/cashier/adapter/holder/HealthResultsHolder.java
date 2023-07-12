package com.xeld.cashier.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.trecyclerview.holder.BaseHolder;
import com.xeld.cashier.R;
import com.xeld.cashier.base.BaseAbsViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthResultsHolder extends BaseAbsViewHolder<String, HealthResultsHolder.ViewHolder> {

    public HealthResultsHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_health_results;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull String item) {
       int position = viewHolder.getAdapterPosition();

        viewHolder.health_count_rl.setVisibility(position==0? View.VISIBLE: View.GONE);


//        viewHolder.timeTv.setText("08:46:02");
//        viewHolder.position.setText("校长");
//        viewHolder.temperature.setText("36.2°C");
//        CommonViewUtils.loadImg(viewHolder.iv1, "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg");
//        CommonViewUtils.loadImg(viewHolder.iv2, "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg");
//        CommonViewUtils.setOnClick(viewHolder.rootRl, view -> {
//            // TODO: 2020/1/6 携带对象跳转到详情页面
//         /*   Intent intent = new Intent(mContext, PersonDetailActivity.class);
//            intent.putExtra("bean", item);
//            mContext.startActivity(intent);*/
//        });
    }

    public  class ViewHolder extends BaseHolder {

        @BindView(R.id.health_count_rl)
        RelativeLayout health_count_rl;
//
//        @BindView(R.id.position)
//        TextView position;
//
//        @BindView(R.id.temperature)
//        TextView temperature;
//
//        @BindView(R.id.iv1)
//        ImageView iv1;
//
//        @BindView(R.id.iv2)
//        ImageView iv2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}