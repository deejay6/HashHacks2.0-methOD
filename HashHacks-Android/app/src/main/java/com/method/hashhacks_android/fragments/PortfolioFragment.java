package com.method.hashhacks_android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.method.hashhacks_android.R;
import com.method.hashhacks_android.models.LoanGiven;
import com.method.hashhacks_android.models.LoanNeeded;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableHeaderAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;

/**
 * Created by piyush0 on 28/10/17.
 */

public class PortfolioFragment extends Fragment {


    private ArrayList<LoanGiven> loanGivens;
    private View view;
    private TableView tableView;

    public PortfolioFragment() {

    }

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
    }

    private void fetchLoanGiven() {
        loanGivens = LoanGiven.getLoanGiven();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        fetchLoanGiven();
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        tableView = (TableView) view.findViewById(R.id.fragment_portfolio_table);

        TableColumnWeightModel columnModel = new TableColumnWeightModel(6);
//        columnModel.setColumnWeight(0, 2);
//        columnModel.setColumnWeight(1, 1);
//        columnModel.setColumnWeight(2, 4);
//        columnModel.setColumnWeight(3, 3);
//        columnModel.setColumnWeight(4, 2);
        tableView.setColumnModel(columnModel);
        tableView.setHeaderAdapter(new HeaderAdapter(getContext()));

        /*I dont understand the code written below. Its was suggested as a hack on github as Header Adapter was not performing well*/

        ViewTreeObserver vto = tableView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tableView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                tableView.setHeaderAdapter(new HeaderAdapter(getContext()));
            }
        });

        tableView.setDataAdapter(new TableAdapter(getContext(), loanGivens));
    }


    private class HeaderAdapter extends TableHeaderAdapter {

        HeaderAdapter(Context context) {
            super(context);
        }

        @Override
        public View getHeaderView(int columnIndex, ViewGroup parentView) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View convertView = null;
            switch (columnIndex) {
                case 0:
                    convertView = li.inflate(R.layout.table_row_tv, null);
                    TextView tv_id = (TextView) convertView;
                    tv_id.setText("ID");
                    break;

                case 1:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_person_id = (TextView) convertView;
                    tv_person_id.setText("Borrower ID");
                    break;
                case 2:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_purpose = (TextView) convertView;
                    tv_purpose.setText("Amount");

                    break;
                case 3:
                    convertView = li.inflate(R.layout.table_row_tv, null);
                    TextView tv_amount = (TextView) convertView;
                    tv_amount.setText("When");
                    break;
                case 4:
                    convertView = li.inflate(R.layout.table_row_tv, null);
                    TextView tv_interest = (TextView) convertView;
                    tv_interest.setText("Tenure");
                    break;
                case 5:
                    convertView = li.inflate(R.layout.table_row_tv, null);
                    TextView tv_tenure = (TextView) convertView;
                    tv_tenure.setText("Interest");
                    break;

            }

            return convertView;
        }
    }

    private class TableAdapter extends TableDataAdapter<LoanGiven> {

        TableAdapter(Context context, List<LoanGiven> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View convertView = null;
            switch (columnIndex) {
                case 0:
                    convertView = li.inflate(R.layout.table_row_tv, null);
                    TextView tv_id = (TextView) convertView;
                    tv_id.setText(loanGivens.get(rowIndex).getID());
                    break;


                case 1:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_person_id = (TextView) convertView;
                    tv_person_id.setText(loanGivens.get(rowIndex).getBorrowerID());
                    break;

                case 2:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_purpose = (TextView) convertView;
                    tv_purpose.setText(loanGivens.get(rowIndex).getAmount() + "");
                    break;

                case 3:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_amount_needed = (TextView) convertView;
                    tv_amount_needed.setText(loanGivens.get(rowIndex).getWhen());
                    break;

                case 4:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_interest = (TextView) convertView;
                    tv_interest.setText(loanGivens.get(rowIndex).getTenure());
                    break;

                case 5:
                    convertView = li.inflate(R.layout.table_row_tv, null);

                    TextView tv_tenure = (TextView) convertView;
                    tv_tenure.setText(loanGivens.get(rowIndex).getInterest() + "");
                    break;


            }

            return convertView;
        }
    }

}
