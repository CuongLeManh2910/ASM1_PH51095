package com.example.asm1_and103_md19304_ph51095;




import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneAdapter extends BaseAdapter {

    List<PhoneModel> phoneModelList;

    Context context;

    //    public CarAdapter (Context context, List<CarModel> carModelList) {
//        this.context = context;
//        this.carModelList = carModelList;
//    }
    private APIService apiService;

    public PhoneAdapter(Context context, List<PhoneModel> phoneModelList, APIService apiService) {
        this.context = context;
        this.phoneModelList = phoneModelList;
        this.apiService = apiService;
    }

    @Override
    public int getCount() {
        return phoneModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return phoneModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_phone, viewGroup, false);

        // Gán view
        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvNamSX = rowView.findViewById(R.id.tvNamSX);
        TextView tvHang = rowView.findViewById(R.id.tvHang);
        TextView tvGia = rowView.findViewById(R.id.tvGia);
        Button btnEdit = rowView.findViewById(R.id.btn_sua);
        Button btnDelete = rowView.findViewById(R.id.btn_xoa);

        // Set dữ liệu
        PhoneModel phone = phoneModelList.get(position);
        tvName.setText(phone.getTen());
        tvNamSX.setText(String.valueOf(phone.getNamSX()));
        tvHang.setText(phone.getHang());
        tvGia.setText(String.valueOf(phone.getGia()));

//xử lý sk nút xóa
        btnDelete.setOnClickListener(v -> {
            String idToDelete = String.valueOf(phone.get_id()); // Chắc chắn `get_id()` trả về đúng giá trị
            Call<List<PhoneModel>> callDelete = apiService.deletePhone(phone.get_id());
            callDelete.enqueue(new Callback<List<PhoneModel>>() {
                @Override
                public void onResponse(Call<List<PhoneModel>> call, Response<List<PhoneModel>> response) {
                    if (response.isSuccessful()) {
                        // Cập nhật lại danh sách sau khi xóa
                        phoneModelList.remove(position);
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<PhoneModel>> call, Throwable t) {
                    Log.e("CarAdapter", t.getMessage());
                }
            });
        });


        return rowView;
    }
}